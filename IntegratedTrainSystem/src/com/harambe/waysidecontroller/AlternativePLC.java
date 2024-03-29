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
public class AlternativePLC implements PLC {

    //Checks the authority requested
    //If none of the blocks in between the train and destination are full, then perform the action
    public boolean checkAuthority(Block nextBlock, Block destinationBlock, int trainID) {
        //Checking authority by looking through all blocks between the next block and destination
        //Perform this check 3 times to ensure that vitality is acheived
        for (int i = 0; i < 3; i++) {
            nextBlock.setSeen(1);
            if (!checkRoute(destinationBlock, nextBlock.peek())) {
                return false;
            }
            nextBlock.setSeen(0);
        }
        return true;

//        if(nextBlock.isBlockOccupied() || destinationBlock.isBlockOccupied()){
//            return false;
//        }
//        else {
//            return true;
//        }
    }

    //Check whether the switch operation is safe
    public boolean checkSwitch(Block unswitched, Block switched, Block switchBlock) {
        //Train incoming and unswitched switch == unoccupied
        boolean status = true;

        for (int i = 0; i < 3; i++) {
            if (!switchBlock.isBlockOccupied() && switchBlock.getSwitch().getunSwitchedBlockBlock().isBlockOccupied()) {

            } //Empty
            else if (!switchBlock.isBlockOccupied() && !switchBlock.getSwitch().getunSwitchedBlockBlock().isBlockOccupied() && !switchBlock.getSwitch().getswitchedBlockBlock().isBlockOccupied()) {

            } else {
                status = false;
                break;
            }
        }

        return status;
    }

    //Check if maintenance is acceptable
    //If none of the surrounding blocks are full, then it is acceptable
    public boolean checkMaintenance(Block maintenance) {
        Block adjacent1 = maintenance.getPrevious();
        Block adjacent2 = maintenance.getNext();
        boolean status = true;

        for (int i = 0; i < 3; i++) {
            if (!adjacent1.isBlockOccupied() && !adjacent2.isBlockOccupied() && !maintenance.isBlockOccupied()) {

            } else {
                return false;
            }
        }
        return status;
    }

    //Check to see if we should toggle crossing
    //If any of the immediately surrounding blocks are occupied, the crossing will close
    public boolean checkCrossing(Block crossing, Block next, Block prev) {
        for (int i = 0; i < 3; i++) {
            if (next.isBlockOccupied() || prev.isBlockOccupied() || crossing.isBlockOccupied()) {

            } else {
                return false;
            }
        }
        return true;
    }

    public boolean checkLight(Switch s) {
        return true;
    }
    
    public boolean checkRoute(Block destination, Block currentBlock) {
        while (currentBlock.getBlockNumber() != destination.getBlockNumber()) {
            if (currentBlock.isBlockOccupied()) {
                return false;
            }
            Block lastTraverse = currentBlock;
            //System.out.println(currentBlock.getSection() + currentBlock.getBlockNumber() + " " + currentBlock.getStation());

            currentBlock = currentBlock.traverse();

            if (lastTraverse == currentBlock) {
                currentBlock.toggleSwitch();
                //currentBlock = currentBlock.getSwitch().getswitchedBlockBlock();
            }

        }
        
        currentBlock.traverse();
        currentBlock.setSeen(0);
        
        return true;
    }
}
