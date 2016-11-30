package com.harambe.traincontroller;

import java.util.ArrayList;
import com.harambe.trainmodel.Train;
import javax.swing.Timer;

public class TrainController {

    private boolean testModeEnabled = true;
    private boolean autoModeEnabled = false;
    private boolean startControl = false;
    private double maxEnginePower = 120000;
    private double DT = 0.001;
    public vitalCalculator vitalCalc = new vitalCalculator();
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
        if (startControl){
            int iterateLength = trainList.size();
            for (int i = 0; i<iterateLength; i++){
                //Check for test mode here
                double[] trainInfo = this.getTrainInfo(trainList.get(i));
                double currSpeed = trainInfo[0];
                double authority = trainInfo[1];
                double speedLimit = trainInfo[2];
                double emergencyBrakesEnabled = trainInfo[3];
                double[] trainStateInfo = this.getTrainStateInfo(trainStateList.get(i));
                double setPoint = trainStateInfo[0];
                double previousError = trainStateInfo[1];
                double previousIntegration = trainStateInfo[2];
                double[] calcOut = vitalCalc.calculatePower(setPoint, currSpeed, previousError, previousIntegration, speedLimit, authority, emergencyBrakesEnabled);
                double power = calcOut[0];
                double velocityError = calcOut[1];
                double integration = calcOut[2];
                if (power<0){
                    trainList.get(i).engageServiceBrakes(true);
                }
                else {
                    trainList.get(i).engageServiceBrakes(false);
                }
                setTrainPower(trainList.get(i), trainStateList.get(i),power);
                setTrainPrevious(trainStateList.get(i), velocityError, integration);
            }
        }
    }
    
    public void setStartControl(boolean startControl) {
        this.startControl = startControl;
    }
    
    public void addTrain(Train newTrain){
        trainList.add(newTrain);
        TrainState trainState = new TrainState(newTrain);
        trainStateList.add(trainState);
    }
    
    public double[] getTrainInfo(Train train){
        double[] trainInfo = new double[4];
        trainInfo[0] = train.getFeedbackVelocity();
        trainInfo[1] = 0; //train.getAuthority();
        trainInfo[2] = train.getSpeedLimit();
        if (train.getEmergencyBreakStatus()){
            trainInfo[3] = 1.0;
        }
        else{
            trainInfo[3] = 0;
        }
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