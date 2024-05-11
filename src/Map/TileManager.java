package Map;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNum[][]; // A 2D array that carry the index number of tiles

    // CONSTRUCTORS:
        public TileManager (GamePanel gamePanel){

            this.gamePanel = gamePanel;
            tile = new Tile[100]; // 100 kinds of tile: a water tile, a grass tile, a wall tile...
            mapTileNum = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];

            // GET TILES IMAGES:
                getTileImage();
            // LOAD MAP METHODS:
                loadMap("res/MapData/mapdata.txt");
        }

    // METHODS:
        public void getTileImage() {
            try (
                    // WATER TILE:
                        InputStream water = new FileInputStream("res/Tile/Water/water.png");
                    // GREEN GROUND TILES:
                        InputStream grass = new FileInputStream("res/Tile/Grass/grass.png");
                    // YELLOW GROUND TILES:


            ) {
                // WATER TILE:
                    tile[0] = new Tile();
                    tile[0].tile_image = ImageIO.read(water);
                // GREEN GROUND TILES:
                    tile[1] = new Tile();
                    tile[1].tile_image = ImageIO.read(grass);

            } catch (IOException e){
                e.printStackTrace();
            }
        }
      public void loadMap(String filePath) {
        try {
            InputStream mapData = new FileInputStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mapData));

            int row = 0;
            String line;

            while ((line = bufferedReader.readLine()) != null && row < gamePanel.maxWorldRow) {

                String[] numbers = line.split(" ");
                int column = 0;

                for (String number : numbers) { // Getting data from column
                    int num = Integer.parseInt(number); // Change from String to Integer
                    mapTileNum[column][row] = num;
                    column++;
                    if (column >= gamePanel.maxWorldColumn) {
                        break;
                    }
                }
                row++; // Increase row after finishing getting data from 1 row
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D graphics2D){
        // VARIABLES:
            int worldColumn = 0;
            int worldRow = 0;

            while ( worldColumn < gamePanel.maxWorldColumn && worldRow < gamePanel.maxWorldRow ) {

                int tileNum = mapTileNum[worldColumn][worldRow];

                int worldX = worldColumn * gamePanel.tileSize;
                int worldY = worldRow * gamePanel.tileSize;
                int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

                graphics2D.drawImage(tile[tileNum].tile_image,screenX,screenY,gamePanel.tileSize, gamePanel.tileSize,null);

                worldColumn++;

                if (worldColumn == gamePanel.maxWorldColumn) {
                    worldColumn = 0;
                    worldRow++;
                }
            }

    }
}

