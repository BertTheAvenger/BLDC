import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GuiGraphHolder extends JPanel {

    GuiGraph angleGraph;
    GuiGraphHolder()
    {
        super(new GridBagLayout());
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10,10,10,10), new TitledBorder(new LineBorder(Color.GRAY, 1), "Graphs")));
        GridBagConstraints c = new GridBagConstraints();

        angleGraph = new GuiGraph();
        angleGraph.setBorder(new EmptyBorder(10,10,10,10));
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = .33;
        add(angleGraph, c);

        c.gridy = 1;
        add(new JPanel(), c);

        c.gridy = 2;
        add(new JPanel(), c);

    }

}
