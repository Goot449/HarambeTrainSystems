/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.harambe.waysidecontroller;

//import com.sun.xml.internal.ws.wsdl.writer.document.Message;
import java.util.*;

/**
 *
 * @author tak72_000
 */
public class WaysideControllerHandler implements Runnable{
    //LinkedBlockingQueue<Message> messages;
    ArrayList<WaysideController> controllers;
    ArrayList<Block> redBlocks;
    ArrayList<Block> greenBlocks;
    ArrayList<Switch> redSwitches;
    ArrayList<Switch> greenSwitches;
    Track myTrack;
    
    public WaysideControllerHandler(Track track){
        myTrack = track;
        redBlocks = track.getRedBlocks();
        greenBlocks = track.getGreenBlocks();
        redSwitches = track.getRedSwitches();
        greenSwitches = track.getGreenSwitches();
        
        LinkedHashMap<Integer, Block> redHashMap = new LinkedHashMap<Integer, Block>();
        for(Block b : redBlocks){
            redHashMap.put(b.getBlockNumber(), b);
        }
        
        LinkedHashMap<Integer, Block> greenHashMap = new LinkedHashMap<Integer, Block>();
        for(Block b : greenBlocks){
            greenHashMap.put(b.getBlockNumber(), b);
        }
        
        LinkedHashMap<Integer, Switch> switches = new LinkedHashMap<Integer, Switch>();
        for(Switch s : redSwitches){
            switches.put(Integer.parseInt(s.getSwitchNumber()), s);
        }
        for(Switch s : greenSwitches){
            switches.put(Integer.parseInt(s.getSwitchNumber()), s);
        }
        
        initControllers(redHashMap, greenHashMap, switches);
    }
    
     /*public WaysideControllerHandler(LinkedHashMap<Integer, Block> redBlocks, LinkedHashMap<Integer, Block> greenBlocks, LinkedHashMap<Integer, Switch> switches){
        controllers = new ArrayList<WaysideController>();
        
        //Get the red and green blocks
        this.redBlocks = new ArrayList<Block>(redBlocks.values());
        this.greenBlocks = new ArrayList<Block>(greenBlocks.values());
        
        //Need to create the controllers and assign the switches...
        //Initialize our 4 wayside controllers
        for(int i = 0; i < 2; i++){
            //Red line wayside controllers
            WaysideController wc = new WaysideController("red" + i);
            if(i == 0){
                for(int b : redBlocks.keySet()){
                    if((b <= 37 || b == 77)){
                        wc.addBlock(redBlocks.get(b));
                    }    
                }
                
                //wc.addSwitch(switches.get(6));
                //wc.addSwitch(switches.get(7));
                //wc.addSwitch(switches.get(8));
                //wc.addSwitch(switches.get(12));
            }
            else {
                for(int b : redBlocks.keySet()){
                    if(b > 37){
                        wc.addBlock(redBlocks.get(b));
                    }    
                }
                //wc.crossings = redBlocks.get(47);
                //wc.addSwitch(switches.get(9));
                //wc.addSwitch(switches.get(10));
                //wc.addSwitch(switches.get(11));
            }
            controllers.add(wc);
            
            //Green wayside controllers
            wc = new WaysideController("green" + i);
            if(i == 0){
                for(int b : greenBlocks.keySet()){
                    if((b >= 63 && b < 122) || b == 151){
                        wc.addBlock(greenBlocks.get(b));
                    }    
                }
                //wc.addSwitch(switches.get(0));
                //wc.addSwitch(switches.get(1));
                //wc.addSwitch(switches.get(2));
            }
            else {
                for(int b : greenBlocks.keySet()){
                    if((b < 63 || b >= 122) && b != 151){
                        wc.addBlock(greenBlocks.get(b));
                    }    
                }
                //wc.crossings = greenBlocks.get(19);
                
                //wc.addSwitch(switches.get(3));
                //wc.addSwitch(switches.get(4));
                //wc.addSwitch(switches.get(5));
            }
            controllers.add(wc);
        }
    }
*/
    
