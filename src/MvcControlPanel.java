import Serial.TXCommands.TXACK;
import Serial.TXCommands.TXADDSHORTS;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MvcControlPanel extends JPanel {

    private serialStatusIndicator serialStatusIndicator;
    private motorControlPanel motorControlPanel;
    public MvcControlPanel()
    {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new TitledBorder("Controls"));
        setPreferredSize(new Dimension(300,0));
        serialStatusIndicator = new serialStatusIndicator();
        //serialStatusIndicator.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(serialStatusIndicator);

        motorControlPanel = new motorControlPanel();
        add(motorControlPanel);

    }
    public void setSerialStatus(int serialStatus){serialStatusIndicator.setSerialStatus(serialStatus);}





    private class serialStatusIndicator extends JPanel {
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
    private class motorControlPanel extends JPanel
    {
        private JToggleButton pausePlayButton;
        private JToggleButton ccButton;
        private JToggleButton cButton;
        private boolean playButtonStatus = false;
        private JCheckBox externalDriveWave;

        private ImageIcon ccIcon;
        private ImageIcon pauseIcon;
        private ImageIcon playIcon;
        private ImageIcon cIcon;
        public motorControlPanel()
        {
            super();
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new TitledBorder("Motor"));

            //ButtonPanel below.
            initIcons();
            add(createSpeedSlider());
            add(createLowerPanel());
            add(new JButton(new AbstractAction("SEND IT") {
                @Override
                public void actionPerformed(ActionEvent e) {
                   SerialHandler.sendSerialCommand(new TXACK());
                }
            }));
        }
        private void initIcons() //STD 30px SQUARE
        {
            ccIcon = new ImageIcon("./img/counterclockwiseIcon.png");
            pauseIcon = new ImageIcon("./img/pauseIcon.png");
            playIcon = new ImageIcon("./img/playIcon.png");
            cIcon = new ImageIcon("./img/clockwiseIcon.png");
        }

        private JPanel createLowerPanel()
        {
            JPanel lowerPanel = new JPanel(new BorderLayout()){
                    public Dimension getMaximumSize() {
                        return new Dimension(super.getMaximumSize().width, 40);
                    }
            };
            //lowerPanel.setBackground(new Color(255,0,0));

            JPanel buttonPanel = createButtonPanel();
            lowerPanel.add(buttonPanel, BorderLayout.LINE_END);

            externalDriveWave = new JCheckBox("External Drive");
            externalDriveWave.setToolTipText("Switches between the internal and external drive wave on the Arduino. External might be slower because it is fed over serial, but is modifiable in real time");
            lowerPanel.add(externalDriveWave, BorderLayout.LINE_START);

            return lowerPanel;
        }


        private JPanel createButtonPanel()
        {

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            //buttonPanel.setBackground(new Color(255,0,255));

            ccButton = new JToggleButton(ccIcon);
            ccButton.setPreferredSize(new Dimension(30,30));
            buttonPanel.add(ccButton);

            pausePlayButton = new JToggleButton();
            pausePlayButton.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playButtonStatus = !playButtonStatus;
                    if(playButtonStatus)
                    {
                        pausePlayButton.setIcon(pauseIcon);
                    }
                    else
                    {
                        pausePlayButton.setIcon(playIcon);
                    }
                }
            });
            pausePlayButton.setPreferredSize(new Dimension(30,30));
            pausePlayButton.setIcon(playIcon);
            buttonPanel.add(pausePlayButton);
            cButton = new JToggleButton(cIcon);
            //cButton.setEnabled(false);
            cButton.setPreferredSize(new Dimension(30,30));
            buttonPanel.add(cButton);

            return buttonPanel;
        }

        private JSlider createSpeedSlider()
        {
            JSlider slider = new JSlider();
            return slider;
        }

        public Dimension getMaximumSize()
        {
            return new Dimension(super.getMaximumSize().width, 100);
        }

    }


}

