package Effect;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Main.GamePanel;

public class explosion extends Entity{
    
    GamePanel gamePanel;

    public explosion(GamePanel gamePanel){

        super(gamePanel);
        this.gamePanel = gamePanel;

        image1 = setup("/Effect/explosion/explosion_1", gamePanel.tileSize, gamePanel.tileSize);
        image2 = setup("/Effect/explosion/explosion_2", gamePanel.tileSize, gamePanel.tileSize);
        image3 = setup("/Effect/explosion/explosion_3", gamePanel.tileSize, gamePanel.tileSize);
        image4 = setup("/Effect/explosion/explosion_4",gamePanel.tileSize, gamePanel.tileSize);
        image5 = setup("/Effect/explosion/explosion_5",gamePanel.tileSize, gamePanel.tileSize);
        image6 = setup("/Effect/explosion/explosion_6",gamePanel.tileSize, gamePanel.tileSize);
        image7 = setup("/Effect/explosion/explosion_7",gamePanel.tileSize, gamePanel.tileSize);
        image8 = setup("/Effect/explosion/explosion_8",gamePanel.tileSize, gamePanel.tileSize);
        image9 = setup("/Effect/explosion/explosion_9",gamePanel.tileSize, gamePanel.tileSize);
        image10 = setup("/Effect/explosion/explosion_10",gamePanel.tileSize, gamePanel.tileSize);
        image11 = setup("/Effect/explosion/explosion_11",gamePanel.tileSize, gamePanel.tileSize);
        image12 = setup("/Effect/explosion/explosion_12",gamePanel.tileSize, gamePanel.tileSize);
        image13 = setup("/Effect/explosion/explosion_13",gamePanel.tileSize, gamePanel.tileSize);
    }
    public void update(){
        effectCounter++;
                if(effectCounter > 5){
                    if(effectNum==1)
                    effectNum = 2;
                    else if(effectNum==2)
                    effectNum = 3;
                    else if(effectNum==3)
                    effectNum = 4;
                    else if(effectNum==4)
                    effectNum = 5;
                    else if(effectNum==5)
                    effectNum = 6;
                    else if(effectNum==6)
                    effectNum = 7;
                    else if(effectNum==7)
                    effectNum = 8;
                    else if(effectNum==8)
                    effectNum = 9;
                    else if(effectNum==9)
                    effectNum = 10;
                    else if(effectNum==10)
                    effectNum = 11;
                    else if(effectNum==11)
                    effectNum = 12;
                    else if(effectNum==12)
                    effectNum = 13;
                    else if(effectNum==13)
                    effectNum = 0;
                    effectCounter = 0;
            }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        if(effectNum == 1) image = image1;
        if(effectNum == 2) image = image2;
        if(effectNum == 3) image = image3;
        if(effectNum == 4) image = image4;
        if(effectNum == 5) image = image5;
        if(effectNum == 6) image = image6;
        if(effectNum == 7) image = image7;
        if(effectNum == 8) image = image8;
        if(effectNum == 9) image = image9;
        if(effectNum == 10) image = image10;
        if(effectNum == 11) image = image11;
        if(effectNum == 12) image = image12;
        if(effectNum == 13) image = image13;
        g2.drawImage(image,gamePanel.ui.effectPosX,gamePanel.ui.effectPosY,null);
    }
}
