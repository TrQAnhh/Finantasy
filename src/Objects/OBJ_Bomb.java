package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Bomb extends Entity {

    GamePanel gamePanel;
    public int value = 5;

    public OBJ_Bomb (GamePanel gamePanel){

        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Bomb";
        type = type_consumable_enemy;
        itemsImage = setupItemImages("/Objects/Bomb");
        description = "[" + name + "]\n- Deal " + value + "DMG to your enemy.";
        price = 30;
        stackable = true;
    }
    // Ability
    public void use(Entity entity){
        gamePanel.playSE(10);
        entity.life -= value;
        if(gamePanel.gameState == gamePanel.battleState || gamePanel.gameState == gamePanel.bossBattleState){
            gamePanel.ui.addMessage(value + " damage!");
        }
        entity.state = entity.burningState;
    }
}