package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import core.DoctorList;
import core.Hospital;
import core.PatientInfo;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class MakeAppointmentGUI extends GUI{
	private JLabel patientName;
	private JLabel patientEmail;
	private JLabel patientContactNumber;
	private JLabel patientNRIC;
	private JLabel patientDateOfBirth;
	private JLabel patientLocation;
	private JLabel patientPrefferedDoctor;
	private JLabel patientPrefferedAppointmentDate;
	private JLabel patientPrefferedTimeSlot;
	private JLabel patientMessage;
	private JLabel error;
	
	private JTextField pName;
	private JTextField pEmail;
	private JTextField pContactNumber;
	private JTextField pNRIC;
	private JTextField pDateOfBirth;
	private JTextField pPrefferedAppointmentDate;
	
	private LimitedRowTextArea pMessage;
	
	private JComboBox<String> pLocation;
	private JComboBox<String> pPrefferedTimeSlot;
	private JComboBox<String> pPrefferedDoctor;
	
	private JButton backButton;
	private JButton makeAppointmentButton;
	
	private int y;
	
	private static MakeAppointmentEvent event;
	
	protected MakeAppointmentGUI() {		
		patientName = new JLabel("Name");
		patientEmail = new JLabel("Email");
		patientContactNumber = new JLabel("Contact Number");
		patientNRIC = new JLabel("NRIC");
		patientDateOfBirth = new JLabel("Date Of Birth");
		patientLocation = new JLabel("Location");
		patientPrefferedDoctor = new JLabel("Preffered Doctor");
		patientPrefferedAppointmentDate = new JLabel("Preffered Appointment Date");
		patientPrefferedTimeSlot = new JLabel("Preffered Time Slot");
		patientMessage = new JLabel("Message");
		error = new JLabel("");
		
		error.setForeground(Color.RED);
		
		pName = new JTextField(PatientInfo.getFirstname().toUpperCase() + " " + PatientInfo.getLastname().toUpperCase());
		pEmail = new JTextField(PatientInfo.getEmail() + "@" + PatientInfo.getEmailProvider());
		pContactNumber = new JTextField("+60 - " + PatientInfo.getPhoneNumber());
		pNRIC = new JTextField(PatientInfo.getIC().substring(0, 6) + "-" + PatientInfo.getIC().substring(6, 8) + "-" + PatientInfo.getIC().substring(8));
		
		String year = PatientInfo.getIC().substring(0, 2);
		
		if(year.charAt(0) - '0' == 0) {
			year = "20" + year;
		}
		else {
			year = "19" + year;
		}
		
		String dateOfBirth = PatientInfo.getIC().substring(4, 6) + "-" + PatientInfo.getIC().substring(2, 4) + "-" + year;
		pDateOfBirth = new JTextField(dateOfBirth);
		
		pPrefferedAppointmentDate = new JTextField();
		pPrefferedAppointmentDate.setDocument(new JTextFieldLimit(10));
		pPrefferedAppointmentDate.setText("DD/MM/YYYY");
		
		JTextField[] textField = new JTextField[] {
				pName,
				pEmail,
				pContactNumber,
				pNRIC,
				pDateOfBirth,
				pPrefferedAppointmentDate
				
		};
		
		for(int i = 0; i < textField.length; i++) {
			textField[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
			if(i != 5) {
				textField[i].setBackground(Color.CYAN);
				textField[i].setEditable(false);
			}
		}
		
		pMessage = new LimitedRowTextArea();
		pMessage.setMaxRows(5);
		pMessage.setRows(5);
		pMessage.setBackground(Color.WHITE);
		pMessage.setText("None");
		
		pLocation = new JComboBox<String>(Hospital.getHospitalLocation());
		pPrefferedTimeSlot = new JComboBox<String>(Hospital.getAppointmentTimeSlot());
		pPrefferedDoctor = new JComboBox<String>(DoctorList.getDoctorName());
		
		pLocation.setBackground(Color.WHITE);
		pPrefferedTimeSlot.setBackground(Color.WHITE);
		pPrefferedDoctor.setBackground(Color.WHITE);
		
		backButton = new JButton("back");
		makeAppointmentButton = new JButton("make appointment");
		
		backButton.setFocusPainted(false);
		makeAppointmentButton.setFocusPainted(false);
		
		y = 0;
		
		event = new MakeAppointmentEvent(pPrefferedAppointmentDate, error, pPrefferedDoctor);
		setFrame();
		setFrameContent();
	}
	
	@Override
	protected void displayGUI() {
		mainFrame.setVisible(true);
		eventPerformed();
	}

	@Override
	protected void setFrame() {
		mainFrame.setTitle("Appointment");
		mainFrame.setLayout(new GridBagLayout());
		mainFrame.setSize(500, 600);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		confirmationBeforeExit();
	}

	@Override
	protected void setFrameContent() {
		GridBagConstraints constraints = new GridBagConstraints();
		
		setContent(constraints);
		setButton(constraints);
	}

	@Override
	protected void eventPerformed() {
		backButton.addActionListener(event);
		makeAppointmentButton.addActionListener(event);
		pLocation.addItemListener(event);
		pPrefferedAppointmentDate.addFocusListener(event);
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
	
	protected boolean getMakeAppointmentStatus() {
		return event.getMakeAppointmentStatus();
	}
	
	protected String getAppointmentInfo() {
		String appointmentInfo = "";
		
		appointmentInfo += String.format(",%s,%s,%s,%s,%s,PENDING", pLocation.getSelectedItem().toString(),
				pPrefferedDoctor.getSelectedItem().toString(),
				pPrefferedAppointmentDate.getText(),
				pPrefferedTimeSlot.getSelectedItem().toString(),
				pMessage.getText());
		
		return appointmentInfo;
	}
	
	private void setContent(GridBagConstraints constraints) {
		int x = 0;
		
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = x;
		constraints.gridy = y;
		
		//patient's name & email
		constraints.insets = new Insets(10, 0, 0, 0);
		mainFrame.add(patientName, constraints);
		constraints.insets = new Insets(10, 0, 0, 0);
		constraints.gridx = ++x;
		mainFrame.add(patientEmail, constraints);
		
		x = 0;
		constraints.insets = new Insets(0, 0, 20, 20);
		constraints.gridx = x;
		constraints.gridy = ++y;
		mainFrame.add(pName, constraints);
		constraints.insets = new Insets(0, 0, 20, 0);
		constraints.gridx = ++x;
		mainFrame.add(pEmail, constraints);
		
		//patient's contact number & dateOfBirth
		x = 0;
		constraints.gridx = x;
		constraints.gridy = ++y;
		constraints.insets = new Insets(0, 0, 0, 0);
		mainFrame.add(patientContactNumber, constraints);
		constraints.gridx = ++x;
		constraints.insets = new Insets(0, 0, 0, 0);
		mainFrame.add(patientDateOfBirth, constraints);
		
		x = 0;
		constraints.gridx = x;
		constraints.gridy = ++y;
		constraints.insets = new Insets(0, 0, 20, 20);
		mainFrame.add(pContactNumber, constraints);
		constraints.gridx = ++x;
		constraints.insets = new Insets(0, 0, 20, 0);
		mainFrame.add(pDateOfBirth, constraints);
		
		//patient's NRIC
		x = 0;
		constraints.gridx = x;
		constraints.gridy = ++y;
		constraints.insets = new Insets(0, 0, 0, 0);
		mainFrame.add(patientNRIC, constraints);
		
		x = 0;
		constraints.gridx = x;
		constraints.gridy = ++y;
		constraints.insets = new Insets(0, 0, 20, 20);
		mainFrame.add(pNRIC, constraints);
		
		//location & preffered doctor
		x = 0;
		constraints.gridx = x;
		constraints.gridy = ++y;
		constraints.insets = new Insets(0, 0, 0, 0);
		mainFrame.add(patientLocation, constraints);
		constraints.gridx = ++x;
		constraints.insets = new Insets(0, 0, 0, 0);
		mainFrame.add(patientPrefferedDoctor, constraints);
		
		x = 0;
		constraints.gridx = x;
		constraints.gridy = ++y;
		constraints.insets = new Insets(0, 0, 20, 20);
		mainFrame.add(pLocation, constraints);
		constraints.gridx = ++x;
		constraints.insets = new Insets(0, 0, 20, 0);
		mainFrame.add(pPrefferedDoctor, constraints);
		
		//preffered appointmentDate & Time Slot
		x = 0;
		constraints.gridx = x;
		constraints.gridy = ++y;
		constraints.insets = new Insets(0, 0, 0, 0);
		mainFrame.add(patientPrefferedAppointmentDate, constraints);
		constraints.gridx = ++x;
		constraints.insets = new Insets(0, 0, 0, 0);
		mainFrame.add(patientPrefferedTimeSlot, constraints);
		
		x = 0;
		constraints.gridx = x;
		constraints.gridy = ++y;
		constraints.insets = new Insets(0, 0, 20, 20);
		mainFrame.add(pPrefferedAppointmentDate, constraints);
		constraints.gridx = ++x;
		constraints.insets = new Insets(0, 0, 20, 0);
		mainFrame.add(pPrefferedTimeSlot, constraints);
		
		//patient's Message
		x = 0;
		constraints.gridx = x;
		constraints.gridy = ++y;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(0, 0, 0, 0);
		mainFrame.add(patientMessage, constraints);
		
		x = 0;
		constraints.gridx = x;
		constraints.gridy = ++y;
		constraints.insets = new Insets(0, 0, 20, 0);
		mainFrame.add(pMessage, constraints);
		
		//error Message
		x = 0;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.CENTER;
		constraints.gridx = x;
		constraints.gridy = ++y;
		mainFrame.add(error, constraints);
	}
	
	private void setButton(GridBagConstraints constraints) {
		int x = 0;
		
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.CENTER;
		constraints.gridx = x;
		constraints.gridy = ++y;
		
		mainFrame.add(backButton, constraints);
		constraints.gridx = ++x;
		mainFrame.add(makeAppointmentButton, constraints);
	}

}
