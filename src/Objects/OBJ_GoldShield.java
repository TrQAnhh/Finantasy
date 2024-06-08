package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_GoldShield extends Entity {

    public OBJ_GoldShield(GamePanel gp) {
        super(gp);
        type = type_shield;
        name = "Aegis";
        itemsImage = setupItemImages("Objects/GoldShield");
        defenseValue = 2;
        description = "[" + name + "]" + " \n- A popular recurring item in Final Fantasy series." + " \n- Defense: " + defenseValue + "\n- Can only be equipped in Battle World";

    }
    @Override
    public void use(Entity entity){
        entity.defense = 120*entity.defense/100;
    }

}
