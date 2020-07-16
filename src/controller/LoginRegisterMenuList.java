package controller;

import java.io.IOException;

import core.AppointmentList;
import core.DoctorList;
import core.Hospital;
import feature.Login;
import feature.MenuList;
import feature.Registration;

/**
 * @author Mohd Ainal Farhan Mohamad Johari
 * 
 * Project : Hospital System
 * Date    : May 14, 2020
 * 
 */

public class LoginRegisterMenuList {
	public LoginRegisterMenuList() throws IOException {
		Login login = new Login();
		Registration registration = new Registration();
		MenuList menuList = new MenuList();
		
		String selectedButton = "back to login menu";
		
		do {
			login.loadLoginInfo();
			login.displayGUI();
			selectedButton = "";
			while(login.loginIsFailed() && !selectedButton.equals("register")) {
				selectedButton = login.getSelectedButton();
			}
			
			login.setSelectedButtonToEmpty();
			login.dispose();
			
			switch(selectedButton){
			case "login":
				login.setPatientID();
				login.loadPatientInfo();
				new Hospital().loadAppointmentTimeSlot();
				new Hospital().loadHospitalLocation();
				new DoctorList();
				new AppointmentList();
				break;
			case "register":
				registration.displayGUI();
				selectedButton = "";
				while(registration.registrationIsFailed() && !selectedButton.equals("back to login menu")) {
					selectedButton = registration.getSelectedButton();
				}registration.dispose();
				if(selectedButton.equals("register")) {
					registration.fillPatientForm();
					while(!registration.getInformationStatus()) {
						System.out.println();
					}
					registration.disposePatientForm();
					registration.saveLoginInfo();
					registration.savePatientInfo();
					selectedButton = "back to login menu";
				}
				registration.setSelectedButtonToEmpty();
				break;
			}
			
		} while(selectedButton.equals("back to login menu"));
		
		menuList.displayGUI();
		while(true) {	
			selectedButton = "";
			while(selectedButton.equals("")) {
				selectedButton = menuList.getSelectedButton();
			}
			menuList.dispose();
			
			switch(selectedButton) {
			case "exit":
				System.exit(1);
			default:
				menuList.displayContentGUI(selectedButton);
				menuList.start();
			}
			
			menuList.setSelectedButtonToEmpty();
			menuList.displayGUI();
		}
	}
}
