package Main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import Entities.Entity;

public class cutScenceManager{
    GamePanel gp;
    Graphics2D g2;
    public int scenceNum;
    public int scencePhase;

    // Scence Number Setting
    public final int NA = 0;
    public final int DragonBoss = 1;
    public final int ending = 2;
    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;


    public cutScenceManager(GamePanel gp) {
        this.gp = gp;

        endCredit = "Master chefs Quoc Anh\n" + "Master chefs Thanh Huy\n " + "Master chefs Khanh Ngan\n ";
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        switch (scenceNum) {
            case DragonBoss: scenceDragonBoss();
                break;
            case ending: scenceEnding();
                break;
        }
    }

    public void scenceDragonBoss() {

    }
    public void scenceEnding() {
        if(scencePhase == 0) {
            gp.stopMusic();
            scencePhase++;
        }
        if(scencePhase == 1) {
            gp.ui.drawDialogueScreen();
        }
        if(scencePhase == 2) {
            if(counterReached(300)){
                scencePhase ++;
            }
        }
        if(scencePhase == 3) {
            alpha += 0.005f;
            if(alpha > 1f) {
                alpha = 1f;
            }
            drawBlackBackGround(alpha);

            if(alpha == 1f) {
                alpha = 0;
                scencePhase ++;
            }
        }
        if(scencePhase == 4) {
            drawBlackBackGround(1f);
            alpha += 0.005f;
            if(alpha > 1f) {
                alpha = 1f;
            }

            String text = "Foul Tarnished,\n"
            + "In search of the Elden Ring.\n"
            + "Emboldened by the flame of ambition.\n";

            drawString(alpha, 38f, 200, text, 70);
            
            if(counterReached(600) ==  true) {

                scencePhase ++;
            }
        }
        if(scenceNum == 5) {
            drawBlackBackGround(1f);

            drawString(1f, 120f, gp.screenHeight / 2, "Final Fantasy Adventure", 40);

            if(counterReached(480) ==  true) {
                
                scencePhase ++;
            }
        }
        if(scenceNum == 6) {
            drawBlackBackGround(1f);

            drawString(1f, 38f, y, endCredit, 40);

            if(counterReached(480) ==  true) {
                
                scencePhase ++;
            }
        }
        if(scenceNum == 7) {
            drawBlackBackGround(1f);

            y--;
            drawString(1f, 38f, y, endCredit, 40);
        }
    }
    public boolean counterReached(int target) {
        boolean counterReached = false;

        counter++;
        if(counter > target) {
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }
    public void drawBlackBackGround(float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.WHITE);

        g2.setFont(g2.getFont().deriveFont(fontSize));

        for(String line: text.split("\n")) {
            int x = gp.ui.getXforCenteredText(text);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    
}
