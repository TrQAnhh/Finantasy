package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Key extends Entity{

    public OBJ_Key(GamePanel gamePanel){

        super(gamePanel);
        type = type_key;
        name = "Key";
        itemsImage = setupItemImages("Objects/Key");
        description = "[" + name + "]" + " \n- A gold key" + " \n- Can only be used to open \n a chest" + "\n- Can only be equipped in Normal World";
        price = 5;
    }
}