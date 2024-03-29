package com.harambe.traincontroller;

import java.util.ArrayList;
import com.harambe.trainmodel.Train;
import javax.swing.Timer;

public class TrainController {

    private boolean testModeEnabled = false;
    private boolean autoModeEnabled = false;
    private boolean startControl = false;
    private double maxEnginePower = 120000;
    private double DT = 0.001;
    public vitalCalculator vitalCalc = new vitalCalculator();
    public ArrayList<Train> trainList = new ArrayList<Train>();
    public ArrayList<TrainState> trainStateList = new ArrayList<TrainState>();

    public TrainController() {
        Timer timer = new Timer((int) (1000 * DT), e -> {
            controlTrain();
        });
        timer.setRepeats(true);
        timer.start();
    }

    public void controlTrain() {
        if (startControl) {
            int iterateLength = trainList.size();
            for (int i = 0; i < iterateLength; i++) {
                //Check for test mode here
                double[] trainInfo = this.getTrainInfo(trainList.get(i));
                double currSpeed = trainInfo[0];
                double authority = trainInfo[1];
                double speedLimit = trainInfo[2];
                double emergencyBrakesEnabled = trainInfo[3];
                double serviceBrakesEnabled = trainInfo[4];
                //add logic for speedLimit here. Should be 0 for approaching a station
                //if authority and speed limit are both 0, approaching station
                //if approaching station and current speed = 0, stopped at station
                //if stopped at station, wait 10000 iterations
                double[] trainStateInfo = this.getTrainStateInfo(trainStateList.get(i));
                double setPoint = trainStateInfo[0];
                double previousError = trainStateInfo[1];
                double previousIntegration = trainStateInfo[2];
                double guiSetServiceBrake = trainStateInfo[3];
                double[] calcOut = vitalCalc.calculatePower(setPoint, currSpeed, previousError, previousIntegration, speedLimit, authority, emergencyBrakesEnabled);
                double power = calcOut[0];
                double velocityError = calcOut[1];
                double integration = calcOut[2];
                if (authority == 0.0) {
                    trainList.get(i).engageServiceBrakes(true);
                    trainList.get(i).engageEmergencyBrakes(true);
                    trainStateList.get(i).setGuiSetServiceBrake(false);
                    power = 0;
                } else if (trainList.get(i).getEmergencyBreakStatus()) {
                    if (authority > 0) {
                        trainList.get(i).engageEmergencyBrakes(false);
                        trainList.get(i).engageServiceBrakes(false);
                    }
                }
                if (power < 0) {
                    trainList.get(i).engageServiceBrakes(true);
                    trainStateList.get(i).setGuiSetServiceBrake(false);
                    power = 0;
                } else if (serviceBrakesEnabled == 1.0 & (power > 0) & guiSetServiceBrake == 0.0) {
                    trainList.get(i).engageEmergencyBrakes(false);
                    trainList.get(i).engageServiceBrakes(false);
                }
                if (guiSetServiceBrake == 1.0) {
                    power = 0;
                }
                // Check for failures here
                boolean failureExists = checkForFailures(trainList.get(i), trainStateList.get(i), testModeEnabled);
                if (failureExists) {
                    trainList.get(i).engageEmergencyBrakes(true);
                    power = 0.0;
                    trainStateList.get(i).setPreviousFailure(true);
                } else if (trainStateList.get(i).getPreviousFailure()) {
                    trainStateList.get(i).setPreviousFailure(false);
                    trainList.get(i).engageEmergencyBrakes(false);
                }
                setTrainPower(trainList.get(i), trainStateList.get(i), power);
                setTrainPrevious(trainStateList.get(i), velocityError, integration);
            }
        }
    }

    public void setStartControl(boolean startControl) {
        this.startControl = startControl;
    }

    public void addTrain(Train newTrain) {
        trainList.add(newTrain);
        TrainState trainState = new TrainState(newTrain);
        trainStateList.add(trainState);
    }

    public double[] getTrainInfo(Train train) {
        double[] trainInfo = new double[5];
        trainInfo[0] = train.getFeedbackVelocity();
        //trainInfo[1] = 5; 
        boolean currBlockIsEndBlock = false;
        try {
            int auth = train.getAuthority();

            //System.out.println(auth);
            if (auth == 0) {
                currBlockIsEndBlock = true;//false; 
            }
        } catch (Exception e) {

        }
        if (!currBlockIsEndBlock) {
            trainInfo[1] = 1.0;
        } else {
            trainInfo[1] = 0.0;
        }
        trainInfo[2] = (2.23694) * train.getCommandSpeed();
        if (train.getEmergencyBreakStatus()) {
            trainInfo[3] = 1.0;
        } else {
            trainInfo[3] = 0;
        }
        if (train.getServiceBreakStatus()) {
            trainInfo[4] = 1.0;
        } else {
            trainInfo[4] = 0;
        }
        return trainInfo;
    }

    public double[] getTrainStateInfo(TrainState trainState) {
        double[] trainInfo = new double[4];
        trainInfo[0] = trainState.getSetPoint();
        trainInfo[1] = trainState.getPreviousError();
        trainInfo[2] = trainState.getPreviousIntegration();
        if (trainState.didGuiSetServiceBrake()) {
            trainInfo[3] = 1.0;
        } else {
            trainInfo[3] = 0;
        }
        return trainInfo;
    }

    public boolean checkForFailures(Train train, TrainState trainState, boolean testModeEnabled) {
        boolean hasFailure;
        if (train.hasBrakeFailure() || train.hasEngineFailure() || train.hasSignalFailure()) {
            hasFailure = true;
        } else if (trainState.hasBrakeFailure() || trainState.hasEngineFailure() || trainState.hasSignalFailure()) {
            hasFailure = true;
        } else {
            hasFailure = false;
        }
        return hasFailure;
    }

    public void setTrainPower(Train train, TrainState trainState, double power) {
        train.setPower(power);
        trainState.setPower(power);
    }

    public void setTrainPrevious(TrainState trainState, double previousError, double previousIntegration) {
        trainState.setPreviousError(previousError);
        trainState.setPreviousIntegration(previousIntegration);
    }

}
