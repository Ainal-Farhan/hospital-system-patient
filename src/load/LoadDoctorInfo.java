package load;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class LoadDoctorInfo implements Load {
	private ArrayList<String> doctorInfo;
	
	public LoadDoctorInfo() {
		doctorInfo = new ArrayList<String>();
	}
	
	@Override
	public void load() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(sourcePath + "doctorInfo.txt"));
		
		String info = reader.readLine();
		
		while((info = reader.readLine()) != null) {
			doctorInfo.add(info);
		}
		reader.close();
	}
	
	public String[] getDoctorInfo() {
		return doctorInfo.toArray(new String[doctorInfo.size()]);
	}

}
