package feature;

import java.io.IOException;

import gui.MenuListGUI;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

public class MenuList extends MenuListGUI{
	Object content;
	
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
	
	public void displayContentGUI(String content) throws IOException {
		switch(content) {
		case "profile":
			this.content = new Profile();
			((Profile)this.content).displayGUI();
			break;
		case "make appointment":
			this.content = new MakeAppointment();
			((MakeAppointment)this.content).displayGUI();
			break;
		case "list appointment":
			this.content = new ListAppointment();
			((ListAppointment)this.content).displayGUI();
			break;
		case "disease information":
			this.content = new DiseaseInformation();
			((DiseaseInformation)this.content).displayGUI();
			break;
		}
	}
	
	public void start() throws IOException {
		if(this.content instanceof Profile) {
			((Profile)this.content).start();
		}
		else if(this.content instanceof MakeAppointment) {
			((MakeAppointment)this.content).start();
		}
		else if(this.content instanceof ListAppointment) {
			((ListAppointment)this.content).start();
		}
		else if(this.content instanceof DiseaseInformation) {
			((DiseaseInformation)this.content).start();
		}	
	}
}
