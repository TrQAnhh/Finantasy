package Main;

// import Entities.NPC_NoMovementSample;

import Entities.NPC_Oldman;
import Entities.NPC_Soldier;
import Entities.NPC_King;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter ( GamePanel gamePanel ){
        this.gamePanel = gamePanel;
    }
    public void setObject(){

    }
    public void setNPC(){
        // NON - MOVEMENT NPC SAMPLE CODE:
        gamePanel.npc[0] = new NPC_King(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 17; // 17 columns * 48
        gamePanel.npc[0].worldY = gamePanel.tileSize * 50; // 50 rows * 48

        gamePanel.npc[1] = new NPC_Soldier(gamePanel);
        gamePanel.npc[1].worldX = gamePanel.tileSize * 23; // 23 columns * 48
        gamePanel.npc[1].worldY = gamePanel.tileSize * 46; // 46 rows * 48

        gamePanel.npc[2] = new NPC_Oldman(gamePanel);
        gamePanel.npc[2].worldX = gamePanel.tileSize * 42; // 42 columns * 48
        gamePanel.npc[2].worldY = gamePanel.tileSize * 47; // 47 rows * 48

    }


}
