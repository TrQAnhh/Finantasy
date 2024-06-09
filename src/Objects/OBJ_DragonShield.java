package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_DragonShield extends Entity {

    public OBJ_DragonShield(GamePanel gp) {
        super(gp);
        type = type_shield;
        name = "Pridwen";
        itemsImage = setupItemImages("Objects/DragonShield");
        defenseValue = 2;
        description = "[" + name + "]" + " \n- King Arthur's Shield" + " \n- Defense: " + defenseValue + "\n- Can only be equipped in Battle World";
        price = 75;
    }
    @Override
    public void use(Entity entity){
        entity.defense = 120*entity.defense/100;
    }

}
