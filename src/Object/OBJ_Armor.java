package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Armor extends Entity {

    public OBJ_Armor(GamePanel gamePanel) {
        super(gamePanel);
        
        name = "Iron Armor";
        type = type_shield;
        down1 = setup("Objects/Armor2");
        defenseValue = 1;
        description = "[" + name + "]\nAn advanced shield.";
        description = description + "\nATK: " + attackValue + "\nDEF: " + defenseValue;
    }
    
}
