package load;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class LoadAppointmentTimeSlot implements Load{
	private Vector<String> appointmentTimeSlot;
	
	public LoadAppointmentTimeSlot() {
		appointmentTimeSlot = new Vector<String>();
	}
	
	@Override
	public void load() throws IOException {
		String path = sourcePath + "appointmentTimeSlot.txt";
		
		BufferedReader reader = new BufferedReader(new FileReader(path));
		
		String info;
		while((info = reader.readLine()) != null) {
			appointmentTimeSlot.addElement(info);
		}
		
		reader.close();
	}
	
	public String[] getAppointmentTimeSlot() {
		return appointmentTimeSlot.toArray(new String[appointmentTimeSlot.size()]);
	}
}
