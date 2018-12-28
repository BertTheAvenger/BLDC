import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

class GCPCalibration extends JPanel //GUI Motor Control Panel. Used by GControlPanel
{
    private JPanel inputPanel;
    private JProgressBar progressBar;
    GCPCalibration() //ControlPanel Motor.
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
        HardwareDriver.addCalibrationStepListener(step -> setProgressBar(step.progress, step.descriptor));



    }

    private JPanel createInputPanel()
    {
        return new JPanel(){
            @Override
            public Dimension getMaximumSize()
            {
                return new Dimension(super.getMaximumSize().width, 20);
            }
        };
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

    private void setProgressBar(double progress, String label)
    {
        progressBar.setValue((int)(progress * 1000));
        progressBar.setString(label);
    }



    @Override
    public Dimension getMaximumSize() {
        return new Dimension(super.getMaximumSize().width, 100);
    }

}