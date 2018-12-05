import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GuiPlot extends JPanel
{
    private XAxis xaxis;
    private YAxis yaxis;
    private GraphRenderer graphRenderer;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    private double xInterval;
    private double majorXDemarcationInterval;
    private double yInterval;

    private int xAxisSize;
    private Color axisColor;
    private int minorTicLength;
    private int majorTicLength;
    private Font font;
    private FontMetrics fm;




    GuiPlot()
    {
        super(new BorderLayout());


        axisColor = Color.BLACK;
        minorTicLength = 3;
        majorTicLength = 7;
        font = new Font("Dialog", Font.PLAIN, 12 );
        fm = this.getFontMetrics(font);

        xAxisSize = 20; //Y axis dynamically resizes based on label needs.

        xMin = -0;
        xMax = 100;
        yMin = -10.0;
        yMax = 10.0;

        xInterval = 5;
        majorXDemarcationInterval = 2;

        xaxis = new XAxis();
        yaxis = new YAxis();
        graphRenderer = new GraphRenderer();
        add(graphRenderer, BorderLayout.CENTER);
        add(yaxis, BorderLayout.WEST);
        add(xaxis, BorderLayout.SOUTH);

    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

    }

    public void setRange(double min, double max){this.yMin = min; this.yMax = max;}
    public void setDomain(double min, double max){this.xMin = min; this.xMax = max;}

    private class XAxis extends JPanel
    {
        XAxis() {
            setPreferredSize(new Dimension(0,xAxisSize));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);  // paint background
            setBackground(Color.green);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(axisColor);
            g2.drawLine(yaxis.getWidth(), 0, getWidth(), 0); //Draw axis line.


            int xMinr = yaxis.getWidth();
            int pxRange = getWidth() - yaxis.getWidth();
            int pxOrgin = (int)(pxRange * -xMin/(xMax-xMin));
            double domain = xMax - xMin;
            //g2.drawLine(xMinr + pxOrgin, 0, xMinr + pxOrgin, 10);
            for(int i = 0; i <= Math.abs(xMax/xInterval); i++) //Draw intervals from x = 0 to xMax. Includes 0
            {
                int pxOffset = point2pix(i * xInterval); //Calculate new offset
                if(i%majorXDemarcationInterval == 0) {
                    g2.drawLine(xMinr + pxOffset, 0,xMinr + pxOffset, majorTicLength ); //Draw major tic
                    //String drawing code below
                    String str = Double.toString(i * xInterval); //Gen string
                    Rectangle2D strSize = fm.getStringBounds(str, g);
                    g2.drawString(str, xMinr + pxOffset - (int)(strSize.getWidth()/2), font.getSize() +  majorTicLength );
                } else {
                    g2.drawLine(xMinr + pxOffset, 0,xMinr + pxOffset, minorTicLength ); //Draw minor tic
                }
            }
            for(int i = 1; i <= Math.abs(xMin/xInterval); i++) //Draw intervals from x = 0 to xMin.
            {
                int pxOffset = point2pix(i * -xInterval); //Go the opposite way.
                if(i%majorXDemarcationInterval == 0) {
                    g2.drawLine(xMinr + pxOffset, 0,xMinr + pxOffset, majorTicLength ); //Draw major tic
                    //String drawing code below
                    String str = Double.toString(i * -xInterval); //Gen string
                    Rectangle2D strSize = fm.getStringBounds(str, g);
                    g2.drawString(str, xMinr + pxOffset - (int)(strSize.getWidth()/2), font.getSize() +  majorTicLength );
                } else {
                    g2.drawLine(xMinr + pxOffset, 0,xMinr + pxOffset, minorTicLength ); //Draw minor tic
                }
            }

        }



        private int point2pix(double point)
        {
            int pxRange = getWidth() - yaxis.getWidth();
            return (int)(pxRange * (point-xMin)/(xMax-xMin));
        }

    }

    private class YAxis extends JPanel
    {
        YAxis() {
            setPreferredSize(new Dimension(xAxisSize,0));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);  // paint background
            setBackground(Color.PINK);

            // Your custom painting codes
            // ......
        }
    }
    private class GraphRenderer extends JPanel
    {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);  // paint background
            setBackground(Color.lightGray);

            // Your custom painting codes
            // ......
        }
    }
}




