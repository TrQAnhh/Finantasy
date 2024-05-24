package Entities;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC_King extends Entity {
    BufferedImage idle1,idle2,idle3,idle4,idle5,idle6,idle7,idle8,idle9;
    public NPC_King(GamePanel gamePanel) {
        super(gamePanel);
        direction = "idle";
        speed = 1;
        getImage();
    }

    public void getImage(){
        idle1 = setup("NPC_King/Idle1");
        idle2 = setup("NPC_King/Idle2");
        idle3 = setup("NPC_King/Idle3");
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

    @Override
    public void draw(Graphics2D graphics2D, GamePanel gamePanel) {
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
            case "idle":
                if (spriteNum == 1) {
                    image = idle1;
                }
                if (spriteNum == 2) {
                    image = idle2;
                }
                if (spriteNum == 3) {
                    image = idle1;
                }
                break;
        }
        if (image != null ) {
            graphics2D.drawImage( image , screenX , screenY, gamePanel.tileSize + 20 , gamePanel.tileSize + 20 , null );
        }
    }
}
