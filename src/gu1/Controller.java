package gu1;

import java.io.*;
import java.util.LinkedList;

public class Controller {
	LinkedList<Media> mediaList;
	LinkedList<User> userList;

	public Controller(String mediaListPath, String userListPath) throws IOException {
		populateUserList(userListPath);
		populateMediaList(mediaListPath);
		
		//make loans and print user
		this.userList.get(1).newLoan(this.mediaList.get(1));
		this.userList.get(1).newLoan(this.mediaList.get(2));
		this.userList.get(1).newLoan(this.mediaList.get(3));
		System.out.println(this.userList.get(1).toString());
		
		//print userlist
		for(int i=0; i<userList.size(); i++)
			System.out.println(userList.get(i).toString());
		
		
		//print medialist
		for(int i=0; i<mediaList.size(); i++)
			System.out.println(mediaList.get(i).toString());
	}

	public void populateUserList(String userListPath) throws IOException {
		LinkedList<User> userList = new LinkedList<User>();
		FileInputStream fis = new FileInputStream(userListPath);
		InputStreamReader isr = new InputStreamReader(fis, "ISO-8859-1");
		BufferedReader reader = new BufferedReader(isr);

		String str;
		String[] values;
		User user;
		while ((str = reader.readLine()) != null) {
			values = str.split(";");
			user = new User(values[0], values[1], values[2]);
			userList.add(user);
		}
		reader.close();
		this.userList = userList;
	}

	public void populateMediaList(String mediaListPath) throws IOException {
		LinkedList<Media> mediaList = new LinkedList<Media>();
		FileInputStream fis = new FileInputStream(mediaListPath);
		InputStreamReader isr = new InputStreamReader(fis, "ISO-8859-1");
		BufferedReader reader = new BufferedReader(isr);

		String str;
		String[] values;
		Media media;
		while ((str = reader.readLine()) != null) {
			values = str.split(";");
			if (values[0].equals("Dvd")) {
				String[] actors = new String[values.length - 4];
				for (int i = 0; i < actors.length; i++)
					actors[i] = values[i + 4];
				media = new Dvd(values[1], values[2], values[3], actors);
				mediaList.add(media);
			} else if (values[0].equals("Bok")) {
				media = new Book(values[1], values[2], values[3], values[4]);
				mediaList.add(media);
			}
		}
		reader.close();
		this.mediaList = mediaList;
	}

}
