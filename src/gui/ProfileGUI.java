package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import core.PatientInfo;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class ProfileGUI extends GUI{
	private JLabel ID;
	private JLabel fn;
	private JLabel ln;
	private JLabel g;
	private JLabel ic;
	private JLabel e;
	private JLabel eP;
	private JLabel pN;
	private JLabel countryCode;
	private JLabel error;
	
	private JTextField firstname;
	private JTextField lastname;
	private JTextField dateOfBirth;
	private JTextField icLast4Digits;
	private JTextField email;
	private JTextField emailProvider;
	private JTextField phoneNo;
	
	private JRadioButton male;
	private JRadioButton female;
	private ButtonGroup gender;
	
	private JComboBox<String> codeStateOfBirth;
	
	private JButton navigateButton;
	private JButton updateButton;
	
	private static ProfileEvent event;
	
	public ProfileGUI() {
		ID = new JLabel("ID : " + PatientInfo.getPatientID());
		fn = new JLabel("Firstname");
		ln = new JLabel("Lastname");
		g = new JLabel ("Gender");
		ic = new JLabel("NRIC");
		e = new JLabel("Email");
		eP = new JLabel(" @ ");
		pN = new JLabel("Phone Number");
		countryCode = new JLabel(" +60 ");
		error = new JLabel("");

		firstname = new JTextField(PatientInfo.getFirstname().toUpperCase(), 20);
		lastname = new JTextField(PatientInfo.getLastname().toUpperCase(), 20);
		dateOfBirth = new JTextField(PatientInfo.getIC().substring(0, 6), 4);
		icLast4Digits = new JTextField(PatientInfo.getIC().substring(8), 3);
		email = new JTextField(PatientInfo.getEmail(), 20);
		emailProvider = new JTextField(PatientInfo.getEmailProvider(), 10);
		phoneNo = new JTextField();
		phoneNo.setDocument(new JTextFieldLimit(10));
		phoneNo.setText(PatientInfo.getPhoneNumber());
		
		male = new JRadioButton("male");
		female = new JRadioButton("female");
		
		male.setFocusPainted(false);
		female.setFocusPainted(false);
		
		gender = new ButtonGroup();
		gender.add(male);
		gender.add(female);
		
		if(PatientInfo.getGender().equals("male"))
			male.setSelected(true);
		else
			female.setSelected(true);
		
		String[] code = new String[16];
		
		int i = 0;
		
		for (STATE_CODE state : STATE_CODE.values()) {
			  code[i] = state.toString();
			  i++;
		}
		
		codeStateOfBirth = new JComboBox<String>(code);
		
		switch(PatientInfo.getIC().substring(6, 8)) {
		case "01":
			codeStateOfBirth.setSelectedItem(STATE_CODE.JOHOR.toString());
			break;
		case "02":
			codeStateOfBirth.setSelectedItem(STATE_CODE.KEDAH.toString());
			break;
		case "03":
			codeStateOfBirth.setSelectedItem(STATE_CODE.KELANTAN.toString());
			break;
		case "04":
			codeStateOfBirth.setSelectedItem(STATE_CODE.MELAKA.toString());
			break;
		case "05":
			codeStateOfBirth.setSelectedItem(STATE_CODE.NEGERI_SEMBILAN.toString());
			break;
		case "06":
			codeStateOfBirth.setSelectedItem(STATE_CODE.PAHANG.toString());
			break;
		case "07":
			codeStateOfBirth.setSelectedItem(STATE_CODE.PULAU_PINANG.toString());
			break;
		case "08":
			codeStateOfBirth.setSelectedItem(STATE_CODE.PERAK.toString());
			break;
		case "09":
			codeStateOfBirth.setSelectedItem(STATE_CODE.PERLIS.toString());
			break;
		case "10":
			codeStateOfBirth.setSelectedItem(STATE_CODE.SELANGOR.toString());
			break;
		case "11":
			codeStateOfBirth.setSelectedItem(STATE_CODE.TERENGGANU.toString());
			break;
		case "12":
			codeStateOfBirth.setSelectedItem(STATE_CODE.SABAH.toString());
			break;
		case "13":
			codeStateOfBirth.setSelectedItem(STATE_CODE.SARAWAK.toString());
			break;
		case "14":
			codeStateOfBirth.setSelectedItem(STATE_CODE.WILAYAH_PERSEKUTUAN_KUALA_LUMPUR.toString());
			break;
		case "15":
			codeStateOfBirth.setSelectedItem(STATE_CODE.WILAYAH_PERSEKUTUAN_LABUAN.toString());
			break;
		case "16":
			codeStateOfBirth.setSelectedItem(STATE_CODE.WILAYAH_PERSEKUTUAN_PUTRAJAYA.toString());
			break;
		}
		
		navigateButton = new JButton("back");
		updateButton = new JButton("edit");
		
		navigateButton.setVisible(true);
		updateButton.setVisible(true);
		
		navigateButton.setFocusPainted(false);
		updateButton.setFocusPainted(false);
		
		setFrame();
		setFrameContent();
		
		firstname.setEditable(false);
		lastname.setEditable(false);
		dateOfBirth.setEditable(false);
		icLast4Digits.setEditable(false);
		email.setEditable(false);
		emailProvider.setEditable(false);
		phoneNo.setEditable(false);
		male.setEnabled(false);
		female.setEnabled(false);
		codeStateOfBirth.setEnabled(false);
		
		firstname.setBackground(Color.CYAN);
		lastname.setBackground(Color.CYAN);
		dateOfBirth.setBackground(Color.CYAN);
		icLast4Digits.setBackground(Color.CYAN);
		email.setBackground(Color.CYAN);
		emailProvider.setBackground(Color.CYAN);
		phoneNo.setBackground(Color.CYAN);
		male.setBackground(Color.CYAN);
		female.setBackground(Color.CYAN);
		codeStateOfBirth.setBackground(Color.CYAN);
		
		Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		
		firstname.setBorder(border);
		lastname.setBorder(border);
		dateOfBirth.setBorder(border);
		icLast4Digits.setBorder(border);
		email.setBorder(border);
		emailProvider.setBorder(border);
		phoneNo.setBorder(border);
		
	}
	
	@Override
	protected void setFrame() {
		mainFrame.setTitle("Profile");
		mainFrame.setLayout(new GridBagLayout());
		mainFrame.setSize(600, 350);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		confirmationBeforeExit();
	}
	
	@Override
	protected void setFrameContent() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(5,5,5,5);
		
		//Patient ID
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		mainFrame.add(ID, constraints);
		constraints.gridwidth = 1;
		
		//Firstname
		constraints.gridx = 0;
		constraints.gridy = 1;
		mainFrame.add(fn, constraints);
		constraints.gridx = 1;
		constraints.gridwidth = 3;
		mainFrame.add(firstname, constraints);
		constraints.gridwidth = 1;
		
		//Lastname
		constraints.gridx = 0;
		constraints.gridy = 2;
		mainFrame.add(ln, constraints);
		constraints.gridx = 1;
		constraints.gridwidth = 3;
		mainFrame.add(lastname, constraints);
		constraints.gridwidth = 1;
		
		//Gender
		constraints.gridx = 0;
		constraints.gridy = 3;
		mainFrame.add(g, constraints);
		constraints.gridx = 1;
		mainFrame.add(male, constraints);
		constraints.gridx = 2;
		constraints.gridwidth = 2;
		mainFrame.add(female, constraints);
		constraints.gridwidth = 1;
		
		//IC
		constraints.gridx = 0;
		constraints.gridy = 4;
		mainFrame.add(ic, constraints);
		constraints.gridx = 1;
		mainFrame.add(dateOfBirth, constraints);
		constraints.gridx = 2;
		mainFrame.add(codeStateOfBirth, constraints);
		constraints.gridx = 3;
		mainFrame.add(icLast4Digits, constraints);
		
		//Email
		constraints.gridx = 0;
		constraints.gridy = 5;
		mainFrame.add(e, constraints);
		constraints.gridx = 1;
		mainFrame.add(email, constraints);
		constraints.gridx = 2;
		mainFrame.add(eP, constraints);
		constraints.gridx = 3;
		mainFrame.add(emailProvider, constraints);
		
		//Phone Number
		constraints.gridx = 0;
		constraints.gridy = 6;
		mainFrame.add(pN, constraints);	
		constraints.gridx = 1;
		mainFrame.add(countryCode, constraints);
		constraints.gridx = 2;
		constraints.gridwidth = 2;
		mainFrame.add(phoneNo, constraints);
		
		//Error Message
		constraints.fill = GridBagConstraints.CENTER;
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridwidth = 4;
		mainFrame.add(error, constraints);
		
		setButton();
	}
	
	@Override
	protected void eventPerformed() {
		JTextField[] textField = new JTextField[]{
			firstname,
			lastname,
			dateOfBirth,
			icLast4Digits,
			email,
			emailProvider,
			phoneNo
		};	
		
		JRadioButton[] g = new JRadioButton[] {
			male,
			female
		};
		
		JButton[] button = new JButton[] {
			navigateButton,
			updateButton
		};
		
		event = new ProfileEvent(error, textField, g, codeStateOfBirth, button, ID);
		navigateButton.addActionListener(event);
		updateButton.addActionListener(event);
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
	protected void dispose() {
		mainFrame.dispose();
	}
	
	private void setButton() {
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBackground(Color.CYAN);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.PAGE_END;
		constraints.insets = new Insets(5,5,5,5);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		buttonPanel.add(updateButton, constraints);
		constraints.gridx = 1;
		buttonPanel.add(navigateButton, constraints);
		
		constraints.gridwidth = 4;
		constraints.gridx = 0;
		constraints.gridy = 8;
		mainFrame.add(buttonPanel, constraints);
	}
	
	protected boolean getUpdateStatus() {
		return event.getUpdateStatus();
	}
	
	public String getUpdatedInformation() {
		return event.getUpdatedInformation();
	}
	
	public void setSelectedButtonToEmpty() {
		event.setSelectedButtonToEmpty();
	}
}
