package breakout.gamescreens;

import java.awt.Graphics2D;

public abstract class GameScreen {
	ScreenManager screenManager;
	
	GameScreen(ScreenManager s) { this.screenManager = s; }
	void tick() { }
	void draw(Graphics2D g) { }
}
