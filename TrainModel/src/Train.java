/**
 * Created by Adam Mannheim on 10/11/2016.
 */
import javax.sound.midi.Track;
import javax.swing.Timer;

public class Train {

    // DELETE AFTER INTEGRATION
    private class Block {
        private Block() {}
        private double getGrade() {
           return -15;
        }
    }
    private class TrackModel {
        private TrackModel() {}
        private Block getBlock(int id) {
            return new Block();
        }
    }

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


    // train data
    private static final double SERVICE_BRAKE_DECELERATION = 1.2;
    private static final double EMERGENCY_BRAKE_DECELERATION = 2.73;
    private static final double MAX_SPEED = 70 * KM_PER_H_TO_M_PER_S;
    private static final double MASS_OF_PERSON = 80.7;  // average mass in North America
    private static final double EMPTY_CAR_MASS = 40.9 * TONS_TO_KG;
    private static final int MAX_PASSENGERS = 148;

    private TrackModel trackModel;

    private int id;
    private int passengerCount;
    private int carCount;

    private double power;
    private double force;
    private double acceleration;
    private double velocity;
    private double position;
    private double temperature;

    private boolean leftDoorsOpen;
    private boolean rightDoorsOpen;
    private boolean lights;

    private boolean serviceBrakesEngaged;
    private boolean emergencyBrakesEngaged;

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

    public Train() {
        this(0, 0);
    }

    public Train(int carCount, int id) {
        this.trackModel = new TrackModel();
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

        Timer timer = new Timer((int) (1000 * DT), e -> {
            step();
        });
        timer.setRepeats(true);
        timer.start();
    }

    /**
     * Sets the power and updates the force and acceleration accordingly.
     * If the velocity is less than the minimum, it will be updated.
     * @param power the input power
     */
    public void setPower(double power) {
        this.power = power;
    }

    /**
     * gets the feedback velocity
     * @return feedback velocity
     */
    public double getFeedbackVelocity() {
        return velocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getMass() {
        return EMPTY_CAR_MASS + passengerCount * MASS_OF_PERSON;
    }

    private void step() {
        double grade = trackModel.getBlock(this.id).getGrade();
        double mass = this.getMass();
        if(velocity < MINIMUM_VELOCITY) {
            if(power > 0) {
                velocity = MINIMUM_VELOCITY;
            }
            force = 0;
        } else {
            force = power / velocity;
            force -= MU_K * mass;
        }
        force += G * Math.sin(Math.toRadians(grade)) * mass;
        acceleration = force / mass;
        if(emergencyBrakesEngaged) {
            acceleration -= EMERGENCY_BRAKE_DECELERATION;
        } else if(serviceBrakesEngaged) {
            acceleration -= SERVICE_BRAKE_DECELERATION;
        }

        velocity = velocity + acceleration * DT;
        position = position + velocity * DT;
    }
}
