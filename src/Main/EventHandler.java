package Main;

public class EventHandler {

    GamePanel gp;
    eventRect eventRect[] [];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new eventRect[gp.maxWorldColumn][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while(col < gp.maxWorldColumn && row < gp.maxWorldRow){
            
            eventRect[col][row] = new eventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = gp.tileSize;
            eventRect[col][row].height = gp.tileSize;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldColumn){
                col = 0;
                row++;
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
     if (canTouchEvent == true){
        if(hit(22,12,"any") == true) {
            if(gp.keyHandler.enterPressed == true)
            gp.gameState = gp.battleState;
            gp.ui.indexBattle = 1;
        }
        if (hit(33,23,"up") == true) {healingPool(33, 23, gp.dialogueState);}
     }
    }
    public boolean hit(int col, int row, String reqDirection){

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
        return hit;
    }
    public void healingPool(int col, int row, int gameState){
        if (gp.keyHandler.enterPressed == true){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Your life has been recovered!";
            gp.player.life = gp.player.maxLife;
            gp.aSetter.setMonster();
        }

    }
}
