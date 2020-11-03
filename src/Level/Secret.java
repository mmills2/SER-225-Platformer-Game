package Level;

import Engine.GraphicsHandler;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Secret {

    protected SpriteFont title;
    protected BufferedImage image;

    public Secret (String title){
        this.title = new SpriteFont(title, 500, 560, "Arial", 12, Color.BLACK);
        //this.image = image;
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangleWithBorder(500, 560, 100, 75, new Color(10f, 4f, 111f), Color.BLACK, 2);
        title.draw(graphicsHandler);
    }
}
