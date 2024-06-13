package Monster;

import java.awt.Graphics2D;

import Entities.Entity;
import Main.GamePanel;

public class MON_Pumpkin extends Entity implements MonsterInt<Graphics2D, GamePanel>{

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

        up1 = setupMonsterImages("Monster/Pumpkin/up_1",gamePanel.tileSize + 18,gamePanel.tileSize + 32);
        down1 = setupMonsterImages("Monster/Pumpkin/down_1",gamePanel.tileSize + 18,gamePanel.tileSize + 32);
        left1 = setupMonsterImages("Monster/Pumpkin/left_1",gamePanel.tileSize + 18,gamePanel.tileSize + 32);
        right1 = setupMonsterImages("Monster/Pumpkin/right_1",gamePanel.tileSize + 18,gamePanel.tileSize + 32);
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
    public void draw(Graphics2D t, GamePanel u) {}

}