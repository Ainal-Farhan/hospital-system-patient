package load;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

import core.PatientInfo;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class LoadAppointmentInfo implements Load{
	private ArrayList<String> appointmentInfo;
	
	public LoadAppointmentInfo() {
		appointmentInfo = new ArrayList<String>();
	}
	
	@Override
	public void load() throws IOException {
		BufferedReader load = new BufferedReader(new FileReader(sourcePath + "appointment.txt"));
		
		String info = load.readLine();
		
		while((info = load.readLine()) != null) {
			if(PatientInfo.getPatientID().equals(info.substring(0, info.indexOf(","))))
				appointmentInfo.add(info.substring(info.indexOf(",") + 1));
		}
		load.close();
	}
	
	public String[] getAppointmentInfo() {
		if(appointmentInfo.isEmpty())
			return null;
		
		return appointmentInfo.toArray(new String[appointmentInfo.size()]);
	}
}
