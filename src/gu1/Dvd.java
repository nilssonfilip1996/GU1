package gu1;

import java.util.Arrays;

public class Dvd extends Media{
	private String[] actor;
	
	public Dvd(String id, String titel, String year, String[] actor) {
		super(id, year, titel);
		this.actor = actor;
	}

	public String[] getActor() {
		return actor;
	}

	public void setActor(String[] actor) {
		this.actor = actor;
	}

	public String toString() {
		return "Dvd [actor=" + Arrays.toString(actor) + ", toString()=" + super.toString() + "]";
	}

	public static void main(String[] args) {
//		Dvd dvd = new Dvd("", "hej", "", "");
//		System.out.println(dvd.getYear());
//		Media media = new Dvd("", "", "", "");
//		String hej = "hej";
		
		//används i GUI kanske
//		System.out.println(media instanceof Dvd);
//		System.out.println(media instanceof Book);
	}




}
