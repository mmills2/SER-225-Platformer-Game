package Players;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Player;

import java.util.HashMap;

// This is the class for the Cat player character
// basically just sets some values for physics and then defines animations
public class Cat extends Player {

    public Cat(float x, float y) {
        super(new SpriteSheet(ImageLoader.load("Cat.png"), 24, 24), x, y, "STAND_RIGHT");
        gravity = .5f;
        terminalVelocityY = 6f;
        jumpHeight = 14.5f;
        jumpDegrade = .5f;
        walkSpeed = 2.1f;
        momentumYIncrease = .5f;
        JUMP_KEY = Key.UP;
        MOVE_LEFT_KEY = Key.LEFT;
        MOVE_RIGHT_KEY = Key.RIGHT;
        CROUCH_KEY = Key.DOWN;
    }

    public void update() {
        super.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        //drawBounds(graphicsHandler, new Color(255, 0, 0, 170));
    }

    @Override
    public HashMap<String, Frame[]> getAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 0)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("INVINCIBLE", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 1), 200)
                        .withScale(3)
                        .withBounds(8, 9, 8, 9)
                        .build()
            });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("JUMP_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 0)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("JUMP_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("FALL_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(3, 0), 0)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("FALL_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(3, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("CROUCH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 0), 0)
                            .withScale(3)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });

            put("CROUCH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });

            put("DEATH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(5, 0), 100)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 1), 100)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 2), -1)
                            .withScale(3)
                            .build()
            });

            put("DEATH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(5, 0), 100)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 1), 100)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 2), -1)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });

            put("MILKED_STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(6, 0), 0)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MILKED_STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(6, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MILKED_WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(7, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(7, 1), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(7, 2), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(7, 3), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MILKED_WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(7, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(7, 1), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(7, 2), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(7, 3), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MILKED_JUMP_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(8, 0), 0)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MILKED_JUMP_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(8, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MILKED_FALL_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(9, 0), 0)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MILKED_FALL_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(9, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MILKED_CROUCH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(10, 0), 0)
                            .withScale(3)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });

            put("MILKED_CROUCH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(10, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });

            put("DEATH_MILKED_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(11, 0), 100)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 1), 100)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 2), -1)
                            .withScale(3)
                            .build()
            });

            put("DEATH_MILKED_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(11, 0), 100)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 1), 100)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 2), -1)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });

            put("PEPPERED_STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(12, 0), 0)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("PEPPERED_STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(12, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("PEPPERED_WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(13, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(13, 1), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(13, 2), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(13, 3), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("PEPPERED_WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(13, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(13, 1), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(13, 2), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(13, 3), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("PEPPERED_JUMP_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(14, 0), 0)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("PEPPERED_JUMP_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(14, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("PEPPERED_FALL_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(15, 0), 0)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("PEPPERED_FALL_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(15, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("PEPPERED_CROUCH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(16, 0), 0)
                            .withScale(3)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });

            put("PEPPERED_CROUCH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(16, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });

            put("DEATH_PEPPERED_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(17, 0), 100)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(17, 1), 100)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(17, 2), -1)
                            .withScale(3)
                            .build()
            });

            put("DEATH_PEPPERED_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(17, 0), 100)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(17, 1), 100)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(17, 2), -1)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });

            put("MEPPERED_STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(18, 0), 0)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MEPPERED_STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(18, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MEPPERED_WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(19, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(19, 1), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(19, 2), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(19, 3), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MEPPERED_WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(19, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(19, 1), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(19, 2), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(19, 3), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MEPPERED_JUMP_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(20, 0), 0)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MEPPERED_JUMP_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(20, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MEPPERED_FALL_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(21, 0), 0)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MEPPERED_FALL_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(21, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("MEPPERED_CROUCH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(22, 0), 0)
                            .withScale(3)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });

            put("MEPPERED_CROUCH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(22, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });

            put("DEATH_MEPPERED_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(23, 0), 100)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(23, 1), 100)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(23, 2), -1)
                            .withScale(3)
                            .build()
            });

            put("DEATH_MEPPERED_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(23, 0), 100)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(23, 1), 100)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(23, 2), -1)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
        }};
    }
}
