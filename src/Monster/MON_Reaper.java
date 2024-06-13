package Monster;

import java.awt.Graphics2D;

import Entities.Entity;
import Main.GamePanel;

public class MON_Reaper extends Entity implements MonsterInt<Graphics2D, GamePanel>{

    GamePanel gamePanel;

    public MON_Reaper(GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Reaper";
        direction = "right";
        maxLife = 15;
        life = maxLife;
        attack = 5;
        defense = 2;
        exp = 5;
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

        up1 = setupMonsterImages("Monster/Reaper/up_1",gamePanel.tileSize + 24,gamePanel.tileSize + 32);
        down1 = setupMonsterImages("Monster/Reaper/down_1",gamePanel.tileSize + 24,gamePanel.tileSize + 32);
        left1 = setupMonsterImages("Monster/Reaper/right_1",gamePanel.tileSize + 24,gamePanel.tileSize + 32);
        right1 = setupMonsterImages("Monster/Reaper/right_1",gamePanel.tileSize + 24,gamePanel.tileSize + 32);
    }   
    @Override
    public void setAction(){}
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
            }
            else{
                mana++;
                entity.life -= damage;
                gamePanel.ui.addMessage(damage + " damage!");
            }
        }
            
    }
    @Override
    public void draw(Graphics2D t, GamePanel u) {}
    
}
