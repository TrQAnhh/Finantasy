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
        maxLife = 50;
        life = maxLife;
        attack = 5;
        defense = 8;
        exp = 10;
        coin = 20;
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
        up1 = setupMonsterImages("Monster/Robot/stand",gamePanel.tileSize + 100, gamePanel.tileSize + 80);
        down1 = setupMonsterImages("Monster/Robot/down",gamePanel.tileSize + 100, gamePanel.tileSize + 80);
        left1 = setupMonsterImages("Monster/Robot/attack",gamePanel.tileSize + 100, gamePanel.tileSize + 80);
        right1 = setupMonsterImages("Monster/Robot/right",gamePanel.tileSize + 100, gamePanel.tileSize + 80);
    }   
    @Override
    public void setAction(){}
    public void damage(Entity entity){
        
            entity.state = entity.getDamageState;
            int damage = attack - entity.defense;
            if(damage <= 0){
                damage = 0;
            }
            else{
                entity.life -= damage;
                gamePanel.ui.addMessage(damage + " damage!");
            }
    }
    @Override
    public void draw(Graphics2D g2,GamePanel gamePanel){}
    
}
