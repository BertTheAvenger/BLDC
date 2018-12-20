import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GControlPanel extends JPanel { //Main panel on left side of screen

    private serialStatusIndicator serialStatusIndicator;
    private GCPMotor motorControlPanel;
    private GCPCalibration calibrationPanel;
    public GControlPanel()
    {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //Align inner panels vertically.
        setBorder(new TitledBorder("Controls"));
        setPreferredSize(new Dimension(300,0));
        serialStatusIndicator = new serialStatusIndicator();
        //serialStatusIndicator.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(serialStatusIndicator);

        motorControlPanel = new GCPMotor();
        add(motorControlPanel);

        calibrationPanel = new GCPCalibration();
        add(calibrationPanel);

    }
    public void setSerialStatus(int serialStatus){serialStatusIndicator.setSerialStatus(serialStatus);}





    private class serialStatusIndicator extends JPanel { //Small indicator at top of screen indicating serial status.
        private JLabel serialLabel;
        public serialStatusIndicator() {
            super(new BorderLayout());
            //setPreferredSize(new Dimension(0, 15));
            //setMaximumSize(new Dimension(20000, 15));
            serialLabel = new JLabel();
            serialLabel.setHorizontalAlignment(SwingConstants.CENTER);
            serialLabel.setVerticalAlignment(SwingConstants.CENTER);
            add(serialLabel, BorderLayout.CENTER);
            setSerialStatus(0);
        }

        @Override
        public Dimension getMaximumSize()
        {
            return new Dimension(super.getMaximumSize().width, 15);
        }

        public void setSerialStatus(int serialStatus) {
            switch (serialStatus){ //0 is disconnected, 1 is connected.
                case 0:
                    setBackground(new Color(244,86,66));
                    serialLabel.setText("Serial Disconnected");
                    break;
                case 1:
                    setBackground(new Color(66,244,78));
                    serialLabel.setText("Serial Connected");
                    break;

            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------



}

