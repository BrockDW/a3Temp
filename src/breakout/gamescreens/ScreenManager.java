package breakout.gamescreens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.Timer;

import breakout.C;

public class ScreenManager extends JComponent {
    private static final long serialVersionUID = -4183563130393427881L;
    private GameScreen currentScreen;
    Timer t = new Timer(C.TIMER_PERIOD, e -> tick());

    public ScreenManager() {
        this.setPreferredSize(new Dimension(C.WIDTH, C.HEIGHT));

        t.start();
        currentScreen = new StartScreen(this);
        this.setDoubleBuffered(true);
    }
    
    public GameScreen getScreen() {
        return this.currentScreen;
    }

    public void tick() {
        currentScreen.tick();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics gfx) {
        super.paintComponent(gfx);
        Graphics2D g = (Graphics2D) gfx;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, C.WIDTH, C.HEIGHT);
        currentScreen.draw(g);
        g.dispose();
    }

    public void switchScreen(GameScreen newScreen) {
        currentScreen = newScreen;
    }

    public void timerStop() {
        t.stop();
    }

    public void timerStart() {
        t.start();
    }
}
