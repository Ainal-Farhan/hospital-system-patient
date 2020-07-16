package load;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class LoadLoginInfo implements Load {
	private static ArrayList<String> patientID = new ArrayList<String>();
	private static ArrayList<String> username = new ArrayList<String>();
	private static ArrayList<String> password = new ArrayList<String>();
	private static String patientLatestID = "P000";
	
	public LoadLoginInfo() {}
	
	public void setPatientLatestID(String id) {
		patientLatestID = id;
	}
	
	private void addPatientID(String ID) {
		patientID.add(ID);
	}
	
	private void addUsername(String patientUsername) {
		username.add(patientUsername);
	}
	
	private void addPassword(String patientPassword) {
		password.add(patientPassword);
	}
	
	protected void clearAllInformation() {
		patientID.clear();
		username.clear();
		password.clear();
	}

	public static String getLatestPatientID() {
		return patientLatestID;
	}
	
	public static String[] getPatientIDs() {
		return patientID.toArray(new String[patientID.size()]);
	}
	
	public static String[] getUsernames() {
		return username.toArray(new String[username.size()]);
	}
	
	public static String[] getPasswords() {
		return password.toArray(new String[password.size()]);
	}
	
	@Override
	public void load() throws IOException {
		
		if(getPatientIDs() != null)
			clearAllInformation();
		
		String path = sourcePath + "login.txt";
		
		@SuppressWarnings("resource")
		BufferedReader load = new BufferedReader(new FileReader(path));
		
		String info = load.readLine();
		setPatientLatestID(info.substring(info.indexOf(':') + 2));
		
		info = load.readLine();
		
		for(; (info = load.readLine()) != null;) {
			addPatientID(info.substring(0, info.indexOf(',')));
			info = info.substring(info.indexOf(',') + 1);
			addUsername(info.substring(0, info.indexOf(',')));
			addPassword(info.substring(info.indexOf(',') + 1, info.length()));
		}
		
		load.close();
	}
}
