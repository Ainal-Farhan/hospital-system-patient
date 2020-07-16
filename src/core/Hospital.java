package core;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

import java.io.IOException;

import load.LoadAppointmentTimeSlot;
import load.LoadHospitalLocation;

public class Hospital {
	private LoadHospitalLocation location;
	private LoadAppointmentTimeSlot timeSlot;
	
	private static String[] hospitalLocation;
	private static String[] appointmentTimeSlot;
	
	public Hospital() {
		location =  new LoadHospitalLocation();
		timeSlot =  new LoadAppointmentTimeSlot();
	}
	
	public void loadHospitalLocation() throws IOException {
		location.load();
		Hospital.hospitalLocation = location.getHospitalLocation();
	}
	
	public void loadAppointmentTimeSlot() throws IOException {
		timeSlot.load();
		Hospital.appointmentTimeSlot = timeSlot.getAppointmentTimeSlot();
	}

	public static String[] getHospitalLocation() {
		return hospitalLocation;
	}
	
	public static String[] getAppointmentTimeSlot() {
		return appointmentTimeSlot;
	}
}
