package Main;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import Entities.Entity;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    int subState = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void drawOptionsScree() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //SUB WINDOW
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState) {
            case 0: options_top(frameX, frameY);
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }
    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;

        //TTILE
        String text = "OPTIONS";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //FULL SCREEN ON/ OFF
        textX = frameX +  gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("FULL SCREEN", textX, textY);
        if()

        //MUSIC 
        textY += gp.tileSize;
        g2.drawString("MUSIC", textX, textY);

        //SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        
        //CONTROL
        textY += gp.tileSize;
        g2.drawString("CONTROL", textX, textY);

        //END GAME
        textY += gp.tileSize;
        g2.drawString("END GAME", textX, textY);

        //BACK TO GAMEPLAY
        textY += gp.tileSize * 2;
        g2.drawString("BACK TO GAMEPLAY", textX, textY);
    }
    public int getItemIndexOnSlot() {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }
    public void drawSubWindow(int x, int y, int width, int height) {

    }
    public int getXforCenteredText(String text) {

    }
    public int getXforAlignToRightText(String text, int tailX) {

    }
}
