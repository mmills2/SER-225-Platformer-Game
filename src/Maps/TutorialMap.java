package Maps;




	import java.util.ArrayList;

    import EnhancedMapTiles.EndLevelBox;
    import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;

    import NPCs.Walrus;
	import Tilesets.CommonTileset;
    import Utils.Point;

	// Represents the map that is used as a background for the main menu and credits menu screen
	public class TutorialMap extends Map {

	    public TutorialMap() {
	        super("Tutorial.txt", new CommonTileset(), new Point(1, 9));
	    }

	    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
	        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

	    

	        enhancedMapTiles.add(new EndLevelBox(
	                getPositionByTileIndex(10, 5)
	        ));

	        return enhancedMapTiles;
	    }
	    @Override
	    public ArrayList<NPC> loadNPCs() {
	        ArrayList<NPC> npcs = new ArrayList<>();

	        npcs.add(new Walrus(getPositionByTileIndex(3, 9).subtract(new Point(0, 13)), this, "The Controls are the Arrow keys: up = Jump, down= crouch, left= walk left, right= walk right"));
	       
	        return npcs;
	    }
	
	    
	}

