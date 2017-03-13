package quarantine;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import gu1.*;

public class GuiTest2 extends JFrame {
	private Controller controller;
	private LogInPanel logInPanel;
	private LibraryPanel libraryPanel;

	public GuiTest2(Controller controller) {
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
		libraryPanel = new LibraryPanel(this, controller);
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
