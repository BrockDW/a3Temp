package breakout.gamescreens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import breakout.Input;

public class StartScreen extends GameScreen {
	
	StartScreen(ScreenManager s) { super(s); }

	@Override
	public void tick() {
		if (Input.spacePressed || (Input.spacePressed = false))
			this.screenManager.switchScreen(new MainScreen(this.screenManager));
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospace", 0, 30));
		g.drawString("Breakout", 50, 100);
		g.drawString("Press space to begin", 50, 150);
		g.drawString("Escape through the", 50, 250);
		g.drawString("   right side to win", 50, 285);
	}
}
