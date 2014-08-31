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
//购物车界面
public class ShopCartClient {
	private JPanel mainPanel;

	//标签
	private JLabel gName=new JLabel("商品名称");
	private JLabel sPrice=new JLabel("单价");
	private JLabel up=new JLabel("增加");
	private JLabel number=new JLabel("数量");
	private JLabel down=new JLabel("减少");
	private JLabel aPrice=new JLabel("总价");
	private JLabel lfPrice=new JLabel();
	private JLabel remain=new JLabel();
	private JLabel number2;
    //按钮
	private JButton ok=new JButton("确定");
	private JButton cancel=new JButton("取消");
	//滚动条
	private JScrollPane jsp=new JScrollPane();	
	//变量与数组
	private int iNumber;//商品购买数量
	public static double allGoodsPrice;//所有购买商品的总金额
    private int row=0;//显示商品的行数
	private int [] num = new int [1000];//正在被操作的商品的购买数量
	private double [] singelPrice = new double [1000];//正在被操作的商品的单价
	private double [] oneGoodsPrice = new double [1000];//正在被操作的商品的总金额
	private int [] aNum = new int [1000];//正在被操作的商品的购买数量
	private String[] goodsName=new String[1000];//正在被操作的商品的商品名
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
    		//从购物车ArrayLIst中获得商品对象的相关信息
    		goodsName[i]=MainClient.cartList.get(i).getgName();
    		singelPrice[i]=MainClient.cartList.get(i).getsPrice();
    		aNum[i]=MainClient.cartList.get(i).getaNumber();
    		//该商品总价等于单价乘以数量
    		oneGoodsPrice[i]=singelPrice[i]*num[i];		
    		
    		JLabel gName2=new JLabel(goodsName[i],JLabel.LEFT);
    		JLabel sPrice2=new JLabel("￥"+singelPrice[i]);
    		final JButton up2=new JButton("+");
    		
    		final JButton down2=new JButton("-");
			final JLabel aPrice2=new JLabel("￥"+oneGoodsPrice[i]);
    		
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

    	
    	lfPrice.setText("总金额：￥"+allGoodsPrice+"");
    	remain.setText("一卡通余额：￥"+MyData.user.getRemain());
    	
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
					JOptionPane.showMessageDialog(null, "一卡通金额不足，请修改商品数量", "错误", JOptionPane.ERROR_MESSAGE);
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
		private JLabel lNum ;//商品购买数量
		private JLabel lOneGoodsPrice;//单个商品购买金额
		private JLabel lAllGoodsPrice;//所有商品购买总额
		private int i;
		private double dSingelPrice;//商品单价
		private int allNumber;//商品库存数量
		private String gName;//商品名称
		
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
			//商品数量增减时判断是否超过库存
			if(num[i]>=allNumber){
				this.up.setEnabled(false);
				JOptionPane.showMessageDialog(null, "商品数量已达到最大库存", "错误", JOptionPane.ERROR_MESSAGE);
			}else {
				num[i]=num[i]+1;
				this.lNum.setText((num[i])+"");
				oneGoodsPrice[i]=oneGoodsPrice[i]+this.dSingelPrice;
				this.lOneGoodsPrice.setText("￥"+oneGoodsPrice[i]);
				allGoodsPrice=allGoodsPrice+this.dSingelPrice;
				lAllGoodsPrice.setText("总金额：￥"+allGoodsPrice);	
				this.down.setEnabled(true);
				// 此处不更新数据库，但是更新MainClient.cartList
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
		private JLabel lNum ;//商品购买数量
		private JLabel lOneGoodsPrice;//单个商品购买金额
		private JLabel lAllGoodsPrice;//所有商品购买总额
		private int i;
		private double dSingelPrice;//商品单价
		private int allNumber;//商品库存数量
		private String gName;//商品名称
		
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
//			商品数量增减时判断是小于0
			if(num[i]<=0){
				this.down.setEnabled(false);
				JOptionPane.showMessageDialog(null, "商品数量已达到最小库存", "错误", JOptionPane.ERROR_MESSAGE);
			}else{
				num[i]=num[i]-1;
				this.lNum.setText((num[i])+"");	
				oneGoodsPrice[i]=oneGoodsPrice[i]-this.dSingelPrice;
				this.lOneGoodsPrice.setText("￥"+oneGoodsPrice[i]);
				allGoodsPrice=allGoodsPrice-this.dSingelPrice;
				lfPrice.setText("总金额：￥"+allGoodsPrice);	
				this.up.setEnabled(true);
//				 此处不更新数据库，但是更新MainClient.cartList
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
		btn.setBorderPainted(false);//按钮不画边框
		btn.setFocusPainted(false);
		btn.addMouseListener(new myButtonListener(btn,"Image/button-normal.png","Image/button-click.png"));
		btn.setContentAreaFilled(false);
		btn.setPressedIcon(new ImageIcon(getClass().getResource("/Image/button-hold.png")));
	}
	
}
