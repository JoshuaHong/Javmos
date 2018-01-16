package javmos2.components.functions;

import static java.lang.Math.E;
import javmos2.JavmosGUI;
import javmos2.enums.FunctionType;

public class Logarithmic extends Function {

    public double a;
    public double base;
    public double k;

    public Logarithmic(JavmosGUI gui, String function) throws Exception {
        super(gui);

        try {
            function = function.replace(" ", "");
            function = function.replace("f(x)", "y");

            switch (function.substring(function.indexOf("=") + 1, function.indexOf('l'))) {
                case "-":
                    a = -1;
                    break;
                case "":
                    a = 1;
                    break;
                default:
                    a = Double.parseDouble(function.substring(function.indexOf("=") + 1, function.indexOf('l')));
                    break;
            }

            if (function.contains("log")) {
                if (function.substring(function.indexOf("g") + 1, function.indexOf("(")).equals("")) {
                    base = 10;
                } else if (Double.parseDouble(function.substring(function.indexOf("g") + 1, function.indexOf("("))) <= 0) {
                    throw new Exception(function + " is not a valid logarithmic function!");
                } else {
                    base = Double.parseDouble(function.substring(function.indexOf("g") + 1, function.indexOf("(")));
                }
            } else if (function.contains("ln")) {
                base = E;
                
                if (!function.substring(function.indexOf("n") + 1, function.indexOf("(")).equals("")) {
                    throw new Exception(function + " is not a valid logarithmic function!");
                }
            }

            switch (function.substring(function.indexOf("(") + 1, function.indexOf('x'))) {
                case "-":
                    k = -1;
                    break;
                case "":
                    k = 1;
                    break;
                default:
                    k = Double.parseDouble(function.substring(function.indexOf("(") + 1, function.indexOf('x')));
                    break;
            }
        } catch (Exception exception) {
            throw new Exception(function + " is not a valid logarithmic function!");
        }
    }

    @Override
    public String getFirstDerivative() {
        return "y=" + a + "/(xln" + base + ")";
    }

    @Override
    public String getSecondDerivative() {
        return "y=" + -1 * a + "/(x^2ln" + base + ")";

    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType.ordinal()) {
            case 0:
                return a * Math.log(k * x) / Math.log(base);
            case 1:
                return a / (x * Math.log(base));
            case 2:
                return -1 * a / (Math.pow(x, 2) * Math.log(base));
            default:
                return 2 * a / (Math.pow(x, 3) * Math.log(base));
        }
    }
}
