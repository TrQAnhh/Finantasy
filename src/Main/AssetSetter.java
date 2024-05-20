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

        gamePanel.object[0] = new OBJ_Door(gamePanel);
        gamePanel.object[0].worldX = gamePanel.tileSize*21;
        gamePanel.object[0].worldY = gamePanel.tileSize*21;

        gamePanel.object[1] = new OBJ_Door(gamePanel);
        gamePanel.object[1].worldX = gamePanel.tileSize*11;
        gamePanel.object[1].worldY = gamePanel.tileSize*21;
        
    }
    public void setNPC(){

        gamePanel.npc[0] = new NPC_Logan(gamePanel);
        gamePanel.npc[0].worldX = 1000;
        gamePanel.npc[0].worldY = 2336;

        gamePanel.npc[1] = new NPC_OldMan(gamePanel);
        gamePanel.npc[1].worldX = gamePanel.tileSize*19;
        gamePanel.npc[1].worldY = gamePanel.tileSize*31;
    }
    public void setMonster(){

        gamePanel.monster[0] = new MON_Slime(gamePanel);
        gamePanel.monster[0].worldX = 700;
        gamePanel.monster[0].worldY = 2336;

        gamePanel.monster[1] = new MON_Slime(gamePanel);
        gamePanel.monster[1].worldX = gamePanel.tileSize*25;
        gamePanel.monster[1].worldY = gamePanel.tileSize*12;

    }
}