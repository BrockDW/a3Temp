package breakout;

import java.awt.GridBagLayout;

import javax.swing.*;

import breakout.gamescreens.ScreenManager;

public class Main {
	public static void main(String[] args) {
        JFrame frame = new JFrame();
        ScreenManager sm = new ScreenManager();
        frame.setContentPane(sm);
        frame.addKeyListener(new Input());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        JButton pause = new JButton("Pause");
    	JButton restart = new JButton("Restart");
    	JButton undo = new JButton("Undo");
    	JButton replay = new JButton("Replay from begining");
    	frame.add(panel);
    	panel.add(pause);
    	frame.pack();
        frame.setVisible(true);
	}
}
