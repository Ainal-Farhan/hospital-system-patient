package feature;

import java.io.IOException;

import core.PatientInfo;
import gui.ProcessStatusGUI;
import gui.ProfileGUI;
import save.SavePatientInformation;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

public class Profile extends ProfileGUI{
	public void displayGUI() {
		super.displayGUI();
	}
	
	public void start() throws IOException {
		String selectedButton = "";
		String updatedInformation = "";

		while(selectedButton.equals("") || selectedButton.equals("next") || selectedButton.equals("edit")) {
			selectedButton = getSelectedButton();
			System.out.println();
		}
		
		switch(selectedButton) {
		case "update":
			updatedInformation = super.getUpdatedInformation();
			updatePatientInformation(updatedInformation);
			refreshPatientInformation(updatedInformation);
			new ProcessStatusGUI(super.mainFrame, "Successfully updated the information!");
			break;
		default:	
		}
		
		dispose();
		resetButton();
	}
	
	public void dispose() {
		super.dispose();
	}
	
	public void resetButton() {
		super.setSelectedButtonToEmpty();
	}
	
	public void refreshPatientInformation(String updatedInformation) throws IOException {
		new PatientInfo(updatedInformation.substring(0, updatedInformation.indexOf(",")));
	}
	
	public void updatePatientInformation(String updatedInformation) throws IOException {
		new SavePatientInformation(updatedInformation, super.getUpdateStatus()).save();
	}
}
