package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import core.AppointmentList;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class ListAppointmentEvent implements ActionListener{
	private String selectedButton;
	private JLabel[] appointmentInfo;
	private JButton cancelButton;
	private String cancelAppointmentInfo;
	private int index;
	
	public ListAppointmentEvent(JLabel[] appointmentInfo, JButton cancelButton) {
		this.selectedButton = "";
		this.appointmentInfo = appointmentInfo;
		this.cancelButton = cancelButton;
		this.cancelAppointmentInfo = "";
		index = -1;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		selectedButton = e.getActionCommand();
		
		if(selectedButton.equals("back")) {
			cancelButton.setVisible(false);
		}		
		else if(selectedButton.equals("")) {
			cancelButton.setVisible(false);
			return;
		} 
		else if(selectedButton.equals("cancel appointment") && index != -1) {
			cancelAppointmentInfo += AppointmentList.getAppointmentLocation(index) + "," + 
					AppointmentList.getPrefferedDoctor(index) + "," +
					AppointmentList.getPrefferedAppointmentDate(index) + "," +
					AppointmentList.getPrefferedTimeSlot(index);
		}
		
		if(selectedButton.indexOf("Appointment ") == -1) {
			cancelButton.setVisible(false);
			return;
		}
		
		
		index = Integer.parseInt(selectedButton.substring(12)) - 1;
		appointmentInfo[1].setText(AppointmentList.getStatus(index));
		appointmentInfo[3].setText(AppointmentList.getAppointmentLocation(index));
		appointmentInfo[5].setText(AppointmentList.getPrefferedDoctor(index));
		appointmentInfo[7].setText(AppointmentList.getPrefferedAppointmentDate(index));
		appointmentInfo[9].setText(AppointmentList.getPrefferedTimeSlot(index));
		
		if(AppointmentList.getMessage(index).equals(""))
			appointmentInfo[11].setText("None");
		else
			appointmentInfo[11].setText(AppointmentList.getMessage(index));
		
		cancelButton.setVisible(true);
		
	}
	
	public void setSelectedButtonToEmpty() {
		selectedButton = "";
	}
	
	public String getSelectedButton() {
		return selectedButton;
	}
	
	public void setCancelAppointmentInfoToEmpty() {
		cancelAppointmentInfo = "";
		index = -1;
	}
	
	public String getCancelAppointmentInfo() {
		return cancelAppointmentInfo;
	}

}
