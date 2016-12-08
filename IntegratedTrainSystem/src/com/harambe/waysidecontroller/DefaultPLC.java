package com.harambe.waysidecontroller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.harambe.trackmodel.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tak72_000
 */
public class DefaultPLC implements PLC {

    public boolean checkAuthority(Block nextBlock, Block destinationBlock, Track myTrack) {
        System.out.println("Checking authority");
//        System.out.println("The authority of " + nextBlock.peek().getBlockNumber() + " and " + destinationBlock.getBlockNumber() + " is " + myTrack.checkRoute(destinationBlock, nextBlock));
        return myTrack.checkRoute(destinationBlock, nextBlock);
//        if(nextBlock.isBlockOccupied() || destinationBlock.isBlockOccupied()){
//            return false;
//        }
//        else {
//            return true;
//        }
    }

    public boolean checkSwitch(Block unswitched, Block switched, Block switchBlock) {
        //Train incoming and unswitched switch == unoccupied
        if(switchBlock.isBlockOccupied() && !switchBlock.getSwitch().getunSwitchedBlockBlock().isBlockOccupied() && switchBlock.getSwitch().getswitchedBlockBlock().isBlockOccupied()){
            return true;
        }
        //Train coming from within and trying to leave?
        if(!switchBlock.isBlockOccupied() && switchBlock.getSwitch().getunSwitchedBlockBlock().isBlockOccupied()){
            return true;
        }
        
        //Empty
        if(!switchBlock.isBlockOccupied() && !switchBlock.getSwitch().getunSwitchedBlockBlock().isBlockOccupied() && !switchBlock.getSwitch().getswitchedBlockBlock().isBlockOccupied()){
            return true;
        }
        
        return false;
    }

    public boolean checkMaintenance(Block maintenance) {
        Block adjacent1 = maintenance.getPrevious();
        Block adjacent2 = maintenance.getNext();

        if(adjacent1.isBlockOccupied() || adjacent2.isBlockOccupied() || maintenance.isBlockOccupied()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkCrossing(Block crossing, Block next, Block prev) {
        if(next.isBlockOccupied() || prev.isBlockOccupied() || crossing.isBlockOccupied()){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkLight(Switch s){
        return true;
    }
}
