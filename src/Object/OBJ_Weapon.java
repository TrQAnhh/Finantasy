package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Weapon extends Entity {
    public OBJ_Weapon(GamePanel gamePanel) {
        super(gamePanel);
        name = "Normal Sword";
        down1 = setup("/Object/sword_normal",gamePanel.tileSize,gamePanel.tileSize);
        attackValue = 1;
        description = "[" + name + "]\nAn old sword.";
    }
}
