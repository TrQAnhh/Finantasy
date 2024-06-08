package Effect;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Main.GamePanel;

public class Defense extends Entity{
    
    GamePanel gp;

    public Defense(GamePanel gp){

        super(gp);
        this.gp = gp;

        image1 = setupEffectImages("/effect/Defense/defense_1", 128, 96);
        image2 = setupEffectImages("/effect/Defense/defense_2", 128, 96);
        image3 = setupEffectImages("/effect/Defense/defense_3", 128, 96);
        image4 = setupEffectImages("/effect/Defense/defense_4", 128, 96);
        image5 = setupEffectImages("/effect/Defense/defense_5", 128, 96);
        image6 = setupEffectImages("/effect/Defense/defense_6", 128, 96);
        image7 = setupEffectImages("/effect/Defense/defense_7", 128, 96);
        image8 = setupEffectImages("/effect/Defense/defense_8", 128, 96);
        image9 = setupEffectImages("/effect/Defense/defense_9", 128, 96);
        image10 = setupEffectImages("/effect/Defense/defense_10", 128, 96);
        image11 = setupEffectImages("/effect/Defense/defense_11", 128, 96);
        image12 = setupEffectImages("/effect/Defense/defense_12", 128, 96);
        image13 = setupEffectImages("/effect/Defense/defense_13", 128, 96);
        image14 = setupEffectImages("/effect/Defense/defense_14", 128, 96);
        image15 = setupEffectImages("/effect/Defense/defense_15", 128, 96);
    }
    public void update(){
        effectCounter++;
                if(effectCounter > 4){
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
                    effectNum = 14;
                    else if(effectNum==14)
                    effectNum = 15;
                    else if(effectNum==15)
                    effectNum = 0;
                    effectCounter = 0;
            }
    }
    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel){
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
        if(effectNum == 14) image = image14;
        if(effectNum == 15) image = image15;
        g2.drawImage(image,gp.ui.effectPosX - 24,gp.ui.effectPosY - 5,null);
    }
}
