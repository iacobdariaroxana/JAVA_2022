package network;

import org.jfree.svg.SVGGraphics2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StoreMapSVG {
    public static void storeMap(Path filePath, SocialNetwork socialNetwork) {
        BufferedImage bufferedImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
        SVGGraphics2D svgGraphics2D = new SVGGraphics2D(bufferedImage.getWidth(), bufferedImage.getHeight());
        try {
            svgGraphics2D.drawImage(bufferedImage, 0, 0, null);
            //a=350, b=350, R=300
//            svgGraphics2D.drawOval(50, 50, 650, 650);
            int numberOfUsers = socialNetwork.getUsers().size();

            Random random = new Random();
            Map<User, List<Integer>> userMap = new HashMap<>();
            int x, y;
            svgGraphics2D.setColor(Color.BLUE);
            svgGraphics2D.setFont(new Font("SansSerif", Font.BOLD, 20));
            for (User user : socialNetwork.getUsers()) {
                x = random.nextInt(700) + 50;
                y = random.nextInt(700) + 50;
                svgGraphics2D.fillOval(x, y, 20, 20);
                svgGraphics2D.drawString(user.getName(), x, y - 20);
                userMap.put(user, List.of(x, y));
            }
            svgGraphics2D.setColor(Color.BLACK);
            svgGraphics2D.setStroke(new BasicStroke(3) );
            for (Map.Entry<User, List<Integer>> entry : userMap.entrySet()) {
                for (User friend : entry.getKey().getFriends()) {
                    svgGraphics2D.drawLine(entry.getValue().get(0) + 10, entry.getValue().get(1) + 10,
                            userMap.get(friend).get(0) + 10, userMap.get(friend).get(1) + 10);
                }
            }

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

    }
}
