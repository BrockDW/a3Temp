package breakout;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {
	public static boolean spacePressed;
	public static boolean upPressed;
	public static boolean downPressed;
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE: spacePressed = true; break;
			case KeyEvent.VK_DOWN :  downPressed = true; break;
			case KeyEvent.VK_UP   :    upPressed = true; break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE: spacePressed = false; break;
			case KeyEvent.VK_DOWN :  downPressed = false; break;
			case KeyEvent.VK_UP   :    upPressed = false; break;
		}
	}
}
