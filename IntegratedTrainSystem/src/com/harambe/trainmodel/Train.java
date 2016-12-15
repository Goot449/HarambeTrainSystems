package com.harambe.trainmodel;
/**
 * Created by Adam Mannheim on 10/11/2016.
 * Unless otherwise noted, all Units are metric internally and output as Imperial
 */
import javax.swing.Timer;
import com.harambe.trackmodel.Track;
import com.harambe.trackmodel.Block;

public class Train {
    
    private static final boolean useTrackModel = false;
    
    // default test properties. these are only used for the standalone train model.
    private static final double MU_K = 0;
    private static final double GRADE = 0;
    
    
    // physics constants
    private static final double G = -9.8;                   /* m/s^2 */
    private static final double DT = 0.01;                  /* s */
    private static final double MINIMUM_VELOCITY = 0.05;    /* m/s */
    
    // conversion factors
    private static final double TONS_TO_KG = 907.185;
    private static final double KM_PER_H_TO_M_PER_S = 1/3.6;
    private static final double MPS_TO_MPH = 2.23694;
    private static final double M_TO_FT = 3.2808399;

    // train data
    private static final double SERVICE_BRAKE_DECELERATION = 1.2;       /* m/s^2 */
    private static final double EMERGENCY_BRAKE_DECELERATION = 2.73;    /* m/s^2 */
    private static final double MAX_SPEED = 70 * KM_PER_H_TO_M_PER_S;   /* m/s */
    private static final double MASS_OF_PERSON = 80.7;                  /* kg (average mass in North America) */
    private static final double EMPTY_CAR_MASS = 40.9 * TONS_TO_KG;     /* kg */
    private static final double CAR_LENGTH = 32.2;                      /* m */
    private static final double CAR_HEIGHT = 3.42;                      /* m */
    private static final double CAR_WIDTH = 2.65;                       /* m */
    private static final int MAX_PASSENGERS = 148;

    private TrainModel trainModel;

    private int id;
    private int passengerCount;
    private int carCount;

    private double power;
    private double force;
    private double acceleration;
    private double velocity;
    private double position;
    private double temperature; /* Degrees Fahrenheit */

    private boolean leftDoorsOpen;
    private boolean rightDoorsOpen;
    private boolean lights;

    private boolean serviceBrakesEngaged;
    private boolean emergencyBrakesEngaged;

    private boolean engineFailure;
    private boolean brakeFailure;
    private boolean signalFailure;
    
    public Train() throws Exception {
        this(1, 0);
    }

    public Train(int carCount, int id, TrainModel trainModel) throws Exception {
        this(carCount, id);
        this.trainModel = trainModel;
    }

    public Train(int carCount, int id) throws Exception{
        this.carCount = carCount;
        this.id = id;
        this.power = 0;
        this.force = 0;
        this.acceleration = 0;
        this.velocity = 0;
        this.position = 0;
        this.lights = true;
        this.leftDoorsOpen = false;
        this.rightDoorsOpen = false;
        this.temperature = 70;
        Timer timer = new Timer((int) (1000 * DT), e -> {
            step();
        });
        timer.setRepeats(true);
        timer.start();
    }

    public void setEngineFailure(boolean engaged) {
        this.trainModel.setEngineFailure(engaged);
    }    
    public void setBrakeFailure(boolean engaged) {
        this.trainModel.setBrakeFailure(engaged);
        if(engaged) this.serviceBrakesEngaged = false;
    }    
    public void setSignalFailure(boolean engaged) {
        this.trainModel.setSignalFailure(engaged);
    }
    public boolean hasEngineFailure() {
        return this.trainModel.hasEngineFailure();
    }
    public boolean hasSignalFailure() {
        return this.trainModel.hasSignalFailure();
    }
    public boolean hasBrakeFailure() {
        return this.trainModel.hasBrakeFailure();
    }

    public int getId() {
        return id;
    }
    public boolean leftDoorsAreOpen() {
        return leftDoorsOpen;
    }
    public void setLeftDoors(boolean open) {
        this.leftDoorsOpen = open;
    }
    public boolean rightDoorsAreOpen() {
        return rightDoorsOpen;
    }
    public void setRightDoors(boolean open) {
        this.rightDoorsOpen = open;
    }

    public boolean lightsAreOn() {
        return lights;
    }
    public void setLights(boolean lights) {
        this.lights = lights;
    }

