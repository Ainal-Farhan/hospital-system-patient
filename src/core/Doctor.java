package core;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

public class Doctor extends Person {
	private String doctorID;
	private String location;
	private String expertise;
	
	public Doctor(String doctorID, String firstname, String lastname, String gender, String NRIC, String location, String expertise) {
		super(firstname, lastname, NRIC, gender);
		this.doctorID = doctorID;
		this.location = location;
		this.expertise = expertise;
	}

	public String getDoctorID() {
		return doctorID;
	}

	public String getLocation() {
		return location;
	}

	public String getExpertise() {
		return expertise;
	}
}
