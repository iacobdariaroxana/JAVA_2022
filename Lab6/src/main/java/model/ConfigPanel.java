package model;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    private final MainFrame frame;
    private JLabel label;
    private JSpinner rowsSpinner;
    private JSpinner colsSpinner;
    private JButton createButton;

    public ConfigPanel(MainFrame frame){
        this.frame = frame;
        init();
    }
    private void init(){
        label = new JLabel("Grid size");
        rowsSpinner = new JSpinner(new SpinnerNumberModel(10,2,100,1));
        colsSpinner = new JSpinner(new SpinnerNumberModel(10,2,100,1));
        createButton = new JButton("Create");

        add(label);
        add(rowsSpinner);
        add(colsSpinner);
        add(createButton);

        createButton.addActionListener(this::createGame);
/*       rowsSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                frame.canvas.init(getRows(), getCols());
                frame.canvas.repaint();
            }
        });
        colsSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                frame.canvas.init(getRows(), getCols());
                frame.canvas.repaint();
            }
        });*/
    }
    private void createGame(ActionEvent e){
        frame.canvas.init(getRows(), getCols());
        frame.canvas.repaint();
    }

    public int getRows() {
        return (Integer)rowsSpinner.getValue();
    }

    public int getCols() {
        return (Integer)colsSpinner.getValue();
    }
}
