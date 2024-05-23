package Main;

import Entities.NPC_Male;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter ( GamePanel gamePanel ){
        this.gamePanel = gamePanel;
    }
    public void setObject(){

    }
    public void setNPC(){
        gamePanel.npc[0] = new NPC_Male(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 24; // 23 columns * 48
        gamePanel.npc[0].worldY = gamePanel.tileSize * 46; // 46 rows * 48
    }


}
