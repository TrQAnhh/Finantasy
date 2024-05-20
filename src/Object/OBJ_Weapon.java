package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Weapon extends Entity {

    public OBJ_Weapon(GamePanel gamePanel) {
        super(gamePanel);
        name = "Sword";
        itemType = 1;
        down1 = setup("/object/sword_normal",gamePanel.tileSize,gamePanel.tileSize);
        attackValue = 1;
        description = "[" + name + "]\nAn old sword.";
    }
    
}
