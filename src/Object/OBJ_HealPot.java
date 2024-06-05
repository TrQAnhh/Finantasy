package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_HealPot extends Entity {

    GamePanel gamePanel;
    int value = 5;

    public OBJ_HealPot(GamePanel gamePanel){

        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Healing Pot";
        type = type_consumable_player;
        down1 = setup("Objects/Healing");
        description = "[" + name + "]\nIt is used for healing.";
        price = 10;
    }
    public void use(Entity entity){
        entity.life += value;
        if(entity.life > entity.maxLife){
            entity.life = entity.maxLife;
        }
        if(gamePanel.gameState == gamePanel.battleState){
            gamePanel.ui.addMessage("Heal" + value);
        }
        else{
            gamePanel.gameState = gamePanel.dialogueState;
            gamePanel.ui.currentDialogue = "You drink the "+name+"!\nYour life has been recovered by "+value+".";
            // gamePanel.playSE(2)
        }
        entity.state = entity.healingState;
    }
}