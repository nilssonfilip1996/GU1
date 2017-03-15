package gu1;

import java.util.Arrays;
/**
 * A class that holds the id, author and title of
 * a given book object. The Book class extends Media.
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
 *
 */
public class Dvd extends Media{
	private String[] actors;
	
	public Dvd(String id, String titel, String year, String[] actors) {
		super(id, year, titel);
		this.actors = actors;
	}

	public String[] getActor() {
		return actors;
	}

	public void setActor(String[] actors) {
		this.actors = actors;
	}

	public String toString() {
		return "Dvd [id=" + this.getId() + ", titel=" + this.getTitle() + ", year=" + this.getYear() + ", actors=" + Arrays.toString(actors) + "]";
	}
}
