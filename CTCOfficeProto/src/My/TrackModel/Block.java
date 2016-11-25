/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My.TrackModel;

import java.util.*;

/**
 *
 * @author Owner
 */
public class Block implements BlockInterface{
    
    private String line;
    private String section;
    private int blockNumber;
    private int blockID;
    private boolean blockOccupied = false;
    private double speedLimit;
    private String infrastructure = " ";
    
    public Block(String[] splitStrings) {
        
        line = splitStrings[0];
        section = splitStrings[1];
        blockID = Integer.parseInt(splitStrings[2]);
        blockNumber = Integer.parseInt(splitStrings[3]);
        
        try {
            speedLimit = Double.parseDouble(splitStrings[4])*0.621371;
        } catch(ArrayIndexOutOfBoundsException e) {
    // do something other
        }
        try {
            infrastructure = splitStrings[5];
        } catch(ArrayIndexOutOfBoundsException e) {
    // do something other
        }
        
    
    }
    
    public String getLine(){
        return line;
    }
    
    public String getSection(){
        return section;
    }
    
    public int getBlockNumber(){
        return blockNumber;
    }
    
    public boolean isBlockOccupied() {
        return blockOccupied;
    }
    
    public double getSpeedLimit(){
        return speedLimit;
    }
    
    public String getInfrastrucure(){
        return infrastructure;
    }
    
    public int getBlockID(){
        return blockID;
    }
    
}
