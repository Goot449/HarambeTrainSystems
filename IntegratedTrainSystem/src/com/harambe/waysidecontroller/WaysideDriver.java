package com.harambe.waysidecontroller;

import com.harambe.trackmodel.*;

public class WaysideDriver {

    public static void main(String[] args) {
        try {
            System.out.println("making track");
            Track track = new Track();

            WaysideControllerHandler handler = new WaysideControllerHandler(track);
            handler.dispatchTrain(1, track.getBlock(13, "red"), 60);
            System.out.println(track.getStringRoute("red", track.getBlock(13, "red")));
            
            
            System.out.println(track.getBlock(78, "red").getTrainAuthority());
            System.out.println(track.getBlock(9, "red").getTrainAuthority());
            System.out.println(track.getBlock(8, "red").getTrainAuthority());
            System.out.println(track.getBlock(7, "red").getTrainAuthority());
            System.out.println(track.getBlock(6, "red").getTrainAuthority());
            System.out.println(track.getBlock(5, "red").getTrainAuthority());
            System.out.println(track.getBlock(4, "red").getTrainAuthority());
            System.out.println(track.getBlock(3, "red").getTrainAuthority());
            System.out.println(track.getBlock(2, "red").getTrainAuthority());
            System.out.println(track.getBlock(1, "red").getTrainAuthority());
            System.out.println(track.getBlock(16, "red").getTrainAuthority());
            System.out.println(track.getBlock(17, "red").getTrainAuthority());
            System.out.println(track.getBlock(18, "red").getTrainAuthority());
            System.out.println(track.getBlock(19, "red").getTrainAuthority());
            System.out.println(track.getBlock(20, "red").getTrainAuthority());
            System.out.println(track.getBlock(21, "red").getTrainAuthority());
            System.out.println(track.getBlock(22, "red").getTrainAuthority());
            System.out.println(track.getBlock(23, "red").getTrainAuthority());
            System.out.println(track.getBlock(24, "red").getTrainAuthority());
            System.out.println(track.getBlock(25, "red").getTrainAuthority());
            System.out.println(track.getBlock(26, "red").getTrainAuthority());
            System.out.println(track.getBlock(27, "red").getTrainAuthority());
            System.out.println(track.getBlock(77, "red").getTrainAuthority());
            System.out.println(track.getBlock(76, "red").getTrainAuthority());
            System.out.println(track.getBlock(75, "red").getTrainAuthority());
            System.out.println(track.getBlock(74, "red").getTrainAuthority());
            System.out.println(track.getBlock(73, "red").getTrainAuthority());
            System.out.println(track.getBlock(72, "red").getTrainAuthority());
            System.out.println(track.getBlock(33, "red").getTrainAuthority());
            System.out.println(track.getBlock(34, "red").getTrainAuthority());
            System.out.println(track.getBlock(35, "red").getTrainAuthority());
            System.out.println(track.getBlock(36, "red").getTrainAuthority());
            System.out.println(track.getBlock(37, "red").getTrainAuthority());
            System.out.println(track.getBlock(38, "red").getTrainAuthority());
            System.out.println(track.getBlock(71, "red").getTrainAuthority());
            System.out.println(track.getBlock(70, "red").getTrainAuthority());
            System.out.println(track.getBlock(69, "red").getTrainAuthority());
            System.out.println(track.getBlock(68, "red").getTrainAuthority());
            System.out.println(track.getBlock(67, "red").getTrainAuthority());
            System.out.println(track.getBlock(44, "red").getTrainAuthority());
            System.out.println(track.getBlock(45, "red").getTrainAuthority());
            
            System.out.println("Yard is " + track.getBlock(1).isBlockOccupied());
            
            handler.dispatchTrain(2, track.getBlock(4, "red"), 60);
            
            System.out.println("Yard is " + track.getBlock(1).isBlockOccupied());
            
            //handler.dispatchTrain(77, "green", 0);

            //System.out.println(track.getBlock(155, "green").getTrainAuthority());
//            track.getBlock(44, "red").toggleOccupied();
//            handler.sendAuthority(1, track.getBlock(45, "red"), 0);
//
//            System.out.println(track.getBlock(78, "red").getTrainAuthority());
//            //System.out.println(track.getBlock(9, "red").getTrainAuthority());
//            System.out.println(track.getBlock(8, "red").getTrainAuthority());
//            System.out.println(track.getBlock(7, "red").getTrainAuthority());
//            System.out.println(track.getBlock(6, "red").getTrainAuthority());
//            System.out.println(track.getBlock(5, "red").getTrainAuthority());
//            System.out.println(track.getBlock(4, "red").getTrainAuthority());
//            System.out.println(track.getBlock(3, "red").getTrainAuthority());
//            System.out.println(track.getBlock(2, "red").getTrainAuthority());
//            System.out.println(track.getBlock(1, "red").getTrainAuthority());
//            System.out.println(track.getBlock(16, "red").getTrainAuthority());
//            System.out.println(track.getBlock(17, "red").getTrainAuthority());
//            System.out.println(track.getBlock(18, "red").getTrainAuthority());
//            System.out.println(track.getBlock(19, "red").getTrainAuthority());
//            System.out.println(track.getBlock(20, "red").getTrainAuthority());
//            System.out.println(track.getBlock(21, "red").getTrainAuthority());

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
