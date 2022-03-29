package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    private JButton exitButton = new JButton("Exit");
    private JButton saveButton = new JButton("Save");
    private JButton loadButton = new JButton("Load");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
//        setLayout(new GridLayout(1, 3, 10, 10));

        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        saveButton.setBackground(Color.GREEN.darker());
        saveButton.setForeground(Color.WHITE);
        loadButton.setBackground(Color.BLUE);
        loadButton.setForeground(Color.WHITE);

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

    }

    private void loadGame(ActionEvent e) {

    }
}
