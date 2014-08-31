package admin.view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import vSchoolSys.common.RewOrPuniInfo;

import admin.biz.IAdminHelper;



public class RewardInsert extends JFrame {
     JPanel jp1; 
	//�в�����
     JLabel jlb1,jlb2,jlb3,jlb4,jlb5,jlb6,jlb7;
     JTextField jtf1,jtf2,jtf4,jtf5,jtf6,jtf7;
     JComboBox jcb3;
	//��������
     JButton xgButton=new JButton("ȷ���ύ");
     
     private DefaultTableModel defaultModel1;
     
     private IAdminHelper adminHelper = new IAdminHelper();
     private boolean success =false;
     @SuppressWarnings("deprecation")
	public RewardInsert(final DefaultTableModel defaultModel) throws ParseException{
    	this.defaultModel1=defaultModel;
    	//this.cID = cID;
    	
    	
    	
    	 int m = defaultModel.getRowCount();
    	 int max =0;
    	 for(int i=0;i<m;i++){
    	 	int temp = ((Integer)defaultModel.getValueAt(i,0)).intValue();
    	 	if(temp >max)
    	 		max=temp;
    	 }
    	jtf1=new JTextField();//���
    	jtf1.setText(new Integer(max+1).toString());
    	jtf1.setEditable(false);
    	
 		jtf2 = new JTextField();
 		
 		String[] type = new String[] {"����","�ͷ�"};
    	jcb3 = new JComboBox(type);
    	
 		jtf4=new JTextField();//����

 		jtf5=new JTextField();//ʱ��
    	
 		jtf6=new JTextField();//ԭ��
 		
 		jtf7=new JTextField();//�ص�
 		
 		
     	jlb1=new JLabel("���",JLabel.CENTER);	
     	jlb2=new JLabel("һ��ͨ��",JLabel.CENTER);
     	jlb3=new JLabel("����",JLabel.CENTER);
		jlb4=new JLabel("����",JLabel.CENTER);
		jlb5=new JLabel("ʱ��",JLabel.CENTER);
		jlb6=new JLabel("ԭ��",JLabel.CENTER);
		jlb7=new JLabel("�ص�",JLabel.CENTER);
		
     	jp1=new JPanel();
     	jp1.setLayout(new GridLayout(7,2));
		jp1.add(jlb1);
		jp1.add(jtf1);
		jp1.add(jlb2);
		jp1.add(jtf2);
		jp1.add(jlb3);
		jp1.add(jcb3);
		jp1.add(jlb4);
		jp1.add(jtf4);
		jp1.add(jlb5);
		jp1.add(jtf5);
		jp1.add(jlb6);
		jp1.add(jtf6);
		jp1.add(jlb7);
		jp1.add(jtf7);

		 xgButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
				
				int gID = new Integer(jtf1.getText()).intValue();
				
				String type = (String)jcb3.getSelectedItem();
		
				//ˢ�½���
                defaultModel.addRow(new Object[]{gID,jtf2.getText(),type,jtf4.getText(),jtf5.getText(),jtf6.getText(),jtf7.getText()});
				//ˢ�����ݿ�
                RewOrPuniInfo  info=  new RewOrPuniInfo(gID,jtf2.getText(),type,jtf4.getText(),jtf5.getText(),jtf6.getText(),jtf7.getText());
				adminHelper.addReward(info);
				//�ر�
				JOptionPane.showMessageDialog(null, "��ӳɹ���");
				//success =true;
				setVisible(false);
				
			}});
     	
			
		
		this.add(xgButton,BorderLayout.SOUTH);
		this.add(jp1);
		this.setTitle("��ӽ��ͼ�¼");
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


