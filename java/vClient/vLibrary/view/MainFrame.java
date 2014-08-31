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

//������
public class MainFrame extends JPanel {
	private User user = MyData.user;
	private CardLayout cardLayout = new CardLayout(20,10);
	private JPanel cardPanel = new JPanel();
	
	Box flowPanel = Box.createHorizontalBox();
	JButton jbtBorrow = new JButton("����",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
    
	JButton jbtReturn = new JButton("����",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
	
	JButton jbtCheck = new JButton("�鿴��¼",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
	JButton jbtList = new JButton("�鿴��",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
	
	
	//���캯��
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
		cardPanel.add("����", new Borrow(user));
		cardPanel.add("����", new Return());
		cardPanel.add("�鿴��¼", new Check());
		cardPanel.add("�鿴��", new List(user));
		cardPanel.setOpaque(false);
		this.add(cardPanel,BorderLayout.CENTER);
		this.setOpaque(false);
		this.setVisible(true);
		

		
		//������Ӧ
		jbtBorrow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardPanel.removeAll();
				cardPanel.add("����", new Borrow(user));
				cardPanel.add("����", new Return());
				cardPanel.add("�鿴��¼", new Check());
				cardPanel.add("�鿴��", new List(user));
				cardLayout.show(cardPanel, "����");
			}
		});
		
		//������Ӧ
		jbtReturn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardPanel.removeAll();
				cardPanel.add("����", new Borrow(user));
				cardPanel.add("����", new Return());
				cardPanel.add("�鿴��¼", new Check());
				cardPanel.add("�鿴��", new List(user));
				cardLayout.show(cardPanel, "����");
			}
		});
		
		//�鿴��¼��Ӧ
		jbtCheck.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardPanel.removeAll();
				cardPanel.add("����", new Borrow(user));
				cardPanel.add("����", new Return());
				cardPanel.add("�鿴��¼", new Check());
				cardPanel.add("�鿴��", new List(user));
				cardLayout.show(cardPanel, "�鿴��¼");
			}
		});
		
		//�鿴����Ӧ
		jbtList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardPanel.removeAll();
				cardPanel.add("����", new Borrow(user));
				cardPanel.add("����", new Return());
				cardPanel.add("�鿴��¼", new Check());
				cardPanel.add("�鿴��", new List(user));
				cardLayout.show(cardPanel, "�鿴��");
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
			g.drawImage(icon0.getImage(), x, y, getSize().width,getSize().height, this);// ͼƬ���Զ�����
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
		btn.setBorderPainted(false);//��ť�����߿�
		btn.setFocusPainted(false);
		btn.addMouseListener(new myButtonListener(btn,"Image/button-normal.png","Image/button-click.png"));
		btn.setContentAreaFilled(false);
		btn.setPressedIcon(new ImageIcon(getClass().getResource("/Image/button-hold.png")));
	}

}
