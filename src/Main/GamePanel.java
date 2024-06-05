package Main;

import Entities.Entity;
import javax.swing.JPanel;
import Entities.Entity;
import Entities.Player;
import Enviroment.EnviromentManager;
import Map.TileManager;
import Main.EventHandler;

import javax.swing.*;
import java.awt.*;
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
    // Second Map
        public final int maxMap = 10;
        public int currentMap = 0;
        public final int worldWidth = tileSize * maxWorldColumn;
        public final int worldHeight = tileSize * maxWorldRow;
    // FOR FULL SCREEN
        public int screenHeight2 = screenHeight;
        public int screenWidth2 = screenWidth;
        BufferedImage tempScreen;
        Graphics2D graphics2D;
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
        TileManager tileManager = new TileManager(this); 
    // Collision CLASS 
        public Collision collision = new Collision(this); 
    // NPC CLASS 
        public AssetSetter aSetter = new AssetSetter(this); 
        public Entity npc[][] = new Entity[maxMap][20]; 
    // Monster CLASS 
        public Entity monster[][] = new Entity[maxMap][20]; 
    // Object CLASS 
        public Entity object[][] = new Entity[maxMap][10];
    // Effect CLASS
    public Entity effect[] = new Entity[10];
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

    // GAME STATE VARIABLES :
    public int gameState;
    public int tempGameState; // USED WHEN PLAYER HITS BUTTON "BACK"
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int battleState = 4;
    public final int characterState = 5;
    public final int optionsState = 6;
    public final int transitionState = 7;
    public final int gameOverState = 8;
    public final int tradeState = 9;

//    // Config
//        Config config = new Config(this);

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
            // SET MONSTER METHODS:
                aSetter.setMonster();
            // SET EFFECT METHODS:
                aSetter.setEffect();

                playMusic(1);
                currentArea = outside;
                eManager.setup();


        }
        public void retry(){
            player.setDefaultPosition();
            player.restoreLife();
            aSetter.setNPC();
            aSetter.setMonster();
        }
        public void restart(){
            player.setDefaultValues();
            player.setItem();
            
            aSetter.setObject();
            aSetter.setNPC();
            aSetter.setMonster();
            aSetter.setEffect();
            // aSetter.setInteractiveTile();            Wtf is this???
        }
        public void setFullScreen() {

            //GET LOCAL SCREEN DEVICE
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            gd.setFullScreenWindow(Main.window);

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

                if ( currentMap == 0 ) {
                    for (int i = 0; i < npc[1].length; i++)
                        if (npc[0][i] != null) {
                            npc[0][i].update(this);
                        }
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
            if (gameState == pauseState){
                
            }
        }
    public void paintComponent(Graphics graphics){

            super.paintComponent(graphics);

            Graphics2D graphics2D = (Graphics2D) graphics;
            //DEBUG
            long drawStart = 0;

        // DEBUG TEXT:
            if(keyHandler.showDebugText == true) {
                drawStart = System.nanoTime();
            }
            // TITLE SCREEN
            if(gameState == titleState) {
                ui.draw(graphics2D);
            }
            // BATTLE (Can u delete it?)   
            else if(gameState == battleState) {
                ui.draw(graphics2D);
            }
        // BATTLE STATE:
            else if(gameState == battleState) {
                ui.draw(graphics2D);
            }
        // PLAY STATE:
            else {
                // DRAW TILES:
                    tileManager.draw(graphics2D);
                // ADD PLAYERS TO THE LIST
                    entityList.add(player);
                // ADD ENTITIES TO THE LIST
                    if ( currentMap == 0 ) {
                        for(int i = 0; i < npc[1].length; ++i) {
                            if(npc[currentMap][i] != null) {
                                entityList.add(npc[currentMap][i]);
                            }
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
                            entityList.get(i).draw(graphics2D,this);
                        }
                    // EMPTY ENTITY LIST
                        entityList.clear();
                    // DRAW PLAYERS:
                        entityList.clear();
                    // ENVIRONMENT
                        eManager.draw(graphics2D);
                    // UI
                        ui.draw(graphics2D);
            }

            // Debug
                if(keyHandler.showDebugText == true){

                    long drawEnd = System.nanoTime();
                    long passed = drawEnd - drawStart;

                    graphics2D.setFont(new Font("Arial", Font.PLAIN, 20));
                    graphics2D.setColor(Color.white);
                    int x = 10;
                    int y = 400;
                    int lineHeight = 20;

                    graphics2D.drawString("WorldX "+player.worldX, x, y);   y += lineHeight;
                    graphics2D.drawString("WorldY "+player.worldY, x, y);   y += lineHeight;
                    graphics2D.drawString("Col "+(player.worldX + player.solidArea.x)/tileSize, x, y);   y += lineHeight;
                    graphics2D.drawString("Row "+(player.worldY + player.solidArea.y)/tileSize, x, y);   y += lineHeight;
                    graphics2D.drawString("Draw Time: "+passed, x, y);
                }

                graphics2D.dispose();
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
