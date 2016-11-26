/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.waysidecontrol;

import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author tak72_000
 */
public class WaysideController {
    String id;
    String username = "admin";
    String password = "admin";
    public HashMap<Integer,Block>  blocks;
    public HashMap<Integer,SwitchBlock> switches;
    public HashMap<Integer,Block> crossings;
    //TrackModel tm;
    PLC plc;
    
    public WaysideController(){
        id = new String();
        switches = new HashMap<Integer, SwitchBlock>();
        blocks = new HashMap<Integer, Block>();
        crossings = new HashMap<Integer, Block>();
        plc = null;
    }
    
    public void init(String id, HashMap<Integer, Block> startBlocks, HashMap<Integer, SwitchBlock> startSwitches){
        this.id = id;
        blocks = startBlocks;
        switches = startSwitches;
        crossings = new HashMap<Integer, Block>();
        
        for(Block b: startBlocks.values()){
            if(b.isCrossing){
                crossings.put(b.blockNumber, b);
            }
        }
    }
    
    public void setPLC(String plcString){
        if(plcString.equals("OneWayPLC")){
            plc = new OneWayPLC();
        } 
        else if(plcString.equals("TwoWayPLC")){
            plc = new TwoWayPLC();
        }
    }
    
    public boolean changeSwitch(SwitchBlock sb){
        return plc.changeSwitch(sb);
    }
    
    public boolean toggleSwitchLight(){
        return true;
    }
    
    public SwitchBlock getSwitch(int sbNum){
        if(switches.containsKey(sbNum)){
            return switches.get(sbNum);
        }
        return null;
    }
    
    
    
    public boolean containsSwitch(int sbNum){
        if(switches.containsKey(sbNum)){
            return true;
        }
        return false;
    }
    
    public Block getBlock(int blockNum){
        if(blocks.containsKey(blockNum)){
            return blocks.get(blockNum);
        }
        return null;
    }
}
