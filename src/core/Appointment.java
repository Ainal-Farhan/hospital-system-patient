package core;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

public class Appointment {
	private String appointmentLocation;
	private String prefferedDoctor;
	private String prefferedAppointmentDate;
	private String prefferedTimeSlot;
	private String message;
	private String status;
	
	public Appointment(String[] appointmentInfo) {
		appointmentLocation = appointmentInfo[0];
		prefferedDoctor = appointmentInfo[1];
		prefferedAppointmentDate = appointmentInfo[2];
		prefferedTimeSlot = appointmentInfo[3];
		message = appointmentInfo[4];
		status = appointmentInfo[5];
	}

	public String getAppointmentLocation() {
		return appointmentLocation;
	}

	public String getPrefferedDoctor() {
		return prefferedDoctor;
	}

	public String getPrefferedAppointmentDate() {
		return prefferedAppointmentDate;
	}

	public String getPrefferedTimeSlot() {
		return prefferedTimeSlot;
	}

	public String getMessage() {
		return message;
	}

	public String getStatus() {
		return status;
	}
}
