package com.harambe.waysidecontroller;

import com.harambe.trackmodel.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author tak72_000
 */
public class WaysideControllerHandler implements Runnable {

    public Thread t;
    public LinkedBlockingQueue<String> messages;
    ArrayList<WaysideController> controllers;
    LinkedHashMap<Integer, Block> oldRedBlocks;
    LinkedHashMap<Integer, Block> oldGreenBlocks;
    ArrayList<Switch> redSwitches;
    ArrayList<Switch> greenSwitches;
    Track myTrack;
    WaysideControlUI UI;
    private HashMap<Integer, Block> finalBlocks;

    public WaysideControllerHandler(Track track) {
        if (t == null) {
            t = new Thread(this, "thread");
        }
        controllers = new ArrayList<>();
        messages = new LinkedBlockingQueue<>();
        myTrack = track;
        finalBlocks = new HashMap<>();

        oldRedBlocks = new LinkedHashMap<>();

        for (Block b : track.getRedBlocks()) {
            oldRedBlocks.put(b.getBlockNumber(), new Block(b));
        }

        oldGreenBlocks = new LinkedHashMap<>();

        for (Block b : track.getGreenBlocks()) {
            oldGreenBlocks.put(b.getBlockNumber(), new Block(b));
        }

        redSwitches = track.getRedSwitches();
        greenSwitches = track.getGreenSwitches();

        LinkedHashMap<String, Switch> switches = new LinkedHashMap<>();

        //Switches are directly manipulated by me
        for (Switch s : redSwitches) {
            switches.put(s.getSwitchNumber(), s);
        }
        for (Switch s : greenSwitches) {
            switches.put(s.getSwitchNumber(), s);
        }

        initControllers(oldRedBlocks, oldGreenBlocks, switches);
    }

