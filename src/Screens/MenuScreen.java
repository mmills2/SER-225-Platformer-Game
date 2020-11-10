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

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected SpriteFont playGame;
    protected SpriteFont credits;
    protected SpriteFont Tutorial;
    protected SpriteFont Secrets;
    protected SpriteFont scoreBoard;
    protected Map background;
    protected Player player;
    protected Stopwatch keyTimer = new Stopwatch();
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();

    public MenuScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        playGame = new SpriteFont("PLAY GAME", 200, 50, "Comic Sans", 30, new Color(49, 207, 240));
        playGame.setOutlineColor(Color.black);
        playGame.setOutlineThickness(3);
        Tutorial = new SpriteFont("TUTORIAL", 200, 250, "Comic Sans", 30, new Color(49, 207, 240));
        Tutorial.setOutlineColor(Color.black);
        Tutorial.setOutlineThickness(3);
        credits = new SpriteFont("CREDITS", 200, 150, "Comic Sans", 30, new Color(49, 207, 240));
        credits.setOutlineColor(Color.black);
        credits.setOutlineThickness(3);
        Secrets = new SpriteFont("SECRETS", 576, 150, "Comic Sans", 30, new Color(49, 207, 240));
        Secrets.setOutlineColor(Color.black);
        Secrets.setOutlineThickness(3);
        scoreBoard = new SpriteFont("HIGH SCORES", 200, 350, "Comic Sans", 30, new Color(49, 207, 240));
        scoreBoard.setOutlineColor(Color.black);
        scoreBoard.setOutlineThickness(3);
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        keyTimer.setWaitTime(200);
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);
        this.player = new Cat(background.getPlayerStartPosition().x, background.getPlayerStartPosition().y);
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(player);

        // if down or up is pressed, change menu item "hovered" over (blue square in front of text will move along with currentMenuItemHovered changing)
        if (Keyboard.isKeyDown(Key.DOWN) && keyTimer.isTimeUp() && currentMenuItemHovered != 4) {
            keyTimer.reset();
            if(currentMenuItemHovered == 3){
                currentMenuItemHovered = 0;
            }
            else {
                currentMenuItemHovered++;
            }
        } else if (Keyboard.isKeyDown(Key.UP) && keyTimer.isTimeUp() && currentMenuItemHovered != 4) {
            keyTimer.reset();
            if(currentMenuItemHovered == 0){
                currentMenuItemHovered = 3;
            }
            else {
                currentMenuItemHovered--;
            }
        }
        else if(Keyboard.isKeyDown(Key.RIGHT) && keyTimer.isTimeUp() && (currentMenuItemHovered == 1 || currentMenuItemHovered == 4) && Screen.anySecretFound){
            keyTimer.reset();
            if(currentMenuItemHovered == 1){ currentMenuItemHovered = 4;}
            else{currentMenuItemHovered = 1;}
        }
        else if(Keyboard.isKeyDown(Key.LEFT) && keyTimer.isTimeUp() && (currentMenuItemHovered == 1 || currentMenuItemHovered == 4) && Screen.anySecretFound){
            keyTimer.reset();
            if(currentMenuItemHovered == 4){ currentMenuItemHovered = 1;}
            else{currentMenuItemHovered = 4;}
        }

        // sets location for blue square in front of text (pointerLocation) and also sets color of spritefont text based on which menu item is being hovered
        if (currentMenuItemHovered == 0) {
            playGame.setColor(new Color(255, 215, 0));
            credits.setColor(new Color(49, 207, 240));
            Tutorial.setColor(new Color(49, 207, 240));
            Secrets.setColor(new Color(49, 207, 240));
            scoreBoard.setColor((new Color(49,207, 240)));
            pointerLocationX = 170;
            pointerLocationY = 30;
        } else if (currentMenuItemHovered == 1) {
            playGame.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(255, 215, 0));
            Tutorial.setColor(new Color(49, 207, 240));
            Secrets.setColor(new Color(49, 207, 240));
            scoreBoard.setColor((new Color(49,207, 240)));
            pointerLocationX = 170;
            pointerLocationY = 130;
        } else if (currentMenuItemHovered == 2) {
            playGame.setColor(new Color(49, 207, 240));
            Tutorial.setColor(new Color(255, 215, 0));
            credits.setColor(new Color(49, 207, 240));
            Secrets.setColor(new Color(49, 207, 240));
            scoreBoard.setColor((new Color(49,207, 240)));
            pointerLocationX = 170;
            pointerLocationY = 230;
        }
        else if (currentMenuItemHovered == 3){
            playGame.setColor(new Color(49, 207, 240));
            Tutorial.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(49, 207, 240));
            scoreBoard.setColor(new Color(255, 215, 0));
            Secrets.setColor((new Color(49,207, 240)));
            pointerLocationX = 170;
            pointerLocationY = 330;
        }
        else if (currentMenuItemHovered == 4){
            playGame.setColor(new Color(49, 207, 240));
            Tutorial.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(49, 207, 240));
            scoreBoard.setColor(new Color(49, 207, 240));
            Secrets.setColor((new Color(255, 215, 0)));
            pointerLocationX = 546;
            pointerLocationY = 130;
        }

        // if space is pressed on menu item, change to appropriate screen based on which menu item was chosen
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            menuItemSelected = currentMenuItemHovered;
            if (menuItemSelected == 0) {
                screenCoordinator.setGameState(GameState.LEVEL);
            } else if (menuItemSelected == 1) {
                screenCoordinator.setGameState(GameState.CREDITS);
            } else if (menuItemSelected == 2) {
                screenCoordinator.setGameState(GameState.TUTORIAL);
            }
            else if(menuItemSelected == 3){
                screenCoordinator.setGameState(GameState.SCOREBOARD);
            }
            else if(menuItemSelected == 4){
                screenCoordinator.setGameState(GameState.SECRETS);
            }
        }


    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        playGame.draw(graphicsHandler);
        credits.draw(graphicsHandler);
        Tutorial.draw(graphicsHandler);
        scoreBoard.draw(graphicsHandler);
        if(Screen.anySecretFound){ Secrets.draw(graphicsHandler);}
        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, new Color(49, 207, 240), Color.black, 2);
    }

    public int getMenuItemSelected() {
        return menuItemSelected;
    }

}
