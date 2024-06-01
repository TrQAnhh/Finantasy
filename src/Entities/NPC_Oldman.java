package Entities;

import java.util.Random;

import Main.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gamePanel){

        super(gamePanel);

        direction = "down";
        speed = 1;

        getImage();
    }
    public void getImage(){

        up1 = setup("/npc/oldman/oldman_up_1",gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/npc/oldman/oldman_up_2",gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/npc/oldman/oldman_down_1",gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/npc/oldman/oldman_down_2",gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/npc/oldman/oldman_left_1",gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/npc/oldman/oldman_left_2",gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/npc/oldman/oldman_right_1",gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/npc/oldman/oldman_right_2",gamePanel.tileSize, gamePanel.tileSize);

    }
    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter == 120) {
            Random rand = new Random();
        int i = rand.nextInt(100)+1;

        if(i <= 25){
            direction = "up";
        }
        if(i > 25 && i <= 50){
            direction = "down";
        }
        if(i > 50 && i <= 75){
            direction = "left";
        }
        if(i > 75 && i <=100){
            direction = "right";
        }
        actionLockCounter = 0;
        }
        
    }
}
