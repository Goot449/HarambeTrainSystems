/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harambe.trackmodel;

/**
 *
 * @author Owner
 */
public class Crossing {
    
    boolean isClosed = false;
    String currentLineDown = null;
    Block greenLineBlock = null;
    Block redLineBlock = null;
    boolean isBroken = false;
    
    public Crossing(Block newBlock){
        
        if(newBlock.getLine().equals("green")){
            greenLineBlock = newBlock;
            currentLineDown = "green";
        } else{
            redLineBlock = newBlock;
            currentLineDown = "red";
        }
    }
    
    public void toggleCrossing(){
        
        System.out.println("Crossing toggled!");
        if(isClosed){
            isClosed = false;
            currentLineDown = "red";
            //System.out.println("RED");
        } else{
            isClosed = true;
            currentLineDown = "green";
            //System.out.println("GREEN");
        }
    }
    
    public void addBlock(Block newBlock){
        
        if(newBlock.getLine().equals("red")){
            redLineBlock = newBlock;
        } else{
            greenLineBlock = newBlock;
        }
    }
    
    public boolean getCrossingState(String line){
        return isClosed;
        /*if(currentLineDown.equals(line)){
            return true;
        } else{
            return false;
        }*/
    }
    
    public boolean isBroken(){
        return isBroken;
    }
    
    public String toString(){
        
        if(currentLineDown.equals("green")){
            return "green" + "  " + greenLineBlock.getSection() + "   " + greenLineBlock.getBlockNumber();
        } else{
            return "red" + "  " + redLineBlock.getSection() + "   " + redLineBlock.getBlockNumber();
        }
    }
    
}
