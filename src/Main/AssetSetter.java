package Main;
import Monster.*;
import Data.Progress;
import Effect.*;
import Entities.*;
import Objects.*;

public class AssetSetter{

    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public void setObject(){
        int i = 0;
        int mapNum = 0;
        gamePanel.object[mapNum][i] = new OBJ_Chest(gamePanel);
        gamePanel.object[mapNum][i].worldX = gamePanel.tileSize * 25; // 25 columns * 48
        gamePanel.object[mapNum][i].worldY = gamePanel.tileSize * 49; // 49 rows * 48
        i++;
        gamePanel.object[mapNum][i] = new OBJ_Barrels(gamePanel);
        gamePanel.object[mapNum][i].worldX = gamePanel.tileSize * 22; // 22 columns * 48
        gamePanel.object[mapNum][i].worldY = gamePanel.tileSize * 49; // 49 rows * 48
        i++;
        gamePanel.object[mapNum][i] = new OBJ_Barrels(gamePanel);
        gamePanel.object[mapNum][i].worldX = gamePanel.tileSize * 13; // 13 columns * 48
        gamePanel.object[mapNum][i].worldY = gamePanel.tileSize * 50; // 50 rows * 48
        i++;
        gamePanel.object[mapNum][i] = new OBJ_Barrels(gamePanel);
        gamePanel.object[mapNum][i].worldX = gamePanel.tileSize * 28; // 28 columns * 48
        gamePanel.object[mapNum][i].worldY = gamePanel.tileSize * 46; // 46 rows * 48
        i++;
        gamePanel.object[mapNum][i] = new OBJ_Barrels(gamePanel);
        gamePanel.object[mapNum][i].worldX = gamePanel.tileSize * 50; // 50 columns * 48
        gamePanel.object[mapNum][i].worldY = gamePanel.tileSize * 49; // 49 rows * 48
        i++;

    }

    public void setNPC(){
        // NON - MOVEMENT NPC SAMPLE CODE:
            gamePanel.npc[0][0] = new NPC_King(gamePanel);
            gamePanel.npc[0][0].worldX = gamePanel.tileSize * 17; // 17 columns * 48
            gamePanel.npc[0][0].worldY = gamePanel.tileSize * 50; // 50 rows * 48

            gamePanel.npc[0][1] = new NPC_Soldier(gamePanel);
            gamePanel.npc[0][1].worldX = gamePanel.tileSize * 23; // 23 columns * 48
            gamePanel.npc[0][1].worldY = gamePanel.tileSize * 46; // 46 rows * 48

            gamePanel.npc[0][2] = new NPC_Oldman(gamePanel);
            gamePanel.npc[0][2].worldX = gamePanel.tileSize * 42; // 42 columns * 48
            gamePanel.npc[0][2].worldY = gamePanel.tileSize * 47; // 47 rows * 48

            gamePanel.npc[0][3] = new NPC_Male(gamePanel);
            gamePanel.npc[0][3].worldX = gamePanel.tileSize * 36; // 36 columns * 48
            gamePanel.npc[0][3].worldY = gamePanel.tileSize * 47; // 47 rows * 48

            gamePanel.npc[0][4] = new NPC_Kid(gamePanel);
            gamePanel.npc[0][4].worldX = gamePanel.tileSize * 38; // 38 columns * 48
            gamePanel.npc[0][4].worldY = gamePanel.tileSize * 50; // 50 rows * 48

            gamePanel.npc[0][5] = new NPC_Merchant(gamePanel);
            gamePanel.npc[0][5].worldX = gamePanel.tileSize * 29; // 29 columns * 48
            gamePanel.npc[0][5].worldY = gamePanel.tileSize * 47; // 47 rows * 48

//            gamePanel.npc[0][6] = new NPC_Merchant(gamePanel);
//            gamePanel.npc[0][6].worldX = gamePanel.tileSize * 48; // 48 columns * 48
//            gamePanel.npc[0][6].worldY = gamePanel.tileSize * 30; // 30 rows * 48

            gamePanel.npc[0][7] = new NPC_Soldier(gamePanel);
            gamePanel.npc[0][7].worldX = gamePanel.tileSize * 36; // 36 columns * 48
            gamePanel.npc[0][7].worldY = gamePanel.tileSize * 34; // 34 rows * 48

            gamePanel.npc[0][8] = new NPC_Soldier(gamePanel);
            gamePanel.npc[0][8].worldX = gamePanel.tileSize * 32; // 32 columns * 48
            gamePanel.npc[0][8].worldY = gamePanel.tileSize * 19; // 19 rows * 48

    }
    public void setMonster(){
        
        int mapNum = 1;
        //Monster in the cave condition for boss appear
        gamePanel.monster[mapNum][0] = MonsterFactory.createMonster("Gate Keeper", gamePanel);
        if(gamePanel.monster[mapNum][0].dying == false) {
            gamePanel.monster[mapNum][0].worldX = 1435;
            gamePanel.monster[mapNum][0].worldY = 1740;
        }
        gamePanel.monster[mapNum][1] = MonsterFactory.createMonster("Bloody Slime", gamePanel);
        if(gamePanel.monster[mapNum][1].dying == false) {
            gamePanel.monster[mapNum][1].worldX = 920;
            gamePanel.monster[mapNum][1].worldY = 1525;
        }
        gamePanel.monster[mapNum][2] = MonsterFactory.createMonster("Spider", gamePanel);
        if(gamePanel.monster[mapNum][2].dying == false) {
            gamePanel.monster[mapNum][2].worldX = 2020;
            gamePanel.monster[mapNum][2].worldY = 1600;
        }
        if(Progress.DragonBossDefeated == false) {
            gamePanel.monster[mapNum][3] = MonsterFactory.createMonster("Boss", gamePanel);
            gamePanel.monster[mapNum][3].worldX = 1380;
            gamePanel.monster[mapNum][3].worldY = 680;
        }
    }

    public void setEffect(){
        int i=0;
        gamePanel.effect[i] = new Slash(gamePanel);
        i++;
        gamePanel.effect[i] = new Stun(gamePanel);
        i++;
        gamePanel.effect[i] = new Bleed(gamePanel);
        i++;
        gamePanel.effect[i] = new Healing(gamePanel);
        i++;
        gamePanel.effect[i] = new Explosion(gamePanel);
        i++;
        gamePanel.effect[i] = new Defense(gamePanel);
        i++;
        gamePanel.effect[i] = new Critical(gamePanel);
        i++;
    }
}
