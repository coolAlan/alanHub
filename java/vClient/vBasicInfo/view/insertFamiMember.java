package vBasicInfo.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import publicData.MyData;
import vBasicInfo.biz.IBasicInfoHelper;
import vSchoolSys.common.FamiMember;

import java.awt.event.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class insertFamiMember extends JFrame {
     JPanel jp1; 
	//中部区域
     JLabel jlb1,jlb2,jlb3,jlb4,jlb5,jlb6;
     JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	//北部区域
     JButton xgButton=new JButton("确认添加");
     
     private DefaultTableModel defaultModel1;
    
     private IBasicInfoHelper dbHelper=new IBasicInfoHelper();
     
     @SuppressWarnings("deprecation")
	public void insertfm(final DefaultTableModel defaultModel) throws ParseException{
    	this.defaultModel1=defaultModel;
    	 
    	jtf1=new JTextField();
 		jtf2=new JTextField();
 		jtf3=new JTextField();
 		jtf4=new JTextField();
 		jtf5=new JTextField();
 		jtf6=new JTextField();
     	jlb1=new JLabel("姓名",JLabel.CENTER);	
		jlb2=new JLabel("年龄",JLabel.CENTER);
		jlb3=new JLabel("与学生关系",JLabel.CENTER);
		jlb4=new JLabel("工作单位",JLabel.CENTER);
		jlb5=new JLabel("职务",JLabel.CENTER);
		jlb6=new JLabel("电话",JLabel.CENTER);
     	jp1=new JPanel();
     	jp1.setLayout(new GridLayout(6,2));
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
		jp1.add(jlb6);
		jp1.add(jtf6);

		 xgButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
				//刷新界面
				defaultModel.addRow(new Object[]{jtf1.getText(),jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText()});
				//刷新数据库
				FamiMember fMember = new FamiMember(MyData.getUser().getUId(),jtf1.getText(),Integer.valueOf(jtf2.getText()),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText());
				dbHelper.addFamilyInfo(fMember);
				//关闭
				JOptionPane.showMessageDialog(null, "添加成功！");
				setVisible(false);
				
			}});
     	
			
		
		this.add(xgButton,BorderLayout.SOUTH);
		this.add(jp1);
		this.setTitle("添加家庭成员");
		this.setSize( 400,300);
		
		this.setLocationRelativeTo(null); // 设置窗口居中
		this.setVisible(true);
		
     }
   
   
    

}


