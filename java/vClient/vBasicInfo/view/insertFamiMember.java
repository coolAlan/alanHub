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
	//�в�����
     JLabel jlb1,jlb2,jlb3,jlb4,jlb5,jlb6;
     JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	//��������
     JButton xgButton=new JButton("ȷ�����");
     
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
     	jlb1=new JLabel("����",JLabel.CENTER);	
		jlb2=new JLabel("����",JLabel.CENTER);
		jlb3=new JLabel("��ѧ����ϵ",JLabel.CENTER);
		jlb4=new JLabel("������λ",JLabel.CENTER);
		jlb5=new JLabel("ְ��",JLabel.CENTER);
		jlb6=new JLabel("�绰",JLabel.CENTER);
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
				//ˢ�½���
				defaultModel.addRow(new Object[]{jtf1.getText(),jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText()});
				//ˢ�����ݿ�
				FamiMember fMember = new FamiMember(MyData.getUser().getUId(),jtf1.getText(),Integer.valueOf(jtf2.getText()),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText());
				dbHelper.addFamilyInfo(fMember);
				//�ر�
				JOptionPane.showMessageDialog(null, "��ӳɹ���");
				setVisible(false);
				
			}});
     	
			
		
		this.add(xgButton,BorderLayout.SOUTH);
		this.add(jp1);
		this.setTitle("��Ӽ�ͥ��Ա");
		this.setSize( 400,300);
		
		this.setLocationRelativeTo(null); // ���ô��ھ���
		this.setVisible(true);
		
     }
   
   
    

}


