package Main;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import Entities.Entity;
import Entities.Player;
import Map.TileManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

        // Game State
            public int gameState;
            public final int titleState = 0;
            public final int playState = 1;
            public final int pauseState = 2;
            public final int dialogueState = 3;
            public final int battleState = 4;
            public final int characterState = 5;
        
   // CONSTRUCTOR:
        public GamePanel(){
                this.setPreferredSize(new DimensionUIResource(screenWidth,screenHeight));
                this.setBackground(Color.WHITE);
                this.setDoubleBuffered(true);
                this.addKeyListener(keyHandler);
                this.setFocusable(true);
        }
        // Add Object, Entity
        public void setupGame(){

            aSetter.setObject();
            aSetter.setNPC();
            aSetter.setMonster();
          //  playMusic(0);
          //  stopMusic();
          gameState = titleState;
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
            if (gameState == playState){
                player.update();
                for(int i=0;i<npc.length;i++)
                if(npc[i] != null){
                    npc[i].update();    
                }
                
            for(int i=0; i<monster.length; i++){
                if(monster[i] != null){
                    if(monster[i].alive == true && monster[i].dying == false){
                    monster[i].update();
                    }
                    if(monster[i].alive == false){
                        monster[i] = null;
                        }
                }
            }
            }
            if (gameState == pauseState){
                
            }
        }

        public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;

    // debug
    long drawStart = 0;
    if(keyHandler.showDebugText == true){
        drawStart = System.nanoTime();
    }

    // Title Screen
    if(gameState == titleState){
        ui.draw(g2);
    }
    // Battle
    else if(gameState == battleState){
        ui.draw(g2);
    }
    // Others
    else{
        // tile
    tileManager.draw(g2);

    // Add entity to the list
    entityList.add(player);
    for(int i=0; i<npc.length;i++){
        if(npc[i] != null){
            entityList.add(npc[i]);
        }
    }

    for(int i=0; i<object.length; i++){
        if(object[i] != null){
            entityList.add(object[i]);
        }
    }
    
    for(int i=0; i<monster.length; i++){
        if(monster[i] != null){
            entityList.add(monster[i]);
        }
    }
    // Sort
    Collections.sort(entityList, new Comparator<Entity>() {

        @Override
        public int compare(Entity e1, Entity e2) {
            int result = Integer.compare(e1.worldY, e2.worldY);
            return result;
        }
        
    });

    // Draw entities
    for(int i=0; i<entityList.size(); i++){
        entityList.get(i).draw(g2);
    }
    // Empty entity list
    entityList.clear();
    // ui
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
}
