
package vBasicInfo.view;
import java.awt.*;

import javax.swing.*;


import publicData.MyData;
import vBasicInfo.biz.IBasicInfoHelper;
import vSchoolSys.common.BasicInfo;


import java.awt.event.*;


public class chanBasInfo extends JFrame{
     
	 JPanel jp1; 
     JLabel jlb1,jlb2,jlb3,jlb4,jlb5,jlb6;
     JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;//��Ϣ��д��
     JButton xgButton;//ȷ���޸İ�ť
     
     StuInfo stuInfoFrame;

     private IBasicInfoHelper dbHelper=new IBasicInfoHelper(); //���ԣ�ѧ��������Ϣ���������
     
     public chanBasInfo(StuInfo stuFrame){
     	
     	this.stuInfoFrame=stuFrame;
     	
     	xgButton=new JButton("ȷ���޸�");
     	xgButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				int age=new Integer(jtf2.getText());
				String address=jtf3.getText();
				//ˢ�½���
				(stuInfoFrame.getJtf2()).setText(jtf2.getText());
				(stuInfoFrame.getJtf3()).setText(jtf3.getText());
				//ˢ�����ݿ�
				dbHelper.changeBasicInfo(age, address);
				
				JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
				setVisible(false);
			}
     		
     	});
     	this.add(xgButton,BorderLayout.SOUTH);
     	
     	jp1=new JPanel();
     	jlb1=new JLabel("����",JLabel.CENTER);
     	jlb1.setEnabled(false);
		jlb2=new JLabel("����",JLabel.CENTER);
		jlb3=new JLabel("����",JLabel.CENTER);
		jlb4=new JLabel("һ��ͨ��",JLabel.CENTER);
		jlb4.setEnabled(false);
		jlb5=new JLabel("ѧ��",JLabel.CENTER);
		jlb6=new JLabel("�Ա�",JLabel.CENTER);
		jlb6.setEnabled(false);
		jtf1=new JTextField();
		jtf1.setEnabled(false);
		
		jtf2=new JTextField();
		jtf3=new JTextField();
		jtf4=new JTextField();
		jtf4.setEnabled(false);
		jtf5=new JTextField();
		jtf5.setEnabled(false);
		jtf6=new JTextField();
		jtf6.setEnabled(false);
		
	
		BasicInfo basicInfo=dbHelper.getBasicInfo();
		jtf1.setText(basicInfo.getuName());
		jtf2.setText( ""+basicInfo.getuAge());
		jtf3.setText(basicInfo.getuAddress());
		jtf4.setText(basicInfo.getuID());
		jtf5.setText(basicInfo.getuStdNumber());
		jtf6.setText(String.valueOf(basicInfo.getuSex()));
		
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
		this.add(jp1);
		this.setTitle("�޸Ļ�����Ϣ");
		this.setSize( 400,300);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
		
     }

}
