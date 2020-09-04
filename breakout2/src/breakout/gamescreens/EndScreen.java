package breakout.gamescreens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import breakout.Input;

public class EndScreen extends GameScreen {
	private boolean won;
	private int time;
	
	EndScreen(ScreenManager s, boolean won, int time) {
		super(s);
		this.won = won;
		this.time = time;
	}

	@Override
	public void tick() {
		if (Input.spacePressed || (Input.spacePressed = false))
			this.screenManager.switchScreen(new MainScreen(this.screenManager));
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospace", 0, 30));
		g.drawString("You " + (won ? "won!" : "lost :("), 50, 100);
		if (won) g.drawString("You took " + time + " seconds to win.", 50, 150);
		else g.drawString("You survived for " + time + " seconds.", 50, 150);
		g.drawString("Press space to restart", 50, 200);
	}
}
