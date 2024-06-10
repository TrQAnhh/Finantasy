package Monster;

import Entities.Entity;
import Main.GamePanel;

public class MON_Reaper extends Entity {

    GamePanel gamePanel;

    public MON_Reaper(GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Reaper";
        direction = "right";
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
        coin = 10;
        mana = 0;
        maxMana = 3;
        state = normalState;
        preState = normalState;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){

        up1 = setup("Monster/Reaper/up_1");
        down1 = setup("Monster/Reaper/down_1");
        left1 = setup("Monster/Reaper/left_1");
        right1 = setup("Monster/Reaper/right_1");
    }   
    public void setAction(){

    }
    public void damage(Entity entity){

        entity.state = entity.getDamageState;
        if(mana >= maxMana){
            entity.state = entity.bleedState;
            mana = 0;
            gamePanel.ui.addMessage("Bleeding");
        }
        else{
            int damage = attack - entity.defense;
            if(damage <= 0){
                damage = 0;
                gamePanel.ui.orderTurn++;
            }
            else{
                mana++;
                
                entity.life -= damage;
                gamePanel.ui.addMessage(damage + " damage!");
            }
        }
            
    }
}
