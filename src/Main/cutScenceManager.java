package Main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

public class cutScenceManager {
    GamePanel gp;
    Graphics2D g2;
    public int scenceNum;
    public int scencePhase;

    // Scene Number Setting
    public final int NA = 0;
    public final int DragonBoss = 1;
    public final int ending = 2;
    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;
    String text;

    public cutScenceManager(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        scenceEnding();
    }

    public void scenceEnding() {
        try {   
            text = "FINAL FANTASY COOKED BY\n" +
                   "MASTER CHEF QUOC ANH\n" +
                   "MASTER CHEF THANH HUY\n" +
                   "MASTER CHEF KHANH NGAN\n";
            String s = "D:/Finantasy-1/res/Background/BattleBackground_1.png";
            new credit(s, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void counterReached(int target) {
        while (counter < target) {
            counter++;
        }
    }

    public void drawBlackBackGround(float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
