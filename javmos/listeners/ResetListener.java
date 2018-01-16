package javmos2.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javmos2.JavmosGUI;
import javmos2.components.JavmosPanel;

public class ResetListener implements ActionListener {

    private final JavmosGUI gui;
    private final JavmosPanel panel;

    public ResetListener(JavmosGUI gui, JavmosPanel panel) {
        this.gui = gui;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gui.resetFields();
        panel.repaint();
    }
}
