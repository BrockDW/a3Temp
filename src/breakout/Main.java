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
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sm.switchScreen(new MainScreen(sm));
			}
        	
        });
        
//        sm.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed");
//        sm.getActionMap().put("pressed", new AbstractAction() {
//        	
//        });
        
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
    	
    	// Reference https://stackoverflow.com/questions/4472530/disabling-space-bar-triggering-click-for-jbutton
    	InputMap im = (InputMap)UIManager.get("Button.focusInputMap");
    	im.put(KeyStroke.getKeyStroke("pressed SPACE"), "none");
    	im.put(KeyStroke.getKeyStroke("released SPACE"), "none");

    	frame.pack();
        frame.setVisible(true);
	}
}
