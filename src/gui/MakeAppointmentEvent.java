package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JTextField;

import core.DoctorList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class MakeAppointmentEvent implements ActionListener, ItemListener, FocusListener{
	private String selectedButton;
	private boolean makeAppointmentStatus;
	
	private JComboBox<String> prefferedDoctor;
	private JTextField prefferedAppointmentDate;
	private JLabel error;
	
	public MakeAppointmentEvent(JTextField prefferedAppointmentDate, JLabel error, JComboBox<String> prefferedDoctor) {
		this.prefferedAppointmentDate = prefferedAppointmentDate;
		this.prefferedDoctor = prefferedDoctor;
		this.error = error;
		this.makeAppointmentStatus = false;
		this.selectedButton = "";
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		selectedButton = e.getActionCommand();
		error.setText("");
		makeAppointmentStatus = false;
		
		switch(selectedButton) {
		case "back":
			return;
		case "make appointment":
			if(prefferedAppointmentDate.getText().equals("DD/MM/YYYY")) {
				error.setText("Please insert your preffered appointment date!");
				return;
			}
			makeAppointmentStatus = true;
			return;
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		String selectedLocation = (String)e.getItem();
		
		prefferedDoctor.removeAllItems();
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) prefferedDoctor.getModel();
		model.removeAllElements();
		
		for(String name: DoctorList.getDoctorName(selectedLocation)) {
			model.addElement(name);
		}
		
		prefferedDoctor.setModel(model);
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if(prefferedAppointmentDate.getText().equals("DD/MM/YYYY"))
			prefferedAppointmentDate.setText("");
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		String date = prefferedAppointmentDate.getText();
		
		error.setText("");
		
		if(date.equals(""))
			error.setText("Please insert your preffered appointment date!");
		else if(!isDateValid(date))
			error.setText("Please enter a valid date!");
		else
			return;
		
		prefferedAppointmentDate.setText("DD/MM/YYYY");
	}
	
	public void setSelectedButtonToEmpty() {
		selectedButton = "";
	}
	
	private boolean isDateValid(String date) {
		if(!date.matches("[0-9]+") && date.charAt(2) - '/' != 0 && date.charAt(5) - '/' != 0) return false;
		LocalDate currentDate = LocalDate.now();
		int day;
		int month;
		int year = Integer.parseInt(date.substring(6));
		
		if(year < currentDate.getYear()) return false;
		
		if(date.charAt(0)-'0' == 0) day = Integer.parseInt(date.substring(1,2));
		
		else  day = Integer.parseInt(date.substring(0,2));
		
		if(date.charAt(3)-'0' == 0)
			month = Integer.parseInt(date.substring(4,5));
		else
			month = Integer.parseInt(date.substring(3,5));
		
		if(month > 12 || month < 1) return false;
		if(day < 1) return false;
		if(year % 4 == 0 && month == 2 && day > 29) return false;
		if(year % 4 != 0 && month == 2 && day > 28) return false;
		if((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day > 31) return false;
		if((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) return false;

		if(year == currentDate.getYear()) {
			if(month == currentDate.getMonthValue()) {
				if(day >= currentDate.getDayOfMonth()) return true;
				else return false;
			}
			if(month < currentDate.getMonthValue()) return false;
		}
		
		return true;
	}
	
	public boolean getMakeAppointmentStatus() {
		return makeAppointmentStatus;
	}
	
	public String getSelectedButton() {
		return selectedButton;
	}
}
