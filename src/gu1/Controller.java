package gu1;

import java.io.*;
import java.util.*;
import javax.swing.*;

import quarantine.GUItest;
import quarantine.GuiTest2;

public class Controller {
	private MediaLibrary<String,Media> mediaLibrary;
	private UserDatabase<String,User> userDatabase;
	private User currentUser;
	private GUItest test;
	private GuiTest2 mainWindow;


	public Controller(String mediaListPath, String userListPath) {
		this.userDatabase = populateUserDatabase(userListPath);
		this.mediaLibrary = populateMediaLibrary(mediaListPath);
		this.mainWindow = new GuiTest2(this);
		test = new GUItest(this);
	}

	public UserDatabase<String, User> populateUserDatabase(String userListPath) {
		UserDatabase<String, User> userList = new UserDatabase<String, User>();
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(userListPath), "UTF-8"));
			String str;
			String[] values;
			while ((str = reader.readLine()) != null) {
				values = str.split(";");
				userList.put(values[0], new User(values[0], values[1], values[2]));
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return userList;
	}

	public MediaLibrary<String,Media> populateMediaLibrary(String mediaListPath) {
		MediaLibrary<String,Media> mediaList = new MediaLibrary<String,Media>();
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(mediaListPath), "UTF-8"));
			String str;
			String[] values;
			while ((str = reader.readLine()) != null) {
				values = str.split(";");
				if (values[0].equals("Dvd")) {
					String[] actors = new String[values.length - 4];
					for (int i = 0; i < actors.length; i++)
						actors[i] = values[i + 4];
					mediaList.put(values[1],new Dvd(values[1], values[2], values[3], actors));
				} else if (values[0].equals("Bok")) {
					mediaList.put(values[1],new Book(values[1], values[2], values[3], values[4]));
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return mediaList;
	}
	
	public String[] populateAvailableMediaList(){
		Iterator<Media> values = mediaLibrary.values();
		String[] mediaList = new String[mediaLibrary.size()];
		int index = 0;
		while (values.hasNext()){
			mediaList[index] = values.next().getTitel();
			index++;
		}
		return mediaList;
	}
	
	public String[] populateCurrentUserLoanList(){
		Iterator<Media> values = currentUser.loans().iterator();
		String[] loanList = new String[currentUser.loans().size()];
		int index = 0;
		while (values.hasNext()){
			loanList[index] = values.next().getTitel();
			index++;
		}
		return loanList;
	}
	
	public String[] searchTitle(String input) {
		String[] inputs = input.toLowerCase().split("[^a-öA-Ö0-9]+");
		Iterator<Media> values = mediaLibrary.values();
		ArrayList<String> resultlist = new ArrayList<String>();
		int count=0;
		while (values.hasNext()){
			String ref = values.next().getTitel();
			String[] refs = ref.toLowerCase().split("[^a-öA-Ö0-9]+");
			for(int i=0; i<inputs.length; i++){
				for(int j=0; j<refs.length; j++){
					if(inputs[i].equals(refs[j])){
						count++;
					}
				}
				if(count==inputs.length){	//Alla angivna sökord har hittats i en titel
					resultlist.add(ref);
				}
			}
			count=0;
		}
		if (resultlist.isEmpty()) {
			JOptionPane.showMessageDialog(null,"No results for keyword(s): " + input); // <- Flytta till GUI?
		}
		return resultlist.toArray(new String[resultlist.size()]);
	}

	public boolean login(String userID) {
		if (userDatabase.contains(userID)){
			currentUser = userDatabase.get(userID);
			return true;
		} 
		JOptionPane.showMessageDialog(null,"User not found"); // Flytta till GUI?
		return false;
	}
	
	public boolean borrowMedia(String mediaID){
		if (mediaLibrary.contains(mediaID)){
			currentUser.borrowMedia(mediaLibrary.borrowMedia(mediaID));
			test.addLoan(mediaLibrary.get(mediaID).getTitel()); //<------- testing borrow!
			return true;
		} 
		JOptionPane.showMessageDialog(null,"Media not found"); // Flytta till GUI?
		return false;
	}
	
	public boolean returnMedia(String mediaID){
		if (currentUser.loans().contains(mediaLibrary.get(mediaID))){
			currentUser.returnMedia(mediaLibrary.returnMedia(mediaID));
			return true;
		}
		JOptionPane.showMessageDialog(null,"Loan not found for this User"); // Flytta till GUI?
		return false;
	}
}
