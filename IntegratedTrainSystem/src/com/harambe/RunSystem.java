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
		
        System.out.println("Start");
        WaysideControllerHandler handler = new WaysideControllerHandler(trackObj);


		
        //Thread.sleep(1000);
//        for (int i = 0; i<1; i++){
//            Train train = new Train(1,i);
//            trainController.addTrain(train);
//            trainModel.addTrain(train);
//        }
//        
        TrackModelPrototypeUI trackGUI = new TrackModelPrototypeUI(trackObj);
        trackGUI.setVisible(true);
		
//        trainController.setStartControl(true);
//        TrainControllerGUI trainGUI = new TrainControllerGUI(trainController.trainList, trainController.trainStateList);
//        trainGUI.setVisible(true);
        
        OfficeWindow ctcOfficeUI = new OfficeWindow(handler);
        ctcOfficeUI.setVisible(true);
        
//        trainModel.setVisible(true);
    }
    
}