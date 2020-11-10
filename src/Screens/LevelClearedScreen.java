package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Engine.ScreenManager;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the level cleared screen
public class LevelClearedScreen extends Screen {
    protected SpriteFont winMessage;

    public static boolean isCleared() {
        return cleared;
    }

    public static void setCleared(boolean cleared) {
        LevelClearedScreen.cleared = cleared;
    }

    protected static boolean cleared=false;
    public LevelClearedScreen() {
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("Level Cleared", 320, 270, "Comic Sans", 30, Color.white);
        cleared=true;
    }

    @Override
    public void update() {

    }

    public void draw(GraphicsHandler graphicsHandler) {
        // paint entire screen black and dislpay level cleared text
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        winMessage.draw(graphicsHandler);
    }
}
