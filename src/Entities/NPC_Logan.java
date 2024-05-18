package Entities;

import java.util.Random;

import Main.GamePanel;

public class NPC_Logan extends Entity {
    public NPC_Logan(GamePanel gamePanel){
        super(gamePanel);

        direction = "down";
        speed = 1;

        getImage();
    }
    public void getImage(){

        up1 = setup("/npc/oldman/oldman_up_1",gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/npc/oldman/oldman_down_1",gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/npc/oldman/oldman_left_1",gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/npc/oldman/oldman_right_1",gamePanel.tileSize, gamePanel.tileSize);

    }
    public void setDialogue(){

        dialogue[0] = "Foul Tarnished,";
        dialogue[1] = "in search of the Elden Ring";
        dialogue[2] = "Emboldened by the flame of \nambition";
        dialogue[3] = "Someone must extinguish thy flame";
    }
    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter == 120) {
            Random rand = new Random();
        actionLockCounter = 0;
        }
    }
    public void speak(){
        super.speak();
    }
}
