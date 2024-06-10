package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_GoldenShield extends Entity {

    public OBJ_GoldenShield(GamePanel gp) {
        super(gp);
        type = type_shield;
        name = "Aegis";
        itemsImage = setupItemImages("Objects/GoldShield");
        defenseValue = 2;
        description = "[" + name + "]" + " \n- A popular recurring item in Finantasy series." + " \n- Defense: " + defenseValue + "\n- Useless in normal world";
        price = 50;
    }
    @Override
    public void use(Entity entity){
        entity.defense = 130*entity.defense/100;
    }

}
