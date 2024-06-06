package Monster;

import Entities.Entity;
import Main.GamePanel;

public class MON_FlameDog extends Entity {

    GamePanel gamePanel;

    public MON_FlameDog(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Flame Dog";
        direction = "down";
        maxLife = 4;
        life = maxLife;
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

        up1 = setup("Monster/Dog/stand");
        down1 = setup("Monster/Dog/stand");
        left1 = setup("Monster/Dog/attack");
        right1 = setup("Monster/Dog/down");
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
