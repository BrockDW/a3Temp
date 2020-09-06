package breakout.command;

import breakout.gamemodel.Ball;

public class BallMover implements Command {
    
    private Ball curBall;
    
    public BallMover(Ball ball) {
        curBall = ball;
    }

    @Override
    public void execute() {
        curBall.tick();
    }

    @Override
    public void unexecute() {
        curBall.unTick();
    }
    

}
