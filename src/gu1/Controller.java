package gu1;

import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * The Controller class handles the core logic. 
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
 *
 */
public class Controller {
	private MediaLibrary mediaLibrary;
	private BinarySearchTree<String, User> userList;
	private User currentUser;
	private MediaViewer mainWindow;
	
	/**
	 * This constructor takes two strings as its arguments. These strings in
	 * turn correspond to names of text files. The constructor uses these
	 * files to populate the UserDatabase and MediaLibrary. Also, an instance 
	 * of the MediaViewer is created. This enables the controller to draw the
	 * appropriate panels later on during the execution of the code.
	 * @param mediaListPath
	 * @param userListPath
	 */
	public Controller(String mediaListPath, String userListPath) {
		this.userList = populateUserDatabase(userListPath);
		this.mediaLibrary = populateMediaLibrary(mediaListPath);
		this.mainWindow = new MediaViewer(this);
	}
	
	/**
	 * The method populates an instance of BinarySearchTree with the current users.							
	 * @param userListPath is a String containing the name of a userList file.
	 * @return BinarySearchTree<String, User> an implementation of a search tree 
	 * populated with the user information found in the document specified by the userListPath.
	 */
	public BinarySearchTree<String, User> populateUserDatabase(String userListPath) {
		BinarySearchTree<String, User> userList = new BinarySearchTree<String, User>();
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
	
	/**
	 * The method populates an instance of MediaLibrary with the current media objects.	
	 * @param mediaListPath
	 * @return MediaLibrary containing the current media objects
	 */
	public MediaLibrary populateMediaLibrary(String mediaListPath) {
		MediaLibrary mediaList = new MediaLibrary();
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
					mediaList.put(values[1], new Dvd(values[1], values[2], values[3], actors));
				} else if (values[0].equals("Bok")) {
					mediaList.put(values[1], new Book(values[1], values[2], values[3], values[4]));
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return mediaList;
	}
	
	/**
	 * The method populates a Media array with the available media.
	 * @return a Media array with the found media.
	 */
	public String[] populateAvailableMediaList() {
		Iterator<Media> values = mediaLibrary.availableMedia();
		String[] mediaList = new String[mediaLibrary.size()];
		int index = 0;
		while (values.hasNext()) {	
			Media temp = values.next();
			if(!temp.isBorrowed()){
				mediaList[index] = (temp.getId() + ", " + temp.getTitle());
				index++;
			}
		}
		return mediaList;
	}
	
	/**
	 * The method populates a Media array with the current loaned media.
	 * @return a Media array with the loaned list.
	 */
	public String[] populateCurrentUserLoanList() {
		Iterator<Media> values = currentUser.loans().iterator();
		String[] loanList = new String[currentUser.loans().size()];
		int index = 0;
		while (values.hasNext()) {
			Media temp = values.next();
			loanList[index] = (temp.getId() + ", " + temp.getTitle());
			index++;
		}
		return loanList;
	}
	
	/**
	 * This method takes a string as its input and searches the available media objects
	 * to find a match. If a match is found
	 * @param input - the piece of information used to search through the media objects.
	 * @return A string array containing the information about a certain type of media
	 */
	public String[] searchTitle(String input) {
		String[] inputs = input.toLowerCase().split("[^a-öA-Ö0-9]+");
		Iterator<Media> values = mediaLibrary.availableMedia();
		ArrayList<String> foundIdlist = new ArrayList<String>();
		int count = 0;
		while (values.hasNext()) {
			Media media = values.next();
			String ref = media.getTitle();
			String id = media.getId();
			String[] refs = ref.toLowerCase().split("[^a-öA-Ö0-9]+");
			for (int i = 0; i < inputs.length; i++) {
				for (int j = 0; j < refs.length; j++) {
					if (inputs[i].equals(refs[j])) {
						count++;
					}
				}
				if (count == inputs.length) {
					foundIdlist.add(id);
				}
			}
			count = 0;
		}
		return foundIdlist.toArray(new String[foundIdlist.size()]);
	}
	
	/**
	 * This method handles the login.
	 * @param userID is a string containing an userID
	 * @return either true or false depending on 
	 * if the login was successful or not.
	 */
	public boolean login(String userID) {
		if (userList.contains(userID)) {
			currentUser = userList.get(userID);
			loginToLibraryPanel(); 
			return true;
		}
		JOptionPane.showMessageDialog(null, "User not found");														
		return false;
	}

	private void loginToLibraryPanel() {
		mainWindow.loginToLibraryPanel(currentUser.getName());
		mainWindow.updateMediaLists(populateAvailableMediaList(), populateCurrentUserLoanList());
	}
	
	/**
	 * The logOut method, as the name implies, 
	 * handles loging out from the system.
	 */
	public void logOut() {
		currentUser = null;
		mainWindow.libraryToLoginPanel();
	}
	
	/**
	 * This method borrows a media object if 
	 * the given mediaID exists in the current mediaLibrary 
	 * @param mediaID is a String containing an ID
	 * @return a boolean depending on the the borrow was successful or not.
	 */
	public boolean borrowMedia(String mediaID) {
		if (mediaLibrary.contains(mediaID)) {
			currentUser.borrowMedia(mediaLibrary.get(mediaID));
			mediaLibrary.get(mediaID).setBorrowed(true);
			mainWindow.updateMediaLists(populateAvailableMediaList(), populateCurrentUserLoanList());
			return true;
		}
		return false;
	}
	
	/**
	 * This method returns a media object if 
	 * the given mediaID exists in the current users list
	 * of loans. 
	 * @param mediaID
	 * @return a boolean depending on the the return was successful or not.
	 */
	public boolean returnMedia(String mediaID) {
		if (currentUser.loans().contains(mediaLibrary.get(mediaID))) {
			currentUser.returnMedia(mediaLibrary.get(mediaID));
			mediaLibrary.get(mediaID).setBorrowed(false);
			mainWindow.updateMediaLists(populateAvailableMediaList(), populateCurrentUserLoanList());
			return true;
		}
		return false;
	}
	
	/**
	 * This method shows the media objects found during 
	 * a search. 
	 * @param key is a String array containing the IDs 
	 * of the media objects to be displayed in the main window.
	 */
	public void showMultipleMediaInfo(String[] key) {
		StringBuilder sb = new StringBuilder();
		sb.append("Search Results: \n\n");
		for (int i = 0; i < key.length; i++) {
			Media theMedia = mediaLibrary.get(key[i]);
			if (theMedia instanceof Dvd) {
				Dvd dvd = (Dvd) theMedia;
				sb.append("Title: " + dvd.getTitle() + "\n");
				sb.append("Id: " + dvd.getId() + "\n");
				sb.append("Year: " + dvd.getYear() + "\n");
				String[] actors = dvd.getActor();
				sb.append("Actor(s): ");
				for (int j = 0; j < actors.length; j++) {
					sb.append(actors[j]);
					if (j < actors.length - 1) {
						sb.append(", ");
					}
				}
			} else if (theMedia instanceof Book) {
				Book book = (Book) theMedia;
				sb.append("Title: " + book.getTitle() + "\n");
				sb.append("Id: " + book.getId() + "\n");
				sb.append("Year: " + book.getYear() + "\n");
				sb.append("Author(s): " + book.getAuthor());
			}
			sb.append("\n" + "-----------------------------------------" + "\n");
		}
		mainWindow.updateMediaInfoField(sb.toString());
	}
	
	/**
	 * This method sends the appropriate information connected to a certain media object
	 * to the main window. There it is later displayed to the user.
	 */
	public void showSingleMediaInfo(String key) {
		Media theMedia = mediaLibrary.get(key);
		if (theMedia instanceof Dvd) {
			Dvd dvd = (Dvd) theMedia;
			StringBuilder sb = new StringBuilder();
			sb.append("Title: " + dvd.getTitle() + "\n");
			sb.append("Id: " + dvd.getId() + "\n");
			sb.append("Year: " + dvd.getYear() + "\n");
			String[] actors = dvd.getActor();
			sb.append("Actor(s): ");
			for (int i = 0; i < actors.length; i++) {
				sb.append(actors[i]);
				if (i < actors.length - 1) {
					sb.append(", ");
				}
			}
			mainWindow.updateMediaInfoField(sb.toString());
		} else if (theMedia instanceof Book) {
			Book book = (Book) theMedia;
			StringBuilder sb = new StringBuilder();
			sb.append("Title: " + book.getTitle() + "\n");
			sb.append("Id: " + book.getId() + "\n");
			sb.append("Year: " + book.getYear() + "\n");
			sb.append("Author(s): " + book.getAuthor());
			mainWindow.updateMediaInfoField(sb.toString());
		}
	}
	
	/**
	 * This method searches the unloaned media objects and
	 * either returns the information connected to an object
	 * or a message that the keyword has no result.
	 * @param str represents a keyword that is used for searching the library.
	 */
	public void showFoundMedia(String str) {
		String[] keyword = searchTitle(str);
		if (keyword.length == 0) {
			mainWindow.updateMediaInfoField("Sorry, no results for the keyword: " + str);
		} else {
			showMultipleMediaInfo(keyword);
		}
	}
}
