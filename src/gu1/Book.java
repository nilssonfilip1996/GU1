package gu1;

public class Book extends Media{
private String author;
	
	public Book(String id, String author, String titel, String year) {
		super(id, year, titel);
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	
	public String toString() {
		return "Book [author=" + author + ", toString()=" + super.toString() + "]";
	}

	public static void main(String[] args) {
//		Dvd dvd = new Dvd("", "hej", "", "");
//		System.out.println(dvd.getYear());
//		Media media = new Dvd("", "", "", "");
		
		//används i GUI kanske
//		System.out.println(media instanceof Dvd);
//		System.out.println(media instanceof Book);
	}

}
