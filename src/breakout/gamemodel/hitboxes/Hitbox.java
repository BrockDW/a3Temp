package breakout.gamemodel.hitboxes;

public abstract class Hitbox {
	// instead of returning boolean yes/no for collided, additionally return some extra information when possible
	// in this case, which side of a rectangle the ball collided on
	public enum CollisionType { NONE, YES, LEFT, RIGHT, TOP, BOTTOM };
	
	public static final CollisionType collide(Hitbox a, Hitbox b) {
		if (a instanceof NoHitbox || b instanceof NoHitbox)
			return CollisionType.NONE;
		
		if (a instanceof CircleHitbox && b instanceof CircleHitbox) {
			return CollisionType.NONE; // unimplemented; not needed in this game
		}
		
		if (a instanceof RectangleHitbox && b instanceof RectangleHitbox) {
			RectangleHitbox c = (RectangleHitbox) a;
			RectangleHitbox d = (RectangleHitbox) b;
			float cl = c.x, cr = c.x + c.width, ct = c.y, cb = c.y + c.height;
			float dl = d.x, dr = d.x + d.width, dt = d.y, db = d.y + d.height;
			if (cl < dr && cr > dl && ct < db && cb > dt) return CollisionType.YES;
		}
		
		if (a instanceof RectangleHitbox && b instanceof CircleHitbox)
			return collide(b,a);
		if (a instanceof CircleHitbox && b instanceof RectangleHitbox) {
			// this algorithm won't work for fast moving objects, but is good enough for this game
			CircleHitbox c = (CircleHitbox) a;
			RectangleHitbox d = (RectangleHitbox) b;
			float cl = c.x - c.radius, cr = c.x + c.radius, ct = c.y - c.radius, cb = c.y + c.radius, rad = c.radius;
			float dl = d.x, dr = d.x + d.width, dt = d.y, db = d.y + d.height;
			//if (cr > dl && cb > dt && c.x < dl && c.y < dt) return CollisionType.LEFTTOP;
			//if (cl < dr && cb > dt && c.x > dr && c.y < dt) return CollisionType.RIGHTTOP;
			//if (cl < dr && ct < db && c.x > dr && c.y > db) return CollisionType.RIGHTBOTTOM;
			//if (cr > dl && ct < db && c.x < dl && c.y > db) return CollisionType.LEFTBOTTOM;
			if (cr > dl && c.x < dl && c.x >= dl - rad && c.y > dt && c.y < db) return CollisionType.LEFT;
			if (cl < dr && c.x > dr && c.x <= dr + rad && c.y > dt && c.y < db) return CollisionType.RIGHT;
			if (cb > dt && c.y < dt && c.y >= dt - rad && c.x < dr && c.x > dl) return CollisionType.TOP;
			if (ct < db && c.y > db && c.y <= db + rad && c.x < dr && c.x > dl) return CollisionType.BOTTOM;
		}
		
		return CollisionType.NONE;
	}
}
