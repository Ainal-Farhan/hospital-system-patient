package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

@SuppressWarnings("serial")
public class DiseaseInformationEvent extends PlainDocument implements ActionListener, PropertyChangeListener{
	JComboBox<Object> comboBox;
	ComboBoxModel<Object> model;
	JTextComponent editor;
	
	private JLabel iconLocation;
	private JTextArea diseaseDescription;
	private JLabel icon;
	
	boolean selecting;
	boolean hidePopupOnFocusLoss;
	boolean hitBackspace;
	boolean hitBackspaceOnSelection;
	boolean hitEnter;
	
	private String selectedButton;
	
	KeyListener editorKeyListener;
	FocusListener editorFocusListener;
	
	public DiseaseInformationEvent(JComboBox<Object> comboBox, JLabel iconLocation, JTextArea diseaseDescription, JLabel icon) {
		this.comboBox = comboBox;
		this.selecting = false;
		this.hitBackspace = false;
		this.hitEnter = false;
		this.model = comboBox.getModel();
		this.iconLocation = iconLocation;
		this.diseaseDescription = diseaseDescription;
		this.icon = icon;
		
		editorKeyListener = new KeyAdapter() {
	        public void keyPressed(KeyEvent e) {
	            if (comboBox.isDisplayable()) comboBox.setPopupVisible(true);
	            
	            comboBox.setBackground(Color.WHITE);
	            hitBackspace=false;
	            
	            switch (e.getKeyCode()) {
	                case KeyEvent.VK_BACK_SPACE : 
	                	hitBackspace=true;
	                	hitBackspaceOnSelection=editor.getSelectionStart()!=editor.getSelectionEnd();
	                	break;
	                case KeyEvent.VK_DELETE : 
	                	e.consume();
	                	comboBox.getToolkit().beep();
	                	break;
	            }
	        }
	    };
	    
	    editorFocusListener = new FocusAdapter() {
	        public void focusGained(FocusEvent e) {
	            highlightCompletedText(0);
	        }
	    };
	    
	    this.selectedButton = "";
	    
	    configureEditor(comboBox.getEditor());
	    
	    Object selected = comboBox.getSelectedItem();
	    if (selected!=null) setText(selected.toString());
	    highlightCompletedText(0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!selecting) highlightCompletedText(0);
		
		selectedButton = e.getActionCommand();
		
		if(selectedButton.equalsIgnoreCase("back"))
			return;
		
		else if(selectedButton.equalsIgnoreCase("next")) {
			for(DISEASE_INFO d: DISEASE_INFO.values()) {
				if(comboBox.getSelectedItem().toString().equalsIgnoreCase(d.getDiseaseName())) {
					iconLocation.setText(d.getIconLocation());
					diseaseDescription.setText(d.getBriefDescription());
					setImageIcon();
					break;
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("editor")) configureEditor((ComboBoxEditor) evt.getNewValue());
        if (evt.getPropertyName().equals("model")) model = (ComboBoxModel<Object>) evt.getNewValue();
	}
	
	private void setImageIcon() {
		ImageIcon iconImage = new ImageIcon(iconLocation.getText());
		icon.setIcon(iconImage);
	}
	
	private void setText(String text) {
	    try {
	        // remove all text and insert the completed string
	    	super.remove(0, getLength());
	    	super.insertString(0, text, null);
	    } catch (BadLocationException e) {
	        throw new RuntimeException(e.toString());
	    }
	}
	
	private void highlightCompletedText(int start) {
	    editor.setCaretPosition(getLength());
	    editor.moveCaretPosition(start);
	}
	
	void configureEditor(ComboBoxEditor newEditor) {
	    if (editor != null) {
	        editor.removeKeyListener(editorKeyListener);
	        editor.removeFocusListener(editorFocusListener);
	    }
	
	    if (newEditor != null) {
	        editor = (JTextComponent) newEditor.getEditorComponent();
	        editor.addKeyListener(editorKeyListener);
	        editor.addFocusListener(editorFocusListener);
	        editor.setDocument(this);
	    }
	}
	
	public void remove(int offs, int len) throws BadLocationException {
	    // return immediately when selecting an item
	    if (selecting) return;
	    if (hitBackspace) {
	        // user hit backspace => move the selection backwards
	        // old item keeps being selected
	        if (offs>0) {
	            if (hitBackspaceOnSelection) offs--;
	        } else {
	            // User hit backspace with the cursor positioned on the start => beep
	            comboBox.getToolkit().beep(); // when available use: UIManager.getLookAndFeel().provideErrorFeedback(comboBox);
	        }
	        highlightCompletedText(offs);
	    } else {
	        super.remove(offs, len);
	    }
	}
	
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	    // return immediately when selecting an item
	    if (selecting) return;
	    // insert the string into the document
	    super.insertString(offs, str, a);
	    // lookup and select a matching item
	    Object item = lookupItem(getText(0, getLength()));
	    if (item != null) {
	        setSelectedItem(item);
	    } else {
	        // keep old item selected if there is no match
	        item = comboBox.getSelectedItem();
	        // imitate no insert (later on offs will be incremented by str.length(): selection won't move forward)
	        offs = offs-str.length();
	        // provide feedback to the user that his input has been received but can not be accepted
	        comboBox.getToolkit().beep(); // when available use: UIManager.getLookAndFeel().provideErrorFeedback(comboBox);
	    }
	    setText(item.toString());
	    // select the completed part
	    highlightCompletedText(offs+str.length());
	}
	
	private void setSelectedItem(Object item) {
	    selecting = true;
	    model.setSelectedItem(item);
	    selecting = false;
	}
	
	private Object lookupItem(String pattern) {
	    Object selectedItem = model.getSelectedItem();
	    // only search for a different item if the currently selected does not match
	    if (selectedItem != null && startsWithIgnoreCase(selectedItem.toString(), pattern)) {
	        return selectedItem;
	    } else {
	        // iterate over all items
	        for (int i=0, n=model.getSize(); i < n; i++) {
	            Object currentItem = model.getElementAt(i);
	            // current item starts with the pattern?
	            if (currentItem != null && startsWithIgnoreCase(currentItem.toString(), pattern)) {
	                return currentItem;
	            }
	        }
	    }
	    // no item starts with the pattern => return null
	    return null;
	}
	
	// checks if str1 starts with str2 - ignores case
	private boolean startsWithIgnoreCase(String str1, String str2) {
	    return str1.toUpperCase().startsWith(str2.toUpperCase());
	}
	
	public String getSelectedButton() {
		return selectedButton;
	}
}
