package com.harambe.waysidecontroller;

import com.harambe.trackmodel.*;
import java.util.*;


/**
 *
 * @author tak72_000
 */
public class WaysideControllerHandler implements Runnable{
    //LinkedBlockingQueue<Message> messages;
    ArrayList<WaysideController> controllers;
    LinkedHashMap<Integer, Block> oldRedBlocks;
    LinkedHashMap<Integer, Block> oldGreenBlocks;
    ArrayList<Switch> redSwitches;
    ArrayList<Switch> greenSwitches;
    Track myTrack;
    WaysideControlUI UI;
    
    public WaysideControllerHandler(Track track){
        controllers = new ArrayList<WaysideController>();
        myTrack = track;
        
        oldRedBlocks = new LinkedHashMap<Integer, Block>();
        
        for(Block b: track.getRedBlocks()){
            oldRedBlocks.put(b.getBlockNumber(), new Block(b));
        }
        
        oldGreenBlocks = new LinkedHashMap<Integer, Block>();
        
        for(Block b: track.getGreenBlocks()){
            oldGreenBlocks.put(b.getBlockNumber(), new Block(b));
        }
        
        redSwitches = track.getRedSwitches();
        greenSwitches = track.getGreenSwitches();
        
        LinkedHashMap<String, Switch> switches = new LinkedHashMap<String, Switch>();
        
        //Switches are directly manipulated by me
        for(Switch s : redSwitches){
            switches.put(s.getSwitchNumber(), s);
        }
        for(Switch s : greenSwitches){
            switches.put(s.getSwitchNumber(), s);
        }
        
        initControllers(oldRedBlocks, oldGreenBlocks, switches);
        UI = new WaysideControlUI();
        UI.init(this);   
    }
    
