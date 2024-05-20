package Main;

public class EventHandler {

    GamePanel gamePanel;
    EventRect eventRect[] [];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        eventRect = new EventRect[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];

        int col = 0;
        int row = 0;
        while(col < gamePanel.maxWorldColumn && row < gamePanel.maxWorldRow){
            
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = gamePanel.tileSize;
            eventRect[col][row].height = gamePanel.tileSize;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gamePanel.maxWorldColumn){
                col = 0;
                row++;
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
     if (canTouchEvent == true){
        if(hit(22,12,"any") == true) {
            if(gamePanel.keyHandler.enterPressed == true){
            gamePanel.gameState = gamePanel.battleState;
            gamePanel.ui.indexBattle = 1;
            }
        }
        if (hit(33,23,"up") == true) {healingamePanelool(33, 23, gamePanel.dialogueState);}
     }
    }
    public boolean hit(int col, int row, String reqDirection){

        boolean hit = false;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;
        eventRect[col][row].x = col*gamePanel.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gamePanel.tileSize + eventRect[col][row].y;

        if(gamePanel.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false){
            if(gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
                previousEventX = gamePanel.player.worldX;
                previousEventY = gamePanel.player.worldY;
            }
        }

        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
        return hit;
    }
    public void healingamePanelool(int col, int row, int gameState){
        if (gamePanel.keyHandler.enterPressed == true){
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "Your life has been recovered!";
            gamePanel.player.life = gamePanel.player.maxLife;
            gamePanel.aSetter.setMonster();
        }

    }
}
