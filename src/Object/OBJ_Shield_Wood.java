package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Shield_Wood extends Entity {

    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);
        type = type_shield;
        name = "Wood Armor";
        down1 = setup("Objects/Shield");
        defenseValue = 1;
        description = "[" + name + "]\nAn old shield.";
        price = 75;
    }
    
}
