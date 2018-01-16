package javmos2.enums;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.HashSet;
import javmos2.JavmosGUI;
import javmos2.components.Point;
import javmos2.components.functions.Function;

public enum RootType {

    X_INTERCEPT(Color.GREEN, FunctionType.ORIGINAL, FunctionType.FIRST_DERIVATIVE),
    CRITICAL_POINT(Color.RED, FunctionType.FIRST_DERIVATIVE, FunctionType.SECOND_DERIVATIVE),
    INFLECTION_POINT(Color.BLUE, FunctionType.SECOND_DERIVATIVE, FunctionType.THIRD_DERIVATIVE);

    public final String rootName = name();
    public final Color rootColor;
    public final int ATTEMPTS = 15;
    public final FunctionType functionOne;
    public final FunctionType functionTwo;

    RootType(Color color, FunctionType one, FunctionType two) {
        rootColor = color;
        functionOne = one;
        functionTwo = two;
    }

    public Color getPointColor() {
        return rootColor;
    }

    public String getPointName() {
        return rootName;
    }

    public java.util.HashSet<Point> getRoots(JavmosGUI gui, Function function, double minDomain, double maxDomain) {
        DecimalFormat f = new DecimalFormat("#.###");
        HashSet<Point> temp = new HashSet<>();
        for (double x = minDomain; x <= maxDomain; x += 1) {
            if (newtonsMethod(function, x, ATTEMPTS * 20) != null && newtonsMethod(function, x, ATTEMPTS * 21) != null) {
                double xA = Double.parseDouble(f.format(newtonsMethod(function, x, ATTEMPTS * 20)));
                double xCheck = Double.parseDouble(f.format(newtonsMethod(function, x, ATTEMPTS * 20)));
                if (xA == xCheck && minDomain <= xA && xA <= maxDomain) {
                    double yA = Double.parseDouble(f.format(function.getValueAt(xA, FunctionType.ORIGINAL)));
                    if (yA == -0) {
                        yA = 0;
                    }
                    Point root = new Point(gui, this, xA, yA);
                    temp.add(root);
                }
            }
        }
        return temp;
    }

    protected Double newtonsMethod(Function function, double guess, int attempts) {
        if (attempts == 0 && Double.isNaN(guess) == false) { //check for NaN
            return guess;
        } else if ((functionOne == null) ||(functionTwo == null) || Double.isNaN(guess) == true) { // check for NaN, function that doesnt have second or third derivative
            return null;
        } else {
            return newtonsMethod(function, guess - (function.getValueAt(guess, functionOne)/function.getValueAt(guess, functionTwo)), attempts - 1);
        }
    }
}
