package breakout.gamemodel.hitboxes;

public class CircleHitbox extends Hitbox {
	public float x, y, radius;
	
	public CircleHitbox(float x, float y, float radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	@Override
	public String toString() {
		return String.format("(%d, %d) r%d", (int)this.x, (int)this.y, (int)this.radius);
	}
}
