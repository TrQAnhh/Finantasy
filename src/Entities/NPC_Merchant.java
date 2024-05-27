package Entities;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_Merchant extends Entity{
    GamePanel gamePanel;

    public NPC_Merchant(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        speed = 0;
        getImage();
        setDialogue();
    }

    public void getImage(){
        down1 = setup("NPC_Merchant/down_1");
        down2 = setup("NPC_Merchant/down_2");
        down3 = setup("NPC_Merchant/down_3");

        left1 = setup("NPC_Merchant/left_1");
        left2 = setup("NPC_Merchant/left_2");
        left3 = setup("NPC_Merchant/left_3");

        right1 = setup("NPC_Merchant/right_1");
        right2 = setup("NPC_Merchant/right_2");
        right3 = setup("NPC_Merchant/right_3");

        up1 = setup("NPC_Merchant/up_1");
        up2 = setup("NPC_Merchant/up_2");
        up3 = setup("NPC_Merchant/up_3");


    }
    public void setDialogue(){
        dialogues[0] = "Welcome to Finantasy Kingdom!";
    }
    public void speak(GamePanel gamePanel){
        super.speak(gamePanel);
    }

    @Override
    public void update(GamePanel gamePanel) {

        setAction();

        collisionOn = false;

        solidArea = new Rectangle(11,42,30,40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        // CHECK IF NPC HAS COLLISION WITH TILES:
        gamePanel.collision.checkTile(this);

        gamePanel.collision.checkObject(this,false);

        // CHECK IF NPC HAS COLLISION WITH THE PLAYER:
        gamePanel.collision.checkPlayer(this);

//        if (collisionOn == false) {
//            switch (direction) {
//                case "up":
//                    worldY = worldY - speed;
//                    break;
//                case "down":
//                    worldY = worldY + speed;
//                    break;
//                case "left":
//                    worldX = worldX - speed;
//                    break;
//                case "right":
//                    worldX = worldX + speed;
//                    break;
//            }
//        }

        // ANIMATIONS FOR MOVEMENT:
//        spriteCounter++;
//        if (spriteCounter > 8) {
//            if (spriteNum == 1) {
//                spriteNum = 2;
//            } else if (spriteNum == 2) {
//                spriteNum = 3;
//            } else if (spriteNum == 3) {
//                spriteNum = 1;
//            }
//            spriteCounter = 0;
//        }

    }

    @Override
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 150){
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if ( i <= 25 ) {
                direction = "down";
            }
            if ( i > 25 && i <= 50 ) {
                direction = "down";
            }
            if ( i > 50 && i <= 75 ) {
                direction = "left";
            }
            if ( i > 75 && i <= 100 ) {
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
                image = up2;
//                if (spriteNum == 1) {
//                    image = up1;
//                }
//                if (spriteNum == 2) {
//                    image = up2;
//                }
//                if (spriteNum == 3) {
//                    image = up3;
//                }
                break;
            case "down":
                image = down2;
//                if (spriteNum == 1) {
//                    image = down1;
//                }
//                if (spriteNum == 2) {
//                    image = down2;
//                }
//                if (spriteNum == 3) {
//                    image = down3;
//                }
                break;
            case "left":
                image = left2;
//                if (spriteNum == 1) {
//                    image = left1;
//                }
//                if (spriteNum == 2) {
//                    image = left2;
//                }
//                if (spriteNum == 3) {
//                    image = left3;
//                }
                break;
            case "right":
                image = right2;
//                if (spriteNum == 1) {
//                    image = right1;
//                }
//                if (spriteNum == 2) {
//                    image = right2;
//                }
//                if (spriteNum == 3) {
//                    image = right3;
//                }
                break;
//            case "idle":
//                if (spriteNum == 1) {
//                    image = down1;
//                }
//                if (spriteNum == 2) {
//                    image = down2;
//                }
//                if (spriteNum == 3) {
//                    image = down3;
//                }
//                break;
        }
        if (image != null ) {
            g2.drawImage( image , screenX , screenY, gamePanel.tileSize + 16, gamePanel.tileSize + 16, null );
        }
    }
}
