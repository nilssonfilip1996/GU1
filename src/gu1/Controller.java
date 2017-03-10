package gu1;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class Controller {
	private MediaLibrary<String,Media> mediaList;
	private UserDatabase<String,User> userList;
	private User currentUser;

	public Controller(String mediaListPath, String userListPath) {
		this.userList = populateUserList(userListPath);
		this.mediaList = populateMediaList(mediaListPath);

//		//Login and loan test
//		login(JOptionPane.showInputDialog("Login with ID"));
//		borrowMedia(JOptionPane.showInputDialog("Enter Media ID to borrow"));
//		userList.print();
//		returnMedia(JOptionPane.showInputDialog("Enter Media ID to return"));
//		userList.print();
		
		//SearchTest
		for (String s : searchTitle(JOptionPane.showInputDialog("Search"))){
			System.out.println(s);
		}
	}

	public UserDatabase<String, User> populateUserList(String userListPath) {
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

	public MediaLibrary<String,Media> populateMediaList(String mediaListPath) {
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
	
	public String[] searchTitle(String input) {
		String[] inputs = input.toLowerCase().split("[^a-öA-Ö0-9]+");
		Iterator<Media> values = mediaList.values();
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
			JOptionPane.showMessageDialog(null,"No Media found"); // <- Flytta till GUI?
		}
		return resultlist.toArray(new String[resultlist.size()]);
	}

	public boolean login(String userID) {
		if (userList.contains(userID)){
			currentUser = userList.get(userID);
			return true;
		} 
		JOptionPane.showMessageDialog(null,"User not found"); // Flytta till GUI?
		return false;
	}
	
	public boolean borrowMedia(String mediaID){
		if (mediaList.contains(mediaID)){
			mediaList.borrowMedia(mediaID);
			currentUser.borrowMedia(mediaList.get(mediaID));
			return true;
		} 
		JOptionPane.showMessageDialog(null,"Media not found"); // Flytta till GUI?
		return false;
	}
	
	public boolean returnMedia(String mediaID){
		if (currentUser.loans().contains(mediaList.get(mediaID))){
			mediaList.returnMedia(mediaID);
			currentUser.returnMedia(mediaList.get(mediaID));
			return true;
		}
		JOptionPane.showMessageDialog(null,"User does not have this Media on loan"); // Flytta till GUI?
		return false;
	}
}
