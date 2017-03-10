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
		availableMedia.addListSelectionListener(this);
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
	
	public void addLoan(String media){
		listModel.addElement(media);
		userLoans.setModel(listModel);
	}

	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			if (e.getSource() == availableMedia) {
				System.out.println(availableMedia.getSelectedValue());
				
			}
			else if(e.getSource()==userLoans){
				System.out.println(userLoans.getSelectedValue());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==makeLoanBtn){
			System.out.println(availableMedia.getSelectedValue());
			addLoan(availableMedia.getSelectedValue());
			
		}
		
	}

}
