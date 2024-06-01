package Main;

import Object.OBJ_Door;
import Effect.explosion;
import Entities.NPC_Logan;
import Entities.NPC_OldMan;
import Monster.MON_Slime;

public class AssetSetter{

    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public void setObject(){
        int i=0;
        gamePanel.object[i] = new OBJ_Door(gamePanel);
        gamePanel.object[i].worldX = gamePanel.tileSize*21;
        gamePanel.object[i].worldY = gamePanel.tileSize*21;
        i++;
        gamePanel.object[i] = new OBJ_Door(gamePanel);
        gamePanel.object[i].worldX = gamePanel.tileSize*11;
        gamePanel.object[i].worldY = gamePanel.tileSize*21;
        i++;
    }
    public void setNPC(){
        int i=0;
        gamePanel.npc[i] = new NPC_Logan(gamePanel);
        gamePanel.npc[i].worldX = 1000;
        gamePanel.npc[i].worldY = 2336;
        i++;
        gamePanel.npc[i] = new NPC_OldMan(gamePanel);
        gamePanel.npc[i].worldX = gamePanel.tileSize*19;
        gamePanel.npc[i].worldY = gamePanel.tileSize*31;
        i++;
    }
    public void setMonster(){
        int i=0;
        gamePanel.monster[i] = new MON_Slime(gamePanel);
        gamePanel.monster[i].worldX = 700;
        gamePanel.monster[i].worldY = 2336;
        i++;
        gamePanel.monster[i] = new MON_Slime(gamePanel);
        gamePanel.monster[i].worldX = gamePanel.tileSize*25;
        gamePanel.monster[i].worldY = gamePanel.tileSize*12;
        i++;
    }
    public void setEffect(){
        int i=0;
        gamePanel.effect[i] = new explosion(gamePanel);
        i++;
    }
}

