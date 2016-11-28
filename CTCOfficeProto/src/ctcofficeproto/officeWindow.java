/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctcofficeproto;

import My.TrackModel.Track;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jake
 */
public class officeWindow extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public officeWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        manualButtonGroup = new javax.swing.ButtonGroup();
        DispatchLineButtonGroup = new javax.swing.ButtonGroup();
        officeTabbedPane = new javax.swing.JTabbedPane();
        officejPanel = new javax.swing.JPanel();
        controlsPanel = new javax.swing.JPanel();
        manualModeLabel = new javax.swing.JLabel();
        manualOnButton = new javax.swing.JToggleButton();
        manualOffButton = new javax.swing.JToggleButton();
        loadTrackButton = new javax.swing.JButton();
        clockSlider = new javax.swing.JSlider();
        clockLabel = new javax.swing.JLabel();
        fileInput = new javax.swing.JTextField();
        trackMapImageLabel = new javax.swing.JLabel();
        stationPanel = new javax.swing.JPanel();
        stationScrollPane1 = new javax.swing.JScrollPane();
        stationTable = new javax.swing.JTable();
        trainPanel = new javax.swing.JPanel();
        trainScrollPane2 = new javax.swing.JScrollPane();
        trainTable = new javax.swing.JTable();
        blockManagerPanel = new javax.swing.JPanel();
        blockScrollPane5 = new javax.swing.JScrollPane();
        blockTable = new javax.swing.JTable();
        dispatchTrainPanel = new javax.swing.JPanel();
        speedLimitLabel = new javax.swing.JLabel();
        dispatchTrainButton = new javax.swing.JButton();
        dispatchBlockLabel = new javax.swing.JLabel();
        dispatchBlockInput = new javax.swing.JTextField();
        speedLimitInput = new javax.swing.JTextField();
        dispatchGreenLine = new javax.swing.JRadioButton();
        dispatchRedLine = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        officejPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "System Controls", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        officejPanel.setToolTipText("Changes the Properties of the Overall Office System Dashboard\n");

        controlsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        manualModeLabel.setText("Manual Mode");

        manualButtonGroup.add(manualOnButton);
        manualOnButton.setSelected(true);
        manualOnButton.setText("On");
        manualOnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualOnButtonActionPerformed(evt);
            }
        });

        manualButtonGroup.add(manualOffButton);
        manualOffButton.setText("Off");
        manualOffButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualOffButtonActionPerformed(evt);
            }
        });

        loadTrackButton.setText("Load Track");
        loadTrackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadTrackButtonActionPerformed(evt);
            }
        });

        clockSlider.setMajorTickSpacing(1);
        clockSlider.setMaximum(10);
        clockSlider.setMinimum(1);
        clockSlider.setPaintLabels(true);
        clockSlider.setPaintTicks(true);
        clockSlider.setSnapToTicks(true);
        clockSlider.setValue(1);

        clockLabel.setText("Wall Clock Speed");

        fileInput.setToolTipText("");

        javax.swing.GroupLayout controlsPanelLayout = new javax.swing.GroupLayout(controlsPanel);
        controlsPanel.setLayout(controlsPanelLayout);
        controlsPanelLayout.setHorizontalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlsPanelLayout.createSequentialGroup()
                        .addComponent(clockLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(controlsPanelLayout.createSequentialGroup()
                                .addComponent(manualOnButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(manualOffButton))
                            .addComponent(clockSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(controlsPanelLayout.createSequentialGroup()
                        .addComponent(fileInput)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loadTrackButton)))
                .addContainerGap())
            .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(controlsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(manualModeLabel)
                    .addContainerGap(232, Short.MAX_VALUE)))
        );
        controlsPanelLayout.setVerticalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlsPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manualOnButton)
                    .addComponent(manualOffButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loadTrackButton)
                    .addComponent(fileInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clockLabel)
                    .addComponent(clockSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
            .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(controlsPanelLayout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(manualModeLabel)
                    .addContainerGap(107, Short.MAX_VALUE)))
        );

        trackMapImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trackImage/trackimage.png"))); // NOI18N

        stationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Station Monitor"));

        stationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Shadyside", "Red", "3.4", "Yes"},
                {"Herron Ave", "Red", "1.2", "No"},
                {"Swissvale", "Red", "2.3", "No"}
            },
            new String [] {
                "Station", "Line", "Throughput", "Occupied"
            }
        ));
        stationTable.setEnabled(false);
        stationScrollPane1.setViewportView(stationTable);

        javax.swing.GroupLayout stationPanelLayout = new javax.swing.GroupLayout(stationPanel);
        stationPanel.setLayout(stationPanelLayout);
        stationPanelLayout.setHorizontalGroup(
            stationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
            .addGroup(stationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(stationScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
        );
        stationPanelLayout.setVerticalGroup(
            stationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(stationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(stationScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
        );

        trainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Train Monitor"));

        trainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Line", "Destination", "Speed Limit"
            }
        ));
        trainTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        trainTable.setEnabled(false);
        trainTable.getTableHeader().setReorderingAllowed(false);
        trainScrollPane2.setViewportView(trainTable);

        javax.swing.GroupLayout trainPanelLayout = new javax.swing.GroupLayout(trainPanel);
        trainPanel.setLayout(trainPanelLayout);
        trainPanelLayout.setHorizontalGroup(
            trainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trainPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(trainScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
        );
        trainPanelLayout.setVerticalGroup(
            trainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trainPanelLayout.createSequentialGroup()
                .addComponent(trainScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        blockManagerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Block Manager"));

        blockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Block Number", "Line", "Speed Limit", "Infrastrucure", "Occupied", "Track Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        blockTable.setEnabled(false);
        blockScrollPane5.setViewportView(blockTable);

        javax.swing.GroupLayout blockManagerPanelLayout = new javax.swing.GroupLayout(blockManagerPanel);
        blockManagerPanel.setLayout(blockManagerPanelLayout);
        blockManagerPanelLayout.setHorizontalGroup(
            blockManagerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blockManagerPanelLayout.createSequentialGroup()
                .addComponent(blockScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
                .addContainerGap())
        );
        blockManagerPanelLayout.setVerticalGroup(
            blockManagerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blockManagerPanelLayout.createSequentialGroup()
                .addComponent(blockScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        dispatchTrainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Dispatch Train"));

        speedLimitLabel.setText("Speed Limit:");

        dispatchTrainButton.setText("Dispatch");
        dispatchTrainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispatchTrainButtonActionPerformed(evt);
            }
        });

        dispatchBlockLabel.setText("Destination Block");

        dispatchBlockInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispatchBlockInputActionPerformed(evt);
            }
        });

        DispatchLineButtonGroup.add(dispatchGreenLine);
        dispatchGreenLine.setText("Green");
        dispatchGreenLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispatchGreenLineActionPerformed(evt);
            }
        });

        DispatchLineButtonGroup.add(dispatchRedLine);
        dispatchRedLine.setText("Red");

        javax.swing.GroupLayout dispatchTrainPanelLayout = new javax.swing.GroupLayout(dispatchTrainPanel);
        dispatchTrainPanel.setLayout(dispatchTrainPanelLayout);
        dispatchTrainPanelLayout.setHorizontalGroup(
            dispatchTrainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dispatchTrainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dispatchTrainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dispatchTrainPanelLayout.createSequentialGroup()
                        .addGroup(dispatchTrainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dispatchTrainPanelLayout.createSequentialGroup()
                                .addComponent(speedLimitLabel)
                                .addGap(18, 18, 18))
                            .addGroup(dispatchTrainPanelLayout.createSequentialGroup()
                                .addComponent(dispatchBlockLabel)
                                .addGap(8, 8, 8)))
                        .addGroup(dispatchTrainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dispatchBlockInput, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(speedLimitInput))
                        .addGap(18, 18, 18)
                        .addComponent(dispatchTrainButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dispatchTrainPanelLayout.createSequentialGroup()
                        .addComponent(dispatchGreenLine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dispatchRedLine)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        dispatchTrainPanelLayout.setVerticalGroup(
            dispatchTrainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dispatchTrainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dispatchTrainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dispatchTrainPanelLayout.createSequentialGroup()
                        .addGroup(dispatchTrainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dispatchBlockLabel)
                            .addComponent(dispatchBlockInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(dispatchTrainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(speedLimitLabel)
                            .addComponent(speedLimitInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(dispatchTrainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dispatchGreenLine)
                            .addComponent(dispatchRedLine))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dispatchTrainPanelLayout.createSequentialGroup()
                        .addComponent(dispatchTrainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout officejPanelLayout = new javax.swing.GroupLayout(officejPanel);
        officejPanel.setLayout(officejPanelLayout);
        officejPanelLayout.setHorizontalGroup(
            officejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(officejPanelLayout.createSequentialGroup()
                .addGroup(officejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(officejPanelLayout.createSequentialGroup()
                        .addGroup(officejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(officejPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(dispatchTrainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(controlsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(trainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(stationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, officejPanelLayout.createSequentialGroup()
                        .addComponent(blockManagerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(trackMapImageLabel)
                .addGap(0, 0, 0))
        );
        officejPanelLayout.setVerticalGroup(
            officejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(officejPanelLayout.createSequentialGroup()
                .addGroup(officejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(officejPanelLayout.createSequentialGroup()
                        .addGroup(officejPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(trainPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, officejPanelLayout.createSequentialGroup()
                                .addComponent(controlsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dispatchTrainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(stationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(blockManagerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(trackMapImageLabel))
                .addGap(0, 0, 0))
        );

        officeTabbedPane.addTab("CTC Office Overview", officejPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(officeTabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(officeTabbedPane)
                .addContainerGap())
        );

        officeTabbedPane.getAccessibleContext().setAccessibleName("CTC Office Overview");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadTrackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadTrackButtonActionPerformed
        // TODO add your handling code here:
        String csv = fileInput.getText();
        Track trackTester;
        try {
            trackTester = new Track();
            trackTester.loadTrack(csv);
            DefaultTableModel model = (DefaultTableModel)blockTable.getModel();
            //System.out.println(trackTester.getBlock(1, "green"));
            //model.addRow(new Object[]{1, trackTester.getBlock(1, "red").getBlockNumber(), trackTester.getBlock(1, "red").getSection(), trackTester.getBlock(1, "red"), trackTester.getBlock(1, "red").getLine(), trackTester.getBlock(1, "red").getInfrastructure(),false, true});    
                
            int i=1;
            trackTester.getBlock(i);
            DecimalFormat df = new DecimalFormat("#.##");
            //System.out.print(df.format(d));
            do{
                model.addRow(new Object[]{trackTester.getBlock(i).getSection() + trackTester.getBlock(i).getBlockNumber(), trackTester.getBlock(i).getLine(), df.format(trackTester.getBlock(i).getSpeedLimit()), trackTester.getBlock(i).getInfrastrucure(), "No", "Enabled"});    
                i++;
            } while (trackTester.getBlock(i) != null);
//            
           
            fileInput.setEditable(false);
            loadTrackButton.setEnabled(false);
        } catch (IOException ex) {
            Logger.getLogger(officeWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_loadTrackButtonActionPerformed

    private void manualOffButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualOffButtonActionPerformed
        // TODO add your handling code here:
        dispatchBlockInput.setEditable(false);
        speedLimitInput.setEditable(false);
        dispatchTrainButton.setEnabled(false);
        dispatchGreenLine.setEnabled(false);
        dispatchRedLine.setEnabled(false);
    }//GEN-LAST:event_manualOffButtonActionPerformed

    private void manualOnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualOnButtonActionPerformed
        // TODO add your handling code here:
        dispatchBlockInput.setEditable(true);
        speedLimitInput.setEditable(true);
        dispatchTrainButton.setEnabled(true);
        dispatchGreenLine.setEnabled(true);
        dispatchRedLine.setEnabled(true);
    }//GEN-LAST:event_manualOnButtonActionPerformed

    private void dispatchGreenLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dispatchGreenLineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dispatchGreenLineActionPerformed

    private void dispatchBlockInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dispatchBlockInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dispatchBlockInputActionPerformed

    private void dispatchTrainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dispatchTrainButtonActionPerformed
        // TODO add your handling code here:
        String destBlock = dispatchBlockInput.getText();
        int destSpeed = Integer.parseInt(speedLimitInput.getText());
        String dispatchLine = "";
        Enumeration<AbstractButton> allRadioButton=DispatchLineButtonGroup.getElements();
        while(allRadioButton.hasMoreElements())
        {
            JRadioButton temp=(JRadioButton)allRadioButton.nextElement();
            if(temp.isSelected())
            {
                dispatchLine = temp.getText();
            }
        }
        DefaultTableModel model = (DefaultTableModel)trainTable.getModel();
        model.addRow(new Object[]{dispatchLine, destBlock, destSpeed});
        dispatchBlockInput.setText("");
        speedLimitInput.setText("");
        DispatchLineButtonGroup.clearSelection();
    }//GEN-LAST:event_dispatchTrainButtonActionPerformed
   
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
            java.util.logging.Logger.getLogger(officeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(officeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(officeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(officeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new officeWindow().setVisible(true);
                
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.ButtonGroup DispatchLineButtonGroup;
    public javax.swing.JPanel blockManagerPanel;
    public javax.swing.JScrollPane blockScrollPane5;
    public javax.swing.JTable blockTable;
    public javax.swing.JLabel clockLabel;
    public javax.swing.JSlider clockSlider;
    public javax.swing.JPanel controlsPanel;
    public javax.swing.JTextField dispatchBlockInput;
    public javax.swing.JLabel dispatchBlockLabel;
    public javax.swing.JRadioButton dispatchGreenLine;
    public javax.swing.JRadioButton dispatchRedLine;
    public javax.swing.JButton dispatchTrainButton;
    public javax.swing.JPanel dispatchTrainPanel;
    public javax.swing.JTextField fileInput;
    public javax.swing.JButton loadTrackButton;
    public javax.swing.ButtonGroup manualButtonGroup;
    public javax.swing.JLabel manualModeLabel;
    public javax.swing.JToggleButton manualOffButton;
    public javax.swing.JToggleButton manualOnButton;
    public javax.swing.JTabbedPane officeTabbedPane;
    public javax.swing.JPanel officejPanel;
    public javax.swing.JTextField speedLimitInput;
    public javax.swing.JLabel speedLimitLabel;
    public javax.swing.JPanel stationPanel;
    public javax.swing.JScrollPane stationScrollPane1;
    public javax.swing.JTable stationTable;
    public javax.swing.JLabel trackMapImageLabel;
    public javax.swing.JPanel trainPanel;
    public javax.swing.JScrollPane trainScrollPane2;
    public javax.swing.JTable trainTable;
    // End of variables declaration//GEN-END:variables
}
