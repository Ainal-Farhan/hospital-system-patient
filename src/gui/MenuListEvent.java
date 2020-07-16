package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class MenuListEvent implements ActionListener {
	private String selectedButton;
	
	public MenuListEvent() {
		this.selectedButton = "";
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		selectedButton = e.getActionCommand().toLowerCase();
	}
	
	public String getSelectedButton() {
		return this.selectedButton;
	}
	
	public void setSelectedButtonToEmpty() {
		this.selectedButton = "";
	}
}
