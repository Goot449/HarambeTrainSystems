/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harambe.traincontroller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import com.harambe.trainmodel.*;

/**
 *
 * @author Alex
 */
public class GetStarted {

    public static ArrayList<Train> list = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Start");
        TrainController trainController = new TrainController();
        Train train1 = new Train(1,1);
        trainController.addTrain(train1);
        Train train2 = new Train(1,2);
        trainController.addTrain(train2);
        TrainControllerGUI thisGUI = new TrainControllerGUI(trainController.trainList, trainController.trainStateList);
        thisGUI.setVisible(true);
        while(true){
            trainController.controlTrain();
            thisGUI.refresh();
        }
//        NumberFormat formatter = new DecimalFormat("#0.00");
//        int kp = 500000;
//        int ki = 1000;
//        double ts = .001;
//        double ek_prev = 0.0;
//        double uk_prev = 0.0;
//        double currentSpeed = 0.0;
//        double setPoint = 43.0;
//        double maxPower = 120000;
//        double power = 0.0;
//        double uk;
//        double ek;
//        int breakCounter = 0;
//        while(true){
//            currentSpeed = train1.getFeedbackVelocity();
//            System.out.println("Speed is " + formatter.format(currentSpeed));
//            ek = setPoint-currentSpeed;
//            uk = uk_prev+(ts/2.0)*(ek-ek_prev);
//            power = kp*ek+ki*uk;
//            if (power>=maxPower){
//                power = maxPower;
//                uk = uk_prev;
//            }
//            train1.setPower(power);
//            System.out.println("Power is " + Integer.toString((int)power));
//            if (breakCounter==1000){
//                System.out.println("1000 in");
//            }
//            train1.step();
//            ek_prev = ek;
//            uk_prev = uk;
//            breakCounter++;
//        }
    }
    
}
