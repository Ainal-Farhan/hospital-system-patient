package gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import verify.VerifyInfo;
import load.LoadLoginInfo;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class RegistrationEvent implements ActionListener{
	private JTextField username;
	private JLabel error;
	private JPasswordField password[];
	private VerifyInfo verify;
	private String selectedButton;
	private boolean registrationIsFailed;
	private String loginInfo;
	
	public RegistrationEvent(JTextField username, JLabel error, JPasswordField[] password) {
		this.error = error;
		this.username= username;
		this.password = password;
		verify = new VerifyInfo();
		this.selectedButton = "";
		this.registrationIsFailed = true;
		this.loginInfo = "";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.selectedButton = e.getActionCommand();
		if(this.selectedButton.equalsIgnoreCase("Register")) {
			this.registrationIsFailed = true;
			this.loginInfo = "";
			if(isEmpty()){
				this.username.setText("");
				this.password[0].setText("");
				this.password[1].setText("");
				return;
			}
			
			if(!(new String(password[0].getPassword()).equals(new String(password[1].getPassword())))) {
				Border border = BorderFactory.createLineBorder(Color.RED, 1);
				this.password[0].setBorder(border);
				this.password[1].setBorder(border);
				
				this.error.setText("Please enter the same password!");
				this.username.setText("");
				this.password[0].setText("");
				this.password[1].setText("");
				return;
			}
			
			String username = this.username.getText();
			
			if(!verify.verifyUsername(username)) {
				Border border = BorderFactory.createLineBorder(Color.RED, 1);
				this.username.setBorder(border);
				this.error.setText(this.username.getText() + " already taken!");
				this.username.setText("");
				this.password[0].setText("");
				this.password[1].setText("");
				return;
			}
			
			this.error.setText("");
			this.registrationIsFailed = false;
			char[] password = this.password[0].getPassword();
			
			this.loginInfo = (setAndReturnPatientID() + "," + username + "," + new String(password));
		}
	}
	
	private String setAndReturnPatientID() {
		String latestPatientID = LoadLoginInfo.getLatestPatientID();
		String patientID = "P";
		int id;
		
		if(latestPatientID.charAt(1) == '0') {
			patientID += "0";
			if(latestPatientID.charAt(2) == '0') {
				patientID += "0";
				if(latestPatientID.charAt(3) == '0')
					patientID += "0";
				else {
					id = Integer.parseInt(latestPatientID.substring(3, 4)) + 1;
					if(id > 9)
						patientID = patientID.substring(0, 2);
					patientID += Integer.toString(id);
				}
			}
			else {
				id = Integer.parseInt(latestPatientID.substring(2, 4)) + 1;
				if(id > 99)
					patientID = patientID.substring(0, 1);
				patientID += Integer.toString(id);
			}
		}
		else {
			id = Integer.parseInt(latestPatientID.substring(1, 4)) + 1;
			patientID += Integer.toString(id);
		}
		
		return patientID;
	}

	public boolean isEmpty() {
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		boolean status = true;
		this.error.setForeground(Color.RED);
		
		if(username.getText().equals("")) {
			username.setBorder(border);
			if((String.valueOf(password[0].getPassword())).equals("")) {
				password[0].setBorder(border);
				if((String.valueOf(password[1].getPassword())).equals("")) {
					password[1].setBorder(border);
					this.error.setText("Please enter username, password, and re-enter password");
				}
				else {
					border = BorderFactory.createLineBorder(Color.BLUE, 1);
					password[1].setBorder(border);
					this.error.setText("Please enter username, password");
				}
			}
			else if((String.valueOf(password[1].getPassword())).equals("")) {
				password[1].setBorder(border);
				this.error.setText("Please enter username and re-enter password");
				border = BorderFactory.createLineBorder(Color.BLUE, 1);
				password[0].setBorder(border);
			}
			else {
				this.error.setText("Please enter username");
				border = BorderFactory.createLineBorder(Color.BLUE, 1);
				password[0].setBorder(border);
				password[1].setBorder(border);
			}
		}
		else if((String.valueOf(password[0].getPassword())).equals("")){
			border = BorderFactory.createLineBorder(Color.BLUE, 1);
			username.setBorder(border);
			if((String.valueOf(password[1].getPassword())).equals("")) {
				this.error.setText("Please enter password and re-enter password");
				border = BorderFactory.createLineBorder(Color.RED, 1);
				password[0].setBorder(border);
			}
			else {
				border = BorderFactory.createLineBorder(Color.RED, 1);
				password[1].setBorder(border);
				this.error.setText("Please enter password");
				border = BorderFactory.createLineBorder(Color.BLUE, 1);
				password[0].setBorder(border);
			}
		}
		else if((String.valueOf(password[1].getPassword())).equals("")) {
			this.error.setText("Please enter re-enter password");
			border = BorderFactory.createLineBorder(Color.RED, 1);
			password[1].setBorder(border);
			border = BorderFactory.createLineBorder(Color.BLUE, 1);
			username.setBorder(border);
		}
		else {
			border = BorderFactory.createLineBorder(Color.BLUE, 1);
			username.setBorder(border);
			password[0].setBorder(border);
			password[1].setBorder(border);
			status = false;
		}
		
		if(String.valueOf(password[1].getPassword()).length() < 6) {
			this.error.setText("Passsword need to be more than 5 characters");
			border = BorderFactory.createLineBorder(Color.RED, 1);
			password[0].setBorder(border);
			status = true;
		}
		
		return status;
	}
	
	public String getSelectedButton() {
		return this.selectedButton;
	}
	
	public void setSelectedButtonToEmpty() {
		this.selectedButton = "";
	}
	
	public boolean registrationIsFailed() {
		return this.registrationIsFailed;
	}
	
	public String getLoginInfo() {
		return this.loginInfo;
	}
}
