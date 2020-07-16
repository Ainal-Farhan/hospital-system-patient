package gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.GridBagConstraints;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class LoginGUI extends GUI{
	private JButton[] button;
	private JLabel[] label;
	private JTextField username;
	private JPasswordField password;
	private JLabel error;
	private static LoginEvent event;
	
	protected LoginGUI() {
		button = new JButton[] {
			new JButton("Login"),
			new JButton("Register")
		};
		
		label = new JLabel[] {
				new JLabel("username"),
				new JLabel("password")
		};
		
		username = new JTextField(20);
		password = new JPasswordField(20);
		
		Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
		username.setBorder(border);
		password.setBorder(border);
		
		error = new JLabel("", SwingConstants.CENTER);
		
		setFrame();
		setFrameContent();
	}
	
	@Override
	protected void displayGUI() {
		mainFrame.setVisible(true);
		eventPerformed();
	}
	
	@Override
	protected void dispose() {
		mainFrame.dispose();
	}
	
	@Override
	protected void setFrame() {
		mainFrame.setTitle("login menu");
		mainFrame.setLayout(new GridBagLayout());
		mainFrame.setSize(350, 200);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		confirmationBeforeExit();
	}

	@Override
	protected void setFrameContent() {
		setLabel();
		setButton();
	}

	@Override
	protected void eventPerformed() {
		event = new LoginEvent(this.username, this.password, this.error);
		for(int i = 0; i < this.button.length; i++)
			button[i].addActionListener(event);
	}
	
	@Override
	protected String getSelectedButton() {
		return event.getSelectedButton();
	}
	
	public String getPatientID() {
		return event.getPatientID();
	}
	
	protected void setSelectedButtonToEmpty() {
		event.setSelectedButtonToEmpty();
	}
	
	protected boolean loginIsFailed() {
		return event.loginIsFailed();
	}
	
	private void setLabel() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(5, 5, 5, 5);
		
		for(int x = 0, y = 0; y < this.label.length; y++, x = 0) {
			constraints.gridx = x;
			constraints.gridy = y;
			
			mainFrame.add(this.label[y], constraints);
			
			constraints.gridx = ++x;
			if(y == 0)
				mainFrame.add(this.username, constraints);
			else
				mainFrame.add(this.password, constraints);
		}
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		
		mainFrame.add(this.error, constraints);
	}
	
	private void setButton() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.PAGE_END;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 100, 5, 100);
		
		for(int y = 3, i = 0; i < this.button.length; i++, y++) {
			constraints.gridy = y;
			mainFrame.add(this.button[i], constraints);
			this.button[i].setFocusPainted(false);
		}
	}
}
