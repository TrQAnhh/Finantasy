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

    public int checkObject(Entity entity, boolean player){

        int index = 999;

        for ( int i = 0 ; i < gamePanel.object.length ; i++ ) {
            if ( gamePanel.object[i] != null ) {

                // GET ENTITY'S SOLID AREA POSITION:
                    entity.solidArea.x = entity.worldX + entity.solidArea.x ;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // GET THE OBJECT'S SOLID AREA POSITION:
                    gamePanel.object[i].solidArea.x = gamePanel.object[i].worldX + gamePanel.object[i].solidArea.x;
                    gamePanel.object[i].solidArea.y = gamePanel.object[i].worldY + gamePanel.object[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.object[i].solidArea)){
                            // CHECK IF THIS OBJECT IS SOLID OR NOT:
                                if ( gamePanel.object[i].collision == true ){
                                    entity.collisionOn = true;
                                }
                                if ( player == true ) {
                                    index = i;
                                }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.object[i].solidArea)){
                            if ( gamePanel.object[i].collision == true ){
                                entity.collisionOn = true;
                            }
                            if ( player == true ) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.object[i].solidArea)){
                            if ( gamePanel.object[i].collision == true ){
                                entity.collisionOn = true;
                            }
                            if ( player == true ) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.object[i].solidArea)){
                            if ( gamePanel.object[i].collision == true ){
                                entity.collisionOn = true;
                            }
                            if ( player == true ) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.object[i].solidArea.x = gamePanel.object[i].solidAreaDefaultX;
                gamePanel.object[i].solidArea.y = gamePanel.object[i].solidAreaDefaultY;
            }

        }
        return index;
    }
}
