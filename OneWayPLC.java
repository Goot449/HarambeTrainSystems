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
public class OneWayPLC implements PLC{
    public OneWayPLC(){
        super();
    }
    
    //Returns false if the switch isn't made; true if switch is made
    public boolean changeSwitch(SwitchBlock sb){
        Block pastBlock = sb.getCurrentPosition();
        Block entry = sb;
        
        boolean hasTail = false;
        for(Block b : sb.getOptions()){
            if(b.arrow == ArrowDirection.Tail){
                hasTail = true;
            }
        }
        
        //If we are trying to change the switch so that the entry is opened will it work?
        if(hasTail){
            if(checkEntry(sb)){
                for(Block b: sb.getOptions()){
                    if(b.arrow == ArrowDirection.Tail){
                       sb.setCurBlock(b);
                       if(pastBlock.equals(b)){
                           return false;
                       } 
                       else {
                           return true;
                       }
                    }
                }
                return true;
            }
            else{
                for(Block b: sb.getOptions()){
                    if(b.arrow == ArrowDirection.Tail){
                        sb.setCurBlock(b);
                        if(pastBlock.equals(b)){
                            return false;
                        } 
                        else {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        else {
            if(checkEntry(sb)){
                ArrayList<Block> options = sb.getOptions();
                sb.setCurBlock(options.get(0));
                if(pastBlock.equals(options.get(0))){
                    return false;
                }
                else {
                    return true;
                }
            }
            else {
                ArrayList<Block> options = sb.getOptions();
                sb.setCurBlock(options.get(1));
                if(pastBlock.equals(options.get(1))){
                    return false;
                }
                else {
                    return true;
                }
            }
        }
    }
    
    private boolean checkEntry(SwitchBlock sb){
        boolean occupied = false;
        Block cur;
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
                cur = cur.adj1;
            }
        }
        return occupied;
    }
    
    private boolean checkMaintenance(Block prevBlock, Block target){
        if(prevBlock.occupancy || target.occupancy){
            return false;
        }
        return true;
    }
    
    private boolean checkLight(Block switchBlock){
        if(switchBlock.isSwitch()){
            return true;
        }
        return false;
    }
    
    private boolean checkCrossing(Block crossingBlock){
        if(crossingBlock.isCrossing){
            if(crossingBlock.adj1.occupancy || crossingBlock.adj2.occupancy || crossingBlock.occupancy){
                return true;
            }
        }
        return false;
    }
    
    private boolean checkAuthority(Block curBlock, Block nxtBlock, Block destination){
        boolean result = true;
        
        if(nxtBlock.occupancy || destination.occupancy){
            result = false;
        }
        
        while(!curBlock.equals(destination)){
            Block temp = new Block(nxtBlock);
            nxtBlock = nxtBlock.giveAdjacent(curBlock);
            curBlock = temp;
            
            if(curBlock.occupancy){
                result = false;
                break;
            }
        }
        
        return result;
    }
    
    private boolean checkSwitch(Block curBlock, Block nxtBlock, Block destination){
        boolean result = true;
        
        if(nxtBlock.occupancy || destination.occupancy){
            result = false;
        }
        
        return result;
    }
}
