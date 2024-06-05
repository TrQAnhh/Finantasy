package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Coin extends Entity {
    public OBJ_Coin (GamePanel gamePanel) {

        super(gamePanel);
        name = "Coin";
        down1 = setup("Objects/GoldIngot");
        
    }
}
