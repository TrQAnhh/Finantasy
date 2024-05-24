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

    public String currentDialogue = " ";
    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
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
        if (gamePanel.gameState == gamePanel.playState) {
            // DO PLAYSTATE STUFF LATER
        }
        if (gamePanel.gameState == gamePanel.pauseState) {
            drawPauseScreen();
        }
        if ( gamePanel.gameState == gamePanel.dialogueState ) {
            drawDialogueScreen();
        }


    }

    public void drawPauseScreen(){
        String text = "PAUSE HAHAHAHAHAHAHA";

        graphics2D.drawString(text,432,336);
    }

    public void drawDialogueScreen(){
        // WINDOW SETTINGS:
            int x = gamePanel.tileSize;
            int y = gamePanel.tileSize * 7;
            int width = gamePanel.screenWidth - (gamePanel.tileSize * 2);
            int height = gamePanel.tileSize * 7;

            drawSubWindow(x,y,width,height);
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

    public void drawSubWindow(int x, int y, int width, int height){

        BufferedImage dialouge;
        dialouge = setup("dialogue_1");
        graphics2D.drawImage(dialouge,x,y,width,height,null);

    }

    public BufferedImage setup(String imagePath) {

        BufferedImage image = null;
        UtilityTool uTool = new UtilityTool();

        String filePath = "res/Background/" + imagePath + ".png";
        File imageFile = new File(filePath);

        try (FileInputStream readFile = new FileInputStream(imageFile)) {
            image = ImageIO.read(readFile);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
