package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Entities.Entity;
import Entities.Player;
import Object.OBJ_Heart;
import Object.OBJ_Key;
import Object.OBJ_Sword;
import Object.OBJ_Heart;

public class UI {
    
    GamePanel gamePanel;
    Graphics2D g2;
    Font maruMonica, purisaB;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;  //#10
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    public int titleScreenState = 0;
    public int subState = 0;

    public ArrayList<Entity> listofMonster;
    public int indexBattle = 0;
    boolean checker = false;
    public boolean playerTurn = true;
    public int interactNum = 0;  // 0 for stuff 1, stuff 2, stuff 3
    public int interactType = 0; // 0 for selection, 1 for choosing equipment, 2 for choosing enemy
    public int selectAction = 0;
    public int choosingEquipAction = 0;
    public int choosingEnemyAction = 0;
    public int numberOfInteractNum = interactNum;

    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");    // cannot read the font?

            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create HUB Object
        Entity heart = new OBJ_Heart(gamePanel);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }
    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D g2){
        
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);

        if(gamePanel.gameState == gamePanel.titleState){
            drawTitleScreen();
        }
        else if(gamePanel.gameState == gamePanel.playState){

        }
        else if(gamePanel.gameState == gamePanel.pauseState){
            drawPauseScreen();
        }

        else if(gamePanel.gameState == gamePanel.dialogueState){
            drawDialogueScreen();
        }
        else if(gamePanel.gameState == gamePanel.characterState){
            drawCharacterScreen();
            drawInventory();
        }
        else if(gamePanel.gameState == gamePanel.battleState){
            drawBattleScreen();
            drawMessage();
        }
        // OPTIONS STATE
        if(gamePanel.gameState == gamePanel.optionsState) {
            drawOptionsScreen();
        }
    }
    public void drawPlayerLife(){

        //   gamePanel.player.life = 5;
   
           int x = gamePanel.tileSize/2;
           int y = gamePanel.tileSize/2;
           int i = 0;
   
           // Draw max life
           while(i < gamePanel.player.maxLife/2){
               g2.drawImage(heart_blank, x, y, null);
               i++;
               x += gamePanel.tileSize;
           }
   
           // Reset
           x = gamePanel.tileSize/2;
           y = gamePanel.tileSize/2;
           i = 0;
   
           // Draw current life
           while(i < gamePanel.player.life){
               g2.drawImage(heart_half, x, y, null);
               i++;
               if(i < gamePanel.player.life) {
                   g2.drawImage(heart_full, x, y, null);
               }
               i++;
               x += gamePanel.tileSize;
           }
    }
    public void drawMessage(){
        int messageX = gamePanel.tileSize;
        int messageY = gamePanel.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for(int i = 0; i < message.size(); i++){

            if(message.get(i) != null){
                
                g2.setColor(Color.BLACK);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.WHITE);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }

    }
    public void drawTitleScreen(){

     /*    try {
            Image image = ImageIO.read(new FileInputStream("/E:/Code/java/Hello/res/titlescreen/titlescreen(1).jpg"));
            g2.drawImage(image, 0, 0,gamePanel);
        } catch (IOException e) {
            e.printStackTrace();
        }   */
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        // Title Name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "Final Fantasy";
        int x = getXforCenteredText(text);
        int y = gamePanel.tileSize*3;

        //Shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);
        // Main color
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // Character Image (Print all important char)
        x = gamePanel.screenWidth/2 - gamePanel.tileSize*4;
        y += gamePanel.tileSize;
        g2.drawImage(gamePanel.player.left1, x, y, gamePanel.tileSize*2, gamePanel.tileSize*2, null);

        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize*3;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-gamePanel.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 1.5;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-gamePanel.tileSize, y);
        }

        text = "OPTION";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 1.5;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x-gamePanel.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 1.5;
        g2.drawString(text, x, y);
        if(commandNum == 3){
            g2.drawString(">", x-gamePanel.tileSize, y);
        }
    }
    public void drawPauseScreen(){

        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gamePanel.screenHeight/2;

        g2.drawString(text,x,y);
    }
    public void drawDialogueScreen(){
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize/2;
        int width = gamePanel.screenWidth - (gamePanel.tileSize*4);
        int height = gamePanel.tileSize * 5;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;

        for(String line : currentDialogue.split("\n")){
        g2.drawString(line, x, y);
        y+=40;
        }
    }
    public void drawCharacterScreen(){
        // Create a frame
        final int frameX = gamePanel.tileSize;
        final int frameY = gamePanel.tileSize;
        final int frameWidth = gamePanel.tileSize*5;
        final int frameHeight = gamePanel.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gamePanel.tileSize;
        final int lineHeight = 35;

        // Names
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Armor", textX, textY);
        textY += lineHeight;

        // Value
        int tailX = (frameX + frameWidth) - 30;
        // Reset text Y
        textY = frameY + gamePanel.tileSize;
        String value;
        
        value = String.valueOf(gamePanel.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.life + "/" + gamePanel.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gamePanel.player.currentWeapon.down1, tailX - gamePanel.tileSize, textY - 14, null);

        textY += gamePanel.tileSize;
        g2.drawImage(gamePanel.player.currentArmor.down1, tailX - gamePanel.tileSize, textY - 14, null);

    }
    public void drawBattleScreen(){
        
        //Draw background
        Image image;
        try {
            image = ImageIO.read(new File("E:/Code/java/Hello/res/titlescreen/battle.png"));
            if(indexBattle == 1)
            g2.drawImage(image, gamePanel.maxScreenColumn, gamePanel.maxScreenRow, null);
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        // Draw Monster
        if(checker == false)
        {
            listofMonster = new ArrayList<>();
            addMonster(indexBattle);
            checker = true;
        }

        int PositionY = 100;
        for(int i=0; i<listofMonster.size(); i++){
            if(listofMonster.get(i) != null){
                g2.drawImage(listofMonster.get(i).right1, 150, PositionY, null);
                PositionY += gamePanel.tileSize;
            }
        }

        // Draw Character
        g2.drawImage(gamePanel.player.left1, 550, 150,null);

        // Draw whose turn
        int frameX = gamePanel.tileSize*3;
        int frameY = 15;
        int frameWidth = gamePanel.tileSize*10;
        int frameHeight = gamePanel.tileSize;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        int nameX = frameX + gamePanel.tileSize*3+10;
        int nameY = frameY + 31;
        g2.setFont(g2.getFont().deriveFont(20F));
        if(playerTurn == true){
            g2.drawString("IT IS PLAYER TURN", nameX, nameY);
        }
        else{
            g2.drawString("IT IS MONSTER TURN", nameX, nameY);
        }

        // Draw Monster Board
        frameX = gamePanel.tileSize;
        frameY = gamePanel.tileSize*9;
        frameWidth = gamePanel.tileSize*7;

        frameHeight = gamePanel.tileSize*4;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        nameX = frameX + 20;
        nameY = frameY + 35;
        g2.setFont(g2.getFont().deriveFont(20F));
        for(int i=0; i < listofMonster.size(); i++){
            if(listofMonster.get(i) != null){
                g2.drawString(listofMonster.get(i).name, nameX, nameY);
                nameY += 30;
            }
        }

        // Draw Player Interact
        if(playerTurn == true){
            
            frameX = gamePanel.tileSize*5;
            frameY = gamePanel.tileSize*9;
            frameWidth = gamePanel.tileSize*3;
            frameHeight = gamePanel.tileSize*4;
            drawSubWindow(frameX, frameY, frameWidth, frameHeight);

            String text;
            int x;
            int y;
            if(interactType == 0){
            gamePanel.player.attack = gamePanel.player.strength;
            gamePanel.player.defense = gamePanel.player.dexterity;

            numberOfInteractNum = 2;

            text = "Attack";
            x = frameX + gamePanel.tileSize - 10;
            y = frameY + 35;
            g2.drawString(text, x, y);
            if(interactNum == 0){
            g2.drawString(">", x - 20, y);
            }
            text = "Defend";
            x = frameX + gamePanel.tileSize - 10;
            y += 35;
            g2.drawString(text, x, y);
            if(interactNum== 1){
            g2.drawString(">", x - 20, y);
            }
            text = "Items";
            x = frameX + gamePanel.tileSize - 10;
            y += 35;
            g2.drawString(text, x, y);
            if(interactNum == 2){
            g2.drawString(">", x - 20, y);
        }
            selectAction = interactNum;
            }
        else if (interactType == 1){

            text = "";
            x = frameX + gamePanel.tileSize - 10;
            y = frameY + 35;

            numberOfInteractNum = numberOfInteract() - 1;

            for(int i=0; i<numberOfInteract(); i++){
                if(interactNum == i){
                    g2.drawString(">", x - 20, y);
                }
                y += 35;
            }
            int j = 0;      // To select the equipment suit to the interactNum
            y = frameY + 35;
            for(int i = 0; i<gamePanel.player.inventory.size(); i++){
                if((selectAction == 0 && (gamePanel.player.inventory.get(i).type == gamePanel.player.type_sword || gamePanel.player.inventory.get(i).type == gamePanel.player.type_axe))
                  || (selectAction == 1 && (gamePanel.player.inventory.get(i).type == gamePanel.player.type_shield))
                  || (selectAction == 2 && (gamePanel.player.inventory.get(i).type == gamePanel.player.type_consumable_player || gamePanel.player.inventory.get(i).type == gamePanel.player.type_consumable_enemy))){
                        if(j == interactNum) choosingEquipAction = i;
                        text = gamePanel.player.inventory.get(i).name;
                        g2.drawString(text, x, y);
                        y += 35;
                        j++;
                }
            }
        }
        else if (interactType == 2){
            text = "";
            x = frameX + gamePanel.tileSize - 10;;
            y = frameY + 35;
            numberOfInteractNum = numberOfInteract() - 1;    

            if(selectAction == 2 && gamePanel.player.inventory.get(choosingEquipAction).type == gamePanel.player.type_consumable_player){
                text = "Player";
                g2.drawString(text, x, y);
                interactNum = 0;
                g2.drawString(">", x - 20, y);
                choosingEnemyAction = 0;
            }
            else{
                for(int i=0; i<listofMonster.size();i++){
                    if(listofMonster.get(i) != null){
                        text = listofMonster.get(i).name;
                        g2.drawString(text, x, y);
                        if(interactNum == i){
                            g2.drawString(">", x - 20, y);
                            choosingEnemyAction = interactNum;
                        }
                        y += 35;
                    }
                }
            }
        }
            
    }
        if(playerTurn == false){

            for(int i=0; i<listofMonster.size();i++){
                if(listofMonster.get(i) != null){
                    listofMonster.get(i).damage();
                }
            }
            selectAction = 0;
            choosingEquipAction = 0;
            choosingEnemyAction = 0;
            playerTurn = true;
        }
        // Draw Player Board
        String value = "";
        frameX = gamePanel.tileSize*8;
        frameY = gamePanel.tileSize*9;
        frameWidth = gamePanel.tileSize*9;

        frameHeight = gamePanel.tileSize*4;
        nameX = frameX + 20;
        nameY = frameY + 35;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        value = String.valueOf(gamePanel.player.life) + "/" + String.valueOf(gamePanel.player.maxLife);
        g2.drawString("Player", nameX, nameY);
        nameX += gamePanel.tileSize*3;
        g2.drawString(value, nameX, nameY);
        

        if(listofMonster.size() == 0){
            checker = false;
            listofMonster.clear();
            gamePanel.gameState = gamePanel.playState;
            gamePanel.keyHandler.enterPressed = false;
        }

    }
    public void addMonster(int index){
        //Battle 1
        if(index == 1){
        listofMonster.add(gamePanel.monster[0]);
        listofMonster.add(gamePanel.monster[1]);
        }
    }
    public int numberOfInteract(){
        int t = 0;
        if(interactType == 1){
            
        for(int i = 0; i<gamePanel.player.inventory.size(); i++){
            if((selectAction == 0 && (gamePanel.player.inventory.get(i).type == gamePanel.player.type_sword || gamePanel.player.inventory.get(i).type == gamePanel.player.type_axe))
                  || (selectAction == 1 && (gamePanel.player.inventory.get(i).type == gamePanel.player.type_shield))
                  || (selectAction == 2 && (gamePanel.player.inventory.get(i).type == gamePanel.player.type_consumable_player || gamePanel.player.inventory.get(i).type == gamePanel.player.type_consumable_enemy))){

                t++;
            }
        }
        }
        else if (interactType == 2){
            if(selectAction == 2 && gamePanel.player.inventory.get(choosingEquipAction).type == gamePanel.player.type_consumable_player){
                t = 1;
            }
            else{
                for(int i = 0; i<listofMonster.size(); i++){
                    t++;
                }
            }
        }
        return t;
    }
    public void drawInventory(){

        // Frame
        int frameX = gamePanel.tileSize*9;
        int frameY = gamePanel.tileSize;
        int frameWidth = gamePanel.tileSize*6;
        int frameHeight = gamePanel.tileSize*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // Slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gamePanel.tileSize + 3;

        // Cursor
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gamePanel.tileSize;
        int cursorHeight = gamePanel.tileSize;

        // Draw Player Items
        for(int i=0; i < gamePanel.player.inventory.size(); i++){

            g2.drawImage(gamePanel.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        // Draw cursor
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // Descriptom frame
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gamePanel.tileSize*3;
        drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
        
        int textX = dFrameX + 20;
        int textY = dFrameY + gamePanel.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < gamePanel.player.inventory.size()){

            for(String line : gamePanel.player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }

    }
    public void drawOptionsScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //SUB WINDOW
        int frameX = gamePanel.tileSize * 6;
        int frameY = gamePanel.tileSize;
        int frameWidth = gamePanel.tileSize * 8;
        int frameHeight = gamePanel.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState) {
            case 0: options_top(frameX, frameY);
                break;
            case 1: option_fullScreenNotification(frameX, frameY);
                break;
            case 2: option_control(frameX, frameY);
                break;
            case 3: options_endGameConfirmation(frameX, frameY);
                break;
        }

        gamePanel.keyHandler.enterPressed = false;
    }
    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;

        //TTILE
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gamePanel.tileSize;
        g2.drawString(text, textX, textY);

        //FULL SCREEN ON/ OFF
        textX = frameX +  gamePanel.tileSize;
        textY += gamePanel.tileSize * 2;
        g2.drawString("Full screen", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyHandler.enterPressed == true) {
                if(gamePanel.fullScreenOn == false) {
                    gamePanel.fullScreenOn = true;
                    g2.fillRect(textX, textY, 24, 24);
                }
                if (gamePanel.fullScreenOn == true) {
                    gamePanel.fullScreenOn = false;
                }
                subState = 1;
            }

        }

        //MUSIC 
        textY += gamePanel.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }

        //SE
        textY += gamePanel.tileSize;
        g2.drawString("Se", textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }

        //CONTROL
        textY += gamePanel.tileSize;
        g2.drawString("Control", textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyHandler.enterPressed == true) {
                subState = 2;
                commandNum = 0;
            }
        }

        //END GAME
        textY += gamePanel.tileSize;
        g2.drawString("End game", textX, textY);
        if(commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyHandler.enterPressed ==  true) {
                subState = 3;
                commandNum = 0;
            }
        }

        //BACK TO GAMEPLAY
        textY += gamePanel.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 5) {
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyHandler.enterPressed == true) {
                gamePanel.gameState = gamePanel.playState;
                commandNum = 0;
            }
        }

        //FULL SCREEN CHECK BOX
        textX = (int)(frameX + gamePanel.tileSize * 4.5);
        textY = frameY + gamePanel.tileSize * 2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gamePanel.fullScreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }

        //MUSIC VOLUME
        textY += gamePanel.tileSize;
        g2.drawRect(textX, textY, 120, 24); // 120/5 = 24
        int volumeWidth = 24 * gamePanel.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);


        //SE VOLUME
        textY += gamePanel.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gamePanel.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

       gamePanel.config.saveConfif();
    }

    public void option_fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gamePanel.tileSize;
        int textY = frameY + gamePanel.tileSize * 3;

        currentDialogue = "The change will take \n effect after restarting \n the game. Do you \n want to contuniuos \n the process !";

        for(String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // BACK
        textY = frameY + gamePanel.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyHandler.enterPressed == true) {
                subState = 0;
            }
        }
    }
    public void option_control(int frameX, int frameY) {
        int textX;
        int textY;

        //TITLE
        String text = "CONTROL";
        textX = getXforCenteredText(text);
        textY = frameY + gamePanel.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gamePanel.tileSize - 25;
        textY += gamePanel.tileSize;
        g2.drawString("Move", textX, textY);
        textY += gamePanel.tileSize;
        g2.drawString("Confirm/ Attack", textX, textY);
        textY += gamePanel.tileSize;
        g2.drawString("Shoot/ Cast", textX, textY);
        textY += gamePanel.tileSize;
        g2.drawString("Character screen", textX, textY);
        textY += gamePanel.tileSize;
        g2.drawString("Pause", textX, textY);
        textY += gamePanel.tileSize;
        g2.drawString("Options", textX, textY);
        textY += gamePanel.tileSize;

        textX = (int) (frameX + gamePanel.tileSize * 5.5);
        textY = frameY + gamePanel.tileSize * 2;
        g2.drawString("WASD", textX, textY);
        textY += gamePanel.tileSize;
        g2.drawString("ENTER", textX, textY);
        textY += gamePanel.tileSize;
        g2.drawString("F", textX + 30, textY);
        textY += gamePanel.tileSize;
        g2.drawString("C", textX + 30, textY);
        textY += gamePanel.tileSize;
        g2.drawString("P", textX + 30, textY);
        textY += gamePanel.tileSize;
        g2.drawString("ESC", textX, textY);
        textY += gamePanel.tileSize;

        // BACK 
        textX = frameX + gamePanel.tileSize;
        textY = frameY + gamePanel.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyHandler.enterPressed == true) {
                subState = 0;
                commandNum = 3;
            }
        }

    }
    public void options_endGameConfirmation(int frameX, int frameY) {
        int textX = frameX + gamePanel.tileSize;
        int textY = frameY + gamePanel.tileSize * 3;

        currentDialogue = "Quit the game and \nreturn to \n the title screen ?";
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //YES
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gamePanel.tileSize * 3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyHandler.enterPressed == true) {
                subState = 0;
                gamePanel.gameState = gamePanel.titleState;
            }
        }
        //NO
        text = "No";
        textX = getXforCenteredText(text);
        textY += gamePanel.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if(gamePanel.keyHandler.enterPressed == true) {
                subState = 0;
                commandNum = 4;
            }
        }
    }
    public int getItemIndexOnSlot() {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }
    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = Color.WHITE;
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
    public int getXforCenteredText(String text){
        int length =(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gamePanel.screenWidth/2 - length/2;
        return x;
    }
    public int getXforAlignToRightText(String text, int tailX){
        int length =(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
