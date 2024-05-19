package Enviroment;


import Main.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lightning {
     GamePanel gamePanel;
    BufferedImage darknessFilter;

    public Lightning(GamePanel gamePanel, int circleSize) { 
        // Create a buffered image
        darknessFilter = new BufferedImage(gamePanel.screenWidth, gamePanel.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
        // Create a screen -sized rectangle area
        Area screenArea = new Area(new Rectangle2D.Double(0, 0, gamePanel.screenWidth, gamePanel.screenHeight));
        // Get the top left x and y of the light circle
        int centerX = gamePanel.player.screenX + (gamePanel.tileSize) / 2;
        int centerY = gamePanel.player.screenY + (gamePanel. tileSize) / 2;
        // Get the light circle shape
        double x = centerX - (circleSize / 2);
        double y = centerY - (circleSize / 2);
        // Create a light circle shape
        Double circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);
        // Create a light circle area
        Area lightArea = new Area(circleShape);

        // Subtract the light circle from the screen rectangle
        screenArea.subtract(lightArea);
        // Set a color (black) to draw the rectangle
        g2.setColor(new Color(0, 0, 0, 0.95f));

        // Draw the screen rectangle without the light circle area
        g2.fill(circleShape);

        g2.dispose();
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(darknessFilter, 0, 0, null);
    }
}