    public void initControllers(LinkedHashMap<Integer, Block> newRedBlocks, LinkedHashMap<Integer, Block> newGreenBlocks, LinkedHashMap<String, Switch> newSwitches){
        //Need to create the controllers and assign the switches...
        //Initialize our 4 wayside controllers
        for(int i = 0; i < 2; i++){
            //Red line wayside controllers
            WaysideController wc = new WaysideController("red" + i, "red");
            if(i == 0){
                for(int b : newRedBlocks.keySet()){
                    if((b <= 36 || b == 78)){
                        wc.addBlock(newRedBlocks.get(b));
                    } 
                }
                
                wc.addSwitch(newSwitches.get("Switch 6"));
                wc.addSwitch(newSwitches.get("Switch 7"));
                wc.addSwitch(newSwitches.get("Switch 8"));
                wc.addSwitch(newSwitches.get("Switch 12"));
            }
            else {
                for(int b : newRedBlocks.keySet()){
                    if(b > 36 && b != 78){
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
            if(i == 0){
                for(int b : newGreenBlocks.keySet()){
                    if((b >= 62 && b < 121) || b == 150 || b == 154 || b == 155){
                        wc.addBlock(newGreenBlocks.get(b));
                    }    
                }
                wc.addSwitch(newSwitches.get("Switch 0"));
                wc.addSwitch(newSwitches.get("Switch 1"));
                wc.addSwitch(newSwitches.get("Switch 2"));
            }
            else {
                for(int b : newGreenBlocks.keySet()){
                    if((b < 62 || b >= 121) && b != 150 && b != 154 && b != 155){
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
    }
    
    public void run(){
        try{
            while(true){
                //Update the track every 500 ms
                Thread.sleep(250);
                
                //Get the new status of track
                ArrayList<Block> redTempBlocks = myTrack.getRedBlocks();
                ArrayList<Block> greenTempBlocks = myTrack.getGreenBlocks();
                
                //Every 500 ms we look through all blocks for changes
                //If change occurred, we will update the wayside controller and check if switch necessary
                for(Block b: redTempBlocks){
                    //If a red block has changed, update within the wayside appropriate
                    if(b.isBlockOccupied() != (oldRedBlocks.get(b.getBlockNumber())).isBlockOccupied()){
                        WaysideController temp = findCorrectWayside(b.getBlockNumber(), "Red");
                        temp.addBlock(b);
                        
                        //If status of switch changes, we must see if changing switch is necessary
                        if(b.isSwitch()){
                            temp.changeSwitch(b.getSwitch());
                        }
                        else if(temp.isSwitchOption(b) != null){
                            temp.changeSwitch(temp.isSwitchOption(b));
                        }
                        //If status around crossing changes, see if changing crossing is necessary
                        else if(b.getBlockNumber() == 45 || b.getBlockNumber() == 46 || b.getBlockNumber() == 47 || b.getBlockNumber() == 48 || b.getBlockNumber() == 49){
                            temp.toggleCrossing();
                        }
                        
                    }
                }
                
                for(Block b: greenTempBlocks){
                    if(b.isBlockOccupied() != (oldGreenBlocks.get(b.getBlockNumber())).isBlockOccupied()){
                        WaysideController temp = findCorrectWayside(b.getBlockNumber(), "Green");
                        temp.addBlock(b);
                        //If status of switch changes, we must see if changing switch is necessary
                        if(b.isSwitch()){
                            temp.changeSwitch(b.getSwitch());
                        }
                        else if(temp.isSwitchOption(b) != null){
                            temp.changeSwitch(temp.isSwitchOption(b));
                        }
                        //If status around crossing changes, see if changing crossing is necessary
                        else if(b.getBlockNumber() == 17 || b.getBlockNumber() == 18 || b.getBlockNumber() == 19 || b.getBlockNumber() == 20 || b.getBlockNumber() == 21){
                            temp.toggleCrossing();
                        }
                    }
                }
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void sendAuthority(int trainID, Block destinationBlock, double speed){
        Block trainBlock = myTrack.getBlock(trainID);
        Block nxtBlock = trainBlock.peek();
        //On same wayside
        if(findCorrectWayside(nxtBlock.getBlockNumber(), nxtBlock.getLine()).equals(findCorrectWayside(destinationBlock.getBlockNumber(), destinationBlock.getLine()))){
            WaysideController wc = findCorrectWayside(nxtBlock.getBlockNumber(), nxtBlock.getLine());
            if(wc.checkAuthority(nxtBlock.getBlockNumber(), destinationBlock.getBlockNumber())){
                trainBlock.setAuthority(destinationBlock.getBlockNumber());
            }
        }
        else{
            if(nxtBlock.getLine().equals(("red"))){
                WaysideController wc = findCorrectWayside(nxtBlock.getBlockNumber(), nxtBlock.getLine());
                int check = 0;
                if(wc.getBlock(36) != null){
                    check = 36;
                }
                else{
                    check = 37;
                }
                if(wc.checkAuthority(nxtBlock.getBlockNumber(), check)){
                    WaysideController temp = findCorrectWayside(destinationBlock.getBlockNumber(), destinationBlock.getLine());
                    if(check == 36){
                        check = 37;
                    }
                    else{
                        check = 36;
                    }
                    if(temp.checkAuthority(check, destinationBlock.getBlockNumber())){
                        trainBlock.setAuthority(destinationBlock.getBlockNumber());
                    }
                    
                }
            }
        }
    }
    
    private void updateUI(){
        UI.updateUI();
    }
    
    public void toggleSwitch(String switchNumber){
        WaysideController wc = findSwitch(switchNumber);
        wc.changeSwitch(wc.getSwitch(switchNumber));
    }
    

    public Track ctcBlockRequest(){
        return myTrack;

    }
    
    public boolean dispatchTrain(Block destinationBlock, double speed){
        String line = destinationBlock.getLine();
        if(line.equals("red")){
            WaysideController initialWayside = findCorrectWayside(78, line);
            if(initialWayside.equals(findCorrectWayside(destinationBlock.getBlockNumber(), line))){
                if(initialWayside.checkAuthority(78, destinationBlock.getBlockNumber())){
                    //Dispatch train
                    System.out.println("Go ahead and dispatch");
                    myTrack.placeTrain("red", 1);
                    myTrack.commandAuthority("red", 100, 78);
                    return true;
                }
            }
            //Need to communicate between two wayside
            else{
                if(initialWayside.checkAuthority(78, 36)){
                    WaysideController other = findCorrectWayside(destinationBlock.getBlockNumber(), line);
                    if(other.checkAuthority(37, destinationBlock.getBlockNumber())){
                        //Dispatch train
                        System.out.println("Go ahead and dispatch");
                        myTrack.placeTrain("red", 1);
                        myTrack.commandAuthority("red", 100, 78);
                        return true;
                    }
                }
            }
        }
        //Green line
        else{
            WaysideController initialWayside = findCorrectWayside(155, line);
            if(initialWayside.equals(findCorrectWayside(destinationBlock.getBlockNumber(), line))){
                if(initialWayside.checkAuthority(155, destinationBlock.getBlockNumber())){
                    //Dispatch train
                    System.out.println("Go ahead and dispatch");
                    myTrack.placeTrain("green", 1);
                    myTrack.commandAuthority("green", 100, 155);
                    return true;
                    
                }
            }
            //Need to communicate between two wayside
            else{
                if(initialWayside.checkAuthority(155, 36)){
                    WaysideController other = findCorrectWayside(destinationBlock.getBlockNumber(), line);
                    if(other.checkAuthority(37, destinationBlock.getBlockNumber())){
                        //Dispatch train
                        System.out.println("Go ahead and dispatch");
                        myTrack.placeTrain("green", 1);
                        myTrack.commandAuthority("green", 100, 155);
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public void manualSwitch(String switchNumber){
        WaysideController temp = findSwitch(switchNumber);
        temp.changeSwitch(temp.getSwitch(switchNumber));
    }
    
    public WaysideController findCorrectWayside(int blockNumber, String line){
        for(WaysideController wc : controllers){
            if(wc.getBlock(blockNumber) != null){
                if(wc.getBlock(blockNumber).getLine().equalsIgnoreCase(line))
                    return wc;
            }
        }
        return null;
    }
    
    public WaysideController findSwitch(String switchNumber){
        for(WaysideController wc : controllers){
            if(wc.getSwitch(switchNumber) != null){
                return wc;
            }
        }
        return null;
    }
    
}
