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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

import Entities.Entity;
//import Object.OBJ_Heart;

public class UI {
    GamePanel gamePanel;
    public boolean gameFinished = false;

    Graphics2D g2;

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



    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public int titleScreenState = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    int counter = 0;

    public ArrayList<Entity> listofMonster = new ArrayList<>();
    public int indexBattle = 0;
    public int remainingMonster = 0;
    boolean checker = false;
    boolean playerTurn = true;
    public int interactNum = 0;  // 0 for stuff 1, stuff 2, stuff 3
    public int interactType = 0; // 0 for selection, 1 for choosing equipment, 2 for choosing enemy
    public int previousInteract = interactNum;
    public int numberOfInteractNum = interactNum;

    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;

        // ANIMATION FOR BUTTON:
            // TITLE SCREEN BUTTONS:
                public int commandNum = 0;
            // PAUSE SCREEN BUTTONS:
                public int pauseCommandNum = 0;
            // SETTING SCREEN BUTTONS:
                public int settingCommandNum = 0;
        // ANIMATION FOR DIALOGUES:
            public String currentDialogue = " ";

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
    }

    public void draw(Graphics2D g2) {

//        // DISPLAY END SCREEN:
//            if (gameFinished == true){
//                String filePath = "res/Background/EndScreen.png";
//                File imageFile = new File(filePath);
//
//                try (FileInputStream readimage = new FileInputStream(imageFile)) {
//                    endScreenImage = ImageIO.read(readimage);
//                } catch(IOException e) {
//                    e.printStackTrace();
//                }
//                g2.drawImage( endScreenImage , 0 , 0 , 18 * gamePanel.tileSize, 14 * gamePanel.tileSize, null );
//
//            // STOP THE THREAD:
//                gamePanel.gameThread = null;
//
//            } else {
//            }

        this.g2 = g2;

        // CHECK CURRENT GAME STATE:
            // TITLE SCREEN STATE:
                if ( gamePanel.gameState == gamePanel.titleState ) {
                    drawTitleScreen();
                }

            // PLAY STATE:
                if (gamePanel.gameState == gamePanel.playState) {
                    // DO PLAYSTATE STUFF LATER
                }
            // PAUSE STATE:
                if (gamePanel.gameState == gamePanel.pauseState) {
                    drawPauseScreen();
                }
            // OPTIONS STATE:
                if (gamePanel.gameState == gamePanel.optionsState) {
                    drawOptionsScreen();
                }
            // DIALOGUE STATE:
                if ( gamePanel.gameState == gamePanel.dialogueState ) {
                    drawDialogueScreen();
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
            g2.drawImage(pauseScreen,screenX,screenY,null);
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
//                buttonX += gamePanel.tileSize * 2 + 40;
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

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = Color.WHITE;
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
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
            image = ImageIO.read(new File("E:/Code/java/Hello/res/titlescreen/battle.png"));
            if(indexBattle == 1)
                g2.drawImage(image, gamePanel.maxScreenColumn, gamePanel.maxScreenRow, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Draw Monster
        if(checker == false)
        {
            remainingMonster = addMonster(indexBattle);
            checker = true;
        }
        int PositionY = 100;
        for(int i=0; i<listofMonster.size(); i++){
            g2.drawImage(listofMonster.get(i).right1, 150, PositionY, null);
            PositionY += gamePanel.tileSize;
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
            g2.drawString(listofMonster.get(i).name, nameX, nameY);
            nameY += 30;
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
                    text = listofMonster.get(i).name;
                    g2.drawString(text, x, y);
                    y += 35;
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

        if(remainingMonster == 0){
            checker = false;
            listofMonster.clear();
            gamePanel.gameState = gamePanel.playState;
        }

    }
    public int addMonster(int index){
        int num = 0;
        return num;
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
    public int getItemIndexOnSlot() {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
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
