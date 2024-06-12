package Objects;

import Entities.Entity;
import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Barrels extends Entity {
    GamePanel gamePanel;
    public OBJ_Barrels(GamePanel gamePanel){
        super(gamePanel);
        this.gamePanel = gamePanel;

        solidArea = new Rectangle(0,20,48,40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        type = type_barrel;
        name = "Barrels";
        objectImage1 = setupObjectImages("/Objects/barrel",48,96);
    }
    @Override
    public void use(Entity entity){
        gamePanel.playSE(7);
        gamePanel.player.coin++;
        gamePanel.ui.addMessage("Got a coin");
    }
    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        BufferedImage image = objectImage1;

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        g2.drawImage(image, screenX, screenY, null);
    }
}