    public final void initControllers(LinkedHashMap<Integer, Block> newRedBlocks, LinkedHashMap<Integer, Block> newGreenBlocks, LinkedHashMap<String, Switch> newSwitches) {
        //Need to create the controllers and assign the switches...
        //Initialize our 4 wayside controllers
        for (int i = 0; i < 2; i++) {
            //Red line wayside controllers
            WaysideController wc = new WaysideController("red" + i, "red");
            if (i == 0) {
                for (int b : newRedBlocks.keySet()) {
                    if ((b <= 36 || b == 72 || b == 77 || b == 78)) {
                        wc.addBlock(newRedBlocks.get(b));
                    }
                }

                wc.addSwitch(newSwitches.get("Switch 6"));
                wc.addSwitch(newSwitches.get("Switch 7"));
                wc.addSwitch(newSwitches.get("Switch 8"));
                wc.addSwitch(newSwitches.get("Switch 12"));
            } else {
                for (int b : newRedBlocks.keySet()) {
                    if (b > 36 && b != 72 && b != 77 && b != 78) {
                        wc.addBlock(newRedBlocks.get(b));
                    }
                }
                wc.crossing = newRedBlocks.get(47);
                wc.addSwitch(newSwitches.get("Switch 9"));
                wc.addSwitch(newSwitches.get("Switch 10"));
                wc.addSwitch(newSwitches.get("Switch 11"));
            }
            controllers.add(wc);

            //Green wayside controllers
            wc = new WaysideController("green" + i, "green");
            if (i == 0) {
                for (int b : newGreenBlocks.keySet()) {
                    if ((b >= 62 && b < 121) || b == 150 || b == 154 || b == 155) {
                        wc.addBlock(newGreenBlocks.get(b));
                    }
                }
                wc.addSwitch(newSwitches.get("Switch 0"));
                wc.addSwitch(newSwitches.get("Switch 1"));
                wc.addSwitch(newSwitches.get("Switch 2"));
            } else {
                for (int b : newGreenBlocks.keySet()) {
                    if ((b < 62 || b >= 121) && b != 150 && b != 154 && b != 155) {
                        wc.addBlock(newGreenBlocks.get(b));
                    }
                }
                wc.crossing = newGreenBlocks.get(19);

                wc.addSwitch(newSwitches.get("Switch 3"));
                wc.addSwitch(newSwitches.get("Switch 4"));
                wc.addSwitch(newSwitches.get("Switch 5"));
            }
            controllers.add(wc);
        }
        UI = new WaysideControlUI(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            boolean running = true;
            while (running) {
                //Update the track every 500 ms
                Thread.sleep(250);
                updateUI();
                messages.clear();

                if (myTrack.getPowerFailure()) {
                    running = false;
                    break;
                }

                //Get the new status of track
                ArrayList<Block> redTempBlocks = myTrack.getRedBlocks();
                ArrayList<Block> greenTempBlocks = myTrack.getGreenBlocks();

                //Every 250 ms we look through all blocks for changes
                //If change occurred, we will update the wayside controller and check if switch necessary
                for (Block b : redTempBlocks) {

                    //If a block changes that was marked as the destination to one of the trains, we update the finalBlocks map and track
                    if (b.getFinalBlock()) {
                        for (Integer i : finalBlocks.keySet()) {
                            if (finalBlocks.get(i) != null) {
                                if (finalBlocks.get(i).getBlockNumber() == b.getBlockNumber()) {
                                    finalBlocks.put(i, null);
                                }
                            }
                        }
                    }

                    if (b.isBroken()) {

                    }

                    //If a red block has changed, update within the wayside appropriate
                    if (b.isBlockOccupied() != (oldRedBlocks.get(b.getBlockNumber())).isBlockOccupied()) {

                        WaysideController temp = findCorrectWayside(b.getBlockNumber(), "Red");
                        temp.addBlock(new Block(b));
                        oldRedBlocks.put(b.getBlockNumber(), new Block(b));

                        //If status of switch changes, we must see if changing switch is necessary
                        if (b.isSwitch()) {
                            temp.changeSwitch(b.getSwitch());
                        } else if (temp.isSwitchOption(b) != null) {
                            temp.changeSwitch(temp.isSwitchOption(b));
                        } //If status around crossing changes, see if changing crossing is necessary
                        else if (b.getBlockNumber() == 45 || b.getBlockNumber() == 46 || b.getBlockNumber() == 47 || b.getBlockNumber() == 48 || b.getBlockNumber() == 49) {
                            System.out.println("Trying to toggle crossing");
                            temp.toggleCrossing();
                        }
                    }
                }

                for (Block b : greenTempBlocks) {
                    if (b.isBlockOccupied() != (oldGreenBlocks.get(b.getBlockNumber())).isBlockOccupied()) {
                        WaysideController temp = findCorrectWayside(b.getBlockNumber(), "Green");
                        temp.addBlock(new Block(b));
                        oldGreenBlocks.put(b.getBlockNumber(), new Block(b));

                        //If status of switch changes, we must see if changing switch is necessary
                        if (b.isSwitch()) {
                            temp.changeSwitch(b.getSwitch());
                        } else if (temp.isSwitchOption(b) != null) {
                            temp.changeSwitch(temp.isSwitchOption(b));
                        } //If status around crossing changes, see if changing crossing is necessary
                        else if (b.getBlockNumber() == 17 || b.getBlockNumber() == 18 || b.getBlockNumber() == 19 || b.getBlockNumber() == 20 || b.getBlockNumber() == 21) {
                            temp.toggleCrossing();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAuthority(int trainID, Block destinationBlock, double speed) {
        messages.add("CTC authority suggestion to train " + trainID + " to block " + destinationBlock.getBlockNumber() + " on the " + destinationBlock.getLine() + " line with speed of " + Double.toString(speed));
        Block trainBlock = myTrack.getBlock(trainID);

        if (trainBlock == null) {
            return;
        }

        Block nxtBlock = trainBlock.peek();

        //On same wayside
        if (findCorrectWayside(nxtBlock.getBlockNumber(), nxtBlock.getLine()).equals(findCorrectWayside(destinationBlock.getBlockNumber(), destinationBlock.getLine()))) {
            WaysideController wc = findCorrectWayside(nxtBlock.getBlockNumber(), nxtBlock.getLine());
            myTrack.getBlock(trainID).setSeen(1);
            if (wc.checkAuthority(nxtBlock.getBlockNumber(), destinationBlock.getBlockNumber(), myTrack)) {
                int authority = wc.getNumberBlocks(destinationBlock.getLine(), destinationBlock, nxtBlock);
                trainBlock.setAuthority(authority + 1);
                trainBlock.setCommandedSpeed(speed);

                //Update track so that the destination is marked as such; this is important for authority 
                finalBlocks.put(trainID, destinationBlock);
                destinationBlock.setFinalBlock();
            }
        } else if (nxtBlock.getLine().equals(("red"))) {
            WaysideController wc = findCorrectWayside(nxtBlock.getBlockNumber(), nxtBlock.getLine());
            int check = 0;
            if (wc.getBlock(36) != null) {
                check = 36;
            } else {
                check = 37;
            }
            myTrack.getBlock(trainID).setSeen(1);
            if (wc.checkAuthority(nxtBlock.getBlockNumber(), check, myTrack)) {
                WaysideController temp = findCorrectWayside(destinationBlock.getBlockNumber(), destinationBlock.getLine());
                myTrack.getBlock(check, "red").setSeen(1);
                if (check == 36) {
                    check = 37;
                } else {
                    check = 36;
                }
                if (temp.checkAuthority(check, destinationBlock.getBlockNumber(), myTrack)) {
                    System.out.println(myTrack.getBlock(trainID).getBlockNumber());
                    System.out.println(destinationBlock.getBlockNumber());

                    int authority = temp.getNumberBlocks("red", destinationBlock, myTrack.getBlock(trainID));
                    System.out.println(myTrack.getStringRoute("red", destinationBlock));
                    System.out.println(authority);
                    trainBlock.setAuthority(authority + 1);
                    trainBlock.setCommandedSpeed(speed);

                    //Update track so that the destination is marked as such; this is important for authority 
                    finalBlocks.put(trainID, destinationBlock);
                    destinationBlock.setFinalBlock();
                }

            }
        } else {
            WaysideController wc = findCorrectWayside(nxtBlock.getBlockNumber(), nxtBlock.getLine());
            int check = 0;
            if (wc.getBlock(62) != null) {
                check = 62;
            } else {
                check = 63;
            }
            myTrack.getBlock(trainID).setSeen(1);
            if (wc.checkAuthority(nxtBlock.getBlockNumber(), check, myTrack)) {
                WaysideController temp = findCorrectWayside(destinationBlock.getBlockNumber(), destinationBlock.getLine());
                myTrack.getBlock(check, "red").setSeen(1);
                if (check == 62) {
                    check = 63;
                } else {
                    check = 62;
                }
                if (temp.checkAuthority(check, destinationBlock.getBlockNumber(), myTrack)) {
                    System.out.println(myTrack.getBlock(trainID).getBlockNumber());
                    System.out.println(destinationBlock.getBlockNumber());

                    int authority = temp.getNumberBlocks("green", destinationBlock, myTrack.getBlock(trainID));
                    System.out.println(myTrack.getStringRoute("green", destinationBlock));
                    System.out.println(authority);
                    trainBlock.setAuthority(authority + 1);
                    trainBlock.setCommandedSpeed(speed);

                    //Update track so that the destination is marked as such; this is important for authority 
                    finalBlocks.put(trainID, destinationBlock);
                    destinationBlock.setFinalBlock();
                }

            }
        }
    }

    private void updateUI() {
        UI.updateUI();
    }

    public void toggleSwitch(String switchNumber) {
        WaysideController wc = findSwitch(switchNumber);
        wc.changeSwitch(wc.getSwitch(switchNumber));
    }

    public Track ctcBlockRequest() {
        return myTrack;

    }

    public void dispatchTrain(int trainID, Block destinationBlock, double speed) {
        //Block destinationBlock = myTrack.getBlock(destinationBlockNumber, line);

        String line = destinationBlock.getLine();

        // code goes here.
        messages.add("CTC dispatch order to Block " + destinationBlock.getBlockNumber() + " on the " + destinationBlock.getLine() + " line with speed of " + Double.toString(speed));
        if (line.equals("red")) {
            WaysideController initialWayside = findCorrectWayside(78, line);
            if (initialWayside.equals(findCorrectWayside(destinationBlock.getBlockNumber(), line))) {
                if (initialWayside.checkAuthority(78, destinationBlock.getBlockNumber(), myTrack)) {
                    //Dispatch train and place in the yard
                    //Blocks speed is set as commanded by CTC, authority in number of blocks is computed
                    System.out.println("Go ahead and dispatch");
                    myTrack.placeTrain("red", 1);
                    int authority = initialWayside.getNumberBlocks("red", destinationBlock, myTrack.getBlock(78, "red"));
                    myTrack.commandAuthority("red", authority, 78);

                    myTrack.getBlock(1).setCommandedSpeed(speed);

                    //Update track so that the destination is marked as such; this is important for authority 
                    finalBlocks.put(trainID, destinationBlock);
                    destinationBlock.setFinalBlock();
                }
            } //Need to communicate between two wayside
            else if (initialWayside.checkAuthority(78, 36, myTrack)) {
                WaysideController other = findCorrectWayside(destinationBlock.getBlockNumber(), line);
                myTrack.getBlock(36, "red").setSeen(1);
                if (other.checkAuthority(37, destinationBlock.getBlockNumber(), myTrack)) {
                    //Dispatch train
                    System.out.println("Go ahead and dispatch");
                    myTrack.placeTrain("red", 1);
                    int authority = other.getNumberBlocks("red", destinationBlock, myTrack.getBlock(78, "red"));
                    myTrack.commandAuthority("red", authority, 78);
                    myTrack.getBlock(1).setCommandedSpeed(speed);

                    //Update track so that the destination is marked as such; this is important for authority 
                    finalBlocks.put(trainID, destinationBlock);
                    destinationBlock.setFinalBlock();
                }
            }
        } //Green line
        else {
            WaysideController initialWayside = findCorrectWayside(155, line);
            if (initialWayside.equals(findCorrectWayside(destinationBlock.getBlockNumber(), line))) {
                if (initialWayside.checkAuthority(155, destinationBlock.getBlockNumber(), myTrack)) {
                    //Dispatch train
                    System.out.println("Go ahead and dispatch");
                    myTrack.placeTrain("green", 1);
                    int authority = initialWayside.getNumberBlocks("green", destinationBlock, myTrack.getBlock(155, "green"));
                    myTrack.commandAuthority("green", authority, 155);
                    myTrack.getBlock(1).setCommandedSpeed(speed);

                    //Update track so that the destination is marked as such; this is important for authority 
                    finalBlocks.put(trainID, destinationBlock);
                    destinationBlock.setFinalBlock();
                }
            } //Need to communicate between two wayside
            else if (initialWayside.checkAuthority(155, 62, myTrack)) {
                WaysideController other = findCorrectWayside(destinationBlock.getBlockNumber(), line);
                myTrack.getBlock(62, "green").setSeen(1);
                if (other.checkAuthority(61, destinationBlock.getBlockNumber(), myTrack)) {
                    //Dispatch train
                    System.out.println("Go ahead and dispatch");
                    myTrack.placeTrain("green", 1);
                    int authority = initialWayside.getNumberBlocks("green", destinationBlock, myTrack.getBlock(155, "green"));
                    myTrack.commandAuthority("green", authority, 155);
                    myTrack.getBlock(1).setCommandedSpeed(speed);

                    //Update track so that the destination is marked as such; this is important for authority 
                    finalBlocks.put(trainID, destinationBlock);
                    destinationBlock.setFinalBlock();
                }
            }
        }
    }

    public void manualSwitch(String switchNumber) {
        WaysideController temp = findSwitch(switchNumber);
        temp.changeSwitch(temp.getSwitch(switchNumber));
    }

    public WaysideController findCorrectWayside(int blockNumber, String line) {
        for (WaysideController wc : controllers) {
            if (wc.getBlock(blockNumber) != null) {
                if (wc.getBlock(blockNumber).getLine().equalsIgnoreCase(line)) {
                    return wc;
                }
            }
        }
        return null;
    }

    public WaysideController findSwitch(String switchNumber) {
        for (WaysideController wc : controllers) {
            if (wc.getSwitch(switchNumber) != null) {
                return wc;
            }
        }
        return null;
    }

}
