package gu1;

/**
 * Media is an abstract superclass for media type classes such as Book and Dvd.
 * Media stores id-number, year of publication and title for subclasses.
 * 
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
 */
public abstract class Media {
	private String id;
	private String year;
	private String title;

	/**
	 * Constructor
	 * @param id id-number
	 * @param year publication year
	 * @param title title
	 */
	public Media(String id, String year, String titel) {
		this.id = id;
		this.year = year;
		this.title = titel;
	}

	/**
	 * Returns the year of publication
	 * @return publication year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Sets the year of publication
	 * @param year publication year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * Returns the title
	 * @return title title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title
	 * @param year publication year
	 */
	public void setTitel(String titel) {
		this.title = titel;
	}

	/**
	 * Returns the id-number
	 * @return id-number
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id-number
	 * @param id id-number
	 */
	public void setId(String id) {
		this.id = id;
	}
}
