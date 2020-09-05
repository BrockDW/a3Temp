package breakout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

import breakout.gamescreens.GameScreen;
import breakout.gamescreens.MainScreen;
import breakout.gamescreens.ScreenManager;

public class Main {
	public static void main(String[] args) {
		
        JFrame frame = new JFrame();
        ScreenManager sm = new ScreenManager();
//        frame.setContentPane(sm);

        sm.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"pressed");
        sm.getActionMap().put("pressed", new AbstractAction() {
			private static final long serialVersionUID = -7539932265070492785L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sm.switchScreen(new MainScreen(sm));
			}
        	
        });
        
        sm.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downPressed");
        sm.getActionMap().put("downPressed", new AbstractAction() {

			private static final long serialVersionUID = -8298149438222677521L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				if(Input.downPressed)
				Input.downPressed = true; 
			}
        	
        });
        sm.getInputMap().put(KeyStroke.getKeyStroke("released DOWN"), "downReleased");
        sm.getActionMap().put("downReleased", new AbstractAction() {

			private static final long serialVersionUID = -2676482285942674873L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Input.downPressed = false; 
			}
        	
        });
        sm.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upPressed");
        sm.getActionMap().put("upPressed", new AbstractAction() {
        	
			private static final long serialVersionUID = -2676482285942674873L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Input.upPressed = true; 
			}
        	
        });
        sm.getInputMap().put(KeyStroke.getKeyStroke("released UP"), "upReleased");
        sm.getActionMap().put("upReleased", new AbstractAction() {

			private static final long serialVersionUID = 7724366134375482607L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Input.upPressed = false; 
			}
        	
        });
        
        frame.add(sm,BorderLayout.CENTER);
        frame.addKeyListener(new Input());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel p = new JPanel();
        JButton pause = new JButton("Pause");
    	JButton restart = new JButton("Restart");
    	JButton undo = new JButton("Undo");
    	JButton replayBeginning = new JButton("Replay from Beginning");
    	p.add(pause);
    	p.add(restart);
    	p.add(undo);
    	p.add(replayBeginning);
    	frame.add(p,BorderLayout.SOUTH);
    	
    	
    	frame.pack();
        frame.setVisible(true);
	}
}
