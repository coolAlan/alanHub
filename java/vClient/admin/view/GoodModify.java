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



public class GoodModify extends JFrame {
     JPanel jp1; 
	//中部区域
     JLabel jlb1,jlb2,jlb3,jlb4,jlb5;
     JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
    // JComboBox jcb1;
	//北部区域
     JButton xgButton=new JButton("确认修改");
     
     private DefaultTableModel defaultModel1;
     private int num;
     private IAdminHelper adminHelper = new IAdminHelper();
     private boolean success =false;
     @SuppressWarnings("deprecation")
	public GoodModify(final DefaultTableModel defaultModel,int rowNo) throws ParseException{
    	this.defaultModel1=defaultModel;
    	//this.cID = cID;
    	this.num = rowNo;
    	String[] type = new String[] {"水果","图书","个人护理","饮品乳品","日用品","零食小吃"};
    	jtf1=new JTextField();//商品ID
    	String gID=(String)defaultModel.getValueAt(rowNo, 0);
    	jtf1.setText(gID);
    	jtf1.setEditable(false);
    	
 		jtf2=new JTextField();//商品名称
 		String gName=(String)defaultModel.getValueAt(rowNo, 1);
    	jtf2.setText(gName);
 		jtf2.setEditable(false);
 		
 		jtf3=new JTextField();//商品价格
 		String gPrice=defaultModel.getValueAt(rowNo, 2).toString();
    	jtf3.setText(gPrice);
 		
 		jtf4=new JTextField();//商品数量
 		String gNum=defaultModel.getValueAt(rowNo, 3).toString();
    	jtf4.setText(gNum);
    	
 		jtf5=new JTextField();//商品类型
 		String gType=(String)defaultModel.getValueAt(rowNo, 4);
    	jtf5.setText(gType);
 		jtf5.setEditable(false);
 		
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
		jp1.add(jtf5);


		 xgButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
				
				String gID = jtf1.getText();
				
				defaultModel.removeRow(num);
			
				//刷新界面
                defaultModel.addRow(new Object[]{gID,jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText()});
				//刷新数据库
				DataGoods good = new DataGoods(gID,jtf2.getText(),new Double(jtf3.getText()).doubleValue(),
						new Integer(jtf4.getText()).intValue(),"image\\goods\\fruit\\block.jpg",0);
				adminHelper.changeGoodInfo(good);
				//关闭
				JOptionPane.showMessageDialog(null, "修改成功！");
				success =true;
				setVisible(false);
				
			}});
     	
			
		
		this.add(xgButton,BorderLayout.SOUTH);
		this.add(jp1);
		this.setTitle("添加家庭成员");
		this.setSize( 400,300);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLocation(20,30);
		this.setVisible(true);
		//this.setIconImage((new ImageIcon("image/xiaohui.jpg")).getImage());
		
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


