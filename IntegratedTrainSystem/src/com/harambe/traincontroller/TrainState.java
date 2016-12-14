package com.harambe.traincontroller;


import com.harambe.trainmodel.Train;

public class TrainState {
    
    private int trainID;
    private int routeID;
    private double setPoint;
    private double power;
    private double previousError;
    private double previousIntegration;
    private boolean previousFailure;
    
    private double testSpeed;
    private double testSpeedLimit;
    private double testAuthority;
    
    private boolean lightsOn;
    private boolean leftDoorsOpen;
    private boolean rightDoorsOpen;
    private int cabinTemp;
    private boolean engineFailure;
    private boolean brakeFailure;
    private boolean signalFailure;
    private boolean emergencyBrakeStatus;
    private boolean guiSetServiceBrake;
    private boolean serviceBrakeStatus;
    private boolean manualModeEnabled;
    
    public TrainState(Train newTrain){
        this.setTrainID(newTrain.getId());
        this.setSetPoint(newTrain.getSpeedLimit());
        this.setLightsOn(newTrain.lightsAreOn());
        this.setLeftDoorsOpen(newTrain.leftDoorsAreOpen());
        this.setRightDoorsOpen(newTrain.rightDoorsAreOpen());
        this.setGuiSetServiceBrake(false);
        this.setManualModeEnabled(false);
        
        this.setEmergencyBrakeStatus(newTrain.getEmergencyBreakStatus());
        this.setServiceBrakeStatus(newTrain.getServiceBreakStatus());
        
        //have adam add a method to change cabin temp
        this.setCabinTemp((int)newTrain.getTemperature());
        this.setEngineFailure(false);
        this.setSignalFailure(false);
        this.setBrakeFailure(false);
        this.setPreviousError(0);
        this.setPreviousIntegration(0);
        this.setPreviousFailure(false);
    }

    public int getTrainID() {
        return trainID;
    }

    public void setTrainID(int trainID) {
        this.trainID = trainID;
    }

    public boolean areLightsOn() {
        return lightsOn;
    }

    public void setLightsOn(boolean lightsOn) {
        this.lightsOn = lightsOn;
    }

    public boolean areLeftDoorsOpen() {
        return leftDoorsOpen;
    }

    public void setLeftDoorsOpen(boolean leftDoorsOpen) {
        this.leftDoorsOpen = leftDoorsOpen;
    }

    public boolean areRightDoorsOpen() {
        return rightDoorsOpen;
    }

    public void setRightDoorsOpen(boolean rightDoorsOpen) {
        this.rightDoorsOpen = rightDoorsOpen;
    }

    public int getCabinTemp() {
        return cabinTemp;
    }

    public void setCabinTemp(int cabinTemp) {
        this.cabinTemp = cabinTemp;
    }

    public boolean hasEngineFailure() {
        return engineFailure;
    }

    public void setEngineFailure(boolean engineFailure) {
        this.engineFailure = engineFailure;
    }

    public boolean hasBrakeFailure() {
        return brakeFailure;
    }

    public void setBrakeFailure(boolean brakeFailure) {
        this.brakeFailure = brakeFailure;
    }

    public boolean hasSignalFailure() {
        return signalFailure;
    }

    public void setSignalFailure(boolean signalFailure) {
        this.signalFailure = signalFailure;
    }

    public boolean getEmergencyBrakeStatus() {
        return emergencyBrakeStatus;
    }

    public void setEmergencyBrakeStatus(boolean emergencyBrakeStatus) {
        this.emergencyBrakeStatus = emergencyBrakeStatus;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }   
    
    public double getSetPoint() {
        return setPoint;
    }

    public void setSetPoint(double setPoint) {
        this.setPoint = setPoint;
    }
    
    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }
    
    public boolean getServiceBrakeStatus() {
        return serviceBrakeStatus;
    }

    public void setServiceBrakeStatus(boolean serviceBrakeStatus) {
        this.serviceBrakeStatus = serviceBrakeStatus;
    }
    
    public boolean didGuiSetServiceBrake() {
        return guiSetServiceBrake;
    }

    public void setGuiSetServiceBrake(boolean guiSetEmergencyBrake) {
        this.guiSetServiceBrake = guiSetEmergencyBrake;
    }
    
    public double getPreviousError() {
        return previousError;
    }

    public void setPreviousError(double previousError) {
        this.previousError = previousError;
    }
    
    public double getPreviousIntegration() {
        return previousIntegration;
    }

    public void setPreviousIntegration(double previousIntegration) {
        this.previousIntegration = previousIntegration;
    }
    
    public double getTestSpeedLimit() {
        return testSpeedLimit;
    }

    public void setTestSpeedLimit(double testSpeedLimit) {
        this.testSpeedLimit = testSpeedLimit;
    }
    
    public double getTestSpeed() {
        return testSpeed;
    }

    public void setTestSpeed(double testSpeed) {
        this.testSpeed = testSpeed;
    }
    
    public boolean isManualModeEnabled() {
        return manualModeEnabled;
    }

    public void setManualModeEnabled(boolean manualModeEnabled) {
        this.manualModeEnabled = manualModeEnabled;
    }
    
    public void setPreviousFailure(boolean previousFailure){
        this.previousFailure = previousFailure;
    }
    
    public boolean getPreviousFailure(){
        return this.previousFailure;
    }
    
}
