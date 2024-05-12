package Map;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNum[][]; // A 2D array that carry the index number of tiles

    // CONSTRUCTORS:
        public TileManager (GamePanel gamePanel){

            this.gamePanel = gamePanel;
            tile = new Tile[100]; // 100 kinds of tile: a water tile, a grass tile, a wall tile...
            mapTileNum = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];

            // GET TILES IMAGES:
                getTileImage();
            // LOAD MAP METHODS:
                loadMap("res/MapData/mapdata_2.txt");
        }

    // METHODS:
        public void getTileImage() {
            try (
                    // WATER TILE:
                        InputStream water_1 = new FileInputStream("res/Tile/Water/water_1.png");
                        InputStream water_2 = new FileInputStream("res/Tile/Water/water_2.png");
                        InputStream water_3 = new FileInputStream("res/Tile/Water/water_3.png");
                        InputStream water_4 = new FileInputStream("res/Tile/Water/water_4.png");
                        InputStream water_big_rock = new FileInputStream("res/Tile/Water/water_big_rock.png");
                        InputStream water_small_rock = new FileInputStream("res/Tile/Water/water_small_rock.png");
                    // GREEN GROUND TILES:
                        InputStream grass = new FileInputStream("res/Tile/Grass/grass.png");
                    // BOAT TILES:
                        InputStream boat_1_1 = new FileInputStream("res/Tile/Boat/boat_1_1.png");
                        InputStream boat_1_2 = new FileInputStream("res/Tile/Boat/boat_1_2.png");
                        InputStream boat_2_1 = new FileInputStream("res/Tile/Boat/boat_2_1.png");
                        InputStream boat_2_2 = new FileInputStream("res/Tile/Boat/boat_2_2.png");
                        InputStream boat_3_1 = new FileInputStream("res/Tile/Boat/boat_3_1.png");
                        InputStream boat_3_2 = new FileInputStream("res/Tile/Boat/boat_3_2.png");
                    // HOUSE TILES:
                        InputStream castle_1 = new FileInputStream("res/Tile/Castle/castle_1.png");
                        InputStream castle_2 = new FileInputStream("res/Tile/Castle/castle_2.png");
                        InputStream castle_3 = new FileInputStream("res/Tile/Castle/castle_3.png");
                        InputStream castle_4 = new FileInputStream("res/Tile/Castle/castle_4.png");
                        InputStream castle_5 = new FileInputStream("res/Tile/Castle/castle_5.png");
                        InputStream castle_6 = new FileInputStream("res/Tile/Castle/castle_6.png");
                        InputStream castle_7 = new FileInputStream("res/Tile/Castle/castle_7.png");
                        InputStream castle_8 = new FileInputStream("res/Tile/Castle/castle_8.png");
                        InputStream castle_9 = new FileInputStream("res/Tile/Castle/castle_9.png");
                        InputStream castle_10 = new FileInputStream("res/Tile/Castle/castle_10.png");
                        InputStream castle_11 = new FileInputStream("res/Tile/Castle/castle_11.png");
                        InputStream castle_12 = new FileInputStream("res/Tile/Castle/castle_12.png");
                        InputStream castle_13 = new FileInputStream("res/Tile/Castle/castle_13.png");
                        InputStream castle_14 = new FileInputStream("res/Tile/Castle/castle_14.png");
                        InputStream castle_15 = new FileInputStream("res/Tile/Castle/castle_15.png");
                    // TOWERS:
                        InputStream tower_1 = new FileInputStream("res/Tile/Tower/tower_1.png");
                        InputStream tower_2 = new FileInputStream("res/Tile/Tower/tower_2.png");
                        InputStream tower_3 = new FileInputStream("res/Tile/Tower/tower_3.png");
                        InputStream tower_4 = new FileInputStream("res/Tile/Tower/tower_4.png");
                        InputStream tower_5 = new FileInputStream("res/Tile/Tower/tower_5.png");
                        InputStream tower_6 = new FileInputStream("res/Tile/Tower/tower_6.png");
                        InputStream tower_7 = new FileInputStream("res/Tile/Tower/tower_7.png");
                        InputStream tower_8 = new FileInputStream("res/Tile/Tower/tower_8.png");
                        InputStream tower_9 = new FileInputStream("res/Tile/Tower/tower_9.png");
                        InputStream tower_10 = new FileInputStream("res/Tile/Tower/tower_10.png");
                        InputStream tower_11 = new FileInputStream("res/Tile/Tower/tower_11.png");
                        InputStream tower_12 = new FileInputStream("res/Tile/Tower/tower_12.png");





            ) {
                // WATER TILE:
                    tile[0] = new Tile();
                    tile[0].tile_image = ImageIO.read(water_1);
                    tile[0].collision = true;

                    tile[1] = new Tile();
                    tile[1].tile_image = ImageIO.read(water_2);
                    tile[1].collision = true;

                    tile[2] = new Tile();
                    tile[2].tile_image = ImageIO.read(water_3);
                    tile[2].collision = true;


                    tile[3] = new Tile();
                    tile[3].tile_image = ImageIO.read(water_4);
                    tile[3].collision = true;


                    tile[4] = new Tile();
                    tile[4].tile_image = ImageIO.read(water_big_rock);
                    tile[4].collision = true;


                    tile[5] = new Tile();
                    tile[5].tile_image = ImageIO.read(water_small_rock);
                    tile[5].collision = true;

                // GREEN GROUND TILES:
                    tile[6] = new Tile();
                    tile[6].tile_image = ImageIO.read(grass);
                    tile[6].collision = false;

                // BOAT TILES:
                    tile[14] = new Tile();
                    tile[14].tile_image = ImageIO.read(boat_1_1);
                    tile[14].collision = true;

                    tile[15] = new Tile();
                    tile[15].tile_image = ImageIO.read(boat_1_2);
                    tile[15].collision = true;

                    tile[16] = new Tile();
                    tile[16].tile_image = ImageIO.read(boat_2_1);
                    tile[16].collision = true;

                    tile[17] = new Tile();
                    tile[17].tile_image = ImageIO.read(boat_2_2);
                    tile[17].collision = true;

                    tile[18] = new Tile();
                    tile[18].tile_image = ImageIO.read(boat_3_1);
                    tile[18].collision = true;

                    tile[19] = new Tile();
                    tile[19].tile_image = ImageIO.read(boat_3_2);
                    tile[19].collision = true;

                // CASTLE TILES:
                    tile[20] = new Tile();
                    tile[20].tile_image = ImageIO.read(castle_1);

                    tile[21] = new Tile();
                    tile[21].tile_image = ImageIO.read(castle_2);

                    tile[22] = new Tile();
                    tile[22].tile_image = ImageIO.read(castle_3);

                    tile[23] = new Tile();
                    tile[23].tile_image = ImageIO.read(castle_4);

                    tile[24] = new Tile();
                    tile[24].tile_image = ImageIO.read(castle_5);

                    tile[25] = new Tile();
                    tile[25].tile_image = ImageIO.read(castle_6);

                    tile[26] = new Tile();
                    tile[26].tile_image = ImageIO.read(castle_7);

                    tile[27] = new Tile();
                    tile[27].tile_image = ImageIO.read(castle_8);

                    tile[28] = new Tile();
                    tile[28].tile_image = ImageIO.read(castle_9);

                    tile[29] = new Tile();
                    tile[29].tile_image = ImageIO.read(castle_10);

                    tile[30] = new Tile();
                    tile[30].tile_image = ImageIO.read(castle_11);

                    tile[31] = new Tile();
                    tile[31].tile_image = ImageIO.read(castle_12);

                    tile[32] = new Tile();
                    tile[32].tile_image = ImageIO.read(castle_13);

                    tile[33] = new Tile();
                    tile[33].tile_image = ImageIO.read(castle_14);

                    tile[34] = new Tile();
                    tile[34].tile_image = ImageIO.read(castle_15);

                // TOWER:
                    tile[35] = new Tile();
                    tile[35].tile_image = ImageIO.read(tower_1);

                    tile[36] = new Tile();
                    tile[36].tile_image = ImageIO.read(tower_2);

                    tile[37] = new Tile();
                    tile[37].tile_image = ImageIO.read(tower_3);

                    tile[38] = new Tile();
                    tile[38].tile_image = ImageIO.read(tower_4);

                    tile[39] = new Tile();
                    tile[39].tile_image = ImageIO.read(tower_5);

                    tile[40] = new Tile();
                    tile[40].tile_image = ImageIO.read(tower_6);

                    tile[41] = new Tile();
                    tile[41].tile_image = ImageIO.read(tower_7);

                    tile[42] = new Tile();
                    tile[42].tile_image = ImageIO.read(tower_8);

                    tile[43] = new Tile();
                    tile[43].tile_image = ImageIO.read(tower_9);

                    tile[44] = new Tile();
                    tile[44].tile_image = ImageIO.read(tower_10);

                    tile[45] = new Tile();
                    tile[45].tile_image = ImageIO.read(tower_11);

                    tile[46] = new Tile();
                    tile[46].tile_image = ImageIO.read(tower_12);

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

