


package Maps;


import java.util.ArrayList;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Enemies.FishEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.*;

import GameObject.Rectangle;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.TileType;
import Level.Tileset;
import NPCs.Walrus;
import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;

public class Level5 extends Map {

    public Level5() {

        super("level5.txt", new CommonTileset(), new Point(1, 11));
    }

    // TODO Auto-generated constructor stub
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        //enemies.add(new BugEnemy(getPositionByTileIndex(4, 11), Direction.LEFT));
        enemies.add(new DinosaurEnemy(getPositionByTileIndex(11, 2).addY(2), getPositionByTileIndex(16, 2).addY(2), Direction.RIGHT));
        enemies.add(new DinosaurEnemy(getPositionByTileIndex(20, 4).addY(2), getPositionByTileIndex(26, 4).addY(2), Direction.RIGHT));
        enemies.add(new DinosaurEnemy(getPositionByTileIndex(33, 2).addY(2), getPositionByTileIndex(37, 2).addY(2), Direction.RIGHT));
        enemies.add(new DinosaurEnemy(getPositionByTileIndex(40, 4).addY(2), getPositionByTileIndex(45, 4).addY(2), Direction.RIGHT));

        enemies.add(new FishEnemy(getPositionByTileIndex(52, 17), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(6, 14), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(12, 15), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(49, 16), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(13, 17), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(52, 14), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(51, 15), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(12, 16), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(27, 17), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(35, 14), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(32, 15), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(42, 16), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(46, 17), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(17, 14), Direction.LEFT));
        //enemies.add(new FishEnemy(getPositionByTileIndex(36, 15), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(30, 16), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(52, 17), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(6, 14), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(12, 15), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(49, 16), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(11, 17), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(52, 14), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(40, 15), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(39, 15), Direction.LEFT));
        //enemies.add(new FishEnemy(getPositionByTileIndex(3, 15), Direction.LEFT));
        //enemies.add(new FishEnemy(getPositionByTileIndex(3, 15), Direction.RIGHT));
        //enemies.add(new FishEnemy(getPositionByTileIndex(36, 16), Direction.LEFT));
        //enemies.add(new FishEnemy(getPositionByTileIndex(35, 16), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(31, 16), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(32, 16), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(33, 16), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(34, 16), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(52, 14), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(20, 14), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(29, 14), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(28, 14), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(27, 17), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(26, 17), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(25, 17), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(21, 17), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(22, 17), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(23, 17), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(24, 14), Direction.LEFT));
        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();


        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getPositionByTileIndex(2, 2),
                getPositionByTileIndex(12, 2),
                TileType.JUMP_THROUGH_PLATFORM,
                3,

                new Rectangle(0, 6, 16, 4),
                Direction.RIGHT
        ));
        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getPositionByTileIndex(18, 3),
                getPositionByTileIndex(26, 3),
                TileType.JUMP_THROUGH_PLATFORM,
                3,

                new Rectangle(0, 6, 16, 4),
                Direction.RIGHT
        ));
        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getPositionByTileIndex(27, 2),
                getPositionByTileIndex(38, 2),
                TileType.JUMP_THROUGH_PLATFORM,
                3,

                new Rectangle(0, 6, 16, 4),
                Direction.RIGHT
        ));




        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getPositionByTileIndex(46, 5),
                getPositionByTileIndex(56, 5),
                TileType.JUMP_THROUGH_PLATFORM,
                3,

                new Rectangle(0, 6, 16, 4),
                Direction.LEFT
        ));

        for (int i = 0; i < 49; i++) {
            enhancedMapTiles.add(new TopWater(
                    getPositionByTileIndex(6 + i, 13)
            ));
            enhancedMapTiles.add(new SkyWater(
                    getPositionByTileIndex(6 + i, 12)
            ));
            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(6 + i, 14)
            ));
        }
        enhancedMapTiles.add(new SkyWater(
                getPositionByTileIndex(24, 19)
        ));
        for (int i = 0; i < 47; i++) {

            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(6 + i, 14)
            ));
        }
        for (int i = 0; i < 41; i++) {

            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(11 + i, 15)
            ));
        }
        for (int i = 0; i < 38; i++) {

            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(12 + i, 16)
            ));
        }
        enhancedMapTiles.add(new EndLevelBox(
                getPositionByTileIndex(57, 8)
        ));


        return enhancedMapTiles;

    }
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        npcs.add(new Walrus(getPositionByTileIndex(37, 19).subtract(new Point(0, 13)), this, "Welcome to the Sneaky Cave"));

        return npcs;
    }

}