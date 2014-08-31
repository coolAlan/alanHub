/*
 * Created on 2013-8-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vEshop.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import publicData.MyData;

import vEshop.biz.IAddSaleRecord;
import vSchoolSys.common.ItemList;
import vSchoolSys.common.ShopCartGoods;



/**
 * @author zhaoziqi
 *
 * 
 */
//购买者信息登记窗口
public class BuyMessage {
	private JFrame f=new JFrame("订单信息");
	//标签
	private JLabel lPhone=new JLabel("联系电话");
	private JLabel lAddr=new JLabel("宿舍地址");
	private JLabel lRemark=new JLabel("备注");
	//变量
	private String sPhone;//联系方式
	private String sAddr;//收货地址
	private String sRemark;//留言
	//文本框
	private JTextField tPhone=new JTextField(20);
	private JTextField tAddr=new JTextField(20);
	private JTextField tRemark=new JTextField(20);
    //按钮
	private JButton ok=new JButton("确定");
	private JButton cancel=new JButton("取消");
	
    //GridBagLayout 布局管理器
	private GridBagLayout gb =new GridBagLayout();
	private GridBagConstraints gbc =new GridBagConstraints();
	
	private boolean success  =false;//判断交易是否形成
	public void init(){
		f.setSize(300,250);
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();

		p1.setLayout(gb);
		
		gbc.fill=GridBagConstraints.NONE;
		gbc.weightx=1;
		gbc.weighty=1;
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gb.setConstraints(lPhone,gbc);
		p1.add(lPhone);
		
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gb.setConstraints(lAddr,gbc);
		p1.add(lAddr);
		
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gb.setConstraints(lRemark,gbc);
		p1.add(lRemark);
		
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gb.setConstraints(tPhone,gbc);
		p1.add(tPhone);
		
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gb.setConstraints(tAddr,gbc);
		p1.add(tAddr);
		
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gb.setConstraints(tRemark,gbc);
		p1.add(tRemark);
		
		p2.add(ok);
		p2.add(cancel);
		
		
		f.add(p1,BorderLayout.CENTER);
		f.add(p2,BorderLayout.SOUTH);
		f.setLocationRelativeTo(null);
		f.setAlwaysOnTop(true);
		f.setVisible(true);
		
		
		
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//返回主界面
				//new MainClient().init();	
				sPhone=tPhone.getText();
				sAddr=tAddr.getText();
				sRemark=tRemark.getText();
				
				f.setVisible(false);
				//通过tblItemList类向数据库tblItemList表中加入一行新信息
				ItemList list=new ItemList(MyData.user.getUId(),"商城"+MyData.user.getUName()
						,sPhone,sAddr,sRemark,(0-ShopCartClient.allGoodsPrice),new Date());
				ArrayList<ShopCartGoods> cartlist = MainClient.cartList;
				   
				IAddSaleRecord saleRecord = new IAddSaleRecord(list,cartlist);
				//获取信息
				MyData myData = new MyData();
				myData.setUser( saleRecord.getMessage().getSender());
				
				System.out.println("user's remain "+MyData.user.getRemain());
				ArrayList l=saleRecord.getMessage().getData();///??????
				if(((Integer)l.get(0))==1){
				  JOptionPane.showMessageDialog(null,"交易成功！");
				  success =true;
				  f.setVisible(false);
				}
			}
			
		 });
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				f.setVisible(false);
			}
		 });
		
	}
	
	
	public boolean isSuccess() {
		return success;
	}
	
}
