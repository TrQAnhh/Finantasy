package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Silver_Shield extends Entity {

    public OBJ_Silver_Shield(GamePanel gp) {
        super(gp);
        type = type_shield;
        name = "Wood Armor";
        itemsImage = setupItemImages("Objects/Shield");
        defenseValue = 1;
        description = "[" + name + "]\nAn old shield.";
    }
    
}
