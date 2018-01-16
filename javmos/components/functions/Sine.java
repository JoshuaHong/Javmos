package javmos2.components.functions;

import javmos2.JavmosGUI;
import javmos2.enums.FunctionType;

public class Sine extends Trigonometric {
    
    public Sine(JavmosGUI gui, String function) throws Exception {
        super(gui, function, "sine");
    }

    @Override
    public String getFirstDerivative() {
        return "y=" + a * k + "cos(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        return "y=" + -1 * a * Math.pow(k, 2) + "sin(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType.ordinal()) {
            case 0:
                return a * Math.sin(k * x);
            case 1:
                return a * k * Math.cos(k * x);
            case 2:
                return -1 * a * Math.pow(k, 2) * Math.sin(k * x);
            default:
                return -1 * a * Math.pow(k, 3) * Math.cos(k * x);
        }
    }
}