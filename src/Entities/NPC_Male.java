package Entities;

import Main.GamePanel;

import java.util.Random;

public class NPC_Male extends Entity {

    public NPC_Male(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        speed = 1;
        getImage();
    }

    public void getImage(){
        down1 = setup("NPC_Male/down_1");
        down2 = setup("NPC_Male/down_2");
        down3 = setup("NPC_Male/down_3");

        left1 = setup("NPC_Male/left_1");
        left2 = setup("NPC_Male/left_2");
        left3 = setup("NPC_Male/left_3");

        right1 = setup("NPC_Male/right_1");
        right2 = setup("NPC_Male/right_2");
        right3 = setup("NPC_Male/right_3");

        up1 = setup("NPC_Male/up_1");
        up2 = setup("NPC_Male/up_2");
        up3 = setup("NPC_Male/up_3");


    }

    @Override
    public void setAction() {

        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // Pick up a number from 1 to 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
