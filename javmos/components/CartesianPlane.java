package javmos2.components;

import java.awt.*;
import java.text.DecimalFormat;
import javmos2.JavmosGUI;

public class CartesianPlane extends JavmosComponent{
    
    public CartesianPlane(JavmosGUI gui) {
        super(gui);
    }

    @Override
    public void draw(java.awt.Graphics2D graphics2D) {
        int width = gui.getPlaneWidth();
        double zoom = gui.getZoom();
        DecimalFormat f = new DecimalFormat("#.##");
        //Paints the axis beginning in the middle
        for (int i = (width / 2); i > 0; i += zoom) {
            graphics2D.setColor(Color.BLACK);
            if (i == (width / 2)) {
                graphics2D.setStroke(new BasicStroke(3));
            } else {
                graphics2D.setStroke(new BasicStroke(1));
            }
            //Start from middle move toward left
            if (i > width) {
                i = (width / 2);
                zoom = -gui.getZoom();
            }

            //Paints the numbers along the axis
            if (i >= (width / 2)) {
                graphics2D.drawString(f.format((((width / 2) - i) / (int) zoom) * gui.getRangeStep()), width / 2, i);
                graphics2D.drawString(f.format(((i - (width / 2)) / (int) zoom) * gui.getDomainStep()), i, width / 2);
            } else {
                graphics2D.drawString(f.format((((width / 2) - i) / (int) zoom) * -gui.getRangeStep()), width / 2, i);
                graphics2D.drawString(f.format(((i - (width / 2)) / (int) zoom) * -gui.getDomainStep()), i, width / 2);
            }

            //Changes the font size and style of the numbers and the axis
            graphics2D.setFont(new Font("Times New Romans", Font.BOLD, 16));
            graphics2D.drawLine(i, width, i, 0);
            graphics2D.drawLine(0, i, width, i);
        }
    }
}
