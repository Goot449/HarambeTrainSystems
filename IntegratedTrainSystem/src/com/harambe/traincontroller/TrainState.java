/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traincontroller;

/**
 *
 * @author Alex
 */
public class TrainState {
    
    private int trainID;
    private int routeID;
    private double setPoint;
    private double power;
    private double previousError;
    private double previousIntegration;
    
    private double testSpeed;
    private double testSpeedLimit;
    private double testAuthority;
    
    private boolean lightsOn;
    private boolean leftDoorsOpen;
    private boolean rightDoorsOpen;
    private int cabinTemp;
    private boolean engineFunctioning;
    private boolean brakeStatus;
    private boolean receivingSignal;
    private boolean emergencyBrakeStatus;
    private boolean serviceBrakeStatus;
    
    public TrainState(Train newTrain){
        this.setTrainID(newTrain.id);
        this.setLightsOn(newTrain.lightsAreOn());
        this.setLeftDoorsOpen(newTrain.leftDoorsAreOpen());
        this.setRightDoorsOpen(newTrain.rightDoorsAreOpen());
        //have adam change break to brake
        this.setEmergencyBrakeStatus(newTrain.getEmegencyBrakeStatus());
        this.setServiceBrakeStatus(newTrain.getServiceBrakeStatus());
        
        //have adam add a method to change cabin temp
        this.setCabinTemp(newTrain.getCabinTemperature());
        this.setEngineFunctioning(true);
        this.setPreviousError(0);
        this.setPreviousIntegration(0);
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

    public boolean isEngineFunctioning() {
        return engineFunctioning;
    }

    public void setEngineFunctioning(boolean engineFunctioning) {
        this.engineFunctioning = engineFunctioning;
    }

    public boolean getBrakeStatus() {
        return brakeStatus;
    }

    public void setBrakeStatus(boolean brakeStatus) {
        this.brakeStatus = brakeStatus;
    }

    public boolean isReceivingSignal() {
        return receivingSignal;
    }

    public void setReceivingSignal(boolean receivingSignal) {
        this.receivingSignal = receivingSignal;
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
    
}
