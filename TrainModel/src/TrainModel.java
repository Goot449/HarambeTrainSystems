import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


/**
 * Created by Adam1 on 10/11/2016.
 */
public class TrainModel {
    private JPanel panel1;
    private JButton emergencyBrake;
    private JButton serviceBreakButton;
    private JButton trainEngineFailureButton;
    private JButton signalFailureButton;
    private JButton brakeFailureButton;
    private JButton setPowerButton;
    private JSpinner spinner1;
    private JLabel feedbackSpeed;
    private JLabel acceleration;
    private JLabel leftDoors;
    private JLabel rightDoors;
    private JLabel lights;
    private JLabel temperature;
    private JLabel maxAcceleration;
    private JLabel maxDeceleration;
    private JLabel speedLimit;
    private JLabel commandSpeed;
    private List<Train> trains;
    private Train train;

    public TrainModel() {
        trains = new LinkedList<>();
        train = new Train(1, 1);
        trains.add(train);
        emergencyBrake.addActionListener(e -> {
            train.engageEmergencyBrakes(!train.getEmergencyBreakStatus());
        });
        serviceBreakButton.addActionListener(e -> {
            train.engageServiceBrakes(!train.getServiceBreakStatus());
        });
        setPowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                train.setPower((double) spinner1.getValue() * 1000);
            }
        });
        Timer timer = new Timer((int) (1), e -> {
            updateProperties();
        });
        timer.setRepeats(true);
        timer.start();
    }

    public void updateProperties() {
        DecimalFormat outputFormat = new DecimalFormat("#.00");
        feedbackSpeed.setText(outputFormat.format(train.getFeedbackVelocity()) + " mph");
        acceleration.setText(outputFormat.format(train.getAcceleration()) + " ft/s²");
        leftDoors.setText(train.leftDoorsAreOpen() ? "Open" : "Closed");
        rightDoors.setText(train.rightDoorsAreOpen() ? "Open" : "Closed");
        lights.setText(train.lightsAreOn() ? "On" : "Off");
        //temperature.setText(outputFormat.format(train.getTemperature()) + "° F");
    }

    public static void main(String[] args) throws Exception {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("TrainModel");
        frame.setContentPane(new TrainModel().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    private void createUIComponents() {
        SpinnerNumberModel model = new SpinnerNumberModel(9.9, 0, 999999999., 0.1);
        spinner1 = new JSpinner(model);
        /*JComponent comp = spinner1.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);*/
    }
}
