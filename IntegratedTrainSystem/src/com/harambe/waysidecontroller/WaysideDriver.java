package com.harambe.waysidecontroller;
import com.harambe.trackmodel.*;

public class WaysideDriver {
    
    public static void main(String[] args){
        try{
        System.out.println("making track");
        Track track = new Track();
        
        WaysideControllerHandler handler = new WaysideControllerHandler(track);
        
        Thread thread = new Thread(handler);
        thread.start();
        
        Thread.sleep(1000);
        track.getBlock(46, "red").toggleOccupied();
        Thread.sleep(2000);
        
        track.getBlock(46, "red").toggleOccupied();
        Thread.sleep(2000);
        
        System.out.println("Made it to the end?");
        //handler.dispatchTrain(track.getBlock(38, "red"), 0);
        /*
        Thread.sleep(100);
        track.getBlock(38, "red").toggleOccupied();
        Thread.sleep(400);
        handler.dispatchTrain(track.getBlock(38, "red"), 0);
    */
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
