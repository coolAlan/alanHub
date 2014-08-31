/*
 * Created on 2013-8-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vEshop.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import publicData.MyData;
import publicData.myButtonListener;

import vSchoolSys.common.DataGoods;



/**
 * @author zhaoziqi
 *
 * 
 */
//���ﳵ����
public class ShopCartClient {
	private JPanel mainPanel;

	//��ǩ
	private JLabel gName=new JLabel("��Ʒ����");
	private JLabel sPrice=new JLabel("����");
	private JLabel up=new JLabel("����");
	private JLabel number=new JLabel("����");
	private JLabel down=new JLabel("����");
	private JLabel aPrice=new JLabel("�ܼ�");
	private JLabel lfPrice=new JLabel();
	private JLabel remain=new JLabel();
	private JLabel number2;
    //��ť
	private JButton ok=new JButton("ȷ��");
	private JButton cancel=new JButton("ȡ��");
	//������
	private JScrollPane jsp=new JScrollPane();	
	//����������
	private int iNumber;//��Ʒ��������
	public static double allGoodsPrice;//���й�����Ʒ���ܽ��
    private int row=0;//��ʾ��Ʒ������
	private int [] num = new int [1000];//���ڱ���������Ʒ�Ĺ�������
	private double [] singelPrice = new double [1000];//���ڱ���������Ʒ�ĵ���
	private double [] oneGoodsPrice = new double [1000];//���ڱ���������Ʒ���ܽ��
	private int [] aNum = new int [1000];//���ڱ���������Ʒ�Ĺ�������
	private String[] goodsName=new String[1000];//���ڱ���������Ʒ����Ʒ��
	private Box vertical = Box.createVerticalBox();
	private ArrayList<DataGoods> goodsList = new ArrayList<DataGoods>();
	
