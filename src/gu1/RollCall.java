package gu1;

public class RollCall {

	public static void rollCall(){
		String[] members = {"Silvester", "?", "?", "?", "?"};
		for (String member : members){
			System.out.println(member + " here!");
		}
	}

	public static void main(String[] args) {
		rollCall();
	}
}