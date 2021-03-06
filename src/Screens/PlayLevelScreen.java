package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Level.Secret;
import Maps.Level2;
import Maps.Level3;
import Maps.Level4;
import Maps.Level5;
import Maps.TestMap;
import Players.Cat;
import Utils.Stopwatch;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen implements PlayerListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected Stopwatch screenTimer = new Stopwatch();
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected int curMap;
    public static boolean running = false;
    protected Secret level2Secret;
    protected static SoundsHandler themeSound;
    protected static int lives = 3;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        this.curMap = 0;
    }

    public void initialize() {
        level2Secret = new Secret("Hidden Grove", ImageLoader.load("Level2Secret.png"), 1);
        // define/setup map
        switch(curMap) {
            case (0):
                this.map = new TestMap();
                break;
            case (1):
                this.map = new Level2(level2Secret);
                break;
            case(2):
                this.map= new Level3();
                break;
            case(3):
                this.map= new Level4();
                break;
            case(4):
                this.map= new Level5();
                break;
            default:
                goBackToMenu();
        }
        map.reset();
        GamePanel.resetTimer();
        // setup player
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        this.player.addListener(this);
        this.player.setLocation(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
        themeSound = new SoundsHandler("theme");
        themeSound.startSound(100);
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {

            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                running = true;
                player.update();
                map.update(player);
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                running = false;
                levelClearedScreen = new LevelClearedScreen();
                levelClearedScreen.initialize();
                screenTimer.setWaitTime(2500);
                playLevelScreenState = PlayLevelScreenState.LEVEL_WIN_MESSAGE;
                if(curMap == 0)
                {
                    if((HighScoreScreen.getLevelOneMinutes() * 60) + HighScoreScreen.getLevelOneSeconds() > (GamePanel.getMinutesPassed() * 60) + GamePanel.getSecondsPassed() || (HighScoreScreen.getLevelOneMinutes() * 60) + HighScoreScreen.getLevelOneSeconds() == 0)
                    {
                        HighScoreScreen.setLevelOneMinutes(GamePanel.getMinutesPassed());
                        HighScoreScreen.setLevelOneSeconds(GamePanel.getSecondsPassed());
                    }
                }
                if(curMap == 1)
                {
                    if((HighScoreScreen.getLevelTwoMinutes() * 60) + HighScoreScreen.getLevelTwoSeconds() > (GamePanel.getMinutesPassed() * 60) + GamePanel.getSecondsPassed() || (HighScoreScreen.getLevelTwoMinutes() * 60) + HighScoreScreen.getLevelTwoSeconds() == 0)
                    {
                        HighScoreScreen.setLevelTwoMinutes(GamePanel.getMinutesPassed());
                        HighScoreScreen.setLevelTwoSeconds(GamePanel.getSecondsPassed());
                    }
                }
                if(curMap == 2)
                {
                    if((HighScoreScreen.getLevelThreeMinutes() * 60) + HighScoreScreen.getLevelThreeSeconds() > (GamePanel.getMinutesPassed() * 60) + GamePanel.getSecondsPassed() || (HighScoreScreen.getLevelThreeMinutes() * 60) + HighScoreScreen.getLevelThreeSeconds() == 0)
                    {
                        HighScoreScreen.setLevelThreeMinutes(GamePanel.getMinutesPassed());
                        HighScoreScreen.setLevelThreeSeconds(GamePanel.getSecondsPassed());
                    }
                }
                if(curMap == 3)
                {
                    if((HighScoreScreen.getLevelFourMinutes() * 60) + HighScoreScreen.getLevelFourSeconds() > (GamePanel.getMinutesPassed() * 60) + GamePanel.getSecondsPassed() || (HighScoreScreen.getLevelFourMinutes() * 60) + HighScoreScreen.getLevelFourSeconds() == 0)
                    {
                        HighScoreScreen.setLevelFourMinutes(GamePanel.getMinutesPassed());
                        HighScoreScreen.setLevelFourSeconds(GamePanel.getSecondsPassed());
                    }
                }
                if(curMap == 4)
                {
                    if((HighScoreScreen.getLevelFiveMinutes() * 60) + HighScoreScreen.getLevelFiveSeconds() > (GamePanel.getMinutesPassed() * 60) + GamePanel.getSecondsPassed() || (HighScoreScreen.getLevelFiveMinutes() * 60) + HighScoreScreen.getLevelFiveSeconds() == 0)
                    {
                        HighScoreScreen.setLevelFiveMinutes(GamePanel.getMinutesPassed());
                        HighScoreScreen.setLevelFiveSeconds(GamePanel.getSecondsPassed());
                    }
                }
                break;
            // if level cleared screen is up and the timer is up for how long it should stay out, go back to main menu
            case LEVEL_WIN_MESSAGE:
                running = false;
                if (screenTimer.isTimeUp()) {
                    levelClearedScreen = null;
                    goToNextLevel();
                }
                break;
            // if player died in level, bring up level lost screen
            case PLAYER_DEAD:
                running = false;
                levelLoseScreen = new LevelLoseScreen(this);
                levelLoseScreen.initialize();
                playLevelScreenState = PlayLevelScreenState.LEVEL_LOSE_MESSAGE;
                break;
            // wait on level lose screen to make a decision (either resets level or sends player back to main menu)
            case LEVEL_LOSE_MESSAGE:
                running = false;
                levelLoseScreen.update();
                break;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
            case LEVEL_COMPLETED:
            case PLAYER_DEAD:
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);
                break;
            case LEVEL_WIN_MESSAGE:
                levelClearedScreen.draw(graphicsHandler);
                break;
            case LEVEL_LOSE_MESSAGE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    @Override
    public void onLevelCompleted() {
        playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
    }

    @Override
    public void onDeath() {
        if(lives == 1) {
            playLevelScreenState = PlayLevelScreenState.PLAYER_DEAD;
        }
        else
        {
            resetLevel();
        }
        lives--;
    }

    public void resetLevel() {
        themeSound.stopSound();
        initialize();
    }

    public void goBackToMenu() {
        themeSound.stopSound();
        screenCoordinator.setGameState(GameState.MENU);
    }

    public void goToNextLevel(){
        curMap++;
        themeSound.stopSound();
        initialize();
    }

    public void restartGame(){
        curMap = 0;
        themeSound.stopSound();
        initialize();
    }

    public static void stopMusic(){
        themeSound.stopSound();
    }


    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED, PLAYER_DEAD, LEVEL_WIN_MESSAGE, LEVEL_LOSE_MESSAGE
    }

    public static boolean isRunning()
    {
        return running;
    }

    public static void setRunning(boolean set)
    {
        running = set;
    }

    public static int getLives()
    {
        return lives;
    }

    public static void setLives(int newLife)
    {
        lives = newLife;
    }
}
