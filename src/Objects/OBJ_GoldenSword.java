package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_GoldenSword extends Entity {

    GamePanel gamePanel;

    public OBJ_GoldenSword(GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;
        type = type_sword;
        name = "Touyako";
        itemsImage = setupItemImages("Objects/GoldSword");
        attackValue = 4;
        description = "[" + name + "]" + " \n- Sakata Gintoki's Bokuto." + " \n- Attack: " + attackValue + "\n- [Ability] Deal 200% Critical Damage to the enemy";
        price = 20;
    }
    @Override
    public void use(Entity entity){

        if(200*gamePanel.player.attack/100 > entity.defense){
            gamePanel.player.attack = 200*gamePanel.player.attack/100;
            entity.state = criticalState;
        }
    }
}
