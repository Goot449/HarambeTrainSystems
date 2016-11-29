/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import com.sun.xml.internal.ws.wsdl.writer.document.Message;
import java.util.*;

/**
 *
 * @author tak72_000
 */
public class WaysideControllerHandler implements Runnable{
    //LinkedBlockingQueue<Message> messages;
    ArrayList<WaysideController> controllers;
    ArrayList<Block> oldRedBlocks;
    ArrayList<Block> oldGreenBlocks;
    ArrayList<Switch> redSwitches;
    ArrayList<Switch> greenSwitches;
    Track myTrack;
    
    public WaysideControllerHandler(Track track){
        controllers = new ArrayList<WaysideController>();
        myTrack = track;
        
        oldRedBlocks = new ArrayList<Block>();
        
        for(Block b: track.getRedBlocks()){
            oldRedBlocks.add(new Block(b));
        }
        
        oldGreenBlocks = new ArrayList<Block>();
        for(Block b: track.getGreenBlocks()){
            oldGreenBlocks.add(new Block(b));
        }
        redSwitches = track.getRedSwitches();
        greenSwitches = track.getGreenSwitches();
        
        LinkedHashMap<Integer, Block> redHashMap = new LinkedHashMap<Integer, Block>();
        for(Block b : oldRedBlocks){
            redHashMap.put(b.getBlockNumber(), b);
        }
        
        LinkedHashMap<Integer, Block> greenHashMap = new LinkedHashMap<Integer, Block>();
        for(Block b : oldGreenBlocks){
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
    
    public void initControllers(LinkedHashMap<Integer, Block> newRedBlocks, LinkedHashMap<Integer, Block> newGreenBlocks, LinkedHashMap<Integer, Switch> newSwitches){
        //Need to create the controllers and assign the switches...
        //Initialize our 4 wayside controllers
        for(int i = 0; i < 2; i++){
            //Red line wayside controllers
            WaysideController wc = new WaysideController("red" + i);
            if(i == 0){
                for(int b : newRedBlocks.keySet()){
                    if((b <= 36 || b == 77)){
                        wc.addBlock(newRedBlocks.get(b));
                    }    
                }
                
                wc.addSwitch(newSwitches.get(6));
                wc.addSwitch(newSwitches.get(7));
                wc.addSwitch(newSwitches.get(8));
                wc.addSwitch(newSwitches.get(12));
            }
            else {
                for(int b : newRedBlocks.keySet()){
                    if(b > 36){
                        wc.addBlock(oldRedBlocks.get(b));
                    }    
                }
                wc.crossing = oldRedBlocks.get(46);
                wc.addSwitch(newSwitches.get(9));
                wc.addSwitch(newSwitches.get(10));
                wc.addSwitch(newSwitches.get(11));
            }
            controllers.add(wc);
            
            //Green wayside controllers
            wc = new WaysideController("green" + i);
            if(i == 0){
                for(int b : newGreenBlocks.keySet()){
                    if((b >= 62 && b < 121) || b == 150 || b == 154 || b == 155){
                        wc.addBlock(newGreenBlocks.get(b));
                    }    
                }
                wc.addSwitch(newSwitches.get(0));
                wc.addSwitch(newSwitches.get(1));
                wc.addSwitch(newSwitches.get(2));
            }
            else {
                for(int b : newGreenBlocks.keySet()){
                    if((b < 62 || b >= 121) && b != 150 && b != 154 && b != 155){
                        wc.addBlock(newGreenBlocks.get(b));
                    }    
                }
                wc.crossing = newGreenBlocks.get(18);
                
                wc.addSwitch(newSwitches.get(3));
                wc.addSwitch(newSwitches.get(4));
                wc.addSwitch(newSwitches.get(5));
            }
            controllers.add(wc);
        }
    }
    
    public void run(){
        try{
            while(true){
                //Update the track every 500 ms
                Thread.sleep(500);
                
                //Get the new status of track
                ArrayList<Block> redTempBlocks = myTrack.getRedBlocks();
                ArrayList<Block> greenTempBlocks = myTrack.getGreenBlocks();
                
                //Every 500 ms we look through all blocks for changes
                //If change occurred, we will update the wayside controller and check if switch necessary
                for(Block b: redTempBlocks){
                    //If a red block has changed, update within the wayside appropriate
                    if(b.isBlockOccupied() != (oldRedBlocks.get(b.getBlockNumber()-1)).isBlockOccupied()){
                        WaysideController temp = findCorrectWayside(b.getBlockNumber(), "Red");
                        System.out.println(temp);
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
                    if(b.isBlockOccupied() != (oldGreenBlocks.get(b.getBlockNumber()-1)).isBlockOccupied()){
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
    
    public void sendAuthority(int blockNumber, int authority, double speed){
        
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
    
}
