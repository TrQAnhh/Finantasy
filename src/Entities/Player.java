package Entities;

import Main.GamePanel;
import Main.KeyHandler;
import Object.OBJ_HealPot;
import Object.OBJ_Key;
import Object.OBJ_Shield_Wood;
import Object.OBJ_Sword;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Player extends Entity{
    // VARIABLES:
        public final int screenX;
        public final int screenY;
        public ArrayList<Entity> inventory = new ArrayList<>();
        public final int inventorySize = 20;
        KeyHandler keyHandler;

    // CONSTRUCTORS:
        public Player(GamePanel gamePanel, KeyHandler keyHandler){
            
            super(gamePanel);

            this.keyHandler = keyHandler;
            // PLAYER'S SCREEN POSITION:
                screenX = ( gamePanel.screenWidth / 2 ) - 48;
                screenY = ( gamePanel.screenHeight / 2 ) - 48;
            // PLAYER'S STARTING POSITION:
                worldX = 700;
                worldY = 2336;
                speed = 3;
            // PLAYER'S MOVEMENT ANIMATIONS:
                direction = " "; // default direction
            // INSTANTIATE RECTANGLE CLASS;
                solidArea = new Rectangle(20,42,35,20);
            // GET PLAYER'S IMAGES:
                getBasePlayerImage();
        }
        public void setDefaultValues(){
            worldX = gamePanel.tileSize * 22; 
            worldY = gamePanel.tileSize * 14;
            speed = 3;
            direction = "down";
    
            // Player status
            level = 1;
            strength = 1;
            dexterity = 1;
            exp = 0;
            nextLevelExp = 4;
            coin = 0;
            currentWeapon = new OBJ_Sword(gamePanel);
            currentArmor = new OBJ_Shield_Wood(gamePanel);
            maxLife = 999;
            attack = strength;
            defense = dexterity;
            life = maxLife;
        }
        public void setItem(){
            inventory.add(currentWeapon);
            inventory.add(currentArmor);
            inventory.add(new OBJ_Key(gamePanel));
            inventory.add(new OBJ_HealPot(gamePanel));
        }
        public int getAttack(){
            return attack = strength + currentWeapon.attackValue;
        }
        public int getDefense(){
            return defense = dexterity + currentArmor.defenseValue;
        }
    // METHODS:
    public void getBasePlayerImage(){
        try (
                InputStream moving_down01 = new FileInputStream(new File("res/Player_warrior/down_1.png"));
                InputStream moving_down02 = new FileInputStream(new File("res/Player_warrior/down_2.png"));
                InputStream moving_down03 = new FileInputStream(new File("res/Player_warrior/down_3.png"));
                InputStream moving_down04 = new FileInputStream(new File("res/Player_warrior/down_4.png"));

                InputStream moving_left01 = new FileInputStream(new File("res/Player_warrior/left_1.png"));
                InputStream moving_left02 = new FileInputStream(new File("res/Player_warrior/left_2.png"));
                InputStream moving_left03 = new FileInputStream(new File("res/Player_warrior/left_3.png"));
                InputStream moving_left04 = new FileInputStream(new File("res/Player_warrior/left_4.png"));

                InputStream moving_right01 = new FileInputStream(new File("res/Player_warrior/right_1.png"));
                InputStream moving_right02 = new FileInputStream(new File("res/Player_warrior/right_2.png"));
                InputStream moving_right03 = new FileInputStream(new File("res/Player_warrior/right_3.png"));
                InputStream moving_right04 = new FileInputStream(new File("res/Player_warrior/right_4.png"));

                InputStream moving_up01 = new FileInputStream(new File("res/Player_warrior/up_1.png"));
                InputStream moving_up02 = new FileInputStream(new File("res/Player_warrior/up_2.png"));
                InputStream moving_up03 = new FileInputStream(new File("res/Player_warrior/up_3.png"));
                InputStream moving_up04 = new FileInputStream(new File("res/Player_warrior/up_4.png"));
            ) {

            down1 = ImageIO.read(moving_down01);
            down2 = ImageIO.read(moving_down02);
            down3 = ImageIO.read(moving_down03);
            down4 = ImageIO.read(moving_down04);

            left1 = ImageIO.read(moving_left01);
            left2 = ImageIO.read(moving_left02);
            left3 = ImageIO.read(moving_left03);
            left4 = ImageIO.read(moving_left04);

            right1 = ImageIO.read(moving_right01);
            right2 = ImageIO.read(moving_right02);
            right3 = ImageIO.read(moving_right03);
            right4 = ImageIO.read(moving_right04);

            up1 = ImageIO.read(moving_up01);
            up2 = ImageIO.read(moving_up02);
            up3 = ImageIO.read(moving_up03);
            up4 = ImageIO.read(moving_up04);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
    // RECEIVE INPUTS FROM KEYBOARDS AND THEN UPDATE worldX - worldY positions:
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed ) {
                if(keyHandler.upPressed){
                    direction = "up";
                } else if (keyHandler.downPressed){
                    direction = "down";
                } else if (keyHandler.leftPressed) {
                    direction = "left";
                } else if (keyHandler.rightPressed){
                    direction = "right";
                }

    // AFTER worldX - worldY HAVE BEEN UPDATED. THEN, CHECK COLLISION:
        collisionOn = false;
        gamePanel.collision.checkTile(this);

        if ( collisionOn == false ) {
            switch (direction){
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
            if ( spriteCounter > 8 ){
                if ( spriteNum == 1 ){
                    spriteNum = 2;
                } else if ( spriteNum == 2 ){
                    spriteNum = 3;
                } else if (spriteNum == 3 ){
                    spriteNum = 4;
                } else if ( spriteNum == 4 ){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void interactNPC(int i){
        if(i != 999){

            if(gamePanel.keyHandler.enterPressed == true){
                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npc[i].speak();
                }       
        }
    }
    public void contactMonster(int i){
        if(i != 999){
            int damage = gamePanel.monster[i].attack - defense;
            if(damage < 0){
                damage = 0;
            }
            life -= damage;
        }
    }
    public void damageMonster(int i){

            int damage = attack - gamePanel.monster[i].defense;
            if(damage < 0){
                damage = 0;
            }
            gamePanel.ui.listofMonster.get(i).life -= damage;
            gamePanel.ui.addMessage(damage + "damage!");
            if(gamePanel.ui.listofMonster.get(i).life <= 0){
                gamePanel.monster[i].dying = true;
                gamePanel.ui.listofMonster.remove(i);
                gamePanel.ui.addMessage("Kill the " + gamePanel.ui.listofMonster.get(i).name + "!");
                exp += gamePanel.ui.listofMonster.get(i).exp;
                checkLevelUp();
            }
    }
    public void battleAction(int selectAction, int choosingEquipAction, int choosingEnemyAction){
        if(selectAction == 0){
            currentWeapon = inventory.get(choosingEquipAction);
            attack = getAttack();
            damageMonster(choosingEnemyAction);
            gamePanel.ui.playerTurn = false;
        }
        else if(selectAction == 1){
            currentArmor = inventory.get(choosingEquipAction);
            defense = getDefense() + getDefense()*30/100;       // Increase your defense 30%
            gamePanel.ui.playerTurn = false;
        }
        else if(selectAction == 2){
            
            Entity selectedItem = inventory.get(choosingEquipAction);
            selectedItem.use(this);
            inventory.remove(choosingEquipAction);
        }
    }
    public void checkLevelUp(){

        if(exp >= nextLevelExp){

            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack += 3;
            defense += 2;

            gamePanel.gameState = gamePanel.dialogueState;
            gamePanel.ui.currentDialogue = "You are level" + level + "now!\n";
        }
    }
    public void paintComponent(Graphics2D graphics2D) {
        BufferedImage image = down1;
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
                if (spriteNum == 4) {
                    image = up4;
                }
                if (!keyHandler.upPressed) {
                    image = up1;
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
                if (spriteNum == 4) {
                    image = down4;
                }
                if (!keyHandler.downPressed) {
                    image = down1;
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
                if (spriteNum == 4) {
                    image = left4;
                }
                if (!keyHandler.leftPressed) {
                    image = left1;
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
                if (spriteNum == 4) {
                    image = right4;
                }
                if (!keyHandler.rightPressed) {
                    image = right1;
                }
                break;
        }
        if (image != null ) {
            graphics2D.drawImage( image , screenX , screenY , gamePanel.tileSize + 32, gamePanel.tileSize + 32, null );
        }
    }
}
