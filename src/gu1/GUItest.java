package gu1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUItest extends JPanel implements ActionListener {

	private Controller c;
	private JFrame mainWindow;
	private JFrame profileWindow;
	private JPanel south = new JPanel(new BorderLayout());
	private JPanel center = new JPanel(new FlowLayout());
	private JTextField logInId = new JTextField();
	private JButton logInBtn = new JButton("OK");
	private JLabel logInHead = new JLabel("Logga in");
	private JPanel logInPanel = new JPanel(new GridLayout(1, 3));
	private JList<String> mediaUserLoan;
	private JList<String> mediaAvailable;

	public GUItest(Controller controller) {
		this.c = controller;
		
		mainWindow = new JFrame("Log-in screen");

		mainWindow.setSize(700, 700);
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLayout(new FlowLayout());

		center.setPreferredSize(new Dimension(400, 400));
		logInId.setPreferredSize(new Dimension(100, 25));
		mainWindow.add(center, BorderLayout.CENTER);
		mainWindow.add(south, BorderLayout.SOUTH);
		center.add(logInPanel, BorderLayout.CENTER);
		logInPanel.add(logInHead, BorderLayout.NORTH);
		logInPanel.add(logInId, BorderLayout.CENTER);
		logInPanel.add(logInBtn);
		logInBtn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logInBtn) {
			if(c.login(logInId.getText())){
				profileScreen();
			} else {
				logInId.setText("");
			}
		}
	}

	public void addLoan(String media){
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement(media);
		mediaUserLoan.setModel(listModel);
	}
	
	public void profileScreen() {
		mainWindow.dispose();

		profileWindow = new JFrame("Profil");
		profileWindow.setSize(700, 700);
		profileWindow.setVisible(true);
		profileWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		profileWindow.setLayout(new FlowLayout());
		
		mediaAvailable = new JList<String>(c.populateAvailableMediaList());
		mediaUserLoan = new JList<String>();
		
		profileWindow.add(mediaAvailable);
		profileWindow.add(mediaUserLoan);
		
		c.borrowMedia(JOptionPane.showInputDialog("Enter Media ID to borrow")); //<--- testing borrow
	}
}
