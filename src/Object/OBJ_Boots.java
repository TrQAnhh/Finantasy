package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Boots extends Entity {

    public OBJ_Boots(GamePanel gamePanel){

        super(gamePanel);
        name = "Boots";
        down1 = setup("/object/boots",gamePanel.tileSize, gamePanel.tileSize);
    }
}