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

import vSchoolSys.common.Book;
import vSchoolSys.common.RewOrPuniInfo;

import admin.biz.IAdminHelper;



public class BookInsert extends JFrame {
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
	public BookInsert(final DefaultTableModel defaultModel) throws ParseException{
    	this.defaultModel1=defaultModel;
    	//this.cID = cID;
    	
    	
    	
    	 int m = defaultModel.getRowCount();
    	 
    	jtf1=new JTextField();//���
    	jtf1.setText("00"+new Integer(m+1).toString());
    	
    	
 		jtf2 = new JTextField();//����
 		
 		jtf4=new JTextField();//����

 		jtf5=new JTextField();//����
    	
 		
 		
 		
     	jlb1=new JLabel("�����",JLabel.CENTER);	
     	jlb2=new JLabel("����",JLabel.CENTER);
     	
		jlb4=new JLabel("����",JLabel.CENTER);
		jlb5=new JLabel("����",JLabel.CENTER);
		
		
     	jp1=new JPanel();
     	jp1.setLayout(new GridLayout(7,2));
		jp1.add(jlb1);
		jp1.add(jtf1);
		jp1.add(jlb2);
		jp1.add(jtf2);
		
		jp1.add(jlb4);
		jp1.add(jtf4);
		jp1.add(jlb5);
		jp1.add(jtf5);
		

		 xgButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) 
			{
				
				String bID = jtf1.getText();
				
				//String type = (String)jcb3.getSelectedItem();
		
				//ˢ�½���
                defaultModel.addRow(new Object[]{bID,jtf2.getText(),jtf4.getText(),jtf5.getText()});
				//ˢ�����ݿ�
                Book book = new Book(bID,jtf2.getText(),new Integer(jtf4.getText()).intValue(),jtf5.getText());
				adminHelper.addBook(book);
				//�ر�
				JOptionPane.showMessageDialog(null, "��ӳɹ���");
				//success =true;
				setVisible(false);
				
			}});
     	
			
		
		this.add(xgButton,BorderLayout.SOUTH);
		this.add(jp1);
		this.setTitle("���ͼ��");
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


