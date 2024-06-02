package Main;

import Object.OBJ_Door;
import Monster.MON_Slime;
import Effect.*;
import Entities.*;

public class AssetSetter{

    GamePanel gamePanel;
    int mapNum = 0;
    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public void setNPC(){
        // NON - MOVEMENT NPC SAMPLE CODE:
            gamePanel.npc[mapNum][0] = new NPC_King(gamePanel);
            gamePanel.npc[mapNum][0].worldX = gamePanel.tileSize * 17; // 17 columns * 48
            gamePanel.npc[mapNum][0].worldY = gamePanel.tileSize * 50; // 50 rows * 48

            gamePanel.npc[mapNum][1] = new NPC_Soldier(gamePanel);
            gamePanel.npc[mapNum][1].worldX = gamePanel.tileSize * 23; // 23 columns * 48
            gamePanel.npc[mapNum][1].worldY = gamePanel.tileSize * 46; // 46 rows * 48

            gamePanel.npc[mapNum][2] = new NPC_Oldman(gamePanel);
            gamePanel.npc[mapNum][2].worldX = gamePanel.tileSize * 42; // 42 columns * 48
            gamePanel.npc[mapNum][2].worldY = gamePanel.tileSize * 47; // 47 rows * 48

            gamePanel.npc[mapNum][3] = new NPC_Male(gamePanel);
            gamePanel.npc[mapNum][3].worldX = gamePanel.tileSize * 36; // 36 columns * 48
            gamePanel.npc[mapNum][3].worldY = gamePanel.tileSize * 47; // 47 rows * 48

            gamePanel.npc[mapNum][4] = new NPC_Kid(gamePanel);
            gamePanel.npc[mapNum][4].worldX = gamePanel.tileSize * 38; // 38 columns * 48
            gamePanel.npc[mapNum][4].worldY = gamePanel.tileSize * 50; // 50 rows * 48

            gamePanel.npc[mapNum][5] = new NPC_Merchant(gamePanel);
            gamePanel.npc[mapNum][5].worldX = gamePanel.tileSize * 29; // 29 columns * 48
            gamePanel.npc[mapNum][5].worldY = gamePanel.tileSize * 47; // 47 rows * 48

            gamePanel.npc[mapNum][6] = new NPC_Merchant(gamePanel);
            gamePanel.npc[mapNum][6].worldX = gamePanel.tileSize * 48; // 48 columns * 48
            gamePanel.npc[mapNum][6].worldY = gamePanel.tileSize * 30; // 30 rows * 48

            gamePanel.npc[mapNum][7] = new NPC_Soldier(gamePanel);
            gamePanel.npc[mapNum][7].worldX = gamePanel.tileSize * 36; // 36 columns * 48
            gamePanel.npc[mapNum][7].worldY = gamePanel.tileSize * 34; // 34 rows * 48

            gamePanel.npc[mapNum][8] = new NPC_Soldier(gamePanel);
            gamePanel.npc[mapNum][8].worldX = gamePanel.tileSize * 32; // 32 columns * 48
            gamePanel.npc[mapNum][8].worldY = gamePanel.tileSize * 19; // 19 rows * 48


    }
    public void setObject(){
        
    }
    public void setMonster(){
        //Set monster for first map
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

    public void setEffect(){
        int i=0;
        gamePanel.effect[i] = new Slash(gamePanel);
        i++;
        gamePanel.effect[i] = new Stunt(gamePanel);
        i++;
        gamePanel.effect[i] = new Bleed(gamePanel);
        i++;
        gamePanel.effect[i] = new Healing(gamePanel);
        i++;
        gamePanel.effect[i] = new Explosion(gamePanel);
        i++;
    }
}
