package core;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

import java.io.IOException;

import load.LoadAppointmentInfo;

public class AppointmentList {
	private static Appointment[] appointmentList;
	
	public AppointmentList() throws IOException {
		LoadAppointmentInfo load = new LoadAppointmentInfo();
		load.load();
		
		if(load.getAppointmentInfo() == null) {
			appointmentList = new Appointment[] {
				new Appointment(new String[] {"none", "none", "none", "none", "none", "none"})	
			};
			return;
		}
		
		String[] appointmentInfo = load.getAppointmentInfo();
		appointmentList = new Appointment[appointmentInfo.length];
		
		String[] info = new String[6];
		
		for(int i = 0; i < appointmentList.length; i++) {
			for(int j = 0; j < info.length; j++) {
				if( j != info.length - 1) {
					info[j] = appointmentInfo[i].substring(0, appointmentInfo[i].indexOf(","));
					appointmentInfo[i] = appointmentInfo[i].substring(appointmentInfo[i].indexOf(",") +1);
				}
					
				else
					info[j] = appointmentInfo[i];
			}
			appointmentList[i] = new Appointment(info);
		}
	}
	
	public static Appointment[] getAppointmentList() {
		return appointmentList;
	}
	
	public static String getAppointmentLocation(int i) {		
		return appointmentList[i].getAppointmentLocation();
	}

	public static String getPrefferedDoctor(int i) {		
		return appointmentList[i].getPrefferedDoctor();
	}

	public static String getPrefferedAppointmentDate(int i) {
		return appointmentList[i].getPrefferedAppointmentDate();
	}

	public static String getPrefferedTimeSlot(int i) {
		return appointmentList[i].getPrefferedTimeSlot();
	}

	public static String getMessage(int i) {		
		return appointmentList[i].getMessage();
	}
	
	public static String getStatus(int i) {
		return appointmentList[i].getStatus();
	}

	public static String[] getStatus() {
		String[] status = new String[appointmentList.length];
		
		for(int i = 0; i < appointmentList.length; i++) {
			status[i] = appointmentList[i].getStatus();
		}
		
		return status;
	}
}
