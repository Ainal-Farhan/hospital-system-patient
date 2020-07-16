package gui;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public abstract class GUI {
	protected JFrame mainFrame;
	
	protected abstract void displayGUI();
	protected abstract void setFrame();
	protected abstract void setFrameContent();
	protected abstract void eventPerformed();
	protected abstract void dispose();
	protected abstract String getSelectedButton();
	
	public GUI() {
		mainFrame = new JFrame();
		mainFrame.getContentPane().setBackground(Color.CYAN);
	}
	
	public void confirmationBeforeExit() {
		mainFrame.addWindowListener((WindowListener) new WindowAdapter(){
            public void windowClosing(WindowEvent evt){            	
                int option = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to exit ?", "Exit !",
                    JOptionPane.YES_NO_OPTION);

                if(option == JOptionPane.YES_OPTION) {
                	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }else{
                	mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
	}
}
