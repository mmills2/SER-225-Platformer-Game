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
public class SecretsScreen extends Screen{
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected KeyLocker keyLocker = new KeyLocker();
    protected Player player;
    protected String creditScreenSecret;
    protected SpriteFont returnInstructionsLabel;

    public SecretsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        creditScreenSecret = "LockedSecret.png";
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        returnInstructionsLabel = new SpriteFont("Press Esc to return to the menu", 20, 560, "Times New Roman", 30, Color.white);
        this.player = new Cat(background.getPlayerStartPosition().x, background.getPlayerStartPosition().y);
    }

    @Override
    public void update() {
        background.update(player);
        if(Screen.creditSecretStatus){creditScreenSecret = "CreditScreenSecret.png";}

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
        graphicsHandler.drawFilledRectangleWithBorder(150, 150, 100, 100, Color.BLACK, Color.DARK_GRAY, 5);
        graphicsHandler.drawImage(ImageLoader.load(creditScreenSecret), 153, 153, 95, 95);
    }
}