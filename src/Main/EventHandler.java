package Main;


public class EventHandler {

    GamePanel gamePanel;
    eventRect eventRect[][][];
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;
    boolean first, second, third, fourth, fifth;
    boolean[] checkHappened;
    public EventHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
            checkHappened = new boolean[6];
            for(int i = 0; i < 6; ++i) {
                checkHappened[i] = false;
            }
            
            eventRect = new eventRect[gamePanel.maxMap][gamePanel.maxWorldColumn][gamePanel.maxWorldRow];
            int map = 0;
            int col = 0;
            int row = 0;
            while(map < gamePanel.maxMap && col < gamePanel.maxWorldColumn && row < gamePanel.maxWorldRow){

            eventRect[map][col][row] = new eventRect();
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

            // HEALING BY STATUE AT X1 = 13 (COLS), Y1 = 33 (ROWS) AND X2 = 13 (COLS), Y2 = 32 (ROWS)
            if( hit(0, 13,33,"any") == true ) {
                healingGamePanelPool(gamePanel.playState);}
            // TELEPORT FROM NORMAL WORLD TO DUNGEON AT COORDINATE X = 31 (COLS), Y = 43 (ROWS)
            else if( hit(0, 14, 12, "any") == true || hit(0, 14, 13, "any") == true ) {
                teleport(1, 31, 43, gamePanel.dungeon);
            }

            // TELEPORT FROM DUNGEON BACK TO NORMAL WORLD AT COORDINATE X = 16 (COLS), Y = 15 (ROWS)
            else if( hit(1, 40, 43, "any") == true || hit(1, 41, 43, "any") == true || hit(1, 42, 43, "any") == true ) {
                teleport(0, 16, 15, gamePanel.outside);
            }

            // MONSTER CHECKED PLACED MAP 0
            if(hit(0,15,18,"any") == true) {
                if(gamePanel.keyHandler.enterPressed == true){
                    gamePanel.gameState = gamePanel.battleState;
                    gamePanel.ui.indexBattle = 1;
                }
            }
            if(hit(0,17,15,"any") == true) {
                if(gamePanel.keyHandler.enterPressed == true){
                    gamePanel.gameState = gamePanel.battleState;
                    gamePanel.ui.indexBattle = 2;
                }
            }
            if(hit(0,14,16,"any") == true) {
                if(gamePanel.keyHandler.enterPressed == true){
                    gamePanel.gameState = gamePanel.battleState;
                    gamePanel.ui.indexBattle = 3;
                }
            }
            //MONSTER CHECKED PLACED MAP 1
            if((checkHappened[5] == false) && hit(1,36,31,"any") == true) {
                gamePanel.gameState = gamePanel.battleState;
                gamePanel.ui.indexBattle = 7;
                checkHappened[5] = true;
            }
            if((checkHappened[4] == false) && hit(1,25,31,"any") == true) {
                gamePanel.gameState = gamePanel.battleState;
                gamePanel.ui.indexBattle = 8;
                checkHappened[4] = true;
            }
            if((checkHappened[0] == false) && (hit(1, 19, 34, "any") == true || hit(1, 20, 34, "any") == true || hit(1, 21, 34, "any") == true)) {
                gamePanel.gameState = gamePanel.battleState;
                gamePanel.ui.indexBattle = 4;
                checkHappened[0] = true;
            }
            if((checkHappened[1] == false) && (hit(1, 30, 40, "any") == true || hit(1, 31, 40, "any") == true || hit(1, 32, 40, "any") == true)) {
                gamePanel.gameState = gamePanel.battleState;
                gamePanel.ui.indexBattle = 6;
                checkHappened[1] = true;
            }
            if((checkHappened[2] == false) && (hit(1, 41, 36, "any") == true || hit(1, 42, 36, "any") == true || hit(1, 43, 36, "any") == true)) {
                gamePanel.gameState = gamePanel.battleState;
                gamePanel.ui.indexBattle = 5;
                checkHappened[2] = true;
            }
            if(gamePanel.ui.gateCounterKill == 3 && (hit(1, 33, 23, "any") == true || hit(1, 32, 23, "any") == true || hit(1, 31, 23, "any") == true || hit(1, 30, 23, "any") == true))  {
                gamePanel.gameState = gamePanel.battleState;
                gamePanel.ui.indexBattle = 9;
            }
            if(gamePanel.bossBattleOn) {
                gamePanel.gameState = gamePanel.bossBattleState;
                gamePanel.ui.indexBattle = 10;
            }
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
    public void healingGamePanelPool(int gameState) {
        if (gamePanel.keyHandler.enterPressed == true){
            gamePanel.gameState = gameState;
            gamePanel.ui.addMessage("Your life has been recovered!");
            gamePanel.player.life = gamePanel.player.maxLife;
            setDyingAttribute();
            gamePanel.aSetter.setMonster();
            gamePanel.ui.listofMonster.clear();
        }

    }
    public void setDyingAttribute() {
        for(int i = 0; i < 3; ++i) {
            gamePanel.monster[1][i].dying = false;
        }
    }   
    public void teleport(int map, int col, int row, int area) {
            gamePanel.gameState = gamePanel.transitionState;
            gamePanel.nextArea = area;
            gamePanel.currentMap = map;
            tempMap = map;
            tempCol = col;
            tempRow = row;
            canTouchEvent = false;
            //gamePanel.playSE(13);
    }
   
}