package feature;

import java.io.IOException;

import core.PatientInfo;
import gui.LoginGUI;
import load.LoadLoginInfo;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

public class Login extends LoginGUI{
	private String patientID;
	
	public void loadLoginInfo() throws IOException {
		new LoadLoginInfo().load();
	}
	
	public void displayGUI() {
		super.displayGUI();
	}
	
	public String getSelectedButton() {
		return super.getSelectedButton().toLowerCase();
	}
	
	public void dispose() {
		super.dispose();
	}
	
	public void setSelectedButtonToEmpty() {
		super.setSelectedButtonToEmpty();
	}
	
	public boolean loginIsFailed() {
		return super.loginIsFailed();
	}
	
	public void setPatientID() {
		patientID = super.getPatientID();
	}
	
	public void loadPatientInfo() throws IOException {
		new PatientInfo(patientID);
	}
	
	public String getPatientID() {
		return patientID;
	}
}
