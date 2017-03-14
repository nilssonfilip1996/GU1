package gu1;

public abstract class Media {
	private String id;
	private String year;
	private String title;

	public Media(String id, String year, String titel) {
		this.id = id;
		this.year = year;
		this.title = titel;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitel(String titel) {
		this.title = titel;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Media(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String toString() {
		return "Media [id=" + id + ", year=" + year + ", titel=" + title + "]";
	}

	public boolean equals(Object obj) {
		if (obj instanceof Media) {
			Media media = (Media) obj;
			return id.equals(media.getId());
		}
		return false;
	}
}
