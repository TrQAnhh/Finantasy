package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    GamePanel gamePanel;

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
        if ( gamePanel.gameState == gamePanel.titleState ) {
            if (code == KeyEvent.VK_W) {
                gamePanel.ui.commandNum--;
                if ( gamePanel.ui.commandNum < 0 ) {
                    gamePanel.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gamePanel.ui.commandNum++;
                if ( gamePanel.ui.commandNum > 2 ) {
                    gamePanel.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if ( gamePanel.ui.commandNum == 0 ) {
                    gamePanel.gameState = gamePanel.playState;
                    gamePanel.playMusic(0);
                }
                if ( gamePanel.ui. commandNum == 1 ) {
                    // UPDATE SETTING LATER
                }
                if ( gamePanel.ui.commandNum == 2 ) {
                    System.exit(0);
                }
            }
        }
    // PLAY STATE:
        if ( gamePanel.gameState == gamePanel.playState ) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE){
                gamePanel.gameState = gamePanel.pauseState;
            }
        }
    // PAUSE STATE:
         else if ( gamePanel.gameState == gamePanel.pauseState ) {
            if (code == KeyEvent.VK_ESCAPE){
                gamePanel.gameState = gamePanel.playState;
            }
        }
    // DIALOGUE STATE:
        else if ( gamePanel.gameState == gamePanel.dialogueState ) {
            if (code == KeyEvent.VK_ENTER){
                gamePanel.gameState = gamePanel.playState;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
    // CHECK IF THE PLAYER IS PAUSING THE GAME
            if (code == KeyEvent.VK_W){
                upPressed = false;
            }
            if (code == KeyEvent.VK_S){
                downPressed = false;
            }
            if (code == KeyEvent.VK_D){
                rightPressed = false;
            }
            if (code == KeyEvent.VK_A){
                leftPressed = false;
            }
    }

}
