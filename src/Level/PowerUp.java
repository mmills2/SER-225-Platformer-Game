package Level;

import Engine.GraphicsHandler;
import GameObject.*;

// This class is a base class for all power ups in the game -- all power ups should extend from it
public class PowerUp extends MapEntity {

    public PowerUp(float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
        setIsRespawnable(false);
    }

    public void update(Player player) {
        super.update();
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
