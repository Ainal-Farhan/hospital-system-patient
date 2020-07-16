package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class MenuListGUI extends GUI{
	private JButton[] features;
	private static MenuListEvent event;
	
	protected MenuListGUI() {
		features = new JButton[] {
			new JButton("Profile"),
			new JButton("Disease Information"),
			new JButton("Make Appointment"),
			new JButton("List Appointment"),
			new JButton("Exit")
		};

		setFrame();
		setFrameContent();
		event = new MenuListEvent();
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
	
	@Override
	protected void setFrameContent() {
		setFrame();
		setButton();
	}
	
	@Override
	protected void dispose() {
		mainFrame.dispose();
	}
	
	@Override
	protected void eventPerformed() {
		for(int i = 0; i < features.length; i++) 
			features[i].addActionListener(event);
	}
	
	@Override
	protected void setFrame() {
		mainFrame.setTitle("Menu");
		mainFrame.setLayout(new GridBagLayout());
		mainFrame.setSize(250, 350);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	protected void setSelectedButtonToEmpty() {
		event.setSelectedButtonToEmpty();
	}
	
	private void setButton() {
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = 0;
		
		for(int i = 0; i < features.length; i++) {
			constraints.gridy = i;
			mainFrame.add(features[i], constraints);
			
			features[i].setFocusPainted(false);
		}
	}
}
