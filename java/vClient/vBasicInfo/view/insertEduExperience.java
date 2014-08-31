
package vBasicInfo.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import publicData.MyData;
import vBasicInfo.biz.IBasicInfoHelper;
import vSchoolSys.common.EduExperience;

import java.awt.event.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class insertEduExperience extends JFrame {
     JPanel jp1; 
	//中部区域
     JLabel jlb1,jlb2,jlb3,jlb4;
     JTextField jtf1,jtf2,jtf3,jtf4;
	//北部区域
     JButton xgButton=new JButton("确认添加");
     
     private DefaultTableModel defaultModel2;
    
     private IBasicInfoHelper dbHelper=new IBasicInfoHelper();
     
     
     @SuppressWarnings("deprecation")
	public void insert(final DefaultTableModel defaultModel) throws ParseException{
    	this.defaultModel2=defaultModel;
    	 
    	jtf1=new JTextField();
 		jtf2=new JTextField();
 		jtf3=new JTextField();
 		jtf4=new JTextField();
     	
     	jlb1=new JLabel("开始时间",JLabel.CENTER);	
		jlb2=new JLabel("终止时间",JLabel.CENTER);
		jlb3=new JLabel("学校",JLabel.CENTER);
		jlb4=new JLabel("所获学历",JLabel.CENTER);
     	
     	jp1=new JPanel();
     	jp1.setLayout(new GridLayout(4,2));
		jp1.add(jlb1);
		jp1.add(jtf1);
		jp1.add(jlb2);
		jp1.add(jtf2);
		jp1.add(jlb3);
		jp1.add(jtf3);
		jp1.add(jlb4);
		jp1.add(jtf4);


		 xgButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
				//刷新界面
				defaultModel.addRow(new Object[]{jtf1.getText(),jtf2.getText(),jtf3.getText(),jtf4.getText()});
				//刷新数据库
				EduExperience eduExperience  = new EduExperience(MyData.getUser().getUId(),jtf1.getText(),jtf2.getText(),jtf3.getText(),jtf4.getText());
				dbHelper.addEducationInfo(eduExperience);
				//关闭
				JOptionPane.showMessageDialog(null, "修改成功！");
				setVisible(false);
				
			}});
     	
		this.add(xgButton,BorderLayout.SOUTH);
		this.add(jp1);
		this.setTitle("添加教育经历");
		this.setSize( 400,300);
		
		this.setLocationRelativeTo(null); // 设置窗口居中
		this.setVisible(true);
		
     }
   
   
    

}

