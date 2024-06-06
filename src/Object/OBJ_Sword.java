package Object;

import java.util.Random;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Sword extends Entity {

    public OBJ_Sword(GamePanel gamePanel) {
        super(gamePanel);
        type = type_sword;
        name = "Sword";
        down1 = setup("Objects/Sword");
        attackValue = 1;
        description = "[" + name + "]\nAn old sword.";
        description = description + "\nATK: " + attackValue + "\nDEF: " + defenseValue;
        price = 100;
    }
    @Override
    public void use(Entity entity){
        
    }
}
