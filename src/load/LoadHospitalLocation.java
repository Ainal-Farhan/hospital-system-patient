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

public class LoadHospitalLocation implements Load{
	private Vector<String> hospitalLocation;
	private String hospitalName;
	
	public LoadHospitalLocation() {
		hospitalLocation = new Vector<String>();
		hospitalName = "";
	}
	
	@Override
	public void load() throws IOException {
		String path = sourcePath + "HospitalLocation.txt";
		
		BufferedReader reader = new BufferedReader(new FileReader(path));
		
		String info = reader.readLine();
		hospitalName = info.substring(info.indexOf(":") + 1);
		
		while((info = reader.readLine()) != null) {
			hospitalLocation.addElement(hospitalName + " " + info);
		}
		
		reader.close();
	}
	
	public String[] getHospitalLocation() {
		return hospitalLocation.toArray(new String[hospitalLocation.size()]);
	}

}
