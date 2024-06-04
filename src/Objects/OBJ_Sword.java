package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Sword extends Entity {

    public OBJ_Sword(GamePanel gamePanel) {
        super(gamePanel);
        type = type_sword;
        name = "Sword";
        itemsImage = setupItemImages("Objects/Sword");
        attackValue = 1;
        description = "[" + name + "]" + " \n- An old sword to protect yourself." + " \n- Hahahaha";
    }
    
}
