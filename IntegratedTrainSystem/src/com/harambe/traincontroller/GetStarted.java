/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harambe.traincontroller;

import java.util.ArrayList;
import com.harambe.trainmodel.Train;
import com.harambe.trainmodel.TrainModel;

/**
 *
 * @author Alex
 */
public class GetStarted {

    public static ArrayList<Train> list = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Start");
        TrainModel trainModel = new TrainModel();
        TrainController trainController = new TrainController();
        Train train1 = new Train(1,0);
        trainController.addTrain(train1);
//        Train train2 = new Train(1,2);
//        trainController.addTrain(train2);
        trainController.setStartControl(true);
        TrainControllerGUI thisGUI = new TrainControllerGUI(trainController.trainList, trainController.trainStateList);
        thisGUI.setVisible(true);
    }
    
}
