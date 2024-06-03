package Monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Main.GamePanel;

public class MON_Spider extends Entity {

    GamePanel gamePanel;

    public MON_Spider(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Spider";
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

        getImage();
    }
    public void getImage(){
        
        up1 = setup("Monster/SPider/Spider");
        down1 = setup("Monster/SPider/Spider");
        left1 = setup("Monster/SPider/Spider");
        right1 = setup("Monster/SPider/Spider");
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
    
    @Override
        public void draw(Graphics2D g2,GamePanel gamePanel){
            BufferedImage image = up1;
    
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
    
            g2.drawImage(image, screenX, screenY, gamePanel.tileSize + 100, gamePanel.tileSize + 100, null);
        }
}
