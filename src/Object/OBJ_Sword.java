package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Sword extends Entity {

    public OBJ_Sword(GamePanel gamePanel) {
        super(gamePanel);
        type = type_sword;
        name = "Sword";
        itemType = 1;
        down1 = setup("/object/sword_normal",gamePanel.tileSize,gamePanel.tileSize);
        attackValue = 1;
        description = "[" + name + "]\nAn old sword.";
    }
    
}
