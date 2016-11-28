/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harambe.trackmodel;

/**
 *
 * @author Owner
 */
public interface BlockInterface {
    
    //Returns values corresponging to that Block
    public boolean isBlockOccupied();
    public double getBlockLength();
    public int getBlockSpeedLimit();
    public double getGrade();
    public int getBlockDirection();
    
    //For use in traversing the track blocks
    public BlockInterface getPrevious();
    public BlockInterface getNext();
    
    //Returns requested values
    public double getTrainAuthority();
    public double getTrainCommandedSpeed();
    public double getFrictionCoefficient();
    
    //T working; F broken
    //public boolean isBlockWorking();
    
    //T if one of the following infrastructures; F if not
    public boolean isCrossing();
    public boolean isStation();
    public boolean isSwitch();
    
}
