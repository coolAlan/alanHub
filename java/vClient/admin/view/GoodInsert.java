package admin.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import admin.biz.IAdminHelper;


import publicData.MyData;
import vBasicInfo.biz.IBasicInfoHelper;
import vSchoolSys.common.Course;
import vSchoolSys.common.DataGoods;
import vSchoolSys.common.FamiMember;

import java.awt.event.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class GoodInsert extends JFrame {
     JPanel jp1; 
	//中部区域
     JLabel jlb1,jlb2,jlb3,jlb4,jlb5;
     JTextField jtf1,jtf2,jtf3,jtf4;
     JComboBox jcb1;
	//北部区域
     JButton xgButton=new JButton("确认添加");
     
     private DefaultTableModel defaultModel1;
     private int num;
     private IAdminHelper adminHelper = new IAdminHelper();
     private boolean success =false;
     @SuppressWarnings("deprecation")
	public GoodInsert(final DefaultTableModel defaultModel) throws ParseException{
    	this.defaultModel1=defaultModel;
    	//this.cID = cID;
    	
    	String[] type = new String[] {"水果","图书","个人护理","饮品乳品","日用品","零食小吃"};
    	jtf1=new JTextField();//商品ID
 		jtf2=new JTextField();//商品名称
 		jtf3=new JTextField();//商品价格
 		JPanel jpl = new JPanel();
 		jtf4=new JTextField();//商品数量
 		jcb1 = new JComboBox(type);//商品类型
 		jpl.add(jcb1);
 		jtf4.setText("50");
 		jtf3.setText("10.00");
     	jlb1=new JLabel("商品编号",JLabel.CENTER);	
		jlb2=new JLabel("商品名称",JLabel.CENTER);
		jlb3=new JLabel("商品价格",JLabel.CENTER);
		jlb4=new JLabel("商品数量",JLabel.CENTER);
		jlb5=new JLabel("商品类型",JLabel.CENTER);
		
     	jp1=new JPanel();
     	jp1.setLayout(new GridLayout(5,2));
		jp1.add(jlb1);
		jp1.add(jtf1);
		jp1.add(jlb2);
		jp1.add(jtf2);
		jp1.add(jlb3);
		jp1.add(jtf3);
		jp1.add(jlb4);
		jp1.add(jtf4);
		
		jp1.add(jlb5);
		jp1.add(jpl);


		 xgButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
				int num = defaultModel1.getRowCount();
				System.out.println("num is "+num);
				
				String gID = jtf1.getText();
				System.out.println("id is "+gID);
				for(int i =0;i<num;i++){
				String id=(String)defaultModel1.getValueAt(i, 0);
				if(gID.equals(id)){
					JOptionPane.showMessageDialog(null, "商品编号重复，请重新输入！");
					success =false;
					return;
				}
				}
				int type = jcb1.getSelectedIndex()+1;
				String sType = jcb1.getSelectedItem().toString();
				//刷新界面
				defaultModel.addRow(new Object[]{gID,jtf2.getText(),jtf3.getText(),jtf4.getText(),sType});
				//刷新数据库
				DataGoods good = new DataGoods(gID,jtf2.getText(),new Double(jtf3.getText()).doubleValue(),
						new Integer(jtf4.getText()).intValue(),"Image/goods/fruit/block.jpg",type);
				adminHelper.addGood(good);
				//关闭
				JOptionPane.showMessageDialog(null, "添加成功！");
				success =true;
				setVisible(false);
				
			}});
     	
			
		
		this.add(xgButton,BorderLayout.SOUTH);
		this.add(jp1);
		this.setTitle("添加商品");
		this.setSize( 400,300);
		
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
		
		
     }
   
   
    

	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
}


