package feature;

import java.io.IOException;

import gui.PatientFormGUI;
import gui.ProcessStatusGUI;
import gui.RegistrationGUI;
import save.SavePatientInformation;
import save.SaveRegistrationInfo;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

public class Registration extends RegistrationGUI{
	private static PatientFormGUI patientForm;
	
	public Registration() {
		patientForm = new PatientFormGUI();
	}
	
	public void displayGUI() {
		super.displayGUI();
	}
	
	public String getSelectedButton() {
		return super.getSelectedButton().toLowerCase();
	}
	
	public void setSelectedButtonToEmpty() {
		super.setSelectedButtonToEmpty();
	}
	
	public void dispose() {
		super.dispose();
	}
	
	public boolean registrationIsFailed() {
		return super.registrationIsFailed();
	}
	
	public void saveLoginInfo() throws IOException {
		String loginInfo = super.getLoginInfo();
		String patientID = loginInfo.substring(0, loginInfo.indexOf(","));
		
		new SaveRegistrationInfo(patientID, loginInfo).save();
	}
	
	public void savePatientInfo() throws IOException {
		String patientID = super.getLoginInfo().substring(0, super.getLoginInfo().indexOf(","));
		new SavePatientInformation(patientID + "," + patientForm.getPatientInformation(), false).save();
		
		new ProcessStatusGUI(super.mainFrame, "The Registration has successfully saved!");
	}
	
	public void fillPatientForm() {
		patientForm.displayGUI();
	}
	
	public void disposePatientForm() {
		patientForm.dispose();
	}
	
	public boolean getInformationStatus() {
		return patientForm.getInformationStatus();
	}
}
