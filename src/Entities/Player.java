package Entities;

import Main.GamePanel;
import Main.KeyHandler;
import Object.OBJ_Bomb;
import Object.OBJ_HealPot;
import Object.OBJ_Key;
import Object.OBJ_Shield_Wood;
import Object.OBJ_Sword;
import Main.UtilityTool;

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

            this.gamePanel = gamePanel;
            this.keyHandler = keyHandler;
            // PLAYER'S SCREEN POSITION:
                screenX = ( gamePanel.screenWidth / 2 ) - 48;
                screenY = ( gamePanel.screenHeight / 2 ) - 48;
            // PLAYER'S STARTING POSITION:
                worldX = 16 * gamePanel.tileSize;
                worldY = 48 * gamePanel.tileSize;
            // PLAYER'S MOVEMENT ANIMATIONS:
                direction = " "; // default direction
            // INSTANTIATE RECTANGLE CLASS;
                solidArea = new Rectangle(20,42,35,20);
                solidAreaDefaultX = solidArea.x;
                solidAreaDefaultY = solidArea.y;
            // GET PLAYER'S IMAGES:
                getBasePlayerImage();
            // Set Player default value
                setDefaultValues();
            // Set Player inventory
                setItem();
            
        }
        public void setDefaultValues(){
            worldX = gamePanel.tileSize * 20; 
            worldY = gamePanel.tileSize * 30;
            speed = 10;
            direction = "down";
    
            // Player status
            level = 1;
            strength = 100;
            dexterity = 1;
            exp = 0;
            nextLevelExp = 4;
            coin = 0;
            currentWeapon = new OBJ_Sword(gamePanel);
            currentArmor = new OBJ_Shield_Wood(gamePanel);
            maxLife = 10;
            attack = strength;
            defense = dexterity;
            life = maxLife;
            state = normalState;
        }
        public void setDefaultPosition(){
            worldX = gamePanel.tileSize * 15; 
            worldY = gamePanel.tileSize * 18;
            direction = "down";
        }
        public void restoreLife(){
            life = maxLife;
        }
        public void setItem(){

            inventory.clear();
            inventory.add(currentWeapon);
            inventory.add(currentArmor);
            inventory.add(new OBJ_Key(gamePanel));
            inventory.add(new OBJ_HealPot(gamePanel));
            inventory.add(new OBJ_Bomb(gamePanel));
        }
        public int getAttack(){
            return attack = strength + currentWeapon.attackValue;
        }
        public int getDefense(){
            return defense = dexterity + currentArmor.defenseValue;
        }
    // METHODS:
    public void getBasePlayerImage(){
//        try (
//                InputStream moving_down01 = new FileInputStream(new File("res/Entities/Player_warrior/down_1.png"));
//                InputStream moving_down02 = new FileInputStream(new File("res/Entities/Player_warrior/down_2.png"));
//                InputStream moving_down03 = new FileInputStream(new File("res/Entities/Player_warrior/down_3.png"));
//
//                InputStream moving_left01 = new FileInputStream(new File("res/Entities/Player_warrior/left_1.png"));
//                InputStream moving_left02 = new FileInputStream(new File("res/Entities/Player_warrior/left_2.png"));
//                InputStream moving_left03 = new FileInputStream(new File("res/Entities/Player_warrior/left_3.png"));
//
//                InputStream moving_right01 = new FileInputStream(new File("res/Entities/Player_warrior/right_1.png"));
//                InputStream moving_right02 = new FileInputStream(new File("res/Entities/Player_warrior/right_2.png"));
//                InputStream moving_right03 = new FileInputStream(new File("res/Entities/Player_warrior/right_3.png"));
//
//                InputStream moving_up01 = new FileInputStream(new File("res/Entities/Player_warrior/up_1.png"));
//                InputStream moving_up02 = new FileInputStream(new File("res/Entities/Player_warrior/up_2.png"));
//                InputStream moving_up03 = new FileInputStream(new File("res/Entities/Player_warrior/up_3.png"));
//            ) {
//
//            down1 = ImageIO.read(moving_down01);
//            down2 = ImageIO.read(moving_down02);
//            down3 = ImageIO.read(moving_down03);
//
//            left1 = ImageIO.read(moving_left01);
//            left2 = ImageIO.read(moving_left02);
//            left3 = ImageIO.read(moving_left03);
//
//            right1 = ImageIO.read(moving_right01);
//            right2 = ImageIO.read(moving_right02);
//            right3 = ImageIO.read(moving_right03);
//
//            up1 = ImageIO.read(moving_up01);
//            up2 = ImageIO.read(moving_up02);
//            up3 = ImageIO.read(moving_up03);
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        // PLAYER DEFAULT IMAGES:
//            down1 = setupPlayerDefault("down_1");
//            down2 = setupPlayerDefault("down_2");
//            down3 = setupPlayerDefault("down_3");
//
//            left1 = setupPlayerDefault("left_1");
//            left2 = setupPlayerDefault("left_2");
//            left3 = setupPlayerDefault("left_3");
//
//            right1 = setupPlayerDefault("right_1");
//            right2 = setupPlayerDefault("right_2");
//            right3 = setupPlayerDefault("right_3");
//
//            up1 = setupPlayerDefault("up_1");
//            up2 = setupPlayerDefault("up_2");
//            up3 = setupPlayerDefault("up_3");
        // PLAYER WARRIOR IMAGES:
            down1 = setupPlayerWarrior("down_1");
            down2 = setupPlayerWarrior("down_2");
            down3 = setupPlayerWarrior("down_3");

            left1 = setupPlayerWarrior("left_1");
            left2 = setupPlayerWarrior("left_2");
            left3 = setupPlayerWarrior("left_3");

            right1 = setupPlayerWarrior("right_1");
            right2 = setupPlayerWarrior("right_2");
            right3 = setupPlayerWarrior("right_3");

            up1 = setupPlayerWarrior("up_1");
            up2 = setupPlayerWarrior("up_2");
            up3 = setupPlayerWarrior("up_3");

    }

    public BufferedImage setupPlayerDefault(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        String filePath = "res/Entities/Player_Default/" + imagePath + ".png";
        File imageFile = new File(filePath);

        try (FileInputStream readImage = new FileInputStream(imageFile)) {
            image = ImageIO.read(readImage);
            image = uTool.scaleImage(image,gamePanel.tileSize + 16, gamePanel.tileSize + 16);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public BufferedImage setupPlayerWarrior(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        String filePath = "res/Entities/Player_Warrior/" + imagePath + ".png";
        File imageFile = new File(filePath);

        try (FileInputStream readImage = new FileInputStream(imageFile)) {
            image = ImageIO.read(readImage);
            image = uTool.scaleImage(image,gamePanel.tileSize + 16, gamePanel.tileSize + 16);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void update(){
    // RECEIVE INPUTS FROM KEYBOARDS AND THEN UPDATE worldX - worldY positions:
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed ) {
                if(keyHandler.upPressed){
                    direction = "up";
                }
                if (keyHandler.downPressed){
                    direction = "down";
                }
                if (keyHandler.leftPressed) {
                    direction = "left";
                }
                if (keyHandler.rightPressed){
                    direction = "right";
                }

        // AFTER worldX - worldY HAVE BEEN UPDATED. THEN:
        // CHECK TILE COLLISION:
            collisionOn = false;
            gamePanel.collision.checkTile(this);
        // CHECK OBJECT COLLISIONS
            int objIndex = gamePanel.collision.checkObject(this,true);
            pickUpObject(objIndex);

            // check npc collision
                int npcIndex = gamePanel.collision.checkEntity(this, gamePanel.npc);
                interactNPC(npcIndex);
            // Check monster collision
                int monsterIndex = gamePanel.collision.checkEntity(this, gamePanel.monster);

            // Check event
                gamePanel.eHandler.checkEvent();

            gamePanel.keyHandler.enterPressed = false;

        // IF COLISION IS FALSE, PLAYER CAN MOVE:
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
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if(life > maxLife){
            life = maxLife;
        }
        if(life < 0){
            gamePanel.gameState = gamePanel.gameOverState;
            // gamePanel.playSE(12);
        }
    }
    // INTERACTION WITH OBJECTS METHOD:
    public void pickUpObject(int i){
        if(i != 999){
            String text;
            if(inventory.size() != inventorySize){
                inventory.add(gamePanel.object[0][i]);
                // gamePanel.playSE(1);
                text = "Got a " + gamePanel.object[0][i].name + "!";
            }
            else{
                text = "You cannot carry anymore!";
            }
            gamePanel.ui.addMessage(text);
            gamePanel.object[i] = null;
        }
    }
    // INTERACTION WITH NPC:
    public void interactNPC(int i){
        if ( i != 999 ) {
            if(gamePanel.keyHandler.enterPressed == true){
                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npc[gamePanel.currentMap][i].speak(gamePanel);
            }
        }
    }
    public void damageMonster(int i){

        int damage = attack - gamePanel.ui.listofMonster.get(i).defense;
        if(damage < 0){
            damage = 0;
        }
        else{
            gamePanel.ui.listofMonster.get(i).state = gamePanel.ui.listofMonster.get(i).getDamageState;
        }
        gamePanel.ui.listofMonster.get(i).life -= damage;
        gamePanel.ui.addMessage(damage + " damage!");
}
    public void battleAction(int selectAction, int choosingEquipAction, int choosingEnemyAction){
        if(state == stuntState){
            gamePanel.ui.addMessage("You Was Stunt");
        }
        else{
            
            if(selectAction == 0){
                currentWeapon = inventory.get(choosingEquipAction);
                attack = getAttack();
                damageMonster(choosingEnemyAction);
            }
            else if(selectAction == 1){
                currentArmor = inventory.get(choosingEquipAction);
                defense = getDefense() + getDefense()*30/100;       // Increase your defense 30%
                gamePanel.ui.orderTurn++;
            }
            else if(selectAction == 2){
                Entity selectedItem = inventory.get(choosingEquipAction);
                if(selectedItem.type == selectedItem.type_consumable_player)
                {
                    selectedItem.use(this);
                }
                else{
                    selectedItem.use(gamePanel.ui.listofMonster.get(choosingEnemyAction));
                }
                inventory.remove(choosingEquipAction);
            }
            
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
            gamePanel.ui.currentDialogue = "YOU ARE LEVEL UP " + level + " NOW !\n";
        }
    }
    public void selectItem(){

        int itemIndex = gamePanel.ui.getItemIndexOnSlot();

        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentWeapon = selectedItem;
                attack = getAttack();
            }
            if(selectedItem.type == type_shield){
                currentArmor = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_consumable_player){
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }
    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
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
                if (!keyHandler.upPressed) {
                    image = up2;
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
                if (!keyHandler.downPressed) {
                    image = down2;
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
                if (!keyHandler.leftPressed) {
                    image = left2;
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
                if (!keyHandler.rightPressed) {
                    image = right2;
                }
                break;
        }
        // change temp screen to drawing
        if(drawing == true) {
            if (image != null ) {
                g2.drawImage( image , screenX , screenY, gamePanel.tileSize + 16 , gamePanel.tileSize + 16, null );
            }
        }
    }
}
