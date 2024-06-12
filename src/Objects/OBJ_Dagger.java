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
        itemsImage = setupItemImages("Objects/Dagger");
        attackValue = 3;
        description = "[" + name + "]" + " \n- A Dagger." + " \n- Attack: " + attackValue + "\n- [Ability] Inflict Bleeding effect to the enemy";
        price = 40;
    }
    @Override
    public void use(Entity entity){
        gamePanel.playSE(9);
        Random rand = new Random();
        int i = rand.nextInt(100)+1;
        if(i <= 30){
            entity.state = bleedState;
        }
        gamePanel.ui.addMessage("Bleed");
    }
}

