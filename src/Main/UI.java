package Main;

import Map.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gamePanel;
    public boolean gameFinished = false;

    Graphics2D graphics2D;

    BufferedImage endScreenImage;

    Font alagard, romulus;

    // TITLE SCREEN IMAGES:
        BufferedImage titleScreen,
                    playButton1, playButton2,
                    settingButton1, settingButton2,
                    exitButton1, exitButton2, dialouge;

        // ANIMATION FOR BUTTON:
            public int commandNum = 0;

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

    public void draw(Graphics2D graphics2D) {

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
//                graphics2D.drawImage( endScreenImage , 0 , 0 , 18 * gamePanel.tileSize, 14 * gamePanel.tileSize, null );
//
//            // STOP THE THREAD:
//                gamePanel.gameThread = null;
//
//            } else {
//            }

        this.graphics2D = graphics2D;

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
            // DIALOGUE STATE:
                if ( gamePanel.gameState == gamePanel.dialogueState ) {
                    drawDialogueScreen();
                }


    }
    public void drawTitleScreen(){

        int x = 0;
        int y = 0;
        // DRAW MENU TITLE SCREEN:
            graphics2D.drawImage( titleScreen , x , y , null );
        // DRAW BUTTON:
            x += gamePanel.tileSize * 10;
            y += gamePanel.tileSize * 5;
            // PLAY BUTTON:
                if ( commandNum == 0 ) {
                    graphics2D.drawImage( playButton1 , x , y , null );
                } else {
                    graphics2D.drawImage( playButton2 , x , y , null );
                }

            // SETTING BUTTON:
                y += 82;
                if ( commandNum == 1 ) {
                    graphics2D.drawImage( settingButton1 , x , y , null );
                } else {
                    graphics2D.drawImage( settingButton2 , x , y , null );
                }
            // EXIT BUTTON:
                y += 82;
                if ( commandNum == 2 ) {
                    graphics2D.drawImage( exitButton1 , x , y , null );
                } else {
                    graphics2D.drawImage( exitButton2 , x , y , null );
                }
    }
    public void getUIImage(){
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
        String text = "PAUSE";
        graphics2D.drawString(text,432,336);
    }

    public void drawDialogueScreen(){
        // WINDOW SETTINGS:
            int x = gamePanel.tileSize;
            int y = gamePanel.tileSize * 7;
            int width = gamePanel.screenWidth - (gamePanel.tileSize * 2);
            int height = gamePanel.tileSize * 7;

            drawSubWindow(x,y);
        // DISPLAY TEXT SETTING:
            x += gamePanel.tileSize * 3;
            y += gamePanel.tileSize * 2;
            graphics2D.setFont(alagard);
            graphics2D.setColor(Color.BLACK);
            graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN,25F));

            for (String line: currentDialogue.split("\n")) {
                graphics2D.drawString(line, x, y);
                y += 40;
            }
    }

    public void drawSubWindow(int x, int y){

        graphics2D.drawImage(dialouge,x,y,null);

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

}