    public void engageServiceBrakes(boolean engaged) {
        this.serviceBrakesEngaged = engaged;
    }
    public void engageEmergencyBrakes(boolean engaged) {
        this.emergencyBrakesEngaged = engaged;
    }
    public boolean getServiceBreakStatus() {
        return this.serviceBrakesEngaged;
    }
    public boolean getEmergencyBreakStatus() {
        return this.emergencyBrakesEngaged;
    }
    public double getTemperature() {                    /* Degrees F */
        return this.temperature;
    }
    public void setTemperature(double temperature) {    /* Degrees F */
        this.temperature = temperature;
    }
    public int getCarCount() {
        return carCount;
    }
    public double getLength() {
        return this.carCount * CAR_LENGTH * M_TO_FT; 
    }
    public double getWidth() {                          /* ft */
        return CAR_WIDTH * M_TO_FT; 
    }
    public double getHeight() {
        return CAR_HEIGHT * M_TO_FT; 
    }
    public double getCommandSpeed() {
        return getBlock() == null ? getSpeedLimit() : getBlock().getTrainCommandedSpeed();
    }
    public double getSpeedLimit() {
        return getBlock() == null ? 15 : getBlock().getSpeedLimit();
    }
    public int getPassengerCount() {
        return passengerCount;
    }
    public int getMaxPassengers() {
        return MAX_PASSENGERS * carCount;
    }    
    public void setTrainModel(TrainModel trainModel) {
        this.trainModel = trainModel;
    }
    public void setPower(double power) {
        this.power = power;
    }
    public double getFeedbackVelocity() {
        return velocity * MPS_TO_MPH;
    }
    public double getAcceleration() {
        return acceleration * M_TO_FT;
    }
    public double getMass() {
        return EMPTY_CAR_MASS + passengerCount * MASS_OF_PERSON;
    }
    public double getWeight() {
        return (EMPTY_CAR_MASS + passengerCount * MASS_OF_PERSON) / TONS_TO_KG;
    }
    public int getAuthority() throws Exception {
        if(this.trainModel == null || this.trainModel.getTrack() == null) {
            throw new Exception("Cannot get authority unless the train has an associated track.");
        }
        return this.getBlock().getTrainAuthority();
    }

    /**
     * Attempts to add specified number of passengers. This will not increase the number of passengers
     * over capacity.
     * @param newPassengers The number of passengers that will attempt to board
     * @return the actual number of passengers that boarded
     */
    public int addPassengers(int newPassengers) {
        int availableSpace = getMaxPassengers() - this.passengerCount;
        int passengersAdded = Math.min(availableSpace, newPassengers);
        this.passengerCount += passengersAdded;
        return passengersAdded;
    }
    
    /**
     * Attempts to add specified number of passengers. This will not remove the
     * number of passengers below 0.
     * @param newPassengers The number of passengers that will attempt to leave
     * @return the actual number of passengers that left
     */
    public int removePassengers(int newPassengers) {
        int passengersRemoved = Math.min(this.passengerCount, newPassengers);
        this.passengerCount -= passengersRemoved;
        return passengersRemoved;
    }
    
    /**
     * Tests if train is at specified block number
     * @param blockNumber the block to test
     * @return true if block is available and train is at specified block number
     */
    public boolean isAtBlock(int blockNumber) {
        return getBlock() != null && getBlock().getBlockNumber() == blockNumber;
    }

    public String toString() {
        return "Train " + this.id;
    }
    
    // Tries to get current block. If unavailable, returns null
    private Block getBlock() {
        try {
            return this.trainModel.getTrack().getBlock(this.id);
        } catch(NullPointerException e) {
            return null;
        }
    }

    // Calculates physics (triggered every DT)
    private void step() {
        //System.out.println(emergencyBrakesEngaged);
        Track track = this.trainModel == null ? null : this.trainModel.getTrack();
        double grade = getBlock() == null ? GRADE : getBlock().getGrade();
        double mass = this.getMass();
        if(Math.abs(velocity) < MINIMUM_VELOCITY) {
            if(power > 0) {
                velocity = velocity >= 0 ? MINIMUM_VELOCITY : -MINIMUM_VELOCITY;
            } else {
                velocity = 0;
            }
            force = 0;
        } else {
            double friction = getBlock() == null ? MU_K : getBlock().getFrictionCoefficient();
            force = power / velocity;
            force -= MU_K * mass;
        }
        force += G * Math.sin(Math.toRadians(grade)) * mass;
        acceleration = force / mass;
        if(Math.abs(velocity) > 0) {
            if(emergencyBrakesEngaged) {
                acceleration -= EMERGENCY_BRAKE_DECELERATION;
            } else if(serviceBrakesEngaged) {
                acceleration -= SERVICE_BRAKE_DECELERATION;
            }
        }
        double rate = this.trainModel != null ? this.trainModel.getRate() : 1;
        velocity = velocity + acceleration * DT * rate;
        position = position + velocity * DT * rate;
        if(track != null)
            track.updateDistance(id, position);
    }
}
