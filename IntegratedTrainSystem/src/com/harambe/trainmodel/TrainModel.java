/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harambe.trainmodel;

import com.harambe.trackmodel.Track;
import java.awt.Color;
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
 *
 * @author Adam1
 */
public class TrainModel extends javax.swing.JFrame {

    private List<Train> trains;
    private Train train;
    private Track track;
    private double rate;
    private boolean engineFailure;
    private boolean brakeFailure;
    private boolean signalFailure;
    
    public TrainModel() throws Exception {
        this(new LinkedList<Train>());
    }
    
    public TrainModel(Track track) throws Exception {
        this();
        this.track = track;
    }
    /**
     * Creates new form TrainModel
     */
    public TrainModel(List<Train> trains) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
        this.rate = 1;
        this.engineFailure = false;
        this.signalFailure = false;
        this.brakeFailure = false;
        if(this.trains == null) {
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
        serviceBrake.addActionListener(e -> {
            train.engageServiceBrakes(!train.getServiceBreakStatus());
        });
        setPowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                train.setPower((double) spinner1.getValue() * 1000);
                System.out.println(e);
            }
        });
        availableTrains.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                train = (Train) availableTrains.getSelectedItem();
            }
        });
        
        Timer timer = new Timer(10, e -> {
            updateProperties();
        });
        timer.setRepeats(true);
        timer.start();
        System.out.println(this.trains);
    }
    
    public double getRate() {
        return this.rate;
    }
    
    public boolean setRate(double rate) {
        if(rate < 0 || rate > 10) {
            return false;
        }
        this.rate = rate;
        return true;
    }
    
    public void setEngineFailure(boolean engaged) {
        this.engineFailure = engaged;
    }    
    public void setBrakeFailure(boolean engaged) {
        this.brakeFailure = engaged;
        for(Train t : this.trains) {
            t.engageServiceBrakes(false);
        }
    }    
    public void setSignalFailure(boolean engaged) {
        this.signalFailure = engaged;
    }
    public boolean hasEngineFailure() {
        return this.engineFailure;
    }
    public boolean hasSignalFailure() {
        return this.signalFailure;
    }
    public boolean hasBrakeFailure() {
        return this.brakeFailure;
    }
    
    
    public Track getTrack() {
        return this.track;
    }
    
    public void setTrack() {
        this.track = track;
    }
    
    public void addTrain(Train train) {
        if(this.train == null) {
            this.train = train;
        }
        train.setTrainModel(this);
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
        speedLimit.setText(outputFormat.format(train.getSpeedLimit(true)) + " mph");
        passengers.setText(train.getPassengerCount() + "");
        maxPassengers.setText(train.getMaxPassengers() + "");
        weight.setText(outputFormat.format(train.getWeight()) + " Tons");
        width.setText(outputFormat.format(train.getWidth()) + " ft");
        length.setText(outputFormat.format(train.getLength()) + " ft");
        height.setText(outputFormat.format(train.getHeight()) + " ft");
        
        applyColor(trainEngineFailureButton, Color.red, hasEngineFailure());
        applyColor(signalFailureButton, Color.red, hasSignalFailure());
        applyColor(brakeFailureButton, Color.red, hasBrakeFailure());
        applyColor(serviceBrake, Color.red, train.getServiceBreakStatus());
        applyColor(emergencyBrake, Color.red, train.getEmergencyBreakStatus());
        
        this.serviceBrake.setEnabled(!brakeFailure);
        this.serviceBrake.setToolTipText(brakeFailure ? "Cannot use service breaks with a brake failure" : null);
    }
    
    private void applyColor(JButton button, Color color, boolean apply) {
        button.setBackground(apply ? color : Color.white);
        button.setForeground(apply ? color : Color.black);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        leftDoors = new javax.swing.JLabel();
        rightDoors = new javax.swing.JLabel();
        temperature = new javax.swing.JLabel();
        lights = new javax.swing.JLabel();
        speedLimit = new javax.swing.JLabel();
        feedbackSpeed = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        acceleration = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        numberOfCars = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        passengers = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        maxPassengers = new javax.swing.JLabel();
        emergencyBrake = new javax.swing.JButton();
        serviceBrake = new javax.swing.JButton();
        trainEngineFailureButton = new javax.swing.JButton();
        signalFailureButton = new javax.swing.JButton();
        brakeFailureButton = new javax.swing.JButton();
        availableTrains = new javax.swing.JComboBox<>();
        testPanel = new javax.swing.JPanel();
        spinner1 = new javax.swing.JSpinner();
        setPowerButton = new javax.swing.JToggleButton();
        addTrainButton = new javax.swing.JButton();
        addPassengersSpinner = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        addPassengersButton = new javax.swing.JButton();
        temperatureSpinner = new javax.swing.JSpinner();
        setTemperatureButton = new javax.swing.JButton();
        toggleRightDoorsButton = new javax.swing.JButton();
        toggleLeftDoorsButton = new javax.swing.JButton();
        toggleLightsButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        weight = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        width = new javax.swing.JLabel();
        height = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        length = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Train Model UI");

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("Left Doors");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setText("Right Doors");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setText("Lights");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setText("Temperature");

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setText("Speed Limit");

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel10.setText("Feedback Speed");

        leftDoors.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        leftDoors.setText("N/A");

        rightDoors.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        rightDoors.setText("N/A");

        temperature.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        temperature.setText("N/A");

        lights.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lights.setText("N/A");

        speedLimit.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        speedLimit.setText("N/A");

        feedbackSpeed.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        feedbackSpeed.setText("N/A");

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel11.setText("Acceleration");

        acceleration.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        acceleration.setText("N/A");

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel12.setText("Number of Cars");

        numberOfCars.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        numberOfCars.setText("N/A");

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel13.setText("Passengers");

        passengers.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        passengers.setText("N/A");

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel14.setText("Max Passengers");

        maxPassengers.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        maxPassengers.setText("N/A");

        emergencyBrake.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emergencyBrake.setText("Emergency Brake");
        emergencyBrake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emergencyBrakeActionPerformed(evt);
            }
        });

        serviceBrake.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        serviceBrake.setText("Service Brake");
        serviceBrake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceBrakeActionPerformed(evt);
            }
        });

        trainEngineFailureButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        trainEngineFailureButton.setText("Engine Failure");
        trainEngineFailureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainEngineFailureButtonActionPerformed(evt);
            }
        });

        signalFailureButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        signalFailureButton.setText("Signal Failure");
        signalFailureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signalFailureButtonActionPerformed(evt);
            }
        });

        brakeFailureButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        brakeFailureButton.setText("Brake Failure");
        brakeFailureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brakeFailureButtonActionPerformed(evt);
            }
        });

        availableTrains.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        availableTrains.setName(""); // NOI18N

        testPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        testPanel.setToolTipText("");

        spinner1.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 0.1d));

        setPowerButton.setSelected(true);
        setPowerButton.setText("setPower");
        setPowerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setPowerButtonActionPerformed(evt);
            }
        });

        addTrainButton.setText("addTrain");
        addTrainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTrainButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Test Pane");

        addPassengersButton.setText("Add Passengers");
        addPassengersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPassengersButtonActionPerformed(evt);
            }
        });

        setTemperatureButton.setText("Set Temperature");
        setTemperatureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setTemperatureButtonActionPerformed(evt);
            }
        });

        toggleRightDoorsButton.setText("Toggle Right Doors");
        toggleRightDoorsButton.setToolTipText("");
        toggleRightDoorsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleRightDoorsButtonActionPerformed(evt);
            }
        });

        toggleLeftDoorsButton.setText("Toggle Left Doors");
        toggleLeftDoorsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleLeftDoorsButtonActionPerformed(evt);
            }
        });

        toggleLightsButton.setText("Toggle Lights");
        toggleLightsButton.setToolTipText("");
        toggleLightsButton.setActionCommand("Toggle Lights");
        toggleLightsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleLightsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout testPanelLayout = new javax.swing.GroupLayout(testPanel);
        testPanel.setLayout(testPanelLayout);
        testPanelLayout.setHorizontalGroup(
            testPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(testPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addTrainButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(testPanelLayout.createSequentialGroup()
                        .addGroup(testPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addPassengersSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(temperatureSpinner))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(testPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(setTemperatureButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addPassengersButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(toggleLeftDoorsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toggleRightDoorsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(testPanelLayout.createSequentialGroup()
                        .addGroup(testPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(testPanelLayout.createSequentialGroup()
                                .addComponent(spinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(setPowerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(testPanelLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel5)))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addComponent(toggleLightsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        testPanelLayout.setVerticalGroup(
            testPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(testPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setPowerButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addTrainButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(testPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addPassengersSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPassengersButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(testPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(temperatureSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setTemperatureButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toggleLeftDoorsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toggleRightDoorsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toggleLightsButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel15.setText("Weight");

        weight.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        weight.setText("N/A");

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel16.setText("Width");

        width.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        width.setText("N/A");

        height.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        height.setText("N/A");

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel17.setText("Height");

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel18.setText("Length");

        length.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        length.setText("N/A");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passengers)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(temperature)
                                .addComponent(lights)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rightDoors)
                                    .addComponent(leftDoors))
                                .addComponent(speedLimit, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(feedbackSpeed)
                            .addComponent(acceleration)
                            .addComponent(numberOfCars)
                            .addComponent(maxPassengers))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(signalFailureButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(emergencyBrake, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(availableTrains, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(trainEngineFailureButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(serviceBrake, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(brakeFailureButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(weight)
                            .addComponent(width)
                            .addComponent(height)
                            .addComponent(length))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(testPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(leftDoors)
                    .addComponent(availableTrains, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rightDoors)
                    .addComponent(emergencyBrake))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lights)
                    .addComponent(serviceBrake))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(temperature)
                    .addComponent(trainEngineFailureButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signalFailureButton)
                    .addComponent(jLabel7)
                    .addComponent(speedLimit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brakeFailureButton)
                    .addComponent(jLabel10)
                    .addComponent(feedbackSpeed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(acceleration))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numberOfCars)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(passengers))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(maxPassengers))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(weight))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(width))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(height))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(length))
                        .addContainerGap())
                    .addComponent(testPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        availableTrains.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emergencyBrakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emergencyBrakeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emergencyBrakeActionPerformed

    private void serviceBrakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceBrakeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceBrakeActionPerformed

    private void trainEngineFailureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainEngineFailureButtonActionPerformed
        // TODO add your handling code here:
        this.setEngineFailure(!this.hasEngineFailure());
    }//GEN-LAST:event_trainEngineFailureButtonActionPerformed

    private void signalFailureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signalFailureButtonActionPerformed
        // TODO add your handling code here:
        this.setSignalFailure(!this.hasSignalFailure());
    }//GEN-LAST:event_signalFailureButtonActionPerformed

    private void brakeFailureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brakeFailureButtonActionPerformed
        // TODO add your handling code here:
        this.setBrakeFailure(!this.hasBrakeFailure());
    }//GEN-LAST:event_brakeFailureButtonActionPerformed

    private void setPowerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setPowerButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setPowerButtonActionPerformed

    private void addPassengersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPassengersButtonActionPerformed
        int value = (int) this.addPassengersSpinner.getValue();
        if(value < 0) {
            this.train.removePassengers(-value);
        } else {
            this.train.addPassengers(value);
        }
    }//GEN-LAST:event_addPassengersButtonActionPerformed

    private void addTrainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTrainButtonActionPerformed
        try {
            addTrain(new Train(3, trains.size()));
        } catch(Exception ex){};
    }//GEN-LAST:event_addTrainButtonActionPerformed

    private void setTemperatureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setTemperatureButtonActionPerformed
        this.train.setTemperature((int) this.temperatureSpinner.getValue());
    }//GEN-LAST:event_setTemperatureButtonActionPerformed

    private void toggleLightsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleLightsButtonActionPerformed
        this.train.setLights(!this.train.lightsAreOn());
    }//GEN-LAST:event_toggleLightsButtonActionPerformed

    private void toggleLeftDoorsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleLeftDoorsButtonActionPerformed
        this.train.setLeftDoors(!this.train.leftDoorsAreOpen());
    }//GEN-LAST:event_toggleLeftDoorsButtonActionPerformed

    private void toggleRightDoorsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleRightDoorsButtonActionPerformed
        this.train.setRightDoors(!this.train.rightDoorsAreOpen());
    }//GEN-LAST:event_toggleRightDoorsButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrainModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrainModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrainModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrainModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run(){
                try {
                    new TrainModel().setVisible(true);
                } catch(Exception e) {}
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acceleration;
    private javax.swing.JButton addPassengersButton;
    private javax.swing.JSpinner addPassengersSpinner;
    private javax.swing.JButton addTrainButton;
    private javax.swing.JComboBox<Train> availableTrains;
    private javax.swing.JButton brakeFailureButton;
    private javax.swing.JButton emergencyBrake;
    private javax.swing.JLabel feedbackSpeed;
    private javax.swing.JLabel height;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel leftDoors;
    private javax.swing.JLabel length;
    private javax.swing.JLabel lights;
    private javax.swing.JLabel maxPassengers;
    private javax.swing.JLabel numberOfCars;
    private javax.swing.JLabel passengers;
    private javax.swing.JLabel rightDoors;
    private javax.swing.JButton serviceBrake;
    private javax.swing.JToggleButton setPowerButton;
    private javax.swing.JButton setTemperatureButton;
    private javax.swing.JButton signalFailureButton;
    private javax.swing.JLabel speedLimit;
    private javax.swing.JSpinner spinner1;
    private javax.swing.JLabel temperature;
    private javax.swing.JSpinner temperatureSpinner;
    private javax.swing.JPanel testPanel;
    private javax.swing.JButton toggleLeftDoorsButton;
    private javax.swing.JButton toggleLightsButton;
    private javax.swing.JButton toggleRightDoorsButton;
    private javax.swing.JButton trainEngineFailureButton;
    private javax.swing.JLabel weight;
    private javax.swing.JLabel width;
    // End of variables declaration//GEN-END:variables
}
