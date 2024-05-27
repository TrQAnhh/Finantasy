package Map;

import Main.GamePanel;
import Main.UtilityTool;

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
            tile = new Tile[201]; // 150 kinds of tile: a water tile, a grass tile, a wall tile...
            mapTileNum = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];

            // GET TILES IMAGES:
                getTileImage();
            // LOAD MAP METHODS:
                loadMap("res/MapData/mapdata.txt");
        }

    // METHODS:
        public void getTileImage() {
            // WATER TILES:
                    setup(0,"Water/water_1",true);
                    setup(1,"Water/water_2",true);
                    setup(2,"Water/water_3",true);
                    setup(3,"Water/water_4",true);
                    setup(4,"Water/water_big_rock",true);
                    setup(5,"Water/water_small_rock",true);

                // LAKE TILES:
                    setup(6,"Water/lake_1",true);
                    setup(7,"Water/lake_2",true);
                    setup(8,"Water/lake_3",true);
                    setup(9,"Water/lake_4",true);
                    setup(10,"Water/lake_5",true);
                    setup(11,"Water/water_lower_right_corner",true);
                    setup(12,"Water/water_lower_left_corner",true);
                    setup(13,"Water/lake_6",true);
                    setup(14,"Water/lake_7",true);
                    setup(15,"Water/water_upper_right_corner",true);
                    setup(16,"Water/water_upper_left_corner",true);
                    setup(17,"Water/lake_8",true);
                    setup(18,"Water/lake_9",true);
                    setup(19,"Water/lake_10",true);
                    setup(20,"Water/lake_11",true);
                    setup(21,"Water/lake_12",true);

            // TERRAIN TILES:
                    setup(22,"Terrain/grass",false);
                    setup(23,"Terrain/flower_1",false);
                    setup(24,"Terrain/flower_2",false);
                    setup(25,"Terrain/flower_3",false);
                    setup(26,"Terrain/flower_4",false);
                    setup(27,"Terrain/normal_rock",true);
                    setup(28,"Terrain/small_rock",true);

                // BIG-ROCK TILES:
                    setup(29,"Terrain/big_rock_1",true);
                    setup(30,"Terrain/big_rock_2",true);
                    setup(31,"Terrain/big_rock_3",true);
                    setup(32,"Terrain/big_rock_4",true);

                // BOXWOOD TILES:
                    setup(33,"Terrain/boxwood_1",true);
                    setup(34,"Terrain/boxwood_2",true);
                    setup(35,"Terrain/boxwood_3",true);
                    setup(36,"Terrain/boxwood_4",true);

                // STUMP TILES:
                    setup(37,"Terrain/stump_1",true);
                    setup(38,"Terrain/stump_2",true);
                    setup(39,"Terrain/stump_3",true);
                    setup(40,"Terrain/stump_4",true);

                // TRUNK TILES:
                    setup(41,"Terrain/trunk_1_1",true);
                    setup(42,"Terrain/trunk_1_2",true);
                    setup(43,"Terrain/trunk_1_3",true);
                    setup(44,"Terrain/trunk_1_4",true);
                    setup(45,"Terrain/trunk_1_5",true);
                    setup(46,"Terrain/trunk_1_6",true);

                    setup(47,"Terrain/trunk_2_1",true);
                    setup(48,"Terrain/trunk_2_2",true);
                    setup(49,"Terrain/trunk_2_3",true);
                    setup(50,"Terrain/trunk_2_4",true);
                    setup(51,"Terrain/trunk_2_5",true);
                    setup(52,"Terrain/trunk_2_6",true);

            // TRAIL TILES:
                    setup(53,"Trail/trail_1",false);
                    setup(54,"Trail/trail_2",false);
                    setup(55,"Trail/trail_3",false);
                    setup(56,"Trail/trail_4",false);
                    setup(57,"Trail/trail_5",false);
                    setup(58,"Trail/trail_6",false);
                    setup(59,"Trail/trail_7",false);
                    setup(60,"Trail/trail_8",false);
                    setup(61,"Trail/trail_9",false);
                    setup(62,"Trail/trail_10",false);
                    setup(63,"Trail/trail_11",false);
                    setup(64,"Trail/trail_12",false);
                    setup(65,"Trail/trail_13",false);
                    setup(66,"Trail/trail_14",false);
                    setup(67,"Trail/trail_15",false);
                    setup(68,"Trail/trail_16",false);

                    setup(69,"Trail/trail_lower_right_corner",false);
                    setup(70,"Trail/trail_lower_left_corner",false);
                    setup(71,"Trail/trail_upper_right_corner",false);
                    setup(72,"Trail/trail_upper_left_corner",false);

             // TREE TILES:
                    setup(73,"Tree/tree_1",true);
                    setup(74,"Tree/tree_2",true);
                    setup(75,"Tree/tree_3",true);
                    setup(76,"Tree/tree_4",true);
                    setup(77,"Tree/tree_5",true);
                    setup(78,"Tree/tree_6",true);
                    setup(79,"Tree/tree_7",true);
                    setup(80,"Tree/tree_8",true);
                    setup(81,"Tree/tree_9",true);
                    setup(82,"Tree/tree_10",true);
                    setup(83,"Tree/tree_11",true);
                    setup(84,"Tree/tree_12",true);
                    setup(85,"Tree/tree_13",true);
                    setup(86,"Tree/tree_14",true);
                    setup(87,"Tree/tree_15",true);
                    setup(88,"Tree/tree_16",true);
                    setup(89,"Tree/tree_17",true);
                    setup(90,"Tree/tree_18",true);
                    setup(91,"Tree/tree_19",true);
                    setup(92,"Tree/tree_20",true);
                    setup(93,"Tree/tree_21",true);
                    setup(94,"Tree/tree_22",true);

              // MOUNTAIN TILES:
                    setup(95,"Mountain/mountain_1",true);
                    setup(96,"Mountain/mountain_2",true);
                    setup(97,"Mountain/mountain_3",true);
                    setup(98,"Mountain/mountain_4",true);
                    setup(99,"Mountain/mountain_5",true);
                    setup(100,"Mountain/mountain_6",true);
                    setup(101,"Mountain/mountain_7",true);
                    setup(102,"Mountain/mountain_8",true);
                    setup(103,"Mountain/mountain_9",true);
                    setup(104,"Mountain/mountain_10",true);
                    setup(105,"Mountain/mountain_11",true);
                    setup(106,"Mountain/mountain_12",true);
                    setup(107,"Mountain/mountain_13",true);
                    setup(108,"Mountain/mountain_14",true);
                    setup(109,"Mountain/mountain_15",true);
                    setup(110,"Mountain/mountain_16",true);
                    setup(111,"Mountain/mountain_17",true);
                    setup(112,"Mountain/mountain_18",true);
                    setup(113,"Mountain/mountain_19",true);
                    setup(114,"Mountain/mountain_20",true);
                    setup(115,"Mountain/mountain_21",true);
                    setup(116,"Mountain/mountain_22",true);
                    setup(117,"Mountain/mountain_23",true);
                    setup(118,"Mountain/mountain_24",true);
                    setup(119,"Mountain/mountain_25",true);
                    setup(120,"Mountain/mountain_26",true);
                    setup(121,"Mountain/mountain_27",true);
                    setup(122,"Mountain/mountain_28",true);
                    setup(123,"Mountain/mountain_29",true);
                    setup(124,"Mountain/mountain_30",true);
                    setup(125,"Mountain/mountain_31",true);
                    setup(126,"Mountain/mountain_32",true);

                // CAVES TILES:
                    setup(127,"Mountain/cave_1",true);
                    setup(128,"Mountain/cave_2",true);
                    setup(129,"Mountain/cave_3",true);
                    setup(130,"Mountain/cave_4",true);
                    setup(131,"Mountain/cave_5",false);
                    setup(132,"Mountain/cave_6",false);

            // BOAT TILES:
                    setup(133,"Boat/boat_1_1",true);
                    setup(136,"Boat/boat_1_2",true);
                    setup(134,"Boat/boat_2_1",true);
                    setup(137,"Boat/boat_2_2",true);
                    setup(135,"Boat/boat_3_1",true);
                    setup(138,"Boat/boat_3_2",true);

            // CONSTRUCTION TILES:
                    setup(139,"Construction/construction_1",true);
                    setup(140,"Construction/construction_2",true);
                    setup(141,"Construction/construction_3",true);
                    setup(142,"Construction/construction_4",true);

                 // TOWERS TILES:
                    setup(143,"Construction/Tower_1",true);
                    setup(144,"Construction/Tower_2",true);
                    setup(145,"Construction/Tower_3",true);
                    setup(146,"Construction/Tower_4",true);
                    setup(147,"Construction/Tower_5",true);
                    setup(148,"Construction/Tower_6",true);
                    setup(149,"Construction/Tower_7",true);
                    setup(150,"Construction/Tower_8",true);
                    setup(151,"Construction/Tower_9",true);
                    setup(152,"Construction/Tower_10",true);
                    setup(153,"Construction/Tower_11",true);
                    setup(154,"Construction/Tower_12",true);

            // SMITHY TILES:
                    setup(155,"Smithy/smithy_1",true);
                    setup(156,"Smithy/smithy_2",true);
                    setup(157,"Smithy/smithy_3",true);
                    setup(158,"Smithy/smithy_4",true);
                    setup(159,"Smithy/smithy_5",true);
                    setup(160,"Smithy/smithy_6",true);
                    setup(161,"Smithy/smithy_7",true);
                    setup(162,"Smithy/smithy_8",true);
                    setup(163,"Smithy/smithy_9",true);
                    setup(164,"Smithy/smithy_10",true);
                    setup(165,"Smithy/smithy_11",true);
                    setup(166,"Smithy/smithy_12",true);
                    setup(167,"Smithy/smithy_13",true);
                    setup(168,"Smithy/smithy_14",true);
                    setup(169,"Smithy/smithy_15",true);
                    setup(170,"Smithy/smithy_16",true);

             // HOUSE TILES:
                    setup(171,"House/house_1",true);
                    setup(172,"House/house_2",true);
                    setup(173,"House/house_3",true);
                    setup(174,"House/house_4",true);
                    setup(175,"House/house_5",true);
                    setup(176,"House/house_6",true);
                    setup(177,"House/house_7",true);
                    setup(178,"House/house_8",true);
                    setup(179,"House/house_9",true);
                    setup(180,"House/house_10",true);
                    setup(181,"House/house_11",true);
                    setup(182,"House/house_12",true);

             // CASTLE TILES:
                    setup(183,"Castle/castle_1",true);
                    setup(184,"Castle/castle_2",true);
                    setup(185,"Castle/castle_3",true);
                    setup(186,"Castle/castle_4",true);
                    setup(187,"Castle/castle_5",true);
                    setup(188,"Castle/castle_6",true);
                    setup(189,"Castle/castle_7",true);
                    setup(190,"Castle/castle_8",true);
                    setup(191,"Castle/castle_9",true);
                    setup(192,"Castle/castle_10",true);
                    setup(193,"Castle/castle_11",true);
                    setup(194,"Castle/castle_12",true);
                    setup(195,"Castle/castle_13",true);
                    setup(196,"Castle/castle_14",true);
                    setup(197,"Castle/castle_15",true);
        }

        public void setup(int index, String imagePath, boolean collision) {

                UtilityTool uTool = new UtilityTool();

                String filePath = "res/Tile/" + imagePath + ".png";
                File imageFile = new File(filePath);

                try (FileInputStream image = new FileInputStream(imageFile)) {
                        tile[index] = new Tile();
                        tile[index].tile_image = ImageIO.read(image);
                        tile[index].tile_image = uTool.scaleImage(tile[index].tile_image, gamePanel.tileSize, gamePanel.tileSize);
                        tile[index].collision = collision;
                } catch(IOException e) {
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
                String delims = ",";
                String[] numbers = line.split(delims);
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
    public void draw(Graphics2D g2){
        // VARIABLES:
            int worldColumn = 0;
            int worldRow = 0;

            while ( worldColumn < gamePanel.maxWorldColumn && worldRow < gamePanel.maxWorldRow ) {

                int tileNum = mapTileNum[worldColumn][worldRow];

                int worldX = worldColumn * gamePanel.tileSize;
                int worldY = worldRow * gamePanel.tileSize;
                int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

                g2.drawImage(tile[tileNum].tile_image,screenX,screenY,null);

                worldColumn++;

                if (worldColumn == gamePanel.maxWorldColumn) {
                    worldColumn = 0;
                    worldRow++;
                }
            }

    }
}

