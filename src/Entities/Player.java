package Entities;

import Main.GamePanel;
import Main.KeyHandler;
import Objects.*;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Player extends Entity{
    private static Player instancePlayer = null;
    // VARIABLES:
        public final int screenX;

        public final int screenY;
        KeyHandler keyHandler;
    // CONSTRUCTORS:
        public Player(GamePanel gamePanel, KeyHandler keyHandler){

                super(gamePanel);

                this.gamePanel = gamePanel;
                this.keyHandler = keyHandler;
            // PLAYER'S STARTING POSITION:
                worldX = 16 * gamePanel.tileSize;
                worldY = 48 * gamePanel.tileSize;
            // PLAYER'S MOVEMENT ANIMATIONS:
                direction = " "; // default direction
            // PLAYER'S SCREEN POSITION:
                screenX = ( gamePanel.screenWidth / 2 ) - 48;
                screenY = ( gamePanel.screenHeight / 2 ) - 48;
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
        public static Player getInstance(GamePanel gamePanel, KeyHandler keyHandler) {
            if(instancePlayer == null) {
                return new Player(gamePanel, keyHandler);
            }
            return instancePlayer;
        }
        public void setDefaultValues(){
            // PLAYER'S SPEED:
                speed = 13;
                direction = "down";
            // PLAYER STATUS
                level = 1;
                strength = 1;
                dexterity = 1;
                exp = 0;
                nextLevelExp = 4;
                coin = 10000;

                currentWeapon = new OBJ_Sword(gamePanel);
                currentShield = new OBJ_WoodenShield(gamePanel);
                currentItem = new OBJ_Key(gamePanel);
                currentTool = new OBJ_Axe(gamePanel);

                maxLife = 10;
                attack = strength;
                defense = dexterity;
                life = maxLife;
                state = normalState;
                preState = state;
        }
        public void setDefaultPosition(){
            if(gamePanel.currentMap == 0){
                worldX = gamePanel.tileSize * 17; 
                worldY = gamePanel.tileSize * 18;
            }
            else if(gamePanel.currentMap == 1){
                worldX = gamePanel.tileSize * 31; 
                worldY = gamePanel.tileSize * 43;
            }
            direction = "down";
        }
        public void restoreLife(){
            life = maxLife;
            dying = false;
            state = normalState;
            preState = state;
        }
        public void setItem(){
            inventory.clear();
            inventory.add(currentWeapon);
            inventory.add(currentShield);
            inventory.add(currentItem);
            inventory.add(currentTool);
        }
        public int getAttack(){
            return attack = strength + currentWeapon.attackValue;
        }
        public int getDefense(){
            return defense = dexterity + currentShield.defenseValue;
        }
    // METHODS:
        // GET PLAYER IMAGES:
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
            //int monsterIndex = gamePanel.collision.checkEntity(this, gamePanel.monster);  not use


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
        if(dying == true){
            gamePanel.gameState = gamePanel.gameOverState;
            // gamePanel.playSE(12);
        }

    }
    // INTERACTION WITH OBJECTS METHOD:
    public void pickUpObject(int i){

        if(i != 999){
            if(gamePanel.keyHandler.enterPressed == true){
                if(inventory.size() < maxInventorySize){
                    if (currentTool instanceof OBJ_Axe && gamePanel.object[gamePanel.currentMap][i].type == type_barrel){
                        gamePanel.playSE(2);
                        gamePanel.object[gamePanel.currentMap][i].use(this);
                        gamePanel.object[gamePanel.currentMap][i] = null;
                    }
                    else if ( currentItem instanceof OBJ_Key && gamePanel.object[gamePanel.currentMap][i].type == type_chest){
                        gamePanel.object[gamePanel.currentMap][i].use(this);
                        if ( currentItem.amount > 1 ) {
                            currentItem.amount--;
                        } else {
                            inventory.remove(currentItem);
                        }
                        gamePanel.object[gamePanel.currentMap][i] = null;
                    }
                }
                else {
                    String text = "You cannot carry anymore!";
                    gamePanel.ui.addMessage(text);
                }
            }
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
    public void damageMonster(int choosingEquipAction, int choosingEnemyAction){

        gamePanel.ui.listofMonster.get(choosingEnemyAction).state = gamePanel.ui.listofMonster.get(choosingEnemyAction).getDamageState;
        int damage = attack - gamePanel.ui.listofMonster.get(choosingEnemyAction).defense;
        if(damage < 0){
            damage = 0;
        }
        else{
            inventory.get(choosingEquipAction).use(gamePanel.ui.listofMonster.get(choosingEnemyAction));
            damage = attack - gamePanel.ui.listofMonster.get(choosingEnemyAction).defense;
        }
        gamePanel.ui.listofMonster.get(choosingEnemyAction).life -= damage;
        gamePanel.ui.addMessage(damage + " damage!");
    }
    public void defensePlayer(int choosingEquipAction){
        defense = getDefense();
        inventory.get(choosingEquipAction).use(this);
        gamePanel.ui.addMessage("Defense!");
    }
public void battleAction(int selectAction, int choosingEquipAction, int choosingEnemyAction){
        if(preState == stunState){
            gamePanel.ui.orderTurn++;
            preState = normalState;
        }
        else{
            if(preState == bleedState){
                life--;
                preState = normalState;
            }
            if(selectAction == 0){
                currentWeapon = inventory.get(choosingEquipAction);
                attack = getAttack();
                damageMonster(choosingEquipAction, choosingEnemyAction);
            }
            else if(selectAction == 1){
                currentShield = inventory.get(choosingEquipAction);
                defense = getDefense();
                state = defenseState;
                defensePlayer(choosingEquipAction);
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
                if(selectedItem.amount > 0){
                    inventory.get(choosingEquipAction).amount--;
                }
                else{
                    inventory.remove(choosingEquipAction);
                }
            }
        }
}
    public void checkLevelUp(){

        if(exp >= nextLevelExp){

            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += maxLife/2;
            strength *= 2;
            dexterity *= 2;
            attack += 3;
            defense += 2;

            gamePanel.playSE(8);

            gamePanel.ui.addMessage("You are level " + level + " now!\n");

        }
    }
    public void selectItem(){

        int itemIndex = gamePanel.ui.getItemIndexOnSlot(gamePanel.ui.playerSlotCol, gamePanel.ui.playerSlotRow);

        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);

        // EQIP TOOL
            if(selectedItem.type == type_axe){
                currentTool = selectedItem;
            }
        // EQUIP WEAPON
            if(selectedItem.type == type_sword || selectedItem.type == type_dagger){
                currentWeapon = selectedItem;
                attack = getAttack();
            }
        // EQUIP SHIELD
            if(selectedItem.type == type_shield){
                currentShield = selectedItem;
                defense = getDefense();
            }
        // EQUIP KEY
            if (selectedItem.type == type_key) {
                currentItem = selectedItem;
            }
        // EQUIP CONSUMABLE
            if(selectedItem.type == type_consumable_player ){
                selectedItem.use(this);
                if(selectedItem.amount > 1){
                    selectedItem.amount--;
                }
                else{
                    inventory.remove(itemIndex);
                }
            }
        }
    }
    public int searchItemInInventory(String itemName){

        int itemIndex = 999;
        for(int i=0; i<inventory.size();i++){
            if(inventory.get(i).name.equals(itemName)){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }
    public boolean canObtainItem(Entity item){
        
        boolean canObtain = false;

        //  CHECK IF STACKABLE
        if(item.stackable == true){
            int index = searchItemInInventory(item.name);

            if(index != 999){
                inventory.get(index).amount++;
                canObtain = true;
            }
            else{
                //  NEW ITEM SO NEED TO CHECK VACANCY
                if(inventory.size() != maxInventorySize){
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        else{
            // NOT STACKABLE SO CHECK VACANCY
            if(inventory.size() != maxInventorySize){
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
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
