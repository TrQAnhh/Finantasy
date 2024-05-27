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

        // Title state
        if(gamePanel.gameState == gamePanel.titleState){
            titleState(code);
        }
        // Game state
        else if(gamePanel.gameState == gamePanel.playState){
            playState(code);
        }
        // Pause state
        else if(gamePanel.gameState == gamePanel.pauseState){
            pauseState(code);
        }
        // Dialogue state
        else if(gamePanel.gameState == gamePanel.dialogueState){
            dialogueState(code);
        }
        // Character state
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
    }
    public void titleState(int code){
        if(code == KeyEvent.VK_W)
        {
            gamePanel.ui.commandNum--;
            if(gamePanel.ui.commandNum < 0)
            gamePanel.ui.commandNum = 3;
        }
        if(code == KeyEvent.VK_S)
        {
            gamePanel.ui.commandNum++;
            if(gamePanel.ui.commandNum > 3)
            gamePanel.ui.commandNum = 0;
        }
        if (code == KeyEvent.VK_ENTER){
            if(gamePanel.ui.commandNum == 0){
                gamePanel.gameState = gamePanel.playState;
                //gamePanel.playMusic(0);
            }
            if(gamePanel.ui.commandNum == 1){

            }
            if(gamePanel.ui.commandNum == 2){

            }
            if(gamePanel.ui.commandNum == 3){
                System.exit(0);
            }
        }
    }
    public void playState(int code){
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
        if(code == KeyEvent.VK_ESCAPE)
        {
            gamePanel.gameState = gamePanel.optionsState;
        }
        if(code == KeyEvent.VK_P) 
        {   
            gamePanel.gameState = gamePanel.pauseState;
        }
        if(code == KeyEvent.VK_C)
        {
            gamePanel.gameState = gamePanel.characterState;
        }
        if(code == KeyEvent.VK_F)
        {
            enterPressed = true;
        }
        if(code == KeyEvent.VK_R) {
            switch (gamePanel.currentMap) {
                case 0:
                    gamePanel.tileManager.loadMap("res/MapData/mapdata.txt", 0); 
                    break;
                case 1:
                    gamePanel.tileManager.loadMap("res/MapData/mapdataDung.txt", 1);
                    break;
            }
        }
        //Debug

        if(code == KeyEvent.VK_T) {
            if(showDebugText == false) {
                showDebugText = true;
            }
            else if(showDebugText == true) {
                showDebugText = false;
            }
        } 
    }
    public void pauseState(int code) {
        if(code == KeyEvent.VK_ESCAPE)
        {
            gamePanel.gameState = gamePanel.playState;
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
        if(code == KeyEvent.VK_W){
            if(gamePanel.ui.slotRow != 0){
                gamePanel.ui.slotRow--;
            }
        }
        if(code == KeyEvent.VK_A){
            if(gamePanel.ui.slotCol != 0){
                gamePanel.ui.slotCol--;
            }
        }
        if(code == KeyEvent.VK_S){
            if(gamePanel.ui.slotRow != 3){
                gamePanel.ui.slotRow++;
            }
        }
        if(code == KeyEvent.VK_D){
            if(gamePanel.ui.slotCol != 4){
                gamePanel.ui.slotCol++;
            }
        }
    }
    public void battleState(int code) {
        if(gamePanel.ui.playerTurn == true){
            if(code == KeyEvent.VK_W){
                gamePanel.ui.interactNum--;
                if(gamePanel.ui.interactNum == -1){
                    gamePanel.ui.interactNum = 2;
                }
            }
            if(code == KeyEvent.VK_S){
                gamePanel.ui.interactNum++;
                if(gamePanel.ui.interactNum == 3){
                    gamePanel.ui.interactNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gamePanel.ui.interactType < 1){
                    gamePanel.ui.interactType = 1;
                }
                else if(gamePanel.ui.interactType == 1){
                    gamePanel.ui.interactType = 0;
                //    gamePanel.ui.playerTurn = false;
                }
            }
            if(code == KeyEvent.VK_ESCAPE){
                if(gamePanel.ui.interactType >= 1){
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
        // equal to number of column number options in chart bar
        int maxCommandNum = 0;
        switch(gamePanel.ui.subState) {
            case 0: maxCommandNum = 5;
                break;
            case 3: maxCommandNum = 1;
                break;
        }
        if(code == KeyEvent.VK_W) {
            gamePanel.ui.commandNum --;
            //gamePanel.playSE(9);
            if(gamePanel.ui.commandNum < 0) {
                gamePanel.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S) {
            gamePanel.ui.commandNum ++;
            //gamePanel.playSE(9);
            if(gamePanel.ui.commandNum > maxCommandNum) {
                gamePanel.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_A) {
            if(gamePanel.ui.subState == 0) {
                if(gamePanel.ui.commandNum == 1 && gamePanel.music.volumeScale > 0) {
                    gamePanel.music.volumeScale --;
                    //gamePanel.music.checkVolume(); // remove "//" when add sound effect and song
                    //gamePanel.playSE(9);
                }
                if(gamePanel.ui.commandNum == 2 && gamePanel.se.volumeScale > 0) {
                    gamePanel.se.volumeScale --;
                    //gamePanel.playSE(9);
                }
            }
        }
        if(code == KeyEvent.VK_D) {
            if(gamePanel.ui.subState == 0) {
                if(gamePanel.ui.commandNum == 1 && gamePanel.music.volumeScale < 5) {
                    gamePanel.music.volumeScale ++;
                    //gamePanel.music.checkVolume(); // remove "//" when add sound effect and song 
                    //gamePanel.playSE(9);
                }
                if(gamePanel.ui.commandNum == 2 && gamePanel.se.volumeScale < 5) {
                    gamePanel.se.volumeScale ++;
                    //gamePanel.playSE(9);
                }
            }
            
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W)
        {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S)
        {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D)
        {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}
