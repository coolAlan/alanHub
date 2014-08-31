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
//�������
public class Return extends JPanel{
	private User user;
	private ArrayList<Object> returnList; //���ڴ��δ�黹�鼮����
	private ArrayList<Object> returnbooks;
	
	public Return(){
		IReturnBookList returnBookList = new IReturnBookList();
		Message msg = returnBookList.getMessage();

		user = MyData.user;
		
		setLayout(new BorderLayout());
		
		JButton returnButton = new JButton("����",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		btnDecorate(returnButton);
		JButton cancelButton = new JButton("ȡ��",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		btnDecorate(cancelButton);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel(new GridLayout(2,1));
		
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel3.setOpaque(false);
		
		JLabel jlbStuNum = new JLabel("ѧ�ţ�"+user.getStdNumber());
		panel1.add(jlbStuNum);
		JLabel jlbName = new JLabel("������"+user.getUName());
		panel1.add(jlbName);
		
		panel2.add(returnButton);
		panel2.add(cancelButton);
		this.setOpaque(false);
		this.add(panel1 , BorderLayout.NORTH);
		
		returnList = msg.getData();
			
		Object[][] tableData = null;
		String[] columnTitle = { "����", "����", "ͼ����", "����", "��������" };

		DefaultTableModel tableModel = new DefaultTableModel(tableData,
				columnTitle);

		JTable jTable = new JTable(tableModel) {
			// ��������༭
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return true;
				} else {
					return false;
				}
			}
			// ���ñ��͸��
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
		        		//�������¼�
		            	for(int i=0; i<returnList.size(); i++){
		            		if(((Record)returnList.get(i)).isBFlag()){
		            			
		            			returnbooks.add((Record)returnList.get(i));	
		            			
		            		}
		            	}
		            	//ִ�л���
		            	IReturnBook rb = new IReturnBook(returnbooks);
		            	System.out.println(returnbooks.size());
		            	ImageIcon smallicon = new ImageIcon(getClass().getResource("/Image/����ɹ�.jpg"));
	        			JOptionPane.showMessageDialog(null, "����ɹ�~", "��ʾ" , JOptionPane.YES_OPTION, smallicon);
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
		btn.setBorderPainted(false);//��ť�����߿�
		btn.setFocusPainted(false);
		btn.addMouseListener(new myButtonListener(btn,"Image/button-normal.png","Image/button-click.png"));
		btn.setContentAreaFilled(false);
		btn.setPressedIcon(new ImageIcon(getClass().getResource("/Image/button-hold.png")));
	}  
}

