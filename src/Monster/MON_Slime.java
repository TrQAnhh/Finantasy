package Monster;

import Entities.Entity;
import Main.GamePanel;

public class MON_Slime extends Entity {

    GamePanel gamePanel;

    public MON_Slime(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Slime";
        direction = "down";
        maxLife = 4;
        life = maxLife;
        type = 2;
        attack = 5;
        defense = 0;
        exp = 2;
        mana = 0;
        maxMana = 3;
        state = normalState;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){

        up1 = setup("/monster/slime_down_1",gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/monster/slime_down_1",gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/monster/slime_down_2",gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/monster/slime_down_1",gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/monster/slime_down_1",gamePanel.tileSize, gamePanel.tileSize);
    }   
    public void setAction(){

    }
    public void damage(Entity entity){
        
        if(state == stuntState){
            gamePanel.ui.addMessage(name + "Was Stunt");
        }
        else{
            if(state == bleedState){
                life--;
            }
            int damage = attack - entity.defense;
            if(damage <= 0){
                damage = 0;
            }
            else{
                entity.state = entity.getDamageState;
            }
            entity.life -= damage;
            gamePanel.ui.addMessage(damage + " damage!");
            if(entity.life <= 0){
                entity.dying = true;
                gamePanel.ui.addMessage("You lose!");
            }
        }
    }
}
