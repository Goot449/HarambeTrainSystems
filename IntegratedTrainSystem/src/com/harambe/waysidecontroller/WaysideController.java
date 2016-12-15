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
    
    public void setPLC(String newPLC){
        
        if(newPLC.equals("DefaultPLC.class")){
            plc = new DefaultPLC();
        }
        else if(newPLC.equals("testPLC.class")){
            plc = new testPLC();
        }
        else{
            plc = new AlternativePLC();
        }
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
    
    public boolean checkMaintenance(Block b){
        System.out.println("Checking maintenance");
        return plc.checkMaintenance(b);
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
    
    public boolean checkAuthority(int srcNum, int destNum, Track myTrack, int trainID){
        System.out.println(srcNum + " " + destNum);
        return plc.checkAuthority(myTrack.getBlock(srcNum, line), myTrack.getBlock(destNum, line), trainID);
    }
    
    public Switch getSwitch(String sbNum){
        if(switches.containsKey(sbNum)){
            return switches.get(sbNum);
        }
        return null;
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
        
        Block next = blocks.get(crossing.getBlockNumber()-1);
        Block prev = blocks.get(crossing.getBlockNumber()+1);
        
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
    
    public int getNumberBlocks(String line, Block destination, Block currentBlock) {
        int authorityNum = 0;

        while (currentBlock.getBlockNumber() != destination.getBlockNumber()) {
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
                //currentBlock = currentBlock.getSwitch().getswitchedBlockBlock();
            } else {
                authorityNum++;
            }
        }

        currentBlock.traverse();
        currentBlock.setSeen(0);

        return authorityNum;
    }
    
    public void setAuthorities(String line, Block destination, Block currentBlock, int num, double speed, int trainID) {
        while (currentBlock.getBlockNumber() != destination.getBlockNumber()) {
            //System.out.println("In set Authorities");
            Block lastTraverse = currentBlock;
            //System.out.println(currentBlock.getSection() + currentBlock.getBlockNumber() + " " + currentBlock.getStation());

            //Special case; couldn't figure out fix
            if(currentBlock.getBlockNumber() == 16 && currentBlock.getLine().equals("red") && destination.getBlockNumber() < 16 && destination.getBlockNumber() > 9 && currentBlock.peek().getBlockNumber() == 1){
                currentBlock.toggleSwitch();
            }
            if(currentBlock.getBlockNumber() == 16 && currentBlock.getLine().equals("red") && (destination.getBlockNumber() < 9 || destination.getBlockNumber() == 78) && currentBlock.peek().getBlockNumber() == 15){
                currentBlock.toggleSwitch();
            }
            
            currentBlock = currentBlock.traverse();

            if (lastTraverse == currentBlock) {
                currentBlock.toggleSwitch();
                //currentBlock = currentBlock.getSwitch().getswitchedBlockBlock();
            } else {
                currentBlock.setAuthority(num);
                currentBlock.setCommandedSpeed(speed);
                currentBlock.setTrainIDAuth(trainID);
                num--;
            }
        }

        currentBlock.traverse();
        currentBlock.setSeen(0);
    }
}
