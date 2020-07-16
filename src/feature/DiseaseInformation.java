package feature;

import gui.DiseaseInformationGUI;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

public class DiseaseInformation extends DiseaseInformationGUI{
	public void start() {
		String selectedButton = "";
		
		while(true) {
			selectedButton = super.getSelectedButton();
			if(selectedButton.equals("back")) break;
			System.out.println();
		}
		
		super.dispose();
	}
	
	public void displayGUI() {
		super.displayGUI();
	}
}
