/*
 * Created on 2013-8-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vEshop.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import publicData.myButtonListener;

import vEshop.biz.ISearchGood;
import vSchoolSys.common.DataGoods;
import vSchoolSys.common.ShopCartGoods;

/**
 * @author zhaoziqi
 *
 * 
 */
//搜索商品结果窗口
public class SearchClient {
	public JPanel f=new JPanel();

	//显示的商品个数
	private int num=0;
	//显示商品的行数
	private int row=0;
    //滚动条窗口
	private JScrollPane jsp=new JScrollPane();
	 //GridBagLayout布局管理器
	private GridBagLayout gb=new GridBagLayout();
	private GridBagConstraints gbc=new GridBagConstraints();
	
	private String imageAddr;//图片地址
	private String goodsName;//商品名称
	private double SinglePrice;//商品单价
	private int allNumber;//商品库存量
	private ArrayList goods;//存储搜索到的商品
	
    
	public SearchClient() throws ClassNotFoundException, SQLException{
		
    	ISearchGood search = new ISearchGood(MainClient.sFind);
        goods = search.getMessage().getData();
    	
        //去掉已经加入购物车的商品避免重复
        num=goods.size();
        for(int i=0;i< num;i++){
           String name = ((DataGoods) goods.get(i)).getgName();
           for(int j=0; j<MainClient.arrayList.size();j++)
           {
           	if(((DataGoods)MainClient.arrayList.get(j)).getIfAdd()==1){
           		String str = ((DataGoods)MainClient.arrayList.get(j)).getgName();
           		if(str.equals(name))
           			//goods.remove(i);//删除已经加入购物车的商品
           			((DataGoods) goods.get(i)).setNumber(0);
           	}
           }
        }
    	//f.setSize(1200,750);
    	JPanel mPanel=new JPanel();
    	JPanel[] p2=new JPanel[1000];
    	
    	//得到商品数量
    	
    	//得到显示行数
    	if(num==0)
    	{
    		JOptionPane.showMessageDialog(null, "没有该商品", "错误", JOptionPane.ERROR_MESSAGE);   
    	}
    	
    	mPanel.setLayout(new GridLayout(0,3));
    	mPanel.setOpaque(false);
    	
    	for(int i=0;i<num;i++){
    		
    		imageAddr=((DataGoods) goods.get(i)).getImageAddr();
    		goodsName=((DataGoods) goods.get(i)).getgName();
    		SinglePrice=((DataGoods) goods.get(i)).getsPrice();
    		allNumber=((DataGoods) goods.get(i)).getNumber();
    		imageAddr ="/" +imageAddr;
    		if(allNumber==0)
    			continue;
    		p2[i]=new JPanel();
    		p2[i].setLayout(gb);
    		p2[i].setOpaque(false);
    		p2[i].setPreferredSize(new Dimension(300, 400));
    
    		ImageIcon image= new ImageIcon(getClass().getResource(imageAddr));
    		image.setImage(image.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
    		JLabel goodsLImage=new JLabel(image);
    		JLabel goodsLName =new JLabel("商品名："+goodsName+"");
    		JLabel goodsLsPrice =new JLabel("单价：￥"+SinglePrice+"");
    		final JButton addGoods =new JButton("加入购物车");
    		btnDecorate(addGoods);
    	    JPanel p3=new JPanel();
    	    JPanel p4=new JPanel();
    	    p3.setLayout(new GridLayout(2,1));
    	    p4.setOpaque(false);
    	    p4.add(goodsLsPrice);
    	    p4.add(addGoods);
    	    
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
    			p2[i].setOpaque(false);
    	        p2[i].add(p3);
    	        
    	        mPanel.add(p2[i]);
    	    
    	    addGoods.addActionListener(new myActionListener(goodsName,SinglePrice,allNumber));
    	    addGoods.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e) {
    				addGoods.setEnabled(false);
    				addGoods.setText("已加入购物车");
    			}
    	    });
    	    
    	   
    	}
    	
    	f.setOpaque(false);
    	jsp.setOpaque(false);   
    	jsp.getViewport().setOpaque(false); 
    	f.add(jsp, BorderLayout.CENTER);
    	
    	
    	jsp.setViewportView(mPanel);
    	f.setVisible(true);
    	
    
    	
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
			//将被选中的商品对象加入购物车ArrayList
			MainClient.cartList.add(new ShopCartGoods(this.goodName,this.price,1,this.allNumber));
			for(int i=0;i<6;i++)
			{   
				for(int j=0;j<MainClient.array.get(i).size();j++){
					String name = ((DataGoods)MainClient.array.get(i).get(j)).getgName();
					if(name.equals(this.goodName))
						MainClient.array.get(i).get(j).setIfAdd(1);
				}
			}
		}
		
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
public JPanel getP() {
	return f;
}

public void setF(JPanel f) {
	this.f = f;
}

	
	
}