package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_DragonShield extends Entity {

    public OBJ_DragonShield(GamePanel gp) {
        super(gp);
        type = type_shield;
        name = "Pridwen";
        itemsImage = setupItemImages("Objects/DragonShield");
        defenseValue = 3;
        description = "[" + name + "]" + " \n- King Arthur's Shield" + " \n- Defense: " + defenseValue + "\n- Useless in normal world";
        price = 75;
    }
    @Override
    public void use(Entity entity){
        entity.defense = 150*entity.defense/100;
    }

}
