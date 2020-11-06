package NPCs;

import Builders.FrameBuilder;
import Engine.*;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.*;
import SpriteFont.SpriteFont;
import Utils.Point;
import Utils.Stopwatch;

import java.awt.*;
import java.util.HashMap;

// This class is for the walrus NPC
public class Walrus extends NPC {

    protected String speech;
    protected SpriteFont walrusText;
    protected boolean isSpeechSet;
    protected double xDistFromPlayer;
    protected double yDistFromPlayer;
    protected double dist;
    protected Secret associatedSecret;
    protected boolean foundSecret;
    protected boolean secretTimerStarted;
    protected boolean secretTimerEnded;
    protected Stopwatch secretTimer = new Stopwatch();


    public Walrus(Point location, Map map, String speech) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Walrus.png"), 24, 24), "TAIL_DOWN", 5000);
        this.speech = speech;
        isSpeechSet = false;
        associatedSecret = null;
        foundSecret = false;
        secretTimerStarted = false;
        secretTimerEnded = false;
    }
    public Walrus(Point location, Map map, String speech, Secret associatedSecret) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Walrus.png"), 24, 24), "TAIL_DOWN", 5000);
        this.speech = speech;
        isSpeechSet = false;
        this.associatedSecret = associatedSecret;
        foundSecret = false;
        secretTimerStarted = false;
        secretTimerEnded = false;
    }

    @Override
    protected SpriteFont createMessage() {
        walrusText = new SpriteFont("default", getX(), getY() - 10, "Arial", 12, Color.BLACK);
        return walrusText;
    }

    public void update(Player player) {

        if(!isSpeechSet){
            walrusText.setText(speech);
            isSpeechSet = true;
        }

        xDistFromPlayer = Math.pow((this.getX() - player.getX()), 2);
        yDistFromPlayer = Math.pow((this.getY() - player.getY()), 2);
        dist = Math.sqrt(xDistFromPlayer + yDistFromPlayer);

        // while npc is being talked to, it raises its tail up (in excitement?)
        if (talkedTo) {
            currentAnimationName = "TAIL_UP";
        } else if (dist < 150 && !this.getTalkedTo()) {
            this.changeWalrusCostume("PLAYER_CLOSE");
        } else {
            currentAnimationName = "TAIL_DOWN";
        }
        super.update(player);
    }

    @Override
    public HashMap<String, Frame[]> getAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
           put("TAIL_DOWN", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0), 0)
                           .withScale(3)
                           .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                           .build()
           });
            put("TAIL_UP", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put("PLAYER_CLOSE", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        if(foundSecret && !secretTimerEnded){
            if(!secretTimerStarted) {
                secretTimer.setWaitTime(5000);
                secretTimerStarted = true;
            }
            associatedSecret.draw(graphicsHandler);
            if(secretTimer.isTimeUp()){
                secretTimerEnded = true;
            }
        }
    }

    @Override
    public void drawMessage(GraphicsHandler graphicsHandler) {
        // draws a box with a border (think like a speech box)
        graphicsHandler.drawFilledRectangleWithBorderAndText(Math.round(getCalibratedXLocation() - 2), Math.round(getCalibratedYLocation() - 24), 25, Color.WHITE, Color.BLACK, 2, speech);

        // draws message "Hello" in the above speech box
        message.setLocation(getCalibratedXLocation() + 2, getCalibratedYLocation() - 8);
        message.draw(graphicsHandler);
    }

    public void changeWalrusCostume(String costumeName){
        currentAnimationName = costumeName;
        this.updateCurrentFrame();
    }

    @Override
    public void checkTalkedTo(Player player){
        if (intersects(player) && Keyboard.isKeyDown(Key.SPACE) && player.getLevelState() != LevelState.PLAYER_DEAD) {
            talkedTo = true;
            timer.setWaitTime(talkedToTime);
            if(associatedSecret != null && !Screen.getSecretStatus(associatedSecret.getId())){
                foundSecret = true;
                Screen.anySecretFound = true;
                Screen.setSecretStatus(associatedSecret.getId(), true);
            }
        };
        if (talkedTo && timer.isTimeUp()) {
            talkedTo = false;
        }
    }

}
