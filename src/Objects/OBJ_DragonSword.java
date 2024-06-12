package Objects;

import java.util.Random;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_DragonSword extends Entity {

    GamePanel gamePanel;
    public OBJ_DragonSword(GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;
        type = type_sword;
        name = "Excalibur";
        itemsImage = setupItemImages("Objects/DragonSword");
        attackValue = 3;
        description = "[" + name + "]" + " \n- King Arthur's Legendary Sword." + " \n- Attack: " + attackValue + "\n- Useless in normal world";
        price = 75;
    }
    @Override
    public void use(Entity entity){
        gamePanel.playSE(11);
        Random rand = new Random();
        int i = rand.nextInt(100)+1;
        if(i <= 50){
            entity.state = entity.stuntState;
        }
        gamePanel.ui.addMessage("Stunt");
    }
}
