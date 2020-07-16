package core;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

import java.io.IOException;

import load.LoadPatientInfo;

public class PatientInfo {
	private static Patient patient;
	
	public PatientInfo(String patientID) throws IOException {
		LoadPatientInfo load = new LoadPatientInfo(patientID);
		load.load();
		
		String patientInfo[] = load.getPatientInfo();
		patient = new Patient(	patientInfo[1], patientInfo[2], 
								patientInfo[3] + patientInfo[9] + patientInfo[4], 
								patientInfo[8], patientInfo[0], patientInfo[5], 
								patientInfo[6], patientInfo[7]);
	}
	
	public static String getFirstname() { 
		return patient.getFirstname();
	}
	
	public static String getLastname() { 
		return patient.getLastname();
	}
	
	public static String getIC() { 
		return patient.getIC();
	}
	
	public static String getGender() { 
		return patient.getGender();
	}
	
	public static String getEmail() {
		return patient.getEmail();
	}

	public static String getEmailProvider() {
		return patient.getEmailProvider();
	}

	public static String getPhoneNumber() {
		return patient.getPhoneNumber();
	}

	public static String getPatientID() {
		return patient.getPatientID();
	}
	
	public static String toStringPerson() {
		return patient.toStringPerson();
	}
	
	public static String toStringPatient() {
		return patient.toString();
	}
}
