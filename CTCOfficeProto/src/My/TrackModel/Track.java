/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My.TrackModel;

import java.io.*;
import java.util.*;

/**
 *
 * @author Owner
 */
public class Track implements TrackInterface {
    
    private Block redLine = null;
    private Block greenLine = null;
    //private ArrayList<Block> redBlocks = new ArrayList<Block>();
    private ArrayList<Block> greenBlocks = new ArrayList<Block>();
    private ArrayList<Block> trainBlocks = new ArrayList<Block>();
    
    public Track() throws IOException{
        //loadTrack("RedLine.csv");
        loadTrack("GreenLine.csv");
    }
    
    //Method to load track in from csv filename input paramter
    public void loadTrack(String csvIn) throws IOException {
        
        //Initialize reader and array to parse values out of the cell
        String inFile = csvIn;
        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        String splitStrings[];
        
        ArrayList<Block> currentAll = null;
        Block currentBlock = null;
        Block currentLine = null;
        
        while(reader.ready()) {
            
            splitStrings = reader.readLine().split(",");
            
            if(currentBlock == null && splitStrings[0].equals("Green")){
                currentAll = greenBlocks;
            }
            
            else if(currentBlock == null && splitStrings[0].equals("Red")){
                currentAll = greenBlocks;
            }
            
            currentBlock = new Block(splitStrings);
            currentAll.add(currentBlock);
            
        }
      
    }
    
    @Override
    public Block getBlock(int blockNumber) {
        
        Block returnBlock = null;
        //line = line.toLowerCase();
        
        //if(line.equals("red")){
//            for(Block currentBlock: redBlocks){
//                if(currentBlock.getBlockNumber() == blockNumber){
//                    returnBlock = currentBlock;
//                }
//            }
        //}
        //else{
            for(Block currentBlock: greenBlocks){
                if(currentBlock.getBlockID() == blockNumber){
                    returnBlock = currentBlock;
                }
            }
        //}
        
        return returnBlock;
    }
    
    
    @Override
    public void displayTrack(){
        
    }
    
    @Override
    public void displayBlock(int blockNumber){
        
    }
    
    @Override
    public Block toggleSwitch(String line, int blockNumber){
        return null;
    }
   
}
