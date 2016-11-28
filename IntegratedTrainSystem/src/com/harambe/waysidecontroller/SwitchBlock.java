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
public class SwitchBlock extends Block {
    //This is the "entry" point for the switch
    //private Block pivot;
    
    int switchNum;
    
    //These are the two blocks that the switch can point to
    private ArrayList<Block> options;
    
    //This is the current block being pointed to
    private Block curBlock;
    
    boolean light;
    
    public SwitchBlock(ArrayList<Block> myOptions, int num, LineTypes myLine, int myBlockNumber, ArrowDirection myArrow){
        super(myLine, myBlockNumber, myArrow);
        switchNum = num;
        options = myOptions;
        curBlock = options.get(0);
        light = false;
    }
    
    public Block getCurrentPosition(){
        return curBlock;
    }
    
    /*public Block getPivot(){
        return pivot;
    }*/
    
    public ArrayList<Block> getOptions(){
        return options;
    }
    
    public void setCurBlock(Block newCur){
        if(options.contains(newCur)){
            curBlock = newCur;
        }
    }
    
    //Overridden method
    public boolean isSwitch(){
        return true;
    }
    
    public void toggleLight(){
        if(light){
            light = false;
        }
        else {
            light = true;
        }
    }
    
    public String toString(){
        return "" + this.blockNumber;
    }
}
