package javmos2.components.functions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.HashSet;
import javmos2.JavmosGUI;
import javmos2.components.Point;
import javmos2.enums.FunctionType;
import javmos2.components.JavmosComponent;
import javmos2.enums.RootType;

public abstract class Function extends JavmosComponent {

    protected Function(JavmosGUI gui) {
        super(gui);
    }

    public abstract String getFirstDerivative();

    public abstract String getSecondDerivative();

    public abstract double getValueAt(double x, FunctionType functionType);

    public HashSet<Point> getXIntercepts() {
        HashSet<Point> set = RootType.X_INTERCEPT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
        return set;
    }

    public HashSet<Point> getCriticalPoints() {
        HashSet<Point> set = RootType.CRITICAL_POINT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
        return set;
        //Combine get X intercept and first derivative
    }

    public HashSet<Point> getInflectionPoints() {
        HashSet<Point> set = RootType.INFLECTION_POINT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
        return set;
        //Combine get x int and second derivative
    }

    @Override
    public void draw(java.awt.Graphics2D graphics2D) {
        for (double i = gui.getMinDomain(); i <= gui.getMaxDomain(); i += 0.01) {
            if (getValueAt(i, FunctionType.ORIGINAL) <= gui.getMaxRange() && getValueAt(i, FunctionType.ORIGINAL) >= gui.getMinRange()) {
                double x = (gui.getPlaneWidth() / 2) + i * gui.getZoom() / gui.getDomainStep();//make sure everything starts from origin
                double y = (gui.getPlaneWidth() / 2) - getValueAt(i, FunctionType.ORIGINAL) * gui.getZoom() / gui.getRangeStep();
                double x1 = (gui.getPlaneWidth() / 2) + (i + 0.01) * gui.getZoom() / gui.getDomainStep();//make sure everything starts from origin
                double y1 = (gui.getPlaneWidth() / 2) - getValueAt(i + 0.01, FunctionType.ORIGINAL) * gui.getZoom() / gui.getRangeStep();
                graphics2D.setStroke(new BasicStroke(3));
                graphics2D.setColor(Color.MAGENTA);
                graphics2D.draw(new Line2D.Double(x, y, x1, y1));
            }
        }
    }
}
