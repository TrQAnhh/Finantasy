package Monster;

import java.awt.Graphics2D;

import Entities.Entity;
import Main.GamePanel;

public class MON_Robot extends Entity implements MonsterInt<Graphics2D, GamePanel>{

    GamePanel gamePanel;

    public MON_Robot(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Robot";
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

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){
        up1 = setup("Monster/Robot/stand");
        down1 = setup("Monster/Robot/down");
        left1 = setup("Monster/Robot/attack");
        right1 = setup("Monster/Robot/right");
    }   
    @Override
    public void setAction(){}
    public void damage(Entity entity){
        
        if(state == stunState){
            gamePanel.ui.addMessage(name + "Was Stunt");
        }
        else{
            if(state == bleedState){
                life--;
            }
            int damage = attack - entity.defense;
            if(damage <= 0){
                damage = 0;
                gamePanel.ui.orderTurn++;
            }
            else{
                entity.state = entity.getDamageState;
                entity.life -= damage;
                gamePanel.ui.addMessage(damage + " damage!");
            }
            
            
        }
    }
    @Override
    public void draw(Graphics2D g2,GamePanel gamePanel){}
    
}