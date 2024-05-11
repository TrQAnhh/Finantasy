package Main;

import Entities.Player;
import Map.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS:
        final int fps = 60;
        final int originalTileSize = 16; // 16x16 tile
        final int scale = 3;

        public final int tileSize = originalTileSize * scale; // 48x48 tile
        public final int maxScreenColumn = 16;
        public final int maxScreenRow = 12;
        public final int screenWidth = tileSize * maxScreenColumn; // 768 pixels
        public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    // WORLD SETTINGS:
        public final int maxWorldColumn = 50; // can be adjusted later
        public final int maxWorldRow = 50; // can be adjusted later
        public final int worldWidth = tileSize * maxWorldColumn;
        public final int worldHeight = tileSize * maxWorldRow;

   // INSTANTIATE OBJECTS:
        KeyHandler keyHandler = new KeyHandler();
        Thread gameThread;
        // Player
            public Player player = new Player(this,keyHandler);
        // TileManager
            TileManager tileManager = new TileManager(this);
   // CONSTRUCTOR:
        public GamePanel(){
                this.setPreferredSize(new Dimension(screenWidth,screenHeight));
                this.setBackground(Color.WHITE);
                this.setDoubleBuffered(true);
                this.addKeyListener(keyHandler);
                this.setFocusable(true);
        }

   // METHODS:
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

            // PLAYER'S POSITIONS UPDATE:
                player.update();

        }

        public void paintComponent(Graphics graphics){

            super.paintComponent(graphics);

            Graphics2D graphics2D = (Graphics2D) graphics;

            // DRAW TILES:
                tileManager.draw(graphics2D);
            // DRAW PLAYERS:
                player.paintComponent(graphics2D);

            graphics2D.dispose();
        }
}
