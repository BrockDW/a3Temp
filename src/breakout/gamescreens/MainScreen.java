package breakout.gamescreens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import javax.swing.Timer;

import breakout.C;
import breakout.Input;
import breakout.Pair;
import breakout.command.BallCollide;
import breakout.command.BallMover;
import breakout.command.BrickCollide;
import breakout.command.Command;
import breakout.command.PaddleMover;
import breakout.command.TimerTick;
import breakout.gamemodel.*;
import breakout.gamemodel.hitboxes.*;

public class MainScreen extends GameScreen {
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private ArrayList<Brick> bricks = new ArrayList<>();
    private Stack<Command> commandStack = new Stack<Command>();
    private Stack<Command> commandReplayStack = new Stack<Command>();
    private Clock clock;

    private HashMap<GameObject, List<Class<? extends GameObject>>> collisionsMap = new HashMap<>();

    public MainScreen(ScreenManager s) {
        super(s);

        this.clock = new Clock(this);
        new Ball(this);
        new Paddle(this);
        createBricks();

        new Border(this, 0, 0, C.WIDTH, C.WALL_WIDTH, false, false, true); // top
        new Border(this, 0, C.HEIGHT - C.WALL_WIDTH, C.WIDTH, C.WALL_WIDTH, false, false, true); // bottom
        new Border(this, 0, 0, C.WALL_WIDTH, C.HEIGHT, false, true, false); // left
        new Border(this, C.WIDTH - C.WALL_WIDTH, 0, C.WALL_WIDTH, C.HEIGHT, true, false, false); // right
    }

    public void unDoCommand(int undoPeriod) {
        while (undoPeriod != 0) {
            if (this.commandStack.size() == 0) {
                break;
            }
            Command curCommand = this.commandStack.pop();
            curCommand.unexecute();

            // System.out.println("it works here in overall");
            if (curCommand instanceof TimerTick) {
                // System.out.println("it works here in timer");
                undoPeriod--;

            }

        }
    }

    public void replayGame() {
        while (this.commandStack.size() != 0) {
            Command curCommand = this.commandStack.pop();
            curCommand.unexecute();
        }
    }

    public void registerGameObject(GameObject o) {
        gameObjects.add(o);
    }

    public void unregisterGameObject(GameObject o) { // used when ball destroys bricks
        gameObjects.remove(o);
    }

    public void registerCollision(GameObject o, List<Class<? extends GameObject>> c) {
        collisionsMap.put(o, c);
    }

    public void unregisterCollision(GameObject o) {
        collisionsMap.remove(o);
    }

    public void winGame() {
        this.screenManager.switchScreen(new EndScreen(this.screenManager, true, clock.seconds));
    }

    public void loseGame() {
        this.screenManager.switchScreen(new EndScreen(this.screenManager, false, clock.seconds));
    }

