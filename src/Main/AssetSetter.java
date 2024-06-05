package Main;
import Monster.MON_BloodySlime;
import Monster.MON_Boss;
import Monster.MON_GateKeeper;
import Monster.MON_GreenDragon;
import Monster.MON_Slime;
import Monster.MON_Spider;
import Effect.*;
import Entities.*;

public class AssetSetter{

    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public void setNPC(){
        // NON - MOVEMENT NPC SAMPLE CODE:
        int mapNum = 0;
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
        int mapNum = 0;
        gamePanel.monster[mapNum][0] = new MON_Slime(gamePanel);
        gamePanel.monster[mapNum][0].worldX = 700;
        gamePanel.monster[mapNum][0].worldY = 2336;

        gamePanel.monster[mapNum][1] = new MON_Slime(gamePanel);
        gamePanel.monster[mapNum][1].worldX = gamePanel.tileSize*25;
        gamePanel.monster[mapNum][1].worldY = gamePanel.tileSize*12;
        //Set monster for second map
        mapNum = 1;
        //Monster in the cave condition for boss appear
        gamePanel.monster[mapNum][0] = new MON_GateKeeper(gamePanel);
        gamePanel.monster[mapNum][0].worldX = 1425;
        gamePanel.monster[mapNum][0].worldY = 1700;
        gamePanel.monster[mapNum][1] = new MON_BloodySlime(gamePanel);
        gamePanel.monster[mapNum][1].worldX = 900;
        gamePanel.monster[mapNum][1].worldY = 1514;
        gamePanel.monster[mapNum][2] = new MON_Spider(gamePanel);
        gamePanel.monster[mapNum][2].worldX = 2050;
        gamePanel.monster[mapNum][2].worldY = 1600;
        gamePanel.monster[mapNum][4] = new MON_GreenDragon(gamePanel);
        //Boss appear when player kill three monster in the cave
        gamePanel.monster[mapNum][3] = new MON_Boss(gamePanel);
        gamePanel.monster[mapNum][3].worldX = 1360;
        gamePanel.monster[mapNum][3].worldY = 650;
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
