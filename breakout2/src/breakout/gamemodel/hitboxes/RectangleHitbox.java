package breakout.gamemodel.hitboxes;

public class RectangleHitbox extends Hitbox {
	public float x, y, width, height;
	
	public RectangleHitbox(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	@Override
	public String toString() {
		return String.format("[%d, %d, %d, %d]", (int)this.x, (int)this.y, (int)this.width, (int)this.height);
	}
}
