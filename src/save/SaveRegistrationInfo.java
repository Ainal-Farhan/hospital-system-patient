package save;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import load.LoadLoginInfo;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class SaveRegistrationInfo implements Save {
	private String latestPatientID;
	private String loginInfo;
	
	public SaveRegistrationInfo(String latestPatientID, String loginInfo) {
		this.latestPatientID = latestPatientID;
		this.loginInfo = loginInfo;
	}

	@Override
	public void save() throws IOException {
		File originalFile = new File(sourcePath + "login.txt");
		String[] patientID = LoadLoginInfo.getPatientIDs();
		String[] patientUsername = LoadLoginInfo.getUsernames();
		String[] patientPassword = LoadLoginInfo.getPasswords();
		
		File tempFile = new File("tempfile.txt");
        PrintWriter save = new PrintWriter(new FileWriter(tempFile));
        
        save.print("Latest ID: " + this.latestPatientID);
        save.flush();
        
        save.print("\nPatient ID, Username, Password");
        save.flush();
        
        for(int i = 0; i <= patientID.length; i++) {
        	if(i == patientID.length)
        		save.print("\n" + this.loginInfo); 
        	else
        		save.print("\n" + patientID[i] + "," + patientUsername[i] + "," + patientPassword[i]);
        	
            save.flush();
        }
        save.close();
        
        if (!originalFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }

        // Rename the new file to the filename the original file had.
        if (!tempFile.renameTo(originalFile))
            System.out.println("Could not rename file");
	}
	
}
