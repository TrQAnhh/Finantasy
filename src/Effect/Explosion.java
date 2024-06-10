package Effect;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Main.GamePanel;

public class Explosion extends Entity{
    
    GamePanel gp;

    public Explosion(GamePanel gp){

        super(gp);
        this.gp = gp;

        image1 = setupEffectImages("/Effect/Explosion/explosion_1", gp.tileSize, gp.tileSize);
        image2 = setupEffectImages("/Effect/Explosion/explosion_2", gp.tileSize, gp.tileSize);
        image3 = setupEffectImages("/Effect/Explosion/explosion_3", gp.tileSize, gp.tileSize);
        image4 = setupEffectImages("/Effect/Explosion/explosion_4",gp.tileSize, gp.tileSize);
        image5 = setupEffectImages("/Effect/Explosion/explosion_5",gp.tileSize, gp.tileSize);
        image6 = setupEffectImages("/Effect/Explosion/explosion_6",gp.tileSize, gp.tileSize);
        image7 = setupEffectImages("/Effect/Explosion/explosion_7",gp.tileSize, gp.tileSize);
        image8 = setupEffectImages("/Effect/Explosion/explosion_8",gp.tileSize, gp.tileSize);
        image9 = setupEffectImages("/Effect/Explosion/explosion_9",gp.tileSize, gp.tileSize);
        image10 = setupEffectImages("/Effect/Explosion/explosion_10",gp.tileSize, gp.tileSize);
        image11 = setupEffectImages("/Effect/Explosion/explosion_11",gp.tileSize, gp.tileSize);
        image12 = setupEffectImages("/Effect/Explosion/explosion_12",gp.tileSize, gp.tileSize);
        image13 = setupEffectImages("/Effect/Explosion/explosion_13",gp.tileSize, gp.tileSize);
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
        g2.drawImage(image,gp.ui.effectPosX,gp.ui.effectPosY,null);
    }
}
