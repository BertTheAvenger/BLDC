import javax.activation.DataHandler;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

class GCPCalibration extends JPanel //GUI Motor Control Panel. Used by GControlPanel
{
    JPanel inputPanel;
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
        return new JPanel();
    }



    @Override
    public Dimension getMaximumSize() {
        return new Dimension(super.getMaximumSize().width, 100);
    }

}