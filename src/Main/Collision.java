package Main;

import Entities.Entity;

public class Collision {
    GamePanel gamePanel;

    public Collision(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        // CORDINATE OF SOLID AREA:
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftColumn = entityLeftWorldX / gamePanel.tileSize;
        int entityRightColumn = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

            switch (entity.direction) {
                case "up":
                    entityTopRow = (entityTopWorldY - entity.speed)/gamePanel.tileSize;
                    tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftColumn][entityTopRow];
                    tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightColumn][entityTopRow];
                    if ( gamePanel.tileManager.tile[gamePanel.currentMap][tileNum1].collision == true || gamePanel.tileManager.tile[gamePanel.currentMap][tileNum2].collision == true ){
                        entity.collisionOn = true;
                    }
                    break;
                case "down":
                    entityBottomRow = (entityBottomWorldY + entity.speed)/gamePanel.tileSize;
                    tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftColumn][entityBottomRow];
                    tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightColumn][entityBottomRow];
                    if ( gamePanel.tileManager.tile[gamePanel.currentMap][tileNum1].collision == true || gamePanel.tileManager.tile[gamePanel.currentMap][tileNum2].collision == true ){
                        entity.collisionOn = true;
                    }
                    break;
                case "left":
                    entityLeftColumn = (entityLeftWorldX - entity.speed)/gamePanel.tileSize;
                    tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftColumn][entityTopRow];
                    tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftColumn][entityBottomRow];
                    if ( gamePanel.tileManager.tile[gamePanel.currentMap][tileNum1].collision == true || gamePanel.tileManager.tile[gamePanel.currentMap][tileNum2].collision == true ){
                        entity.collisionOn = true;
                    }
                    break;
                case "right":
                    entityRightColumn = (entityRightWorldX + entity.speed)/gamePanel.tileSize;
                    tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightColumn][entityTopRow];
                    tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightColumn][entityBottomRow];
                    if ( gamePanel.tileManager.tile[gamePanel.currentMap][tileNum1].collision == true || gamePanel.tileManager.tile[gamePanel.currentMap][tileNum2].collision == true ){
                        entity.collisionOn = true;
                    }
                    break;
            }
    }
    // Check the objectect collision
    public int checkObject(Entity entity, boolean player){

        int index = 999;
        for(int i=0; i<gamePanel.object[1].length; i++){
            if(gamePanel.object[gamePanel.currentMap][i] != null){
                //get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get the objectect's solid area position
                gamePanel.object[gamePanel.currentMap][i].solidArea.x = gamePanel.object[gamePanel.currentMap][i].worldX + gamePanel.object[gamePanel.currentMap][i].solidArea.x;
                gamePanel.object[gamePanel.currentMap][i].solidArea.y = gamePanel.object[gamePanel.currentMap][i].worldY + gamePanel.object[gamePanel.currentMap][i].solidArea.y;

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
                if(entity.solidArea.intersects(gamePanel.object[gamePanel.currentMap][i].solidArea)){
                    if(gamePanel.object[gamePanel.currentMap][i].collision == true){
                        entity.collisionOn = true;
                        }
                        if(player == true){
                        index = i;
                        }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.object[gamePanel.currentMap][i].solidArea.x = gamePanel.object[gamePanel.currentMap][i].solidAreaDefaultX;
                gamePanel.object[gamePanel.currentMap][i].solidArea.y = gamePanel.object[gamePanel.currentMap][i].solidAreaDefaultY;

            }
        }
        return index;
    }
    // NPC or monster collision
    public int checkEntity(Entity entity, Entity[][] target){

        int index = 999;
        for(int i=0; i<target[1].length; i++){
            if(target[gamePanel.currentMap][i] != null){
                //get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get the object's solid area position
            target[gamePanel.currentMap][i].solidArea.x = target[gamePanel.currentMap][i].worldX + target[gamePanel.currentMap][i].solidArea.x;
            target[gamePanel.currentMap][i].solidArea.y = target[gamePanel.currentMap][i].worldY + target[gamePanel.currentMap][i].solidArea.y;

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
            if(entity.solidArea.intersects(target[gamePanel.currentMap][i].solidArea)){
                if(target[gamePanel.currentMap][i] != entity){
                    entity.collisionOn = true;
                    index = i;
                }
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            target[gamePanel.currentMap][i].solidArea.x = target[gamePanel.currentMap][i].solidAreaDefaultX;
            target[gamePanel.currentMap][i].solidArea.y = target[gamePanel.currentMap][i].solidAreaDefaultY;
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
