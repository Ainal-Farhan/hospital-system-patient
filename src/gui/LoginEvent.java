package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import verify.VerifyInfo;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class LoginEvent implements ActionListener{
	private JTextField username;
	private JPasswordField password;
	private JLabel error;
	private VerifyInfo verify;
	private String selectedButton;
	private boolean loginIsFailed;
	
	public LoginEvent(JTextField username, JPasswordField password, JLabel error){
		this.username = username;
		this.password = password;
		this.error = error;
		verify = new VerifyInfo();
		this.selectedButton = "";
		this.loginIsFailed = true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.selectedButton = e.getActionCommand();
		
		if(this.selectedButton.equalsIgnoreCase("login")) {
			this.loginIsFailed = true;
			if(isEmpty()) return;		
			
			Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
			username.setBorder(border);
			password.setBorder(border);
			
			String username = this.username.getText();
			char[] password = this.password.getPassword();
			
			if(!verify.verifyLoginDetails(username, new String(password))) {
				error.setText("Either username or password is incorrect!");
				this.password.setText("");
				this.username.setText("");
				return;
			}
			
			this.loginIsFailed = false;
		}
	}
	
	public boolean isEmpty() {
		error.setForeground(Color.RED);
		
		if(!username.getText().equals("") && !(new String(password.getPassword())).equals(""))
			return false;
		else if(username.getText().equals("") && (new String(password.getPassword())).equals(""))
			error.setText("Please enter your username and password!");
		else if(username.getText().equals(""))
			error.setText("Please enter your username!");
		else if((new String(password.getPassword())).equals(""))
			error.setText("Please enter your password!");
		
		password.setText("");
		username.setText("");
		
		Border border = BorderFactory.createLineBorder(Color.RED, 2);
		username.setBorder(border);
		password.setBorder(border);
		
		return true;
	}
	
	public String getSelectedButton() {
		return this.selectedButton;
	}
	
	public void setSelectedButtonToEmpty() {
		this.selectedButton = "";
	}
	
	public boolean loginIsFailed() {
		return this.loginIsFailed;
	}

	public String getPatientID() {
		return verify.getPatientID();
	}
}
