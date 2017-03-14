package gu1;

import java.util.Arrays;

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
