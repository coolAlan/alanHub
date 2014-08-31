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
	//中部区域
     JLabel jlb1,jlb2,jlb3,jlb4,jlb5,jlb6,jlb7;
     JTextField jtf1,jtf2,jtf4,jtf5,jtf6,jtf7;
     JComboBox jcb3;
	//北部区域
     JButton xgButton=new JButton("确认提交");
     
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
    	jtf1=new JTextField();//序号
    	jtf1.setText(new Integer(max+1).toString());
    	jtf1.setEditable(false);
    	
 		jtf2 = new JTextField();
 		
 		String[] type = new String[] {"奖励","惩罚"};
    	jcb3 = new JComboBox(type);
    	
 		jtf4=new JTextField();//名称

 		jtf5=new JTextField();//时间
    	
 		jtf6=new JTextField();//原因
 		
 		jtf7=new JTextField();//地点
 		
 		
     	jlb1=new JLabel("序号",JLabel.CENTER);	
     	jlb2=new JLabel("一卡通号",JLabel.CENTER);
     	jlb3=new JLabel("类型",JLabel.CENTER);
		jlb4=new JLabel("名称",JLabel.CENTER);
		jlb5=new JLabel("时间",JLabel.CENTER);
		jlb6=new JLabel("原因",JLabel.CENTER);
		jlb7=new JLabel("地点",JLabel.CENTER);
		
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
		
				//刷新界面
                defaultModel.addRow(new Object[]{gID,jtf2.getText(),type,jtf4.getText(),jtf5.getText(),jtf6.getText(),jtf7.getText()});
				//刷新数据库
                RewOrPuniInfo  info=  new RewOrPuniInfo(gID,jtf2.getText(),type,jtf4.getText(),jtf5.getText(),jtf6.getText(),jtf7.getText());
				adminHelper.addReward(info);
				//关闭
				JOptionPane.showMessageDialog(null, "添加成功！");
				//success =true;
				setVisible(false);
				
			}});
     	
			
		
		this.add(xgButton,BorderLayout.SOUTH);
		this.add(jp1);
		this.setTitle("添加奖惩记录");
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


