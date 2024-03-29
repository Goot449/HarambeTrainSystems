/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harambe.trackmodel;

import java.io.*;
import java.util.*;
import com.harambe.traincontroller.*;
import com.harambe.trainmodel.*;

/**
 *
 * @author Owner
 */
public class Track {

    private Block redYard = null;
    private Block greenYard = null;
    private Crossing lineCrossing = null;

    private ArrayList<Block> redBlocks = new ArrayList<Block>();
    private ArrayList<Block> greenBlocks = new ArrayList<Block>();
    private ArrayList<Switch> redSwitches = new ArrayList<Switch>();
    private ArrayList<Switch> greenSwitches = new ArrayList<Switch>();
    private TrainController trainController;
    private TrainControllerGUI trainControllerGUI;
    private TrainModel trainModel;
    private HashMap<Integer, Train> trains;

    ArrayList<Block> trainBlocks = new ArrayList<>();

    private double coeffFriction;
    private int weather;
    private boolean powerFailure;

    public Track() throws IOException {

        loadTrack("RedLine.csv");
        loadTrack("GreenLine.csv");

        try {
            trains = new HashMap<>();
            for (int i = 1; i < 401; i++) {
                Train t = new Train(2, i, trainModel);
                trains.put(i, t);
                trainController.addTrain(t);
            }
        } catch (Exception e) {

        }

        /*TrackModelGUI myGUI = new TrackModelGUI();
		myGUI.initialize(this);*/
    }

    public void loadTrack(String csvIn) throws IOException {

        String inFile = csvIn;
        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        String splitStrings[];
        ArrayList<Switch> currentSwitches = null;
        ArrayList<Block> currentAll = null;
        Block currentBlock = null;
        Block currentYard = null;

        //Read until end of file 
        while (reader.ready()) {

            splitStrings = reader.readLine().split(",");
            //System.out.println(Arrays.toString(splitStrings));			
            if (currentBlock == null && splitStrings[0].equals("red")) {
                currentSwitches = redSwitches;
                currentAll = redBlocks;
            } else if (currentBlock == null && splitStrings[0].equals("green")) {
                currentSwitches = greenSwitches;
                currentAll = greenBlocks;
            }

            currentBlock = new Block(splitStrings, currentBlock);
            currentAll.add(currentBlock);

            if (currentBlock.isCrossing()) {

                if (lineCrossing == null) {
                    lineCrossing = new Crossing(currentBlock);
                } else {
                    lineCrossing.addBlock(currentBlock);
                }

                currentBlock.addCrossing(lineCrossing);
            }

            //Connect all switch blocks and put them into ArrayList
            switchMaker(splitStrings, currentBlock, currentSwitches);
            if (splitStrings[6].equals("FROM YARD") || splitStrings[6].equals("TO YARD/FROM YARD")) {
                if (splitStrings[0].equals("green")) {
                    greenYard = currentBlock;
                } else {
                    redYard = currentBlock;
                }
            }
        }

        //printSwitchList(currentSwitches);
        //Setup switch connections 
        for (Switch setupSwitch : currentSwitches) {
            setupSwitch.setup();
        }

        //printSwitchList(currentSwitches);
        //TEST CURRENT BLOCKS AS WELL AS SWITCH TOGGLING. 
        //printBlockList(currentAll);
        //Setup switch connections 
        for (Switch setupSwitch : currentSwitches) {
            setupSwitch.toggleSwitch();
        }

        //printSwitchList(currentSwitches);
        //printBlockList(currentAll);
        //TEST FOR PROPER ROUTING! 
        //printSwitchList(currentSwitches);
        /*System.out.println();
		Block traverseBlock = redYard;
		Block lastTraverse = null;
		if(traverseBlock != null)
			System.out.println(traverseBlock.getSection()+traverseBlock.getBlockNumber());
		while(traverseBlock !=null)
		{
			//System.out.println(traverseBlock);
			lastTraverse = traverseBlock;
			//printBlockList(currentAll);
			traverseBlock = traverseBlock.traverse();
			System.out.println(traverseBlock.getSection() + traverseBlock.getBlockNumber());
			if(traverseBlock == lastTraverse)
			{
				System.out.println("toggling");
				traverseBlock.toggleSwitch();
			}
		}
         */
    }

    public void printSwitchList(ArrayList<Switch> printList) {

        System.out.println();
        for (Switch currentSwitch : printList) {
            System.out.println(currentSwitch.getSwitchNumber());
            System.out.println("Switch: " + currentSwitch.getSwitchBlock().getSection() + currentSwitch.getSwitchBlock().getBlockNumber());
            printBlockConnections(currentSwitch.getSwitchBlock());
            System.out.println("switchedBlock: " + currentSwitch.getswitchedBlockBlock().getSection() + currentSwitch.getswitchedBlockBlock().getBlockNumber());
            printBlockConnections(currentSwitch.getswitchedBlockBlock());
            System.out.println("unSwitchedBlock: " + currentSwitch.getunSwitchedBlockBlock().getSection() + currentSwitch.getunSwitchedBlockBlock().getBlockNumber());
            printBlockConnections(currentSwitch.getunSwitchedBlockBlock());
            System.out.println();
            //printBlockConnections()
        }
    }

