package Screens;

import java.awt.Color;

import Engine.GraphicsHandler;

import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.TestMap;
import Maps.TutorialMap;
import Players.Cat;
import SpriteFont.SpriteFont;
import Utils.Stopwatch;

// This class is for when the platformer game is actually being played
public class TutorialScreen extends Screen implements PlayerListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState tutorialLevelScreenState;
    protected Stopwatch screenTimer = new Stopwatch();
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected SpriteFont Tutorial;
    private static boolean Running=false;

    public TutorialScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // define/setup map
        this.map = new TutorialMap();
        
        map.reset();
        

        // setup player
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        this.player.addListener(this);
        this.player.setLocation(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.tutorialLevelScreenState = PlayLevelScreenState.RUNNING;
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (tutorialLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
// so player can pause when plying level only
            	setRunning(true);
            	//System.out.println(Running);
                player.update();
                map.update(player);
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
            	//so the player cannot pause on a completed screen
            	setRunning(false);
            	
                levelClearedScreen = new LevelClearedScreen();
                levelClearedScreen.initialize();
                screenTimer.setWaitTime(2500);
                tutorialLevelScreenState = PlayLevelScreenState.LEVEL_WIN_MESSAGE;
                break;
            // if level cleared screen is up and the timer is up for how long it should stay out, go back to main menu
            case LEVEL_WIN_MESSAGE:
            	//so the player cannot pause on a win screen
            	setRunning(false);
                if (screenTimer.isTimeUp()) {
                    levelClearedScreen = null;
                    goBackToMenu();
                }
                break;
            // if player died in level, bring up level lost screen
            case PLAYER_DEAD:
            	setRunning(false);
            	//so the player cannot pause on a lose screen
                goBackToMenu();
                tutorialLevelScreenState = PlayLevelScreenState.LEVEL_LOSE_MESSAGE;
                break;
            // wait on level lose screen to make a decision (either resets level or sends player back to main menu)
            case LEVEL_LOSE_MESSAGE:
            	setRunning(false);
                levelLoseScreen.update();
                break;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (tutorialLevelScreenState) {
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
        return tutorialLevelScreenState;
    }

    @Override
    public void onLevelCompleted() {
        tutorialLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
    }

    @Override
    public void onDeath() {
        tutorialLevelScreenState = PlayLevelScreenState.PLAYER_DEAD;
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

  

	public static void setRunning(boolean running) {
		TutorialScreen.Running = running;
	}

	// This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED, PLAYER_DEAD, LEVEL_WIN_MESSAGE, LEVEL_LOSE_MESSAGE
    }

	public static boolean isRuning() {
		// TODO Auto-generated method stub
		return Running;
	}
}
