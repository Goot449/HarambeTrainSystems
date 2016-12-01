package com.harambe;

import com.harambe.ctcoffice.OfficeWindow;
import java.io.*;
import java.util.*;
import com.harambe.trackmodel.Track;
import com.harambe.trackmodel.Crossing;
import com.harambe.trackmodel.Station;
import com.harambe.trackmodel.Block;
import com.harambe.trackmodel.TrackModelPrototypeUI;
import com.harambe.traincontroller.TrainController;
import com.harambe.traincontroller.TrainControllerGUI;
import com.harambe.waysidecontroller.*;
import com.harambe.trainmodel.Train;
import com.harambe.trainmodel.TrainModel;

public class RunSystem {

    public static void main(String[] args) throws Exception {
	
	Track trackObj = new Track();
        WaysideControllerHandler handler = new WaysideControllerHandler(trackObj);

		
		//Test broken rail and broken circuit
		/*Block brokenRailBlock = trackObj.getBlock(100, "red");
		brokenRailBlock.toggleBroken();
		Block brokenCircuitBlock = trackObj.getBlock(100, "green");
		brokenCircuitBlock.breakCircuit();*/
		
		//Test commanding authority and speed
		/*trackObj.commandAuthority("red", 15, 27);
		trackObj.commandSpeed("red", 20, 27);*/
		
        System.out.println("Start");
        TrainModel trainModel = new TrainModel(trackObj);
        TrainController trainController = new TrainController();
		
        for (int i = 1; i<11; i++){
            Train train = new Train(1,i);
            trainController.addTrain(train);
            trainModel.addTrain(train);
            
            //Place train in yard
            if(i%2 == 0){ //Let's place even train IDs in red
                    trackObj.placeTrain("red", i);
                    trackObj.updateDistance(2, 1000);
            } else{ //And odd number trains can get thrown in green
                    trackObj.placeTrain("green", i);
                    trackObj.updateDistance(1, 1000);
            }

        }
        
        TrackModelPrototypeUI trackGUI = new TrackModelPrototypeUI(trackObj);
        trackGUI.setVisible(true);
		
        trainController.setStartControl(true);
        TrainControllerGUI trainGUI = new TrainControllerGUI(trainController.trainList, trainController.trainStateList);
        trainGUI.setVisible(true);
        
        OfficeWindow ctcOfficeUI = new OfficeWindow(handler);
        ctcOfficeUI.setVisible(true);
        
        trainModel.setVisible(true);
        
        while(true){
            Block b = trackObj.getBlock(78, "red");
            if(b.getTrainAuthority() > 1){
                System.out.println("good");
            }
        }
    }
    
}
