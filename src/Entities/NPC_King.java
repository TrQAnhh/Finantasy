package Entities;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_King extends Entity {
    BufferedImage idle1,idle2,idle3,idle4,idle5,idle6,idle7,idle8,idle9;
    public NPC_King(GamePanel gamePanel) {
        super(gamePanel);
        direction = "left";
        speed = 1;
        getImage();
        setDialogue();

    }

    public void getImage(){
        down1 = setup("NPC_King/down_1");
        down2 = setup("NPC_King/down_2");
        down3 = setup("NPC_King/down_3");

        left1 = setup("NPC_King/left_1");
        left2 = setup("NPC_King/left_2");
        left3 = setup("NPC_King/left_3");

        right1 = setup("NPC_King/right_1");
        right2 = setup("NPC_King/right_2");
        right3 = setup("NPC_King/right_3");

        up1 = setup("NPC_King/up_1");
        up2 = setup("NPC_King/up_2");
        up3 = setup("NPC_King/up_3");
    }


    @Override
    public void update(GamePanel gamePanel) {
        setAction();

        collisionOn = false;

        solidArea = new Rectangle(12,12,40,40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        // CHECK IF NPC HAS COLLISION WITH TILES:
        gamePanel.collision.checkTile(this);

        gamePanel.collision.checkObject(this,false);

        // CHECK IF NPC HAS COLLISION WITH THE PLAYER:
        gamePanel.collision.checkPlayer(this);

        if (collisionOn == false) {
            switch (direction) {
                case "up":
//                    worldY = worldY - speed;
                    break;
                case "down":
//                    worldY = worldY + speed;
                    break;
                case "left":
                    worldX = worldX - speed;
                    break;
                case "right":
                    worldX = worldX + speed;
                    break;
            }
        }

        // ANIMATIONS FOR MOVEMENT:
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
    }

    public void setDialogue(){
        dialogue[0] = "Welcome to Finantasy Kingdom!";
        dialogue[1] = "This is a place for everyone who \nwant to be a Hero";
        dialogue[2] = ".Therefore, do not be hesitate! ";


    }
    public void speak(GamePanel gamePanel){
        super.speak(gamePanel);
    }

        @Override
        public void setAction() {
            actionLockCounter++;

            if (actionLockCounter == 70){

                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if ( i <= 50 ) {
                    direction = "left";
                }
    //            if ( i > 25 && i <= 50 ) {
    //                direction = "down";
    //            }
    //            if ( i > 50 && i <= 75 ) {
    //                direction = "left";
    //            }
                if ( i > 50 && i <= 100 ) {
                    direction = "right";
                }

                actionLockCounter = 0;
            }
        }
    @Override
    public void draw(Graphics2D g2,GamePanel gamePanel) {

        BufferedImage image = null;

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        switch (direction) {
            case "up":
//                if (spriteNum == 1) {
//                    image = up1;
//                }
//                if (spriteNum == 2) {
//                    image = up2;
//                }
//                if (spriteNum == 3) {
//                    image = up3;
//                }
                image = up2;
                break;
            case "down":
//                if (spriteNum == 1) {
//                    image = down1;
//                }
//                if (spriteNum == 2) {
//                    image = down2;
//                }
//                if (spriteNum == 3) {
//                    image = down3;
//                }
                image = down2;
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
                break;
        }
        if (image != null ) {
            g2.drawImage( image , screenX , screenY, gamePanel.tileSize + 16, gamePanel.tileSize + 16, null );
        }
    }
}
