/*
 * Created on 2013-8-26
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vEshop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import publicData.myButtonListener;

import vSchoolSys.common.ShopCartGoods;

/**
 * @author zhaoziqi
 *
 * 
 */
//�鼮���촰��
public class Book {

	
	//�������
	private JPanel p=new JPanel();
	
	
	
	//��ʾ����Ʒ����
	private int num=0;
	//��ʾ��Ʒ������
	private int row=0;
    //����������
	private JScrollPane jsp=new JScrollPane();
	//GridBagLayout���ֹ�����
	private GridBagLayout gb=new GridBagLayout();
	private GridBagConstraints gbc=new GridBagConstraints();
	
	private String imageAddr;//ͼƬ��ַ
	private String goodsName;//��Ʒ����
	private double SinglePrice;//��Ʒ����
	private int allNumber;//��Ʒ�����
	
	public Book() throws ClassNotFoundException, SQLException{
    	
    	
		 p.setOpaque(false); 
    	
    	//JPanel pButton=new JPanel();
    	JPanel[] p2=new JPanel[1000];
    	
    	//�õ���Ʒ����
    	num=MainClient.array.get(1).size();
    	
    	p.setLayout(new GridLayout(0,3));
    	
    	for(int i=0;i<num;i++){
    		int iAdd = MainClient.array.get(1).get(i).getIfAdd();
    		if(iAdd==1)//�ѱ����빺�ﳵ�˴�������ʾ
    			continue;
    		imageAddr=MainClient.array.get(1).get(i).getImageAddr();
    		goodsName=MainClient.array.get(1).get(i).getgName();
    		SinglePrice=MainClient.array.get(1).get(i).getsPrice();
    		allNumber=MainClient.array.get(1).get(i).getNumber();

    		imageAddr = "/"+imageAddr;
    		p2[i]=new JPanel();
    		
    		p2[i].setOpaque(false);
    		
    		p2[i].setLayout(gb);
    		p2[i].setPreferredSize(new Dimension(300, 400));
    
    		ImageIcon image= new ImageIcon(getClass().getResource(imageAddr));
    		image.setImage(image.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
    		JLabel goodsLImage=new JLabel(image);
    		JLabel goodsLName =new JLabel("��Ʒ����"+goodsName+"");
    		JLabel goodsLsPrice =new JLabel("���ۣ���"+SinglePrice+"");
    		final JButton addGoods =new JButton("���빺�ﳵ");
    		btnDecorate(addGoods);
    	    JPanel p3=new JPanel();
    	    JPanel p4=new JPanel();
    	    p3.setLayout(new GridLayout(2,1));
    		
    	    p4.add(goodsLsPrice);
    	    p4.add(addGoods);
    	    p4.setOpaque(false);
    	    
    	    p3.add(goodsLName);
    	    p3.add(p4);
    	    p3.setOpaque(false);
      
    		gbc.fill=GridBagConstraints.NONE;
    			gbc.weightx=1;
    			gbc.weighty=1;
    			gbc.gridx=0;
    			gbc.gridy=0;
    			gbc.gridwidth=2;
    			gbc.gridheight=2;
    			gb.setConstraints(goodsLImage,gbc);
    			p2[i].add(goodsLImage);
    	   
    			gbc.gridx=0;
    			gbc.gridy=2;
    			gbc.gridwidth=2;
    			gbc.gridheight=2;
    			gb.setConstraints(p3,gbc);
    	        p2[i].add(p3);
  
    	    p.add(p2[i]);
    	     
    	    
    	    addGoods.addActionListener(new myActionListener(goodsName,SinglePrice,allNumber));
    	    addGoods.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e) {
    				addGoods.setEnabled(false);
    				addGoods.setText("�Ѽ��빺�ﳵ");
    			}
    	    });
    	    
    	   
    	}
    	
    	
    	//f.add(jsp, BorderLayout.CENTER);
    	//jsp.setViewportView(p);
    	//f.add(pButton,BorderLayout.SOUTH);
    	//f.setVisible(true);
    	
    }

	public void btnDecorate(JButton btn){
		    btn.setIcon(new ImageIcon(getClass().getResource("/Image/button-normal.png")));
			btn.setBackground(new Color(0,0,0));
			btn.setHorizontalTextPosition(0);
			btn.setOpaque(false);
			btn.setBorderPainted(false);//��ť�����߿�
			btn.setFocusPainted(false);
			btn.addMouseListener(new myButtonListener(btn,"Image/button-normal.png","Image/button-click.png"));
			btn.setContentAreaFilled(false);
			btn.setPressedIcon(new ImageIcon(getClass().getResource("/Image/button-hold.png")));
		}
class myActionListener implements ActionListener{
		private String goodName;
		private double price ;
		private int allNumber;
		public myActionListener(String str1,double str2,int str3){
			this.goodName = str1;
			this.price = str2;
			this.allNumber = str3;
		}

		public void actionPerformed(ActionEvent e) {
			//����ѡ�е���Ʒ������빺�ﳵArrayList
			MainClient.cartList.add(new ShopCartGoods(this.goodName,this.price,1,this.allNumber));
			for(int i=0;i<num;i++)
			{   
				String name = MainClient.array.get(1).get(i).getgName();
				if(name.equals(this.goodName))
				MainClient.array.get(1).get(i).setIfAdd(1);
			}
		}
		
	}

	
	//�����������
	public JPanel getP() {
		return p;
	}


}
