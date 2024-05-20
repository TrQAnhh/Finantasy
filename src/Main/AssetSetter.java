package Main;

import Object.OBJ_Chest;
import Object.OBJ_Door;
import Object.OBJ_Key;
import Entities.NPC_Logan;
import Entities.NPC_Oldman;
import Monster.MON_Slime;
import Object.OBJ_Boots;

public class AssetSetter{

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){

        gp.obj[0] = new OBJ_Door(gp);
        gp.obj[0].worldX = gp.tileSize*21;
        gp.obj[0].worldY = gp.tileSize*21;

        gp.obj[1] = new OBJ_Door(gp);
        gp.obj[1].worldX = gp.tileSize*11;
        gp.obj[1].worldY = gp.tileSize*21;
        
    }
    public void setNPC(){

        gp.npc[0] = new NPC_Oldman(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;

        gp.npc[1] = new NPC_Logan(gp);
        gp.npc[1].worldX = gp.tileSize*19;
        gp.npc[1].worldY = gp.tileSize*31;
    }
    public void setMonster(){

        gp.monster[0] = new MON_Slime(gp);
        gp.monster[0].worldX = gp.tileSize*20;
        gp.monster[0].worldY = gp.tileSize*12;

        gp.monster[1] = new MON_Slime(gp);
        gp.monster[1].worldX = gp.tileSize*25;
        gp.monster[1].worldY = gp.tileSize*12;

    }
}