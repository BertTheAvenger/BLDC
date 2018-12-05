import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GuiGraph extends JPanel implements GuiComponent
{
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private boolean autoScale;
    private boolean border;


    private double xDemarcationInterval;
    private double yDemarcationInterval;

    private Color lineColor;

    private ArrayList<Point2D> points;
    public GuiGraph()
    {
        super();
        this.xMin = -10;
        this.xMax = 10;
        this.yMin = -10;
        this.yMax = 10;
        this. xDemarcationInterval = 1;
        this.autoScale = false;
        this.lineColor = Color.black;
        border = true;
    }

    @Override
    public GridBagConstraints getConstraints() {
        return null;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // paint background
        //setBackground(Color.lightGray);
        Graphics2D g2 =  (Graphics2D) g;

        Font font = new Font("Dialog", Font.PLAIN, 12); //Set Font
        FontMetrics fm = g2.getFontMetrics(font);
        int advance = fm.getMaxAdvance();

        Point graphCenter = point2graph(new Point2D.Double(0,0));

        g2.setColor(lineColor);
        g2.setStroke(new BasicStroke(1));

        if(border){g2.drawRect(getInsets().left, getInsets().top, getWidth() - getInsets().right - getInsets().left, getHeight() - getInsets().bottom - getInsets().top);}

        g2.drawLine(graphCenter.x, getInsets().top, graphCenter.x, getHeight() - getInsets().bottom); //Draw Y axis line
        g2.drawLine(getInsets().left, graphCenter.y, getWidth() - getInsets().right, graphCenter.y); //Draw X axis line
        //int numOfXPlus =
        for(int i = 1; true; i++) //From center of graph to graph max... Increase by pixel count demarcation length
        {
            int x = point2graph(new Point2D.Double(i*xDemarcationInterval, 0)).x;
            if(x > getInsets().right){g2.drawLine(x, graphCenter.y , x, graphCenter.y + 5 );}
            if(x > getWidth() - getInsets().right){break;}
        }



        g2.setFont(font);
        //g2.drawString("AYYY MAH NIGGA", 0,10);
        //g2.fillOval(0,0,100,100);

        // Your custom painting codes
        // ......
    }
    private Point point2graph(Point2D.Double point)
    {

        return new Point((int)((getWidth() - getInsets().left)*(point.x - xMin)/(xMax-xMin)) ,(int)((getHeight() - getInsets().top)*(point.y - yMin)/(yMax-yMin)));
    }


    public void setDomain(double min, double max) {
        this.xMin = min;
        this.xMax = max;
    }
    public void setRange(double min, double max) {
        this.yMin = min;
        this.yMax = max;
    }
    public void setAutoScale(boolean set) {
        this.autoScale = set;
    }

    public void setIntervals(double x, double y)
    {
        this.xDemarcationInterval = x;
        this.yDemarcationInterval = y;
    }
    public void clearGraph()
    {

    }
    public void addPoint(Point2D point)
    {
        points.add(point);
        updateUI();
    }
}
