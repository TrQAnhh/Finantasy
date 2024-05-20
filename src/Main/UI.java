package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
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
import Object.OBJ_Weapon;

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

    public ArrayList<Entity> listofMonster;
    public int indexBattle = 0;
    boolean checker = false;
    boolean playerTurn = true;
    public int interactNum = 0;  // 0 for stuff 1, stuff 2, stuff 3
    public int interactType = 0; // 0 for selection, 1 for choosing equipment, 2 for choosing enemy
    public int previousInteract = interactNum;
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
        g2.setColor(new Color(70,120,80));
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
        y += gamePanel.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-gamePanel.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-gamePanel.tileSize, y);
        }

        text = "OPTION";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x-gamePanel.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize;
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
        x+= gamePanel.tileSize;
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

        g2.drawImage(gamePanel.player.currentWeapon1.down1, tailX - gamePanel.tileSize, textY - 14, null);
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
        frameY = gamePanel.tileSize*7;
        frameWidth = gamePanel.tileSize*6;
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
            if(interactType == 0){
                frameX = gamePanel.tileSize*4;
            frameY = gamePanel.tileSize*7;
            frameWidth = gamePanel.tileSize*3;
            frameHeight = gamePanel.tileSize*4;
            drawSubWindow(frameX, frameY, frameWidth, frameHeight);

            String text = "";
            int x;
            int y;
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
            }
        else if (interactType == 1){
            frameX = gamePanel.tileSize*4;
            frameY = gamePanel.tileSize*7;
            frameWidth = gamePanel.tileSize*3;
            frameHeight = gamePanel.tileSize*4;
            drawSubWindow(frameX, frameY, frameWidth, frameHeight);

            String text = "";
            int x = frameX + gamePanel.tileSize - 10;
            int y = frameY + 35;
            numberOfInteractNum = numberOfInteract() - 1;

            for(int i=0; i<numberOfInteract(); i++){
                if(interactNum == i){
                    g2.drawString(">", x - 20, y);
                }
                y += 35;
            }

            y = frameY + 35;
            for(int i = 0; i<gamePanel.player.inventory.size(); i++){
                if(previousInteract+1 == (gamePanel.player.inventory.get(i).itemType)){
                    text = gamePanel.player.inventory.get(i).name;
                    g2.drawString(text, x, y);
                    y += 35;
                }
            }
        }
        else if (interactType == 2){
            frameX = gamePanel.tileSize*4;
            frameY = gamePanel.tileSize*7;
            frameWidth = gamePanel.tileSize*3;
            frameHeight = gamePanel.tileSize*4;
            drawSubWindow(frameX, frameY, frameWidth, frameHeight);

            String text = "";
            int x = frameX + gamePanel.tileSize - 10;;
            int y = frameY + 35;
            numberOfInteractNum = numberOfInteract() - 1;

            for(int i=0; i<numberOfInteract(); i++){
                if(interactNum == i){
                    g2.drawString(">", x - 20, y);
                }
                y += 35;
            }

            y = frameY + 35;
                for(int i=0; i<listofMonster.size();i++){
                    if(listofMonster.get(i) != null){
                        text = listofMonster.get(i).name;
                        g2.drawString(text, x, y);
                        y += 35;
                    }
                }

        }
            
    }
        // Draw Player Board
        String value = "";
        frameX = gamePanel.tileSize*7;
        frameY = gamePanel.tileSize*7;
        frameWidth = gamePanel.tileSize*8;
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
            if(previousInteract+1 == (gamePanel.player.inventory.get(i).itemType)){
                t++;
            }
        }
        }
        else if (interactType == 2){
            for(int i = 0; i<listofMonster.size(); i++){
                    t++;
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
    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,204,200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
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
