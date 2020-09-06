package breakout.gamemodel;

import java.awt.Graphics2D;
import java.util.ArrayList;

import breakout.C;
import breakout.gamemodel.hitboxes.Hitbox;
import breakout.gamemodel.hitboxes.Hitbox.CollisionType;
import breakout.gamemodel.hitboxes.RectangleHitbox;
import breakout.gamescreens.MainScreen;

public class Border implements GameObject {
	private MainScreen ms;
	private boolean win, lose, visible;
	private RectangleHitbox h;
	
	public Border(MainScreen ms, int x, int y, int w, int h, boolean win, boolean lose, boolean visible) {
		this.ms = ms;
		this.win = win;
		this.lose = lose;
		this.visible = visible;
		this.h = new RectangleHitbox(x, y, w, h);

		ms.registerGameObject(this);
		if (win || lose) { // no handling required here for top/bottom walls, so don't register
			ArrayList<Class<? extends GameObject>> collisionClasses = new ArrayList<>();
			collisionClasses.add(Ball.class);
			ms.registerCollision(this, collisionClasses);
		}
	}

	@Override
	public void tick() { }
	
	@Override
	public void draw(Graphics2D g) {
		if (!visible) return;
		g.setColor(C.WALL_COLOR);
		g.fillRect((int)h.x, (int)h.y, (int)h.width, (int)h.height);
	}

	@Override
	public Hitbox getHitbox() { return h; }

	@Override
	public void onCollide(GameObject o, Hitbox.CollisionType ctype) {
		if (win) ms.winGame();
		else if (lose) ms.loseGame();
	}

    
}
