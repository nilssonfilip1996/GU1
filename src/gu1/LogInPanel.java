package gu1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LogInPanel extends JPanel implements ActionListener {
	private MediaViewer mainFrame;
	private JLabel logInLbl;
	private JTextField logInTxt;
	private JButton logInBtn;
	
	public LogInPanel(MediaViewer mainFrame){
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
		logInBtn.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==logInBtn){
			mainFrame.logInPressed(logInTxt.getText());
		}
	}
}
