package gu1;

public abstract class Media {
	private String id;
	private String year;
	private String titel;
	private boolean isBorrowed;

	public Media(String id, String year, String titel) {
		this.id = id;
		this.year = year;
		this.titel = titel;
	}

	public boolean isBorrowed() {
		return isBorrowed;
	}

	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
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
		return "Media [id=" + id + ", year=" + year + ", titel=" + titel + "]";
	}

	public boolean equals(Object obj) {
		if (obj instanceof Media) {
			Media media = (Media) obj;
			return id.equals(media.getId());
		}
		return false;
	}
}
