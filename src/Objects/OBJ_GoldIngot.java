package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_GoldIngot extends Entity {
    public OBJ_GoldIngot(GamePanel gamePanel){

        super(gamePanel);
        name = "Gold ingot";
        itemsImage = setupItemImages("/Objects/GoldIngot");
        description = "- Used to trade with villagers";
    }


}
