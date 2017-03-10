package quarantine;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class LibraryPanel extends JPanel {
	private GuiTest2 mainFrame;
	private JLabel welcomeLbl;
	private JList<String> mediaUserLoan;
	private JList<String> availableMedia;
	private JList<String> userLoans;
	private JButton makeLoan;

	public LibraryPanel(GuiTest2 mainFrame) {
		this.mainFrame = mainFrame;
		drawLibraryPanel();
	}

	private void drawLibraryPanel() {
		setLayout(new BorderLayout());
		this.availableMedia = new JList<String>();
		availableMedia.setBackground(Color.GREEN);
		this.userLoans = new JList<String>();
		userLoans.setBackground(Color.BLUE);
		this.welcomeLbl = new JLabel("hello");
		this.makeLoan = new JButton("Make Loan");
		add(makeLoan, BorderLayout.SOUTH);
		add(availableMedia, BorderLayout.WEST);
		add(userLoans, BorderLayout.EAST);
	}

	public void fillUserName(String name) {
		welcomeLbl.setText("Welcome: " + name);
	}

	public void fillAvailableMediaList(String[] availableMedia) {
		this.availableMedia.setListData(availableMedia);

	}

	public void fillUserLoanList(String[] userLoans) {
		this.userLoans.setListData(userLoans);
	}
}
