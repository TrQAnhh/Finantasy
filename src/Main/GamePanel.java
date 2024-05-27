package Main;

import Entities.Entity;
import Entities.Player;
import Map.TileManager;
import Objects.SuperObject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS:
        final int fps = 60;
        final int originalTileSize = 16; // 16x16 tile
        final int scale = 3;
     // TILE SETTINGS:
        public final int tileSize = originalTileSize * scale; // 48x48 tile

    // SCREEN SETTINGS:
        public final int maxScreenColumn = 18;
        public final int maxScreenRow = 14;
        public final int screenWidth = tileSize * maxScreenColumn;
        public final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS:
        public final int maxWorldColumn = 64; // can be adjusted
        public final int maxWorldRow = 64; // can be adjusted
//        public final int worldWidth = tileSize * maxWorldColumn;
//        public final int worldHeight = tileSize * maxWorldRow;

   // GAME STATE VARIABLES:
        public int gameState;
        public final int titleState = 0;
        public final int playState = 1;
        public final int pauseState = 2;

        public final int dialogueState = 3;
        public final int optionState = 5;

   // INSTANTIATE OBJECTS:
            public KeyHandler keyHandler = new KeyHandler(this);
            Thread gameThread;
        // TileManager CLASS
            TileManager tileManager = new TileManager(this);
        // Collision CLASS
            public CollisionChecker collisionChecker = new CollisionChecker(this);
        // AssetSetter CLASS:
            public AssetSetter aSetter = new AssetSetter(this);
        // ENTITIES AND OBJECTS:
            // Player CLASS
                public Player player = new Player(this,keyHandler);
            // Objects ARRAY:
                public SuperObject obj[] = new SuperObject[10];
            // NPC ARRAY:
                public Entity npc[] = new Entity[10];

        // Sound CLASS:
            Sound music = new Sound();
            Sound se = new Sound();
        // UI CLASS:
            public UI ui = new UI(this);


   // CONSTRUCTOR:
        public GamePanel(){
                this.setPreferredSize(new Dimension(screenWidth,screenHeight));
                this.setBackground(Color.WHITE);
                this.setDoubleBuffered(true);
                this.addKeyListener(keyHandler);
                this.setFocusable(true);
        }

   // METHODS:
        public void setupGame(){
            // DEFAULT GAME STATE:
                gameState = titleState;
            // SET OBJECTS METHOD:
                aSetter.setObject();
            // SET NPC METHODS:
                aSetter.setNPC();
                playMusic(1);


        }

        public void startGameThread(){
            gameThread = new Thread(this);
            gameThread.start();
        }

        @Override
        public void run() {

            double drawInterval = 1000000000/fps; // redraw the screen each 0.016 seconds
            double nextDrawTime = System.nanoTime() + drawInterval;

            while ( gameThread != null ) {

                // 1. UPDATE: Update information such as character positions,...
                    update();
                // 2. DRAW: redraw the screen with the updated information
                    repaint();

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                try {
                    if (remainingTime < 0 ){
                        remainingTime = 0;
                    }

                    Thread.sleep((long) remainingTime);

                    nextDrawTime = nextDrawTime + drawInterval;

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        }

        public void update(){
            if ( gameState == playState ) {
                // PLAYER'S POSITIONS UPDATE:
                    player.update();
                // NPC POSITIONS UPDATE:
                for ( int i = 0 ; i < npc.length ; i++ ) {
                    if (npc[i] != null){
                        npc[i].update(this);
                    }
                }
            }
            if ( gameState == pauseState ) {
                // UPDATE LATER
            }
        }

        public void paintComponent(Graphics graphics){

            super.paintComponent(graphics);

            Graphics2D g2 = (Graphics2D) graphics;

            // TITLE SCREEN:
                if ( gameState == titleState ) {
                    ui.draw(g2);
                } // OTHERS
                else {
                    // DRAW TILES:
                        tileManager.draw(g2);

                    // DRAW OBJECTS:
                    for ( int i = 0 ; i < obj.length ; i++ ) {
                        if ( obj[i] != null ) {
                            obj[i].draw(g2,this);
                        }
                    }
                    // DRAW NPC:
                        for ( int i = 0 ; i < npc.length ; i++ ) {
                            if ( npc[i] != null ) {
                                npc[i].draw(g2, this);
                            }
                        }

                    // DRAW PLAYERS:
                        player.paintComponent(g2);

                    // UI
                        ui.draw(g2);
                }

            g2.dispose();
        }

    // GAME THEME SONG:
        public void playMusic(int i){

            music.setFile(i);
            music.play();
            music.loop();
        }

        public void stopMusic(){
            music.stop();
        }
    // SOUND EFFECTS:
        public void playSE(int i){
            se.setFile(i);
            se.play();
        }
}
