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



public class changeFamiMem extends JFrame {
     JPanel jp1; 
	//�в�����
     JLabel jlb1,jlb2,jlb3,jlb4,jlb5,jlb6;
     JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	//��������
     JButton changeButton=new JButton("ȷ���޸�");
     
     //
     private IBasicInfoHelper dbHelper=new IBasicInfoHelper();
     
     private DefaultTableModel jtModel;
     private int row;
     //private JTable//
     
     @SuppressWarnings("deprecation")
     
	public changeFamiMem(final String fName, DefaultTableModel jtDefaultModel,int r) throws ParseException{

    	this.jtModel = jtDefaultModel;
    	this.row = r;
 
    	jtf1=new JTextField();
 		jtf2=new JTextField();
 		jtf3=new JTextField();
 		jtf4=new JTextField();
 		jtf5=new JTextField();
 		jtf6=new JTextField();
 		//�����ʼ��
 		jtf1.setText((String)jtModel.getValueAt(row, 0));
 		jtf1.setEnabled(false);
 		jtf2.setText((String)jtModel.getValueAt(row, 1));
 		jtf3.setText((String)jtModel.getValueAt(row, 2));
 		jtf4.setText((String)jtModel.getValueAt(row, 3));
 		jtf5.setText((String)jtModel.getValueAt(row, 4));
 		jtf6.setText((String)jtModel.getValueAt(row, 5));
 		
 		
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

		 changeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{	
				//ˢ�½���
				jtModel.setValueAt(jtf1.getText(),row, 0);
				jtModel.setValueAt(jtf2.getText(),row, 1);
				jtModel.setValueAt(jtf3.getText(),row, 2);
				jtModel.setValueAt(jtf4.getText(),row, 3);
				jtModel.setValueAt(jtf5.getText(),row, 4);
				jtModel.setValueAt(jtf6.getText(),row, 5);
				//ˢ�����ݿ�
				FamiMember fMember = new FamiMember(MyData.getUser().getUId(),fName,Integer.valueOf(jtf2.getText()),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText());
				dbHelper.changeFamilyInfo( fMember );
				//�ر�
				JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
				setVisible(false);
				
			}});		
		
		this.add(changeButton,BorderLayout.SOUTH);
		this.add(jp1);
		this.setTitle("�޸ļ�ͥ��Ա��Ϣ");
		this.setSize( 400,300);
		
		this.setLocationRelativeTo(null); // ���ô��ھ���
		this.setVisible(true);
		
		
     }
     
}
