package Main;

import java.awt.Graphics2D;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2;
    public int scenceNum;
    public int scenePhase;

    public CutsceneManager(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        if(scenceNum == 0) {
            gp.ui.drawDialogueScreen();
        }
    }
}