	public ShopCartClient(){
		
		mainPanel = new JPanel();
		allGoodsPrice=0;
		//mainPanel.setSize(1240,750);
		
		btnDecorate(ok);
		btnDecorate(cancel);
		JPanel p=new JPanel();
		JPanel pLabel=new JPanel();
    	JPanel pButton=new JPanel();
    	JPanel[] p2=new JPanel[1000];
    	JLabel[] lb_num = new JLabel[1000];
    	
    	p.setOpaque(false);
    	mainPanel.setOpaque(false);
    	pLabel.setOpaque(false);
    	pButton.setOpaque(false);
    	
    	row=MainClient.cartList.size();
    	
    	
    	pLabel.setLayout(new GridLayout(1,6));
    	//p.setLayout(new GridLayout(row,1));
    	
    	for(int i=0;i<row;i++){
    		p2[i]=new JPanel();
    		p2[i].setOpaque(false);
    		Box horizontal = Box.createHorizontalBox();
    		p2[i].setLayout(new GridLayout(1,8));
    		p2[i].setPreferredSize(new Dimension(1200, 100));
    		lb_num[i] = new JLabel();
    		lb_num[i].setOpaque(false);
    		num[i] =1;
    		oneGoodsPrice[i]=0;
    		lb_num[i].setText(""+num[i]);
    		//�ӹ��ﳵArrayLIst�л����Ʒ����������Ϣ
    		goodsName[i]=MainClient.cartList.get(i).getgName();
    		singelPrice[i]=MainClient.cartList.get(i).getsPrice();
    		aNum[i]=MainClient.cartList.get(i).getaNumber();
    		//����Ʒ�ܼ۵��ڵ��۳�������
    		oneGoodsPrice[i]=singelPrice[i]*num[i];		
    		
    		JLabel gName2=new JLabel(goodsName[i],JLabel.LEFT);
    		JLabel sPrice2=new JLabel("��"+singelPrice[i]);
    		final JButton up2=new JButton("+");
    		
    		final JButton down2=new JButton("-");
			final JLabel aPrice2=new JLabel("��"+oneGoodsPrice[i]);
    		
			gName2.setPreferredSize(new Dimension(150,100));
			
			horizontal.add(gName2);
			horizontal.add(Box.createHorizontalStrut(40));
			horizontal.add(sPrice2);
			horizontal.add(Box.createHorizontalGlue());
			horizontal.add(up2);
			horizontal.add(Box.createHorizontalGlue());
			horizontal.add(lb_num[i]);
			horizontal.add(Box.createHorizontalGlue());
			horizontal.add(down2);
			horizontal.add(Box.createHorizontalGlue());
			horizontal.add(aPrice2);
			horizontal.add(Box.createHorizontalGlue());
			
			horizontal.setBorder(BorderFactory.createEtchedBorder());
			p2[i].add(horizontal);
    	    vertical.add(p2[i]);
    		//p.add(p2[i]);
    		
    		up2.addActionListener(new myActionListeneru(up2,down2,lb_num[i],aPrice2,lfPrice,i,singelPrice[i],aNum[i],goodsName[i]));
    
    		down2.addActionListener(new myActionListenerd(up2,down2,lb_num[i],aPrice2,lfPrice,i,singelPrice[i],goodsName[i]));
    	
    	}
    	
    	
    	p.add(vertical);
    	
    	for(int i=0;i<row;i++){
    		allGoodsPrice+=oneGoodsPrice[i];
    	}
    	
    	pLabel.add(gName);
    	pLabel.add(sPrice);
    	pLabel.add(up);
    	pLabel.add(number);
    	pLabel.add(down);
    	pLabel.add(aPrice);

    	
    	lfPrice.setText("�ܽ���"+allGoodsPrice+"");
    	remain.setText("һ��ͨ����"+MyData.user.getRemain());
    	
    	pButton.add(remain);
    	pButton.add(lfPrice);
    	pButton.add(ok);
    	pButton.add(cancel);
    	
    	
    	mainPanel.setLayout(new BorderLayout());
    	mainPanel.add(pLabel, BorderLayout.NORTH);

    	mainPanel.add(p, BorderLayout.CENTER);
    	
    	mainPanel.add(pButton,BorderLayout.SOUTH);
    	
    	mainPanel.setVisible(true);
    	
    	cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//new MainClient().init();	
				mainPanel.setVisible(false);
				MainClient.cartList.clear();
			}
		 });
    	
    	ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(allGoodsPrice>MyData.user.getRemain()){
					JOptionPane.showMessageDialog(null, "һ��ͨ���㣬���޸���Ʒ����", "����", JOptionPane.ERROR_MESSAGE);
				}else{
					BuyMessage buy=	new BuyMessage();	
					buy.init();
					if(buy.isSuccess()){
				
				
			       
						for(int j=0; j<MainClient.arrayList.size();j++)
						{
							if(((DataGoods)MainClient.arrayList.get(j)).getIfAdd()==1){
								((DataGoods)MainClient.arrayList.get(j)).setType(0);
							}
						}
						mainPanel.setVisible(false);
						MainClient.cartList.clear();
					}
				
				
				}
			}
		 });
	}
	
	class myActionListeneru implements ActionListener{
		private JButton up;
		private JButton down;
		private JLabel lNum ;//��Ʒ��������
		private JLabel lOneGoodsPrice;//������Ʒ������
		private JLabel lAllGoodsPrice;//������Ʒ�����ܶ�
		private int i;
		private double dSingelPrice;//��Ʒ����
		private int allNumber;//��Ʒ�������
		private String gName;//��Ʒ����
		
		public myActionListeneru(JButton b1,JButton b2,JLabel l1,JLabel l2,JLabel l3,int i,double d1,int an,String name){
			this.up=b1;
			this.down=b2;
			this.lNum=l1 ;
			this.lOneGoodsPrice=l2;
			this.lAllGoodsPrice=l3;
			this.i=i;
			this.dSingelPrice=d1;
			this.allNumber=an;
			this.gName=name;
		
		}

		public void actionPerformed(ActionEvent e) {
			//��Ʒ��������ʱ�ж��Ƿ񳬹����
			if(num[i]>=allNumber){
				this.up.setEnabled(false);
				JOptionPane.showMessageDialog(null, "��Ʒ�����Ѵﵽ�����", "����", JOptionPane.ERROR_MESSAGE);
			}else {
				num[i]=num[i]+1;
				this.lNum.setText((num[i])+"");
				oneGoodsPrice[i]=oneGoodsPrice[i]+this.dSingelPrice;
				this.lOneGoodsPrice.setText("��"+oneGoodsPrice[i]);
				allGoodsPrice=allGoodsPrice+this.dSingelPrice;
				lAllGoodsPrice.setText("�ܽ���"+allGoodsPrice);	
				this.down.setEnabled(true);
				// �˴����������ݿ⣬���Ǹ���MainClient.cartList
				 for(int j =0;j<row;j++)
				 {	
				    String name = MainClient.cartList.get(j).getgName();
				    if(this.gName.equals(name))
				    {
				    	MainClient.cartList.get(j).setNumber(num[i]);
				    }
				    	
				 }
			}
		}
	}
	
	class myActionListenerd implements ActionListener{
		private JButton up;
		private JButton down;
		private JLabel lNum ;//��Ʒ��������
		private JLabel lOneGoodsPrice;//������Ʒ������
		private JLabel lAllGoodsPrice;//������Ʒ�����ܶ�
		private int i;
		private double dSingelPrice;//��Ʒ����
		private int allNumber;//��Ʒ�������
		private String gName;//��Ʒ����
		
		public myActionListenerd(JButton b1,JButton b2,JLabel l1,JLabel l2,JLabel l3,int i1,double d1,String name){
			this.up=b1;
			this.down=b2;
			this.lNum=l1 ;
			this.lOneGoodsPrice=l2;
			this.lAllGoodsPrice=l3;
			this.i=i1;
			this.dSingelPrice=d1;
			this.gName=name;
		}

		public void actionPerformed(ActionEvent e) {
//			��Ʒ��������ʱ�ж���С��0
			if(num[i]<=0){
				this.down.setEnabled(false);
				JOptionPane.showMessageDialog(null, "��Ʒ�����Ѵﵽ��С���", "����", JOptionPane.ERROR_MESSAGE);
			}else{
				num[i]=num[i]-1;
				this.lNum.setText((num[i])+"");	
				oneGoodsPrice[i]=oneGoodsPrice[i]-this.dSingelPrice;
				this.lOneGoodsPrice.setText("��"+oneGoodsPrice[i]);
				allGoodsPrice=allGoodsPrice-this.dSingelPrice;
				lfPrice.setText("�ܽ���"+allGoodsPrice);	
				this.up.setEnabled(true);
//				 �˴����������ݿ⣬���Ǹ���MainClient.cartList
				for(int j =0;j<row;j++)
				 {	
				    String name = MainClient.cartList.get(j).getgName();
				    if(this.gName.equals(name))
				    {
				    	MainClient.cartList.get(j).setNumber(num[i]);
				    }
				    	
				 }
			}
		}
		
	}
	
	public JPanel getP() {
		return mainPanel;
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
	
}
