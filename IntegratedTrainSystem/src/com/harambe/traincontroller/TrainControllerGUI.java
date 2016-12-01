package com.harambe.traincontroller;

import java.awt.Color;
import java.util.ArrayList;
import com.harambe.trainmodel.Train;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;

public class TrainControllerGUI extends javax.swing.JFrame {

    public ArrayList<Train> trainList = new ArrayList<Train>();
    public ArrayList<TrainState> trainStateList = new ArrayList<TrainState>();
    public int selectedTrain;
    private double DT = .001;
    

    @Override
    public void setTitle(String title) {
        super.setTitle(title); //To change body of generated methods, choose Tools | Templates.
    }
    
    public TrainControllerGUI(ArrayList trainList, ArrayList trainStateList) {
        this.trainList = trainList;
        this.trainStateList = trainStateList;
        this.setTitle("Train Controller");
        initComponents(trainList);
        Timer timer = new Timer((int) (1000 * DT), e -> {
            refresh();
        });
        timer.setRepeats(true);
        timer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents(ArrayList trainList) {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        modeToggleButton = new javax.swing.JToggleButton();
        leftDoorOpenButton = new javax.swing.JRadioButton();
        leftDoorClosedButton = new javax.swing.JRadioButton();
        leftDoorLabel = new javax.swing.JLabel();
        rightDoorLabel = new javax.swing.JLabel();
        rightDoorOpenButton = new javax.swing.JRadioButton();
        rightDoorClosedButton = new javax.swing.JRadioButton();
        interiorLightsLabel = new javax.swing.JLabel();
        interiorLightsOnButton = new javax.swing.JRadioButton();
        interiorLightsOffButton = new javax.swing.JRadioButton();
        simSpeedLabel = new javax.swing.JLabel();
        trainSelectorBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        notificationArea = new javax.swing.JTextArea();
        notificationLabel = new javax.swing.JLabel();
        setSpeedSlider = new javax.swing.JSlider();
        interiorTempSpinner = new javax.swing.JSpinner();
        interiorTempLabel = new javax.swing.JLabel();
        powerLabel = new javax.swing.JLabel();
        powerValueLabel = new javax.swing.JLabel();
        powerUnitsLabel = new javax.swing.JLabel();
        setSpeedLabel = new javax.swing.JLabel();
        setSpeedValueLabel = new javax.swing.JLabel();
        setSpeedUnitsLabel = new javax.swing.JLabel();
        engineStatusLabel = new javax.swing.JLabel();
        signalStatusLabel = new javax.swing.JLabel();
        brakeStatusLabel = new javax.swing.JLabel();
        serviceBrakeToggleButton = new javax.swing.JToggleButton();
        speedLimitLabel = new javax.swing.JLabel();
        speedLimitValueLabel = new javax.swing.JLabel();
        speedLimitUnitsLabel = new javax.swing.JLabel();
        currentSpeedLabel = new javax.swing.JLabel();
        currentSpeedValueLabel = new javax.swing.JLabel();
        currentSpeedUnitsLabel = new javax.swing.JLabel();
        emergencyBrakeToggleButton = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        leftDoorFailToggle = new javax.swing.JToggleButton();
        rightDoorFailToggle = new javax.swing.JToggleButton();
        interiorLightsFailToggle = new javax.swing.JToggleButton();
        hvacFailToggle = new javax.swing.JToggleButton();
        engineFailToggle = new javax.swing.JToggleButton();
        signalFailToggle = new javax.swing.JToggleButton();
        brakeFailToggle = new javax.swing.JToggleButton();
        testCurrentSpeedSlider = new javax.swing.JSlider();
        testCurrentSpeedValueLabel = new javax.swing.JLabel();
        testCurrentSpeedLabel = new javax.swing.JLabel();
        testCurrentSpeedUnitsLabel = new javax.swing.JLabel();
        testSpeedLimitSlider = new javax.swing.JSlider();
        testSpeedLimitLabel = new javax.swing.JLabel();
        testSpeedLimitValueLabel = new javax.swing.JLabel();
        testSpeedLimitUnitsLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        modeToggleButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        modeToggleButton.setText("Automatic Mode");
        modeToggleButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                modeToggleStateChanged(evt);
            }
        });
        

        buttonGroup1.add(leftDoorOpenButton);
        leftDoorOpenButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        leftDoorOpenButton.setText("Open");

        buttonGroup1.add(leftDoorClosedButton);
        leftDoorClosedButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        leftDoorClosedButton.setSelected(true);
        leftDoorClosedButton.setText("Closed");

        leftDoorLabel.setBackground(new java.awt.Color(153, 255, 153));
        leftDoorLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        leftDoorLabel.setText("Left Doors");
        leftDoorLabel.setOpaque(true);

        rightDoorLabel.setBackground(new java.awt.Color(153, 255, 153));
        rightDoorLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rightDoorLabel.setText("Right Doors");
        rightDoorLabel.setOpaque(true);

        buttonGroup2.add(rightDoorOpenButton);
        rightDoorOpenButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rightDoorOpenButton.setText("Open");

        buttonGroup2.add(rightDoorClosedButton);
        rightDoorClosedButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rightDoorClosedButton.setSelected(true);
        rightDoorClosedButton.setText("Closed");

        interiorLightsLabel.setBackground(new java.awt.Color(153, 255, 153));
        interiorLightsLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        interiorLightsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        interiorLightsLabel.setText("Interior Lights");
        interiorLightsLabel.setOpaque(true);

        buttonGroup3.add(interiorLightsOnButton);
        interiorLightsOnButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        interiorLightsOnButton.setText("On");

        buttonGroup3.add(interiorLightsOffButton);
        interiorLightsOffButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        interiorLightsOffButton.setSelected(true);
        interiorLightsOffButton.setText("Off");

        simSpeedLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        simSpeedLabel.setText("1x");

        
        //*******************Change Code HERE*****************************//
        trainSelectorBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        String[] trainIDs = new String[trainList.size()];
        for (int i = 0; i<trainList.size(); i++){
            Train thisTrain = (Train) trainList.get(i);
            trainIDs[i] = "Train " + Integer.toString(thisTrain.getId());
        }
        trainSelectorBox.setModel(new javax.swing.DefaultComboBoxModel(trainIDs));
        trainSelectorBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainSelectorBoxActionPerformed(evt);
            }
        });
        selectedTrain = 0;
        //****************************************************************//

        notificationArea.setColumns(20);
        notificationArea.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        notificationArea.setLineWrap(true);
        notificationArea.setRows(5);
        notificationArea.setText("Notifications Here");
        notificationArea.setEditable(false);
        notificationArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(notificationArea);

        notificationLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        notificationLabel.setText("Notifications");

        setSpeedSlider.setMaximum(43);
        setSpeedSlider.setOrientation(javax.swing.JSlider.VERTICAL);
        setSpeedSlider.setPaintLabels(true);
        setSpeedSlider.setMajorTickSpacing(10);
        setSpeedSlider.setMinorTickSpacing(2);
        setSpeedSlider.setPaintTicks(true);
        setSpeedSlider.setValue(0);
        setSpeedSlider.setPreferredSize(new java.awt.Dimension(31, 140));
        setSpeedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                setSpeedSliderStateChanged(evt);
            }
        });

        interiorTempSpinner.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        interiorTempSpinner.setModel(new javax.swing.SpinnerNumberModel(70, 60, 80, 1));

        interiorTempLabel.setBackground(new java.awt.Color(153, 255, 153));
        interiorTempLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        interiorTempLabel.setText("Interior Temperature");
        interiorTempLabel.setOpaque(true);

        powerLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        powerLabel.setText("Power");

        powerValueLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        powerValueLabel.setText("120");

        powerUnitsLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        powerUnitsLabel.setText("kWh");

        setSpeedLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        setSpeedLabel.setText("Set Speed");

        setSpeedValueLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        setSpeedValueLabel.setText("0");

        setSpeedUnitsLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        setSpeedUnitsLabel.setText("mph");

        engineStatusLabel.setBackground(new java.awt.Color(153, 255, 153));
        engineStatusLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        engineStatusLabel.setText("Engine Status");
        engineStatusLabel.setOpaque(true);

        signalStatusLabel.setBackground(new java.awt.Color(153, 255, 153));
        signalStatusLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        signalStatusLabel.setText("Signal Status");
        signalStatusLabel.setOpaque(true);

        brakeStatusLabel.setBackground(new java.awt.Color(153, 255, 153));
        brakeStatusLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        brakeStatusLabel.setText("Brake Status");
        brakeStatusLabel.setOpaque(true);

        serviceBrakeToggleButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        serviceBrakeToggleButton.setText("Service Brake");
        serviceBrakeToggleButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                serviceBrakeToggleStateChanged(evt);
            }
        });

        speedLimitLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        speedLimitLabel.setText("Speed Limit");

        speedLimitValueLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        speedLimitValueLabel.setText("25");

        speedLimitUnitsLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        speedLimitUnitsLabel.setText("mph");

        currentSpeedLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        currentSpeedLabel.setText("Current Speed");

        currentSpeedValueLabel.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        currentSpeedValueLabel.setText("0");

        currentSpeedUnitsLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        currentSpeedUnitsLabel.setText("mph");

        emergencyBrakeToggleButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        emergencyBrakeToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/red-stop-icon-37737.png"))); // NOI18N
        emergencyBrakeToggleButton.setText("Emergency Brake");
        emergencyBrakeToggleButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                emergencyBrakeToggleStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(modeToggleButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simSpeedLabel))
                    .addComponent(leftDoorLabel))
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(trainSelectorBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(engineStatusLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(notificationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(brakeStatusLabel)
                            .addComponent(signalStatusLabel))))
                .addGap(66, 66, 66))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rightDoorLabel)
                    .addComponent(interiorLightsLabel)
                    .addComponent(interiorTempLabel)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rightDoorOpenButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rightDoorClosedButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(leftDoorOpenButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(leftDoorClosedButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(interiorLightsOnButton)
                                .addGap(18, 18, 18)
                                .addComponent(interiorLightsOffButton))
                            .addComponent(interiorTempSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(powerValueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(powerUnitsLabel)))))
                .addGap(60, 60, 60)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(speedLimitValueLabel)
                                .addGap(2, 2, 2)
                                .addComponent(speedLimitUnitsLabel))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(currentSpeedValueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(currentSpeedUnitsLabel)))
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(serviceBrakeToggleButton)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(currentSpeedLabel)
                            .addComponent(speedLimitLabel))
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(emergencyBrakeToggleButton)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(powerLabel)
                .addGap(188, 188, 188)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(setSpeedValueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setSpeedUnitsLabel))
                    .addComponent(setSpeedLabel))
                .addGap(18, 18, 18)
                .addComponent(setSpeedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modeToggleButton)
                    .addComponent(simSpeedLabel)
                    .addComponent(trainSelectorBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(engineStatusLabel))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(notificationLabel))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(leftDoorLabel)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(signalStatusLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(leftDoorOpenButton)
                            .addComponent(leftDoorClosedButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rightDoorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rightDoorOpenButton)
                            .addComponent(rightDoorClosedButton))
                        .addGap(7, 7, 7)
                        .addComponent(interiorLightsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(interiorLightsOnButton)
                            .addComponent(interiorLightsOffButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(interiorTempLabel)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(interiorTempSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(powerLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(powerValueLabel)
                                    .addComponent(powerUnitsLabel)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(setSpeedLabel)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(setSpeedValueLabel)
                                    .addComponent(setSpeedUnitsLabel)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(setSpeedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(currentSpeedLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(currentSpeedValueLabel)
                                    .addComponent(currentSpeedUnitsLabel)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(brakeStatusLabel)
                        .addGap(35, 35, 35)
                        .addComponent(serviceBrakeToggleButton)
                        .addGap(18, 18, 18)
                        .addComponent(emergencyBrakeToggleButton)
                        .addGap(41, 41, 41)
                        .addComponent(speedLimitLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(speedLimitValueLabel)
                            .addComponent(speedLimitUnitsLabel))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("User", jPanel1);

        leftDoorFailToggle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        leftDoorFailToggle.setText("Left Door Fail");
        leftDoorFailToggle.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                leftDoorFailToggleStateChanged(evt);
            }
        });
        leftDoorFailToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftDoorFailToggleActionPerformed(evt);
            }
        });

        rightDoorFailToggle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rightDoorFailToggle.setText("Right Door Fail");
        rightDoorFailToggle.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rightDoorFailToggleStateChanged(evt);
            }
        });
        rightDoorFailToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightDoorFailToggleActionPerformed(evt);
            }
        });

        interiorLightsFailToggle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        interiorLightsFailToggle.setText("Interior Lights Fail");
        interiorLightsFailToggle.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                interiorLightsFailToggleStateChanged(evt);
            }
        });

        hvacFailToggle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        hvacFailToggle.setText("HVAC Fail");
        hvacFailToggle.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                hvacFailToggleStateChanged(evt);
            }
        });
        hvacFailToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hvacFailToggleActionPerformed(evt);
            }
        });

        engineFailToggle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        engineFailToggle.setText("Engine Fail");
        engineFailToggle.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                engineFailToggleStateChanged(evt);
            }
        });

        signalFailToggle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        signalFailToggle.setText("Signal Fail");
        signalFailToggle.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                signalFailToggleStateChanged(evt);
            }
        });

        brakeFailToggle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        brakeFailToggle.setText("Brake Fail");
        brakeFailToggle.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                brakeFailToggleStateChanged(evt);
            }
        });

        testCurrentSpeedSlider.setMaximum(43);
        testCurrentSpeedSlider.setPaintLabels(true);
        testCurrentSpeedSlider.setMajorTickSpacing(10);
        testCurrentSpeedSlider.setMinorTickSpacing(2);
        testCurrentSpeedSlider.setPaintTicks(true);
        testCurrentSpeedSlider.setValue(0);
        testCurrentSpeedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                testCurrentSpeedSliderStateChanged(evt);
            }
        });

        testCurrentSpeedValueLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        testCurrentSpeedValueLabel.setText("0");

        testCurrentSpeedLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        testCurrentSpeedLabel.setText("Current Speed");

        testCurrentSpeedUnitsLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        testCurrentSpeedUnitsLabel.setText("mph");

        testSpeedLimitSlider.setMaximum(43);
        testSpeedLimitSlider.setPaintLabels(true);
        testSpeedLimitSlider.setPaintTicks(true);
        testSpeedLimitSlider.setValue(25);
        testSpeedLimitSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                testSpeedLimitSliderStateChanged(evt);
            }
        });

        testSpeedLimitLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        testSpeedLimitLabel.setText("Speed Limit");

        testSpeedLimitValueLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        testSpeedLimitValueLabel.setText("25");

        testSpeedLimitUnitsLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        testSpeedLimitUnitsLabel.setText("mph");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(signalFailToggle)
                            .addComponent(brakeFailToggle)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(leftDoorFailToggle)
                                    .addComponent(rightDoorFailToggle)
                                    .addComponent(interiorLightsFailToggle))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(testCurrentSpeedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(testCurrentSpeedLabel))
                                    .addComponent(testSpeedLimitSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(testCurrentSpeedValueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(testCurrentSpeedUnitsLabel)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hvacFailToggle)
                            .addComponent(engineFailToggle))
                        .addGap(161, 161, 161)
                        .addComponent(testSpeedLimitLabel)
                        .addGap(83, 83, 83)
                        .addComponent(testSpeedLimitValueLabel)
                        .addGap(18, 18, 18)
                        .addComponent(testSpeedLimitUnitsLabel)
                        .addGap(217, 217, 217))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(leftDoorFailToggle)
                        .addGap(18, 18, 18)
                        .addComponent(rightDoorFailToggle))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(testCurrentSpeedValueLabel)
                            .addComponent(testCurrentSpeedUnitsLabel)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(testCurrentSpeedSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(testCurrentSpeedLabel)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(interiorLightsFailToggle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hvacFailToggle)
                        .addGap(18, 18, 18)
                        .addComponent(engineFailToggle)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(testSpeedLimitSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(testSpeedLimitLabel)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(testSpeedLimitValueLabel)
                                .addComponent(testSpeedLimitUnitsLabel)))
                        .addGap(22, 22, 22)))
                .addComponent(signalFailToggle)
                .addGap(18, 18, 18)
                .addComponent(brakeFailToggle)
                .addGap(98, 98, 98))
        );

        jTabbedPane1.addTab("Test", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>    
    
    void refresh() {
        double currentPower = trainStateList.get(selectedTrain).getPower();
        double currentSpeed = trainList.get(selectedTrain).getFeedbackVelocity();
        currentSpeedValueLabel.setText(Integer.toString((int)Math.round(currentSpeed)));
        double speedLimit = trainList.get(selectedTrain).getSpeedLimit();
        if (trainStateList.get(selectedTrain).isManualModeEnabled()){
            modeToggleButton.setSelected(false);
            setSpeedSlider.setEnabled(true);
            serviceBrakeToggleButton.setEnabled(true);
        }
        else{
            modeToggleButton.setSelected(true);
            setSpeedSlider.setEnabled(true);
            setSpeedSlider.setValue((int)speedLimit);
            setSpeedSlider.setEnabled(false);
            toggleServiceBrakes(false);
            serviceBrakeToggleButton.setEnabled(false);
        }
        this.changeTestSpeedLimitLabel((int)speedLimit);
        setPowerOut((int)currentPower);
        //if we decide to dynamically create trains, this will have to change
        if (trainSelectorBox.getItemCount()!=trainList.size()){
            trainSelectorBox.removeAllItems();
            String[] trainIDs = new String[trainList.size()];
            for (int i = 0; i<trainList.size(); i++){
                Train thisTrain = (Train) trainList.get(i);
                trainIDs[i] = "Train " + Integer.toString(thisTrain.getId());
            }
            trainSelectorBox.setModel(new javax.swing.DefaultComboBoxModel(trainIDs));
        }
        toggleServiceBrakes(trainList.get(selectedTrain).getServiceBreakStatus());
    }
    

    private void setSpeedSliderStateChanged(javax.swing.event.ChangeEvent evt) {                                            
        int speedLimit = Integer.parseInt(speedLimitValueLabel.getText());
        int setSpeed = setSpeedSlider.getValue();
        if (setSpeed>speedLimit){
            setSpeed = speedLimit;
            setSpeedSlider.setValue(setSpeed);
        }
        changeSetSpeedLabel(setSpeed);
        setTrainStateSetPoint(setSpeed);
    }                                           

    private void testSpeedLimitSliderStateChanged(javax.swing.event.ChangeEvent evt) {                                                  
        changeTestSpeedLimitLabel(testSpeedLimitSlider.getValue());
        setTrainStateTestSpeedLimit(testSpeedLimitSlider.getValue());
    }                                                 

    private void testCurrentSpeedSliderStateChanged(javax.swing.event.ChangeEvent evt) {                                                    
        changeTestCurrentSpeedLabel(testCurrentSpeedSlider.getValue());
        setTrainStateTestSpeed(testCurrentSpeedSlider.getValue());
    }                                                   

    private void brakeFailToggleStateChanged(javax.swing.event.ChangeEvent evt) {                                             
        changeBrakeFailState(brakeFailToggle.isSelected());
        setBrakeStatus(brakeFailToggle.isSelected());
    }                                            

    private void signalFailToggleStateChanged(javax.swing.event.ChangeEvent evt) {                                              
        changeSignalFailState(signalFailToggle.isSelected());
    }                                             

    private void engineFailToggleStateChanged(javax.swing.event.ChangeEvent evt) {                                              
        // TODO add your handling code here:
        changeEngineFailState(engineFailToggle.isSelected());
    }  
    
    private void trainSelectorBoxActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        changeTrainInGUI(trainSelectorBox.getSelectedIndex());
    }  
    
    private void modeToggleStateChanged(ChangeEvent evt) {
        toggleMode(modeToggleButton.isSelected());
    }
    
    private void emergencyBrakeToggleStateChanged(ChangeEvent evt) {
        toggleEmergencyBrakes(emergencyBrakeToggleButton.isSelected());
    }
    
    private void serviceBrakeToggleStateChanged(ChangeEvent evt) {
        trainStateList.get(selectedTrain).setGuiSetServiceBrake(serviceBrakeToggleButton.isSelected());
        toggleServiceBrakes(serviceBrakeToggleButton.isSelected());
    }

    private void hvacFailToggleActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void hvacFailToggleStateChanged(javax.swing.event.ChangeEvent evt) {                                            
        // TODO add your handling code here:
        changeHVACFailState(hvacFailToggle.isSelected());
    }                                           

    private void interiorLightsFailToggleStateChanged(javax.swing.event.ChangeEvent evt) {                                                      
        // TODO add your handling code here:
        changeInteriorLightsFailState(interiorLightsFailToggle.isSelected());
    }                                                     

    private void rightDoorFailToggleActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
    }                                                   

    private void rightDoorFailToggleStateChanged(javax.swing.event.ChangeEvent evt) {                                                 
        // TODO add your handling code here:
        changeRightDoorFailState(rightDoorFailToggle.isSelected());
    }                                                

    private void leftDoorFailToggleActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }                                                  

    private void leftDoorFailToggleStateChanged(javax.swing.event.ChangeEvent evt) {                                                
        // TODO add your handling code here:
        //System.out.println("This worked");
        changeLeftDoorFailState(leftDoorFailToggle.isSelected());
    }                                               
    
    public int getCurrentSpeed(){
        int currentSpeed = Integer.parseInt(currentSpeedValueLabel.getText());
        return currentSpeed;
    }
    
    public int getSetSpeed(){
        int setSpeed = Integer.parseInt(setSpeedValueLabel.getText());
        return setSpeed;
    }
    
    void setPowerOut(int powerOut){
        powerValueLabel.setText(Integer.toString(powerOut/1000));
    }
    
    void changeSetSpeedLabel(int setSpeed){
        setSpeedValueLabel.setText(Integer.toString(setSpeed));
    }
    
    void toggleMode(boolean enabled){
        trainStateList.get(selectedTrain).setManualModeEnabled(!enabled);
    }
    
    void setTrainStateSetPoint(int setSpeed) {
        trainStateList.get(selectedTrain).setSetPoint((double)setSpeed);
    }
    
    void changeTestCurrentSpeedLabel(int currentSpeed){
        testCurrentSpeedValueLabel.setText(Integer.toString(currentSpeed));
        currentSpeedValueLabel.setText(Integer.toString(currentSpeed));
    }
    
    void setTrainStateTestSpeed(int currentSpeed){
        trainStateList.get(selectedTrain).setTestSpeed((double)currentSpeed);
    }
    
    void changeTestSpeedLimitLabel(int speedLimit){
        testSpeedLimitValueLabel.setText(Integer.toString(speedLimit));
        speedLimitValueLabel.setText(Integer.toString(speedLimit));
        int setSpeed = Integer.parseInt(setSpeedValueLabel.getText());
        if(setSpeed>speedLimit){
            setSpeed = speedLimit;
            setSpeedSlider.setValue(setSpeed);
        }
        if (testSpeedLimitSlider.getValue() != speedLimit){
            testSpeedLimitSlider.setValue(speedLimit);
        }
    }
    
    void setTrainStateTestSpeedLimit(int speedLimit){
        trainStateList.get(selectedTrain).setTestSpeedLimit((double)speedLimit);
    }
    
    //Want to reload train state here
    void changeTrainInGUI(int selectedTrain){
        this.selectedTrain = selectedTrain;
        changeSetSpeedLabel((int)trainStateList.get(selectedTrain).getSetPoint());
        setSpeedSlider.setValue((int)trainStateList.get(selectedTrain).getSetPoint());
    }
    
    void changeLeftDoorFailState(Boolean enabled){
        if(enabled){
            leftDoorLabel.setBackground(Color.red);
            //notificationArea.insert("Left Door Failure!\n", 0);
        }
        else{
            Color labelColor = new Color(153, 255, 153);
            leftDoorLabel.setBackground(labelColor);
        }
    }
    
    void changeRightDoorFailState(Boolean enabled){
        if(enabled){
            rightDoorLabel.setBackground(Color.red);
        }
        else{
            Color labelColor = new Color(153, 255, 153);
            rightDoorLabel.setBackground(labelColor);
        }
    }
    
    void changeInteriorLightsFailState(Boolean enabled){
        if(enabled){
            interiorLightsLabel.setBackground(Color.red);
        }
        else{
            Color labelColor = new Color(153, 255, 153);
            interiorLightsLabel.setBackground(labelColor);
        }
    }
    
    void changeHVACFailState(Boolean enabled){
        if(enabled){
            interiorTempLabel.setBackground(Color.red);
        }
        else{
            Color labelColor = new Color(153, 255, 153);
            interiorTempLabel.setBackground(labelColor);
        }
    }
    
    void changeEngineFailState(Boolean enabled){
        if(enabled){
            engineStatusLabel.setBackground(Color.red);
        }
        else{
            Color labelColor = new Color(153, 255, 153);
            engineStatusLabel.setBackground(labelColor);
        }
    }
    
    void changeSignalFailState(Boolean enabled){
        if(enabled){
            signalStatusLabel.setBackground(Color.red);
        }
        else{
            Color labelColor = new Color(153, 255, 153);
            signalStatusLabel.setBackground(labelColor);
        }
    }
    
    void changeBrakeFailState(Boolean enabled){
        if(enabled){
            brakeStatusLabel.setBackground(Color.red);
        }
        else{
            Color labelColor = new Color(153, 255, 153);
            brakeStatusLabel.setBackground(labelColor);
        }
    }
    
    //make sure to change everywhere
    void toggleEmergencyBrakes(Boolean enabled){
        trainList.get(selectedTrain).engageEmergencyBrakes(enabled);
    }
    
    void toggleServiceBrakes(Boolean enabled){
        trainList.get(selectedTrain).engageServiceBrakes(enabled);
        serviceBrakeToggleButton.setSelected(enabled);
    }
    
    void setBrakeStatus(Boolean enabled){
        trainStateList.get(selectedTrain).setBrakeStatus(!enabled);
    }

    // Variables declaration - do not modify                     
    private javax.swing.JToggleButton brakeFailToggle;
    private javax.swing.JLabel brakeStatusLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel currentSpeedLabel;
    private javax.swing.JLabel currentSpeedUnitsLabel;
    private javax.swing.JLabel currentSpeedValueLabel;
    private javax.swing.JToggleButton emergencyBrakeToggleButton;
    private javax.swing.JToggleButton engineFailToggle;
    private javax.swing.JLabel engineStatusLabel;
    private javax.swing.JToggleButton hvacFailToggle;
    private javax.swing.JToggleButton interiorLightsFailToggle;
    private javax.swing.JLabel interiorLightsLabel;
    private javax.swing.JRadioButton interiorLightsOffButton;
    private javax.swing.JRadioButton interiorLightsOnButton;
    private javax.swing.JLabel interiorTempLabel;
    private javax.swing.JSpinner interiorTempSpinner;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton leftDoorClosedButton;
    private javax.swing.JToggleButton leftDoorFailToggle;
    private javax.swing.JLabel leftDoorLabel;
    private javax.swing.JRadioButton leftDoorOpenButton;
    private javax.swing.JToggleButton modeToggleButton;
    private javax.swing.JTextArea notificationArea;
    private javax.swing.JLabel notificationLabel;
    private javax.swing.JLabel powerLabel;
    private javax.swing.JLabel powerUnitsLabel;
    private javax.swing.JLabel powerValueLabel;
    private javax.swing.JRadioButton rightDoorClosedButton;
    private javax.swing.JToggleButton rightDoorFailToggle;
    private javax.swing.JLabel rightDoorLabel;
    private javax.swing.JRadioButton rightDoorOpenButton;
    private javax.swing.JToggleButton serviceBrakeToggleButton;
    private javax.swing.JLabel setSpeedLabel;
    private javax.swing.JSlider setSpeedSlider;
    private javax.swing.JLabel setSpeedUnitsLabel;
    private javax.swing.JLabel setSpeedValueLabel;
    private javax.swing.JToggleButton signalFailToggle;
    private javax.swing.JLabel signalStatusLabel;
    private javax.swing.JLabel simSpeedLabel;
    private javax.swing.JLabel speedLimitLabel;
    private javax.swing.JLabel speedLimitUnitsLabel;
    private javax.swing.JLabel speedLimitValueLabel;
    private javax.swing.JLabel testCurrentSpeedLabel;
    private javax.swing.JSlider testCurrentSpeedSlider;
    private javax.swing.JLabel testCurrentSpeedUnitsLabel;
    private javax.swing.JLabel testCurrentSpeedValueLabel;
    private javax.swing.JLabel testSpeedLimitLabel;
    private javax.swing.JSlider testSpeedLimitSlider;
    private javax.swing.JLabel testSpeedLimitUnitsLabel;
    private javax.swing.JLabel testSpeedLimitValueLabel;
    private javax.swing.JComboBox trainSelectorBox;
    // End of variables declaration                   
}
