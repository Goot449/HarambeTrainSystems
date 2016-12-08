package com.harambe.waysidecontroller;

import com.harambe.trackmodel.*;

public class WaysideDriver {

    public static void main(String[] args) {
        try {
            System.out.println("making track");
            Track track = new Track();

            WaysideControllerHandler handler = new WaysideControllerHandler(track);

            System.out.println("Get route ");
 //           System.out.println(track.getStringRoute("red", track.getBlock(48, "red")));


            Thread.sleep(1000);
            handler.dispatchTrain(45, "red", 0);
            //handler.dispatchTrain(77, "green", 0);
            
            //System.out.println(track.getBlock(155, "green").getTrainAuthority());
            System.out.println(track.getBlock(78, "red").getTrainAuthority());
            track.getBlock(47, "red").toggleOccupied();
            handler.sendAuthority(1, track.getBlock(48, "red"), 0);
            System.out.println(track.getBlock(78, "red").getTrainAuthority());
//        System.out.println(track.getStringRoute("red", track.getBlock(48, "red")));
//        System.out.println(track.getStringRoute("green", track.getBlock(39, "green")));

//        System.out.println(track.getStringRoute("green", "CENTRAL"));
//        System.out.println(track.getNumberBlocks("green", "CENTRAL"));
//        Block startBlock = track.getBlock(67, "green");
//        System.out.println(track.getStringRoute("green", "CENTRAL", startBlock));
//        System.out.println(track.getNumberBlocks("green", "CENTRAL", startBlock));
            /*Thread thread = new Thread(handler);
        thread.start();
        
        Thread.sleep(1000);
        track.getBlock(46, "red").toggleOccupied();
        Thread.sleep(2000);
        
        track.getBlock(46, "red").toggleOccupied();
        Thread.sleep(2000);
             */
            //System.out.println("Made it to the end?");
            //handler.dispatchTrain(track.getBlock(38, "red"), 0);

            //track.getBlock(38, "red").toggleOccupied();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
