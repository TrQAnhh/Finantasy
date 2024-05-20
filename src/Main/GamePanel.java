package Main;

import Entities.Entity;
import Entities.Player;
import Map.TileManager;
import Main.EventHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        public final int screenWidth = tileSize * maxScreenColumn; // 768 pixels
        public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS:
        public final int maxWorldColumn = 64; // can be adjusted
        public final int maxWorldRow = 64; // can be adjusted
        public final int worldWidth = tileSize * maxWorldColumn;
        public final int worldHeight = tileSize * maxWorldRow;

   // INSTANTIATE OBJECTS: 
        public KeyHandler keyHandler = new KeyHandler(this); 
        Thread gameThread; 
    // Sound CLASS 
        Sound music = new Sound(); 
        Sound se = new Sound(); 
    // Player CLASS 
        public Player player = new Player(this,keyHandler); 
    // TileManager CLASS 
        TileManager tileManager = new TileManager(this); 
    // Collision CLASS 
        public Collision collision = new Collision(this); 
    // NPC CLASS 
        public AssetSetter aSetter = new AssetSetter(this); 
        public Entity npc[] = new Entity[10]; 
    // Monster CLASS 
        public Entity monster[] = new Entity[20]; 
    // Object CLASS 
        public Entity object[] = new Entity[10]; 
    // UNIT INTERFACE 
        public UI ui = new UI(this); 
    // List of Entity 
        ArrayList<Entity> entityList = new ArrayList<>(); 
    // EventHandler 
        public EventHandler eHandler = new EventHandler(this);

    // GAME STATE :
        public int gameState; 
        public final int titleState = 0; 
        public final int playState = 1; 
        public final int pauseState = 2; 
        public final int dialogueState = 3; 
        public final int battleState = 4; 
        public final int characterState = 5; 
        public final int optionsState = 6;

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
