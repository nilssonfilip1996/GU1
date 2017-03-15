package gu1;

import java.util.Arrays;
/**
 * A class that holds the id, author, title and year of publication
 * of a given Dvd object. The Dvd class extends Media.
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
 *
 */
public class Dvd extends Media{
	private String[] actors;
	/**
	 * A constructor that takes four arguments
	 * three of these, id, year and title 
	 * are sent to, and later handled by, the Media class.
	 * @param id
	 * @param titel
	 * @param year
	 * @param actors
	 */
	public Dvd(String id, String titel, String year, String[] actors) {
		super(id, year, titel);
		this.actors = actors;
	}
	/**
	 * getActor returns the actors connected to the current Dvd object.
	 * @return a String array of the actors connected to the current Dvd object.
	 */
	public String[] getActor() {
		return actors;
	}
	/**
	 * A method that sets the actors of a particular Dvd object.
	 * @param actors
	 */
	public void setActor(String[] actors) {
		this.actors = actors;
	}
	/**
	 * A toString method that returns all of the available informations
	 * surrounding a given Dvd object.
	 */
	public String toString() {
		return "Dvd [id=" + this.getId() + ", titel=" + this.getTitle() + ", year=" + this.getYear() + ", actors=" + Arrays.toString(actors) + "]";
	}
}
