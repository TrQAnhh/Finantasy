package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_WoodenShield extends Entity {

    public OBJ_WoodenShield(GamePanel gp) {
        super(gp);
        type = type_shield;
        name = "Aspis";
        itemsImage = setupItemImages("Objects/WoodenShield");
        defenseValue = 1;
        description = "[" + name + "]" + " \n- A wooden Greek shield" + " \n- Defense: " + defenseValue + "\n- Can only be equipped in Battle World";

    }
    
}
