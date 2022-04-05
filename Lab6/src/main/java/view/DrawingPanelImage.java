package view;

import controller.Controller;
import controller.GameUtil;
import model.Node;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawingPanelImage extends JPanel {
    final MainFrame frame;
    private int rows;
    private int cols;
    private int canvasWidth = 400;
    private int canvasHeight = 400;
    private int boardWidth;
    private int boardHeight;
    private int cellWidth;
    private int cellHeight;
    private int padX;
    private int padY;
    private final int stoneSize = 20;
    private BufferedImage image; //the offscreen image
    private Graphics2D offscreen; //the offscreen graphics
    private Controller controller;

    public DrawingPanelImage(MainFrame frame, Controller controller) {
        this.frame = frame;
        this.setController(controller);
        createOffScreenImage();
        init(frame.configPanel.getRows(), frame.configPanel.getCols());
        controller.deleteNodes();
        paintGrid();
        paintSticks();
        addListener();
    }

    public void createOffScreenImage() {
        image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        offscreen = getImage().createGraphics();
        offscreen.setColor(new Color(255, 255, 254)); //fill the image with white
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    final void init(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;

        offscreen.setColor(new Color(255, 255, 254));
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(getImage(), 0, 0, this);
        graphics2D.dispose();
    }

    public void paintGrid() {
        offscreen.setColor(new Color(209, 209, 233));


        offscreen.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + boardWidth;
            offscreen.drawLine(x1, y1, x2, y1);
        }

        for (int col = 0; col < cols; col++) {
            int x1 = padX + col * cellWidth;
            int y1 = padY;
            int y2 = padY + boardHeight;
            offscreen.drawLine(x1, y1, x1, y2);
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                offscreen.setColor(Color.LIGHT_GRAY);
                offscreen.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }
    }

    private boolean getRandomBoolean(float probability) {
        double randomValue = Math.random()*100;  //0.0 to 99.9
        return randomValue <= probability;
    }

    public void paintSticks() {
        offscreen.setColor(new Color(43, 44, 52));
        offscreen.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols - 1; col++) {
                if (getRandomBoolean(60)) {
                    int x1 = padX + col * cellWidth;
                    int y1 = padY + row * cellHeight;
                    int x2 = x1 + cellWidth;
                    offscreen.drawLine(x1, y1, x2, y1);
                    controller.createLink(x1, y1, x2, y1);
                }
            }
        }

        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows - 1; row++) {
                if (getRandomBoolean(60)) {
                    int x1 = padX + col * cellWidth;
                    int y1 = padY + row * cellHeight;
                    int y2 = y1 + cellHeight;
                    offscreen.drawLine(x1, y1, x1, y2);
                    controller.createLink(x1, y1, x1, y2);
                }
            }
        }
    }

    private void setCanvasSize(int width, int height) {
        this.canvasWidth = width;
        this.canvasHeight = height;
    }

    private void addListener() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setCanvasSize(frame.getCanvas().getWidth(), frame.getCanvas().getHeight());
                getController().deleteNodes();
                getController().setFlagStart(true);
                init(rows, cols);
                createOffScreenImage();
                paintGrid();
                paintSticks();
/*                createOffScreenImage();
                init(rows, cols);
                new GameUtil().save(controller, "game.bin");
                Controller restoredController = new GameUtil().load("game.bin");
                frame.updateController(restoredController);
                replaceImage();*/
                repaint();
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int code = getController().validate(e.getX(), e.getY());
                if (code == 1) {
                    drawStone(getController().getClickedNode().getX(), getController().getClickedNode().getY(), getController().getColor());
                    repaint();
                } else if (code == 0) {
                    System.out.println("Not a node!");
                } else if (code == 2) {
                    System.out.println("Not a neighbor of previous selected node!");
                } else if (code == 3 || code == 4) {
                    drawFinal(code);
                }
            }
        });
    }

    public void drawStone(int x, int y, Color color) {
        offscreen.setColor(color);
        offscreen.fillOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
    }

    public void drawFinal(int code) {
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
        offscreen.setColor(new Color(43, 44, 52));
        offscreen.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        offscreen.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        offscreen.drawString("WINNER", (float) (0.4 * boardWidth), (float) (0.25 * boardHeight));
        if (code == 3)
            offscreen.setColor(new Color(239, 69, 101));
        else
            offscreen.setColor(new Color(98, 70, 234));
        offscreen.fillOval(boardWidth / 2 - 50, boardHeight / 2 - 50, 150, 150);
        repaint();
        Timer timer = new Timer(1300, event -> {
            getController().deleteNodes();
            getController().setFlagStart(true);
            init(rows, cols);
            paintGrid();
            paintSticks();
            repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void saveCurrentStateToPng() {
        try {
            ImageIO.write(image, "png", new File("game-board.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void replaceImage() {
        offscreen.setColor(new Color(255, 255, 254)); //fill the image with white
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid();
        for(Node node:controller.getNodes()){
            for(Node neighbor:node.getNeighbors()){
                offscreen.setColor(new Color(43, 44, 52));
                offscreen.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                offscreen.drawLine(node.getX(), node.getY(), neighbor.getX(), neighbor.getY());
            }
        }
        for(Node node:controller.getClickedNodes().keySet()){
            drawStone(node.getX(), node.getY(), controller.getClickedNodes().get(node));
        }
        repaint();
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
