package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Sword extends Entity {

    public OBJ_Sword(GamePanel gamePanel) {
        super(gamePanel);
        type = type_sword;
        name = "Ion Sword";
        itemsImage = setupItemImages("Objects/Sword");
        attackValue = 1;
        description = "[" + name + "]" + " \n- An ion sword" + " \n- Attack: " + attackValue + "\n- Useless in normal world";
    }
    
}
