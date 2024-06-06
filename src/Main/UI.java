package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.text.DecimalFormat;
import java.util.ArrayList;

import Entities.Entity;
import Entities.*;
import Object.*;

public class UI {
    GamePanel gamePanel;

    Graphics2D g2;
    Font maruMonica, purisaB;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;  //#10
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    public int titleScreenState = 0;
    public int subState = 0;

    BufferedImage endScreenImage;

    Font alagard, romulus;

    // TITLE SCREEN IMAGES:
        BufferedImage titleScreen,
                    playButton1, playButton2,
                    settingButton1, settingButton2,
                    exitButton1, exitButton2, dialouge;
    // PAUSE SCREEN IMAGES:
        BufferedImage pauseScreen,
                      resumeButton1, resumeButton2,
                      musicButton1, musicButton2,
                      sfxButton1, sfxButton2,
                      controlButton1, controlButton2,
                      backButton1, backButton2,
                      bar1_1,bar1_2,bar1_3,bar1_4,bar1_5,bar1_6,bar1_7,bar1_8,bar1_9,bar1_10,bar1_11,bar1_12,
                      bar2_1,bar2_2,bar2_3,bar2_4,bar2_5,bar2_6,bar2_7,bar2_8,bar2_9,bar2_10,bar2_11,bar2_12;

    
    BufferedImage heart_full, heart_half, heart_blank, coin;
    int counter = 0;

    // ANIMATION FOR BUTTON:
            // TITLE SCREEN BUTTONS:
            public int commandNum = 0;
            // PAUSE SCREEN BUTTONS:
                public int pauseCommandNum = 0;
            // SETTING SCREEN BUTTONS:
                public int settingCommandNum = 0;
            // ANIMATION FOR DIALOGUES:
                public String currentDialogue = " ";
            // TRADE BUTTONS
                public int tradeCommandNum = 0;

    public ArrayList<Entity> listofMonster = new ArrayList<>();
    public boolean effect = false;
    boolean checker = false;
    public int indexBattle = 0;
    public int orderTurn = 0; // 0 for player
    public int interactNum = 0;  // 0 for stuff 1, stuff 2, stuff 3
    public int interactType = 0; // 0 for selection, 1 for choosing equipment, 2 for choosing enemy
    public int selectAction = 0;
    public int choosingEquipAction = 0;
    public int choosingEnemyAction = 0;
    public int numberOfInteractNum = interactNum;
    public int effectPosX;     // Using arraylist when AOE skill
    public int effectPosY;     // Using arraylist when AOE skill
    int effecttedNo;    // Using arraylist when AOE skill
    Entity effectted;      // Using arraylist when AOE skill
    int preEffectedState = 0;

    public Entity npc;

    public UI(GamePanel gamePanel){
            this.gamePanel = gamePanel;
        // GET UI IMAGES:
                    getUIImage();
        // FONT CHỮ TRONG GAME:
            try {
                InputStream is = getClass().getResourceAsStream("/Font/alagard.ttf");
                if (is != null) {
                    alagard = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(28f);
                    is.close();
                }
                is = getClass().getResourceAsStream("/Font/romulus.ttf");
                if (is != null) {
                    romulus = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(28f);
                    is.close();
                }
            } catch (FontFormatException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        Entity bcoin = new OBJ_Coin(gamePanel);
        coin = bcoin.down1;
    }
    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);

