package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Armor extends Entity {

    public OBJ_Armor(GamePanel gamePanel) {
        super(gamePanel);
        
        name = "Iron Armor";
        itemType = 2;
        down1 = setup("/Object/axe", gamePanel.tileSize, gamePanel.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nAn old axe.";
    }
    
}
