package PowerUps;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.LevelState;
import Level.MapEntityStatus;
import Level.Player;
import Level.PowerUp;
import Screens.PlayLevelScreen;
import Utils.Point;

import java.util.HashMap;

// This class is for the fish power up
// when the player touches it, the player gets an extra life
public class Pepper extends PowerUp {

    public Pepper(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Pepper.png"), 16, 16), "DEFAULT");
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (intersects(player) && player.getLevelState() != LevelState.PLAYER_DEAD) {
            player.setPepperedUp(true);
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
