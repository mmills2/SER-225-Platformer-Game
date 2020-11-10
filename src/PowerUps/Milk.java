package PowerUps;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.MapEntityStatus;
import Level.Player;
import Level.PowerUp;
import Utils.Point;

import java.util.HashMap;

// This class is for the milk power up
// when the player touches it, the player will be able to take another hit in the level without dying
public class Milk extends PowerUp {

    public Milk(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Milk.png"), 16, 16), "DEFAULT");
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (intersects(player)) {
            player.setMilkedUp(true);
            setMapEntityStatus(MapEntityStatus.REMOVED);
        }
    }

    @Override
    public HashMap<String, Frame[]> getAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 500)
                            .withScale(3)
                            .withBounds(1, 1, 14, 14)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 500)
                            .withScale(3)
                            .withBounds(1, 1, 14, 14)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 500)
                            .withScale(3)
                            .withBounds(1, 1, 14, 14)
                            .build()
            });
        }};
    }
}
