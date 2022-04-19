package cartography;

import model.City;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawingPanel extends JPanel {
    public final WorldFrame worldFrame;
    private int canvasWidth;
    private int canvasHeight;
    private BufferedImage image;
    private Graphics2D offscreen; //the offscreen graphics

    public DrawingPanel(WorldFrame frame) {
        this.worldFrame = frame;
//        addListener();
        init(768, 768);
        createOffScreenImage();
    }

    public void createOffScreenImage() {
        try {
            image = ImageIO.read(new File("map2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        offscreen = image.createGraphics();
    }

    final void init(int canvasHeight, int canvasWidth) {
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;
        setPreferredSize(new Dimension(canvasWidth + 20, canvasHeight + 20));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        paintCities();
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(image, 10, 10, this);
        graphics2D.dispose();
        super.paintComponent(graphics);
    }

    private void paintCities() {
        offscreen.setColor(new Color(255, 255, 255));
        EllipticalMercator ellipticalMercator = new EllipticalMercator();
        double xMax = 20037508.34;
        double yMax = 20037508.34;
        for (City capital : worldFrame.getCapitals()) { //(-20037508.34, -23810769.32, 20037508.34, 23810769.32).
            double x = ellipticalMercator.xAxisProjection(capital.getLongitude());
            double y = ellipticalMercator.yAxisProjection(capital.getLatitude());
            x = xMax + x;
            y = yMax - y;
//            offscreen.fillOval(10 + (int) ((x * canvasWidth) / (2 * xMax)), 10 + (int) ((y * canvasHeight) / (2 * yMax)), 7, 7);
            offscreen.setFont(new Font(this.getFont().getName(), Font.PLAIN, 10));
            offscreen.drawString(capital.getName(), (int) ((x * canvasWidth) / (2 * xMax)), (int) ((y * canvasHeight) / (2 *
                    yMax)));
        }
    }

    private void addListener() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                init(worldFrame.canvas.getHeight(), worldFrame.canvas.getWidth());
                createOffScreenImage();
                repaint();
            }
        });
    }

}
