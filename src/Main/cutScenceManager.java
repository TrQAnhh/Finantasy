package Main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;


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
    String text;


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
        try {   
            text = "FINAL FANTASY COOKED BY\n"+ "MASTER CHEF QUOC ANH\n" + "MASTER CHEF THANH HUY\n" + "MASTER CHEF KHANH NGAN\n" ;
            String s = "C:/Users/hoang/Downloads/Finantasy-1/res/Background/DragonAppear.JPG";
            credit c = new credit(s, gp, g2, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void scenceEnding() {
        try {

            alpha += 0.0005f;
                if(alpha > 1f) {
                    alpha = 1f;
                }
                drawBlackBackGround(alpha);
    
                if(alpha == 1f) {
                    alpha = 0;
                }
            text = "Welcome to my world" ;
            String s = "C:/Users/hoang/Downloads/Finantasy-1/res/Background/thiep.JPG";
            credit c = new credit(s, gp, g2, text);
        } catch (IOException e) {
            e.printStackTrace();
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
    
}
