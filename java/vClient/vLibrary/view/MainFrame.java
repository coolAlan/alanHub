/*
 * Created on 2013-8-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vLibrary.view;

/**
 * @author C5132
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import publicData.MyData;
import publicData.myButtonListener;
import vLibrary.biz.IRecordCheck;
import vLibrary.biz.IReturnBook;
import vLibrary.biz.IReturnBookList;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;

//主界面
public class MainFrame extends JPanel {
	private User user = MyData.user;
	private CardLayout cardLayout = new CardLayout(20,10);
	private JPanel cardPanel = new JPanel();
	
	Box flowPanel = Box.createHorizontalBox();
	JButton jbtBorrow = new JButton("借书",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
    
	JButton jbtReturn = new JButton("还书",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
	
	JButton jbtCheck = new JButton("查看记录",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
	JButton jbtList = new JButton("查看榜单",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
	
	
	//构造函数
	public MainFrame(){
		
		cardPanel.setLayout(cardLayout);

		btnDecorate(jbtBorrow);
		btnDecorate(jbtReturn);
		btnDecorate(jbtCheck);
		btnDecorate(jbtList);
		flowPanel.add(Box.createHorizontalStrut(5));
		flowPanel.add(jbtBorrow);
		flowPanel.add(Box.createHorizontalStrut(5));
		flowPanel.add(jbtReturn);
		flowPanel.add(Box.createHorizontalStrut(5));
		flowPanel.add(jbtCheck);
		flowPanel.add(Box.createHorizontalStrut(5));
		flowPanel.add(jbtList);
		flowPanel.setOpaque(false);
		this.setLayout(new BorderLayout());
		add(flowPanel,BorderLayout.NORTH);
		cardPanel.add("借书", new Borrow(user));
		cardPanel.add("还书", new Return());
		cardPanel.add("查看记录", new Check());
		cardPanel.add("查看榜单", new List(user));
		cardPanel.setOpaque(false);
		this.add(cardPanel,BorderLayout.CENTER);
		this.setOpaque(false);
		this.setVisible(true);
		

		
		//借书响应
		jbtBorrow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardPanel.removeAll();
				cardPanel.add("借书", new Borrow(user));
				cardPanel.add("还书", new Return());
				cardPanel.add("查看记录", new Check());
				cardPanel.add("查看榜单", new List(user));
				cardLayout.show(cardPanel, "借书");
			}
		});
		
		//还书响应
		jbtReturn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardPanel.removeAll();
				cardPanel.add("借书", new Borrow(user));
				cardPanel.add("还书", new Return());
				cardPanel.add("查看记录", new Check());
				cardPanel.add("查看榜单", new List(user));
				cardLayout.show(cardPanel, "还书");
			}
		});
		
		//查看记录响应
		jbtCheck.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardPanel.removeAll();
				cardPanel.add("借书", new Borrow(user));
				cardPanel.add("还书", new Return());
				cardPanel.add("查看记录", new Check());
				cardPanel.add("查看榜单", new List(user));
				cardLayout.show(cardPanel, "查看记录");
			}
		});
		
		//查看榜单响应
		jbtList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardPanel.removeAll();
				cardPanel.add("借书", new Borrow(user));
				cardPanel.add("还书", new Return());
				cardPanel.add("查看记录", new Check());
				cardPanel.add("查看榜单", new List(user));
				cardLayout.show(cardPanel, "查看榜单");
			}
		});
	}
	class MyPanel extends JPanel{
		private String address;
		public MyPanel(String addr){
			address = addr;
		}
		public void paintComponent(Graphics g) {
			int x = 0, y = 0;
			ImageIcon icon0 = new ImageIcon(getClass().getResource(address));
			g.drawImage(icon0.getImage(), x, y, getSize().width,getSize().height, this);// 图片会自动缩放
		}
	}
	
	

	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void btnDecorate(JButton btn){
		btn.setPreferredSize(new Dimension(5,29));
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
