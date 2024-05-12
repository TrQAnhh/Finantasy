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
}
