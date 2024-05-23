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

    BufferedImage endScreenImage;

    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics2D graphics2D){

        // DISPLAY END SCREEN:
            if (gameFinished == true){
                String filePath = "res/Background/EndScreen.png";
                File imageFile = new File(filePath);

                try (FileInputStream readimage = new FileInputStream(imageFile)) {
                    endScreenImage = ImageIO.read(readimage);
                } catch(IOException e) {
                    e.printStackTrace();
                }
                graphics2D.drawImage( endScreenImage , 0 , 0 , 18 * gamePanel.tileSize, 14 * gamePanel.tileSize, null );

            // STOP THE THREAD:
                gamePanel.gameThread = null;

            } else {
            }
    }

}
