package javmos2.components.functions;

import javmos2.JavmosGUI;
import javmos2.enums.FunctionType;

public abstract class Trigonometric extends Function {

    protected double a;
    protected double k;

    public Trigonometric(JavmosGUI gui, String function, String name) throws Exception {
        super(gui);

        try {
            function = function.replace(" ", "");
            function = function.replace("f(x)", "y");
            
            for (int i = 0; i < function.length(); i++) {
                if (function.charAt(Character.toLowerCase(i)) == name.charAt(0)) {
                    k = i;
                }
            }

            switch (function.substring(function.indexOf("=") + 1, (int) k)) {
                case "":
                    a = 1;
                    break;
                case "-":
                    a = -1;
                    break;
                default:
                    a = Double.parseDouble(function.substring(function.indexOf("=") + 1, (int) k));
                    break;
            }
            
            switch (function.substring(function.indexOf("(") + 1, function.indexOf("x"))) {
                case "":
                    k = 1;
                    break;
                case "-":
                    k = -1;
                    break;
                default:
                    k = Double.parseDouble(function.substring(function.indexOf("(") + 1, function.indexOf("x")));
                    break;
            }
        } catch (Exception exception) {
            throw new Exception(function + " is not a valid trigonometric function!");
        }
    }

    @Override
    public abstract String getFirstDerivative();

    @Override
    public abstract String getSecondDerivative();

    @Override
    public abstract double getValueAt(double x, FunctionType functionType);
}
