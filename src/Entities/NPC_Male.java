package Entities;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_Male extends Entity{
    GamePanel gamePanel;
    public NPC_Male(GamePanel gamePanel) {
        super(gamePanel);
        direction = "left";
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
    public void update(GamePanel gamePanel) {

        setAction();

        collisionOn = false;

        solidArea = new Rectangle(11,42,30,40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        // CHECK IF NPC HAS COLLISION WITH TILES:
        gamePanel.collisionChecker.checkTile(this);

        gamePanel.collisionChecker.checkObject(this,false);

        // CHECK IF NPC HAS COLLISION WITH THE PLAYER:
        gamePanel.collisionChecker.checkPlayer(this);

        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY = worldY - speed;
                    break;
                case "down":
                    worldY = worldY + speed;
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
        if (spriteCounter > 8) {
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

    @Override
    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(50) + 1;

            if ( i <= 25 ) {
                direction = "left";
            }
//            if ( i > 25 && i <= 50 ) {
//                direction = "down";
//            }
//            if ( i > 50 && i <= 75 ) {
//                direction = "left";
//            }
            if ( i > 25 && i <= 50 ) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D,GamePanel gamePanel) {

        BufferedImage image = null;

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
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
            graphics2D.drawImage( image , screenX , screenY, gamePanel.tileSize + 14, gamePanel.tileSize + 14, null );
        }
    }
}
