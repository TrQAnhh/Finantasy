package Objects;

import java.util.Random;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Dagger extends Entity {

    GamePanel gamePanel;
    public OBJ_Dagger(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        type = type_sword;
        name = "Dagger";
        itemsImage = setup("Objects/Dagger");
        attackValue = 3;
        description = "[" + name + "]" + " \n- A Dagger." + " \n- Attack: " + attackValue + "\n- Can only be equipped in Battle World";
        price = 40;
    }
    @Override
    public void use(Entity entity){
        Random rand = new Random();
        int i = rand.nextInt(100)+1;
        if(i <= 30){
            entity.state = bleedState;
        }
        gamePanel.ui.addMessage("Bleed");
    }
}

