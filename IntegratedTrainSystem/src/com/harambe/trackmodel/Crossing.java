/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My.TrackModel;

/**
 *
 * @author Owner
 */
public class Crossing {
    
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
        if(currentLineDown.equals("green")){
            currentLineDown = "red";
            System.out.println("RED");
        } else{
            currentLineDown = "green";
            System.out.println("GREEN");
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
        
        if(currentLineDown.equals(line)){
            return true;
        } else{
            return false;
        }
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
