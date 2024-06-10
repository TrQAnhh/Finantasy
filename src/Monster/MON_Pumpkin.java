package Monster;

import Entities.Entity;
import Main.GamePanel;

public class MON_Pumpkin extends Entity {

    GamePanel gamePanel;

    public MON_Pumpkin(GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Pumpkin";
        direction = "down";
        maxLife = 10;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
        coin = 10;
        mana = 0;
        maxMana = 3;
        state = normalState;
        preState = state;
        dying = false;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){

        up1 = setup("Monster/Pumpkin/up_1");
        down1 = setup("Monster/Pumpkin/down_1");
        left1 = setup("Monster/Pumpkin/left_1");
        right1 = setup("Monster/Pumpkin/right_1");
    }   
    public void setAction(){

    }
    public void damage(Entity entity){

            entity.state = entity.getDamageState;
            int damage = attack - entity.defense;
            if(damage <= 0){
                damage = 0;
                gamePanel.ui.orderTurn++;
            }
            else{
                entity.life -= damage;
                gamePanel.ui.addMessage(damage + " damage!");
            }
        }
}
