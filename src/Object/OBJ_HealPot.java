package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_HealPot extends Entity {


    public OBJ_HealPot(GamePanel gamePanel){

        super(gamePanel);
        name = "Healing Pot";
        itemType = 3;
        down1 = setup("/object/key",gamePanel.tileSize, gamePanel.tileSize);
        description = "[" + name + "]\nIt is used for healing.";
    }
}