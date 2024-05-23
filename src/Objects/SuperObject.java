package Objects;

import Main.GamePanel;
import Map.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    //DISPLAY OBJECTS:
        public void draw(Graphics2D graphics2D, GamePanel gamePanel){
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            graphics2D.drawImage(image,screenX,screenY,gamePanel.tileSize, gamePanel.tileSize,null);
        }
}
