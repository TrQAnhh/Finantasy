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
        down1 = setup("/Object/key",gamePanel.tileSize, gamePanel.tileSize);
        description = "[" + name + "]\nIt is used for healing.";
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