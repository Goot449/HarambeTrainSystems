package com.harambe.waysidecontroller;
import com.harambe.trackmodel.*;
import java.util.LinkedHashMap;

public class WaysideDriver {
    
    public static void main(String[] args) throws Exception{
        System.out.println("making track");
        Track track = new Track();
        
        WaysideControllerHandler handler = new WaysideControllerHandler(track);
        
        Thread thread = new Thread(handler);
        thread.start();
        
        track.getBlock(5, "red").toggleOccupied();
        
        Thread.sleep(1000);
        handler.dispatchTrain(track.getBlock(5, "red"), 0);
    }
    
}
