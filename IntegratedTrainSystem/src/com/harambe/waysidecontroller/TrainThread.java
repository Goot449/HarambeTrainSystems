/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harambe.waysidecontroller;
import com.harambe.trainmodel.*;
/**
 *
 * @author tak72_000
 */
public class TrainThread implements Runnable{
    Train t;
    int trainID;
    public void init(int trainID){
        this.trainID = trainID;
    }
    
    public void run(){
        try{
            t = new Train(2, trainID);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public Train getTrain(){
        if(t != null)
            return t;
        return null;
    }
}
