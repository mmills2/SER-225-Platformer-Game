package Engine;

// Base Screen class
// This game engine runs off the idea of "screens", which are classes that contain their own update/draw methods for a particular piece of the game
// For example, there may be a "MenuScreen" or a "PlayGameScreen"
public abstract class Screen {
    public abstract void initialize();
    public abstract void update();
    public abstract void draw(GraphicsHandler graphicsHandler);
    public static boolean anySecretFound = false;
    public static boolean creditSecretStatus = false;
    public static boolean level2SecretStatus = false;

    public static boolean getSecretStatus(int id){
        switch(id){
            case(0):
                return creditSecretStatus;
            case(1):
                return level2SecretStatus;
            default:
                return true;
        }
    }

    public static void setSecretStatus(int id, boolean status){
        switch(id){
            case(0):
                creditSecretStatus = status;
                break;
            case(1):
                level2SecretStatus = status;
                break;
            default:
                break;
        }
    }
}
