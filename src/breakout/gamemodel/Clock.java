package breakout.gamemodel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import breakout.C;
import breakout.gamemodel.hitboxes.Hitbox;
import breakout.gamemodel.hitboxes.NoHitbox;
import breakout.gamescreens.MainScreen;

public class Clock implements GameObject {
    public int seconds = 0, milliseconds = 0;

    public Clock(MainScreen ms) {
        ms.registerGameObject(this);
    }

    @Override
    public void tick() {
        if ((milliseconds += C.TIMER_PERIOD) > 1000) {
            seconds += 1;
            milliseconds -= 1000;
        }
    }

    public void unTick() {
        if (seconds > 0) {
            seconds -= 1;
            milliseconds += 1000;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.setFont(new Font("Monospace", 0, 24));
        g.drawString(Integer.toString(seconds), 20, 40);
    }

    @Override
    public Hitbox getHitbox() {
        return new NoHitbox();
    }

    @Override
    public void onCollide(GameObject o, Hitbox.CollisionType ctype) {
    }

}
