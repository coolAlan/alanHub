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

//借书界面
public class Borrow extends JPanel{
	private String bookName;
	private JTextField inputBookName = new JTextField(30);
	private User bUser;
	//private JPanel borrowPanel = new JPanel();
	public  CardLayout c;
	public JPanel pane;
	public Borrow(User user){
		bUser = user;    //传入当前用户
		c = new CardLayout();
		pane = new JPanel(c);
		setLayout(new GridLayout(3,1));
		pane.setOpaque(false);
		JPanel panel1 = new JPanel();  //用于放置“学号”、“姓名”Label
		JPanel panel2 = new JPanel(); //用于放置输入书名的JTextField
		JPanel panel3 = new JPanel(); //用于放置“搜索”和“取消”按钮 
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel3.setOpaque(false);
		JLabel jlbStuNum = new JLabel("学号：" + user.getStdNumber());
		JLabel jlbName = new JLabel("姓名："+user.getUName());
		panel1.add(jlbStuNum);
		panel1.add(jlbName);
		
		JLabel jlbInputBook = new JLabel("书名：");
		
		panel2.add(jlbInputBook);
		panel2.add(inputBookName);
		
		bookName = inputBookName.getText().trim(); //获得输入的书名
		
		JButton jbtSearch = new JButton("搜索",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		btnDecorate(jbtSearch);
		JButton jbtCancel = new JButton("取消",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		btnDecorate(jbtCancel);
		panel3.add(jbtSearch);
		panel3.add(jbtCancel);
		
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.setOpaque(false);
		pane.add(this,"搜索");
		this.setVisible(true);
		
		//搜索响应
		jbtSearch.addActionListener( new SearchAction());
	}
	
	private class SearchAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			bookName = inputBookName.getText().trim(); //获得输入的书名
			ILibSearchBook libSearch = new ILibSearchBook(bookName,1);//发送消息
			Message msg = libSearch.getMessage();
			
			//若在数据库中搜索到相应图书，则显示图书信息
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
				JOptionPane.showMessageDialog(null, "查无此书 !", "sorry", JOptionPane.YES_OPTION, icon);
			}

		}
	}
	
	//返回所搜索书名
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
		
		//构造函数
		public SearchResult(User user , ArrayList<Object> booklist){
			sUser = user;
			
			setLayout(new GridLayout(4,1));
			
			JLabel jlbStuNum = new JLabel("学号："+user.getStdNumber());
			JLabel jlbName = new JLabel("姓名："+user.getUName());
			JButton borrowButton = new JButton("借书",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
			btnDecorate(borrowButton);
			JButton cancelButton = new JButton("取消",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
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
				public boolean isCellEditable(int row, int column) {// 表格不允许被编辑
					if (column == 0) {
						return true;
					} else {
						return false;
					}
				}
			};
			
			Object[][] tableData = null;
			String[] columnTitle = { "借阅", "图书编号", "书名", "总数", "剩余数量", "作者" };
			
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
	        		//处理借书事件
	            	for(int i=0; i<bookList.size(); i++){ 
	            		System.out.println(((Book)bookList.get(i)).getbFlag());
	            		if(((Book)bookList.get(i)).getbFlag()){  //若书籍被选中，则进行借书操作 
	            			//图书的剩余数量不为零
	            			if(((Book)bookList.get(i)).getbRemain()!=0&&((Book)bookList.get(i)).getState()==0){//若该书籍还有剩余并且该用户没有未归还的同种书籍，则允许借阅
	            				//将多选的书加入列表
	            				borrowBooks.add(bookList.get(i));
	            			}
	            			
	            		}
	            	}
	            	Icon smallicon = new ImageIcon(getClass().getResource("/Image/info_blue.png"));
	            	if(borrowBooks.size()!=0){
	            	IBorrowBook borrow = new IBorrowBook(borrowBooks);
	    			JOptionPane.showMessageDialog(null, "借书成功~", "提示" , JOptionPane.YES_OPTION, smallicon);
	    			borrowBooks.clear();
	            	}
	            	else{
	            		JOptionPane.showMessageDialog(null, "请选择至少一本书！", "提示" , JOptionPane.YES_OPTION, smallicon);
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
					c.show(pane,"搜索");
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
					if(((Book)bookList.get(i)).getbRemain()==0){ //书籍剩余为0的情况
	    				Icon smallicon = new ImageIcon(getClass().getResource("/Image/4.png"));
	        			JOptionPane.showMessageDialog(null, "这本书被借光啦。。。", "提示" , JOptionPane.YES_OPTION, smallicon);
	        			check.setSelected(false);
					}
	    			else if(((Book)bookList.get(i)).getState()==1){  //该用户没有未归还的同种书籍
	    				Icon smallicon = new ImageIcon(getClass().getResource("/Image/4.png"));
	        			JOptionPane.showMessageDialog(null, "你已经借过这本书啦。。。", "提示" , JOptionPane.YES_OPTION, smallicon);
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
		btn.setBorderPainted(false);//按钮不画边框
		btn.setFocusPainted(false);
		btn.addMouseListener(new myButtonListener(btn,"Image/button-normal.png","Image/button-click.png"));
		btn.setContentAreaFilled(false);
		btn.setPressedIcon(new ImageIcon(getClass().getResource("/Image/button-hold.png")));
	}
}
