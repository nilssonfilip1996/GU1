package gu1;

public class User<Media> {
	private String id;
	private String name;
	private String phoneNr;
	private List<Media> myLoans;
	
	public User(String id, String name, String phoneNr) {
		this.id = id;
		this.name = name;
		this.phoneNr = phoneNr;
	}
	
	public Media returnLoan(int index){
		return myLoans.remove(index);
	}
	
	public void newLoan(Media element){
		myLoans.addLast(element);
	}
	
	public List<Media> getMyLoans() {
		return myLoans;
	}

	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNr() {
		return phoneNr;
	}

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}
	
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phoneNr=" + phoneNr + ", myLoans=" + myLoans + "]";
	}
	
}
