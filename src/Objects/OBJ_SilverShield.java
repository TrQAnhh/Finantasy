package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_SilverShield extends Entity {

    public OBJ_SilverShield(GamePanel gp) {
        super(gp);
        type = type_shield;
        name = "Hylian";
        itemsImage = setupItemImages("Objects/SilverShield");
        defenseValue = 1;
        description = "[" + name + "]" + " \n- A traditional Shield borne by the Knights of Hyrule" + " \n- Defense: " + defenseValue + "\n- Can only be equipped in Battle World";

    }
    
}
