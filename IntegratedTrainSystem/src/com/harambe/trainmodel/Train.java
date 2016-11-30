package com.harambe.trainmodel;
/**
 * Created by Adam Mannheim on 10/11/2016.
 * Unless otherwise noted, all Units are metric internally and output as Imperial
 */
import javax.swing.Timer;
import com.harambe.trackmodel.Track;
import com.harambe.trackmodel.Block;

public class Train {
    
    public static boolean USE_TRACK_MODEL = false;
    /** TODO
     * Handle Passengers
     * Add passengers to UI
     * Add Train selector to UI
     * Add number of cars to UI
     * Finish Setters/Getters
     * Add test module
     * Set output to imperial units
     * Finish outputting all data
     */

    // DELETE AFTER INTEGRATION
    /**private class Block {
        private Block() {}
        private double getGrade() {
            return 0;
        }
        private int getSpeedLimit() {
            return 50;
        }
    }
    private class TrackModel {
        private TrackModel() {}
        private Block getBlock(int id) {
            return new Block();
        }
    }**/

    /**
    private static final double SEC_PER_HOUR = 3600;
    private static final double FT_PER_MILE = 5280;
    private static final double FPS_PER_MPH = FT_PER_MILE / SEC_PER_HOUR;
    private static final double WATTS_PER_FOOT_POUND_PER_SECOND = 0.737562149333;

     **/
    // TODO: find real mu_k
    private static final double MU_K = 0.1;
    private static final double G = -9.8;
    private static final double DT = 0.01;
    private static final double MINIMUM_VELOCITY = 0.05;

    // conversion factors
    private static final double TONS_TO_KG = 907.185;
    private static final double KM_PER_H_TO_M_PER_S = 1/3.6;
    private static final double MPS_TO_MPH = 2.23694;
    private static final double M_TO_FT = 3.2808399;

    // train data
    private static final double SERVICE_BRAKE_DECELERATION = 1.2;
    private static final double EMERGENCY_BRAKE_DECELERATION = 2.73;
    private static final double MAX_SPEED = 70 * KM_PER_H_TO_M_PER_S;
    private static final double MASS_OF_PERSON = 80.7;  // average mass in North America
    private static final double EMPTY_CAR_MASS = 40.9 * TONS_TO_KG;
    private static final int MAX_PASSENGERS = 148;

    private Track track;
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
    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    public int getCarCount() {
        return carCount;
    }
    public int getSpeedLimit() {
        return USE_TRACK_MODEL ? track.getBlock(this.id).getSpeedLimit() : 15;
    }
    public int getPassengerCount() {
        return passengerCount;
    }
    public int getMaxPassengers() {
        return MAX_PASSENGERS * carCount;
    }

    /**
     * Attempts to add specified number of passengers. This will not increase the number of passengers
     * over capacity.
     * @param newPassengers The number of passengers that will attempt to board
     * @return the actual number of passengers that boarded
     */
    public int addPassengers(int newPassengers) {
        int availableSpace = getMaxPassengers() - newPassengers;
        int passengersAdded = Math.min(availableSpace, newPassengers);
        this.passengerCount += passengersAdded;
        return passengersAdded;
    }

    public Train() throws Exception {
        this(0, 0);
    }

    public Train(int carCount, int id, TrainModel trainModel) throws Exception {
        this(carCount, id);
        this.trainModel = trainModel;
    }

    public Train(int carCount, int id) throws Exception{
        this.track = USE_TRACK_MODEL ? new Track() : null;
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

    public String toString() {
        return "Train " + this.id;
    }

    private void step() {
        double grade = USE_TRACK_MODEL ? track.getBlock(this.id).getGrade() : 0;
        double mass = this.getMass();
        if(Math.abs(velocity) < MINIMUM_VELOCITY) {
            if(power > 0) {
                velocity = velocity > 0 ? MINIMUM_VELOCITY : -MINIMUM_VELOCITY;
            } else {
                velocity = 0;
            }
            force = 0;
        } else {
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

        velocity = velocity + acceleration * DT;
        position = position + velocity * DT;
    }
}
