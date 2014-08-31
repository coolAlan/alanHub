package vSelectCourse.view;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vSchoolSys.common.Classroom;

public class EmptyRoomTable extends JPanel{
	private ArrayList<Object> roomList = null;
	private JTable cTable;						//可借教室表格
	DefaultTableModel roomTable;
	
	public EmptyRoomTable(ArrayList<Object> list) {
		roomList = list;
		Object[] title = {"可借教室"};
		Object[][] content = null;
		roomTable = new DefaultTableModel(content,
				title);
    	//表格不被修改函数
    	cTable = new JTable(roomTable) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		for (int i = 0; i < roomList.size(); i++) {
			roomTable.addRow(new Object[] {
					((String)roomList.get(i))
			});
		}
		this.setOpaque(false);
		JScrollPane js = new JScrollPane(cTable);
	    js.setPreferredSize(new Dimension(400, 300));
	    //设置js背景透明
	    js.setOpaque(false);   
	    js.getViewport().setOpaque(false); 
		this.add(js);
		this.setVisible(true);
		
	/**
	 * @param args
	 */
	}
	
	public JTable getcTable() {
		return cTable;
	}
	
	public DefaultTableModel getModel() {
		return roomTable;
	}
}
