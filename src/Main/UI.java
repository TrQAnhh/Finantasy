package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class UI {
    GamePanel gamePanel;
    public boolean gameFinished = false;

    Graphics2D graphics2D;

    BufferedImage endScreenImage;

    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
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


    }

    public void drawPauseScreen(){
        String text = "PAUSE HAHAHAHAHAHAHA";

        graphics2D.drawString(text,432,336);
    }

}
