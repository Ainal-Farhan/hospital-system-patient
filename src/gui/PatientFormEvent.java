package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class PatientFormEvent implements ActionListener{
	private JTextField[] textField;
	private JComboBox<String> stateCode;
	private JRadioButton[] gender;
	private JLabel error;
	private JButton[] button;
	private boolean informationStatus = false;
	private String selectedButton = "";
	private String patientInformation = "";
	
	public PatientFormEvent() {}
	
	public PatientFormEvent(JTextField[] textField, JComboBox<String> stateCode, JRadioButton[] gender, JLabel error, JButton[] button) {
		this.textField = textField;
		this.stateCode = stateCode;
		this.gender = gender;
		this.error = error;
		this.button = button;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		selectedButton = e.getActionCommand();
		informationStatus = false;
		
		if(selectedButton.equals("next")) {
			if(!isTextFieldFull()) {
				error.setText("You need to fill the form first before saving!");
				return;
			}
			
			for(int i = 0; i < textField.length; i++) {
				this.textField[i].setEditable(false);
				this.textField[i].setBackground(Color.CYAN);
				this.textField[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			}
			
			this.stateCode.setEnabled(false);
			this.gender[0].setEnabled(false);
			this.gender[1].setEnabled(false);
			
			this.stateCode.setBackground(Color.CYAN);
			
			button[0].setText("save");
			button[1].setText("edit");
			
			button[1].setVisible(true);
		}
		else if(selectedButton.equals("edit")) {
			for(int i = 0; i < textField.length; i++) {
				this.textField[i].setEditable(true);
				this.textField[i].setBackground(Color.WHITE);
				this.textField[i].setBorder(new JTextField().getBorder());
			}
			
			this.stateCode.setEnabled(true);
			this.gender[0].setEnabled(true);
			this.gender[1].setEnabled(true);
			
			this.stateCode.setBackground(Color.WHITE);
				
			button[0].setText("next");
			button[1].setVisible(false);
		}
		
		else if(selectedButton.equals("save")) {
			informationStatus = true;
			for(int i = 0; i < textField.length; i++)
				patientInformation += this.textField[i].getText() + ",";
			if(gender[0].isSelected())
				patientInformation += "male,";
			else if(gender[1].isSelected())
				patientInformation += "female,";
			patientInformation += stateCode.getSelectedItem().toString();
			button[1].setVisible(false);
		}
	}
	
	private boolean isTextFieldFull() {
		for(int i = 0; i < this.textField.length; i++) {
			if(i == 7 || i == 8)
				continue;
			else if(this.textField[i].getText().equals("")) {
				return false;
			}
		}
		return true;
	}
	
	public boolean getInformationStatus() {
		return informationStatus;
	}
	
	public String getSelectedButton() {
		return selectedButton;
	}
	
	public String getPatientInformation() {
		return patientInformation;
	}

}
