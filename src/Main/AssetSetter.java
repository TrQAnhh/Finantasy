package Main;

import Objects.OBJ_Chest;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter ( GamePanel gamePanel ){
        this.gamePanel = gamePanel;
    }
    public void setObject(){
        gamePanel.object[0] = new OBJ_Chest();
        gamePanel.object[0].worldX = 53 * gamePanel.tileSize; // column 54
        gamePanel.object[0].worldY = 13 * gamePanel.tileSize; // row 14
    }


}
