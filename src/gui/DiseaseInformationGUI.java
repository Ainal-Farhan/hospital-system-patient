package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public class DiseaseInformationGUI extends GUI{
	private JComboBox<Object> disease;
	private JLabel diseaseLabel;
	private JButton nextButton;
	private JButton backButton;
	private JLabel iconLocation;
	private JTextArea diseaseDescription;
	private JLabel icon;
	private static DiseaseInformationEvent event;
	private int y;
	
	public DiseaseInformationGUI() {
		Object[] diseaseName = new Object[13];
		int i = 0;
		for (DISEASE_INFO disease : DISEASE_INFO.values()) { 
			diseaseName[i] = disease.getDiseaseName();
			i++;
		}
		
		disease = new JComboBox<Object> (diseaseName);
		
		diseaseLabel = new JLabel();
		nextButton = new JButton();
		backButton = new JButton();
		diseaseDescription = new JTextArea(25, 1);
		iconLocation = new JLabel();
		icon = new JLabel(new ImageIcon(DISEASE_INFO.D1.getIconLocation()));
		
		Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
		
		diseaseDescription.setText(DISEASE_INFO.D1.getBriefDescription());
		diseaseDescription.setEditable(false);
		diseaseDescription.setBackground(Color.WHITE);
		diseaseDescription.setBorder(border);

		setFrame();
		setFrameContent();
		event = new DiseaseInformationEvent(disease, iconLocation, diseaseDescription, icon);
	}

	@Override
	protected void displayGUI() {
		mainFrame.setVisible(true);
		eventPerformed();
	}

	@Override
	protected void setFrame() {
		mainFrame.setTitle("Disease Information");
		mainFrame.setLayout(new GridBagLayout());
		mainFrame.setSize(500, 300);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		confirmationBeforeExit();
	}

	@Override
	protected void setFrameContent() {
		y = 0;
		GridBagConstraints constraints = new GridBagConstraints();
		setJComboBox(constraints);
		setButton(constraints);
		setImageIcon(constraints);
	}

	@Override
	protected void eventPerformed() {
		disease.addActionListener(event);
		disease.addPropertyChangeListener(event);
		backButton.addActionListener(event);
		nextButton.addActionListener(event);
	}

	@Override
	protected void dispose() {
		mainFrame.dispose();
	}
	
	@Override
	protected String getSelectedButton() {
		return event.getSelectedButton();
	}
	
	public void setImageIcon(GridBagConstraints constraints) {
		JPanel imagePanel = new JPanel(new GridBagLayout());
		imagePanel.setBackground(Color.CYAN);
		
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		imagePanel.add(icon, constraints);
		
		constraints.gridx = 1;
		imagePanel.add(diseaseDescription, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = y;
		constraints.gridwidth = 3;
		addIntoFrame(imagePanel, constraints);
		
		y++;
	}
	
	public void setButton(GridBagConstraints constraints) {
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBackground(Color.CYAN);
		
		backButton.setText("back");
		nextButton.setText("next");
		
		backButton.setFocusPainted(false);
		nextButton.setFocusPainted(false);
		
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5,5,5,5);
		constraints.fill = GridBagConstraints.CENTER;
		constraints.gridx = 0;
		constraints.gridy = 0;
		buttonPanel.add(backButton, constraints);
		constraints.gridx = 1;
		buttonPanel.add(nextButton, constraints);
		
		constraints.gridy = --y;
		constraints.gridwidth = 2;
		addIntoFrame(buttonPanel, constraints);
		
		y++;
	}
	
	public void setJComboBox(GridBagConstraints constraints) {
		diseaseLabel.setText("Disease: ");
		disease.setEditable(true);
		disease.setBackground(Color.CYAN);
		
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5,5,5,5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = y;
		addIntoFrame(diseaseLabel, constraints);
		
		constraints.gridy = ++y;
		addIntoFrame(disease, constraints);
		
		y++;
	}
	
	public void addIntoFrame(Component component, GridBagConstraints constraints) {
		mainFrame.add(component, constraints);
	}
}
