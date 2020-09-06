package breakout.command;

import breakout.gamemodel.Ball;
import breakout.gamemodel.GameObject;
import breakout.gamemodel.hitboxes.Hitbox;

public class BallCollide implements Command {
    
    private Ball curBall;
    private GameObject gameObj;
    private Hitbox.CollisionType cType;
    
    public BallCollide(Ball ball, GameObject gameObj, Hitbox.CollisionType cType) {
        this.curBall = ball;
        this.gameObj = gameObj;
        this.cType = cType;
    }

    @Override
    public void execute() {
        curBall.onCollide(gameObj, this.cType);
    }

    @Override
    public void unexecute() {
        curBall.onCollide(gameObj, this.cType);
    }

}
