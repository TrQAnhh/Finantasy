//package Entities;
//
//import Main.GamePanel;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//
//public class NPC_NoMovementSample extends Entity {
//
//    public NPC_NoMovementSample(GamePanel gamePanel) {
//        super(gamePanel);
//        direction = "idle";
//        speed = 1;
//        getImage();
//    }
//
//    public void getImage(){
//        idle1 = setup("NPC_Female/female_1");
//        idle2 = setup("NPC_Female/female_2");
//        idle3 = setup("NPC_Female/female_3");
//        idle4 = setup("NPC_Female/female_4");
//        idle5 = setup("NPC_Female/female_5");
//        idle6 = setup("NPC_Female/female_6");
//    }
//
//
//    @Override
//    public void update() {
//        setAction();
//        collisionOn = false;
//        gamePanel.collision.checkTile(this);
//
//
//        // ANIMATIONS FOR MOVEMENT:
//            spriteCounter++;
//            if (spriteCounter > 8) {
//                if (spriteNum == 1) {
//                    spriteNum = 2;
//                } else if (spriteNum == 2) {
//                    spriteNum = 3;
//                } else if (spriteNum == 3) {
//                    spriteNum = 4;
//                } else if (spriteNum == 4) {
//                    spriteNum = 5;
//                } else if (spriteNum == 5) {
//                    spriteNum = 1;
//                }
//                spriteCounter = 0;
//            }
//
//    }
//
//    @Override
//    public void draw(Graphics2D graphics2D) {
//        BufferedImage image = null;
//
//        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
//        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
//            if (spriteNum == 1) {
//                image = idle1;
//            }
//            if (spriteNum == 2) {
//                image = idle2;
//            }
//            if (spriteNum == 3) {
//                image = idle3;
//            }
//            if (spriteNum == 4) {
//                image = idle4;
//            }
//            if (spriteNum == 5) {
//                image = idle5;
//            }
//            if (spriteNum == 6) {
//                image = idle6;
//            }
//        if (image != null ) {
//            graphics2D.drawImage( image , screenX , screenY, gamePanel.tileSize + 32, gamePanel.tileSize + 32, null );
//        }
//    }
//}
