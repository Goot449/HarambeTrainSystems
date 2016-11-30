package com.harambe;

import java.io.*;
import java.util.*;
import com.harambe.trackmodel.Track;
import com.harambe.trackmodel.Crossing;
import com.harambe.trackmodel.Station;
import com.harambe.trackmodel.Block;
import com.harambe.trackmodel.TrackModelPrototypeUI;
import com.harambe.traincontroller.TrainController;
import com.harambe.traincontroller.TrainControllerGUI;
import com.harambe.trainmodel.Train;
import com.harambe.trainmodel.TrainModel;

public class RunSystem {

    public static void main(String[] args) throws Exception {
	
		Track trackObj = new Track();
		
		//Test broken rail and broken circuit
		Block brokenRailBlock = trackObj.getBlock(20, "red");
		brokenRailBlock.toggleBroken();
		Block brokenCircuitBlock = trackObj.getBlock(10, "green");
		brokenCircuitBlock.breakCircuit();
		
		//Test commanding authority and speed
		/*trackObj.commandAuthority("red", 15, 27);
		trackObj.commandSpeed("red", 20, 27);*/
		
        System.out.println("Start");
        TrainModel trainModel = new TrainModel();
        TrainController trainController = new TrainController();
		
        for (int i = 0; i<400; i++){
            Train train = new Train(1,i);
            //Place train in yard
			if(i%2 == 0){ //Let's place even train IDs in red cause
				trackObj.placeTrain("red", i);
			} else{ //And odd number trains can get thrown in green
				trackObj.placeTrain("green", i);
			}
            trainController.addTrain(train);
        }
		
		//Testing train traversal
		trackObj.updateDistance(1, 100);
		
        TrackModelPrototypeUI trackGUI = new TrackModelPrototypeUI(trackObj);
        trainGUI.setVisible(true);
		
        trainController.setStartControl(true);
        TrainControllerGUI trainGUI = new TrainControllerGUI(trainController.trainList, trainController.trainStateList);
        trainGUI.setVisible(true);
		
    }
    
}
