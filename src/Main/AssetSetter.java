package Main;



import Entities.*;

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

        gamePanel.npc[3] = new NPC_Male(gamePanel);
        gamePanel.npc[3].worldX = gamePanel.tileSize * 36; // 36 columns * 48
        gamePanel.npc[3].worldY = gamePanel.tileSize * 47; // 47 rows * 48

        gamePanel.npc[4] = new NPC_Kid(gamePanel);
        gamePanel.npc[4].worldX = gamePanel.tileSize * 38; // 38 columns * 48
        gamePanel.npc[4].worldY = gamePanel.tileSize * 50; // 50 rows * 48

        gamePanel.npc[5] = new NPC_Merchant(gamePanel);
        gamePanel.npc[5].worldX = gamePanel.tileSize * 29; // 29 columns * 48
        gamePanel.npc[5].worldY = gamePanel.tileSize * 47; // 47 rows * 48

        gamePanel.npc[6] = new NPC_Merchant(gamePanel);
        gamePanel.npc[6].worldX = gamePanel.tileSize * 48; // 48 columns * 48
        gamePanel.npc[6].worldY = gamePanel.tileSize * 30; // 30 rows * 48

        gamePanel.npc[7] = new NPC_Soldier(gamePanel);
        gamePanel.npc[7].worldX = gamePanel.tileSize * 36; // 36 columns * 48
        gamePanel.npc[7].worldY = gamePanel.tileSize * 34; // 34 rows * 48

        gamePanel.npc[8] = new NPC_Soldier(gamePanel);
        gamePanel.npc[8].worldX = gamePanel.tileSize * 32; // 32 columns * 48
        gamePanel.npc[8].worldY = gamePanel.tileSize * 19; // 19 rows * 48

    }


}
