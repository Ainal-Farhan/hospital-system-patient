package gui;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
class LimitedRowTextArea extends JTextArea {
	private int maxRows = 0;
	   
	   public void setMaxRows(int maxRows) {
	      this.maxRows = maxRows;
	   }

	   public int getMaxRows() {
	      return maxRows; 
	   }
	   
	   public void replaceSelection(String content) {
	       if (getMaxRows() > 0 && getLineCount() <= getMaxRows()) super.replaceSelection(content);
	       else this.getToolkit().beep();  
	   }   
	}
