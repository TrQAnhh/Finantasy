package Objects;

import java.util.Random;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_GreatSword extends Entity {

    GamePanel gamePanel;
    public OBJ_GreatSword(GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;
        type = type_greatsword;
        name = "A.Sword";
        itemsImage = setup("Objects/axe");
        attackValue = 2;
        description = "[" + name + "]\nAn old axe.";
        description = description + "\nATK: " + attackValue + "\nDEF: " + defenseValue;
    }
    @Override
    public void use(Entity entity){
        
        Random rand = new Random();
        int i = rand.nextInt(100)+1;
        if(i <= 50){
            entity.state = entity.stuntState;
        }
        gamePanel.ui.addMessage("Stunt");
    }
}
