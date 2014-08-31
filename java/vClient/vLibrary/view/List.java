/*
 * Created on 2013-8-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vLibrary.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import vLibrary.biz.IBestList;
import vSchoolSys.common.Record;
import vSchoolSys.common.User;

/**
 * @author C5139
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//�鿴�񵥽���
public class List extends JPanel{
	private User iUser;
	private ArrayList<Object> sortkList;


	public List(User user) {
		setLayout(new BorderLayout());
		
		iUser = user;

		JPanel panel1 = new JPanel();
		panel1.setOpaque(false);
		JLabel jlbStuNum = new JLabel("ѧ�ţ�" + iUser.getStdNumber());
		panel1.add(jlbStuNum);
		JLabel jlbName = new JLabel("������" + iUser.getUName());
		panel1.add(jlbName);
		this.setOpaque(false);
		this.add(panel1 , BorderLayout.NORTH);
		
		IBestList bestList = new IBestList();
		sortkList = bestList.getMessage().getData();
		
		

			Object[][] tableData = null;
			String[] columnTitle = { "����","�����", "����", "����", "���Ĵ���" };

			DefaultTableModel tableModel = new DefaultTableModel(tableData,
					columnTitle);

			JTable jTable = new JTable(tableModel) {
				// ��������༭
				public boolean isCellEditable(int row, int column) {
					return false;
				}
				//���ñ��͸��
				public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
					Component c=super.prepareRenderer(renderer,row,column);
					if(c instanceof JComponent){
					((JComponent)c).setOpaque(false);
					}
					return c;
					}
			};
			jTable.setOpaque(false);

			JScrollPane jSP = new JScrollPane(jTable);
			jSP.setOpaque(false);
			jSP.getViewport().setOpaque(false); 
			jSP.setBorder(BorderFactory.createEmptyBorder());
			this.add(jSP , BorderLayout.CENTER);

			for (int i = 0; i < 10; i++) {
				tableModel.addRow(new Object[] {
 						i+1 ,((Record) sortkList.get(i)).getbID(),
						((Record) sortkList.get(i)).getbBookName(),
						((Record) sortkList.get(i)).getAuthor(),
						((Record) sortkList.get(i)).getFreq() });
			}
		
	}
}
