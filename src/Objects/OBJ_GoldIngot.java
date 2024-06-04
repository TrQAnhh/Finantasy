package Objects;

import Entities.Entity;
import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_GoldIngot extends Entity {
    public OBJ_GoldIngot(GamePanel gamePanel){

        super(gamePanel);
        name = "Gold ingot";
        itemsImage = setupItemImages("/Objects/GoldIngot");
        description = "- Used to trade with villagers";
    }
    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        BufferedImage image = itemsImage;

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

    }

}
