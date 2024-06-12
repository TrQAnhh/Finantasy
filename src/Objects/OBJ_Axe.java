package Objects;

import Entities.Entity;
import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Axe extends Entity {

    public OBJ_Axe(GamePanel gamePanel) {
        super(gamePanel);
        type = type_axe;
        name = "Axe";
        itemsImage = setupItemImages("/Objects/axe");
        attackValue = 0;
        description = "[" + name + "]" + " \n- An ion axe" + " \n- Can only be used to break barrels" + "\n- Can only be equipped in Normal World";

    }
    @Override
    public void use(Entity entity){
        entity.state = entity.stunState;
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        BufferedImage image = itemsImage;

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        g2.drawImage(image, screenX, screenY, null);
    }
}
