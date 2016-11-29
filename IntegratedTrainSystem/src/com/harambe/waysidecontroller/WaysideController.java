/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    public Block crossing;
    PLC plc;
    
    public WaysideController(String id){
        this.id = id;
        switches = new HashMap<Integer, Switch>();
        blocks = new HashMap<Integer, Block>();
        crossing = null;
        plc = new DefaultPLC();
    }
    
    public void setPLC(String plcString){
        plc = new PLCClass(plcString);
    }
    
    public boolean changeSwitch(Switch sb){
        Block block1 = sb.getswitchedBlockBlock();
        Block block2 = sb.getunSwitchedBlockBlock();
        if(plc.checkSwitch(block1, block2, sb.getSwitchBlock())){
            sb.toggleSwitch();
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
        System.out.println("Trying to toggle crossing");
        System.out.println("Current crossing status = " + crossing.getCrossing().isClosed);
        
        //System.out.println(crossing.getBlockNumber());
        
        Block next = blocks.get(crossing.getBlockNumber()-1);
        Block prev = blocks.get(crossing.getBlockNumber()+1);
        
        if(plc.checkCrossing(crossing, next, prev))
        {
            Crossing temp = crossing.getCrossing();
            //System.out.println(temp.toString());
            if(!crossing.getCrossing().getCrossingState(crossing.getLine())){
                crossing.getCrossing().toggleCrossing();
            }
        }
        //Crossing should be open/green/up
        else{
            if(crossing.getCrossing().getCrossingState(crossing.getLine())){
                crossing.getCrossing().toggleCrossing();
            }
        }
    }
    
    public String toString(){
        return id;
    }
}
