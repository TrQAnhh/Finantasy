package Entities;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

public class Entity {
    GamePanel gamePanel;
    public BufferedImage up1, up2, up3,
                         down1, down2, down3,
                         left1, left2, left3,
                         right1, right2, right3;
    public BufferedImage upStand1, upStand2, upStand3, upStand4, downStand1, downStand2, downStand3, downStand4, leftStand1, leftStand2, leftStand3, leftStand4, rightStand1, rightStand2, rightStand3, rightStand4;
    public BufferedImage image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12, image13;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    String dialogue[] = new String[20];

    // State
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    public int standNum = 1;
    public int effectNum = 1;
    int dialogueIndex = 0;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;

    // Counter
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int effectCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;

    // Character Attributes
    public int speed;
    public int maxLife;
    public int life;
    public String name;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentArmor;
    public int mana;
    public int maxMana;

    // Item attribute
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int inventorySize = 20;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int price;

    // Type 
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_consumable_player = 7;
    public final int type_consumable_enemy = 8;

    // Battle state
    public int state;
    public final int normalState = 0;
    public final int getDamageState = 1;
    public final int stuntState = 2;
    public final int bleedState = 3;
    public final int healingState = 4;
    public final int burningState = 5;

    public Entity ( GamePanel gamePanel ) {
        this.gamePanel = gamePanel;
    }

    public BufferedImage setup(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        String filePath = "res/Entities/" + imagePath + ".png";
        File imageFile = new File(filePath);

        try (FileInputStream readImage = new FileInputStream(imageFile)) {
            image = ImageIO.read(readImage);
            image = uTool.scaleImage(image,gamePanel.tileSize, gamePanel.tileSize + 16);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void update(GamePanel gamePanel) {

        setAction();

        collisionOn = false;
        gamePanel.collision.checkTile(this);
        gamePanel.collision.checkObject(this,false);
        gamePanel.collision.checkPlayer(this);

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

    public void setAction(){}
    public void damage(Entity entity){}          //For monster
    public void speak(GamePanel gamePanel)
    {
        if(dialogue[dialogueIndex] == null){
        dialogueIndex = 0;
    }
    gamePanel.ui.currentDialogue = dialogue[dialogueIndex];
    dialogueIndex++;

    switch (gamePanel.player.direction) {
        case "up":
            direction = "down";
            break;
        case "down":
            direction = "up";
            break;
        case "left":
            direction = "right";
            break;
        case "right":
            direction = "left";
            break;
    }
}

    public void use(Entity entity){}

    public void draw(Graphics2D g2,GamePanel gamePanel){

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
        // Monster HP bar
            if (type == 2 && hpBarOn == true) {
                double oneScale = (double) gamePanel.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 1, screenY - 16, gamePanel.tileSize + 2, 12);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);

                hpBarCounter++;
                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

//        if (dying == true) {
//            dyingAnimation(g2);
//        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

//        changeAlpha(g2, 1F);
    }
    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;

        int i = 5;

        if(dyingCounter <= i) {changeAlpha(g2, 0f);}
        if(dyingCounter >i && dyingCounter <= i*2){changeAlpha(g2, 1f);}
        if(dyingCounter >i*2 && dyingCounter <= i*3){changeAlpha(g2, 0f);}
        if(dyingCounter >i*3 && dyingCounter <= i*4){changeAlpha(g2, 1f);}
        if(dyingCounter >i*4 && dyingCounter <= i*5){changeAlpha(g2, 0f);}
        if(dyingCounter >i*5 && dyingCounter <= i*6){changeAlpha(g2, 1f);}
        if(dyingCounter >i*6 && dyingCounter <= i*7){changeAlpha(g2, 0f);}
        if(dyingCounter >i*7 && dyingCounter <= i*8){changeAlpha(g2, 1f);}
        if(dyingCounter > i*8){
            dying = false;
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
//    public BufferedImage setup(String imagePath, int width, int height){
//        UtilityTool uTool = new UtilityTool();
//        BufferedImage image = null;
//
//        try {
//            image = ImageIO.read(new File("res"+imagePath+".png"));
//            image = uTool.scaleImage(image, width, height);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return image;
//    }

    public void update(){
        setAction();

        collisionOn = false;

        gamePanel.collision.checkTile(this);
        gamePanel.collision.checkObject(this, false);
        gamePanel.collision.checkEntity(this, gamePanel.npc);
        gamePanel.collision.checkEntity(this, gamePanel.monster);
        gamePanel.collision.checkPlayer(this);

        if(collisionOn == false){
            switch (direction) {
                case "up": worldY -= speed;
                    break;
                case "down": worldY += speed;
                    break;
                case "left": worldX -= speed;
                    break;
                case "right": worldX += speed;
                    break;
            }
        }

        if(direction.equals("stand"))
        {   spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum==1)
                    spriteNum = 2;
                else if(spriteNum==2)
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }
}
