package breakout.gamemodel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import breakout.gamemodel.hitboxes.Hitbox;
import breakout.gamemodel.hitboxes.RectangleHitbox;
import breakout.gamescreens.MainScreen;

public class Brick implements GameObject {

    private Color color;
    private RectangleHitbox h;
    private MainScreen ms;

    public Brick(MainScreen ms, Color c, int x, int y, int width, int height) {
        ms.registerGameObject(this);

        // bricks listen for collisions with the ball
        ArrayList<Class<? extends GameObject>> collisionClasses = new ArrayList<>();
        collisionClasses.add(Ball.class);
        ms.registerCollision(this, collisionClasses);
        this.ms = ms;
        this.color = c;
        this.h = new RectangleHitbox(x, y, width, height);
    }

    @Override
    public void tick() {
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect((int) h.x, (int) h.y, (int) h.width, (int) h.height);
    }

    @Override
    public Hitbox getHitbox() {
        return h;
    }

    @Override
    public void onCollide(GameObject o, Hitbox.CollisionType ctype) {
        // bricks delete themselves when hit by the ball
        ms.unregisterGameObject(this);
        ms.unregisterCollision(this);
    }

    public void unCollide() {
        ms.registerGameObject(this);
        ArrayList<Class<? extends GameObject>> collisionClasses = new ArrayList<>();
        collisionClasses.add(Ball.class);
        ms.registerCollision(this, collisionClasses);
    }

}
