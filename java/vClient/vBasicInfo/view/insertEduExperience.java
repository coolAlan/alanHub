
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
	//�в�����
     JLabel jlb1,jlb2,jlb3,jlb4;
     JTextField jtf1,jtf2,jtf3,jtf4;
	//��������
     JButton xgButton=new JButton("ȷ�����");
     
     private DefaultTableModel defaultModel2;
    
     private IBasicInfoHelper dbHelper=new IBasicInfoHelper();
     
     
     @SuppressWarnings("deprecation")
	public void insert(final DefaultTableModel defaultModel) throws ParseException{
    	this.defaultModel2=defaultModel;
    	 
    	jtf1=new JTextField();
 		jtf2=new JTextField();
 		jtf3=new JTextField();
 		jtf4=new JTextField();
     	
     	jlb1=new JLabel("��ʼʱ��",JLabel.CENTER);	
		jlb2=new JLabel("��ֹʱ��",JLabel.CENTER);
		jlb3=new JLabel("ѧУ",JLabel.CENTER);
		jlb4=new JLabel("����ѧ��",JLabel.CENTER);
     	
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
				//ˢ�½���
				defaultModel.addRow(new Object[]{jtf1.getText(),jtf2.getText(),jtf3.getText(),jtf4.getText()});
				//ˢ�����ݿ�
				EduExperience eduExperience  = new EduExperience(MyData.getUser().getUId(),jtf1.getText(),jtf2.getText(),jtf3.getText(),jtf4.getText());
				dbHelper.addEducationInfo(eduExperience);
				//�ر�
				JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
				setVisible(false);
				
			}});
     	
		this.add(xgButton,BorderLayout.SOUTH);
		this.add(jp1);
		this.setTitle("��ӽ�������");
		this.setSize( 400,300);
		
		this.setLocationRelativeTo(null); // ���ô��ھ���
		this.setVisible(true);
		
     }
   
   
    

}

