package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class PatientFormGUI extends GUI{
	private JLabel[] label;
	private JRadioButton[] gender;
	private JButton[] button;
	private JTextField[] textField;
	private JLabel error;
	private ButtonGroup group;
	private JComboBox<String> stateCode;
	private static PatientFormEvent event;
	
	public PatientFormGUI() {
		label = new JLabel[] {
			new JLabel("Firstname"),
			new JLabel("Lastname"),
			new JLabel("NRIC"),
			new JLabel("\u2014"),
			new JLabel("\u2014"),
			new JLabel("Gender"),
			new JLabel("Age"),
			new JLabel("Email"),
			new JLabel("@"),
			new JLabel("Phone No."),
			new JLabel("+60 - "),
		};
		
		gender = new JRadioButton[] {
			new JRadioButton("male", true),
			new JRadioButton("female", false)
		};
		
		button = new JButton[] {
				new JButton("next"),
				new JButton("edit"),
		};
		
		textField = new JTextField[] {
				new JTextField(20),
				new JTextField(20),
				new JTextField(4),
				new JTextField(3),
				new JTextField(20),
				new JTextField(10),
				new JTextField(7)
		};
		
		label[8].setForeground(Color.BLUE);
		
		error = new JLabel("", SwingConstants.CENTER);
		error.setForeground(Color.RED);
		
		textField[2].setDocument(new JTextFieldLimit(6));
		textField[3].setDocument(new JTextFieldLimit(4));
		textField[6].setDocument(new JTextFieldLimit(10));
		
		button[1].setVisible(false);
		
		group = new ButtonGroup();
		for(int i = 0; i < gender.length; i++) {
			group.add(gender[i]);
			gender[i].setFocusPainted(false);
			gender[i].setBackground(Color.CYAN);;
		}
		
		String[] code = new String[16];
		
		int i = 0;
		
		for (STATE_CODE state : STATE_CODE.values()) {
			  code[i] = state.toString();
			  i++;
		}
		stateCode = new JComboBox<String>(code);
	
		setFrame();
		setFrameContent();
		
		event = new PatientFormEvent(textField, stateCode, gender, error, button);
	}
	
	@Override
	protected void setFrame() {
		mainFrame.setTitle("Patient Information");
		mainFrame.setLayout(new GridBagLayout());
		mainFrame.setSize(500, 300);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(true);
		confirmationBeforeExit();
	}
	
	@Override
	protected void setFrameContent() {
		setLabel();
		setButton();
	}
	
	@Override
	protected void eventPerformed() {
		event = new PatientFormEvent(textField, stateCode, gender, error, button);
		button[0].addActionListener(event);
		button[1].addActionListener(event);
	}
	
	@Override
	public void displayGUI() {
		mainFrame.setVisible(true);
		eventPerformed();
	}
	
	@Override
	public void dispose() {
		mainFrame.dispose();
	}
	
	@Override
	public String getSelectedButton() {
		return event.getSelectedButton();
	}
	
	public boolean getInformationStatus() {
		return event.getInformationStatus();
	}
	
	public String getPatientInformation() {
		return event.getPatientInformation();
	}
	
 	private void setLabel() {
		
		JPanel fn = new JPanel(new GridBagLayout());
		JPanel ln = new JPanel(new GridBagLayout());
		JPanel ic = new JPanel(new GridBagLayout());
		JPanel g = new JPanel(new GridBagLayout());
		JPanel em = new JPanel(new GridBagLayout());
		JPanel pn = new JPanel(new GridBagLayout());
		
		fn.setBackground(Color.CYAN);
		ln.setBackground(Color.CYAN);
		ic.setBackground(Color.CYAN);
		g.setBackground(Color.CYAN);
		em.setBackground(Color.CYAN);
		pn.setBackground(Color.CYAN);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		//firstname
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(5, 5, 0, 0);
		mainFrame.add(label[0], constraints);
		constraints.gridx = 1;
		constraints.insets = new Insets(5, 0, 0, 120);
		fn.add(textField[0], constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, 0, 0);
		mainFrame.add(fn);
		
		//lastname
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(5, 5, 0, 0);
		mainFrame.add(label[1], constraints);
		constraints.gridx = 1;
		constraints.insets = new Insets(5, 0, 5, 120);
		ln.add(textField[1], constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
		mainFrame.add(ln, constraints);
		
		//ic
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(5, 5, 0, 0);
		mainFrame.add(label[2], constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 1;
		ic.add(textField[2], constraints);
		constraints.gridx = 2;
		constraints.insets = new Insets(0, 3, 0, 3);
		ic.add(label[3], constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 3;
		ic.add(stateCode, constraints);
		constraints.gridx = 4;
		constraints.insets = new Insets(0, 3, 0, 3);
		ic.add(label[4], constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 5;
		ic.add(textField[3], constraints);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(5, 0, 5, 165);
		mainFrame.add(ic, constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		
		//gender
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new Insets(5, 5, 0, 0);
		mainFrame.add(label[5], constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 1;
		g.add(gender[0], constraints);
		constraints.gridx = 2;
		g.add(gender[1], constraints);
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.insets = new Insets(5, 0, 5, 210);
		mainFrame.add(g, constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		
		//email
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.insets = new Insets(5, 5, 0, 0);
		mainFrame.add(label[7], constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 1;
		em.add(textField[4], constraints);
		constraints.gridx = 2;
		constraints.insets = new Insets(0, 5, 0, 5);
		em.add(label[8], constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 3;
		em.add(textField[5], constraints);
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.insets = new Insets(5, 10, 5, 0);
		mainFrame.add(em, constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		
		//phone number
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.insets = new Insets(5, 5, 0, 0);
		mainFrame.add(label[9], constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 1;
		pn.add(label[10], constraints);
		constraints.gridx = 2;
		pn.add(textField[6], constraints);
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.insets = new Insets(5, 0, 5, 210);
		mainFrame.add(pn, constraints);
		constraints.insets = new Insets(0, 0, 0, 0);
		
		//error Label
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(0, 5, 0, 5);
		mainFrame.add(error, constraints);
	}
 	
	private void setButton() {
		JPanel panel = new JPanel(new GridBagLayout());
		
		panel.setBackground(Color.CYAN);
		
		for(int i  = 0; i < button.length; i++) {
			button[i].setFocusPainted(false);
		}
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.PAGE_END;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(5, 5, 5, 5);
		panel.add(button[0], constraints);
		
		constraints.gridx = 1;
		panel.add(button[1], constraints);
		
		constraints.gridy = 7;
		constraints.gridwidth = 2;
		mainFrame.add(panel, constraints);
	}
}
