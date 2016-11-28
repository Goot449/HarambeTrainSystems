/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.waysidecontrol;

import java.util.ArrayList;

/**
 *
 * @author tak72_000
 */
public class TwoWayPLC implements PLC {
    
    public boolean changeSwitch(SwitchBlock sb){
        //Need to know current position of switch to tell if we actually are allowed to switch
        Block pastBlock = sb.getCurrentPosition();
        
        System.out.println("Testing TwoWayPLC");
        
        //If we are looking to have a train "enter" the switch
        if(checkEntry(sb)){
            System.out.println("Entry is full currently...");
            if(!checkBranch(sb.getOptions().get(0)) && !checkBranch(sb.getOptions().get(1))){
                System.out.println("Both branches open");
                if(sb.getOptions().get(0).equals(pastBlock)){
                    sb.setCurBlock(sb.getOptions().get(1));
                }
                else {
                    sb.setCurBlock(sb.getOptions().get(0));
                }
                return true;
            } 
            else if(!checkBranch(sb.getOptions().get(0))){
                System.out.println("Branch 0 is open");
                System.out.println(sb.getOptions().get(0).toString());
                if(sb.getOptions().get(0).equals(pastBlock)){
                    return false;
                } else {
                    sb.setCurBlock(sb.getOptions().get(0));
                    return true;
                }
            } 
            else {
                System.out.println("Branch 1 is open");
                System.out.println(sb.getOptions().get(1).toString());
                if(sb.getOptions().get(0).equals(pastBlock)){
                    sb.setCurBlock(sb.getOptions().get(1));
                    return true;
                } else {
                    return false;
                }
            }
            
            /*boolean unoccupied = true;

            //We're going to check if option 1 is unoccupied
            ArrayList<Block> options = sb.getOptions();
            Block option1 = options.get(0);
            
            Block prevBlock = sb;

            //Go through option1 until encounter next switch; if we encounter no occupancies, then good to use
            while(!option1.isSwitch()){
                //Once we encounter an occupied block, we must check the other option
                if(option1.isOccupied()){
                    unoccupied = false;
                    break;
                }
                Block temp = option1;
                option1 = option1.giveAdjacent(prevBlock);
                prevBlock = temp;
            }

            if(unoccupied){
                if(pastBlock.equals(option1)){
                    return false;
                }
                else {
                    sb.setCurBlock(option1);
                    return true;
                }
            }

            Block option2 = options.get(1);
            unoccupied = true;
            
            prevBlock = sb;
            
            while(!option2.isSwitch()){
                if(option2.isOccupied()){
                    unoccupied = false;
                    break;
                }
                Block temp = option2;
                option2 = option2.giveAdjacent(prevBlock);
                prevBlock = temp;
            }
            
            if(unoccupied){
                if(pastBlock.equals(option2)){
                    return false;
                }
                else {
                    sb.setCurBlock(option2);
                    return true;
                }
            }*/
        }
        else {
            if(checkBranch(sb.getOptions().get(0))){
                if(pastBlock.equals(sb.getOptions().get(0))){
                    return false;
                } else {
                    sb.setCurBlock(sb.getOptions().get(0));
                    return true;
                }
            }
            else if(checkBranch(sb.getOptions().get(1))){
                if(pastBlock.equals(sb.getOptions().get(1))){
                    return false;
                } else {
                    sb.setCurBlock(sb.getOptions().get(1));
                    return true;
                }
            }
            else {
                if(pastBlock.equals(sb.getOptions().get(0))){
                    sb.setCurBlock(sb.getOptions().get(1));
                    return true;
                }
                else {
                    sb.setCurBlock(sb.getOptions().get(0));
                    return true;
                }
            }
        }
    }
    
    private boolean checkEntry(SwitchBlock sb){
        boolean occupied = false;
        Block cur;
        
        if(sb.isOccupied()){
            return true;
        }
        //False means we are using adj1; true is adj2
        boolean adj = false;
        if(sb.adj1 != null){
            cur = sb.adj1;
            adj = false;
        }
        else{
            cur = sb.adj2;
            adj = true;
        }
        for(int i = 0; i < 5; i++){
            if(cur == null){
                return false;
            }
            if(cur.isOccupied()){
                occupied = true;
                break;
            }
            if(!adj){
                cur = cur.adj1;
            }
            else {
                cur = cur.adj2;
            }
        }
        return occupied;
    }
    
    //True == occupied, false == unoccupied
    private boolean checkBranch(Block curBlock){
        if(curBlock.adj1 instanceof SwitchBlock){
            while(curBlock != null){
                if(curBlock.isOccupied()){
                    return true;
                }
                if(curBlock instanceof SwitchBlock){
                    return false;
                }
                curBlock = curBlock.adj2;
            }
        }
        else {
            while(curBlock != null){
                if(curBlock.isOccupied()){
                    return true;
                }
                if(curBlock instanceof SwitchBlock){
                    return false;
                }
                curBlock = curBlock.adj1;
            }
        }
        return false;
    }
    
}
