/*
 * Created on 2013-8-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vLibrary.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import publicData.MyData;
import publicData.myButtonListener;

import vLibrary.biz.IReturnBook;
import vLibrary.biz.IReturnBookList;

import vSchoolSys.common.Book;
import vSchoolSys.common.Message;
import vSchoolSys.common.Record;

import vSchoolSys.common.User;




/**
 * @author C5139
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//还书界面
public class Return extends JPanel{
	private User user;
	private ArrayList<Object> returnList; //用于存放未归还书籍对象
	private ArrayList<Object> returnbooks;
	
	public Return(){
		IReturnBookList returnBookList = new IReturnBookList();
		Message msg = returnBookList.getMessage();

		user = MyData.user;
		
		setLayout(new BorderLayout());
		
		JButton returnButton = new JButton("还书",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		btnDecorate(returnButton);
		JButton cancelButton = new JButton("取消",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		btnDecorate(cancelButton);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel(new GridLayout(2,1));
		
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel3.setOpaque(false);
		
		JLabel jlbStuNum = new JLabel("学号："+user.getStdNumber());
		panel1.add(jlbStuNum);
		JLabel jlbName = new JLabel("姓名："+user.getUName());
		panel1.add(jlbName);
		
		panel2.add(returnButton);
		panel2.add(cancelButton);
		this.setOpaque(false);
		this.add(panel1 , BorderLayout.NORTH);
		
		returnList = msg.getData();
			
		Object[][] tableData = null;
		String[] columnTitle = { "还书", "书名", "图书编号", "作者", "借书日期" };

		DefaultTableModel tableModel = new DefaultTableModel(tableData,
				columnTitle);

		JTable jTable = new JTable(tableModel) {
			// 表格不允许被编辑
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return true;
				} else {
					return false;
				}
			}
			// 设置表格透明
			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (c instanceof JComponent) {
					((JComponent) c).setOpaque(false);
				}
				return c;
			}
		};
		jTable.getColumnModel().getColumn(0).setCellEditor(new MyCheckBoxEditor(new JCheckBox())); 
		jTable.getColumnModel().getColumn(0).setCellRenderer(new MyCheckBoxRenderer());
		
		
		jTable.setRowHeight(30);
		
		JScrollPane jSP = new JScrollPane(jTable);
		jSP.setOpaque(false);
		jSP.getViewport().setOpaque(false); 
		jSP.setBorder(BorderFactory.createEmptyBorder());
		panel3.add(jSP , BorderLayout.CENTER);
		panel3.add(panel2 , BorderLayout.SOUTH);
		this.add(panel3);

		
		for (int i = 0; i < returnList.size(); i++) {
			final JCheckBox check = new JCheckBox();
			tableModel
					.addRow(new Object[] {
							check,
							((Record)returnList.get(i)).getbBookName(),
							((Record)returnList.get(i)).getbID(),
							((Record)returnList.get(i)).getAuthor(),
							((Record)returnList.get(i)).getblBorDate()
						 });
			check.addItemListener(new myItemListener(i ,check));
			}
		
		returnbooks = new ArrayList<Object>();
		returnButton.addActionListener(new ActionListener()  
		        {  
		            public void actionPerformed(ActionEvent e)  
		            { 
		        		//处理还书事件
		            	for(int i=0; i<returnList.size(); i++){
		            		if(((Record)returnList.get(i)).isBFlag()){
		            			
		            			returnbooks.add((Record)returnList.get(i));	
		            			
		            		}
		            	}
		            	//执行还书
		            	IReturnBook rb = new IReturnBook(returnbooks);
		            	System.out.println(returnbooks.size());
		            	ImageIcon smallicon = new ImageIcon(getClass().getResource("/Image/还书成功.jpg"));
	        			JOptionPane.showMessageDialog(null, "还书成功~", "提示" , JOptionPane.YES_OPTION, smallicon);
	        			returnbooks.clear();
				Return.this.setVisible(false);
			}

		});
		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Return.this.setVisible(false);
				// Return.this.dispose();

			}

		});
			}
	
	  class  myItemListener implements ItemListener{
	  	private int i =0;
		private JCheckBox check;
		public myItemListener(int i ,JCheckBox check){
			this.i = i;
			this.check = check;
		}

		public void itemStateChanged(ItemEvent arg0) {
			if(check.isSelected()){
			((Record)returnList.get(i)).setBFlag(true);
			}
			else
				((Record)returnList.get(i)).setBFlag(false);
		}
	  	
	  }
	  public void btnDecorate(JButton btn){
		//btn.setPreferredSize(new Dimension(5,29));
		btn.setHorizontalTextPosition(0);
		btn.setBackground(new Color(0,0,0));
		btn.setOpaque(false);
		btn.setBorderPainted(false);//按钮不画边框
		btn.setFocusPainted(false);
		btn.addMouseListener(new myButtonListener(btn,"Image/button-normal.png","Image/button-click.png"));
		btn.setContentAreaFilled(false);
		btn.setPressedIcon(new ImageIcon(getClass().getResource("/Image/button-hold.png")));
	}  
}

