package Main;

// import Entities.NPC_NoMovementSample;

import Entities.NPC_Witch;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter ( GamePanel gamePanel ){
        this.gamePanel = gamePanel;
    }
    public void setObject(){

    }
    public void setNPC(){
        // NON - MOVEMENT NPC SAMPLE CODE:
//        gamePanel.npc[0] = new NPC_NoMovementSample(gamePanel);
//        gamePanel.npc[0].worldX = gamePanel.tileSize * 23; // 23 columns * 48
//        gamePanel.npc[0].worldY = gamePanel.tileSize * 45; // 46 rows * 48

        gamePanel.npc[1] = new NPC_Witch(gamePanel);
        gamePanel.npc[1].worldX = gamePanel.tileSize * 23; // 23 columns * 48
        gamePanel.npc[1].worldY = gamePanel.tileSize * 46; // 46 rows * 48
    }


}
