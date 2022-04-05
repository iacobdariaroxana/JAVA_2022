package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.NORTH;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    private DrawingPanelImage canvas;
    private Controller controller;

    public MainFrame() {
        super("Positional Game");
        this.setController(new Controller());
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controlPanel = new ControlPanel(this, getController());
        configPanel = new ConfigPanel(this, getController());
        setCanvas(new DrawingPanelImage(this, getController()));

        add(controlPanel, SOUTH);
        add(configPanel, NORTH);
        add(getCanvas(), CENTER);

        ImageIcon icon = new ImageIcon("logo.png");
        setIconImage(icon.getImage());

        pack();
        setLocationRelativeTo(null);
    }

    public void updateController(Controller controller){
        this.setController(controller);
        configPanel.setController(controller);
        canvas.setController(controller);
        controlPanel.setController(controller);
    }

    @Override
    public void update(Graphics g) {
    }

    public DrawingPanelImage getCanvas() {
        return canvas;
    }

    public void setCanvas(DrawingPanelImage canvas) {
        this.canvas = canvas;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