    public void initControllers(LinkedHashMap<Integer, Block> redBlocks, LinkedHashMap<Integer, Block> greenBlocks, LinkedHashMap<Integer, Switch> switches){
        //Need to create the controllers and assign the switches...
        //Initialize our 4 wayside controllers
        for(int i = 0; i < 2; i++){
            //Red line wayside controllers
            WaysideController wc = new WaysideController("red" + i);
            if(i == 0){
                for(int b : redBlocks.keySet()){
                    if((b <= 37 || b == 77)){
                        wc.addBlock(redBlocks.get(b));
                    }    
                }
                
                wc.addSwitch(switches.get(6));
                wc.addSwitch(switches.get(7));
                wc.addSwitch(switches.get(8));
                wc.addSwitch(switches.get(12));
            }
            else {
                for(int b : redBlocks.keySet()){
                    if(b > 37){
                        wc.addBlock(redBlocks.get(b));
                    }    
                }
                wc.crossings = redBlocks.get(47);
                wc.addSwitch(switches.get(9));
                wc.addSwitch(switches.get(10));
                wc.addSwitch(switches.get(11));
            }
            controllers.add(wc);
            
            //Green wayside controllers
            wc = new WaysideController("green" + i);
            if(i == 0){
                for(int b : greenBlocks.keySet()){
                    if((b >= 63 && b < 122) || b == 151){
                        wc.addBlock(greenBlocks.get(b));
                    }    
                }
                wc.addSwitch(switches.get(0));
                wc.addSwitch(switches.get(1));
                wc.addSwitch(switches.get(2));
            }
            else {
                for(int b : greenBlocks.keySet()){
                    if((b < 63 || b >= 122) && b != 151){
                        wc.addBlock(greenBlocks.get(b));
                    }    
                }
                wc.crossings = greenBlocks.get(19);
                
                wc.addSwitch(switches.get(3));
                wc.addSwitch(switches.get(4));
                wc.addSwitch(switches.get(5));
            }
            controllers.add(wc);
        }
    }
    
    public void run(){
        try{
            while(true){
                //Update the track every 500 ms
                Thread.sleep(500);
                
                //System.out.println("Stuck");
                
                //TrackModel.
                LinkedHashMap<Integer, Block> redTemp = new LinkedHashMap<Integer, Block>();
                LinkedHashMap<Integer, Block> greenTemp = new LinkedHashMap<Integer, Block>();
                ArrayList<Block> redTempBlocks = new ArrayList<Block>(redTemp.values());
                ArrayList<Block> greenTempBlocks = new ArrayList<Block>(greenTemp.values());
                
                //Every 500 ms we look through all blocks for changes
                //If change occurred, we will update the wayside controller and check if switch necessary
                for(Block b: redTempBlocks){
                    //If a red block has changed, update within the wayside appropriate
                    if(b.isBlockOccupied() != (redBlocks.get(b.getBlockNumber())).isBlockOccupied()){
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
                    if(b.isBlockOccupied() != (greenBlocks.get(b.getBlockNumber())).isBlockOccupied()){
                        WaysideController temp = findCorrectWayside(b.getBlockNumber(), "Green");
                        temp.addBlock(b);
                        //If status of switch changes, we must see if changing switch is necessary
                        if(b.isSwitch()){
                            temp.changeSwitch(b.getSwitch());
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
            
        }
    }
    
    public void sendAuthority(int blockNumber, int authority, double speed){
        
    }
    
    public WaysideController findCorrectWayside(int blockNumber, String line){
        for(WaysideController wc : controllers){
            if(wc.getBlock(blockNumber) != null){
                if(wc.getBlock(blockNumber).getLine().equals(line))
                    return wc;
            }
        }
        return null;
    }
    
}
