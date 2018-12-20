import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

class GCPMotor extends JPanel //GUI Motor Control Panel. Used by GControlPanel
{
    private JToggleButton pausePlayButton; //Pause/Plays motor.
    private JToggleButton ccButton; //Sets motor dir Counter Clockwise
    private JToggleButton cButton; //Sets motor dir Clockwise
    private boolean playButtonStatus = false; //Keeps track of pause/play state. Replace W/ Event hander on toggle.
    private JCheckBox externalDriveWave; //Determines wether drive wave on Arduino is used or external from computer is.

    //Icons for control buttons.
    private ImageIcon ccIcon;
    private ImageIcon pauseIcon;
    private ImageIcon playIcon;
    private ImageIcon cIcon;
    public GCPMotor() //ControlPanel Motor.
    {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new TitledBorder("Motor (UNIMPLEMENTED)"));

        //ButtonPanel below.
        initIcons();
        add(createSpeedSlider());
        add(createLowerPanel());
    }
    private void initIcons() //STD 30px SQUARE
    {
        ccIcon = new ImageIcon("./img/counterclockwiseIcon.png");
        pauseIcon = new ImageIcon("./img/pauseIcon.png");
        playIcon = new ImageIcon("./img/playIcon.png");
        cIcon = new ImageIcon("./img/clockwiseIcon.png");
    }

    private JPanel createLowerPanel() //Holds buttons and checkbox.
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


    private JPanel createButtonPanel() //Holds only motor buttons.
    {

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //buttonPanel.setBackground(new Color(255,0,255));

        //Create buttons.
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

    private JSlider createSpeedSlider() //Slider to adujust motor speed.
    {
        JSlider slider = new JSlider();
        return slider;
    }

    public Dimension getMaximumSize()
    {
        return new Dimension(super.getMaximumSize().width, 100);
    }

}