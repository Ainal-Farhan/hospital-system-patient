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

public class SavePatientInformation implements Save{
	private String patientInformation;
	private boolean updateStatus;
	
	public SavePatientInformation(String patientInformation, boolean updateStatus){
		this.patientInformation = patientInformation;
		this.updateStatus = updateStatus;
	}

	@Override
	public void save() throws IOException{    
		
		if(!updateStatus) {
			BufferedWriter save = new BufferedWriter(new FileWriter(sourcePath + "PatientInfo.txt", true));
			save.write("\n" + this.patientInformation);
			save.close();
			return;
		}
		
		File originalFile = new File(sourcePath + "PatientInfo.txt");
		BufferedReader read = new BufferedReader(new FileReader(originalFile));
		
		File tempFile = new File("tempfile.txt");
        PrintWriter update = new PrintWriter(new FileWriter(tempFile));
		
        String info = read.readLine();
        update.print(info);
        update.flush();
        
        while(!((info = read.readLine()) == null)) {
        	if((patientInformation.substring(0, patientInformation.indexOf(","))).equals(info.substring(0, info.indexOf(","))))
        		update.print("\n" + patientInformation);
        	else
        		update.print("\n" + info);
        	
        	update.flush();
        }
        read.close();
        
        if (!originalFile.delete()) {
            System.out.println("Could not delete file");
            update.close();
            return;
        }
        
        update.close();
        // Rename the new file to the filename the original file had.
        if (!tempFile.renameTo(originalFile))
            System.out.println("Could not rename file");
	}
}
