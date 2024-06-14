package Monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Main.GamePanel;

public class MON_GateKeeper extends Entity implements MonsterInt<Graphics2D, GamePanel>{

    GamePanel gamePanel;

    public MON_GateKeeper(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Gate Keeper";
        direction = "down";
        maxLife = 15;
        life = maxLife;
        attack = 7;
        defense = 5;
        exp = 5;
        coin = 20;
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
    //change later
    public void getImage(){

        up1 = setupMonsterImages("Monster/GateKeeper/GateKeeper",gamePanel.tileSize + 100, gamePanel.tileSize + 150);
        down1 = setupMonsterImages("Monster/GateKeeper/GateKeeper",gamePanel.tileSize + 100, gamePanel.tileSize + 150);
        left1 = setupMonsterImages("Monster/GateKeeper/GateKeeper",gamePanel.tileSize + 100, gamePanel.tileSize + 150);
        right1 = setupMonsterImages("Monster/GateKeeper/GateKeeper",gamePanel.tileSize + 100, gamePanel.tileSize + 150);
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
    public void draw(Graphics2D g2,GamePanel gamePanel){
        BufferedImage image = up1;
    
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
    
        if(dying == false) {
            g2.drawImage(image, screenX, screenY, gamePanel.tileSize + 100, gamePanel.tileSize + 100, null);
        }
    }
}
