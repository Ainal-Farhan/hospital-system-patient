package core;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

public class Patient extends Person {
	private String patientID;
	private String email;
	private String emailProvider;
	private String phoneNumber;
	
	public Patient() {}
	
	public Patient(String fn, String ln, String icNo, String g, String id, String e, String eP,String pN){
		super(fn, ln, icNo, g);
		patientID = id;
		email = e;
		emailProvider = eP;
		phoneNumber = pN;
	}

	public String getEmail() {
		return email;
	}

	public String getEmailProvider() {
		return emailProvider;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPatientID() {
		return patientID;
	}
	
	public String toString() {
		return patientID + "," + super.toString() + "," + email + "," + emailProvider + "," + phoneNumber + "," + super.getGender() + "," + super.getIC().substring(6, 8);
	}
	
	protected String toStringPerson() {
		return getFirstname() + "," + getLastname() + "," + getIC().substring(0, 6) + "," + getIC().substring(8, 12);
	}
}
