package Monster;

import java.awt.Graphics2D;

import Entities.Entity;
import Main.GamePanel;

public class MON_GreenDragon extends Entity implements MonsterInt<Graphics2D, GamePanel>{

    GamePanel gamePanel;

    public MON_GreenDragon(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Earth Dragon";
        direction = "down";
        maxLife = 4;
        life = maxLife;
        attack = 2;
        defense = 5;
        exp = 2;
        mana = 0;
        maxMana = 2;
        state = normalState;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        Defeat = false;
        getImage();
    }
    public void getImage(){
        
        up1 = setup("Monster/BossMonster/Dragon_1");
        down1 = setup("Monster/BossMonster/Dragon_1");
        left1 = setup("Monster/BossMonster/Dragon_1");
        right1 = setup("Monster/BossMonster/Dragon_1");
    }   
    @Override
    public void setAction(){}
    @Override
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
    
    @Override
    public void draw(Graphics2D g2,GamePanel gamePanel) {}
}
