package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Sword extends Entity {

    public OBJ_Sword(GamePanel gamePanel) {
        super(gamePanel);
        type = type_sword;
        name = "Sword";
        down1 = setup("/Object/sword_normal");
        attackValue = 1;
        description = "[" + name + "]\nAn old sword.";
    }
    
}
