package Enviroment;

import java.awt.Graphics2D;

import Main.GamePanel;

public class EnviromentManager {
    GamePanel gamePanel;
    Lightning lightning;

    public EnviromentManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void setup() {

        lightning = new Lightning(gamePanel);
    }
    public void update() {
        lightning.update();
    }
    public void draw(Graphics2D g2) {
        lightning.draw(g2);
    }
}
