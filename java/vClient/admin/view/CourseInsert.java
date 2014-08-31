package admin.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import admin.biz.IAdminHelper;


import publicData.MyData;
import vBasicInfo.biz.IBasicInfoHelper;
import vSchoolSys.common.Course;
import vSchoolSys.common.FamiMember;

import java.awt.event.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class CourseInsert extends JFrame {
     JPanel jp1; 
	//�в�����
     JLabel jlb1,jlb2,jlb3,jlb4,jlb5,jlb6;
     JTextField jtf1,jtf2,jtf3,jtf5,jtf6;
     JComboBox jcb1,jcb2,jcb3;
	//��������
     JButton xgButton=new JButton("ȷ�����");
     
     private DefaultTableModel defaultModel1;
     private int num;
     private IAdminHelper adminHelper = new IAdminHelper();
     private boolean success =false;
     @SuppressWarnings("deprecation")
	public CourseInsert(final DefaultTableModel defaultModel,int row) throws ParseException{
    	this.defaultModel1=defaultModel;
    	//this.cID = cID;
    	this.num = row;
    	String[] week = new String[] {"��һ","�ܶ�","����","����","����"};
    	String[] day = new String[] {"����","����","����"};
    	String[] time = new String[]{"1��2��","3��4��"};
    	jtf1=new JTextField();
 		jtf2=new JTextField();
 		jtf3=new JTextField();
 		jcb1=new JComboBox(week);
 		jcb2=new JComboBox(day);
 		jcb3=new JComboBox(time);
 		JPanel jpl = new JPanel();
 		jpl.add(jcb1);
 		jpl.add(jcb2);
 		jpl.add(jcb3);
 		jtf5=new JTextField();
 		jtf6=new JTextField();
 		jtf6.setText("50");
     	jlb1=new JLabel("�γ̱��",JLabel.CENTER);	
		jlb2=new JLabel("�γ�����",JLabel.CENTER);
		jlb3=new JLabel("�ον�ʦ",JLabel.CENTER);
		jlb4=new JLabel("�Ͽ�ʱ��",JLabel.CENTER);
		jlb5=new JLabel("��ʱ",JLabel.CENTER);
		jlb6=new JLabel("����",JLabel.CENTER);
     	jp1=new JPanel();
     	jp1.setLayout(new GridLayout(6,2));
		jp1.add(jlb1);
		jp1.add(jtf1);
		jp1.add(jlb2);
		jp1.add(jtf2);
		jp1.add(jlb3);
		jp1.add(jtf3);
		jp1.add(jlb4);
		jp1.add(jpl);
		jp1.add(jlb5);
		jp1.add(jtf5);
		jp1.add(jlb6);
		jp1.add(jtf6);

		 xgButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
				String cID = jtf1.getText();
				for(int i =0;i<num;i++){
				String id=(String)defaultModel1.getValueAt(i, 0);
				if(cID.equals(id)){
					JOptionPane.showMessageDialog(null, "�γ̱���ظ������������룡");
					success =false;
					return;
				}
				}
				String time = jcb1.getSelectedItem().toString()+jcb2.getSelectedItem().toString()+jcb3.getSelectedItem().toString();
				//ˢ�½���
				defaultModel.addRow(new Object[]{cID,jtf2.getText(),jtf3.getText(),time,jtf5.getText(),jtf6.getText()});
				//ˢ�����ݿ�
				Course course = new Course(cID,jtf2.getText(),jtf3.getText(),new Integer(jtf5.getText()).intValue(),time,
						new Integer(jtf6.getText()).intValue(),0);
				adminHelper.addCourse(course);
				//�ر�
				JOptionPane.showMessageDialog(null, "��ӳɹ���");
				success =true;
				setVisible(false);
				
			}});
     	
			
		
		this.add(xgButton,BorderLayout.SOUTH);
		this.add(jp1);
		this.setTitle("��ӿγ�");
		this.setSize( 400,300);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//this.setLocation(20,30);
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


