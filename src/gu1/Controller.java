package gu1;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class Controller {
	private MediaLibrary<String,Media> mediaList;
	private UserDatabase<String, User> userList;
	private User currentUser;

	public Controller(String mediaListPath, String userListPath) {
		this.userList = populateUserList(userListPath);
		this.mediaList = populateMediaList(mediaListPath);
		// make loans and print user
//		this.userList.get(1).newLoan(this.mediaList.get(1));
//		this.userList.get(1).newLoan(this.mediaList.get(2));
//		this.userList.get(1).newLoan(this.mediaList.get(3));
//		System.out.println(this.userList.get(1).toString());

		// print userlist
//		for (int i = 0; i < userList.size(); i++)
//			System.out.println(userList.get(i).toString());

		// print medialist
//		for (int i = 0; i < mediaList.size(); i++)
//			System.out.println(mediaList.get(i).toString());

//		test = new GUItest(this);
//		mediaList.list();
//		mediaList.remove("The office");
//		mediaList.list();
//		mediaList.returnMedia("The office");
//		
		searchTitle();
//		mediaList.list();
//		userList.print();
//		System.out.println(userList.size());
//		System.out.println(mediaList.borrowMedia("277877"));
//		mediaList.list();
//		System.out.println(mediaList.returnMedia("277877"));
//		mediaList.list();
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
		MediaLibrary<String,Media> mediaList = new MediaLibrary<String,Media>(currentUser);
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
	
	public void searchTitle() {
		String[] inputs = (JOptionPane.showInputDialog("Skriv in sökord")).split(",");
		Iterator<Media> values = mediaList.values();
		int count=0;
		while (values.hasNext()){
			String ref = values.next().getTitel();
			String[] refs = ref.split(" ");
			for(int i=0; i<inputs.length; i++){
				for(int j=0; j<refs.length; j++){
					if(inputs[i].equals(refs[j])){
						count++;
					}
				}
				if(count==inputs.length){	//Alla angivna sökord har hittats i en titel
					System.out.println(ref);
				}
			}
			count=0;
		}
	}

//	public void checkLogIn(String id) {
//		boolean found = false;
//		for (int i = 0; i < userList.size(); i++) {
//			System.out.println(userList.get(i).getId());
//
//			if (id.equals(userList.get(i).getId())) {
//				User theUser = userList.get(i);
//				test.profileScreen(theUser, mediaList);
//				found = true;
//				break;
//			}
//		}
//		if (found == false) {
//
//			test.showError();
//		}
//
//	}

}