    public void printBlockConnections(Block block) {

        Block next;
        Block previous;
        next = block.getNext();
        previous = block.getPrevious();
        System.out.print(block.getSection() + block.getBlockNumber());
        System.out.print(" next: ");
        if (next != null) {
            System.out.print(next.getSection() + next.getBlockNumber() + " ");
        } else {
            System.out.print(next + " ");
        }

        System.out.print("previous: ");
        if (previous != null) {
            System.out.print(previous.getSection() + previous.getBlockNumber() + " ");
        } else {
            System.out.print(previous + " ");
        }

        System.out.println();
    }

    public void printBlockList(ArrayList<Block> printList) {

        System.out.println();
        for (Block block : printList) {
            printBlockConnections(block);
        }
    }

    public ArrayList<String> getStringRoute(String line, Block destination) {

        ArrayList<Block> pathBlocks = new ArrayList<Block>();
        ArrayList<String> pathBlockStrings = new ArrayList<String>();
        Block currentBlock = null;

        if (line.equals("red")) {
            currentBlock = redYard;
        } else {
            currentBlock = greenYard;
        }

        pathBlocks.add(currentBlock);

        while (currentBlock.getBlockNumber() != destination.getBlockNumber()) {
            //System.out.println(currentBlock.getBlockNumber());

            Block lastTraverse = currentBlock;
            //System.out.println(currentBlock.getSection() + currentBlock.getBlockNumber() + " " + currentBlock.getStation());

            //Special case; couldn't figure out fix
            if(currentBlock.getBlockNumber() == 16 && currentBlock.getLine().equals("red") && (destination.getBlockNumber() < 16 && destination.getBlockNumber() > 9) && currentBlock.peek().getBlockNumber() == 1){
                currentBlock.toggleSwitch();
            }
            if(currentBlock.getBlockNumber() == 16 && currentBlock.getLine().equals("red") && (destination.getBlockNumber() < 9 || destination.getBlockNumber() == 78) && currentBlock.peek().getBlockNumber() == 15){
                currentBlock.toggleSwitch();
            }
            
            currentBlock = currentBlock.traverse();

            if (lastTraverse == currentBlock) {
                currentBlock.toggleSwitch();
            } else {
                pathBlocks.add(currentBlock);
                pathBlockStrings.add(currentBlock.getBlockNumber() + "," + currentBlock.getSection());
            }
            
        }

        currentBlock.traverse();
        currentBlock.setSeen(0);

//		String routeBlocks;
//		for(Block path: pathBlocks){
//                    routeBlocks = path.getBlockNumber() + "," + path.getSection() + "," + path.getBlockLength() + "," + path.getSpeedLimit();
//                    
//		}
        //System.out.println(pathBlockStrings.toString());
        return pathBlockStrings;

        //"blockNumber, section, blockLength, speed limit" in each string, separated by commas.
    }

    private void printAllSwitches(ArrayList<Switch> currentSwitches) {
        for (Switch temp : currentSwitches) {
            //temp.printBlocks();	
        }
    }

    private void switchMaker(String[] splitStrings, Block currentBlock, ArrayList<Switch> currentSwitches) {

        if (splitStrings[11].length() > 0) {

            boolean newSwitch = true;
            Switch existingSwitch = null;
            for (Switch checkSwitches : currentSwitches) {

                if (checkSwitches.getSwitchNumber().equals(splitStrings[11])) {
                    existingSwitch = checkSwitches;
                    newSwitch = false;
                }
            }

            if (newSwitch) {
                currentSwitches.add(new Switch(currentBlock));
            } else {
                existingSwitch.addBlock(currentBlock);
            }
        }
    }

    public void breakBlock(String line, int blockNum) {
        getBlock(blockNum, line).toggleBroken();
    }

    public void setWeather(int weatherType) {
        //TODO
    }

    public boolean commandCrossingDown(int blockNumber) {
        return false;
    }

    public void commandAuthority(String line, int commandedAuthority, int blockNumber) {
        getBlock(blockNumber, line).setAuthority(commandedAuthority);
    }

    public Block toggleSwitch(String line, int blockNumber) {
        //Now block level
        return null;
    }

    public boolean closeBlock(String line, int blockNumber) {
        getBlock(blockNumber, line).closeBlock();
        return false;
    }

