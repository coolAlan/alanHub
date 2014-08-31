/*
 * Created on 2013-8-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vLibrary.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import publicData.myButtonListener;

import vLibrary.biz.IBorrowBook;
import vLibrary.biz.ILibSearchBook;
import vSchoolSys.common.Book;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;

//import Client.MainFrame.BorrowAction;

/**
 * @author C5139
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

//�������
public class Borrow extends JPanel{
	private String bookName;
	private JTextField inputBookName = new JTextField(30);
	private User bUser;
	//private JPanel borrowPanel = new JPanel();
	public  CardLayout c;
	public JPanel pane;
	public Borrow(User user){
		bUser = user;    //���뵱ǰ�û�
		c = new CardLayout();
		pane = new JPanel(c);
		setLayout(new GridLayout(3,1));
		pane.setOpaque(false);
		JPanel panel1 = new JPanel();  //���ڷ��á�ѧ�š�����������Label
		JPanel panel2 = new JPanel(); //���ڷ�������������JTextField
		JPanel panel3 = new JPanel(); //���ڷ��á��������͡�ȡ������ť 
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel3.setOpaque(false);
		JLabel jlbStuNum = new JLabel("ѧ�ţ�" + user.getStdNumber());
		JLabel jlbName = new JLabel("������"+user.getUName());
		panel1.add(jlbStuNum);
		panel1.add(jlbName);
		
		JLabel jlbInputBook = new JLabel("������");
		
		panel2.add(jlbInputBook);
		panel2.add(inputBookName);
		
		bookName = inputBookName.getText().trim(); //������������
		
		JButton jbtSearch = new JButton("����",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		btnDecorate(jbtSearch);
		JButton jbtCancel = new JButton("ȡ��",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		btnDecorate(jbtCancel);
		panel3.add(jbtSearch);
		panel3.add(jbtCancel);
		
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.setOpaque(false);
		pane.add(this,"����");
		this.setVisible(true);
		
		//������Ӧ
		jbtSearch.addActionListener( new SearchAction());
	}
	
	private class SearchAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			bookName = inputBookName.getText().trim(); //������������
			ILibSearchBook libSearch = new ILibSearchBook(bookName,1);//������Ϣ
			Message msg = libSearch.getMessage();
			
			//�������ݿ�����������Ӧͼ�飬����ʾͼ����Ϣ
			if (msg.getData().size()!=0 ) {
				Borrow.this.removeAll();
				//SearchResult searchResult = new SearchResult(bUser, msg.getData());
				Borrow.this.setVisible(false);
				setLayout(new GridLayout(1,1));
				Borrow.this.add( new SearchResult(bUser, msg.getData()));
				//Borrow.this.setVisible(false);
			}
			else{
				Icon icon = new ImageIcon(getClass().getResource("/Image/info_blue.png"));
				JOptionPane.showMessageDialog(null, "���޴��� !", "sorry", JOptionPane.YES_OPTION, icon);
			}

		}
	}
	
	//��������������
	public String getBookName() {
		return bookName;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public class SearchResult extends JPanel{
		private ArrayList<Object> bookList;
		private ArrayList<Object> borrowBooks = new ArrayList<Object>();
		private User sUser;
		
		//���캯��
		public SearchResult(User user , ArrayList<Object> booklist){
			sUser = user;
			
			setLayout(new GridLayout(4,1));
			
			JLabel jlbStuNum = new JLabel("ѧ�ţ�"+user.getStdNumber());
			JLabel jlbName = new JLabel("������"+user.getUName());
			JButton borrowButton = new JButton("����",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
			btnDecorate(borrowButton);
			JButton cancelButton = new JButton("ȡ��",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
			btnDecorate(cancelButton);
			JPanel jLabelPanel1 = new JPanel();
			JPanel jLabelPanel2 = new JPanel();
			jLabelPanel1.setOpaque(false);
			jLabelPanel2.setOpaque(false);
			
			jLabelPanel1.add(jlbStuNum);
			jLabelPanel1.add(jlbName);
			jLabelPanel2.add(borrowButton);
			jLabelPanel2.add(cancelButton);
			
			this.add(jLabelPanel1);
			
			bookList = booklist;
			JTable jTable = new JTable() {
				public boolean isCellEditable(int row, int column) {// ��������༭
					if (column == 0) {
						return true;
					} else {
						return false;
					}
				}
			};
			
			Object[][] tableData = null;
			String[] columnTitle = { "����", "ͼ����", "����", "����", "ʣ������", "����" };
			
			DefaultTableModel tableModel = new DefaultTableModel(tableData,
					columnTitle);
			jTable.setModel(tableModel);
			//JCheckBox 
			jTable.getColumnModel().getColumn(0).setCellEditor(new MyCheckBoxEditor(new JCheckBox())); 
			jTable.getColumnModel().getColumn(0).setCellRenderer(new MyCheckBoxRenderer());
			
			
			jTable.setRowHeight(30);

			this.add(new JScrollPane(jTable));
			this.add(jLabelPanel2);

			for (int i = 0; i < bookList.size(); i++) {
				final JCheckBox check = new JCheckBox();
				tableModel
						.addRow(new Object[] {
								check,
								((Book) bookList.get(i)).getbID(),
								((Book) bookList.get(i)).getbName(),
								new Integer(((Book) bookList.get(i)).getbSum())
										.toString(),
								new Integer(((Book) bookList.get(i)).getbRemain())
									.toString(),
							((Book) bookList.get(i)).getbAuthor() });
				check.addItemListener(new myItemListener(i ,check));
				}
			
			 

			borrowButton.addActionListener(new ActionListener()  
	        {  
	            public void actionPerformed(ActionEvent e)  
	            { 
	        		//��������¼�
	            	for(int i=0; i<bookList.size(); i++){ 
	            		System.out.println(((Book)bookList.get(i)).getbFlag());
	            		if(((Book)bookList.get(i)).getbFlag()){  //���鼮��ѡ�У�����н������ 
	            			//ͼ���ʣ��������Ϊ��
	            			if(((Book)bookList.get(i)).getbRemain()!=0&&((Book)bookList.get(i)).getState()==0){//�����鼮����ʣ�ಢ�Ҹ��û�û��δ�黹��ͬ���鼮�����������
	            				//����ѡ��������б�
	            				borrowBooks.add(bookList.get(i));
	            			}
	            			
	            		}
	            	}
	            	Icon smallicon = new ImageIcon(getClass().getResource("/Image/info_blue.png"));
	            	if(borrowBooks.size()!=0){
	            	IBorrowBook borrow = new IBorrowBook(borrowBooks);
	    			JOptionPane.showMessageDialog(null, "����ɹ�~", "��ʾ" , JOptionPane.YES_OPTION, smallicon);
	    			borrowBooks.clear();
	            	}
	            	else{
	            		JOptionPane.showMessageDialog(null, "��ѡ������һ���飡", "��ʾ" , JOptionPane.YES_OPTION, smallicon);
	            	}
	    			//SearchResult.this.setVisible(false);
	            }
	        });
			cancelButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					SearchResult.this.removeAll();
				//	setLayout(new GridLayout(1,1));
					//SearchResult.this.add(new Borrow(sUser));
					SearchResult.this.setVisible(false);
				//	Borrow.this.setVisible(true);
				//	SearchResult.this.add(new Borrow(sUser));
				//	SearchResult.this.setVisible(true);
					//Borrow.this.setVisible(true);
					c.show(pane,"����");
				}
				
			});
			this.setOpaque(false);
			this.setVisible(true);
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
					System.out.println("is selected");
					if(((Book)bookList.get(i)).getbRemain()==0){ //�鼮ʣ��Ϊ0�����
	    				Icon smallicon = new ImageIcon(getClass().getResource("/Image/4.png"));
	        			JOptionPane.showMessageDialog(null, "�Ȿ�鱻�����������", "��ʾ" , JOptionPane.YES_OPTION, smallicon);
	        			check.setSelected(false);
					}
	    			else if(((Book)bookList.get(i)).getState()==1){  //���û�û��δ�黹��ͬ���鼮
	    				Icon smallicon = new ImageIcon(getClass().getResource("/Image/4.png"));
	        			JOptionPane.showMessageDialog(null, "���Ѿ�����Ȿ����������", "��ʾ" , JOptionPane.YES_OPTION, smallicon);
	        			check.setSelected(false);
	    			}
	    			else
	    				((Book) bookList.get(i)).setbFlag(true);
				}
				else
					((Book) bookList.get(i)).setbFlag(false);
				
				
			}
			
		}
	}
	public void btnDecorate(JButton btn){
		
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
