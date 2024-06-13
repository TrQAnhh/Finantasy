package Monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Main.GamePanel;

public class MON_Boss extends Entity implements MonsterInt<Graphics2D, GamePanel>{

    GamePanel gamePanel;
    BufferedImage animate1, animate2, animate3, animate4, animate5 , animate6;
    public MON_Boss(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Dragon Lord";
        direction = "down";
        maxLife = 50;
        life = maxLife;
        attack = 30;
        defense = 30;
        exp = 50;
        coin = 100;
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
        animate1 = setupMonsterImages("Monster/BossMonster/dragonBoss_1",gamePanel.tileSize * 6, gamePanel.tileSize * 6);
        animate2 = setupMonsterImages("Monster/BossMonster/dragonBoss_2",gamePanel.tileSize * 6, gamePanel.tileSize * 6);
        animate3 = setupMonsterImages("Monster/BossMonster/dragonBoss_3",gamePanel.tileSize * 6, gamePanel.tileSize * 6);
        animate4 = setupMonsterImages("Monster/BossMonster/dragonBoss_4",gamePanel.tileSize * 6, gamePanel.tileSize * 6);
        animate5 = setupMonsterImages("Monster/BossMonster/dragonBoss_5",gamePanel.tileSize * 6, gamePanel.tileSize * 6);
        animate6 = setupMonsterImages("Monster/BossMonster/dragonBoss_6",gamePanel.tileSize * 6, gamePanel.tileSize * 6);
    }   
    @Override
    public void setAction(){}
    @Override
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
    public void draw(Graphics2D g2,GamePanel gamePanel){

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if(Defeat == false) {
            g2.drawImage(getAnimationImages(), screenX, screenY, null);
        }
    }

    public BufferedImage getAnimationImages(){
        BufferedImage image = null;

        spriteCounter++;
        if ( spriteCounter > 12 ){
            if ( spriteNum == 1 ){
                spriteNum = 2;
            } else if ( spriteNum == 2 ){
                spriteNum = 3;
            } else if (spriteNum == 3 ){
                spriteNum = 4;
            } else if (spriteNum == 4 ){
                spriteNum = 5;
            } else if (spriteNum == 5 ){
                spriteNum = 6;
            } else if (spriteNum == 6 ){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if(spriteNum == 1) {
            image = animate1;
        }
        if(spriteNum == 2) {
            image = animate2;
        }
        if(spriteNum == 3) {
            image = animate3;
        }
        if(spriteNum == 4) {
            image = animate4;
        }
        if(spriteNum == 5) {
            image = animate5;
        }
        if(spriteNum == 6) {
            image = animate6;
        }

        return image;
    }

}

