package breakout.command;

import breakout.gamemodel.Clock;

public class TimerTick implements Command{

    private Clock clock;
    
    public TimerTick(Clock curClock) {
        this.clock = curClock;
    }
    
    @Override
    public void execute() {
        this.clock.tick();
        // TODO Auto-generated method stub
        
    }

    @Override
    public void unexecute() {
        this.clock.unTick();
        // TODO Auto-generated method stub
        
    }
    
    

}
