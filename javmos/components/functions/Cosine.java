package javmos2.components.functions;

import javmos2.JavmosGUI;
import javmos2.enums.FunctionType;

public class Cosine extends Trigonometric {
    
    public Cosine(JavmosGUI gui, String function) throws Exception {
        super(gui, function, "cosine");
    }

    @Override
    public String getFirstDerivative() {
        return "y=" + -1 * a * k + "sin(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        return "y=" + -1 * a * Math.pow(k, 2) + "cos(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType.ordinal()) {
            case 0:
                return a * Math.cos(k * x);
            case 1:
                return -1 * a * k * Math.sin(k * x);
            case 2:
                return -1 * a * Math.pow(k, 2) * Math.cos(k * x);
            default:
                return a * Math.pow(k, 3) * Math.sin(k * x);
        }
    }
}