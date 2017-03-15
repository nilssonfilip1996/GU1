package gu1;

import java.awt.*;

import javax.swing.*;

/**
 * The class MediaViewer represents the graphical interface of the Library
 * program. The class uses two instances of JPanel(LogInPanel & LibraryPanel) to
 * display different kinds of content. A Controller instance decides which of
 * these two panels to show.
 * 
 * 
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter
 *         Dezsi
 *
 */
public class MediaViewer extends JFrame {
	private Controller controller;
	private LogInPanel logInPanel;
	private LibraryPanel libraryPanel;

	/**
	 * The constructor draws the frame together with adding an instance of
	 * LogInPanel and an instance of LibraryPanel. When constructed, only the
	 * LogInPanel is set to visible and not the LibraryPanel.
	 * 
	 * @param controller
	 */
	public MediaViewer(Controller controller) {
		this.controller = controller;
		setSize(1200, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new CardLayout());
		drawLoginPanel();
		drawLibraryPanel();
	}

	private void drawLoginPanel() {
		logInPanel = new LogInPanel(this);
		add(logInPanel);
	}

	private void drawLibraryPanel() {
		libraryPanel = new LibraryPanel(controller);
		add(libraryPanel);
		libraryPanel.setVisible(false);
	}

	/**
	 * Calls for the method login in the class controller.
	 * 
	 * @param idEntered
	 */
	public void logInPressed(String idEntered) {
		controller.login(idEntered);
	}

	/**
	 * Sets the LoginPanel to invisible and the LibraryPanel to visible. Also
	 * passes a userName to the LibraryPanel.
	 * 
	 * @param name
	 */
	public void loginToLibraryPanel(String name) {
		logInPanel.setVisible(false);
		libraryPanel.fillUserName(name);
		libraryPanel.setVisible(true);
	}

	/**
	 * Used to display the available media and the loans of a specific user.
	 * 
	 * @param availableMediaList
	 *            , media-array to display as available.
	 * @param userLoanList
	 *            , media-array to show the logged in users loans.
	 */
	public void updateMediaLists(Media[] availableMediaList, Media[] userLoanList) {
		libraryPanel.fillAvailableMediaList(availableMediaList);
		libraryPanel.fillUserLoanList(userLoanList);
	}

	/**
	 * Recieves the info of a specific media as a String and passes it to the
	 * method showMediaInfo in the class LibraryPanel.
	 * 
	 * @param media
	 *            , information of a certain media within the library.
	 */
	public void updateMediaInfoField(String media) {
		libraryPanel.showMediaInfo(media);
	}

	/**
	 * Sets the logInPanel to visible and the libraryPanel to invisible.
	 */
	public void libraryToLoginPanel() {
		logInPanel.setVisible(true);
		libraryPanel.setVisible(false);
	}
}
