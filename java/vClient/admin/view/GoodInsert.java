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
	//�в�����
     JLabel jlb1,jlb2,jlb3,jlb4,jlb5;
     JTextField jtf1,jtf2,jtf3,jtf4;
     JComboBox jcb1;
	//��������
     JButton xgButton=new JButton("ȷ�����");
     
     private DefaultTableModel defaultModel1;
     private int num;
     private IAdminHelper adminHelper = new IAdminHelper();
     private boolean success =false;
     @SuppressWarnings("deprecation")
	public GoodInsert(final DefaultTableModel defaultModel) throws ParseException{
    	this.defaultModel1=defaultModel;
    	//this.cID = cID;
    	
    	String[] type = new String[] {"ˮ��","ͼ��","���˻���","��Ʒ��Ʒ","����Ʒ","��ʳС��"};
    	jtf1=new JTextField();//��ƷID
 		jtf2=new JTextField();//��Ʒ����
 		jtf3=new JTextField();//��Ʒ�۸�
 		JPanel jpl = new JPanel();
 		jtf4=new JTextField();//��Ʒ����
 		jcb1 = new JComboBox(type);//��Ʒ����
 		jpl.add(jcb1);
 		jtf4.setText("50");
 		jtf3.setText("10.00");
     	jlb1=new JLabel("��Ʒ���",JLabel.CENTER);	
		jlb2=new JLabel("��Ʒ����",JLabel.CENTER);
		jlb3=new JLabel("��Ʒ�۸�",JLabel.CENTER);
		jlb4=new JLabel("��Ʒ����",JLabel.CENTER);
		jlb5=new JLabel("��Ʒ����",JLabel.CENTER);
		
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
					JOptionPane.showMessageDialog(null, "��Ʒ����ظ������������룡");
					success =false;
					return;
				}
				}
				int type = jcb1.getSelectedIndex()+1;
				String sType = jcb1.getSelectedItem().toString();
				//ˢ�½���
				defaultModel.addRow(new Object[]{gID,jtf2.getText(),jtf3.getText(),jtf4.getText(),sType});
				//ˢ�����ݿ�
				DataGoods good = new DataGoods(gID,jtf2.getText(),new Double(jtf3.getText()).doubleValue(),
						new Integer(jtf4.getText()).intValue(),"Image/goods/fruit/block.jpg",type);
				adminHelper.addGood(good);
				//�ر�
				JOptionPane.showMessageDialog(null, "��ӳɹ���");
				success =true;
				setVisible(false);
				
			}});
     	
			
		
		this.add(xgButton,BorderLayout.SOUTH);
		this.add(jp1);
		this.setTitle("�����Ʒ");
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


