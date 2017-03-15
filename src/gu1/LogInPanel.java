package gu1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * The class LogInPanel represents the login-screen of a library application.
 * It extends JPanel giving it attributes of a JPanel.
 * 
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
 *
 */
public class LogInPanel extends JPanel implements ActionListener {
	private MediaViewer mainFrame;
	private JLabel logInLbl;
	private JTextField logInTxt;
	private JButton logInBtn;
	
	/**
	 * Constructor receives an instance of MediaViewer making it accessable.
	 * Calls for drawLoginPanel which adds components to the JPanel.
	 * @param mainFrame , MediaViewer extends JFrame
	 */
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
	
	/**
	 * Called upon when the JButton logInBtn is pressed.
	 * When the button is pressed the logInPressed method within the MediaViewer is called upon.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==logInBtn){
			mainFrame.logInPressed(logInTxt.getText());
		}
	}
}
