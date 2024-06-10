package Monster;

import Entities.Entity;
import Main.GamePanel;

public class MON_GhostRider extends Entity {

    GamePanel gamePanel;

    public MON_GhostRider(GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Ghost Rider";
        direction = "down";
        maxLife = 20;
        life = maxLife;
        attack = 3;
        defense = 2;
        exp = 2;
        coin = 10;
        mana = 0;
        maxMana = 3;
        state = normalState;
        preState = state;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){

        up1 = setup("Monster/HatHatPumpkinWithHorseWithHorse/up_2");
        down1 = setup("Monster/HatPumpkinWithHorse/down_2");
        left1 = setup("Monster/HatPumpkinWithHorse/left_2");
        right1 = setup("Monster/HatPumpkinWithHorse/right_2");
    }   
    public void setAction(){

    }
    public void damage(Entity entity){

        if(mana >= maxMana){
            String text = "";
            state = healingState;
            life += 5;
            mana = 0;
            text = "Healing";
            if(life > maxLife){
                maxLife *= 2;
                defense += 2;
                state = defenseState;
                text = "Increase Defense";
            }
            gamePanel.ui.addMessage(text);
        }
        else{
            entity.state = entity.getDamageState;
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
