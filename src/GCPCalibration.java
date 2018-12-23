import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

class GCPCalibration extends JPanel //GUI Motor Control Panel. Used by GControlPanel
{
    JPanel inputPanel;
    JProgressBar progressBar;
    public GCPCalibration() //ControlPanel Motor.
    {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new TitledBorder("Calibration"));

        inputPanel = createInputPanel();
        add(inputPanel);

        add(new JButton(new AbstractAction("Start") { //Start Calibration button.
            @Override
            public void actionPerformed(ActionEvent e) {
                HardwareDriver.startCalibration();
            }
        }));

        createProgressBar();
        add(progressBar);
        HardwareDriver.addCalibrationStepListener(new CalibrationStepListener() {
            @Override
            public void calibrationStepped(CalibrationStep step) {
                setProgressBar(step.progress, step.descriptor); //Replace with getters and setters
            }
        });



    }

    private JPanel createInputPanel()
    {
        JPanel panel = new JPanel(){
            @Override
            public Dimension getMaximumSize()
            {
                return new Dimension(super.getMaximumSize().width, 20);
            }

        };
        return panel;
    }

    private void createProgressBar()
    {
        progressBar = new JProgressBar(){
            public Dimension getMaximumSize()
            {
                return new Dimension(super.getMaximumSize().width, 30);
            }
        };
        progressBar.setMinimum(0);
        progressBar.setMaximum(1000);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setString("LMAO");
    }

    void setProgressBar(double progress, String label)
    {
        progressBar.setValue((int)(progress * 1000));
        progressBar.setString(label);
    }



    @Override
    public Dimension getMaximumSize() {
        return new Dimension(super.getMaximumSize().width, 100);
    }

}