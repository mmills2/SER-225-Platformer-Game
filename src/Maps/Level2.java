package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Enemies.FishEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.*;
import GameObject.Rectangle;
import Level.*;
import NPCs.Walrus;
import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class Level2 extends Map {

    public Level2() {
        super("level2.txt", new CommonTileset(), new Point(1, 17));
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        enhancedMapTiles.add(new EndLevelBox(
                getPositionByTileIndex(97, 16)
        ));

        for(int i = 0; i < 10; i++) {
            enhancedMapTiles.add(new TopWater(
                    getPositionByTileIndex(3 + i, 19)
            ));
            enhancedMapTiles.add(new SkyWater(
                    getPositionByTileIndex(3 + i, 18)
            ));
            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(3 + i, 20)
            ));
        }

        for(int i = 0; i < 8; i++){
            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(4 + i, 21)
            ));
        }

        for(int i = 0; i < 12; i++){
            enhancedMapTiles.add(new TopWater(
                    getPositionByTileIndex(19 + i, 20)
            ));
            enhancedMapTiles.add(new SkyWater(
                    getPositionByTileIndex(19 + i, 19)
            ));
        }

        for(int i = 0; i < 11; i++){
            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(19 + i, 21)
            ));
        }

        for(int i = 0; i < 27; i++){
            enhancedMapTiles.add(new TopWater(
                    getPositionByTileIndex(44 + i, 17)
            ));
            enhancedMapTiles.add(new SkyWater(
                    getPositionByTileIndex(44 + i, 16)
            ));
        }

        for(int i = 0; i < 25; i++){
            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(44 + i, 18)
            ));
        }

        for(int i = 0; i < 21; i++){
            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(45 + i, 19)
            ));
        }

        for(int i = 0; i < 12; i++){
            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(45 + i, 20)
            ));
        }

        for(int i = 0; i < 8; i++){
            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(45 + i, 21)
            ));
        }

        for(int i = 0; i < 3; i++){
            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(55, 21 + i)
            ));
        }

        enhancedMapTiles.add(new SkyWater(
                getPositionByTileIndex(56, 22)
        ));

        for(int i = 0; i < 7; i++){
            enhancedMapTiles.add(new TopWater(
                    getPositionByTileIndex(87 + i, 21)
            ));
            enhancedMapTiles.add(new SkyWater(
                    getPositionByTileIndex(87 + i, 20)
            ));
        }

        for(int i = 0; i < 6; i++){
            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(87 + i, 22)
            ));
        }

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new BugEnemy(getPositionByTileIndex(62, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(67, 23), Direction.LEFT));
        enemies.add(new DinosaurEnemy(getPositionByTileIndex(13, 18).addY(2), getPositionByTileIndex(18, 18).addY(2), Direction.RIGHT));
        enemies.add(new DinosaurEnemy(getPositionByTileIndex(79, 7).addY(2), getPositionByTileIndex(81, 7).addY(2), Direction.RIGHT));
        enemies.add(new DinosaurEnemy(getPositionByTileIndex(82, 5).addY(2), getPositionByTileIndex(85, 5).addY(2), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(24, 21), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(46, 21), Direction.RIGHT));
        enemies.add(new FishEnemy(getPositionByTileIndex(55, 18), Direction.LEFT));
        enemies.add(new FishEnemy(getPositionByTileIndex(64, 19), Direction.LEFT));
        return enemies;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        npcs.add(new Walrus(getPositionByTileIndex(79, 19).subtract(new Point(0, 13)), this, "Welcome to the hidden grove"));

        return npcs;
    }
}
