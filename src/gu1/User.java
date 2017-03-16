package gu1;

import java.util.LinkedList;
/**
 * A class that holds the the infomation of a library borrower such a
 * name, Id, phone number and the loans of a boroower.  
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
 *
 */
public class User {
	private String id;
	private String name;
	private String phoneNr;
	private LinkedList<Media> loans;
	
	/**
	 * A constructor that takes three arguments
	 *the Id , name and the phone number of the library borrower.
	 * constructor initietes the loans of the borrower.
	 * @param id
	 * @param name
	 * @param phoneNr
	 * @param loans
	 */
	public User(String id, String name, String phoneNr) {
		this.id = id;
		this.name = name;
		this.phoneNr = phoneNr;
		this.loans = new LinkedList<Media>();
	}
	/**
	 * A method that returns the media object 
	 * and removes the object from the borrowers loan list.
	 * @return the index of the removed media.
	 */
	public Media returnMedia(Media media){
		return loans.remove(loans.indexOf(media));
	}
	/**
	 * A method that allows the borrower to borrow a media object 
	 * and adds the object to the borrowers loan list .
	 * @param media the object to be borrowed.
	 */
	public void borrowMedia(Media media){
		loans.add(media);
	}
	/**
	 * A method that returns the borrowers loan list
	 * @return the loans.
	 */
	public LinkedList<Media> loans() {
		return loans;
	}
	/**
	 * A method that returns the ID of borrower.
	 * @return Id of a borrower.
	 */
	public String getId() {
		return id;
	}
	/**
	 * A method that changes the ID of borrower.
	 * @param id the id of a specified
	 * library borrower.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * A method that returns the name of borrower.
	 * @return a name of a borrower.
	 */
	public String getName() {
		return name;
	}
	/**
	 * A method that changes the name of borrower.
	 * @param name the name of a specified
	 * library borrower.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * A method that returns the phone number of borrower.
	 * @return a phone number of a borrower.
	 */
	public String getPhoneNr() {
		return phoneNr;
	}
	/**
	 * A method that changes the phone number of borrower.
	 * @param phoneNr the phone nember
	 *  of a specified library borrower.
	 */
	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}
	/**
	 * A toString method that returns all of the available informations
	 *  a libray borrower.
	 */
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phoneNr=" + phoneNr + ", loans=" + loans.toString() + "]";
	}
}
