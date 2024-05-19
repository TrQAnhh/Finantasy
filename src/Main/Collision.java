package Main;

import Entities.Entity;

public class Collision {
    GamePanel gamePanel;
    public Collision(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public void checkTile(Entity entity){
        // CORDINATE OF SOLID AREA:
            int entityLeftWorldX = entity.worldX + entity.solidArea.x;
            int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
            int entityTopWorldY = entity.worldY + entity.solidArea.y;
            int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

            int entityLeftColumn = entityLeftWorldX / gamePanel.tileSize;
            int entityRightColumn = entityRightWorldX / gamePanel.tileSize;
            int entityTopRow = entityTopWorldY / gamePanel.tileSize;
            int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

            int tileNum1,tileNum2;

            switch (entity.direction) {
                case "up":
                    entityTopRow = (entityTopWorldY - entity.speed)/gamePanel.tileSize;
                    tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftColumn][entityTopRow];
                    tileNum2 = gamePanel.tileManager.mapTileNum[entityRightColumn][entityTopRow];
                    if ( gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true ){
                        entity.collisionOn = true;
                    }
                    break;
                case "down":
                    entityBottomRow = (entityBottomWorldY + entity.speed)/gamePanel.tileSize;
                    tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftColumn][entityBottomRow];
                    tileNum2 = gamePanel.tileManager.mapTileNum[entityRightColumn][entityBottomRow];
                    if ( gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true ){
                        entity.collisionOn = true;
                    }
                    break;
                case "left":
                    entityLeftColumn = (entityLeftWorldX - entity.speed)/gamePanel.tileSize;
                    tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftColumn][entityTopRow];
                    tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftColumn][entityBottomRow];
                    if ( gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true ){
                        entity.collisionOn = true;
                    }
                    break;
                case "right":
                    entityRightColumn = (entityRightWorldX + entity.speed)/gamePanel.tileSize;
                    tileNum1 = gamePanel.tileManager.mapTileNum[entityRightColumn][entityTopRow];
                    tileNum2 = gamePanel.tileManager.mapTileNum[entityRightColumn][entityBottomRow];
                    if ( gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true ){
                        entity.collisionOn = true;
                    }
                    break;
            }
    }
    // Check the objectect collision
    public int checkObject(Entity entity, boolean player){

        int index = 999;
        for(int i=0; i<gamePanel.object.length; i++){
            if(gamePanel.object[i] != null){
                //get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get the objectect's solid area position
                gamePanel.object[i].solidArea.x = gamePanel.object[i].worldX + gamePanel.object[i].solidArea.x;
                gamePanel.object[i].solidArea.y = gamePanel.object[i].worldY + gamePanel.object[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(gamePanel.object[i].solidArea)){
                    if(gamePanel.object[i].collision == true){
                        entity.collisionOn = true;
                        }
                        if(player == true){
                        index = i;
                        }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.object[i].solidArea.x = gamePanel.object[i].solidAreaDefaultX;
                gamePanel.object[i].solidArea.y = gamePanel.object[i].solidAreaDefaultY;

            }
        }
        return index;
    }
    // NPC or monster collision
    public int checkEntity(Entity entity, Entity[] target){

        int index = 999;
        for(int i=0; i<target.length; i++){
            if(target[i] != null){
                //get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get the object's solid area position
            target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
            target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
            if(entity.solidArea.intersects(target[i].solidArea)){
                if(target[i] != entity){
                    entity.collisionOn = true;
                    index = i;
                }
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            target[i].solidArea.x = target[i].solidAreaDefaultX;
            target[i].solidArea.y = target[i].solidAreaDefaultY;
            }   
        }
        return index;
    }
    // Check the collision of NPC/Monster to Player
    public void checkPlayer(Entity entity){
        //get entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        //get the object's solid area position
    gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
    gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if(entity.solidArea.intersects(gamePanel.player.solidArea)){
                    entity.collisionOn = true;
                    }

                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects(gamePanel.player.solidArea)){
                        entity.collisionOn = true;
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if(entity.solidArea.intersects(gamePanel.player.solidArea)){
                        entity.collisionOn = true;
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if(entity.solidArea.intersects(gamePanel.player.solidArea)){
                        entity.collisionOn = true;
                }
                break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
    }
}
