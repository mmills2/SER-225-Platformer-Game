package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.TitleScreenMap;
import Players.Cat;
import SpriteFont.SpriteFont;
import Utils.Stopwatch;
import java.awt.*;

// This is the class for the secrets screen
public class HighScoreScreen extends Screen{
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected KeyLocker keyLocker = new KeyLocker();
    protected Player player;
    protected SpriteFont returnInstructionsLabel;
    protected SpriteFont highScoreLabel;
    protected SpriteFont levelOneLabel;
    protected SpriteFont levelTwoLabel;
    protected SpriteFont levelThreeLabel;
    protected SpriteFont levelFourLabel;
    protected SpriteFont levelFiveLabel;

    protected static long levelOneMinutes;
    protected static long levelOneSeconds;
    protected static long levelTwoMinutes;
    protected static long levelTwoSeconds;
    protected static long levelThreeMinutes;
    protected static long levelThreeSeconds;
    protected static long levelFourMinutes;
    protected static long levelFourSeconds;
    protected static long levelFiveMinutes;
    protected static long levelFiveSeconds;

    public HighScoreScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        returnInstructionsLabel = new SpriteFont("Press Esc to return to the menu", 20, 560, "Times New Roman", 30, Color.white);
        highScoreLabel = new SpriteFont("HIGH SCORES", 200, 75, "Comic Sans", 30, Color.red);
        highScoreLabel.setOutlineColor(Color.black);
        highScoreLabel.setOutlineThickness(4);
        if(levelOneSeconds < 10) {
            levelOneLabel = new SpriteFont("Level 1: " + levelOneMinutes + ":0" + levelOneSeconds, 240, 125, "Comic Sans", 20, Color.white);
            levelOneLabel.setOutlineColor(Color.black);
            levelOneLabel.setOutlineThickness(2);
        }
        if(levelOneSeconds >= 10) {
            levelOneLabel = new SpriteFont("Level 1: " + levelOneMinutes + ":" + levelOneSeconds, 240, 125, "Comic Sans", 20, Color.white);
            levelOneLabel.setOutlineColor(Color.black);
            levelOneLabel.setOutlineThickness(2);
        }
        if(levelTwoSeconds < 10) {
            levelTwoLabel = new SpriteFont("Level 2: " + levelTwoMinutes + ":0" + levelTwoSeconds, 240, 175, "Comic Sans", 20, Color.white);
            levelTwoLabel.setOutlineColor(Color.black);
            levelTwoLabel.setOutlineThickness(2);
        }
        if(levelTwoSeconds >= 10) {
            levelTwoLabel = new SpriteFont("Level 2: " + levelTwoMinutes + ":" + levelTwoSeconds, 240, 175, "Comic Sans", 20, Color.white);
            levelTwoLabel.setOutlineColor(Color.black);
            levelTwoLabel.setOutlineThickness(2);
        }
        if(levelThreeSeconds < 10) {
            levelThreeLabel = new SpriteFont("Level 3: " + levelThreeMinutes + ":0" + levelThreeSeconds, 240, 225, "Comic Sans", 20, Color.white);
            levelThreeLabel.setOutlineColor(Color.black);
            levelThreeLabel.setOutlineThickness(2);
        }
        if(levelThreeSeconds >= 10) {
            levelThreeLabel = new SpriteFont("Level 3: " + levelThreeMinutes + ":" + levelThreeSeconds, 240, 225, "Comic Sans", 20, Color.white);
            levelThreeLabel.setOutlineColor(Color.black);
            levelThreeLabel.setOutlineThickness(2);
        }
        if(levelFourSeconds < 10) {
            levelFourLabel = new SpriteFont("Level 4: " + levelFourMinutes + ":0" + levelFourSeconds, 240, 275, "Comic Sans", 20, Color.white);
            levelFourLabel.setOutlineColor(Color.black);
            levelFourLabel.setOutlineThickness(2);
        }
        if(levelFourSeconds >= 10) {
            levelFourLabel = new SpriteFont("Level 4: " + levelFourMinutes + ":" + levelFourSeconds, 240, 275, "Comic Sans", 20, Color.white);
            levelFourLabel.setOutlineColor(Color.black);
            levelFourLabel.setOutlineThickness(2);
        }
        if(levelFiveSeconds < 10) {
            levelFiveLabel = new SpriteFont("Level 5: " + levelFiveMinutes + ":0" + levelFiveSeconds, 240, 325, "Comic Sans", 20, Color.white);
            levelFiveLabel.setOutlineColor(Color.black);
            levelFiveLabel.setOutlineThickness(2);
        }
        if(levelFiveSeconds >= 10) {
            levelFiveLabel = new SpriteFont("Level 5: " + levelFiveMinutes + ":" + levelFiveSeconds, 240, 325, "Comic Sans", 20, Color.white);
            levelFiveLabel.setOutlineColor(Color.black);
            levelFiveLabel.setOutlineThickness(2);
        }
        this.player = new Cat(background.getPlayerStartPosition().x, background.getPlayerStartPosition().y);
    }

    @Override
    public void update() {
        background.update(player);

        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }

        // if esc is pressed, go back to main menu
        if (!keyLocker.isKeyLocked(Key.ESC) && Keyboard.isKeyDown(Key.ESC)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
        highScoreLabel.draw(graphicsHandler);
        levelOneLabel.draw(graphicsHandler);
        levelTwoLabel.draw(graphicsHandler);
        levelThreeLabel.draw(graphicsHandler);
        levelFourLabel.draw(graphicsHandler);
        levelFiveLabel.draw(graphicsHandler);
    }

    public static void setLevelOneMinutes(long minutes)
    {
        levelOneMinutes = minutes;
    }
    public static void setLevelTwoMinutes(long minutes)
    {
        levelTwoMinutes = minutes;
    }
    public static void setLevelThreeMinutes(long minutes)
    {
        levelThreeMinutes = minutes;
    }
    public static void setLevelFourMinutes(long minutes)
    {
        levelFourMinutes = minutes;
    }
    public static void setLevelFiveMinutes(long minutes)
    {
        levelFiveMinutes = minutes;
    }

    public static void setLevelOneSeconds(long seconds)
    {
        levelOneSeconds = seconds;
    }
    public static void setLevelTwoSeconds(long seconds)
    {
        levelTwoSeconds = seconds;
    }
    public static void setLevelThreeSeconds(long seconds)
    {
        levelThreeSeconds = seconds;
    }
    public static void setLevelFourSeconds(long seconds)
    {
        levelFourSeconds = seconds;
    }
    public static void setLevelFiveSeconds(long seconds)
    {
        levelFiveSeconds = seconds;
    }

    public static long getLevelOneMinutes()
    {
        return levelOneMinutes;
    }
    public static long getLevelTwoMinutes()
    {
        return levelTwoMinutes;
    }
    public static long getLevelThreeMinutes()
    {
        return levelThreeMinutes;
    }
    public static long getLevelFourMinutes()
    {
        return levelFourMinutes;
    }
    public static long getLevelFiveMinutes()
    {
        return levelFiveMinutes;
    }

    public static long getLevelOneSeconds()
    {
        return levelOneSeconds;
    }
    public static long getLevelTwoSeconds()
    {
        return levelTwoSeconds;
    }
    public static long getLevelThreeSeconds()
    {
        return levelThreeSeconds;
    }
    public static long getLevelFourSeconds()
    {
        return levelFourSeconds;
    }
    public static long getLevelFiveSeconds()
    {
        return levelFiveSeconds;
    }
}