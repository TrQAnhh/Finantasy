package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Armor extends Entity {

    public OBJ_Armor(GamePanel gamePanel) {
        super(gamePanel);
        
        name = "Iron Armor";
        type = type_shield;
        down1 = setup("/Object/axe", gamePanel.tileSize, gamePanel.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nAn old axe.";
    }
    
}
