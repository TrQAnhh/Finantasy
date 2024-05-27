package Main;

import Object.OBJ_Door;
import Entities.NPC_Logan;
import Entities.NPC_OldMan;
import Monster.MON_Slime;

public class AssetSetter{

    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel){
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
        int mapNum = 0;
        gamePanel.npc[mapNum][0] = new NPC_Logan(gamePanel);
        gamePanel.npc[mapNum][0].worldX = 1000;
        gamePanel.npc[mapNum][0].worldY = 2336;

        gamePanel.npc[mapNum][1] = new NPC_OldMan(gamePanel);
        gamePanel.npc[mapNum][1].worldX = gamePanel.tileSize*19;
        gamePanel.npc[mapNum][1].worldY = gamePanel.tileSize*31;
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