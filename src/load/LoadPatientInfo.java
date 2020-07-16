package load;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class LoadPatientInfo implements Load{
	private String[] patientInfo;
	private String patientID;
	
	public LoadPatientInfo(String patientID){
		patientInfo = new String[10];
		this.patientID = patientID;
	}
	
	public String[] getPatientInfo() {
		return patientInfo;
	}
	
	@Override
	public void load() throws IOException {
		String path = sourcePath + "PatientInfo.txt";
		BufferedReader load = new BufferedReader(new FileReader(path));
		
		String read = load.readLine();
		
		do {
			read = load.readLine();
		} while(!patientID.equals(read.substring(0, read.indexOf(","))));
		
		for(int i = 0; i < patientInfo.length; i++) {
			if(i == (patientInfo.length - 1))
				patientInfo[i] = read;
			else {
				patientInfo[i] = read.substring(0, read.indexOf(","));
				read = read.substring(read.indexOf(",") + 1);
			}
		}
		load.close();
	}

}
