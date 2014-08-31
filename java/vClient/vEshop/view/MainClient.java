/*
 * Created on 2013-8-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vEshop.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import publicData.MyData;
import publicData.MyEvent;
import publicData.MyPanel;
import publicData.RemainEvent;

import vEshop.biz.IGoodsInit;
import vEshop.view.Care;
import vSchoolSys.common.DataGoods;
import vSchoolSys.common.Message;
import vSchoolSys.common.ShopCartGoods;
import vSchoolSys.common.User;




/**
 * @author zhaoziqi
 *
 *
 */
//商店主界面
public class MainClient extends MyPanel{
	
	//菜单箱子
    protected Box xkBox = Box.createHorizontalBox();
    //按钮
  	private JButton shopCar =new JButton("购物车",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
  	private JButton fruit =new JButton("时鲜水果",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
  	private JButton snacks =new JButton("零食小吃",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
  	private JButton drink =new JButton("乳品饮料",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
  	private JButton book =new JButton("书籍音像",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
  	private JButton everyday =new JButton("生活用品",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
  	private JButton care =new JButton("个人护理",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
	private JButton search =new JButton("搜索商品",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
  	
    //内容主面板
    private JPanel sd_content =new JPanel();
    //内容主面板的布局
    final CardLayout card = new CardLayout();

    JPanel fruitPanel = new JPanel();
    JPanel snacksPanel = new JPanel();
    JPanel drinkPanel = new JPanel();
    JPanel bookPanel = new JPanel();
    JPanel everydayPanel = new JPanel();
    JPanel carePanel = new JPanel();
    JPanel shopcarPanel = new JPanel();
    JPanel searchPanel = new JPanel();

	
    private JScrollPane jsp1=new JScrollPane();

	
	//分类存储商品
	public static  ArrayList<ArrayList<DataGoods>> array=new ArrayList<ArrayList<DataGoods>>();
	//无差别的存储商品
	public static ArrayList arrayList;
	
	
	
	public User user ;
	//上部信息显示
	private JLabel sName;
	private JLabel id ; 
	private JLabel remain;

	
  	private JButton bFind =new JButton("搜索",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
	//商品搜索框
	private JTextField tFind =new JTextField(40);

	 //滚动条窗口
	private JScrollPane jsp=new JScrollPane();	

	
	//搜索商品关键字
	public static String sFind;
	//购物车数组
	public static ArrayList<ShopCartGoods> cartList=new ArrayList<ShopCartGoods>();
	
	public void AddArray(){
		for(int i=0;i<6;i++){
			array.add(new ArrayList<DataGoods>());
		}
	}
	
	public MainClient(String addr) throws ClassNotFoundException, SQLException {
		
		super(addr);
		

		this.initialBox();
		this.intialContentPanel();
		
		this.setLayout(new BorderLayout());
		this.add(xkBox,BorderLayout.NORTH);
		this.add(sd_content,BorderLayout.CENTER);
		
  

		//MyData,,,,我不知道要干啥
		MyData myData = new MyData();	
		myData.addRemainListener(new MyRemainListener());
		
		user = MyData.user;
		remain =new JLabel("余额："+user.getRemain());
		sName  =new JLabel("姓名："+user.getUName());
		id  =new JLabel("一卡通号："+user.getUId());

		shopCar.setHorizontalTextPosition(0);
		
		btnDecorate(shopCar);
		fruit.setHorizontalTextPosition(0);
	//	fruit.setPreferredSize(new Dimension(5,29));
		btnDecorate(fruit);
		drink.setHorizontalTextPosition(0);
		//drink.setPreferredSize(new Dimension(5,29));
		btnDecorate(drink);
		snacks.setHorizontalTextPosition(0);
		//snacks.setPreferredSize(new Dimension(5,29));
		btnDecorate(snacks);
		book.setHorizontalTextPosition(0);
		btnDecorate(book);
		everyday.setHorizontalTextPosition(0);
		//everyday.setPreferredSize(new Dimension(5,29));
		btnDecorate(everyday);
		care.setHorizontalTextPosition(0);
		//care.setPreferredSize(new Dimension(5,29));
		btnDecorate(care);
		search.setHorizontalTextPosition(0);
		//search.setPreferredSize(new Dimension(5,29));
		btnDecorate(search);
		///btn.setPreferredSize(new Dimension(25,30));
		bFind.setHorizontalTextPosition(0);
		//bFind.setPreferredSize(new Dimension(5,29));
		btnDecorate(bFind);
		bFind.setPreferredSize(new Dimension(150,30));
		//****初始化商品信息，发送请求得到所有商品信息
		
		IGoodsInit goods = new IGoodsInit();
		Message msg = goods.getMessage();
		arrayList = msg.getData();
		
		array.clear();
		cartList.clear();
		AddArray();//初始化大数组
		//分拣信息将六种商品分别加到六个arrayList中
		
		for(int i =0;i<arrayList.size();i++)
		{
			int tbType = ((DataGoods)arrayList.get(i)).getType();
			array.get(tbType-1).add((DataGoods)arrayList.get(i));
		}


	}
	
	private void intialContentPanel() {
		sd_content.setLayout(card);
		sd_content.setOpaque(false);

		sd_content.add("搜索商品",searchPanel);
		sd_content.add("时鲜水果",fruitPanel);
		sd_content.add("零食小吃",snacksPanel);	
		sd_content.add("乳品饮料",drinkPanel);
		sd_content.add("书籍音像",bookPanel);	
		sd_content.add("生活用品",everydayPanel);
	    sd_content.add("个人护理",carePanel);	
	    sd_content.add("购物车",shopcarPanel);  	
	    
	    fruitPanel.setOpaque(false);
	    snacksPanel.setOpaque(false);
	    drinkPanel.setOpaque(false);
	    bookPanel.setOpaque(false);
	    everydayPanel.setOpaque(false);
	    carePanel.setOpaque(false);
	    shopcarPanel.setOpaque(false);
	    searchPanel.setOpaque(false);
	    
	}
	
	private void initialBox() {
		//菜单按钮
    	xkBox.add(Box.createHorizontalStrut(3));
    	xkBox.add(fruit);
    	xkBox.add(Box.createHorizontalStrut(3));
    	xkBox.add(snacks);
    	xkBox.add(Box.createHorizontalStrut(3));
    	xkBox.add(drink);
    	xkBox.add(Box.createHorizontalStrut(3));
    	xkBox.add(book);
    	xkBox.add(Box.createHorizontalStrut(3));
    	xkBox.add(everyday);
    	xkBox.add(Box.createHorizontalStrut(3));
    	xkBox.add(care);
    	xkBox.add(Box.createHorizontalStrut(3));
    	xkBox.add(search);
    	xkBox.add(Box.createHorizontalStrut(3));
    	xkBox.add(shopCar);

    	//设置JScrollPane大小，不然滑条显示不出来，具体原因还不清楚。。。
    	jsp1.getVerticalScrollBar().setUnitIncrement(20);
    	jsp1.setPreferredSize(new Dimension(920, 450));
    	//设置JScrollPane背景透明
    	jsp1.setOpaque(false);   
    	jsp1.getViewport().setOpaque(false); 
    	
		shopCar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//判断购物车是否为空
				if(cartList.size()==0){
					JOptionPane.showMessageDialog(null, "购物车还是空的哦~", "错误", JOptionPane.ERROR_MESSAGE);
				}else{
					card.show(sd_content,"购物车");
					
					ShopCartClient bb=new ShopCartClient();						
			    	jsp1.setViewportView(bb.getP());
			 		shopcarPanel.add(jsp1);
		
				}
			}
		 });
		

		
		final JPanel jjj = new JPanel();
		jjj.setOpaque(false);
		
		jjj.add(tFind);
		jjj.add(bFind);
		searchPanel.add(jjj,BorderLayout.NORTH);
		
		
		
		
		bFind.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				sFind=tFind.getText();//***获得用户的输入
				if(sFind.equals(""))
				{
					JOptionPane.showMessageDialog(null, "请输入商品名", "错误", JOptionPane.ERROR_MESSAGE);   
				}else{
					try {		
						//jjj必须经历false到true，否则jsp1不显示
						jjj.setVisible(false);
						SearchClient bb=new SearchClient();	
						jsp1.setViewportView(bb.getP());
				    	searchPanel.add(jsp1,BorderLayout.SOUTH);
				    	jjj.setVisible(true);
						

					} catch (ClassNotFoundException e2) {
						e2.printStackTrace();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
						
				}
			}
		 });
		
		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

					card.show(sd_content,"搜索商品");
					//jjj.setVisible(true);
			 }
		});
		
		book.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {	
				try {
					card.show(sd_content,"书籍音像");
					
					Book bb=new Book();				
			    	jsp1.setViewportView(bb.getP());
			    	bookPanel.add(jsp1);
					
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			
			}
		 });
		
		care.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					card.show(sd_content,"个人护理");
					
					Care bb=new Care();
			    	jsp1.setViewportView(bb.getP());
			    	carePanel.add(jsp1);
			    	
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		 });
		
		drink.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					card.show(sd_content,"乳品饮料");
					
					Drink bb=new Drink();
					jsp1.setViewportView(bb.getP());
					drinkPanel.add(jsp1);
					
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		 });
		
		everyday.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					card.show(sd_content,"生活用品");
					
					Everyday bb=new Everyday();
			    	jsp1.setViewportView(bb.getP());
			    	everydayPanel.add(jsp1);
			 			    	
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			
			}
		 });
		
		fruit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					card.show(sd_content,"时鲜水果");
					
					Fruit bb=new Fruit();
			    	jsp1.setViewportView(bb.getP());
			    	fruitPanel.add(jsp1);
			    	
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			
			}
		 });
		
		snacks.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					card.show(sd_content,"零食小吃");
					
					Snacks bb=new Snacks();
			    	jsp1.setViewportView(bb.getP());
			    	snacksPanel.add(jsp1);
			    	
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			
			}
		 });
		
	}

	
	public class MyRemainListener implements java.util.EventListener{
		 //这里是当事件发生后的响应过程
		    public void EventActivated(RemainEvent me)
		    {
		    	user = MyData.user;
		    	System.out.println("触发余额"+user.getRemain());
		    	
		    	remain.setText("余额："+user.getRemain());
		    	remain.repaint();
		    	
				//f.add(remain);
		    }
		}
	
	public void btnDecorate(JButton btn){
		btn.setPreferredSize(new Dimension(5,30));
		
		btn.setBackground(new Color(0,0,0));
		btn.setOpaque(false);
		btn.setBorderPainted(false);//按钮不画边框
		btn.setFocusPainted(false);
		btn.addMouseListener(new myButtonListener(btn,"Image/button-normal.png","Image/button-click.png"));
		btn.setContentAreaFilled(false);
		btn.setPressedIcon(new ImageIcon(getClass().getResource("/Image/button-hold.png")));
	}
	

	class myButtonListener extends MouseAdapter{//继承MouseAdapter，重写部分方法
		JButton jb;
		String picture1;
		String picture2;
		public myButtonListener(JButton jb,String picture1,String picture2){
			this.jb=jb;
			this.picture1=picture1;
			this.picture2=picture2;
			
			
		}
		public void mouseEntered(MouseEvent arg0) {
			jb.setIcon(new ImageIcon(getClass().getResource("/"+picture2)));
			
			
		}

		public void mouseExited(MouseEvent arg0) {
			jb.setIcon(new ImageIcon(getClass().getResource("/"+picture1)));
			
	    }
	}
}
	