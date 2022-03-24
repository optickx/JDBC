package code;


public class Owner {
	
	private final String ID;
	private String name;
	private String telephone;
	
	// Constructor (ID is obligatory and final)
	public Owner(String pID,String pName, String pTelephone) {
		ID = pID;
		name = pName;
		telephone = pTelephone;
	}

	public Owner(String pID) {
		ID = pID;
	}
	
	// Getters.
	public String getID() {
		return ID;
	}
	public String pName() {
		return name;
	}
	public String getTelephone() {
		return telephone;
	}
	
	// Setters.
	public void setName(String pName) {
		name = pName;
	}
	public void setTelephone(String pTelephone) {
		telephone = pTelephone;
	}

	@Override
	public String toString() {
		return "ID is " + ID + ", the owner is called " + name + 
		" and the telephone is " + telephone + ".";
	}
	
	
}
