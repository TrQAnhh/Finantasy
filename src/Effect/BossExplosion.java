package Effect;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Main.GamePanel;

public class BossExplosion extends Entity{
    
    GamePanel gamePanel;

    public BossExplosion(GamePanel gamePanel){

        super(gamePanel);
        this.gamePanel = gamePanel;
        //change later
        image1 = setup("Effect/Explosion/explosion_1");
        image2 = setup("Effect/Explosion/explosion_2");
        image3 = setup("Effect/Explosion/explosion_3");
        image4 = setup("Effect/Explosion/explosion_4");
        image5 = setup("Effect/Explosion/explosion_5");
        image6 = setup("Effect/Explosion/explosion_6");
        image7 = setup("Effect/Explosion/explosion_7");
        image8 = setup("Effect/Explosion/explosion_8");
        image9 = setup("Effect/Explosion/explosion_9");
        image10 = setup("Effect/Explosion/explosion_10");
        image11 = setup("Effect/Explosion/explosion_11");
        image12 = setup("Effect/Explosion/explosion_12");
        image13 = setup("Effect/Explosion/explosion_13");
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
