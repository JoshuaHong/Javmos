package javmos2.components;

import javmos2.JavmosGUI;

public abstract class JavmosComponent {

    protected final JavmosGUI gui;

    public JavmosComponent(JavmosGUI gui) {
        this.gui = gui;
    }
    public abstract void draw(java.awt.Graphics2D graphics2D);
}
