package quarantine;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class LibraryPanel extends JPanel{
	private GuiTest2 mainFrame;
	private JLabel welcomeLbl;
	private JList availableMedia;
	
	public LibraryPanel(GuiTest2 mainFrame){
		this.mainFrame = mainFrame;
		drawLibraryPanel();
	}
	
	private void drawLibraryPanel(){
		setLayout(new GridBagLayout());
		this.availableMedia = new JList();
		this.welcomeLbl = new JLabel("hello");
		add(welcomeLbl);
	}
	
	public void fillUserName(String name){
		welcomeLbl.setText("Welcome: " +name);
	}
	
	public void fillAvailableMediaList(ArrayList<String> availableMedia){
		
	}
}
