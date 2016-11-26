/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My.TrackModel;

import java.io.*;

/**
 *
 * @author Owner
 */
public interface TrackInterface {
    
    //---------------------USER METHODS---------------------
    //User loads track in from a csv file
    public void loadTrack(String csvIn) throws FileNotFoundException, IOException;
    
    //Retrieves the specific block number
    public BlockInterface getBlock(int blockNumber, String line);
    
    //Allows the user to break any block or circuit
    //Toggle controlled: If broken, fixes, else it breaks (T broken; F fixed)
    public boolean breakBlock(int blockNumber);
    public boolean breakBlockCircuit(int blockNumber);
    
    //0 dry; 1 rain; 2 snow
    public void setWeather(int weatherType);
    
    //---------------------WAYSIDE---------------------
    //Toggle controlled: T down; F up
    public boolean commandCrossingDown(int blockNumber);
    
    //Set the authority for a block
    public void commandAuthority(String line, double commandedAuthority, int blockNumber);
    
    //Toggle controlled: T closed; F not closed
    public boolean closeBlock(String line, int blockNumber);
    
    //Set the speed for a block
    public boolean commandSpeed(String line, double commandedSpeed, int blockNumber);
    
    //Toggle controlled: T green; F red
    public boolean toggleRedGreen(String line, int blockNumber);
    
    //---------------------TRAIN---------------------
    //Update the current distance
    public void updateDistance(int TrainID, double distance);
    
    //---------------------OUT---------------------
    //Retreives the block object for the given block
    //public BlockInterface getBlock(int TrainID);
    
    //---------------------GENERAL USAGE---------------------
    public void displayTrack();
    public void displayBlock(int blockNumber);
    Block toggleSwitch(String line, int switchNumber);
    
}
