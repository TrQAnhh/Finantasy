package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Heart extends Entity{

    public OBJ_Heart(GamePanel gamePanel){
        
        super(gamePanel);

        name = "Heart";
        image1 = setup("/Objects/heart_full");
        image2 = setup("/Objects/heart_half");
        image3 = setup("/Objects/heart_blank");
    }
}
