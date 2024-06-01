package Main;

public class EventHandler {

    GamePanel gp;
    eventRect eventRect[][][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp){
        this.gp = gp;

            eventRect = new eventRect[gp.maxMap][gp.maxWorldColumn][gp.maxWorldRow];
            int map = 0;
            int col = 0;
            int row = 0;
            while(map < gp.maxMap && col < gp.maxWorldColumn && row < gp.maxWorldRow){

            eventRect[map][col][row] = new eventRect();
            eventRect[map][col][row].x = 48;
            eventRect[map][col][row].y = 48;
            eventRect[map][col][row].width = gp.tileSize;
            eventRect[map][col][row].height = gp.tileSize;

            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;

            if(col == gp.maxWorldColumn){
                col = 0;
                row++;

                if(row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }

    }

    public void checkEvent() {
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }
        if(canTouchEvent == true){

            //gp.gameState = gp.battleState;
            //gp.ui.indexBattle = 1;

            if( hit(0, 33,23,"up") == true ) {
                healingPool(gp.dialogueState);}
            // TELEPORT FROM NORMAL WORLD TO DUNGEON AT COORDINATE X = 16 (COLS), Y = 48 (ROWS)
            else if( hit(0, 14, 12, "any") == true || hit(0, 14, 13, "any") == true ) {
                teleport(1, 16, 48, gp.dungeon);}

            // TELEPORT FROM DUNGEON BACK TO NORMAL WORLD AT COORDINATE X = 16 (COLS), Y = 15 (ROWS)
            else if( hit(1, 20, 46, "any") == true ) {
                teleport(0, 16, 15, gp.outside);}
        }
    }
    public boolean hit(int map, int col, int row, String reqDirection){

        boolean hit = false;
        if(map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row *gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false){
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;

            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }
    public void healingPool(int gameState){
        if (gp.keyHandler.enterPressed == true){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Your life has been recovered!";
            gp.player.life = gp.player.maxLife;
            gp.aSetter.setMonster();
        }

    }
    public void teleport(int map, int col, int row, int area) {
            gp.gameState = gp.transitionState;
            gp.currentMap = map;
            gp.nextArea = area;
            tempMap = map;
            tempCol = col;
            tempRow = row;
            canTouchEvent = false;
            //gp.playSE(13);
    }
}