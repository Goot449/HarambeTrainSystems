/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.waysidecontrol;

import com.sun.xml.internal.ws.wsdl.writer.document.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author tak72_000
 */
public class WaysideControllerHandler {
    LinkedBlockingQueue<Message> messages;
    ArrayList<WaysideController> controllers;
    
    /*public WaysideControllerHandler(TrackModel tm){
        
    }*/
    
    public WaysideControllerHandler(HashMap<Integer, Block> blocks, HashMap<Integer, SwitchBlock> switches){
        controllers = new ArrayList<WaysideController>();
        
        //Need to create the controllers and assign the switches...
        for(int i = 0; i < 2; i++){
            WaysideController wc = new WaysideController();
            wc.init("red "+ i, blocks, switches);
            controllers.add(wc);
            wc = new WaysideController();
            wc.init("green" + i, blocks, switches);
            controllers.add(wc);
        }
        
        for(int i : blocks.keySet()){
            if(i < 37){
                
            }
        }
        
    }
    
    public void sendAuthority(int blockNumber, int authority, double speed){
        
    }
    
    public WaysideController findCorrectWayside(int blockNumber){
        for(WaysideController wc : controllers){
            if(wc.getBlock(blockNumber) != null){
                return wc;
            }
        }
        return null;
    }
    
}