    @Override
    public void tick() {
        for (GameObject gameObj : this.gameObjects) {
            if (gameObj instanceof Ball) {
                Command curCommand = new BallMover((Ball) gameObj);
                this.commandStack.add(curCommand);
                curCommand.execute();
            } else if (gameObj instanceof Clock) {
                Clock curClock = (Clock) gameObj;
                if (curClock.getMilliSec() + C.TIMER_PERIOD > 1000) {
                    Command curCommand = new TimerTick(curClock);
                    this.commandStack.add(curCommand);
                    curCommand.execute();
                } else {
                    curClock.tick();
                }

            } else if (gameObj instanceof Paddle) {
                if (Input.upPressed || Input.downPressed) {
                    Paddle curPaddle = (Paddle) gameObj;
                    Command curCommand = new PaddleMover(curPaddle);
                    this.commandStack.add(curCommand);
                    curCommand.execute();
                } else {
                    gameObj.tick();
                }

            } else {
                gameObj.tick();
            }
            // System.out.println(this.commandStack.size());
        }

        System.out.println(this.commandStack.size());

        /*
         * // Handle collisions. // Temporarily store detected collisions & their
         * collision types here. HashMap<GameObject, List<GameObject>> collisions = new
         * HashMap<>(); // Iterate through all the GameObjects that requested collision
         * notification for (GameObject o : collisionsMap.keySet()) { // Get the list of
         * classes they want to be notified of collisions with List<Class<? extends
         * GameObject>> classes = collisionsMap.get(o); // Filter the (list of all
         * GameObjects) to those of the correct class and which are also hitting this
         * object for (GameObject target : gameObjects.stream() .filter(e ->
         * classes.contains(e.getClass())) .filter(e -> Hitbox.collide(o.getHitbox(),
         * e.getHitbox()) != CollisionType.NONE) .collect(Collectors.toList())) { //
         * Finally, call their onCollide... Actually, can't call o.onCollide(target)
         * here since some // objects unregister themselves in the handler, causing a
         * ConcurrentModificationException. // Instead, keep track of the collisions and
         * then call all the handlers at the end. if (!collisions.containsKey(o))
         * collisions.put(o, new ArrayList<GameObject>());
         * collisions.get(o).add(target); } } // Actually call all the collision
         * handlers collisions.forEach((k,v) -> v.forEach(v2 -> k.onCollide(v2)));
         */
        // Handle collisions.
        // Temporarily store detected collisions & their collision types here.
        HashMap<GameObject, List<Pair<GameObject, Hitbox.CollisionType>>> collisions = new HashMap<>();
        // Iterate through all the GameObjects that requested collision notification
        for (GameObject o : collisionsMap.keySet()) {
            // System.out.println(o.getClass());
            // Get the list of classes they want to be notified of collisions with
            List<Class<? extends GameObject>> classes = collisionsMap.get(o);
            // Filter the (list of all GameObjects) to those of the correct class and which
            // are also hitting this object
            for (Pair<GameObject, Hitbox.CollisionType> collision : gameObjects.stream()
                    .filter(gameObject -> classes.contains(gameObject.getClass()))
                    .map(gameObject -> new Pair<GameObject, Hitbox.CollisionType>(gameObject,
                            Hitbox.collide(o.getHitbox(), gameObject.getHitbox())))
                    .filter(pair -> pair.b != Hitbox.CollisionType.NONE).collect(Collectors.toList())) {
                // Finally, call their onCollide... Actually, can't call o.onCollide(target)
                // here since some
                // objects unregister themselves in the handler, causing a
                // ConcurrentModificationException.
                // Instead, keep track of the collisions and then call all the handlers at the
                // end.
                if (!collisions.containsKey(o))
                    collisions.put(o, new ArrayList<Pair<GameObject, Hitbox.CollisionType>>());
                collisions.get(o).add(new Pair<GameObject, Hitbox.CollisionType>(collision.a, collision.b));
            }
        }
        // Actually call all the collision handlers
        collisions.forEach((k, pairs) -> {
            pairs.forEach(pair -> {
                if (k instanceof Ball) {
                    BallCollide curBallCommand = new BallCollide((Ball) k, pair.a, pair.b);
                    this.commandStack.add(curBallCommand);
                    curBallCommand.execute();
                } else if (k instanceof Brick) {
                    // System.out.println("I am working here in brick");
                    BrickCollide curBrickCommand = new BrickCollide((Brick) k, pair.a, pair.b);
                    this.commandStack.add(curBrickCommand);
                    curBrickCommand.execute();
                } else {
                    // System.out.println(k.getClass());
                    k.onCollide(pair.a, pair.b);
                }
            });
        });
    }

    @Override
    public void draw(Graphics2D g) {
        gameObjects.forEach(o -> o.draw(g));
    }

    private void createBricks() {
        while (bricks.size() < C.NUM_BRICKS) {
            int x = C.R.nextInt(C.WIDTH - C.BRICK_MIN_X - C.BRICK_WIDTH) + C.BRICK_MIN_X;
            int y = C.R.nextInt(C.HEIGHT - C.BRICK_HEIGHT);

            // don't let them overlap
            RectangleHitbox h = new RectangleHitbox(x, y, C.BRICK_WIDTH, C.BRICK_HEIGHT);
            if (bricks.stream().anyMatch(e -> Hitbox.collide(e.getHitbox(), h) != Hitbox.CollisionType.NONE))
                continue;

            bricks.add(new Brick(this, new Color(C.R.nextInt(200) + 55, C.R.nextInt(200) + 55, C.R.nextInt(200) + 55),
                    x, y, C.BRICK_WIDTH, C.BRICK_HEIGHT));
        }
    }
}
