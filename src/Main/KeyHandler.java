package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, charPressed = false;

    // Debug
    boolean showDebugText = false;

    public KeyHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITLE STATE:
            if(gamePanel.gameState == gamePanel.titleState){
                titleState(code);
            }
        // GAME STATE:
            else if(gamePanel.gameState == gamePanel.playState){
                playState(code);
            }
        // PAUSE STATE:
            else if(gamePanel.gameState == gamePanel.pauseState){
                pauseState(code);
            }
        // DIALOGUE STATE:
            else if(gamePanel.gameState == gamePanel.dialogueState){
                dialogueState(code);
            }
        // CHARACTER STATE:
            else if(gamePanel.gameState == gamePanel.characterState){
                characterState(code);
            }
            else if(gamePanel.gameState == gamePanel.battleState){
                battleState(code);
            }
        //OPTION STATE
            else if(gamePanel.gameState == gamePanel.optionsState) {
                optionsState(code);
            }
            // GAMEOVER STATE
            else if(gamePanel.gameState == gamePanel.gameOverState){
                gameOverState(code);
            }
            // TRADE STATE
            else if(gamePanel.gameState == gamePanel.tradeState){
                tradeState(code);
            }
    }
    public void titleState(int code){
        if(code == KeyEvent.VK_W)
        {
            gamePanel.ui.commandNum--;
            if(gamePanel.ui.commandNum < 0)
            gamePanel.ui.commandNum = 2;
        }
        if(code == KeyEvent.VK_S)
        {
            gamePanel.ui.commandNum++;
            if(gamePanel.ui.commandNum > 2)
            gamePanel.ui.commandNum = 0;
        }
        if (code == KeyEvent.VK_ENTER){
            // PRESS ENTER WITH PLAY BUTTON:
                if(gamePanel.ui.commandNum == 0){
                    gamePanel.tempGameState = gamePanel.titleState;
                    gamePanel.stopMusic();
                    gamePanel.playMusic(0);
                    gamePanel.gameState = gamePanel.playState;
                }
            // PRESS ENTER WITH SETTING BUTTON:
                if(gamePanel.ui.commandNum == 1){
                    gamePanel.tempGameState = gamePanel.titleState;
                    gamePanel.gameState = gamePanel.optionsState;
                }
            // PRESS ENTER WITH EXIT BUTTON:
                if(gamePanel.ui.commandNum == 2){
                    System.exit(0);
                }
        }
    }
    
    public void playState(int code){
        // W-A-S-D MOVEMENT:
        if(code == KeyEvent.VK_W)
        {
            upPressed = true;
        }
        if(code == KeyEvent.VK_A)
        {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S)
        {
            downPressed = true;
        }
        if(code == KeyEvent.VK_D)
        {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        // CHANGE STATES:
        // PRESS ESCAPE TOP PAUSE GAME AND SETTINGS:
        if(code == KeyEvent.VK_ESCAPE)
        {
            gamePanel.gameState = gamePanel.pauseState;
        }
        
        // PRESS C TO CHANGE TO CHARACTER STATE:
        if(code == KeyEvent.VK_C)
        {
            gamePanel.gameState = gamePanel.characterState;
        }
        // Debug
        if(code == KeyEvent.VK_T){
            if(showDebugText == false){
                showDebugText = true;
            }
            else if(showDebugText == true){
                showDebugText = false;
            }
        }
    if(code == KeyEvent.VK_R) {
        System.out.println(+gamePanel.currentMap);
        switch (gamePanel.currentMap) {
            case 0:
                gamePanel.tileManager.loadMap("res/MapData/mapdata.txt", 0);
                break;
            case 1:
                gamePanel.tileManager.loadMap("res/MapData/mapdataDung.txt", 1);
                break;
        }
    }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_ESCAPE)
        {
            gamePanel.gameState = gamePanel.playState;
        }
        if ( code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        // INTERACT WITH BUTTONS IN PAUSE SCREEN:
            if(code == KeyEvent.VK_W)
            {
                gamePanel.ui.pauseCommandNum--;
                if(gamePanel.ui.pauseCommandNum < 0)
                    gamePanel.ui.pauseCommandNum = 2;
            }
            if(code == KeyEvent.VK_S)
            {
                gamePanel.ui.pauseCommandNum++;
                if(gamePanel.ui.pauseCommandNum > 2)
                    gamePanel.ui.pauseCommandNum = 0;
            }
            if (code == KeyEvent.VK_ENTER){
                // PRESS ENTER WITH PLAY BUTTON:
                if(gamePanel.ui.pauseCommandNum == 0){
                    gamePanel.gameState = gamePanel.playState;
                }
                // PRESS ENTER WITH SETTING BUTTON:
                if(gamePanel.ui.pauseCommandNum == 1){
                    gamePanel.tempGameState = gamePanel.pauseState;
                    gamePanel.gameState = gamePanel.optionsState;
                }
                // PRESS ENTER WITH EXIT BUTTON:
                if(gamePanel.ui.pauseCommandNum == 2){
                    System.exit(0);
                }
            }
    }

        
    public void dialogueState(int code) {
        if(code == KeyEvent.VK_ENTER){
            gamePanel.gameState = gamePanel.playState;
        }
    }
    public void characterState(int code) {
        if(code == KeyEvent.VK_C){
            gamePanel.gameState = gamePanel.playState;
        }
        
        if(code == KeyEvent.VK_ENTER){
            gamePanel.player.selectItem();
        }
        playerInventory(code);
    }
    public void battleState(int code){
        if(gamePanel.ui.orderTurn == 0){
            if(code == KeyEvent.VK_W){
                gamePanel.ui.interactNum--;
                if(gamePanel.ui.interactNum == -1){
                    gamePanel.ui.interactNum = gamePanel.ui.numberOfInteractNum;
                }
            }
            if(code == KeyEvent.VK_S){
                gamePanel.ui.interactNum++;
                if(gamePanel.ui.interactNum > gamePanel.ui.numberOfInteractNum){
                    gamePanel.ui.interactNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gamePanel.ui.interactType < 2){
                    gamePanel.ui.interactType++;
                }
                else if(gamePanel.ui.interactType == 2){
                    gamePanel.ui.interactType = 0;
                    gamePanel.player.battleAction(gamePanel.ui.selectAction, gamePanel.ui.choosingEquipAction, gamePanel.ui.choosingEnemyAction);
                }
                gamePanel.ui.interactNum = 0;
            }
            if(code == KeyEvent.VK_ESCAPE){
                if(gamePanel.ui.interactType > 0){
                    gamePanel.ui.interactType--;
                }
            }
        }
    }
    public void optionsState(int code) {
        if(code == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.playState;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if(code == KeyEvent.VK_W){
            gamePanel.ui.settingCommandNum--;
            if (gamePanel.ui.settingCommandNum < 0 ){
                gamePanel.ui.settingCommandNum = 4;
            }
        }

        if(code == KeyEvent.VK_S){
            gamePanel.ui.settingCommandNum++;
            if ( gamePanel.ui.settingCommandNum > 4 ) {
                gamePanel.ui.settingCommandNum = 0;
            }
        }
        // PRESS ENTER:
            if (code == KeyEvent.VK_ENTER){
                // PRESS ENTER WITH SFX BUTTON & VOLUME BAR:
                    if(gamePanel.ui.settingCommandNum == 1){

                    }
                // PRESS ENTER WITH CONTROL BUTTON:
                    if(gamePanel.ui.settingCommandNum == 2){

                    }
                // PRESS ENTER WITH EXIT BUTTON:
                    if(gamePanel.ui.settingCommandNum == 3){
                        System.exit(0);
                    }
                // PRESS ENTER WITH BACK BUTTON:
                    if(gamePanel.ui.settingCommandNum == 4){
                        gamePanel.gameState = gamePanel.tempGameState;
                    }
            }
            // MUSIC BUTTON & VOLUME BAR:
                if ( code == KeyEvent.VK_A ) {
                    if (gamePanel.ui.settingCommandNum == 0) {
                        if (gamePanel.music.volumeScale > 0) {
                            gamePanel.music.volumeScale--;
                            gamePanel.music.checkVolume();
                        }
                    }
                    if (gamePanel.ui.settingCommandNum == 1) {
                        if (gamePanel.se.volumeScale > 0) {
                            gamePanel.se.volumeScale--;
                            gamePanel.se.checkVolume();
                        }
                    }
                }
                if ( code == KeyEvent.VK_D ) {
                    if (gamePanel.ui.settingCommandNum == 0) {
                        if (gamePanel.music.volumeScale < 11) {
                            gamePanel.music.volumeScale++;
                            gamePanel.music.checkVolume();
                        }
                    }
                    if (gamePanel.ui.settingCommandNum == 1) {
                        if (gamePanel.se.volumeScale < 11) {
                            gamePanel.se.volumeScale++;
                            gamePanel.se.checkVolume();
                        }
                    }
                }
    }
    public void gameOverState(int code){
        if(code == KeyEvent.VK_W){
            gamePanel.ui.commandNum--;
            if(gamePanel.ui.commandNum < 0){
                gamePanel.ui.commandNum = 1;
            }
            // gamePanel.playSE(9);
        }
        if(code == KeyEvent.VK_S){
            gamePanel.ui.commandNum++;
            if(gamePanel.ui.commandNum > 1){
                gamePanel.ui.commandNum = 0;
            }
            // gamePanel.playSE(9);
        }
        if(code == KeyEvent.VK_ENTER){
            if(gamePanel.ui.commandNum == 0){
                gamePanel.gameState = gamePanel.playState;
                gamePanel.retry();
            }
            else if(gamePanel.ui.commandNum == 1){
                gamePanel.gameState = gamePanel.titleState;
                gamePanel.restart();
            }
        }
    }
    public void tradeState(int code){
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(gamePanel.ui.subState == 0){
            if(code == KeyEvent.VK_W){
                gamePanel.ui.tradeCommandNum--;
                if (gamePanel.ui.tradeCommandNum < 0) {
                    gamePanel.ui.tradeCommandNum = 2;
                }
                // gamePanel.playSE(9);
            }
            if(code == KeyEvent.VK_S){
                gamePanel.ui.tradeCommandNum++;
                if (gamePanel.ui.tradeCommandNum > 2) {
                    gamePanel.ui.tradeCommandNum = 0;
                }
                // gamePanel.playSE(9);
            }
        }
        if(gamePanel.ui.subState == 1){
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gamePanel.ui.subState = 0;
            }
        }
        if(gamePanel.ui.subState == 2){
            playerInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gamePanel.ui.subState = 0;
            }
        }
    }
    public void playerInventory(int code){
        if(code == KeyEvent.VK_W){
            if(gamePanel.ui.playerSlotRow != 0){
                gamePanel.ui.playerSlotRow--;
            }
        }
        if(code == KeyEvent.VK_A){
            if(gamePanel.ui.playerSlotCol != 0){
                gamePanel.ui.playerSlotCol--;
            }
        }
        if(code == KeyEvent.VK_S){
            if(gamePanel.ui.playerSlotRow != 3){
                gamePanel.ui.playerSlotRow++;
            }
        }
        if(code == KeyEvent.VK_D){
            if(gamePanel.ui.playerSlotCol != 4){
                gamePanel.ui.playerSlotCol++;
            }
        }
    }
    public void npcInventory(int code){
        if(code == KeyEvent.VK_W){
            if(gamePanel.ui.npcSlotRow != 0){
                gamePanel.ui.npcSlotRow--;
            }
        }
        if(code == KeyEvent.VK_A){
            if(gamePanel.ui.npcSlotCol != 0){
                gamePanel.ui.npcSlotCol--;
            }
        }
        if(code == KeyEvent.VK_S){
            if(gamePanel.ui.npcSlotRow != 3){
                gamePanel.ui.npcSlotRow++;
            }
        }
        if(code == KeyEvent.VK_D){
            if(gamePanel.ui.npcSlotCol != 4){
                gamePanel.ui.npcSlotCol++;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W)
        {
            upPressed = false;
        }
        if(code == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S)
        {
            downPressed = false;
        }
        if(code == KeyEvent.VK_D)
        {
            rightPressed = false;
        }
    //    throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}