        // CHECK CURRENT GAME STATE:
        // TITLE SCREEN STATE:
        if(gamePanel.gameState == gamePanel.titleState){
            drawTitleScreen();
        }
        // PLAY STATE:
        else if(gamePanel.gameState == gamePanel.playState){
            drawMessage();
            gamePanel.player.checkLevelUp();
        }
        // PAUSE STATE:
        else if(gamePanel.gameState == gamePanel.pauseState){
            drawPauseScreen();
        }
        // OPTIONS STATE:
        else if (gamePanel.gameState == gamePanel.optionsState) {
            drawOptionsScreen();
        }
        // DIALOGUE STATE:
        else if(gamePanel.gameState == gamePanel.dialogueState){
            drawDialogueScreen();
        }
        // CHARACTER STATE:
        else if(gamePanel.gameState == gamePanel.characterState){
            drawCharacterScreen();
            drawInventory(gamePanel.player, true);
        }
        // BATTLE STATE:
        else if(gamePanel.gameState == gamePanel.battleState){
            drawBattleScreen();
            drawMessage();
        }
        // TRANSITION STATE:
        else if ( gamePanel.gameState == gamePanel.transitionState ) {
            drawTransition();
        }
        // GAMEOVER STATE:
        else if(gamePanel.gameState == gamePanel.gameOverState){
            drawGameOverScreen();
        }
        // TRADE STATE:
        else if(gamePanel.gameState == gamePanel.tradeState){
            drawTradeScreen();
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

        if(gamePanel.gameState == gamePanel.battleState && orderTurn > 0){
            messageX = gamePanel.tileSize*14;
        }
        for(int i = 0; i < message.size(); i++){

            if(message.get(i) != null){
                
                g2.setColor(Color.BLACK);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.WHITE);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if(messageCounter.get(i) > 85){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    public void drawTitleScreen(){

        int x = 0;
        int y = 0;
        // DRAW MENU TITLE SCREEN:
            g2.drawImage( titleScreen , x , y , null );
        // DRAW BUTTON:
            x += gamePanel.tileSize * 10;
            y += gamePanel.tileSize * 5;
            // PLAY BUTTON:
                if ( commandNum == 0 ) {
                    g2.drawImage( playButton1 , x , y , null );
                } else {
                    g2.drawImage( playButton2 , x , y , null );
                }

            // SETTING BUTTON:
                y += 82;
                if ( commandNum == 1 ) {
                    g2.drawImage( settingButton1 , x , y , null );
                } else {
                    g2.drawImage( settingButton2 , x , y , null );
                }
            // EXIT BUTTON:
                y += 82;
                if ( commandNum == 2 ) {
                    g2.drawImage( exitButton1 , x , y , null );
                } else {
                    g2.drawImage( exitButton2 , x , y , null );
                }
    }
    public void getUIImage(){
        // PAUSE SCREEN:
            pauseScreen = setup("PauseScreen",gamePanel.tileSize * 18,gamePanel.tileSize * 14);
            resumeButton1 =  setup("ResumeButton_1",(gamePanel.tileSize * 3) + 7,gamePanel.tileSize + 10);
            resumeButton2 = setup("ResumeButton_2",(gamePanel.tileSize * 3) + 7,gamePanel.tileSize + 10);
            musicButton1 =  setup("MusicButton_1",(gamePanel.tileSize * 3) + 7,gamePanel.tileSize + 10);
            musicButton2 =  setup("MusicButton_2",(gamePanel.tileSize * 3) + 7,gamePanel.tileSize + 10);
            sfxButton1 =  setup("SFXButton_1",(gamePanel.tileSize * 3) + 7,gamePanel.tileSize + 10);
            sfxButton2 =  setup("SFXButton_2",(gamePanel.tileSize * 3) + 7,gamePanel.tileSize + 10);
            controlButton1 =  setup("ControlButton_1",(gamePanel.tileSize * 3) + 7,gamePanel.tileSize + 10);
            controlButton2 =  setup("ControlButton_2",(gamePanel.tileSize * 3) + 7,gamePanel.tileSize + 10);
            backButton1 =  setup("BackButton_1",(gamePanel.tileSize * 2) + 16,gamePanel.tileSize - 6);
            backButton2 =  setup("BackButton_2",(gamePanel.tileSize * 2) + 16,gamePanel.tileSize - 6);

            bar1_1 =  setup("Bar1_1",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar1_2 =  setup("Bar1_2",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar1_3 =  setup("Bar1_3",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar1_4 =  setup("Bar1_4",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar1_5 =  setup("Bar1_5",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar1_6 =  setup("Bar1_6",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar1_7 =  setup("Bar1_7",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar1_8 =  setup("Bar1_8",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar1_9 =  setup("Bar1_9",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar1_10 =  setup("Bar1_10",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar1_11 =  setup("Bar1_11",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar1_12 =  setup("Bar1_12",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);

            bar2_1 =  setup("Bar2_1",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar2_2 =  setup("Bar2_2",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar2_3 =  setup("Bar2_3",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar2_4 =  setup("Bar2_4",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar2_5 =  setup("Bar2_5",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar2_6 =  setup("Bar2_6",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar2_7 =  setup("Bar2_7",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar2_8 =  setup("Bar2_8",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar2_9 =  setup("Bar2_9",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar2_10 =  setup("Bar2_10",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar2_11 =  setup("Bar2_11",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);
            bar2_12 =  setup("Bar2_12",(gamePanel.tileSize * 5) + 8,gamePanel.tileSize + 25);

        // TITLE SCREEN:
            titleScreen = setup("TitleScreen", gamePanel.tileSize * 18,gamePanel.tileSize * 14);
            exitButton1 = setup("Exitbutton_1",(gamePanel.tileSize * 3) + 7,gamePanel.tileSize + 10);
            exitButton2 = setup("Exitbutton_2",(gamePanel.tileSize * 3) + 7,gamePanel.tileSize + 10);
            playButton1 = setup("Playbutton_1",(gamePanel.tileSize * 3) + 7,gamePanel.tileSize + 10);
            playButton2 = setup("Playbutton_2",(gamePanel.tileSize * 3) + 7,gamePanel.tileSize + 10);
            settingButton1 = setup("Settingbutton_1",(gamePanel.tileSize * 3) + 7,gamePanel.tileSize + 10);
            settingButton2 = setup("Settingbutton_2",(gamePanel.tileSize * 3) + 7,gamePanel.tileSize + 10);

        // DIALOGUE:
            dialouge = setup("dialogue_1",gamePanel.screenWidth - (gamePanel.tileSize * 2),gamePanel.tileSize * 7);
    }
    public void drawPauseScreen(){
        int x = 0;
        int y = 0;
        // DRAW PAUSE SCREEN:
            g2.drawImage( pauseScreen , x , y , null );
        // DRAW BUTTON:
                x += gamePanel.tileSize * 7 + 20; // 7 COLUMNS * 48 + 15 pixels
                y += gamePanel.tileSize * 5 - 10; // 5 ROWS * 48 - 10 pixels
            // RESUME BUTTON:
                if ( pauseCommandNum == 0 ) {
                    g2.drawImage( resumeButton1 , x , y , null );
                } else {
                    g2.drawImage( resumeButton2 , x , y , null );
                }

            // SETTING BUTTON:
                y += 82;
                if ( pauseCommandNum == 1 ) {
                    g2.drawImage( settingButton1 , x , y , null );
                } else {
                    g2.drawImage( settingButton2 , x , y , null );
                }
            // EXIT BUTTON:
                y += 82;
                if ( pauseCommandNum == 2 ) {
                    g2.drawImage( exitButton1 , x , y , null );
                } else {
                    g2.drawImage( exitButton2 , x , y , null );
                }
    }
    public void drawOptionsScreen(){
        int screenX = 0;
        int screenY = 0;
        int buttonX =0;
        int buttonY = 0;
        int barX = 0;
        int barY = 0;
    // DRAW OPTION SCREEN:
            g2.drawImage( pauseScreen ,screenX,screenY,null);
        // DRAW BUTTONS:
            buttonX += gamePanel.tileSize * 5 - 7;
            buttonY += gamePanel.tileSize * 4 - 24;
            barX = ((gamePanel.tileSize * 5) - 7) + ((gamePanel.tileSize * 3) + 17);
            barY = ((gamePanel.tileSize * 4) - 24) - 7;

            // MUSIC BUTTON & VOLUME BAR:
                if ( settingCommandNum == 0) {
                    g2.drawImage(musicButton1,buttonX,buttonY,null);
                    if ( gamePanel.music.volumeScale == 0 ) {
                        g2.drawImage(bar1_1,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 1 ) {
                        g2.drawImage(bar1_2,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 2 ) {
                        g2.drawImage(bar1_3,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 3 ) {
                        g2.drawImage(bar1_4,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 4 ) {
                        g2.drawImage(bar1_5,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 5 ) {
                        g2.drawImage(bar1_6,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 6 ) {
                        g2.drawImage(bar1_7,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 7 ) {
                        g2.drawImage(bar1_8,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 8 ) {
                        g2.drawImage(bar1_9,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 9 ) {
                        g2.drawImage(bar1_10,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 10 ) {
                        g2.drawImage(bar1_11,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 11 ) {
                        g2.drawImage(bar1_12,barX,barY,null);
                    }
                } else {
                    g2.drawImage(musicButton2,buttonX,buttonY,null);
                    if ( gamePanel.music.volumeScale == 0 ) {
                        g2.drawImage(bar2_1,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 1 ) {
                        g2.drawImage(bar2_2,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 2 ) {
                        g2.drawImage(bar2_3,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 3 ) {
                        g2.drawImage(bar2_4,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 4 ) {
                        g2.drawImage(bar2_5,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 5 ) {
                        g2.drawImage(bar2_6,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 6 ) {
                        g2.drawImage(bar2_7,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 7 ) {
                        g2.drawImage(bar2_8,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 8 ) {
                        g2.drawImage(bar2_9,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 9 ) {
                        g2.drawImage(bar2_10,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 10 ) {
                        g2.drawImage(bar2_11,barX,barY,null);
                    }
                    if ( gamePanel.music.volumeScale == 11 ) {
                        g2.drawImage(bar2_12,barX,barY,null);
                    }
                }
            // SFX BUTTON & VOLUME BAR:
                buttonY += 83;
                barY += 83;
                if ( settingCommandNum == 1){
                    g2.drawImage(sfxButton1,buttonX,buttonY,null);
                    if ( gamePanel.se.volumeScale == 0 ) {
                        g2.drawImage(bar1_1,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 1 ) {
                        g2.drawImage(bar1_2,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 2 ) {
                        g2.drawImage(bar1_3,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 3 ) {
                        g2.drawImage(bar1_4,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 4 ) {
                        g2.drawImage(bar1_5,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 5 ) {
                        g2.drawImage(bar1_6,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 6 ) {
                        g2.drawImage(bar1_7,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 7 ) {
                        g2.drawImage(bar1_8,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 8 ) {
                        g2.drawImage(bar1_9,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 9 ) {
                        g2.drawImage(bar1_10,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 10 ) {
                        g2.drawImage(bar1_11,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 11 ) {
                        g2.drawImage(bar1_12,barX,barY,null);
                    }
                } else {
                    g2.drawImage(sfxButton2,buttonX,buttonY,null);
                    if ( gamePanel.se.volumeScale == 0 ) {
                        g2.drawImage(bar2_1,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 1 ) {
                        g2.drawImage(bar2_2,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 2 ) {
                        g2.drawImage(bar2_3,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 3 ) {
                        g2.drawImage(bar2_4,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 4 ) {
                        g2.drawImage(bar2_5,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 5 ) {
                        g2.drawImage(bar2_6,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 6 ) {
                        g2.drawImage(bar2_7,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 7 ) {
                        g2.drawImage(bar2_8,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 8 ) {
                        g2.drawImage(bar2_9,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 9 ) {
                        g2.drawImage(bar2_10,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 10 ) {
                        g2.drawImage(bar2_11,barX,barY,null);
                    }
                    if ( gamePanel.se.volumeScale == 11 ) {
                        g2.drawImage(bar2_12,barX,barY,null);
                    }

                }
            // CONTROL BUTTON:
                buttonX += gamePanel.tileSize * 2 + 35;
                buttonY += 70;
                if ( settingCommandNum == 2){
                    g2.drawImage(controlButton1,buttonX,buttonY,null);
                } else {
                    g2.drawImage(controlButton2,buttonX,buttonY,null);
                }
            // EXIT BUTTON:
                buttonY += 65;
                if ( settingCommandNum == 3){
                    g2.drawImage(exitButton1,buttonX,buttonY,null);
                } else {
                    g2.drawImage(exitButton2,buttonX,buttonY,null);
                }
            // BACK BUTTON:
                buttonX += 20;
                buttonY += 65;
                if ( settingCommandNum == 4){
                    g2.drawImage(backButton1,buttonX,buttonY,null);
                } else {
                    g2.drawImage(backButton2,buttonX,buttonY,null);
                }

    }
    public void drawDialogueScreen(){
        // DRAW DIALOGUE SETTINGS:
            int x = gamePanel.tileSize;
            int y = gamePanel.tileSize * 7;
            int width = gamePanel.screenWidth - (gamePanel.tileSize * 2);
            int height = gamePanel.tileSize * 7;

            g2.drawImage(dialouge,x,y,null);

        // DISPLAY TEXT SETTING:
            x += gamePanel.tileSize * 3;
            y += gamePanel.tileSize * 2;
            g2.setFont(alagard);
            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));

            for (String line: currentDialogue.split("\n")) {
                g2.drawString(line, x, y);
                y += 40;
            }
    }
    public void drawCharacterScreen(){
    
    // Create a frame
    final int frameX = gamePanel.tileSize;
    final int frameY = gamePanel.tileSize;
    final int frameWidth = gamePanel.tileSize*5;
    final int frameHeight = gamePanel.tileSize*7;
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

    // CREATE COIN FRAME
    final int coinFrameX = gamePanel.tileSize;
    final int coinFrameY = gamePanel.tileSize * 8;
    final int coinFrameWidth = gamePanel.tileSize * 5;
    final int coinFrameHeight = gamePanel.tileSize * 2;
    drawSubWindow(coinFrameX, coinFrameY, coinFrameWidth, coinFrameHeight);
    g2.drawImage(coin, coinFrameX+12, coinFrameY+25, 42, 42, null);
    String text = "" + gamePanel.player.coin;
    textX = getXforAlignToRightText(text, tailX);
    textY = coinFrameY+60;
    g2.drawString(text, textX, textY);
}
    public BufferedImage setup(String imagePath,int width, int height) {

        BufferedImage image = null;
        UtilityTool uTool = new UtilityTool();
        String filePath = "res/Background/" + imagePath + ".png";
        File imageFile = new File(filePath);

        try (FileInputStream readFile = new FileInputStream(imageFile)) {
            image = ImageIO.read(readFile);
            image = uTool.scaleImage(image, width , height);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void drawBattleScreen(){
        
        //Draw background
        Image image;
        try {
            if(indexBattle == 1){
                image = ImageIO.read(new File("E:/Code/java/Hello/res/titlescreen/battle.png"));
            g2.drawImage(image, gamePanel.maxScreenColumn, gamePanel.maxScreenRow, null);
            }
            if(indexBattle == 2){
                image = ImageIO.read(new File("E:/Code/java/Hello/res/titlescreen/battle.png"));
            g2.drawImage(image, gamePanel.maxScreenColumn, gamePanel.maxScreenRow, null);
            }

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

        int frameX;
        int frameY;
        int frameHeight;
        int frameWidth;
        int nameX;
        int nameY;
        String textTurn = "";
        
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
            if(listofMonster.get(i) != null && listofMonster.get(i).dying == false){
                g2.drawString(listofMonster.get(i).name, nameX, nameY);
                g2.drawString(listofMonster.get(i).life + "/" + listofMonster.get(i).maxLife, nameX+gamePanel.tileSize*5, nameY);
                nameY += 30;
            }
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
        nameX += gamePanel.tileSize*6.5;
        g2.drawString(value, nameX, nameY);

        // Draw Monster
        int PositionX = gamePanel.tileSize*5;
        int PositionY = 100;
        for(int i=0; i<listofMonster.size(); i++){
            if(listofMonster.get(i).state != listofMonster.get(i).normalState){
                effectPosX = PositionX;
                effectPosY = PositionY + 10;
            }
            if(listofMonster.get(i) != null && listofMonster.get(i).dying == false){
                if(listofMonster.get(i).life <= 0){
                    gamePanel.player.exp += listofMonster.get(i).exp;
                    gamePanel.player.coin += listofMonster.get(i).coin;
                    listofMonster.get(i).dying = true;
                }
                if(i == (orderTurn - 1) && listofMonster.get(i).preState != listofMonster.get(i).stuntState){
                    g2.drawImage(listofMonster.get(i).right1, PositionX + gamePanel.tileSize*2, PositionY, null);
                }
                else{
                    g2.drawImage(listofMonster.get(i).right1, PositionX, PositionY, null);
                }
            }
            PositionY += gamePanel.tileSize + 20;
        }

        // Draw Character
        PositionX = gamePanel.tileSize*12;
        PositionY = 150;
        if(orderTurn == 0){
            PositionX = gamePanel.tileSize*10;
        }
        if(gamePanel.player.state != gamePanel.player.normalState){
            effectPosX = PositionX;
            effectPosY = PositionY;
        }
        g2.drawImage(gamePanel.player.left1, PositionX, PositionY,null);

        // Print Whose Turn Text
        if(orderTurn == 0){
            textTurn = "IT IS PLAYER TURN";
        }
        else{
            textTurn = "IT IS MONSTER TURN";
        }
        
        checkEffect();
        if(effect == true){
            drawEffect();
        }
        else{
            // Draw Player Interact
            if(orderTurn == 0){
    
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
                    if((selectAction == 0 && (gamePanel.player.inventory.get(i).type == gamePanel.player.type_sword || gamePanel.player.inventory.get(i).type == gamePanel.player.type_greatsword || gamePanel.player.inventory.get(i).type == gamePanel.player.type_dagger))
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
    
                if((selectAction == 2 && gamePanel.player.inventory.get(choosingEquipAction).type == gamePanel.player.type_consumable_player)
                    || selectAction == 1){
                    text = "Player";
                    g2.drawString(text, x, y);
                    interactNum = 0;
                    g2.drawString(">", x - 20, y);
                    choosingEnemyAction = 0;
                }
                else
                {
                    for(int i=0; i<numberOfInteract(); i++){
                        if(interactNum == i){
                            g2.drawString(">", x - 20, y);
                        }
                        y += 35;
                    }

                    int j = 0;      // To select the enemy suit to the interactNum
                    y = frameY + 35;
                    for(int i = 0; i<listofMonster.size(); i++){
                        if(listofMonster.get(i) != null && listofMonster.get(i).dying == false){
                                if(j == interactNum) choosingEnemyAction = i;
                                text = listofMonster.get(i).name;
                                g2.drawString(text, x, y);
                                y += 35;
                                j++;
                        }
                    }
                }
            } 
        }
            else if(orderTurn > 0){
                monsterTurn();
    }
        }
        
        // Draw whose turn
        frameX = gamePanel.tileSize*4;
        frameY = 15;
        frameWidth = gamePanel.tileSize*10;
        frameHeight = gamePanel.tileSize;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        nameX = frameX + gamePanel.tileSize*3+10;
        nameY = frameY + 31;
        g2.setFont(g2.getFont().deriveFont(20F));
        g2.drawString(textTurn, nameX, nameY);
        
        // End of the battle
        if(checkBattleEnd() == true){
            orderTurn = 0;
            checker = false;
            listofMonster.clear();
            gamePanel.gameState = gamePanel.playState;
            gamePanel.keyHandler.enterPressed = false;
        } 
}
    // Count the number of interaction
    public int numberOfInteract(){
        int t = 0;
        if(interactType == 1){
            
        for(int i = 0; i<gamePanel.player.inventory.size(); i++){
            if((selectAction == 0 && (gamePanel.player.inventory.get(i).type == gamePanel.player.type_sword || gamePanel.player.inventory.get(i).type == gamePanel.player.type_greatsword || gamePanel.player.inventory.get(i).type == gamePanel.player.type_dagger))
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
                    if(listofMonster.get(i).dying == false)
                    t++;
                }
            }
        }
        return t;
    }
    // Add monster due to the place (indexBattle)
    public void addMonster(int index){
        //Battle 1
        if(index == 1){
        listofMonster.add(gamePanel.monster[0][0]);
        listofMonster.add(gamePanel.monster[1][0]);
        }
        //Battle 2
        if(index == 2){
            listofMonster.add(gamePanel.monster[0][1]);
            listofMonster.add(gamePanel.monster[0][2]);
            }
    }
    // Monster Turn
    public void monsterTurn(){
        if((orderTurn - 1) >= listofMonster.size()) {
            orderTurn = 0;
            selectAction = 0;
            choosingEquipAction = 0;
            choosingEnemyAction = 0;
        }
        else{
            if(listofMonster.get(orderTurn - 1).preState == listofMonster.get(orderTurn - 1).stuntState){
                listofMonster.get(orderTurn - 1).preState = listofMonster.get(orderTurn - 1).normalState;
                orderTurn++;
            }
            else{
                
                if(listofMonster.get(orderTurn-1).dying == false){
                    if(listofMonster.get(orderTurn - 1).preState == listofMonster.get(orderTurn - 1).bleedState){
                        listofMonster.get(orderTurn - 1).life--;
                        listofMonster.get(orderTurn - 1).preState = listofMonster.get(orderTurn - 1).normalState;
                    }
                    listofMonster.get(orderTurn - 1).damage(gamePanel.player);
                }
                else{
                orderTurn++;
                }
            }
    }
}
    // Checking the state of all entities in the battle
    public void checkEffect(){
        effect = false;
        if(gamePanel.player.state != gamePanel.player.normalState){
            effect = true;
            effectted = gamePanel.player;
            effecttedNo = 0;
            gamePanel.player.preState = gamePanel.player.state;
        }
        else{
            for(int i=0;i<listofMonster.size();i++){
                if(listofMonster.get(i) != null && listofMonster.get(i).state != listofMonster.get(i).normalState){
                    effect = true;
                    effectted = listofMonster.get(i);
                    effecttedNo = i;
                    listofMonster.get(i).preState = listofMonster.get(i).state;
                }
            }
        }
    }
    // Draw the effect
    public void drawEffect(){

        if(gamePanel.effect[effectted.state - 1] != null){
            if(gamePanel.effect[effectted.state - 1].effectNum != 0){
                gamePanel.effect[effectted.state - 1].update();
                gamePanel.effect[effectted.state - 1].draw(g2, gamePanel);
            }
            else if (gamePanel.effect[effectted.state - 1].effectNum == 0){
                    gamePanel.effect[effectted.state - 1].effectNum = 1;
                    orderTurn++;
                    effect = false;
                    resetEffect();
            }
        }
    }
    // Reset effect after draw
    public void resetEffect(){
            if(effectted.type == effectted.type_player){
                gamePanel.player.state = gamePanel.player.normalState;
            }
            if(effectted.type == effectted.type_monster){
                listofMonster.get(effecttedNo).state = listofMonster.get(effecttedNo).normalState;
            }
    }
    // Checking if the battle end or not
    public boolean checkBattleEnd(){
        if(gamePanel.player.dying == true){
            return true;
        }
        else{
            for(int i = 0; i<listofMonster.size();i++){
                if(listofMonster.get(i).dying == false){
                    return false;
                }
            }
            return true;
        }
    }
    public void drawInventory(Entity entity, boolean cursor){

        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if(entity == gamePanel.player){

            frameX = gamePanel.tileSize*9 + gamePanel.tileSize/2;
            frameY = gamePanel.tileSize;
            frameWidth = gamePanel.tileSize*7 + gamePanel.tileSize/2;
            frameHeight = gamePanel.tileSize*6;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        else{
            
            frameX = gamePanel.tileSize*1;
            frameY = gamePanel.tileSize;
            frameWidth = gamePanel.tileSize*7 + gamePanel.tileSize/2;
            frameHeight = gamePanel.tileSize*6;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
            
        }

        // Frame
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        // Slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gamePanel.tileSize + 16;

        // Draw Player Items
        for(int i=0; i < entity.inventory.size(); i++){

            // Equip cursor
            if(entity.inventory.get(i) == entity.currentWeapon ||
                entity.inventory.get(i) == entity.currentArmor){
                    g2.setColor(new Color(240,190,90));
                    g2.fillRoundRect(slotX + 5/2, slotY - 3, gamePanel.tileSize + 13, gamePanel.tileSize + 13, 10, 10);
                }
            g2.drawImage(entity.inventory.get(i).down1, slotX + 9, slotY - 3, null);

            // DISPLAY AMOUNT
            if(entity == gamePanel.player && entity.inventory.get(i).amount > 1){

                g2.setFont(g2.getFont().deriveFont(32F));
                int amountX;
                int amountY;

                String s = "" + entity.inventory.get(i).amount;
                amountX = getXforAlignToRightText(s, slotX + 62);
                amountY = slotY + gamePanel.tileSize + 10;

                // SHADOW
                g2.setColor(new Color(60,60,60));
                g2.drawString(s, amountX, amountY);
                // NUMBER
                g2.setColor(Color.WHITE);
                g2.drawString(s, amountX-3, amountY-3);
            }
            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        // Cursor
        if(cursor == true){
            int cursorX = slotXstart + (slotSize * slotCol);
            int cursorY = slotYstart + (slotSize * slotRow) - 4;
            int cursorWidth = gamePanel.tileSize + 16;
            int cursorHeight = gamePanel.tileSize + 16;
            // Draw cursor
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    
            // Descriptom frame
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gamePanel.tileSize*4;
            
            int textX = dFrameX + 20;
            int textY = dFrameY + gamePanel.tileSize;
            g2.setFont(g2.getFont().deriveFont(28F));
    
            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
            if(itemIndex < entity.inventory.size()){
    
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
                for(String line : entity.inventory.get(itemIndex).description.split("\n")){
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }
            }
    
        }
        
    }
    public void drawTradeScreen(){

        switch (subState) {
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
//            case 2: trade_sell(); break;
        }
        gamePanel.keyHandler.enterPressed = false;
    }
    public void trade_select(){

        drawDialogueScreen();

        // DRAW WINDOW
        int x = gamePanel.tileSize * 13;
        int y = (int) (gamePanel.tileSize * 3.5);
        int width = gamePanel.tileSize * 3;
        int height = gamePanel.tileSize * 4;
        drawSubWindow(x, y, width, height);

        // DRAW TEXTS
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        g2.drawString("Buy", x, y);
        if(tradeCommandNum == 0){
            g2.drawString(">", x-24, y);
            if(gamePanel.keyHandler.enterPressed == true){
                subState = 1;
            }
        }
        y += gamePanel.tileSize;

/*        g2.drawString("Sell", x, y);
        if(tradeCommandNum == 1){
            g2.drawString(">", x-24, y);
            if(gamePanel.keyHandler.enterPressed == true){
                subState = 2;
            }
        }
        y += gamePanel.tileSize;             */

        g2.drawString("Leave", x, y);
        if(tradeCommandNum == 1){
            g2.drawString(">", x-24, y);
            if(gamePanel.keyHandler.enterPressed == true){
                tradeCommandNum = 0;
                gamePanel.gameState = gamePanel.dialogueState;
                currentDialogue = "Have a good trip!";
            }
        }
    }
    public void trade_buy(){
        
        // DRAW PLAYER INVENTORY
        drawInventory(gamePanel.player, false);
        // DRAW NPC INVENTORY
        drawInventory(npc, true);

        // DRAW HINT WINDOW
        int x = gamePanel.tileSize * 1;
        int y = gamePanel.tileSize * 11;
        int width = gamePanel.tileSize * 6;
        int height = gamePanel.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x+24, y+60);

        // DRAW PLAYER COIN WINDOW
        x = gamePanel.tileSize*9 + gamePanel.tileSize/2;;
        y = gamePanel.tileSize * 11;
        width = gamePanel.tileSize * 6;
        height = gamePanel.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your coin:  " + gamePanel.player.coin, x+24, y+60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if(itemIndex < npc.inventory.size()){

            x = (int) (gamePanel.tileSize*6);
            y = (int) (gamePanel.tileSize*6.5);
            width = (int) (gamePanel.tileSize*2.5);
            height = gamePanel.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+8, 32, 32, null);

            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlignToRightText(text, gamePanel.tileSize*8);
            g2.drawString(text, x, y+34);

            // BUY AN ITEM
            if(gamePanel.keyHandler.enterPressed == true){
                if(npc.inventory.get(itemIndex).price > gamePanel.player.coin){
                    subState = 0;
                    gamePanel.gameState = gamePanel.dialogueState;
                    currentDialogue = "You need more coin to buy that!";
                    drawDialogueScreen();
                }
                else{
                    if(gamePanel.player.canObtainItem(npc.inventory.get(itemIndex)) == true){
                        gamePanel.player.coin -= npc.inventory.get(itemIndex).price;
                    }
                    else{
                        subState = 0;
                        gamePanel.gameState = gamePanel.dialogueState;
                        currentDialogue = "You cannot carry anymore!";
                    }
                }
            }
        }

    }
/*     public void trade_sell(){

        // DRAW PLAYER INVENTORY
        drawInventory(gamePanel.player, true);
        
        int x;
        int y;
        int width;
        int height;
        
        // DRAW HINT WINDOW
        x = gamePanel.tileSize * 1;
        y = gamePanel.tileSize * 11;
        width = gamePanel.tileSize * 6;
        height = gamePanel.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x+24, y+60);

        // DRAW PLAYER COIN WINDOW
        x = gamePanel.tileSize*9 + gamePanel.tileSize/2;;
        y = gamePanel.tileSize * 11;
        width = gamePanel.tileSize * 6;
        height = gamePanel.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your coin:  " + gamePanel.player.coin, x+24, y+60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if(itemIndex < gamePanel.player.inventory.size()){

            x = (int) (gamePanel.tileSize*14.5);
            y = (int) (gamePanel.tileSize*6.5);
            width = (int) (gamePanel.tileSize*2.5);
            height = gamePanel.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+8, 32, 32, null);

            int price = gamePanel.player.inventory.get(itemIndex).price/2;
            String text = "" + price;
            x = getXforAlignToRightText(text, (int) (gamePanel.tileSize*16.5));
            g2.drawString(text, x, y+34);

            // SELL AN ITEM
            if(gamePanel.keyHandler.enterPressed == true){
                if(gamePanel.player.inventory.get(itemIndex) == gamePanel.player.currentWeapon ||
                    gamePanel.player.inventory.get(itemIndex) == gamePanel.player.currentArmor){
                        tradeCommandNum = 0;
                        subState = 0;
                        gamePanel.gameState = gamePanel.dialogueState;
                        currentDialogue = "You cannot sell an equipped item!";  
                    }
                else{
                    if(gamePanel.player.inventory.get(itemIndex).amount > 1){
                        gamePanel.player.inventory.get(itemIndex).amount--;
                    }
                    else{
                        gamePanel.player.inventory.remove(itemIndex);
                    }
                    gamePanel.player.coin += price;
                }
            }
        }

    }
    */
    public int getItemIndexOnSlot(int slotCol, int slotRow){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
    public void drawGameOverScreen(){

        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));

        text = "Game Over";
        // Shadow
        g2.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y = gamePanel.tileSize*4;
        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.WHITE);
        g2.drawString(text, x-4, y-4);

        // Retry
        g2.setFont(g2.getFont().deriveFont(50F));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-40, y);
        }
        // Back to the Title Screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-40, y);
        }
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
    public void drawTransition() {
        counter++;
        g2.setColor(new Color(0, 0, 0, counter * 5));
        g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        if(counter == 10) { // Transition is done
            counter = 0;
            gamePanel.gameState = gamePanel.playState;
            gamePanel.currentMap = gamePanel.eHandler.tempMap;
            gamePanel.player.worldX = gamePanel.tileSize * gamePanel.eHandler.tempCol;
            gamePanel.player.worldY = gamePanel.tileSize * gamePanel.eHandler.tempRow;
            gamePanel.eHandler.previousEventX = gamePanel.player.worldX;
            gamePanel.eHandler.previousEventY = gamePanel.player.worldY;
            gamePanel.changeArea();
        }
    }
}