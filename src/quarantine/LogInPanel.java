package quarantine;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LogInPanel extends JPanel{
	private GuiTest2 mainFrame;
	private JLabel logInLbl;
	private JTextField logInTxt;
	private JButton logInBtn;
	
	public LogInPanel(GuiTest2 mainFrame){
		this.mainFrame = mainFrame;
		drawLoginPanel();
	}
	
	private void drawLoginPanel(){
		setBackground(Color.PINK);
		logInLbl = new JLabel("Enter login");
		logInTxt = new JTextField();
		logInTxt.setPreferredSize(new Dimension(100, 20));
		logInBtn = new JButton("Press to login");
		setLayout(new GridBagLayout());
		add(logInLbl);
		add(logInTxt);
		add(logInBtn);
		ButtonListener listener = new ButtonListener();
		logInBtn.addActionListener(listener);
	}
	
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==logInBtn){
				mainFrame.logInPressed(logInTxt.getText());
			}
			
		}

	}
}
