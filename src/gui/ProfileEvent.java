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

public class ProfileEvent implements ActionListener{
	private String selectedButton = "";
	private JLabel error;
	private boolean updateStatus;
	private JTextField[] textField;
	private JRadioButton[] gender;
	private JComboBox<String> stateOfBirth;
	private JButton[] button;
	private JLabel patientID;
	
	public ProfileEvent(JLabel error, JTextField[] textField, JRadioButton[] gender, JComboBox<String> stateOfBirth, JButton[] button, JLabel patientID) {
		this.error = error;
		this.updateStatus = false;
		this.textField = textField;
		this.gender = gender;
		this.stateOfBirth = stateOfBirth;
		this.button = button;
		this.patientID = patientID;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		selectedButton = e.getActionCommand();
		error.setText("");
		if(selectedButton.equals("update")) {
			button[0].setText("back");
			button[1].setText("edit");
			updateStatus = true;
		}
		
		else if(selectedButton.equals("edit")) {
			setEnable(true);
			button[0].setText("next");
			button[1].setVisible(false);
		}
		
		else if(selectedButton.equals("next")) {
			if(!isFull()) {
				error.setText("Please fill the necessary information");
				error.setForeground(Color.RED);
				return;
			}
			setEnable(false);
			button[0].setText("cancel");
			button[1].setText("update");
			button[1].setVisible(true);
		}
		
		else if(selectedButton.equals("cancel") || selectedButton.equals("back")) {
			setEnable(false);
			updateStatus = false;
		}
	}
	
	public boolean getUpdateStatus() {
		return updateStatus;
	}
	
	public String getSelectedButton() {
		return selectedButton;
	}
	
	public void setEnable(boolean status) {
		for(int i = 0; i < textField.length; i++) {
			textField[i].setEditable(status);
			
			if(status) {
				textField[i].setBorder(new JTextField().getBorder());
				textField[i].setBackground(Color.WHITE);
			}
			else {
				textField[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
				textField[i].setBackground(Color.CYAN);
			}
			
			if(i < gender.length) gender[i].setEnabled(status);
		}
		stateOfBirth.setEnabled(status);
		stateOfBirth.setBackground(Color.WHITE);
	}
	
	private boolean isFull() {
		for(int i = 0; i < textField.length; i++)
			if(textField[i].getText().equals(""))
				return false;
		
		return true;
	}
	
	public void setSelectedButtonToEmpty() {
		selectedButton = "";
	}
	
	public String getUpdatedInformation() {
		String updatedInformation = patientID.getText().substring(5);
		
		for(int i = 0; i < textField.length; i++)
			updatedInformation += "," + textField[i].getText();
			
		if(gender[0].isSelected())
			updatedInformation += "," + gender[0].getText();
		else
			updatedInformation += "," + gender[1].getText();
		
		updatedInformation += "," + stateOfBirth.getSelectedItem().toString();
		
		return updatedInformation;
	}
}
