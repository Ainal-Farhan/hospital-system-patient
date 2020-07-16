package verify;

import load.LoadLoginInfo;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class VerifyInfo extends LoadLoginInfo implements Verification{
	private String username;
	private String password;
	private String patientID;
	
	public VerifyInfo() {}
	
	public boolean verifyLoginDetails(String username, String password) {
		this.username = username;
		this.password = password;
		this.patientID = "";
		return verify();
	}
	
	public String getPatientID() {
		return this.patientID;
	}
	
	//To check either the username already taken or not
	public boolean verifyUsername(String name) {
		String[] username = getUsernames();
		
		for(int i = 0; i < username.length; i++) {
			if(username[i].equals(name))
				return false;
		}
		return true;
	}

	@Override
	public boolean verify() {
		String[] patientID = getPatientIDs();
		String[] password = getPasswords();
		String[] username = getUsernames();
		
		for(int i = 0; i < patientID.length; i++) {
			if(username[i].equals(this.username)) {
				if(password[i].equals(this.password)) {
					this.patientID = patientID[i];
					return true;
				}
			}
		}
		return false;
	}
	
}
