/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harambe.waysidecontroller;

import java.util.*;
import com.harambe.trackmodel.*;
/**
 *
 * @author tak72_000
 */
public class WaysideController {
    String id;
    String line;
    String username = "admin";
    String password = "admin";
    public HashMap<Integer,Block>  blocks;
    public HashMap<String,Switch> switches;
    public Block crossing;
    PLC plc;
    
    public WaysideController(String id, String line){
        this.id = id;
        this.line = line;
        switches = new HashMap<String, Switch>();
        blocks = new HashMap<Integer, Block>();
        crossing = null;
        plc = new DefaultPLC();
    }
    
    public String getLine(){
        return line;
    }
    
    public void setPLC(){
        plc = new DefaultPLC();
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
    
    public boolean checkAuthority(int srcNum, int destNum){
        System.out.println(srcNum + " " + destNum);
        return plc.checkAuthority(blocks.get(srcNum), blocks.get(destNum));
    }
    
    public Switch getSwitch(String sbNum){
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
        if(b.getBlockNumber() == 47 && b.getLine().equals("red")){
            crossing = b;
        }
        if(b.getBlockNumber() == 19 && b.getLine().equals("green")){
            crossing = b;
        }
    }
    
    public void addSwitch(Switch s){
        switches.put(s.getSwitchNumber(), s);
    }
    
    public void toggleCrossing(){
        //If the crossing should be closed/down/red
        //System.out.println("Current crossing status = " + crossing.getCrossing().isClosed);
        
        System.out.println(crossing.getBlockNumber() + " " + crossing.isBlockOccupied());
        
        Block next = blocks.get(crossing.getBlockNumber()-1);
        Block prev = blocks.get(crossing.getBlockNumber()+1);
        System.out.println(next.getBlockNumber() + " " + next.isBlockOccupied());
        System.out.println(prev.getBlockNumber() + " " + prev.isBlockOccupied());
        
        if(plc.checkCrossing(crossing, next, prev))
        {
            Crossing temp = crossing.getCrossing();
            //System.out.println(temp.toString());
            if(!temp.getCrossingState(crossing.getLine())){
                temp.toggleCrossing();
            }
        }
        //Crossing should be open/green/up
        else{
            if(crossing.getCrossing().getCrossingState(crossing.getLine())){
                Crossing temp = crossing.getCrossing();
                temp.toggleCrossing();
            }
        }
    }
    
    public String toString(){
        return id;
    }
}
