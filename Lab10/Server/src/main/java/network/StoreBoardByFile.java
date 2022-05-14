package network;

import org.jfree.svg.SVGGraphics2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StoreBoardByFile {
    public static void storeBoard(Path filePath) {
        BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        SVGGraphics2D svgGraphics2D = new SVGGraphics2D(bufferedImage.getWidth(), bufferedImage.getHeight());
        try {
            svgGraphics2D.drawImage(bufferedImage, 0, 0, null);
            svgGraphics2D.drawOval(10,10,400,400);
            String fileContent = svgGraphics2D.getSVGDocument();
            try (FileWriter writer = new FileWriter(filePath.toFile())) {
                writer.write(fileContent);
            } catch (Exception e) {
                System.err.println("Error: cannot save svg: " + e.getMessage());
            }
        } finally {
            svgGraphics2D.dispose();
        }
    }

    public static void main(String[] args) {
        storeBoard(Paths.get("src/main/test.svg"));
    }
}
