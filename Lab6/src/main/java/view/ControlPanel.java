package view;

import controller.Controller;
import controller.GameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    private JButton exitButton = new JButton("Exit");
    private JButton saveButton = new JButton("Save");
    private JButton loadButton = new JButton("Load");
    private Controller controller;

    public ControlPanel(MainFrame frame, Controller controller) {
        this.frame = frame;
        this.setController(controller);
        init();
    }

    private void init() {
        this.setBackground(new Color(255, 255, 254));
        exitButton.setBackground(new Color(217, 212, 231));
        exitButton.setForeground(new Color(43, 44, 52));
        exitButton.setFocusPainted(false);
        saveButton.setBackground(new Color(217, 212, 231));
        saveButton.setForeground(new Color(43, 44, 52));
        saveButton.setFocusPainted(false);
        loadButton.setBackground(new Color(217, 212, 231));
        loadButton.setForeground(new Color(43, 44, 52));
        loadButton.setFocusPainted(false);
        add(exitButton);
        add(saveButton);
        add(loadButton);

        new GroupLayout(this);
        exitButton.addActionListener(this::exitGame);
        saveButton.addActionListener(this::saveGame);
        loadButton.addActionListener(this::loadGame);
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    private void saveGame(ActionEvent e) {
        new GameUtil().save(controller, "game.bin");
        frame.getCanvas().saveCurrentStateToPng();

        saveButton.setBackground(new Color(217, 212, 231));
        saveButton.setText("Saved");
        Timer timer = new Timer(400, event -> {
            saveButton.setBackground(new Color(217, 212, 231));
            saveButton.setText("Save");
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void loadGame(ActionEvent e) {
        Controller restoredController = new GameUtil().load("game.bin");
        frame.updateController(restoredController);
        frame.getCanvas().replaceImage();
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
