/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harambe.waysidecontroller;

/**
 *
 * @author tak72_000
 */

public class Block implements Comparable<Block> {
    LineTypes line;
    Section section;
    int blockNumber;
    ArrowDirection arrow;
    Block adj1, adj2;
    boolean occupancy;
    boolean isCrossing;
    String crossingStatus;
    
    public Block(LineTypes myLine, int myBlockNumber, ArrowDirection myArrow){
        line = myLine;
        section = null;
        blockNumber = myBlockNumber;
        arrow = myArrow;
        adj1 = null;
        adj2 = null;
        occupancy = false;
    }
    
    public Block(Block copy){
        line = copy.line;
        section = copy.section;
        blockNumber = copy.blockNumber;
        arrow = copy.arrow;
        adj1 = copy.adj1;
        adj2 = copy.adj2;
        occupancy = copy.occupancy;
    }
    
    public int compareTo(Block b){
        if(this.blockNumber < b.blockNumber){
            return -1;
        }
        else if(this.blockNumber > b.blockNumber){
            return 1;
        }
        return 0;
    }
    
    public void addAdjacent(Block newAdj){
        if(adj1 == null){
            adj1 = newAdj;
        }
        else {
            adj2 = newAdj;
        }
    }
    
    public void setOccupancy(boolean newOccupancy){
        occupancy = newOccupancy;
    }
    
    public boolean isOccupied(){
        return occupancy;
    }
    
    //Override this method
    public boolean isSwitch(){
        return false;
    }
    
    //Find the adjacent block
    //Pass in the current block so we can return the opposite adjacency
    public Block giveAdjacent(Block b){
        if(adj1.equals(b)){
            return adj2;
        }
        else {
            return adj1;
        }
    }
    
    public String toString(){
        return "" + blockNumber;
    }
}
