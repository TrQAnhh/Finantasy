package Objects;

import Entities.Entity;
import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Chest extends Entity{
    GamePanel gamePanel;
    public OBJ_Chest(GamePanel gamePanel){

        super(gamePanel);
        this.gamePanel = gamePanel;

        type = type_chest;
        name = "Chest";
        objectImage1 = setupObjectImages("/Objects/chest",100,100);
    }

    @Override
    public void use(Entity entity){
        gamePanel.player.coin++;
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        BufferedImage image = objectImage1;

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

    }
}
