package gu1;

import java.awt.*;

import javax.swing.*;


/**
 * The class MediaViewer represents the graphical interface of the Library program.
 * The class uses two instances of JPanel(LogInPanel & LibraryPanel) to display different kinds of content.
 * A Controller instance decides which of these two panels to show.
 * 
 * 
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
 *
 */
public class MediaViewer extends JFrame {
	private Controller controller;
	private LogInPanel logInPanel;
	private LibraryPanel libraryPanel;

	/**
	 * The constructor draws the frame together with an instance of LogInPanel and an instance of LibraryPanel. 
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

	public void logInPressed(String idEntered) {
		controller.login(idEntered);
	}

	public void loginToLibraryPanel(String name) {
		logInPanel.setVisible(false);
		libraryPanel.fillUserName(name);
		libraryPanel.setVisible(true);
	}
	
	public void updateMediaLists(Media[] availableMediaList, Media[] userLoanList){
		libraryPanel.fillAvailableMediaList(availableMediaList);
		libraryPanel.fillUserLoanList(userLoanList);
	}

	public void updateMediaInfoField(String media) {
		libraryPanel.showMediaInfo(media);	
	}

	public void libraryToLoginPanel() {
		logInPanel.setVisible(true);
		libraryPanel.setVisible(false);	
	}
}
