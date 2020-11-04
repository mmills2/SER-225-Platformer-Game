package Level;

import Engine.GraphicsHandler;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Secret {

    protected SpriteFont title;
    protected BufferedImage image;
    protected int id;

    public Secret (String title, BufferedImage image, int id){
        this.title = new SpriteFont(title, 690, 535, "Arial", 12, Color.WHITE);
        this.image = image;
        this.id = id;
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangleWithBorder(610, 490, 175, 76, new Color(0.02f, 0.01f, 0.31f), Color.BLACK, 2);
        graphicsHandler.drawImage(image, 623, 503, 50, 50);
        title.draw(graphicsHandler);
    }

    public void printSecret(){
        System.out.println(title.getText());
    }
}
