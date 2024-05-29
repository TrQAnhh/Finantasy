package Main;

import Entities.Entity;
import javax.swing.JPanel;


import Entities.Entity;
import Entities.Player;
import Enviroment.EnviromentManager;
import Map.TileManager;
import Objects.SuperObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


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
        public final int maxMap = 10;
        public int currentMap = 0;
        public final int worldWidth = tileSize * maxWorldColumn;
        public final int worldHeight = tileSize * maxWorldRow;
    // FOR FULL SCREEN
        public int screenHeight2 = screenHeight;
        public int screenWidth2 = screenWidth;
        BufferedImage tempScreen;
        Graphics2D g2;
        public boolean fullScreenOn = true;
// INSTANTIATE OBJECTS:
        public KeyHandler keyHandler = new KeyHandler(this);
        Thread gameThread;
    // Sound CLASS
        Sound music = new Sound();
        Sound se = new Sound();
    // Player CLASS
        public Player player = new Player(this,keyHandler);
    // TileManager CLASS
        public TileManager tileManager = new TileManager(this);
    // Collision CLASS
        public Collision collision = new Collision(this);
    // NPC CLASS
        public AssetSetter aSetter = new AssetSetter(this);
        public Entity npc[][] = new Entity[maxMap][10];
    // Monster CLASS
        public Entity monster[][] = new Entity[maxMap][20];
    // Object CLASS
        public Entity object[][] = new Entity[maxMap][10];
    // UNIT INTERFACE
        public UI ui = new UI(this);
    // List of Entity
        ArrayList<Entity> entityList = new ArrayList<>();
    // EventHandler
        public EventHandler eHandler = new EventHandler(this);
    // Config
        Config config = new Config(this);
    // EnviromentManager
        EnviromentManager eManager = new EnviromentManager(this);
    // GAME STATE :
        // Option choice
        public int gameState;
        public final int titleState = 0;
        public final int playState = 1;
        public final int pauseState = 2;
        public final int dialogueState = 3;
        public final int battleState = 4;
        public final int characterState = 5;
        public final int optionsState = 6;
        public final int transitionState = 7;

    // AREA
        public int currentArea;
        public int nextArea;
        public final int outside = 50;
        public final int dungeon = 52;
   // CONSTRUCTOR:
        public GamePanel() {
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
                currentArea = outside;
                eManager.setup();


        }
        public void setFullScreen() {

            //GET LOCAL SCREEN DEVICE
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            gd.setFullScreenWindow(Main.window);

            //GET FULL SCREEN WIDTH AND HEIGHT
            screenWidth2 = Main.window.getWidth();
            screenHeight2 = Main.window.getHeight();

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

        public void update() {
            if (gameState == playState) {
                player.update();
                for (int i = 0; i < npc[1].length; i++)
                    if (npc[currentMap][i] != null) {
                        npc[currentMap][i].update(this);
                    }

                for (int i = 0; i < monster[1].length; i++) {
                    if (monster[currentMap][i] != null) {
                        if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
                            monster[currentMap][i].update();
                        }
                        if (monster[currentMap][i].alive == false) {
                            monster[currentMap][i] = null;
                        }
                    }
                }
                eManager.update();
                if (gameState == pauseState) {
                    // UPDATE LATER
                }
            }
        }
    public void paintComponent(Graphics graphics){

            super.paintComponent(graphics);

            Graphics2D g2 = (Graphics2D) graphics;

            long drawStart = 0;

        // DEBUG TEXT:
            if(keyHandler.showDebugText == true) {
                drawStart = System.nanoTime();
            }
        // TITLE STATE:
            if(gameState == titleState) {
                ui.draw(g2);
            }
        // BATTLE STATE:
            else if(gameState == battleState) {
                ui.draw(g2);
            }
        // PLAY STATE:
            else {
                // DRAW TILES:
                    tileManager.draw(g2);
//                // ADD PLAYERS TO THE LIST
//                    entityList.add(player);
                // ADD ENTITIES TO THE LIST
                    for(int i = 0; i < npc[1].length; ++i) {
                        if(npc[currentMap][i] != null) {
                            entityList.add(npc[currentMap][i]);
                        }
                    }

                    for(int i = 0; i < object[1].length; ++i) {
                        if(object[currentMap][i] != null) {
                            entityList.add(object[currentMap][i]);
                        }
                    }
                    for(int i = 0; i < monster[1].length; ++i) {
                        if(monster[currentMap][i] != null) {
                            entityList.add(monster[currentMap][i]);
                        }
                    }

                // SORT
                    Collections.sort(entityList, new Comparator<Entity>() {

                        @Override
                        public int compare(Entity e1, Entity e2) {
                            int result = Integer.compare(e1.worldY, e2.worldY);
                            return result;
                        }
                    });
                    // DRAW ENTITIES:
                        for(int i=0; i < entityList.size(); i++){
                            entityList.get(i).draw(g2,this);
                        }
                    // EMPTY ENTITY LIST
                        entityList.clear();
                    // DRAW PLAYERS:
                        player.draw(g2);
                        entityList.clear();
                    // ENVIRONMENT
                        eManager.draw(g2);
                    // UI
                        ui.draw(g2);
            }

            // Debug
                if(keyHandler.showDebugText == true){

                    long drawEnd = System.nanoTime();
                    long passed = drawEnd - drawStart;

                    g2.setFont(new Font("Arial", Font.PLAIN, 20));
                    g2.setColor(Color.white);
                    int x = 10;
                    int y = 400;
                    int lineHeight = 20;

                    g2.drawString("WorldX "+player.worldX, x, y);   y += lineHeight;
                    g2.drawString("WorldY "+player.worldY, x, y);   y += lineHeight;
                    g2.drawString("Col "+(player.worldX + player.solidArea.x)/tileSize, x, y);   y += lineHeight;
                    g2.drawString("Row "+(player.worldY + player.solidArea.y)/tileSize, x, y);   y += lineHeight;
                    g2.drawString("Draw Time: "+passed, x, y);
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
        public void playSE(int i){
        se.setFile(i);
        se.play();
    }
        public void changeArea(){
            if(nextArea != currentArea) {
                /*
                stopMusic();
                if(nextArea == outside) {
                    playSE(0);
                }
                if(nextArea == dungeon) {
                    playSE(1);
                }
                */
            }
            currentArea = nextArea;
            aSetter.setMonster();
            aSetter.setNPC();
        }
}
