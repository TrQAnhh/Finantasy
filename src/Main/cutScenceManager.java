package Main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;


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
        for(int i = 0; i < 8 ; ++i) {
            if(i == 0) {
                gp.stopMusic();
                scencePhase ++;
            }
            if(i == 1) {
                gp.ui.drawDialogueScreen();
            }
            if(i == 2) {
                counterReached(200);
                System.out.println(i);
            }
            if(i == 3) {
                alpha += 0.005f;
                if(alpha > 1f) {
                    alpha = 1f;
                }
                drawBlackBackGround(alpha);
    
                if(alpha == 1f) {
                    alpha = 0;
                }
                System.out.println(i);
            }
            if(i == 4) {
                drawBlackBackGround(1f);
                alpha += 0.005f;
                if(alpha > 1f) {
                    alpha = 1f;
                }
    
                String text = "Foul Tarnished,\n"
                + "In search of the Elden Ring.\n"
                + "Emboldened by the flame of ambition.\n";
    
                drawString(alpha, 38f, 200, text, 70);
                
                counterReached(700);
    
                System.out.println(i);
            }
            if(i == 5) {
                drawBlackBackGround(1f);
    
                drawString(1f, 120f, gp.screenHeight / 2, "Final Fantasy Adventure", 40);
    
                counterReached(480);
                System.out.println(i);
            }
            if(i == 6) {
                drawBlackBackGround(1f);
    
                drawString(1f, 38f, y, endCredit, 40);
    
                counterReached(480);
                System.out.println(i);
            }
            if(i == 7) {
                drawBlackBackGround(1f);
    
                y--;
                drawString(1f, 38f, y, endCredit, 40);
                System.out.println(i);
            }
        }
    }
    public void counterReached(int target) {
        while(counter < target) {
            counter++;
        }
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
            int x = gp.ui.getXforCenteredText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    
}
