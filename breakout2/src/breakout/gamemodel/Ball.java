package breakout.gamemodel;

import java.awt.Graphics2D;
import java.util.ArrayList;

import breakout.C;
import breakout.gamemodel.hitboxes.Hitbox;
import breakout.gamemodel.hitboxes.CircleHitbox;
import breakout.gamescreens.MainScreen;

public class Ball implements GameObject {
	private float dx = 2;
	private float dy = 2;
	private CircleHitbox h = new CircleHitbox(C.BALL_RADIUS + 70, C.BALL_RADIUS + 250, C.BALL_RADIUS);
	
	public Ball(MainScreen ms) {
		ms.registerGameObject(this);
		
		// the ball listens for collisions with bricks, the paddle, and walls
		ArrayList<Class<? extends GameObject>> collisionClasses = new ArrayList<>();
		collisionClasses.add(Brick.class);
		collisionClasses.add(Paddle.class);
		collisionClasses.add(Border.class);
		ms.registerCollision(this, collisionClasses);
	}
	
	@Override
	public void tick() {
		h.x += dx;
		h.y += dy;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(C.BALL_COLOR);
		g.fillOval((int) (h.x - C.BALL_RADIUS), (int) (h.y - C.BALL_RADIUS),
				(int)( 2 * C.BALL_RADIUS), (int)( 2 * C.BALL_RADIUS));
	}

	@Override
	public Hitbox getHitbox() { return h; }

	@Override
	public void onCollide(GameObject o, Hitbox.CollisionType ctype) {
		switch (ctype) {
			case LEFT:
			case RIGHT:
				dx *= -1;
				h.x += dx;
				break;
			case TOP:
			case BOTTOM:
				dy *= -1;
				h.y += dy;
				break;
		}
	}
}
