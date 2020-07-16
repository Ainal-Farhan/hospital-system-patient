package save;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class SaveAppointmentInfo implements Save{
	String appointmentInfo;
	boolean cancelStatus;
	
	public SaveAppointmentInfo(String appointmentInfo) {
		this.appointmentInfo = appointmentInfo;
		this.cancelStatus = false;
	}
	
	@Override
	public void save() throws IOException {
		if(!cancelStatus) {
			BufferedWriter save = new BufferedWriter(new FileWriter(sourcePath + "appointment.txt", true));
			save.write("\n" + appointmentInfo);
			save.close();
			return;
		}
		File originalFile = new File(sourcePath + "appointment.txt");
		File tempFile = new File(sourcePath + "tempFile.txt");
		
		BufferedReader reader =  new BufferedReader(new FileReader(originalFile));
		PrintWriter save = new PrintWriter(new FileWriter(tempFile));
		
		String info = reader.readLine();
		save.print(info);
		save.flush();
		
		while((info = reader.readLine()) != null) {
			if(info.indexOf(appointmentInfo) != -1)
				continue;
			save.print("\n" + info);
			save.flush();
		}
		
		reader.close();
		
		if (!originalFile.delete()) {
            System.out.println("Could not delete file");
            save.close();
            return;
        }
		
		save.close();
        // Rename the new file to the filename the original file had.
        if (!tempFile.renameTo(originalFile))
            System.out.println("Could not rename file");
        
	}
	
	public void setUpdateStatus(boolean status) {
		cancelStatus = status;
	}
}
