package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.*;
import GameObject.Rectangle;
import Level.*;
import NPCs.Walrus;
import PowerUps.Fish;
import PowerUps.Pepper;
import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;
import PowerUps.Milk;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class TestMap extends Map {

    public TestMap() {
        super("test_map.txt", new CommonTileset(), new Point(1, 11));
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new BugEnemy(getPositionByTileIndex(15, 9), Direction.LEFT));
        enemies.add(new DinosaurEnemy(getPositionByTileIndex(19, 1).addY(2), getPositionByTileIndex(22, 1).addY(2), Direction.RIGHT, this));
        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getPositionByTileIndex(24, 6),
                getPositionByTileIndex(27, 6),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));

        enhancedMapTiles.add(new EndLevelBox(
                getPositionByTileIndex(32, 7)
        ));

        for(int i = 0; i < 4; i++){
            enhancedMapTiles.add(new SkyWater(
                    getPositionByTileIndex(26 + i, 10)
            ));
            for(int j = 0; j < 2; j++) {
                enhancedMapTiles.add(new Water(
                        getPositionByTileIndex(26 + i, 11 + j)
                ));
            }
        }

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        npcs.add(new Walrus(getPositionByTileIndex(30, 10).subtract(new Point(0, 13)), this, "Hello!"));

        return npcs;
    }

    @Override
    public ArrayList<PowerUp> loadPowerUps() {
        ArrayList<PowerUp> powerUps = new ArrayList<>();

        powerUps.add(new Milk(getPositionByTileIndex(18, 9)));
        powerUps.add(new Fish(getPositionByTileIndex(17, 9)));
        powerUps.add(new Pepper(getPositionByTileIndex(19, 9)));

        return powerUps;
    }
}
