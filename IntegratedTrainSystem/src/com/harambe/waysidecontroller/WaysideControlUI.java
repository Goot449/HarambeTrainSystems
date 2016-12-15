package com.harambe.waysidecontroller;

import com.harambe.trackmodel.*;
import java.io.File;
import java.util.TreeSet;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class WaysideControlUI extends javax.swing.JFrame {

    static WaysideControllerHandler handler;
    static boolean manual = false;
    DefaultTableModel blockTableModel;
    DefaultListModel ctcListModel;
    static boolean loggedIn = false;

    /**
     * Creates new form WaysideControlUI
     */
    public WaysideControlUI(WaysideControllerHandler handler) {
        this.handler = handler;
        initComponents();
        jList1.setModel(new DefaultListModel());
        blockTableModel = (DefaultTableModel) blockTable.getModel();
        ctcListModel = (DefaultListModel) jList1.getModel();
        this.lineSelectComboBoxActionPerformed(null);
        this.setVisible(true);
    }

    public void updateUI() {
        if (this.switchSelectComboBox.getSelectedItem() != null) {
            int blockNum = handler.findSwitch(switchSelectComboBox.getSelectedItem().toString()).getSwitch(switchSelectComboBox.getSelectedItem().toString()).getswitchedBlockBlock().getBlockNumber();
            this.switchPositionTextBox.setText("");
            this.switchPositionTextBox.setText(blockNum + "");

            for (String s : handler.messages) {
                ctcListModel.addElement(s);
            }

            WaysideController wc = handler.findSwitch(switchSelectComboBox.getSelectedItem().toString());

            blockTableModel.setRowCount(0);

            try {
                Switch s = wc.getSwitch(this.switchSelectComboBox.getSelectedItem().toString());
                if (s.getSwitchBlock().isBlockOccupied()) {
                    blockTableModel.addRow(new Object[]{s.getSwitchNumber() + " : " + s.getSwitchBlock().getBlockNumber(), "Yes"});
                } else {
                    blockTableModel.addRow(new Object[]{s.getSwitchNumber() + " : " + s.getSwitchBlock().getBlockNumber(), "No"});
                }
                if (s.getswitchedBlockBlock().isBlockOccupied()) {
                    blockTableModel.addRow(new Object[]{"Current Position : " + s.getswitchedBlockBlock().getBlockNumber(), "Yes"});
                } else {
                    blockTableModel.addRow(new Object[]{"Current Position : " + s.getswitchedBlockBlock().getBlockNumber(), "No"});
                }
                if (s.getunSwitchedBlockBlock().isBlockOccupied()) {
                    blockTableModel.addRow(new Object[]{"Other Position : " + s.getunSwitchedBlockBlock().getBlockNumber(), "Yes"});
                } else {
                    blockTableModel.addRow(new Object[]{"Other Position : " + s.getunSwitchedBlockBlock().getBlockNumber(), "No"});
                }
            } catch (Exception e) {

            }

            if (lineSelectComboBox.getSelectedItem().toString().equals("Red Line")) {
                railRoadCrossingTextBox.setText("");
                boolean crossingStatus = handler.findCorrectWayside(47, "red").getBlock(47).getCrossing().getCrossingState("red");
                if (crossingStatus) {
                    railRoadCrossingTextBox.setText("Closed");
                } else {
                    railRoadCrossingTextBox.setText("Open");
                }
            } else {
                railRoadCrossingTextBox.setText("");
                boolean crossingStatus = handler.findCorrectWayside(19, "green").getBlock(19).getCrossing().getCrossingState("green");
                if (crossingStatus) {
                    railRoadCrossingTextBox.setText("Closed");
                } else {
                    railRoadCrossingTextBox.setText("Open");
                }
            }
        }

//        for(Block b : wc.blocks.values()){
//            if(b.isBlockOccupied()){
//                blockTableModel.addRow(new Object[]{b.getBlockNumber(), "Yes"});    
//            }
//            else{
//                blockTableModel.addRow(new Object[]{b.getBlockNumber(), "No"});    
//            }
//            count++;
//        }
        //this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        switchSelectComboBox = new javax.swing.JComboBox();
        switchPositionTextBox = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        changeSwitchPositionButton = new javax.swing.JToggleButton();
        railRoadCrossingTextBox = new javax.swing.JTextField();
        lineSelectComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        loadPLCButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        blockTable = new javax.swing.JTable();
        jToggleButton2 = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        switchLightTextBox = new javax.swing.JTextField();
        jToggleButton3 = new javax.swing.JToggleButton();
        jLabel9 = new javax.swing.JLabel();
        controllerSelectComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Wayside Controller UI");

        switchSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Switch 0", "Switch 1", "Switch 2", "Switch 3", "Switch 4", "Switch 5", "Switch 6", "Switch 7", "Switch 8", "Switch 9", "Switch 10", "Switch 11", "Switch 12" }));
        switchSelectComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchSelectComboBoxActionPerformed(evt);
            }
        });

        switchPositionTextBox.setText("Open/Closed");
        switchPositionTextBox.setEnabled(false);
        switchPositionTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchPositionTextBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Switch Position");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Manual Mode");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Automatic Mode");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Railroad Crossing Status");

        changeSwitchPositionButton.setText("Change Switch Position (Manual Mode)");
        changeSwitchPositionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSwitchPositionButtonActionPerformed(evt);
            }
        });

        railRoadCrossingTextBox.setText("Red/Yellow/Green");
        railRoadCrossingTextBox.setEnabled(false);
        railRoadCrossingTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                railRoadCrossingTextBoxActionPerformed(evt);
            }
        });

        lineSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Red Line", "Green Line" }));
        lineSelectComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lineSelectComboBoxActionPerformed(evt);
            }
        });

        jLabel3.setText("Line Select:");

        jLabel7.setText("Switch Select:");

        loadPLCButton.setText("Load New PLC");
        loadPLCButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadPLCButtonActionPerformed(evt);
            }
        });

        blockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Block Number", "Occupancy"
            }
        ));
        jScrollPane5.setViewportView(blockTable);

        jToggleButton2.setText("Toggle Railroad Crossing (Manual Mode)");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Switch Light");

        switchLightTextBox.setEditable(false);
        switchLightTextBox.setText("On/Off");
        switchLightTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchLightTextBoxActionPerformed(evt);
            }
        });

        jToggleButton3.setText("Toggle Switch Light (Manual Mode)");
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        jLabel9.setText("Wayside Controller Select: ");

        controllerSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Controller 1", "Controller 2" }));
        controllerSelectComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controllerSelectComboBoxActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Master Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setText("username");

        jTextField2.setText("password");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(switchPositionTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(switchSelectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lineSelectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(loadPLCButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(changeSwitchPositionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jRadioButton1)
                                            .addComponent(jRadioButton2)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(controllerSelectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField2)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 6, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(switchLightTextBox, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(railRoadCrossingTextBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addGap(16, 16, 16)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lineSelectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(controllerSelectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(switchSelectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(switchPositionTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(changeSwitchPositionButton)
                                .addGap(7, 7, 7)
                                .addComponent(loadPLCButton))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(switchLightTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(railRoadCrossingTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton2)))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void switchSelectComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchSelectComboBoxActionPerformed
        // TODO add your handling code here:
        //System.out.println(switchSelectComboBox.getSelectedItem().toString());
        if (switchSelectComboBox.getSelectedItem() != null) {
            int blockNum = handler.findSwitch(switchSelectComboBox.getSelectedItem().toString()).getSwitch(switchSelectComboBox.getSelectedItem().toString()).getswitchedBlockBlock().getBlockNumber();
            switchPositionTextBox.setText("");
            switchPositionTextBox.setText(blockNum + "");
            switchLightTextBox.setText("");

            boolean light = handler.findSwitch(switchSelectComboBox.getSelectedItem().toString()).getSwitch(switchSelectComboBox.getSelectedItem().toString()).getLight();

            if (light) {
                switchLightTextBox.setText("On");
            } else {
                switchLightTextBox.setText("Off");
            }
        }
    }//GEN-LAST:event_switchSelectComboBoxActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        if (loggedIn == true) {
            manual = true;
        }
        else {
            jRadioButton2ActionPerformed(evt);
            jRadioButton2.setSelected(true);
        }

    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void changeSwitchPositionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSwitchPositionButtonActionPerformed
        // TODO add your handling code here:
        if (switchSelectComboBox.getSelectedItem() != null && manual) {
            handler.manualSwitch(switchSelectComboBox.getSelectedItem().toString());
            int blockNum = handler.findSwitch(switchSelectComboBox.getSelectedItem().toString()).getSwitch(switchSelectComboBox.getSelectedItem().toString()).getswitchedBlockBlock().getBlockNumber();
            switchPositionTextBox.setText("");
            switchPositionTextBox.setText(blockNum + "");
        }
    }//GEN-LAST:event_changeSwitchPositionButtonActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        manual = false;
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void switchLightTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchLightTextBoxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_switchLightTextBoxActionPerformed

    private void controllerSelectComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_controllerSelectComboBoxActionPerformed
        // TODO add your handling code here:
        lineSelectComboBoxActionPerformed(evt);
    }//GEN-LAST:event_controllerSelectComboBoxActionPerformed

    private void switchPositionTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchPositionTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_switchPositionTextBoxActionPerformed

    private void lineSelectComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lineSelectComboBoxActionPerformed
        // TODO add your handling code here:
        switchSelectComboBox.removeAllItems();
        if (lineSelectComboBox.getSelectedItem().toString().equals("Red Line")) {
            if (controllerSelectComboBox.getSelectedItem().toString().equals("Controller 1")) {
                for (String s : new TreeSet<String>(handler.controllers.get(0).switches.keySet())) {
                    switchSelectComboBox.addItem(s);
                }
            } else {
                for (String s : new TreeSet<String>(handler.controllers.get(2).switches.keySet())) {
                    switchSelectComboBox.addItem(s);
                }
            }
        } else if (controllerSelectComboBox.getSelectedItem().toString().equals("Controller 1")) {
            for (String s : new TreeSet<String>(handler.controllers.get(1).switches.keySet())) {
                switchSelectComboBox.addItem(s);
            }
        } else {
            for (String s : new TreeSet<String>(handler.controllers.get(3).switches.keySet())) {
                switchSelectComboBox.addItem(s);
            }
        }
    }//GEN-LAST:event_lineSelectComboBoxActionPerformed

    private void loadPLCButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadPLCButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File(""));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PLC", "class");
        jFileChooser.setFileFilter(filter);
        int result = jFileChooser.showOpenDialog(new JFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            try {

                String pathToFile = selectedFile.getAbsolutePath().substring(0, selectedFile.getAbsolutePath().lastIndexOf("\\"));
                System.out.println("Path to file: " + pathToFile);
                String fileName = selectedFile.getAbsolutePath().substring(selectedFile.getAbsolutePath().lastIndexOf("\\") + 1);
                System.out.println("File name: " + fileName);

                if (!fileName.equals("DefaultPLC.class") && !fileName.equals("AlternativePLC.class") && !fileName.equals("testPLC.class")) {
                    JOptionPane.showMessageDialog(null, "Error: requested file is not a PLC");
                } else {
                    handler.findSwitch(switchSelectComboBox.getSelectedItem().toString()).setPLC(fileName);
                }

                /*URLClassLoader classLoader = new URLClassLoader(new URL[] {new File("").toURI().toURL()});
                Class<?> loadClass = classLoader.loadClass(fileName);
                PLC newPLC = (PLC) loadClass.newInstance();
                System.out.println("Good");*/
 /*String fileName = selectedFile.getAbsolutePath().substring(selectedFile.getAbsolutePath().lastIndexOf("\\") + 1);
               
               InputStream fileInputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
               byte rawBytes[] = new byte[fileInputStream.available()];
               fileInputStream.read(rawBytes);
               
               Class<?> regeneratedClass = */
            } catch (Exception e) {
                System.out.println("Bad");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_loadPLCButtonActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        // TODO add your handling code here:
        if (switchSelectComboBox.getSelectedItem() != null && manual == true) {
            WaysideController wc = handler.findSwitch(switchSelectComboBox.getSelectedItem().toString());
            wc.getSwitch((switchSelectComboBox.getSelectedItem().toString())).toggleLight();
            boolean light = handler.findSwitch(switchSelectComboBox.getSelectedItem().toString()).getSwitch(switchSelectComboBox.getSelectedItem().toString()).getLight();

            if (light) {
                switchLightTextBox.setText("On");
            } else {
                switchLightTextBox.setText("Off");
            }
        }
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
        if (switchSelectComboBox.getSelectedItem() != null && manual == true) {

        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void railRoadCrossingTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_railRoadCrossingTextBoxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_railRoadCrossingTextBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        loggedIn = handler.login(jTextField1.getText(), jTextField2.getText());
        if(!loggedIn){
            JOptionPane.showMessageDialog(null, "Error: wrong login credentials");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void init(WaysideControllerHandler handler) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

 /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WaysideControlUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WaysideControlUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WaysideControlUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WaysideControlUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        this.handler = handler;
        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new WaysideControlUI().setVisible(true);
//            }
//        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable blockTable;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JToggleButton changeSwitchPositionButton;
    private javax.swing.JComboBox<String> controllerSelectComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JComboBox<String> lineSelectComboBox;
    private javax.swing.JButton loadPLCButton;
    private javax.swing.JTextField railRoadCrossingTextBox;
    private javax.swing.JTextField switchLightTextBox;
    private javax.swing.JTextField switchPositionTextBox;
    private javax.swing.JComboBox switchSelectComboBox;
    // End of variables declaration//GEN-END:variables
}
