package model;

import javax.swing.*;

import java.awt.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.NORTH;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanelImage canvas;

    public MainFrame() {
        super("My Game");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controlPanel = new ControlPanel(this);
        configPanel = new ConfigPanel(this);
        canvas = new DrawingPanelImage(this);

        add(controlPanel, SOUTH);
        add(configPanel, NORTH);
        add(canvas, CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void update(Graphics g) {
    }
}
