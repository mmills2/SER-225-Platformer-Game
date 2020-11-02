package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Enemies.FishEnemy;
import EnhancedMapTiles.ChangeCameraState;
import EnhancedMapTiles.SkyWater;
import EnhancedMapTiles.TopWater;
import EnhancedMapTiles.Water;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import NPCs.Walrus;
import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;

// Represents the map that is used as a background for the main menu and credits menu screen
public class TitleScreenMap extends Map {

    public TitleScreenMap() {
        super("title_screen_map.txt", new CommonTileset(), new Point(1, 9));
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        enhancedMapTiles.add(new ChangeCameraState(getPositionByTileIndex(13, 3)));
        
        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        npcs.add(new Walrus(getPositionByTileIndex(18, 9).subtract(new Point(0, 13)), this, "Congratulations you found me"));

        return npcs;
    }
}
