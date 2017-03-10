package quarantine;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import gu1.*;

public class GuiTest2 extends JFrame{
	private Controller controller;
	private LogInPanel logInPanel;
	private JPanel userPanel;
	
	public GuiTest2(Controller controller){
		this.controller = controller;
		setSize(1200, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		drawLoginPanel();
	}
	
	
	


	private void drawLoginPanel() {
		logInPanel = new LogInPanel(this);
		add(logInPanel);
	}

	public void logInPressed(String idEntered){
		
	}


	
	
	
	
	
	
	
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}

	}
}


