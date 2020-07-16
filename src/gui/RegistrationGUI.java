package gui;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

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

public class RegistrationGUI extends GUI{
	private JTextField username;
	private JPasswordField[] password;
	private JLabel[] label;
	private JButton[] button;
	private JLabel[] error;
	private static RegistrationEvent event;
	
	public RegistrationGUI() {
		Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
		
		username = new JTextField(20);
		username.setBorder(border);
		
		password = new JPasswordField[] {
				new JPasswordField(20),
				new JPasswordField(20)
		};
		
		password[0].setBorder(border);
		password[1].setBorder(border);
		
		label = new JLabel[] {
				new JLabel("username"),
				new JLabel("password"),
				new JLabel("re-enter password")
		};
		
		button = new JButton[] {
				new JButton("Register"),
				new JButton("Back to Login Menu")
		};
		
		error = new JLabel[] {
				new JLabel("*"),
				new JLabel("*"),
				new JLabel("*"),
				new JLabel("", SwingConstants.CENTER)
		};
		
		setFrame();
		setFrameContent();
	}
	
	@Override
	protected void setFrame() {
		mainFrame.setTitle("Registration menu");
		mainFrame.setLayout(new GridBagLayout());
		mainFrame.setSize(400, 250);
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
		event = new RegistrationEvent(this.username, this.error[3], this.password);
		for(int i = 0; i < this.button.length; i++)
			button[i].addActionListener(event);
	}
	
	@Override
	protected void dispose() {
		mainFrame.dispose();
	}
	
	@Override
	protected void displayGUI() {
		mainFrame.setVisible(true);
		eventPerformed();
	}
	
	@Override
	protected String getSelectedButton() {
		return event.getSelectedButton();
	}
	
	protected void setSelectedButtonToEmpty() {
		event.setSelectedButtonToEmpty();
	}
	
	protected boolean registrationIsFailed() {
		return event.registrationIsFailed();
	}
	
	protected String getLoginInfo() {
		return event.getLoginInfo();
	}

	private void setLabel() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(5, 5, 5, 0);
		
		for(int x = 0, y = 0, i = 0; y < label.length; y++, x = 0, i++) {
			constraints.gridx = x++;
			constraints.gridy = y;
			mainFrame.add(label[i], constraints);
			
			while(x < 3) {
				constraints.gridx = x;
				constraints.insets = new Insets(5, 5, 5, 5);
				if(x == 1) {
					constraints.insets = new Insets(5, 0, 5, 5);
					error[y].setForeground(Color.RED);
					mainFrame.add(error[y], constraints);
				}
				else if(y == 0)
					mainFrame.add(username, constraints);
				else if(y == 1)
					mainFrame.add(password[0], constraints);
				else
					mainFrame.add(password[1], constraints);
				++x;
			}
		}
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		
		mainFrame.add(this.error[3], constraints);
	}
	
	private void setButton() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridwidth = 3;
		constraints.insets = new Insets(5, 100, 5, 100);
		
		for(int y = 4, i = 0; i < this.button.length; i++, y++) {
			constraints.gridy = y;
			mainFrame.add(this.button[i], constraints);
			this.button[i].setFocusPainted(false);
		}
	}
}
