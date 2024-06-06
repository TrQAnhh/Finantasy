package Monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Data.Progress;
import Entities.Entity;
import Main.GamePanel;

public class MON_Boss extends Entity {

    GamePanel gamePanel;

    public MON_Boss(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Dragon Lord";
        direction = "down";
        maxLife = 1;
        life = maxLife;
        attack = 8;
        defense = 0;
        exp = 2;
        mana = 0;
        maxMana = 0;
        state = normalState;

        int size = gamePanel.tileSize * 5;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = size - 48 * 2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        Defeat = false;
        getImage();
    }
    //change later 
    public void getImage(){

        up1 = setup("Monster/BossMonster/Dragon");
        down1 = setup("Monster/BossMonster/Dragon");
        left1 = setup("Monster/BossMonster/Dragon");
        right1 = setup("Monster/BossMonster/Dragon");
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
            
            if(Defeat == false) {
                g2.drawImage(image, screenX, screenY, gamePanel.tileSize + 300, gamePanel.tileSize + 300, null);
                g2.drawImage(image, screenX, screenY, gamePanel.tileSize + 300, gamePanel.tileSize + 300, null);
            }
        }
        @Override
        public void checkDrop() {
            gamePanel.bossBattleOn = false;
            Progress.DragonBossDefeated = true;
        }
    }

