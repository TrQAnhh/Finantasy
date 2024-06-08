package Objects;

import java.util.Random;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_GoldSword extends Entity {

    GamePanel gamePanel;

    public OBJ_GoldSword(GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;
        type = type_sword;
        name = "Touyako";
        itemsImage = setup("Objects/GoldSword");
        attackValue = 4;
        description = "[" + name + "]" + " \n- Sakata Gintoki's Bokuto." + " \n- Attack: " + attackValue + "\n- Can only be equipped in Battle World";
    }
    @Override
    public void use(Entity entity){
        
        Random rand = new Random();
        int i = rand.nextInt(100)+1;
        if(i <= 30){
            entity.state = entity.criticalState;
        }
        gamePanel.ui.addMessage("Stunt");
    }
}
