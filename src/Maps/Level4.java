


package Maps;


import java.util.ArrayList;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
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

public class Level4 extends Map{

    public Level4() {

        super("level4.txt", new CommonTileset(), new Point(1, 11));
    }
    // TODO Auto-generated constructor stub
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new BugEnemy(getPositionByTileIndex(4, 11), Direction.LEFT));
        //enemies.add(new DinosaurEnemy(getPositionByTileIndex(23, 5).addY(2), getPositionByTileIndex(25, 5).addY(2), Direction.RIGHT));
        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();






        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getPositionByTileIndex(6, 8),
                getPositionByTileIndex(10, 8),
                TileType.JUMP_THROUGH_PLATFORM,
                3,

                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));
        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getPositionByTileIndex(20,5),
                getPositionByTileIndex(22, 5),
                TileType.JUMP_THROUGH_PLATFORM,
                3,

                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));
        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getPositionByTileIndex(18, 8),
                getPositionByTileIndex(20,8),
                TileType.JUMP_THROUGH_PLATFORM,
                3,

                new Rectangle(0, 6,16,4),
                Direction.LEFT
        ));
        for(int i = 0; i < 5; i++) {
            enhancedMapTiles.add(new TopWater(
                    getPositionByTileIndex(5 + i, 12)

            ));
            enhancedMapTiles.add(new SkyWater(
                    getPositionByTileIndex(5 + i, 11)

            ));
        }
        for(int i = 0; i < 8; i++){
            enhancedMapTiles.add(new TopWater(
                    getPositionByTileIndex(18 + i, 11)
            ));
            enhancedMapTiles.add(new SkyWater(
                    getPositionByTileIndex(18 + i, 10)
            ));
            enhancedMapTiles.add(new Water(
                    getPositionByTileIndex(18 + i, 12)
            ));
        }
        for(int i = 0; i < 3; i++){
            enhancedMapTiles.add(new TopWater(
                    getPositionByTileIndex(23 + i,5 )
            ));
            enhancedMapTiles.add(new SkyWater(
                    getPositionByTileIndex(23 + i, 4)
            ));
        }
        enhancedMapTiles.add(new EndLevelBox(
                getPositionByTileIndex(32, 7)
        ));


        return enhancedMapTiles;
    }
        public ArrayList<NPC> loadNPCs() {
            ArrayList<NPC> npcs = new ArrayList<>();

            npcs.add(new Walrus(getPositionByTileIndex(30, 11).subtract(new Point(0, 13)), this, "hi"));

            return npcs;
        }

}
	
	