package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Axe extends Entity {

    public OBJ_Axe(GamePanel gamePanel) {
        super(gamePanel);
        
        type = type_axe;
        name = "Axe";
        down1 = setup("/object/axe", gamePanel.tileSize, gamePanel.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nAn old axe.";
    }
    @Override
    public void use(Entity entity){
        entity.state = entity.stuntState;
    }
}
