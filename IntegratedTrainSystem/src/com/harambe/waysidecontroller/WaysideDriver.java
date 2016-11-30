package com.harambe.waysidecontroller;
import com.harambe.trackmodel.*;

public class WaysideDriver {
    
    public static void main(String[] args) throws Exception{
        System.out.println("making track");
        Track track = new Track();
        
        WaysideControllerHandler handler = new WaysideControllerHandler(track);
        
        Thread thread = new Thread(handler);
        thread.start();
        
        //System.out.println(track.getRoute("red", "SHADYSIDE-R"));
        
        System.out.println("Switch status : " + track.getSwitch("Switch 3").getswitchedBlockBlock().getBlockNumber());
        
        handler.manualSwitch("Switch 3");
        Thread.sleep(500);
        System.out.println("Switch status : " + track.getSwitch("Switch 3").getswitchedBlockBlock().getBlockNumber());
        /*
        track.getBlock(38, "red").toggleOccupied();
        
        Thread.sleep(1000);
        handler.dispatchTrain(track.getBlock(38, "red"), 0);
        
        Thread.sleep(100);
        track.getBlock(38, "red").toggleOccupied();
        Thread.sleep(400);
        handler.dispatchTrain(track.getBlock(38, "red"), 0);
    */
    }
    
}
