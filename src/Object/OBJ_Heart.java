package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Heart extends Entity{

    public OBJ_Heart(GamePanel gamePanel){
        
        super(gamePanel);

        name = "Heart";
        image = setup("/object/heart_full",gamePanel.tileSize, gamePanel.tileSize);
        image2 = setup("/object/heart_half",gamePanel.tileSize, gamePanel.tileSize);
        image3 = setup("/object/heart_blank",gamePanel.tileSize, gamePanel.tileSize);
        
    }
}