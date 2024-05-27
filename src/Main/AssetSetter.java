package Main;

import Object.OBJ_Door;
import Entities.NPC_Logan;
import Entities.NPC_OldMan;
import Monster.MON_Slime;
import Entities.*;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter ( GamePanel gamePanel ){
        this.gamePanel = gamePanel;
    }
    public void setObject(){
        int mapNum = 0;
        gamePanel.object[mapNum][0] = new OBJ_Door(gamePanel);
        gamePanel.object[mapNum][0].worldX = gamePanel.tileSize*21;
        gamePanel.object[mapNum][0].worldY = gamePanel.tileSize*21;

        gamePanel.object[mapNum][1] = new OBJ_Door(gamePanel);
        gamePanel.object[mapNum][1].worldX = gamePanel.tileSize*11;
        gamePanel.object[mapNum][1].worldY = gamePanel.tileSize*21;

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
    public void setMonster(){
        //Set monster for first map
        int mapNum = 0;
        gamePanel.monster[mapNum][0] = new MON_Slime(gamePanel);
        gamePanel.monster[mapNum][0].worldX = 700;
        gamePanel.monster[mapNum][0].worldY = 2336;

        gamePanel.monster[mapNum][1] = new MON_Slime(gamePanel);
        gamePanel.monster[mapNum][1].worldX = gamePanel.tileSize*25;
        gamePanel.monster[mapNum][1].worldY = gamePanel.tileSize*12;
        //Set monster for second map
        mapNum = 1;
        gamePanel.monster[mapNum][0] = new MON_Slime(gamePanel);
        gamePanel.monster[mapNum][0].worldX = 700;
        gamePanel.monster[mapNum][0].worldY = 2336;

        gamePanel.monster[mapNum][1] = new MON_Slime(gamePanel);
        gamePanel.monster[mapNum][1].worldX = gamePanel.tileSize*25;
        gamePanel.monster[mapNum][1].worldY = gamePanel.tileSize*12;
    }


}
