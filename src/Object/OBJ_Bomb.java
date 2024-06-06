package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Bomb extends Entity {

    GamePanel gamePanel;
    int value = 5;

    public OBJ_Bomb (GamePanel gamePanel){

        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Bomb";
        type = type_consumable_enemy;
        down1 = setup("/Objects/key");
        description = "[" + name + "]\nDeal " + value + "DMG to your enemy.";
        price = 75;
        stackable = true;
    }
    // Ability
    public void use(Entity entity){
        entity.life -= value;
        if(gamePanel.gameState == gamePanel.battleState){
            gamePanel.ui.addMessage(value + " damage!");
        }
        entity.state = entity.burningState;
    }
}