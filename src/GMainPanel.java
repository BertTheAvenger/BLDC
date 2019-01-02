import javax.swing.*;
import java.awt.*;


class GMainPanel extends JPanel{
    private GGraphHolder graphPanel;
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

    void setSinWave(int[] sinArr){graphPanel.setSinWave(sinArr);}
}

