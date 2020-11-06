package Maps;


import java.util.ArrayList;


import Enemies.BugEnemy;

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

public class Level3 extends Map{

    public Level3() {

        super("level_3.txt", new CommonTileset(), new Point(1, 11));
    }
    // TODO Auto-generated constructor stub
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new BugEnemy(getPositionByTileIndex(5, 11), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(18, 12), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(29, 11), Direction.LEFT));
        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getPositionByTileIndex(18, 6),
                getPositionByTileIndex(23, 6),
                TileType.JUMP_THROUGH_PLATFORM,
                3,

                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));
        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getPositionByTileIndex(26, 6),
                getPositionByTileIndex(30, 6),
                TileType.JUMP_THROUGH_PLATFORM,
                3,

                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));
        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getPositionByTileIndex(6, 8),
                getPositionByTileIndex(8, 8),
                TileType.JUMP_THROUGH_PLATFORM,
                3,

                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));
        for(int i = 0; i < 12; i++) {
            enhancedMapTiles.add(new TopWater(
                    getPositionByTileIndex(18+i, 11)
            ));
            enhancedMapTiles.add(new SkyWater(
                    getPositionByTileIndex(18+i, 10)
            ));
            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(18 + i, 12)
            ));
        }

        enhancedMapTiles.add(new EndLevelBox(
                getPositionByTileIndex(32, 6)
        ));

        return enhancedMapTiles;
    }
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        npcs.add(new Walrus(getPositionByTileIndex(30, 10).subtract(new Point(0, 13)), this, "hi"));

        return npcs;
    }
}





