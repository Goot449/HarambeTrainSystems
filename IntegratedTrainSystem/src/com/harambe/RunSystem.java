package com.harambe;

import com.harambe.traincontroller.TrainController;
import com.harambe.traincontroller.TrainControllerGUI;
import com.harambe.trainmodel.Train;
import com.harambe.trainmodel.TrainModel;

public class RunSystem {

    public static void main(String[] args) throws Exception {
        System.out.println("Start");
        TrainModel trainModel = new TrainModel();
        TrainController trainController = new TrainController();
        for (int i = 0; i<400; i++){
            Train train = new Train(1,i);
            trainController.addTrain(train);
        }
        trainController.setStartControl(true);
        TrainControllerGUI thisGUI = new TrainControllerGUI(trainController.trainList, trainController.trainStateList);
        thisGUI.setVisible(true);
    }
    
}
