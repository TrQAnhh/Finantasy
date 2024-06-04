package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Key extends Entity{

    public OBJ_Key(GamePanel gamePanel){

        super(gamePanel);
        name = "Key";
        itemsImage = setupItemImages("Objects/Key");
        description = "[" + name + "]\nIt opens the chest.";
    }
}