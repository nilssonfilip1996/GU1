package gu1;

import java.io.*;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Controller {
	private MediaLibrary<String,Media> mediaList;
	LinkedList<User> userList;
	private GUItest test;

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
		mediaList.list();
		mediaList.remove("The office");
		mediaList.list();
		mediaList.returnMedia("The office");
		
		mediaList.list();
		
	}

	public LinkedList<User> populateUserList(String userListPath) {
		LinkedList<User> userList = new LinkedList<User>();
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(userListPath), "ISO-8859-1"));
			String str;
			String[] values;
			while ((str = reader.readLine()) != null) {
				values = str.split(";");
				userList.add(new User(values[0], values[1], values[2]));
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
					new InputStreamReader(new FileInputStream(mediaListPath), "ISO-8859-1"));
			String str;
			String[] values;
			while ((str = reader.readLine()) != null) {
				values = str.split(";");
				if (values[0].equals("Dvd")) {
					String[] actors = new String[values.length - 4];
					for (int i = 0; i < actors.length; i++)
						actors[i] = values[i + 4];
					mediaList.put(values[2],new Dvd(values[1], values[2], values[3], actors));
				} else if (values[0].equals("Bok")) {
					mediaList.put(values[3],new Book(values[1], values[2], values[3], values[4]));
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return mediaList;
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
