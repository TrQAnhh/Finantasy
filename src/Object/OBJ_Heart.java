package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Heart extends Entity{

    public OBJ_Heart(GamePanel gamePanel){
        
        super(gamePanel);

        name = "Heart";
        image1 = setup("/Object/heart_full");
        image2 = setup("/Object/heart_half");
        image3 = setup("/Object/heart_blank");
    }
}
