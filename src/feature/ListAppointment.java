package feature;

import java.io.IOException;

import core.AppointmentList;
import core.PatientInfo;
import gui.ListAppointmentGUI;
import gui.ProcessStatusGUI;
import save.SaveAppointmentInfo;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

public class ListAppointment extends ListAppointmentGUI{
	
	public ListAppointment() throws IOException {
		new AppointmentList();
	}
	
	public void displayGUI() {
		super.displayGUI();
	}
	
	public void start() throws IOException {
		String selectedButton = "";

		while(true) {
			System.out.println();
			if(!super.statusAppointment) {
				new ProcessStatusGUI(super.mainFrame, "There is no appointment has been created yet!");
				break;
			}
			selectedButton = getSelectedButton();
			if(selectedButton.equals("back") || selectedButton.equals("cancel appointment")) break;
		}
		
		if(selectedButton.equals("cancel appointment")) {
			SaveAppointmentInfo update = 
					new SaveAppointmentInfo(PatientInfo.getPatientID() + "," + 
					super.getCancelAppointmentInfo());
			
			super.setCancelAppointmentInfoToEmpty();
			
			update.setUpdateStatus(true);
			update.save();
			new ProcessStatusGUI(super.mainFrame, "The appointment has been cancelled!");
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
