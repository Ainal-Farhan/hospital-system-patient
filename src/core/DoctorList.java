package core;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project : Hospital System
* Date    : May 14, 2020
* 
*/

import java.io.IOException;
import java.util.ArrayList;

import load.LoadDoctorInfo;

public class DoctorList {
	private static Doctor[] doctorList;
	private LoadDoctorInfo load;
	
	public DoctorList() throws IOException {
		load = new LoadDoctorInfo();
		load.load();
		String[] doctorInfo = load.getDoctorInfo();
		
		doctorList = new Doctor[doctorInfo.length];
		
		String[] info = new String[7];
		
		for(int i = 0; i < doctorList.length; i++) {
			for(int j = 0; j < 7; j++) {
				if(j == 6)
					info[j] = doctorInfo[i];
				else
					info[j] = doctorInfo[i].substring(0, doctorInfo[i].indexOf(","));
				
				doctorInfo[i] = doctorInfo[i].substring(doctorInfo[i].indexOf(",") + 1);
			}
			doctorList[i] = new Doctor(info[0], info[1], info[2], info[3], info[4], info[5], info[6]);
		}
	}
	
	public static String[] getDoctorName() {
		String[] name = new String[doctorList.length];
		
		for(int i = 0; i < name.length; i++) {
			name[i] = "Dr. " + doctorList[i].getFirstname() + " " + doctorList[i].getLastname();
		}
		
		return name;
	}
	
	public static String[] getDoctorName(String locationSelected) {
		ArrayList<String> name = new ArrayList<String>();
		locationSelected = locationSelected.substring(17);
		
		for(int i = 0; i < doctorList.length; i++) {
			if(locationSelected.equalsIgnoreCase(doctorList[i].getLocation()))
				name.add("Dr. " + doctorList[i].getFirstname() + " " + doctorList[i].getLastname());
		}
		
		if(name.isEmpty())
			name.add("NONE");
		
		return name.toArray(new String[name.size()]);
	}
	
	public static String[] getDoctorLocation() {
		String[] location = new String[doctorList.length];
		
		for(int i = 0; i < location.length; i++) {
			location[i] = doctorList[i].getLocation();
		}
		
		return location;
	}
	
	public static String[] getDoctorExpertise() {
		String[] expertise = new String[doctorList.length];
		
		for(int i = 0; i < expertise.length; i++) {
			expertise[i] = doctorList[i].getExpertise();
		}
		
		return expertise;
	}
}
