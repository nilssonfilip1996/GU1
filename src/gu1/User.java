package gu1;

import java.util.LinkedList;
import java.util.Queue;

public class User {
	private String id;
	private String name;
	private String phoneNr;
	private LinkedList<Media> loans;
	//private Queue<Media> loans2;				//Ändra till kö så att de lagras i tidsordning?
	
	public User(String id, String name, String phoneNr) {
		this.id = id;
		this.name = name;
		this.phoneNr = phoneNr;
		this.loans = new LinkedList<Media>();
	}
	
	public Media returnMedia(Media media){
		return loans.remove(loans.indexOf(media));
	}
	
	public void borrowMedia(Media media){
		loans.add(media);
	}
	
	public LinkedList<Media> loans() {
		return loans;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNr() {
		return phoneNr;
	}

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}
	
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phoneNr=" + phoneNr + ", loans=" + loans.toString() + "]";
	}
}
