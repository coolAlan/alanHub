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

import publicData.MyData;

import vLibrary.biz.IRecordCheck;
import vSchoolSys.common.Message;
import vSchoolSys.common.Record;
import vSchoolSys.common.User;

/**
 * @author C5139
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//查看借书记录界面
public class Check extends JPanel{
	private User cUser;
	private ArrayList<Object> checkList;
	//private JPanel checkPanel = new JPanel();
	
	public Check(){
		setLayout(new BorderLayout());
		
		IRecordCheck record = new IRecordCheck();
		Message msg = record.getMessage();
		
		cUser = MyData.user;

		JPanel jPanel1 = new JPanel();
		jPanel1.setOpaque(false);
		JLabel jlbStuNum = new JLabel("学号："+ cUser.getStdNumber());
		jPanel1.add(jlbStuNum);
		JLabel jlbName = new JLabel("姓名："+ cUser.getUName());
		jPanel1.add(jlbName);
		
		checkList = msg.getData();
			Object[][] tableData = null;
			String[] columnTitle = { "书名", "图书编号", "借书时间", "还书时间" };

			DefaultTableModel tableModel = new DefaultTableModel(tableData,
					columnTitle);

			JTable jTable = new JTable(tableModel) {
				// 表格不允许被编辑
				public boolean isCellEditable(int row, int column) {
					return false;
				}
				//设置表格透明
				public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
					Component c=super.prepareRenderer(renderer,row,column);
					if(c instanceof JComponent){
					((JComponent)c).setOpaque(false);
					}
					return c;
					}
			};
			 jTable.setOpaque(false); 
			
			jPanel1.setOpaque(false);
			this.add(jPanel1 , BorderLayout.NORTH);
			this.setOpaque(false);
			JScrollPane jSP = new JScrollPane(jTable) ;
			jSP.setOpaque(false);
			jSP.getViewport().setOpaque(false); 
			jSP.setBorder(BorderFactory.createEmptyBorder());
			this.add(jSP, BorderLayout.CENTER);

			for (int i = 0; i < checkList.size(); i++) {
				tableModel.addRow(new Object[] {
						((Record) checkList.get(i)).getbBookName(),
						((Record) checkList.get(i)).getbID(),
						((Record) checkList.get(i)).getblBorDate(),
						((Record) checkList.get(i)).getblRetDate() });
			}
		}
	}