    public void commandSpeed(String line, double commandedSpeed, int blockNumber) {
        getBlock(blockNumber, line).setCommandedSpeed(commandedSpeed);
    }

    public boolean updateDistance(int trainID, double distance) {

        for (int i = 0; i < trainBlocks.size(); i++) {
            if (trainBlocks.get(i).getTrainID() == trainID) {

                Block nextBlock = trainBlocks.get(i).moveTrain(distance);
                if (nextBlock != trainBlocks.get(i)) {
                    trainBlocks.set(i, nextBlock);
                    trainBlocks.remove(i);
                    trainBlocks.add(nextBlock);
                    return true;
                }
            }
        }
        return false;

    }

    public void placeTrain(String line, int trainID) {

        Block trainBlock = null;
        line = line.toLowerCase();

        if (trainController == null) {
            trainController = new TrainController();
        }
        if (trainModel == null) {
            try {
                trainModel = new TrainModel(this);
            } catch (Exception e) {

            }
        }
//        TrainThread trainThread = new TrainThread();
//        trainThread.init(trainID);
//        trainThread.run();
//        trainThreads.add(trainThread);

        trainController.addTrain(trains.get(trainID));
        trainModel.addTrain(trains.get(trainID));

        trainController.setStartControl(true);
        if (trainControllerGUI == null){
            trainControllerGUI = new TrainControllerGUI();
        }
        trainControllerGUI.addTrain(trainController.trainList, trainController.trainStateList);
        trainControllerGUI.setVisible(true);

        trainModel.setVisible(true);

        //System.out.println(line);
        if (line.equals("red")) {
            trainBlock = redYard.placeTrain(trainID, 0);
        } else {
            trainBlock = greenYard.placeTrain(trainID, 0);
        }

        trainBlocks.add(trainBlock);
    }

    public synchronized Block getBlock(int blockNumber, String line) {

        Block returnBlock = null;
        line = line.toLowerCase();
        if (line.equals("red")) {

            for (Block currentBlock : redBlocks) {

                if (currentBlock.getBlockNumber() == blockNumber) {
                    returnBlock = currentBlock;
                }
            }
        } else {

            for (Block currentBlock : greenBlocks) {

                if (currentBlock.getBlockNumber() == blockNumber) {
                    returnBlock = currentBlock;
                }
            }
        }

        return returnBlock;
    }

    public synchronized Block getBlock(int TrainID) {

        for (Block trainer : trainBlocks) {

            if (trainer.getTrainID() == TrainID) {
                return trainer;
            }
        }

        return null;
    }

    public void showBeacon(int blockNumber, String line) {
        getBlock(blockNumber, line).setBeaconOn();
    }

    public String[] getGUIBlocks() {

        int size = redBlocks.size() + greenBlocks.size();
        int cnt = 0;

        String[] allNames = new String[size];

        for (Block reds : redBlocks) {
            allNames[cnt] = reds.getLine() + " " + reds.getSection() + " " + reds.getBlockNumber();
            cnt++;
        }

        for (Block greens : greenBlocks) {
            allNames[cnt] = greens.getLine() + " " + greens.getSection() + " " + greens.getBlockNumber();
            cnt++;
        }

        return allNames;
    }

    public String[] getGUISwitches() {

        int size = redSwitches.size() + greenSwitches.size();
        int cnt = 0;

        String[] allNames = new String[size];
        for (Switch reds : redSwitches) {
            allNames[cnt] = reds.getSwitchNumber();
            cnt++;
        }

        for (Switch greens : greenSwitches) {
            allNames[cnt] = greens.getSwitchNumber();
            cnt++;
        }

        return allNames;
    }

    public Switch getSwitch(String switchNum) {

        for (Switch reds : redSwitches) {

            if (reds.getSwitchNumber().equals(switchNum)) {
                return reds;
            }
        }

        for (Switch greens : greenSwitches) {

            if (greens.getSwitchNumber().equals(switchNum)) {
                return greens;
            }
        }

        return null;
    }

    public void setFriction(double frictionIn) {

        for (Block reds : redBlocks) {
            reds.setFriction(frictionIn);
        }

        for (Block greens : greenBlocks) {
            greens.setFriction(frictionIn);
        }
    }

    public void setPowerFailure() {
        powerFailure = true;
        System.out.println("SYSTEM POWER FAILURE");
    }

    public boolean getPowerFailure() {
        return powerFailure;
    }

    public ArrayList<Block> getRedBlocks() {
        return redBlocks;
    }

    public ArrayList<Block> getGreenBlocks() {
        return greenBlocks;
    }

    public ArrayList<Switch> getRedSwitches() {
        return redSwitches;
    }

    public ArrayList<Switch> getGreenSwitches() {
        return greenSwitches;
    }
}
