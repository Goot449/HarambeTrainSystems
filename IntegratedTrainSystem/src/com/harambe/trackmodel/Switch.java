/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harambe.trackmodel;

import java.util.*;

/**
 *
 * @author Owner
 */
public class Switch implements SwitchInterface{
    
    private int setupCount = 0;
    private String switchNumber;
    private Block switchBlock = null;
    private Block switchedBlock = null;
    private Block unSwitchedBlock = null;
    private ArrayList<Block> switchBlocks;
    private boolean brokenSwitch = false;
    private boolean lightStatus = false;
	
    public Switch(Block blockIn){
        
        switchNumber = blockIn.getSwitchNumber();
        addBlock(blockIn);
    }
    
    public boolean getLight(){
        return lightStatus;
    }
    
    public void toggleLight(){
        lightStatus = !lightStatus;
    }
	
    public String getSwitchNumber(){
        return switchNumber;
    }
	
    public Block getSwitchBlock(){
        return switchBlock;
    }

    public Block getswitchedBlockBlock(){
	return switchedBlock;
    }

    public Block getunSwitchedBlockBlock(){
	return unSwitchedBlock;
    }
	
    public void addBlock(Block blockIn){

	blockIn.setSwitch(this);

	if(blockIn.getSwitchBlock().length()>0){
            switchBlock = blockIn;
        } else if(switchedBlock == null){
            switchedBlock = blockIn;
        } else{
            unSwitchedBlock = blockIn;
        }
    }
    
    public void breakSwitch(){
        brokenSwitch = (!brokenSwitch);
    }
    
    @Override
    public void toggleSwitch(){
        
	System.out.println("Toggling " + switchNumber);
	Block temp = switchedBlock;
	switchedBlock = unSwitchedBlock;
	unSwitchedBlock = temp;
	setup();
    }
    
    @Override
    public boolean isSwitchWorking(){
        return brokenSwitch;
    }
    
    @Override
    public boolean getSwitchPosition(){
	return false;
    }
    
    private void setOutOfSection(Block sourceBlock, Block targetBlock){
        
        //NEXT or PREVIOUS within the same section
	if(sourceBlock.getNext() != null && sourceBlock.getNext().getSection().equals(sourceBlock.getSection())){
            sourceBlock.setPrevious(targetBlock);
        } else{
            sourceBlock.setNext(targetBlock);
        }
    }
    
    public String toGUI(){
        
        return "Switch on: " + switchBlock.getSection() + switchBlock.getBlockNumber() + "\n\nConnected to: " + switchedBlock.getSection() + switchedBlock.getBlockNumber() + "\n\nNOT connected to: " + unSwitchedBlock.getSection() + unSwitchedBlock.getBlockNumber();
    }
    
    public String toString(){
        
        if(brokenSwitch){
            return switchedBlock.getLine() + "\t" + switchNumber + "\t" + brokenSwitch;
        } else{
            return switchedBlock.getLine() + "\t" + switchNumber + "\t" + switchedBlock.getSection() + switchedBlock.getBlockNumber();
        }
    }
    
    public void setup(){

        //normal 3-way junction case.  3 sepearate section blocks
        //Typical 3-WAY junction; 3 different sectiond
	if(switchBlock.getSwitchType().equals("-")){
            setOutOfSection(switchBlock, switchedBlock);
            setOutOfSection(switchedBlock, switchBlock);
            setOutOfSection(unSwitchedBlock, null);
        //Actual switch is before its attachments
	} else if(switchBlock.getSwitchType().equals("BEFORE")) {
            switchBlock.setPrevious(switchedBlock);
            //Same section
            if(switchedBlock.getSection().equals(switchBlock.getSection())) {
                switchedBlock.setNext(switchBlock);
		setOutOfSection(unSwitchedBlock, null);
            //Different section
            } else{
                setOutOfSection(switchedBlock,switchBlock);
		unSwitchedBlock.setNext(null);
            }
        //Mid-section switch is after fork
	} else if(switchBlock.getSwitchType().equals("AFTER")) {

            //If backwards 1-WAY
            if(switchBlock.getDirection()==-1) {
		switchBlock.setPrevious(switchedBlock);

		if(switchedBlock.getSection().equals(switchBlock.getSection())) {
                    switchedBlock.setNext(switchBlock);
                    setOutOfSection(unSwitchedBlock, null);
                } else{
                    setOutOfSection(switchedBlock, switchBlock);
                    unSwitchedBlock.setNext(null);
                }

            } else{
                switchBlock.setNext(switchedBlock);
                if(switchedBlock.getSection().equals(switchBlock.getSection())){
                    switchedBlock.setPrevious(switchBlock);
                    setOutOfSection(unSwitchedBlock,null);
                } else{
                    setOutOfSection(switchedBlock, switchBlock);
                    unSwitchedBlock.setPrevious(null);	
                }
            }
	}

	if(setupCount<2){	
            setupCount++;
            toggleSwitch();
	}
    }
}
