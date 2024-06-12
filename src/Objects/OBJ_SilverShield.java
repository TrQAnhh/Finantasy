package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_SilverShield extends Entity {

    public OBJ_SilverShield(GamePanel gp) {
        super(gp);
        type = type_shield;
        name = "The Legendary";
        itemsImage = setupItemImages("Objects/SilverShield");
        defenseValue = 2;
        description = "[" + name + "]" + " \n- Naofumi Iwatani's Legendary Shield" + " \n- Defense: " + defenseValue + "\n- [Ability] Gain 20% base Defense";
        price = 25;
    }
    @Override
    public void use(Entity entity){
        entity.defense = 120*entity.defense/100;
    }

}
