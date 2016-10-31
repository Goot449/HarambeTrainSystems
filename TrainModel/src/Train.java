/**
 * Created by Adam Mannheim on 10/11/2016.
 */
import javax.swing.Timer;

public class Train {

    private static final double DT = 0.01;
    private static final double MINIMUM_VELOCITY = 0.05;
    private static final double SEC_PER_HOUR = 3600;
    private static final double FT_PER_MILE = 5280;
    private static final double FPS_PER_MPH = FT_PER_MILE / SEC_PER_HOUR;
    private static final double WATTS_PER_FOOT_POUND_PER_SECOND = 0.737562149333;

    // TODO: find real mu_k
    private static final double MU_K = 0.1;

    // TODO: set to imperial units
    private static final double G = -9.8;

    private double power;
    private double weight;
    private double force;
    private double acceleration;
    private double velocity;
    private double position;

    public Train(double mass) {
        this.weight = mass;
        this.power = 0;
        this.force = 0;
        this.acceleration = 0;
        this.velocity = 0;
        this.position = 0;

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

    private void step() {
        if(velocity < MINIMUM_VELOCITY) {
            if(power > 0) {
                velocity = MINIMUM_VELOCITY;
            }
            force = 0;
        } else {
            force = power / (WATTS_PER_FOOT_POUND_PER_SECOND * velocity);
            force -= MU_K * weight;
        }
        acceleration = force / weight;
        velocity = velocity + FPS_PER_MPH * acceleration * DT;
        position = position + velocity * DT;
    }
}
