package breakout.gamemodel;

import java.awt.Graphics2D;

import breakout.C;
import breakout.Input;
import breakout.gamemodel.hitboxes.Hitbox;
import breakout.gamemodel.hitboxes.RectangleHitbox;
import breakout.gamescreens.MainScreen;

public class Paddle implements GameObject {
    private RectangleHitbox h = new RectangleHitbox(C.PADDLE_X, C.HEIGHT / 2 - C.PADDLE_HEIGHT, C.PADDLE_WIDTH,
            C.PADDLE_HEIGHT);

    public Paddle(MainScreen ms) {
        ms.registerGameObject(this);
    }

    @Override
    public void tick() {
        // move C.PADDLE_SPEED units per tick, but in increments of 1 pixel until the
        // wall is hit
        int i = 0;
        if (Input.upPressed)
            while (h.y > C.WALL_WIDTH && ++i < C.PADDLE_SPEED)
                --h.y;
        else if (Input.downPressed)
            while (h.y < C.HEIGHT - C.PADDLE_HEIGHT - C.WALL_WIDTH - 1 && ++i < C.PADDLE_SPEED)
                ++h.y;
    }
    
    public void tickForCmd(boolean upPressedHa, boolean downPressedHa) {
        int i = 0;
        if (upPressedHa) {
            while (h.y > C.WALL_WIDTH && ++i < C.PADDLE_SPEED) {
                --h.y;
            }
        } else if(downPressedHa){
            while (h.y < C.HEIGHT - C.PADDLE_HEIGHT - C.WALL_WIDTH - 1 && ++i < C.PADDLE_SPEED) {
                ++h.y;
            }
        }
    }

    public void unTick(boolean upPressedHa, boolean downPressedHa) {
        int i = 0;
        if (upPressedHa) {
            while (h.y < C.HEIGHT - C.PADDLE_HEIGHT - C.WALL_WIDTH - 1 && ++i < C.PADDLE_SPEED) {
                ++h.y;
            }
        } else if(downPressedHa){
            while (h.y > C.WALL_WIDTH && ++i < C.PADDLE_SPEED) {
                --h.y;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(C.PADDLE_COLOR);
        g.fillRect(C.PADDLE_X, (int) h.y, C.PADDLE_WIDTH, C.PADDLE_HEIGHT);
        g.setColor(C.PADDLE_BORDER_COLOR);
        g.drawRect(C.PADDLE_X, (int) h.y, C.PADDLE_WIDTH, C.PADDLE_HEIGHT);
    }

    @Override
    public Hitbox getHitbox() {
        return h;
    }

    @Override
    public void onCollide(GameObject o, Hitbox.CollisionType ctype) {
    }

}
