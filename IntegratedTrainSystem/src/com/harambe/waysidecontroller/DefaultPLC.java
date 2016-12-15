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

    //Checks the authority requested
    //If none of the blocks in between the train and destination are full, then perform the action
    public boolean checkAuthority(Block nextBlock, Block destinationBlock, int trainID) {
        //Checking authority by looking through all blocks between the next block and destination
        //Perform this check 3 times to ensure that vitality is acheived
        for (int i = 0; i < 3; i++) {
            nextBlock.setSeen(1);
            if (!checkRoute(destinationBlock, nextBlock.peek(), trainID) || nextBlock.isBlockOccupied()) {
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
            if (switchBlock.isBlockOccupied() && !switchBlock.getSwitch().getunSwitchedBlockBlock().isBlockOccupied() && switchBlock.getSwitch().getswitchedBlockBlock().checkAuthority() != -1) {

            } //Train coming from within and trying to leave?
            else if (!switchBlock.isBlockOccupied() && switchBlock.getSwitch().getunSwitchedBlockBlock().isBlockOccupied()) {

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
        for(int i = 0; i < 3; i++){
            if(maintenance.checkAuthority() != -1){
                return false;
            }
        }
        return true;
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
    
    public boolean checkRoute(Block destination, Block currentBlock, int trainID) {
        while (currentBlock.getBlockNumber() != destination.getBlockNumber()) {
            if ((currentBlock.checkAuthority() != -1 && currentBlock.getTrainIDAuth() != trainID) || currentBlock.isBlockOccupied() || currentBlock.isBroken() || currentBlock.isClosed()) {
                return false;
            }
            
            Block lastTraverse = currentBlock;
            
            //Special case; couldn't figure out fix
            if(currentBlock.getBlockNumber() == 16 && currentBlock.getLine().equals("red") && (destination.getBlockNumber() < 16 && destination.getBlockNumber() > 9) && currentBlock.peek().getBlockNumber() == 1){
                currentBlock.toggleSwitch();
            }
            if(currentBlock.getBlockNumber() == 16 && currentBlock.getLine().equals("red") && (destination.getBlockNumber() < 9 || destination.getBlockNumber() == 78) && currentBlock.peek().getBlockNumber() == 15){
                currentBlock.toggleSwitch();
            }
            
            currentBlock = currentBlock.traverse();

            if (lastTraverse == currentBlock) {
                currentBlock.toggleSwitch();
                //currentBlock = currentBlock.getSwitch().getswitchedBlockBlock();
            }

        }
        
        currentBlock.setSeen(0);
        
        currentBlock.traverse();
        currentBlock.setSeen(0);
        
        return true;
    }
}
