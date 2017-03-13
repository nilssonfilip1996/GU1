package quarantine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.event.*;
import gu1.Controller;
import gu1.Media;

public class LibraryPanel extends JPanel implements ListSelectionListener, ActionListener {
	private GuiTest2 mainFrame;
	private JPanel librarySide;
	private JPanel userSide;
	private JPanel center;
	private JPanel north;
	private JLabel librarySideLbl;
	private JLabel userSideLbl;
	private JLabel welcomeLbl;
	private JLabel middle;
	private JTextArea mediaInfoTxt;
	private JList<String> availableMedia;
	private JList<String> userLoans;
	private JPanel southGrid;
	private JButton makeLoanBtn;
	private JButton searchBtn;
	private JButton logOutBtn;
	private JButton returnLoanBtn;
	private JTextField searchText;
	private Controller controller;

	public LibraryPanel(GuiTest2 mainFrame, Controller controller) {
		this.mainFrame = mainFrame;
		this.controller = controller;
		drawLibraryPanel();
	}

	private void drawLibraryPanel() {
		setLayout(new BorderLayout());
		this.returnLoanBtn = new JButton("Return Media");
		this.searchBtn = new JButton("Seach Media");
		this.logOutBtn = new JButton("Log Out");
		this.searchText = new JTextField();
		this.southGrid = new JPanel(new GridLayout(1, 3));
		this.librarySide = new JPanel();
		this.center = new JPanel(new BorderLayout());
		this.north = new JPanel(new BorderLayout());
		this.middle = new JLabel();
		this.librarySide.setLayout(new BorderLayout());
		this.userSide = new JPanel();
		this.userSide.setLayout(new BorderLayout());
		this.librarySideLbl = new JLabel("Available Media:");
		this.librarySideLbl.setBackground(Color.CYAN);
		this.librarySideLbl.setOpaque(true);
		this.welcomeLbl = new JLabel();
		this.welcomeLbl.setOpaque(true);
		this.welcomeLbl.setBackground(Color.PINK);
		this.userSideLbl = new JLabel("User loans:");
		this.userSideLbl.setBackground(Color.MAGENTA);
		this.userSideLbl.setOpaque(true);
		this.availableMedia = new JList<String>();
		this.userLoans = new JList<String>();
		this.makeLoanBtn = new JButton("Make Loan");
		add(center, BorderLayout.CENTER);
		this.mediaInfoTxt = new JTextArea();
		this.mediaInfoTxt.setEditable(false);
		
		southGrid.setBackground(Color.BLUE);
		southGrid.setOpaque(true);
		southGrid.add(makeLoanBtn);
		southGrid.add(searchBtn);
		southGrid.add(returnLoanBtn);
		middle.setOpaque(true);
		middle.setBackground(Color.ORANGE);
		center.add(mediaInfoTxt, BorderLayout.NORTH);
		center.add(middle, BorderLayout.CENTER);
		center.add(searchText, BorderLayout.SOUTH);

		librarySide.add(librarySideLbl, BorderLayout.NORTH);
		librarySide.add(availableMedia, BorderLayout.CENTER);
		userSide.add(userSideLbl, BorderLayout.NORTH);
		userSide.add(userLoans, BorderLayout.CENTER);

		mediaInfoTxt.setBackground(Color.ORANGE);
		availableMedia.setBackground(Color.GREEN);
		userLoans.setBackground(Color.YELLOW);

		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLbl.setPreferredSize(new Dimension(getWidth(), 40));
		makeLoanBtn.setPreferredSize(new Dimension(getWidth(), 60));
		north.add(welcomeLbl, BorderLayout.CENTER);
		north.add(logOutBtn, BorderLayout.EAST);
		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);

		add(southGrid, BorderLayout.SOUTH);
		librarySide.setPreferredSize(new Dimension(320, getHeight()));
		add(librarySide, BorderLayout.WEST);
		userSide.setPreferredSize(new Dimension(320, getHeight()));
		add(userSide, BorderLayout.EAST);


		makeLoanBtn.addActionListener(this);
		searchBtn.addActionListener(this);
		returnLoanBtn.addActionListener(this);
		userLoans.addListSelectionListener(this);
		availableMedia.addListSelectionListener(this);
		logOutBtn.addActionListener(this);
	}

	public void fillUserName(String name) {
		welcomeLbl.setText("Welcome: " + name);
	}

	public void fillAvailableMediaList(Media[] availableMedia) {
		DefaultListModel<String> availableMediaListModel = new DefaultListModel<String>();
		for (int i = 0; i < availableMedia.length; i++) {
			if (availableMedia[i] != null) {
				availableMediaListModel.addElement(availableMedia[i].getId() + ", " + availableMedia[i].getTitel()); // Nödlösning,
																														// visar
																														// upp
																														// mediats
																														// id
																														// tillsamans
																														// med
																														// titeln
																														// för
																														// att
																														// sedan
																														// kunna
																														// hämta
																														// mediats
																														// id.
			}
		}
		this.availableMedia.setModel(availableMediaListModel);
	}

	public void fillUserLoanList(Media[] userLoans) {
		DefaultListModel<String> userLoanListModel = new DefaultListModel<String>();
		for (int i = 0; i < userLoans.length; i++) {
			userLoanListModel.addElement(userLoans[i].getId() + ", " + userLoans[i].getTitel());
		}

		this.userLoans.setModel(userLoanListModel);
	}

	// public void addLoan(String media){
	// listModel.addElement(media);
	// userLoans.setModel(listModel);
	// }

	public void showMediaInfo(String mediaInfo) {
		mediaInfoTxt.setText(mediaInfo);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			if (e.getSource() == availableMedia) {
				controller.showSingleMediaInfo(availableMedia.getSelectedValue().substring(0, 6));
				userLoans.clearSelection();
			} else if (e.getSource() == userLoans) {
				controller.showSingleMediaInfo(userLoans.getSelectedValue().substring(0, 6));
				availableMedia.clearSelection();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == makeLoanBtn && !availableMedia.isSelectionEmpty()) {
			String mediaID = availableMedia.getSelectedValue().substring(0, 6); // urskilja
																				// nyckeln
																				// till
																				// media.
			controller.borrowMedia(mediaID);
		} else if (e.getSource() == returnLoanBtn && !userLoans.isSelectionEmpty()) {
			String mediaID = userLoans.getSelectedValue().substring(0, 6);
			controller.returnMedia(mediaID);
		} else if (e.getSource() == searchBtn) {
			String input = searchText.getText();
			controller.showFoundMedia(input);
		}
		else if(e.getSource() == logOutBtn){
			controller.logOut();
		}
	}

}
