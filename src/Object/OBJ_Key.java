package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Key extends Entity{

    public OBJ_Key(GamePanel gamePanel){

        super(gamePanel);
        name = "Key";
        down1 = setup("Objects/key");
        description = "[" + name + "]\nIt opens the door.";
    }
}