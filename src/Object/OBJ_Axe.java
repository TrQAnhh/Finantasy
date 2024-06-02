package Object;

import java.util.Random;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Axe extends Entity {

    public OBJ_Axe(GamePanel gamePanel) {
        super(gamePanel);
        
        type = type_axe;
        name = "Axe";
        down1 = setup("Objects/axe");
        defenseValue = 1;
        description = "[" + name + "]\nAn old axe.";
    }
    @Override
    public void use(Entity entity){
        
        Random rand = new Random();
        int i = rand.nextInt(100)+1;
        if(i <= 50){
            entity.state = entity.stuntState;
        }
    }
}
