package cartography;

import model.City;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WorldFrame extends JFrame {
    public DrawingPanel canvas;
    private List<City> capitals;
    public WorldFrame(List<City> capitals){
        super("World Capitals Map");
        this.capitals = capitals;
        init();
    }

    public void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new DrawingPanel(this);

//        ImageIcon frameIcon = new ImageIcon("frameIcon.jpg");
//        setIconImage(frameIcon.getImage());

        setLayout(new BorderLayout());

        add(canvas, BorderLayout.CENTER);
        pack();
    }

    public List<City> getCapitals() {
        return capitals;
    }

    public void setCapitals(List<City> capitals) {
        this.capitals = capitals;
    }
}
