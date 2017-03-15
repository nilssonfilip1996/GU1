package gu1;

/**
 * A class that holds the id, author and title of
 * a given book object. The Book class extends Media.
 * @author ?
 *
 */

public class Book extends Media{
private String author;
	/**
	 * A constructor that takes four arguments
	 * three of these, id, year and title 
	 * are sent to and later handled by the Media class.
	 * @param id
	 * @param author
	 * @param titel
	 * @param year
	 */
	public Book(String id, String author, String titel, String year) {
		super(id, year, titel);
		this.author = author;
	}
	/**
	 * A method that returns the author or authors 
	 * connected to a specific Book object.
	 * @return 											// (the author in form of a string) är osäker på om det är såhär det ska skrivas -Aron
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * A method that sets the author of a particular Book object.
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * A toString method that returns all of the available informations
	 * surrounding a given Book object.
	 */
	public String toString() {
		return "Book [id=" + this.getId() + ", author=" + author + ", titel=" + this.getTitle() + ", year=" + this.getYear() + "]";
	}
}
