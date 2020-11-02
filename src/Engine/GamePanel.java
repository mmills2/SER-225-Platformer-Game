package Engine;

import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Rectangle;
import GameObject.Sprite;
import Screens.PlayLevelScreen;
import SpriteFont.SpriteFont;
import Utils.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This is where the game loop starts
 * The JPanel uses a timer to continually call cycles of update and draw
 */
public class GamePanel extends JPanel {
	// loads Screens on to the JPanel
	// each screen has its own update and draw methods defined to handle a "section" of the game.
	private ScreenManager screenManager;

	// used to create the game loop and cycle between update and draw calls
	private Timer timer;

	// used to draw graphics to the panel
	private GraphicsHandler graphicsHandler;

	private boolean doPaint = false;
	private boolean isGamePaused = false;
	private SpriteFont pauseLabel;
	private KeyLocker keyLocker = new KeyLocker();
	private final Key pauseKey = Key.P;

	protected SpriteFont timerFont;
	protected static long startTime;
	protected static int minutesPassed = 0;
	protected long timePassed ;
	protected long secondsPassed;
	protected static long pauseTime;
	protected long pauseKeyTime;
	protected boolean enteringPause;
	boolean startLimiter;
	/*
	 * The JPanel and various important class instances are setup here
	 */
	public GamePanel() {
		super();
		this.setDoubleBuffered(true);

		// attaches Keyboard class's keyListener to this JPanel
		this.addKeyListener(Keyboard.getKeyListener());

		graphicsHandler = new GraphicsHandler();

		screenManager = new ScreenManager();

		pauseLabel = new SpriteFont("PAUSE", 365, 280, "Comic Sans", 24, Color.white);
		pauseLabel.setOutlineColor(Color.black);
		pauseLabel.setOutlineThickness(2.0f);

		timerFont = new SpriteFont("", 650, 20, "Comic Sans", 24, Color.white);
		timerFont.setOutlineColor(Color.black);
		timerFont.setOutlineThickness(2.0f);

		// Every timer "tick" will call the update method as well as tell the JPanel to repaint
		// Remember that repaint "schedules" a paint rather than carries it out immediately
		// If the game is really laggy/slow, I would consider upping the FPS in the Config file.
		timer = new Timer(1000 / Config.FPS, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				repaint();
			}
		});
		timer.setRepeats(true);
		startTime = System.currentTimeMillis();
		startLimiter = false;
		enteringPause = true;
	}

	// this is called later after instantiation, and will initialize screenManager
	// this had to be done outside of the constructor because it needed to know the JPanel's width and height, which aren't available in the constructor
	public void setupGame() {
		setBackground(Colors.CORNFLOWER_BLUE);
		screenManager.initialize(new Rectangle(getX(), getY(), getWidth(), getHeight()));
		doPaint = true;
	}

	// this starts the timer (the game loop is started here
	public void startGame() {
		timer.start();
	}

	public ScreenManager getScreenManager() {
		return screenManager;
	}

	public void update() {

		if(PlayLevelScreen.isRunning()) {
			if (Keyboard.isKeyDown(pauseKey) && !keyLocker.isKeyLocked(pauseKey)) {
				isGamePaused = !isGamePaused;
				keyLocker.lockKey(pauseKey);
				if(enteringPause) {
					pauseKeyTime = System.currentTimeMillis();
					enteringPause = false;
				}
			}

			if (Keyboard.isKeyUp(pauseKey)) {
				keyLocker.unlockKey(pauseKey);
			}
		}

		if (!isGamePaused) {
			if(!enteringPause) {
				pauseTime += System.currentTimeMillis() - pauseKeyTime;
				pauseKeyTime = 0;
				enteringPause = true;
			}
			screenManager.update();
		}

		if(PlayLevelScreen.isRunning() && !isGamePaused)
		{
			if(!startLimiter)
			{
				startTime = System.currentTimeMillis();
				startLimiter = true;
			}
			timePassed = System.currentTimeMillis() - startTime - pauseTime;
			secondsPassed = timePassed/1000;
			if(secondsPassed == 60)
			{
				secondsPassed = 0;
				startTime = System.currentTimeMillis();
				minutesPassed++;
			}
			if(secondsPassed < 10)
			{
				timerFont = new SpriteFont("Timer: " + minutesPassed + ":0" + secondsPassed, 650, 20, "Comic Sans", 24, Color.white);
			}
			else
			{
				timerFont = new SpriteFont("Timer: " + minutesPassed + ":" + secondsPassed, 650, 20, "Comic Sans", 24, Color.white);
			}
			timerFont.setOutlineColor(Color.black);
			timerFont.setOutlineThickness(2.0f);
		}
	}

	public void draw() {
		screenManager.draw(graphicsHandler);

		// if game is paused, draw pause gfx over Screen gfx
		if (isGamePaused) {
			pauseLabel.draw(graphicsHandler);
			graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), new Color(0, 0, 0, 100));
		}
		if(ScreenCoordinator.getGameState() == GameState.LEVEL) {
			timerFont.draw(graphicsHandler);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// every repaint call will schedule this method to be called
		// when called, it will setup the graphics handler and then call this class's draw method
		graphicsHandler.setGraphics((Graphics2D) g);
		if (doPaint) {
			draw();
		}
	}

	public static void resetTimer(){
		startTime = System.currentTimeMillis();
		minutesPassed = 0;
		pauseTime = 0;
	}

	public static void pauseTimer(){
		PlayLevelScreen.setRunning(false);
	}
}
