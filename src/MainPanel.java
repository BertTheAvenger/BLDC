import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MainPanel extends JPanel implements GuiComponent{
    private JPanel graphPanel;
    private JPanel controlPanel;
    MainPanel()
    {
        super();
        setLayout(new GridBagLayout());
        setBackground(new Color(255,127,127));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;


        graphPanel = new GuiGraphHolder();
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = .5;
        c.weighty = 1;
        add(graphPanel, c);

        controlPanel = new JPanel(new GridBagLayout());
        c.gridx = 0;
        add(controlPanel, c);

    }

    @Override
    public GridBagConstraints getConstraints() {

        return null;
    }
}
