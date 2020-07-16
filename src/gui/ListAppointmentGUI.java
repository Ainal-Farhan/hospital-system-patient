package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.AppointmentList;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class ListAppointmentGUI extends GUI{
	private JButton[] listAppointment;
	private JButton backButton;
	private JButton cancelButton;
	
	private JLabel appointmentLocationLabel;
	private JLabel prefferedDoctorLabel;
	private JLabel prefferedAppointmentDateLabel;
	private JLabel prefferedTimeSlotLabel;
	private JLabel messageLabel;
	private JLabel statusLabel;
	
	private JLabel appointmentLocationInfo;
	private JLabel prefferedDoctorInfo;
	private JLabel prefferedAppointmentDateInfo;
	private JLabel prefferedTimeSlotInfo;
	private JLabel messageInfo;
	private JLabel statusInfo;
	
	protected boolean statusAppointment;
	private int y;
	
	private static ListAppointmentEvent event;
	
	public ListAppointmentGUI() {
		if(AppointmentList.getPrefferedAppointmentDate(0).equals("none"))
			statusAppointment = false;
		else
			statusAppointment = true;
		
		y = 0;
		listAppointment = new JButton[AppointmentList.getAppointmentList().length];
		
		for(int i = 0; i < listAppointment.length; i++) {
			listAppointment[i] = new JButton();
			listAppointment[i].setText("Appointment " + (i+1));
			listAppointment[i].setFocusPainted(false);
		}
		
		backButton = new JButton("back");
		backButton.setFocusPainted(false);
		
		cancelButton = new JButton("cancel appointment");
		cancelButton.setFocusPainted(false);
		cancelButton.setVisible(false);
		
		statusLabel = new JLabel("Status");
		statusInfo = new JLabel("- - -");
		appointmentLocationLabel = new JLabel("Location");
		appointmentLocationInfo = new JLabel("- - -");
		prefferedDoctorLabel = new JLabel("Preffered Doctor");
		prefferedDoctorInfo = new JLabel("- - -");
		prefferedAppointmentDateLabel = new JLabel("Preffered Appointment Date");
		prefferedAppointmentDateInfo = new JLabel("- - -");
		prefferedTimeSlotLabel = new JLabel("Preffered Time Slot");
		prefferedTimeSlotInfo = new JLabel("- - -");
		messageLabel = new JLabel("Message");
		messageInfo = new JLabel("- - -");
		
		JLabel[] appointmentInfo = new JLabel[] {
				statusLabel,
				statusInfo,
				appointmentLocationLabel,
				appointmentLocationInfo,
				prefferedDoctorLabel,
				prefferedDoctorInfo,
				prefferedAppointmentDateLabel,
				prefferedAppointmentDateInfo,
				prefferedTimeSlotLabel,
				prefferedTimeSlotInfo,
				messageLabel,
				messageInfo				
		};
		
		for(int i = 0; i < appointmentInfo.length; i++) {
			appointmentInfo[++i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		}
		
		setFrame();
		setFrameContent();
		event = new ListAppointmentEvent(appointmentInfo, cancelButton);
	}

	@Override
	protected void displayGUI() {
		if(!statusAppointment) return;
		
		mainFrame.setVisible(true);
		eventPerformed();
	}

	@Override
	protected void setFrame() {
		mainFrame.setTitle("List of Appointment");
		mainFrame.setLayout(new GridBagLayout());
		mainFrame.setSize(600, 600);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(true);
		confirmationBeforeExit();
	}

	@Override
	protected void setFrameContent() {
		GridBagConstraints constraints = new GridBagConstraints();
		setSelectionButton(constraints);
		setAppointmentInfo(constraints);
		setNavigateButton(constraints);
	}

	@Override
	protected void eventPerformed() {
		for(int i = 0; i < listAppointment.length; i++) {
			listAppointment[i].addActionListener(event);
		}
		
		backButton.addActionListener(event);
		cancelButton.addActionListener(event);
		
	}

	@Override
	protected void dispose() {
		mainFrame.dispose();		
	}

	@Override
	protected String getSelectedButton() {
		return event.getSelectedButton();
	}
	
	protected void setSelectedButtonToEmpty() {
		event.setSelectedButtonToEmpty();
	}
	
	protected String getCancelAppointmentInfo() {
		return event.getCancelAppointmentInfo();
	}
	
	protected void setCancelAppointmentInfoToEmpty() {
		event.setCancelAppointmentInfoToEmpty();
	}
	
	private void setSelectionButton(GridBagConstraints constraints) {
		int x = 0;
		int y = 0;
		
		JPanel listAppointmentPanel = new JPanel(new GridBagLayout());
		listAppointmentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK), "Appointment List"));
		listAppointmentPanel.setBackground(Color.CYAN);
		
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.EAST;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = x;
		
		for(int i = 0; i < listAppointment.length; i++, y++) {
			constraints.gridy = y;
			listAppointmentPanel.add(listAppointment[i], constraints);
		}
		constraints.gridy = 0;
		mainFrame.add(listAppointmentPanel, constraints);
	}
	
	private void setNavigateButton(GridBagConstraints constraints) {
		int x = 0;
		y = 1;
		
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.CENTER;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = x;
		constraints.gridy = y;
		
		mainFrame.add(backButton, constraints);
		
		constraints.gridy = ++y;
		mainFrame.add(cancelButton, constraints);
	}
	
	private void setAppointmentInfo(GridBagConstraints constraints) {
		
		JPanel appointmentInfoPanel = new JPanel(new GridBagLayout());
		appointmentInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK), "Appointment Details"));
		appointmentInfoPanel.setBackground(Color.CYAN);
		
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
					
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, 0, 20);
		appointmentInfoPanel.add(statusLabel, constraints);
		constraints.gridx = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
		appointmentInfoPanel.add(appointmentLocationLabel, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 10, 10, 20);
		appointmentInfoPanel.add(statusInfo, constraints);
		constraints.gridx = 1;
		constraints.insets = new Insets(0, 0, 10, 10);
		appointmentInfoPanel.add(appointmentLocationInfo, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(0, 0, 0, 20);
		appointmentInfoPanel.add(prefferedDoctorLabel, constraints);
		constraints.gridx = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
		appointmentInfoPanel.add(prefferedAppointmentDateLabel, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new Insets(0, 10, 10, 20);
		appointmentInfoPanel.add(prefferedDoctorInfo, constraints);
		constraints.gridx = 1;
		constraints.insets = new Insets(0, 0, 10, 10);
		appointmentInfoPanel.add(prefferedAppointmentDateInfo, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.insets = new Insets(0, 0, 0, 20);
		appointmentInfoPanel.add(prefferedTimeSlotLabel, constraints);
		constraints.gridx = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
		appointmentInfoPanel.add(messageLabel, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.insets = new Insets(0, 10, 10, 20);
		appointmentInfoPanel.add(prefferedTimeSlotInfo, constraints);
		constraints.gridx = 1;
		constraints.insets = new Insets(0, 0, 10, 10);
		appointmentInfoPanel.add(messageInfo, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(5, 5, 5, 5);
		mainFrame.add(appointmentInfoPanel, constraints);
	}

}
