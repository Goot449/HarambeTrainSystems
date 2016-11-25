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
    
    //User loads track in from a csv file
    public void loadTrack(String csvIn) throws FileNotFoundException, IOException;
    
    //Retrieves the specific block number
    public BlockInterface getBlock(int blockNumber);
   
    //General
    public void displayTrack();
    public void displayBlock(int blockNumber);
    
    //Used for displaying the correct blocks once stored
    Block toggleSwitch(String line, int switchNumber);
    
}
