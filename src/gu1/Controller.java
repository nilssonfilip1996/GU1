package gu1;

import java.io.*;

import java.util.LinkedList;

public class Controller {
	LinkedList<Media> mediaList;
	LinkedList<User> userList;

	public Controller(String mediaList, String userList) throws IOException {
		populateUser(userList);
		populateMedia(mediaList);
	}

	public void populateUser(String userlistPath) throws IOException {
		LinkedList<User> userList = new LinkedList<User>();
		FileInputStream fis = new FileInputStream(userlistPath);
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

	public void populateMedia(String medialistPath) throws IOException {
		LinkedList<Media> mediaList = new LinkedList<Media>();
		FileInputStream fis = new FileInputStream(medialistPath);
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
		System.out.println(mediaList.get(7).toString());
		System.out.println(mediaList.get(1).toString());
		System.out.println(mediaList.get(23).toString());
		System.out.println(mediaList.get(10).toString());
	}

}
