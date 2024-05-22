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
                // gamePanel.playMusic(0);
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
        // Debug
        if(code == KeyEvent.VK_T){
            if(showDebugText == false){
                showDebugText = true;
            }
            else if(showDebugText == true){
                showDebugText = false;
            }
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_ESCAPE)
        {
            gamePanel.gameState = gamePanel.playState;
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
        gamePanel.gameState = gamePanel.playState;
    }
    }
    public void characterState(int code){
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
    public void battleState(int code){
        if(gamePanel.ui.playerTurn == true){
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
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

    
}
