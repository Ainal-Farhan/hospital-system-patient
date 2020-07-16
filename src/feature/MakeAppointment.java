package feature;

import java.io.IOException;

import core.AppointmentList;
import core.PatientInfo;
import gui.MakeAppointmentGUI;
import gui.ProcessStatusGUI;
import save.SaveAppointmentInfo;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

public class MakeAppointment extends MakeAppointmentGUI{
	public void displayGUI() {
		super.displayGUI();
	}
	
	public void start() throws IOException {
		String selectedButton = "";
		boolean makeAppointmentStatus = false;

		while(true) {
			System.out.println();
			selectedButton = getSelectedButton();
			
			if(selectedButton.equals("make appointment")) {
				makeAppointmentStatus = super.getMakeAppointmentStatus();
				if(makeAppointmentStatus) break;
			}
			else if(selectedButton.equals("back")) break;
		}
		
		if(makeAppointmentStatus) {
			new SaveAppointmentInfo(PatientInfo.getPatientID() + super.getAppointmentInfo()).save();
			new ProcessStatusGUI(super.mainFrame, "Appointment Info has been saved!");
			new AppointmentList();
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
}
