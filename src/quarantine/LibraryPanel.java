package quarantine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;
import gu1.Controller;
import gu1.Media;

public class LibraryPanel extends JPanel implements ListSelectionListener, ActionListener {
	private GuiTest2 mainFrame;
	private JPanel librarySide;
	private JPanel userSide;
	private JLabel librarySideLbl;
	private JLabel userSideLbl;
	private JLabel welcomeLbl;
	private JTextArea mediaInfoTxt;
	private JList<String> availableMedia;
	private JList<String> userLoans;
	private JButton makeLoanBtn;
	private Controller controller;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();

	public LibraryPanel(GuiTest2 mainFrame, Controller controller) {
		this.mainFrame = mainFrame;
		this.controller = controller;
		drawLibraryPanel();
	}

	private void drawLibraryPanel() {
		setLayout(new BorderLayout());
		this.librarySide = new JPanel();
		this.librarySide.setLayout(new BorderLayout());
		this.userSide = new JPanel();
		this.userSide.setLayout(new BorderLayout());
		this.librarySideLbl = new JLabel("Available Media:");
		librarySideLbl.setBackground(Color.CYAN);
		librarySideLbl.setOpaque(true);
		this.userSideLbl = new JLabel("User loans:");
		userSideLbl.setBackground(Color.MAGENTA);
		userSideLbl.setOpaque(true);;
		this.availableMedia = new JList<String>();
		this.userLoans = new JList<String>();
		librarySide.add(librarySideLbl, BorderLayout.NORTH);
		librarySide.add(availableMedia, BorderLayout.CENTER);
		userSide.add(userSideLbl, BorderLayout.NORTH);
		userSide.add(userLoans, BorderLayout.CENTER);
		this.welcomeLbl = new JLabel();
		this.makeLoanBtn = new JButton("Make Loan");
		this.mediaInfoTxt = new JTextArea();
		this.mediaInfoTxt.setEditable(false);
		mediaInfoTxt.setBackground(Color.ORANGE);
		availableMedia.setBackground(Color.GREEN);
		userLoans.setBackground(Color.YELLOW);

		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLbl.setPreferredSize(new Dimension(getWidth(), 40));
		makeLoanBtn.setPreferredSize(new Dimension(getWidth(), 60));
		add(welcomeLbl, BorderLayout.NORTH);
		add(mediaInfoTxt, BorderLayout.CENTER);
		add(makeLoanBtn, BorderLayout.SOUTH);
		add(librarySide, BorderLayout.WEST);
		add(userSide, BorderLayout.EAST);

		makeLoanBtn.addActionListener(this);
		userLoans.addListSelectionListener(this);
		availableMedia.addListSelectionListener(this);
	}

	public void fillUserName(String name) {
		welcomeLbl.setText("Welcome: " + name);
	}

	public void fillAvailableMediaList(Media[] availableMedia) {
		DefaultListModel<String> availableMediaListModel = new DefaultListModel<String>();
		for(int i=0; i<availableMedia.length; i++){
			if(availableMedia[i]!=null){
				availableMediaListModel.addElement(availableMedia[i].getId() + ", " +availableMedia[i].getTitel());		//Nödlösning, visar upp mediats id tillsamans med titeln för att sedan kunna hämta mediats id.		
			}
		}
		this.availableMedia.setModel(availableMediaListModel);
	}

	public void fillUserLoanList(Media[] userLoans) {
		DefaultListModel<String> userLoanListModel = new DefaultListModel<String>();
		for(int i=0; i<userLoans.length; i++){
			userLoanListModel.addElement(userLoans[i].getId() + ", " +userLoans[i].getTitel());
		}

		this.userLoans.setModel(userLoanListModel);
	}
	
//	public void addLoan(String media){
//		listModel.addElement(media);
//		userLoans.setModel(listModel);
//	}

	public void showMediaInfo(String mediaInfo) {
			mediaInfoTxt.setText(mediaInfo);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			if (e.getSource() == availableMedia) {
				System.out.println(availableMedia.getSelectedValue());
				controller.showMediaInfo(availableMedia.getSelectedValue().substring(0, 6));
			}
			else if(e.getSource()==userLoans){
				System.out.println(userLoans.getSelectedValue());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==makeLoanBtn && !availableMedia.isSelectionEmpty()){
			String key = availableMedia.getSelectedValue().substring(0, 6);				//urskilja nyckeln till media.
			controller.borrowMedia(key);
		}
	}
}
