package breakout;

import java.awt.Color;
import java.util.Random;

public class C { // Constants
	public static final Random R = new Random();
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final int TIMER_PERIOD = 1000/60; // approx 60 fps
	
	public static final int NUM_BRICKS = 15;
	public static final int BRICK_MIN_X = 200;
	public static final int BRICK_WIDTH = 20;
	public static final int BRICK_HEIGHT = 40;

	public static final int BALL_RADIUS = 10;
	public static final Color BALL_COLOR = new Color(255, 200, 200);
	
	public static final int PADDLE_X = 20;
	public static final int PADDLE_WIDTH = 10;
	public static final int PADDLE_HEIGHT = 70;
	public static final int PADDLE_SPEED = 10;
	public static final Color PADDLE_COLOR = new Color(255, 50, 50);
	public static final Color PADDLE_BORDER_COLOR = Color.WHITE;

	public static final int WALL_WIDTH = 5;
	public static final Color WALL_COLOR = Color.WHITE;
}
