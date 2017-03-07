package gu1;

import java.util.LinkedList;

public class User {
	private String id;
	private String name;
	private String phoneNr;
	private LinkedList<Media> myLoans;
	
	public User(String id, String name, String phoneNr) {
		this.id = id;
		this.name = name;
		this.phoneNr = phoneNr;
		this.myLoans = new LinkedList<Media>();
	}
	
	public Media returnLoan(int index){
		return myLoans.remove(index);
	}
	
	public void newLoan(Media element){
		myLoans.addLast(element);
		element.setBorrowed(true);
	}
	
	public LinkedList<Media> getMyLoans() {
		return myLoans;
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
		return "User [id=" + id + ", name=" + name + ", phoneNr=" + phoneNr + ", myLoans=" + myLoans.toString() + "]";
	}
	
	public static void main(String[] args) {
//		User user = new User("1111", "333", "33");
//		Media media = new Book("4","44","44", "44");
//		user.newLoan(media);
//		user.newLoan(media);
//		System.out.print(user.myLoans.toString());
	}
}
