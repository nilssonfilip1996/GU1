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
		return "Book [id=" + this.getId() + ", author=" + author + ", titel=" + this.getTitle() + ", year=" + this.getYear() + "]";
	}
}
