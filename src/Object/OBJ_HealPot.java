package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_HealPot extends Entity {

    GamePanel gamePanel;
    int value = 5;

    public OBJ_HealPot(GamePanel gamePanel){

        super(gamePanel);
        name = "Healing Pot";
        type = type_consumable_player;
        down1 = setup("/object/key",gamePanel.tileSize, gamePanel.tileSize);
        description = "[" + name + "]\nIt is used for healing.";
    }
    // Ability
    public void use(Entity entity){
        entity.life += value;
        if(gamePanel.player.life > gamePanel.player.maxLife){
            gamePanel.player.life = gamePanel.player.maxLife;
        }
        if(gamePanel.gameState == gamePanel.battleState){
            gamePanel.ui.addMessage("You drink the "+name+"!\nYour life has been recovered by "+value+".");
        }
        else{
            gamePanel.gameState = gamePanel.dialogueState;
            gamePanel.ui.currentDialogue = "You drink the "+name+"!\nYour life has been recovered by "+value+".";
            // gamePanel.playSE(2)
        }
    }
}