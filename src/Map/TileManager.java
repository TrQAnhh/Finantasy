package Map;

import java.awt.Graphics2D;
import java.io.*;
import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gamePanel;
    public Tile[][] tile;
    public int mapTileNum[][][]; // A 2D array that carry the index number of tiles


    // CONSTRUCTORS:
        public TileManager (GamePanel gamePanel){

            this.gamePanel = gamePanel;
            tile = new Tile[gamePanel.maxMap][350]; // 350 kinds of tile: a water tile, a grass tile, a wall tile...
            mapTileNum = new int[gamePanel.maxMap][gamePanel.maxWorldColumn][gamePanel.maxWorldRow];

            // GET TILES IMAGES:
                getTileImageFirstMap();
                getTileImageSecondMap();
            // LOAD MAP METHODS:
                loadMap("res/MapData/mapdata.txt", 0);
                loadMap("res/MapData/mapdataDung.txt", 1);
        }

    // METHODS:
        public void getTileImageFirstMap() {
            // WATER TILES:
                setup(0,0,"Water/water_1",true);
                setup(0,1,"Water/water_2",true);
                setup(0,2,"Water/water_3",true);
                setup(0,3,"Water/water_4",true);
                setup(0,4,"Water/water_big_rock",true);
                setup(0,5,"Water/water_small_rock",true);

            // LAKE TILES:
                setup(0,6,"Water/lake_1",true);
                setup(0,7,"Water/lake_2",true);
                setup(0,8,"Water/lake_3",true);
                setup(0,9,"Water/lake_4",true);
                setup(0,10,"Water/lake_5",true);
                setup(0,11,"Water/water_lower_right_corner",true);
                setup(0,12,"Water/water_lower_left_corner",true);
                setup(0,13,"Water/lake_6",true);
                setup(0,14,"Water/lake_7",true);
                setup(0,15,"Water/water_upper_right_corner",true);
                setup(0,16,"Water/water_upper_left_corner",true);
                setup(0,17,"Water/lake_8",true);
                setup(0,18,"Water/lake_9",true);
                setup(0,19,"Water/lake_10",true);
                setup(0,20,"Water/lake_11",true);
                setup(0,21,"Water/lake_12",true);

            // TERRAIN TILES:
                setup(0,22,"Terrain/grass",false);
                setup(0,23,"Terrain/flower_1",true);
                setup(0,24,"Terrain/flower_2",false);
                setup(0,25,"Terrain/flower_3",false);
                setup(0,26,"Terrain/flower_4",false);
                setup(0,27,"Terrain/normal_rock",true);
                setup(0,28,"Terrain/small_rock",true);
            // BIG-ROCK TILES:
                setup(0,29,"Terrain/big_rock_1",true);
                setup(0,30,"Terrain/big_rock_2",true);
                setup(0,31,"Terrain/big_rock_3",true);
                setup(0,32,"Terrain/big_rock_4",true);

            // BOXWOOD TILES:
                setup(0,33,"Terrain/boxwood_1",true);
                setup(0,34,"Terrain/boxwood_2",true);
                setup(0,35,"Terrain/boxwood_3",true);
                setup(0,36,"Terrain/boxwood_4",true);

            // STUMP TILES:
                setup(0,37,"Terrain/stump_1",true);
                setup(0,38,"Terrain/stump_2",true);
                setup(0,39,"Terrain/stump_3",true);
                setup(0,40,"Terrain/stump_4",true);

            // TRUNK TILES:
                setup(0,41,"Terrain/trunk_1_1",true);
                setup(0,42,"Terrain/trunk_1_2",true);
                setup(0,43,"Terrain/trunk_1_3",true);
                setup(0,44,"Terrain/trunk_1_4",true);
                setup(0,45,"Terrain/trunk_1_5",true);
                setup(0,46,"Terrain/trunk_1_6",true);

                setup(0,47,"Terrain/trunk_2_1",true);
                setup(0,48,"Terrain/trunk_2_2",true);
                setup(0,49,"Terrain/trunk_2_3",true);
                setup(0,50,"Terrain/trunk_2_4",true);
                setup(0,51,"Terrain/trunk_2_5",true);
                setup(0,52,"Terrain/trunk_2_6",true);
            // TRAIL TILES:
                setup(0,53,"Trail/trail_1",false);
                setup(0,54,"Trail/trail_2",false);
                setup(0,55,"Trail/trail_3",false);
                setup(0,56,"Trail/trail_4",false);
                setup(0,57,"Trail/trail_5",false);
                setup(0,58,"Trail/trail_6",false);
                setup(0,59,"Trail/trail_7",false);
                setup(0,60,"Trail/trail_8",false);
                setup(0,61,"Trail/trail_9",false);
                setup(0,62,"Trail/trail_10",false);
                setup(0,63,"Trail/trail_11",false);
                setup(0,64,"Trail/trail_12",false);
                setup(0,65,"Trail/trail_13",false);
                setup(0,66,"Trail/trail_14",false);
                setup(0,67,"Trail/trail_15",false);
                setup(0,68,"Trail/trail_16",false);

                setup(0,69,"Trail/trail_lower_right_corner",false);
                setup(0,70,"Trail/trail_lower_left_corner",false);
                setup(0,71,"Trail/trail_upper_right_corner",false);
                setup(0,72,"Trail/trail_upper_left_corner",false);
            // TREE TILES:
                setup(0,73,"Tree/tree_1",true);
                setup(0,74,"Tree/tree_2",true);
                setup(0,75,"Tree/tree_3",true);
                setup(0,76,"Tree/tree_4",true);
                setup(0,77,"Tree/tree_5",true);
                setup(0,78,"Tree/tree_6",true);
                setup(0,79,"Tree/tree_7",true);
                setup(0,80,"Tree/tree_8",true);
                setup(0,81,"Tree/tree_9",true);
                setup(0,82,"Tree/tree_10",true);
                setup(0,83,"Tree/tree_11",true);
                setup(0,84,"Tree/tree_12",true);
                setup(0,85,"Tree/tree_13",true);
                setup(0,86,"Tree/tree_14",true);
                setup(0,87,"Tree/tree_15",true);
                setup(0,88,"Tree/tree_16",true);
                setup(0,89,"Tree/tree_17",true);
                setup(0,90,"Tree/tree_18",true);
                setup(0,91,"Tree/tree_19",true);
                setup(0,92,"Tree/tree_20",true);
                setup(0,93,"Tree/tree_21",true);
                setup(0,94,"Tree/tree_22",true);
            // MOUNTAIN TILES:
                setup(0,95,"Mountain/mountain_1",true);
                setup(0,96,"Mountain/mountain_2",true);
                setup(0,97,"Mountain/mountain_3",true);
                setup(0,98,"Mountain/mountain_4",true);
                setup(0,99,"Mountain/mountain_5",true);
                setup(0,100,"Mountain/mountain_6",true);
                setup(0,101,"Mountain/mountain_7",true);
                setup(0,102,"Mountain/mountain_8",true);
                setup(0,103,"Mountain/mountain_9",true);
                setup(0,104,"Mountain/mountain_10",true);
                setup(0,105,"Mountain/mountain_11",true);
                setup(0,106,"Mountain/mountain_12",true);
                setup(0,107,"Mountain/mountain_13",true);
                setup(0,108,"Mountain/mountain_14",true);
                setup(0,109,"Mountain/mountain_15",true);
                setup(0,110,"Mountain/mountain_16",true);
                setup(0,111,"Mountain/mountain_17",true);
                setup(0,112,"Mountain/mountain_18",true);
                setup(0,113,"Mountain/mountain_19",true);
                setup(0,114,"Mountain/mountain_20",true);
                setup(0,115,"Mountain/mountain_21",true);
                setup(0,116,"Mountain/mountain_22",true);
                setup(0,117,"Mountain/mountain_23",true);
                setup(0,118,"Mountain/mountain_24",true);
                setup(0,119,"Mountain/mountain_25",true);
                setup(0,120,"Mountain/mountain_26",true);
                setup(0,121,"Mountain/mountain_27",true);
                setup(0,122,"Mountain/mountain_28",true);
                setup(0,123,"Mountain/mountain_29",true);
                setup(0,124,"Mountain/mountain_30",true);
                setup(0,125,"Mountain/mountain_31",true);
                setup(0,126,"Mountain/mountain_32",true);
            // CAVES TILES:
                setup(0,127,"Mountain/cave_1",true);
                setup(0,128,"Mountain/cave_2",true);
                setup(0,129,"Mountain/cave_3",false);
                setup(0,130,"Mountain/cave_4",false);
                setup(0,131,"Mountain/cave_5",false);
                setup(0,132,"Mountain/cave_6",false);

            // BOAT TILES:
                setup(0,133,"Boat/boat_1_1",true);
                setup(0,136,"Boat/boat_1_2",true);
                setup(0,134,"Boat/boat_2_1",true);
                setup(0,137,"Boat/boat_2_2",true);
                setup(0,135,"Boat/boat_3_1",true);
                setup(0,138,"Boat/boat_3_2",true);

            // CONSTRUCTION TILES:
                setup(0,139,"Construction/construction_1",true);
                setup(0,140,"Construction/construction_2",true);
                setup(0,141,"Construction/construction_3",true);
                setup(0,142,"Construction/construction_4",true);
            // TOWERS TILES:
                setup(0,143,"Construction/Tower_1",true);
                setup(0,144,"Construction/Tower_2",true);
                setup(0,145,"Construction/Tower_3",true);
                setup(0,146,"Construction/Tower_4",true);
                setup(0,147,"Construction/Tower_5",true);
                setup(0,148,"Construction/Tower_6",true);
                setup(0,149,"Construction/Tower_7",true);
                setup(0,150,"Construction/Tower_8",true);
                setup(0,151,"Construction/Tower_9",true);
                setup(0,152,"Construction/Tower_10",true);
                setup(0,153,"Construction/Tower_11",true);
                setup(0,154,"Construction/Tower_12",true);

            // SMITHY TILES:
                setup(0,155,"Smithy/smithy_1",true);
                setup(0,156,"Smithy/smithy_2",true);
                setup(0,157,"Smithy/smithy_3",true);
                setup(0,158,"Smithy/smithy_4",true);
                setup(0,159,"Smithy/smithy_5",true);
                setup(0,160,"Smithy/smithy_6",true);
                setup(0,161,"Smithy/smithy_7",true);
                setup(0,162,"Smithy/smithy_8",true);
                setup(0,163,"Smithy/smithy_9",true);
                setup(0,164,"Smithy/smithy_10",true);
                setup(0,165,"Smithy/smithy_11",true);
                setup(0,166,"Smithy/smithy_12",true);
                setup(0,167,"Smithy/smithy_13",true);
                setup(0,168,"Smithy/smithy_14",true);
                setup(0,169,"Smithy/smithy_15",true);
                setup(0,170,"Smithy/smithy_16",true);
            // HOUSE TILES:
                setup(0,171,"House/house_1",true);
                setup(0,172,"House/house_2",true);
                setup(0,173,"House/house_3",true);
                setup(0,174,"House/house_4",true);
                setup(0,175,"House/house_5",true);
                setup(0,176,"House/house_6",true);
                setup(0,177,"House/house_7",true);
                setup(0,178,"House/house_8",true);
                setup(0,179,"House/house_9",true);
                setup(0,180,"House/house_10",true);
                setup(0,181,"House/house_11",true);
                setup(0,182,"House/house_12",true);
            // CASTLE TILES:
                setup(0,183,"Castle/castle_1",true);
                setup(0,184,"Castle/castle_2",true);
                setup(0,185,"Castle/castle_3",true);
                setup(0,186,"Castle/castle_4",true);
                setup(0,187,"Castle/castle_5",true);
                setup(0,188,"Castle/castle_6",true);
                setup(0,189,"Castle/castle_7",true);
                setup(0,190,"Castle/castle_8",true);
                setup(0,191,"Castle/castle_9",true);
                setup(0,192,"Castle/castle_10",true);
                setup(0,193,"Castle/castle_11",true);
                setup(0,194,"Castle/castle_12",true);
                setup(0,195,"Castle/castle_13",true);
                setup(0,196,"Castle/castle_14",true);
                setup(0,197,"Castle/castle_15",true);
            // STATUE TILES:
                setup(0,198,"Statue/statue_1",true);
                setup(0,199,"Statue/statue_2",false);
                setup(0,200,"Statue/statue_3",true);
                setup(0,201,"Statue/statue_4",false);
                setup(0,202,"Statue/statue_5",true);
                setup(0,203,"Statue/statue_6",false);
                setup(0,204,"Statue/statue_7",true);
                setup(0,205,"Statue/statue_8",true);
                setup(0,206,"Statue/statue_9",true);
                setup(0,207,"Statue/statue_10",false);
                setup(0,208,"Statue/statue_11",false);
                setup(0,209,"Statue/statue_12",true);
                setup(0,210,"Statue/statue_13",true);
                setup(0,211,"Statue/statue_14",true);
                setup(0,212,"Statue/statue_15",false);
                setup(0,213,"Statue/statue_16",false);
                setup(0,214,"Statue/statue_17",true);
                setup(0,215,"Statue/statue_18",true);
                setup(0,216,"Statue/statue_19",true);
                setup(0,217,"Statue/statue_20",false);
                setup(0,218,"Statue/statue_21",true);
                setup(0,219,"Statue/statue_22",false);
                setup(0,220,"Statue/statue_23",false);
                setup(0,221,"Statue/statue_24",false);
                setup(0,222,"Statue/statue_25",true);
            // GRAVE:
                setup(0,223,"Statue/grave_1",true);
                setup(0,224,"Statue/grave_2_1",true);
                setup(0,225,"Statue/grave_2_2",true);
                setup(0,226,"Statue/grave_3_1",true);
                setup(0,227,"Statue/grave_3_2",true);
                setup(0,228,"Statue/grave_4_1",true);
                setup(0,229,"Statue/grave_4_2",true);
                setup(0,230,"Statue/grave_5_1",true);
                setup(0,231,"Statue/grave_5_2",true);
                setup(0,232,"Statue/barrels_1",true);
                setup(0,233,"Statue/barrels_2",true);
                setup(0,234,"Statue/urn",true);
                setup(0,235,"Statue/urn2",true);
                setup(0,236,"Statue/wooden_box_1",true);
                setup(0,237,"Statue/wooden_box_2",true);
                setup(0,238,"Statue/wooden_box_3",true);
                setup(0,239,"Statue/wooden_box_4",true);
            // DESTRUCTION:
                setup(0,240,"Statue/stone_column_1",true);
                setup(0,241,"Statue/stone_column_2",true);
                setup(0,242,"Statue/stone_column_3",true);
                setup(0,243,"Statue/stone_column_4",true);
                setup(0,244,"Statue/stone_column_5",true);
                setup(0,245,"Statue/stone_column_6",true);
                setup(0,246,"Statue/stone_column_7",true);
                setup(0,247,"Statue/stone_column_8",true);
        }
            public void getTileImageSecondMap() {
                // WATER TILES:
                    // DUNGEON TILES:
                    setup(1,248,"Dungeon/void",true);
                    setup(1,249,"Dungeon/ground",false);
                    setup(1,250,"Dungeon/stone_1_1",true);
                    setup(1,251,"Dungeon/stone_1_2",true);
                    setup(1,252,"Dungeon/stone_1_3",true);
                    setup(1,253,"Dungeon/stone_1_4",true);
                    setup(1,254,"Dungeon/stone_1_5",true);
                    setup(1,255,"Dungeon/stone_1_6",true);
                    setup(1,256,"Dungeon/stone_tower_1",true);
                    setup(1,257,"Dungeon/stone_tower_2",true);
                    setup(1,258,"Dungeon/stone_tower_3",true);
                    setup(1,259,"Dungeon/stone_tower_4",true);
                    setup(1,260,"Dungeon/stone_tower_6",true);
                    setup(1,261,"Dungeon/stone_tower_7",true);
                    setup(1,262,"Dungeon/stone_tower_8",true);
                    setup(1,263,"Dungeon/stone_tower_9",true);
                    setup(1,264,"Dungeon/stone_tower_10",true);
                    setup(1,265,"Dungeon/stone_tower_11",true);
                    setup(1,266,"Dungeon/stone_tower_12",true);
                    setup(1,267,"Dungeon/stone_tower_13",true);
                    setup(1,268,"Dungeon/stone_tower_14",true);
                    setup(1,269,"Dungeon/stone_tower_15",true);
                    setup(1,270,"Dungeon/stone_tower_16",true);
                    setup(1,271,"Dungeon/stone_tower_17",true);
                    setup(1,272,"Dungeon/stone_tower_18",true);
                    setup(1,273,"Dungeon/stone_cave_1",true);
                    setup(1,274,"Dungeon/stone_cave_2",true);
                    setup(1,275,"Dungeon/stone_cave_3",true);
                    setup(1,276,"Dungeon/stone_cave_4",true);
                    setup(1,277,"Dungeon/stone_cave_5",true);
                    setup(1,278,"Dungeon/stone_cave_6",true);
                    setup(1,279,"Dungeon/stone_stair_1",false);
                    setup(1,280,"Dungeon/stone_stair_2",false);
                    setup(1,281,"Dungeon/stone_stair_3",false);
                    setup(1,282,"Dungeon/stone_stair_4",true);
                    setup(1,283,"Dungeon/stone_stair_5",false);
                    setup(1,284,"Dungeon/stone_stair_6",true);
                    setup(1,285,"Dungeon/stone_stair_7",true);
                    setup(1,286,"Dungeon/stone_stair_8",false);
                    setup(1,287,"Dungeon/stone_stair_9",true);
                    setup(1,288,"Dungeon/stone_2_1",true);
                    setup(1,289,"Dungeon/stone_2_2",true);
                    setup(1,290,"Dungeon/stone_2_3",true);
                    setup(1,291,"Dungeon/stone_2_4",true);
                    setup(1,292,"Dungeon/stone_2_5",true);
                    setup(1,293,"Dungeon/stone_2_6",true);
                    setup(1,294,"Dungeon/stone_3_1",true);
                    setup(1,295,"Dungeon/stone_3_2",true);
                    setup(1,296,"Dungeon/stone_3_3",true);
                    setup(1,297,"Dungeon/stone_3_4",true);
                    setup(1,298,"Dungeon/bone_1",true);
                    setup(1,299,"Dungeon/bone_2",true);
                    setup(1,300,"Dungeon/bone_3",true);
                    setup(1,301,"Dungeon/bone_4",true);
                    setup(1,302,"Dungeon/sign",true);
                    setup(1,303,"Dungeon/left_tentacles_1",true);
                    setup(1,304,"Dungeon/left_tentacles_2",true);
                    setup(1,305,"Dungeon/left_tentacles_3",true);
                    setup(1,306,"Dungeon/left_tentacles_4",true);
                    setup(1,307,"Dungeon/left_tentacles_5",true);
                    setup(1,308,"Dungeon/left_tentacles_6",true);
                    setup(1,309,"Dungeon/left_tentacles_7",true);
                    setup(1,310,"Dungeon/left_tentacles_8",true);
                    setup(1,311,"Dungeon/left_tentacles_9",true);
                    setup(1,312,"Dungeon/left_tentacles_10",true);
                    setup(1,313,"Dungeon/left_tentacles_11",true);
                    setup(1,314,"Dungeon/left_tentacles_12",true);
                    setup(1,315,"Dungeon/left_tentacle_1",true);
                    setup(1,316,"Dungeon/left_tentacle_2",true);
                    setup(1,317,"Dungeon/left_tentacle_3",true);
                    setup(1,318,"Dungeon/right_tentacles_1",true);
                    setup(1,319,"Dungeon/right_tentacles_2",true);
                    setup(1,320,"Dungeon/right_tentacles_3",true);
                    setup(1,321,"Dungeon/right_tentacles_4",true);
                    setup(1,322,"Dungeon/right_tentacles_5",true);
                    setup(1,323,"Dungeon/right_tentacles_6",true);
                    setup(1,324,"Dungeon/right_tentacles_7",true);
                    setup(1,325,"Dungeon/right_tentacles_8",true);
                    setup(1,326,"Dungeon/right_tentacles_9",true);
                    setup(1,327,"Dungeon/right_tentacles_10",true);
                    setup(1,328,"Dungeon/right_tentacles_11",true);
                    setup(1,329,"Dungeon/right_tentacles_12",true);
                    setup(1,330,"Dungeon/right_tentacle_1",true);
                    setup(1,331,"Dungeon/right_tentacle_2",true);
                    setup(1,332,"Dungeon/right_tentacle_3",true);
                    setup(1,333,"Dungeon/stone_lower_right_corner",true);
                    setup(1,334,"Dungeon/stone_lower_left_corner",true);
                    setup(1,335,"Dungeon/stone_upper_right_corner",true);
                    setup(1,336,"Dungeon/stone_lower_left_corner",true);

            }
        public void setup(int map, int index, String imagePath, boolean collision) {

                UtilityTool uTool = new UtilityTool();

                String filePath = "res/Tile/" + imagePath + ".png";
                File imageFile = new File(filePath);

                try (FileInputStream image = new FileInputStream(imageFile)) {
                        tile[map][index] = new Tile();
                        tile[map][index].tile_image = ImageIO.read(image);
                        tile[map][index].tile_image = uTool.scaleImage(tile[map][index].tile_image, gamePanel.tileSize, gamePanel.tileSize);
                        tile[map][index].collision = collision;
                } catch(IOException e) {
                        e.printStackTrace();
                }

        }
      public void loadMap(String filePath, int map) {
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
                      mapTileNum[map][column][row] = num;
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

                int tileNum = mapTileNum[gamePanel.currentMap][worldColumn][worldRow];

                int worldX = worldColumn * gamePanel.tileSize;
                int worldY = worldRow * gamePanel.tileSize;
                int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

                g2.drawImage(tile[gamePanel.currentMap][tileNum].tile_image,screenX,screenY,gamePanel.tileSize, gamePanel.tileSize,null);

                worldColumn++;

                if (worldColumn == gamePanel.maxWorldColumn) {
                    worldColumn = 0;
                    worldRow++;
                }
            }
    }
}

