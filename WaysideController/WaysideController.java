/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
    public HashMap<Integer,Switch> switches;
    public Block crossings;
    //TrackModel tm;
    PLCClass plc;
    
    public WaysideController(String id){
        this.id = id;
        switches = new HashMap<Integer, Switch>();
        blocks = new HashMap<Integer, Block>();
        crossings = null;
        plc = null;
        //plc = new PLCClass("default.plc");
    }
    
    public void setPLC(String plcString){
        plc = new PLCClass(plcString);
    }
    
    public boolean changeSwitch(Switch sb){
        Block block1 = sb.getswitchedBlockBlock();
        Block block2 = sb.getunSwitchedBlockBlock();
        if(plc.checkSwitch(block1, block2, sb.getSwitchBlock())){
            return true;
        }
        else {
            return false;
        }
    }
    
    public Switch isSwitchOption(Block b){
        for(Switch s : switches.values()){
            if(s.getswitchedBlockBlock().equals(b) || s.getunSwitchedBlockBlock().equals(b)){
                return s;
            }
        }
        return null;
    }
    
    public boolean toggleSwitchLight(){
        return true;
    }
    
    public Switch getSwitch(int sbNum){
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
    
    public void addBlock(Block b){
        blocks.put(b.getBlockNumber(), b);
    }
    
    public void addSwitch(Switch s){
        switches.put(Integer.parseInt(s.getSwitchNumber()), s);
    }
    
    public void toggleCrossing(){
        //If the crossing should be closed/down/red
        if(plc.checkCrossing(crossings))
        {
            if(!crossings.getCrossing().getCrossingState(crossings.getLine())){
                crossings.getCrossing().toggleCrossing();
            }
        }
        //Crossing should be open/green/up
        else{
            if(crossings.getCrossing().getCrossingState(crossings.getLine())){
                crossings.getCrossing().toggleCrossing();
            }
        }
    }
    
    public String toString(){
        return id;
    }
}
