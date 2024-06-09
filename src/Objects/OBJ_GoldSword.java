package Objects;

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
        price = 20;
    }
    @Override
    public void use(Entity entity){

        if(130*gamePanel.player.attack/100 > entity.defense){
            gamePanel.player.attack = 130*gamePanel.player.attack/100;
            entity.state = criticalState;
        }
    }
}
