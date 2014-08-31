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
	private JTable cTable;						//�ɽ���ұ��
	DefaultTableModel roomTable;
	
	public EmptyRoomTable(ArrayList<Object> list) {
		roomList = list;
		Object[] title = {"�ɽ����"};
		Object[][] content = null;
		roomTable = new DefaultTableModel(content,
				title);
    	//��񲻱��޸ĺ���
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
	    //����js����͸��
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
