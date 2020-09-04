package breakout.gamemodel;

import java.awt.Graphics2D;

import breakout.gamemodel.hitboxes.Hitbox;

public interface GameObject {
	public void tick();
	public void draw(Graphics2D g);
	public void onCollide(GameObject o, Hitbox.CollisionType ctype);
	public Hitbox getHitbox();
}
