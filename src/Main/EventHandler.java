package Main;

public class EventHandler {

    GamePanel gamePanel;
    EventRect eventRect[][][];
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;

            eventRect = new EventRect[gamePanel.maxMap][gamePanel.maxWorldColumn][gamePanel.maxWorldRow];
            int map = 0;
            int col = 0;
            int row = 0;
            while(map < gamePanel.maxMap && col < gamePanel.maxWorldColumn && row < gamePanel.maxWorldRow){

            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 48;
            eventRect[map][col][row].y = 48;
            eventRect[map][col][row].width = gamePanel.tileSize;
            eventRect[map][col][row].height = gamePanel.tileSize;

            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;

            if(col == gamePanel.maxWorldColumn){
                col = 0;
                row++;

                if(row == gamePanel.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }

    }

    public void checkEvent() {
        int xDistance = Math.abs(gamePanel.player.worldX - previousEventX);
        int yDistance = Math.abs(gamePanel.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gamePanel.tileSize){
            canTouchEvent = true;
        }
        if(canTouchEvent == true){

            //gamePanel.gameState = gamePanel.battleState;
            //gamePanel.ui.indexBattle = 1;

            if( hit(0, 13,33,"any") == true ) {    // ???????????????????????
                healingGamePanelPool(gamePanel.dialogueState);}
            // TELEPORT FROM NORMAL WORLD TO DUNGEON AT COORDINATE X = 16 (COLS), Y = 48 (ROWS)
            else if( hit(0, 14, 12, "any") == true || hit(0, 14, 13, "any") == true ) {
                teleport(1, 16, 48, gamePanel.dungeon);}

            // TELEPORT FROM DUNGEON BACK TO NORMAL WORLD AT COORDINATE X = 16 (COLS), Y = 15 (ROWS)
            else if( hit(1, 20, 46, "any") == true ) {
                teleport(0, 16, 15, gamePanel.outside);}
        }
    }
    public boolean hit(int map, int col, int row, String reqDirection){

        boolean hit = false;
        if(map == gamePanel.currentMap) {
            gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
            gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;
            eventRect[map][col][row].x = col*gamePanel.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row *gamePanel.tileSize + eventRect[map][col][row].y;

            if(gamePanel.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false){
                if(gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                    hit = true;
                    previousEventX = gamePanel.player.worldX;
                    previousEventY = gamePanel.player.worldY;
                }
            }

            gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
            gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;

            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }
    public void healingGamePanelPool(int gameState){
        if (gamePanel.keyHandler.enterPressed == true){
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "Your life has been recovered!";
            gamePanel.player.life = gamePanel.player.maxLife;
            gamePanel.aSetter.setMonster();
        }

    }
    public void teleport(int map, int col, int row, int area) {
            gamePanel.gameState = gamePanel.transitionState;
            gamePanel.currentMap = map;
            gamePanel.nextArea = area;
            tempMap = map;
            tempCol = col;
            tempRow = row;
            canTouchEvent = false;
            //gamePanel.playSE(13);
    }
}