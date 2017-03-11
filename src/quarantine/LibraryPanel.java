package quarantine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import gu1.Controller;
import gu1.Media;

public class LibraryPanel extends JPanel implements ListSelectionListener, ActionListener {
	private GuiTest2 mainFrame;
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
		this.availableMedia = new JList<String>();
		this.userLoans = new JList<String>();
		this.welcomeLbl = new JLabel("hello");
		this.makeLoanBtn = new JButton("Make Loan");
		this.mediaInfoTxt = new JTextArea();
		mediaInfoTxt.setBackground(Color.ORANGE);
		availableMedia.setBackground(Color.GREEN);
		userLoans.setBackground(Color.YELLOW);

		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLbl.setPreferredSize(new Dimension(getWidth(), 40));
		add(welcomeLbl, BorderLayout.NORTH);
		add(mediaInfoTxt, BorderLayout.CENTER);
		add(makeLoanBtn, BorderLayout.SOUTH);
		add(availableMedia, BorderLayout.WEST);
		add(userLoans, BorderLayout.EAST);

		makeLoanBtn.addActionListener(this);
		userLoans.addListSelectionListener(this);
		availableMedia.addListSelectionListener(this);
	}

	public void fillUserName(String name) {
		welcomeLbl.setText("Welcome: " + name);
	}

	public void fillAvailableMediaList(Media[] availableMedia) {
		String[] availableMediaArr = new String[availableMedia.length];
		for(int i=0; i<availableMediaArr.length; i++){
			if(availableMedia[i]!=null){
			availableMediaArr[i] = availableMedia[i].getId() + ", " +availableMedia[i].getTitel();		//Nödlösning, visar upp mediats id tillsamans med titeln för att sedan kunna hämta mediats id.
			}
		}
		this.availableMedia.setListData(availableMediaArr);
	}

	public void fillUserLoanList(Media[] userLoans) {
		String[] userLoanArr = new String[userLoans.length];
		for(int i=0; i<userLoanArr.length; i++){
			userLoanArr[i] = userLoans[i].getTitel();
		}

		this.userLoans.setListData(userLoanArr);
	}
	
	public void addLoan(String media){
		listModel.addElement(media);
		userLoans.setModel(listModel);
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
			//System.out.println(availableMedia.getSelectedValue());
			String key = availableMedia.getSelectedValue().substring(0, 6);				//urskilja nyckeln till media.
			controller.borrowMedia(key);
			//System.out.println(key);
			
		}
		
	}

	public void showMediaInfo(String mediaInfo) {
		mediaInfoTxt.setText(mediaInfo);
		
	}

}
