package gu1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

/**
 * The class LibraryPanel represents graphical interface a user is faced with
 * after a successful login a library application. It extends JPanel giving it
 * attributes of a JPanel.
 * 
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
 *
 */
public class LibraryPanel extends JPanel implements ListSelectionListener, ActionListener, KeyListener {

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

	/**
	 * Constructor receives an instance of the class Controller.
	 * Calls for drawLibraryPanel which adds components to the JPanel.
	 * @param controller
	 */
	public LibraryPanel(Controller controller) {

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
		this.librarySideLbl.setPreferredSize(new Dimension(320, 40));
		this.welcomeLbl = new JLabel();
		this.welcomeLbl.setOpaque(true);
		this.welcomeLbl.setBackground(Color.PINK);
		this.userSideLbl = new JLabel("User loans:");
		this.userSideLbl.setBackground(Color.MAGENTA);
		this.userSideLbl.setOpaque(true);
		this.userSideLbl.setPreferredSize(new Dimension(320, 40));
		this.availableMedia = new JList<String>();
		this.userLoans = new JList<String>();
		this.makeLoanBtn = new JButton("Loan");
		add(center, BorderLayout.CENTER);
		this.mediaInfoTxt = new JTextArea();
		this.mediaInfoTxt.setEditable(false);
		searchText.setText("Search for available titles...");

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
		searchText.addKeyListener(this);
	}

	/**
	 * Displays the name of the current user.
	 * @param name , name to be displayed.
	 */
	public void fillUserName(String name) {
		welcomeLbl.setText("Welcome: " + name);
	}

	/**
	 * Method receives an array of Media objects that are available for loan and displays them.
	 * 
	 * @param availableMedia , Media array
	 */
	public void fillAvailableMediaList(Media[] availableMedia) {
		DefaultListModel<String> availableMediaListModel = new DefaultListModel<String>();
		for (int i = 0; i < availableMedia.length; i++) {
			if (availableMedia[i] != null) {
				availableMediaListModel.addElement(availableMedia[i].getId() + ", " + availableMedia[i].getTitle());
			}
		}
		this.availableMedia.setModel(availableMediaListModel);
	}

	/**
	 * Method receives an array of Media objects corresponding to the loans of a certain user.
	 * 
	 * @param userLoans , Media array
	 */
	public void fillUserLoanList(Media[] userLoans) {
		DefaultListModel<String> userLoanListModel = new DefaultListModel<String>();
		for (int i = 0; i < userLoans.length; i++) {
			userLoanListModel.addElement(userLoans[i].getId() + ", " + userLoans[i].getTitle());
		}
		this.userLoans.setModel(userLoanListModel);
	}

	/**
	 * Receives the info of a particular media and displays it.
	 * @param mediaInfo , info of a particular media to display.
	 */
	public void showMediaInfo(String mediaInfo) {
		mediaInfoTxt.setText(mediaInfo);
	}

	/**
	 * Called upon when the user selects something in either the available media list or the user loan list.
	 * Shows info of a certain media title that is selected in one of the lists.
	 */
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

	/**
	 * Called upon when the user presses one of the buttons on the GUI.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == makeLoanBtn && !availableMedia.isSelectionEmpty()) {
			String mediaID = availableMedia.getSelectedValue().substring(0, 6);
			controller.borrowMedia(mediaID);
		} else if (e.getSource() == returnLoanBtn && !userLoans.isSelectionEmpty()) {
			String mediaID = userLoans.getSelectedValue().substring(0, 6);
			controller.returnMedia(mediaID);
		} else if (e.getSource() == searchBtn) {
			String input = searchText.getText();
			controller.showFoundMedia(input);
		} else if (e.getSource() == logOutBtn) {
			controller.logOut();
		}
	}
	/**
	 * Not used.
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Called upon when the user presses Enter on the keyboard.
	 * Does the same as when Search Media is pressed i.e searches for a media and prints the result.
	 */
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_ENTER)) {
			String input = searchText.getText();
			controller.showFoundMedia(input);
		}
	}

	/**
	 * Not used.
	 */
	public void keyReleased(KeyEvent e) {
	}
}
