package breakout.command;

import breakout.gamemodel.Brick;
import breakout.gamemodel.GameObject;
import breakout.gamemodel.hitboxes.Hitbox;

public class BrickCollide implements Command{
    
    private Brick curBrick;
    private GameObject gameObj;
    private Hitbox.CollisionType cType;
    
    public BrickCollide(Brick brick, GameObject gameObj, Hitbox.CollisionType cType) {
        this.curBrick = brick;
        this.gameObj = gameObj;
        this.cType = cType;
    }

    @Override
    public void execute() {
        curBrick.onCollide(gameObj, cType);
        
    }

    @Override
    public void unexecute() {
        curBrick.unCollide();
    }

}
