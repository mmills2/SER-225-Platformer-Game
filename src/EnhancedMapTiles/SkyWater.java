package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;

import java.util.HashMap;

// This class is for the top water
// when the player touches it, the player will be affected by water physics
public class SkyWater extends EnhancedMapTile {
    public SkyWater(Point location) {
        super((ImageLoader.load("InvisibleTile.png")), location.x, location.y, TileType.PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (intersects(player)) {
            player.setPlayerSwimming(false);
        }
    }
}
