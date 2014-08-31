/*
 * Created on 2013-9-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vLibrary.view;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @author Alan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MyCheckBoxEditor extends DefaultCellEditor implements ItemListener {

	private JCheckBox checkBox;  
	  
	    public MyCheckBoxEditor(JCheckBox checkBox) {  
	        super(checkBox);  
	    }  
	  
	    public Component getTableCellEditorComponent(JTable table, Object value,  
	            boolean isSelected, int row, int column) {  
	        if (value == null)  
	            return null;  
	        checkBox = (JCheckBox) value;  
	        checkBox.addItemListener(this);  
	        return (Component) value;  
	    }  
	  
	    public Object getCellEditorValue() {  
	    	checkBox.removeItemListener(this);  
	        return checkBox;  
	    }  
	
	public void itemStateChanged(ItemEvent arg0) {
		System.out.println("check11");
		
	}

}
