package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Door extends Entity {
    
    public OBJ_Door(GamePanel gamePanel){
        
        super(gamePanel);
        name = "Door";
        down1 = setup("/object/door",gamePanel.tileSize, gamePanel.tileSize);

        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
