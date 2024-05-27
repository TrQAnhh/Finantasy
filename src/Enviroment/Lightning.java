package Enviroment;


import Main.GamePanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lightning {
    GamePanel gamePanel;
    BufferedImage darknessFilter;
    int dayCounter;
    float filterAlpha = 0f;

    // Date state value
    final int day = 0;
    final int dusk = 1;
    final int night = 2;
    final int dawn = 3;
    int dayState = day;
    int circleSize = 600;

    public Lightning(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setLightSource();
    }
    public void setLightSource() {
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
        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);
        // Create a light circle area
        Area lightArea = new Area(circleShape);

        // Subtract the light circle from the screen rectangle
        screenArea.subtract(lightArea);

        // Create a gradation effect within the light circle
        Color color[] = new Color[12];
        float fraction[] = new float[12];

        color[0] = new Color(0, 0, 0, 0.1f);
        color[1] = new Color(0, 0, 0, 0.42f);
        color[2] = new Color(0, 0, 0, 0.52f);
        color[3] = new Color(0, 0, 0, 0.61f);
        color[4] = new Color(0, 0, 0, 0.69f);
        color[5] = new Color(0, 0, 0, 0.76f);
        color[6] = new Color(0, 0, 0, 0.82f);
        color[7] = new Color(0, 0, 0, 0.87f);
        color[8] = new Color(0, 0, 0, 0.91f);
        color[9] = new Color(0, 0, 0, 0.94f);
        color[10] = new Color(0, 0, 0, 0.96f);
        color[11] = new Color(0, 0, 0, 0.98f);

        fraction[0] = 0f;
        fraction[1] = 0.4f;
        fraction[2] = 0.5f;
        fraction[3] = 0.6f;
        fraction[4] = 0.65f;
        fraction[5] = 0.7f;
        fraction[6] = 0.75f;
        fraction[7] = 0.8f;
        fraction[8] = 0.85f;
        fraction[9] = 0.9f;
        fraction[10] = 0.95f;
        fraction[11] = 1f;

        // Create a gradation paint settings for the light circle
        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (circleSize / 2), fraction, color);

        // Set the gradian data on gracphics
        g2.setPaint(gPaint);

        // Draw the light circle
        g2.fill(lightArea);

        // Draw the screen rectangle without the light circle area
        g2.fill(screenArea);

        g2.dispose();
    }
    public void update() {
        // Check the state of day
        if(dayState == day) {
            dayCounter ++;
            if(dayCounter > 10) {
                dayState = dusk;
                dayCounter = 0;
            }
        }

        if(dayState == dusk) {
            filterAlpha += 0.001f;

            if(filterAlpha > 1f) {
                filterAlpha = 1f;
                dayState = night;
            }
        }
        if(dayState == night) {
            dayCounter ++;
            if(dayCounter > 10) {
                dayState = dawn;
                dayCounter = 0;
            }
        }

        if(dayState == dawn) {
            filterAlpha -= 0.001f;

            if(filterAlpha < 0f) {
                filterAlpha = 0;
                dayState = day;
            }
        }
    }
    public void draw(Graphics2D g2) {

        if(gamePanel.currentArea == gamePanel.outside) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        }   
        if(gamePanel.currentArea == gamePanel.outside || gamePanel.currentArea == gamePanel.dungeon) {
            g2.drawImage(darknessFilter, 0, 0, null);
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


        }
    }
