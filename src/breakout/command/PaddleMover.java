package breakout.command;

import breakout.Input;
import breakout.gamemodel.Paddle;

public class PaddleMover implements Command{

    private Paddle paddle;
    private boolean upPressedHa;
    private boolean downPressedHa;
    
    public PaddleMover(Paddle curPaddle) {
        this.paddle = curPaddle;
        upPressedHa = Input.upPressed;
        this.downPressedHa = Input.downPressed;
    }
    
    @Override
    public void execute() {
        this.paddle.tickForCmd(upPressedHa, downPressedHa);
    }

    @Override
    public void unexecute() {
        this.paddle.unTick(upPressedHa, downPressedHa);
    }
    

}
