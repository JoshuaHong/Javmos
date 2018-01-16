package javmos2.components.functions;

import java.util.HashSet;
import javmos2.JavmosGUI;
import javmos2.components.Point;
import javmos2.enums.FunctionType;
import javmos2.enums.RootType;

public class Tangent extends Trigonometric {

    public Tangent(JavmosGUI gui, String function) throws Exception {
        super(gui, function, "tangent");
    }

    @Override
    public String getFirstDerivative() {
        return "y=" + a * k + "sec^2(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        return "y=" + a * Math.pow(k, 3) + "sec^2(" + k + "x)tan(" + k + "x)";
    }

    public HashSet<Point> getCriticalPoints() {
        HashSet<Point> set = new HashSet<>();
        return set;
        //Combine get X intercept and first derivative
    }

    public HashSet<Point> getInflectionPoints() {
        HashSet<Point> set = new HashSet<>();
        return set;
        //Combine get x int and second derivative
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType.ordinal()) {
            case 0:
                return a * Math.tan(k * x);
            case 1:
                return a * k / Math.pow(Math.cos(k * x), 2);
            case 2:
                return a * Math.pow(k, 3) * Math.tan(k * x) / Math.pow(Math.cos(k * x), 2);
            default:
                return a * Math.pow(k, 5) * Math.pow(Math.tan(k * x), 2) / Math.pow(Math.cos(k * x), 2) + a * Math.pow(k, 4) / Math.pow(Math.cos(k * x), 2);
        }
    }
}
