/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traincontroller;

import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Alex
 */
public class TrainController {

    private boolean testModeEnabled = true;
    private boolean autoModeEnabled = false;
    private double DT = 0.001;
    public vitalCalculator vitalCalc = new vitalCalculator();
    //public Train[] trainList;
    public ArrayList<Train> trainList = new ArrayList<Train>();
    public ArrayList<TrainState> trainStateList = new ArrayList<TrainState>();
    
    public TrainController(){
        Timer timer = new Timer((int) (1000 * DT), e -> {
            controlTrain();
        });
        timer.setRepeats(true);
        timer.start();
    }
    
    public void controlTrain(){
        int iterateLength = trainList.size();
        for (int i = 0; i<iterateLength; i++){
            //Check for test mode here
            double[] trainInfo = this.getTrainInfo(trainList.get(i));
            double currSpeed = trainInfo[0];
            double authority = trainInfo[1];
            double speedLimit = trainInfo[2];
            double[] trainStateInfo = this.getTrainStateInfo(trainStateList.get(i));
            double setPoint = trainStateInfo[0];
            double previousError = trainStateInfo[1];
            double previousIntegration = trainStateInfo[2];
            double[] calcOut = vitalCalc.calculatePower(setPoint, currSpeed, previousError, previousIntegration, speedLimit, authority);
            double power = calcOut[0];
            double velocityError = calcOut[1];
            double integration = calcOut[2];
            setTrainPower(trainList.get(i), trainStateList.get(i),calcOut[0]);
            setTrainPrevious(trainStateList.get(i), calcOut[1], calcOut[2]);
        }
    }
    
    public void addTrain(Train newTrain){
        trainList.add(newTrain);
        TrainState trainState = new TrainState(newTrain);
        trainStateList.add(trainState);
    }
    
    public double[] getTrainInfo(Train train){
        double[] trainInfo = new double[3];
        trainInfo[0] = train.getFeedbackVelocity();
        trainInfo[1] = train.getAuthority();
        trainInfo[2] = train.getSpeedLimit();
        
        return trainInfo;
    }
    
    public double[] getTrainStateInfo(TrainState trainState){
        double[] trainInfo = new double[3];
        trainInfo[0] = trainState.getSetPoint();
        trainInfo[1] = trainState.getPreviousError();
        trainInfo[2] = trainState.getPreviousIntegration();
        return trainInfo;
    }
    
    public void setTrainPower(Train train, TrainState trainState, double power){
        train.setPower(power);
        trainState.setPower(power);
    }
    
    public void setTrainPrevious(TrainState trainState, double previousError, double previousIntegration){
        trainState.setPreviousError(previousError);
        trainState.setPreviousIntegration(previousIntegration);
    }
    
}
