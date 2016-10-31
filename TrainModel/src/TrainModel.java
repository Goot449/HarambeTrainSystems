import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private Train train;

    public TrainModel() {
        Timer timer = new Timer((int) (1), e -> {
            updateProperties();
        });
        timer.setRepeats(true);
        timer.start();

        train = new Train(500);
        emergencyBrake.addActionListener(e -> {
            System.out.println(train.getFeedbackVelocity());
        });
        setPowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                train.setPower((double) spinner1.getValue() * 1000);
            }
        });
    }

    public void updateProperties() {
        DecimalFormat outputFormat = new DecimalFormat("#.00");
        feedbackSpeed.setText(outputFormat.format(train.getFeedbackVelocity()) + " mph");
        acceleration.setText(outputFormat.format(train.getAcceleration()) + " ft/s²");
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
