package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    private final MainFrame frame;
    private JLabel label;
    private JSpinner rowsSpinner;
    private JSpinner colsSpinner;
    private JButton createButton;
    private Controller controller;

    public ConfigPanel(MainFrame frame, Controller controller) {
        this.frame = frame;
        this.setController(controller);
        init();
    }

    private void init() {
        this.setBackground(new Color(255, 255, 254));
        label = new JLabel("Grid size");
        rowsSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        colsSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        createButton = new JButton("Create");
        createButton.setFocusPainted(false);
        label.setForeground(new Color(43, 44, 52));
        createButton.setBackground(new Color(217, 212, 231));
        createButton.setForeground(new Color(43, 44, 52));
        add(label);
        add(rowsSpinner);
        add(colsSpinner);
        add(createButton);

        createButton.addActionListener(this::createGame);
    }

    private void createGame(ActionEvent e) {
        getController().deleteNodes();
        getController().setFlagStart(true);
        frame.getCanvas().init(getRows(), getCols());
        frame.getCanvas().createOffScreenImage();
        frame.getCanvas().paintGrid();
        frame.getCanvas().paintSticks();
        frame.getCanvas().repaint();
    }

    public int getRows() {
        return (Integer) rowsSpinner.getValue();
    }

    public int getCols() {
        return (Integer) colsSpinner.getValue();
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
