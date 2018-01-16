package javmos2.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javmos2.JavmosGUI;
import javmos2.components.JavmosPanel;
import javmos2.components.functions.*;

public class DrawListener implements ActionListener {

    private final JavmosGUI gui;
    private final JavmosPanel panel;

    public DrawListener(JavmosGUI gui, JavmosPanel panel) {
        this.gui = gui;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            if (gui.getEquationField().contains("sin")) {
                panel.setFunction(new Sine(gui, gui.getEquationField()));
            } else if (gui.getEquationField().contains("cos")) {
                panel.setFunction(new Cosine(gui, gui.getEquationField()));
            } else if (gui.getEquationField().contains("tan")) {
                panel.setFunction(new Tangent(gui, gui.getEquationField()));
            } else if (gui.getEquationField().contains("log") ||gui.getEquationField().contains("ln")) {
                panel.setFunction(new Logarithmic(gui, gui.getEquationField()));
            } else if (gui.getEquationField().isEmpty() == false) {
                panel.setFunction(new Polynomial(gui, gui.getEquationField()));
            }
            gui.setFirstDerivativeLabel(panel.getFunction().getFirstDerivative());
            gui.setSecondDerivativeLabel(panel.getFunction().getSecondDerivative());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Function Error", JOptionPane.ERROR_MESSAGE);
        }
        panel.repaint();
    }
}
