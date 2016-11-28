import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
    private JComboBox<Train> availableTrains;
    private JLabel numberOfCars;
    private JButton addTrainButton;
    private JButton resetPowerButton;
    private JLabel passengers;
    private JLabel maxPassengers;
    private List<Train> trains;
    private Train train;

    public TrainModel() {
        this(new LinkedList<>());
    }

    public TrainModel(List<Train> trains) {
        this.trains = trains;
        if(trains == null) {
            this.trains = new LinkedList<>();
        }
        if(trains.size() > 0) {
            this.train = trains.get(0);
        } else {
            this.train = null;
        }
        for(Train t : trains) {
            availableTrains.addItem(t);
        }
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
        resetPowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spinner1.setValue(0);
                train.setPower(0);
            }
        });
        availableTrains.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                train = (Train) availableTrains.getSelectedItem();
            }
        });
        addTrainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTrain(new Train(3, trains.size()));
            }
        });

        Timer timer = new Timer(10, e -> {
            updateProperties();
        });
        timer.setRepeats(true);
        timer.start();
    }

    public void addTrain(Train train) {
        if(this.train == null) {
            this.train = train;
        }
        this.trains.add(train);
        this.availableTrains.addItem(train);
    }

    public void updateProperties() {
        if(train == null) return;

        DecimalFormat outputFormat = new DecimalFormat("#.00");
        feedbackSpeed.setText(outputFormat.format(train.getFeedbackVelocity()) + " mph");
        acceleration.setText(outputFormat.format(train.getAcceleration()) + " ft/s²");
        leftDoors.setText(train.leftDoorsAreOpen() ? "Open" : "Closed");
        rightDoors.setText(train.rightDoorsAreOpen() ? "Open" : "Closed");
        lights.setText(train.lightsAreOn() ? "On" : "Off");
        temperature.setText(outputFormat.format(train.getTemperature()) + "° F");
        numberOfCars.setText("" + train.getCarCount());
        speedLimit.setText(train.getSpeedLimit() + "MPH");
        passengers.setText(train.getPassengerCount() + "");
        maxPassengers.setText(train.getMaxPassengers() + "");
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
