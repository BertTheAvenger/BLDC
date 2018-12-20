import javax.swing.*;
import java.awt.*;


public class GMainPanel extends JPanel implements GuiComponent{
    private JPanel graphPanel;
    private GControlPanel controlPanel;
    GMainPanel()
    {
        super();
        setLayout(new BorderLayout());
        setBackground(new Color(255,127,127));

        graphPanel = new GGraphHolder();
        add(graphPanel, BorderLayout.CENTER);

        controlPanel = new GControlPanel();
        add(controlPanel, BorderLayout.LINE_START);
    }

    @Override
    public GridBagConstraints getConstraints() {

        return null;
    }
    public void setSerialStatus(int serialStatus){controlPanel.setSerialStatus(serialStatus);}
}
