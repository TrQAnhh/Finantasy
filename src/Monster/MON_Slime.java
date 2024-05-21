package Monster;

import Entities.Entity;
import Main.GamePanel;

public class MON_Slime extends Entity {

    GamePanel gamePanel;

    public MON_Slime(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Slime";
        direction = "down";
        maxLife = 4;
        life = maxLife;
        type = 2;
        attack = 5;
        defense = 0;
        exp = 2;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){

        up1 = setup("/monster/slime_down_1",gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/monster/slime_down_1",gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/monster/slime_down_2",gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/monster/slime_down_1",gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/monster/slime_down_1",gamePanel.tileSize, gamePanel.tileSize);
    }   
    public void setAction(){

    }
}
