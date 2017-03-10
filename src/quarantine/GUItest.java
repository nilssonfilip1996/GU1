package quarantine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.prism.paint.Color;

import gu1.Controller;
import gu1.Media;
import gu1.User;
import sun.applet.Main;

public class GUItest extends JPanel implements ActionListener {

	private Controller controller;
	private JFrame mainWindow;
	private JFrame profileWindow;
	private JPanel south = new JPanel(new BorderLayout());
	private JPanel center = new JPanel(new FlowLayout());
	private JTextField logInId = new JTextField();
	private JButton logInBtn = new JButton("OK");
	private JLabel logInHead = new JLabel("Logga in");
	private JPanel logInPanel = new JPanel(new GridLayout(1, 3));
	private JComboBox<String> mediaUserLoan = new JComboBox<String>();
	private JComboBox<String> mediaAvailable = new JComboBox<String>();

	public GUItest(Controller controller) {
		this.controller = controller;

		mainWindow = new JFrame("Log-in screen");

		mainWindow.setSize(1200, 700);
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
			controller.checkLogIn(logInId.getText());
			// System.out.println(logInId.getText());

		}

	}

	public void showError() {

		JOptionPane.showMessageDialog(null, "Du har inte behörighet");

	}

	public void profileScreen(User theUser, LinkedList<Media> mediaList) {
		mainWindow.dispose();

		profileWindow = new JFrame("Profil");
		profileWindow.setSize(1200, 700);
		profileWindow.setVisible(true);
		profileWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		profileWindow.setLayout(new FlowLayout());
		
		profileWindow.add(mediaAvailable);
		profileWindow.add(mediaUserLoan);
		
		fillComboBoxLibrary(mediaList, mediaAvailable);
		fillComboBoxUserLoans(theUser.getMyLoans(), mediaUserLoan);
	}

	public void fillComboBoxLibrary(LinkedList<Media> list, JComboBox<String> mediaLibrary) {

		for (int i = 0; i < list.size(); i++) {
			if (!(list.get(i).isBorrowed())) {
				mediaLibrary.addItem(list.get(i).getTitel().toString());
			}
		}
	}

	private void fillComboBoxUserLoans(LinkedList<Media> myLoans, JComboBox<String> mediaAvailable) {
		for(int i=0; i<myLoans.size(); i++){
			mediaAvailable.addItem(myLoans.get(i).getTitel().toString());
		}
	}
	
	
}
