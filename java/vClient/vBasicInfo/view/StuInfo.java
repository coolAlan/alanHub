package vBasicInfo.view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import publicData.MyData;

import vBasicInfo.biz.IBasicInfoHelper;
import vCardCenter.view.vIncreConsu;
import vCardCenter.view.vLostReturn;
import vCardCenter.view.vRecordShow;
import vSchoolSys.common.*;


import java.awt.event.*;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

public class StuInfo extends JPanel implements ActionListener{
	
	


	//主内容面板
	private JPanel xjgl_content = new JPanel();
	//内容面板
	private JPanel jp1,familyPanel,eduPanel,jcPanel;
	
	//信息面板
	JPanel fp1,ep1,jcp1;	
	//信息表格
	private DefaultTableModel familyTableModel,eduTableModel2,jcTableModel3;
	static JTable familyTable;
	private JTable eduTable,jcTable;
	
	final CardLayout c = new CardLayout();
	
	//北部区域
	JLabel jlb1;
	//中部第一页
	JPanel jp1_1,jp1_2;
	JLabel jlb3,jlb4,jlb5,jlb6,jlb7,jlb8;
	JButton jb2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	

	
	JButton jb3,jb4;

	JButton jb6;
	JButton jb8;
	
	
	//帮助类
	private IBasicInfoHelper dbHelper=new IBasicInfoHelper();
	

    //构造函数
	public StuInfo(){
		
		BasicInfo basicInfo=dbHelper.getBasicInfo();
		ArrayList<Object> famiMembers=dbHelper.getFamilyMember();
		ArrayList<Object> eduExperiences=dbHelper.getEducationInfo();
		ArrayList<Object> rewOrPuniInfos=dbHelper.getRewOrPunInfo();

		jp1=new JPanel();
		jp1_1=new JPanel();
		jp1_2=new JPanel();
		jp1_1.setOpaque(false);
		jp1_2.setOpaque(false);
		
		jb2=new JButton("修改1",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		jb2.addActionListener((ActionListener) this);
		btnDecorate(jb2);
		
		jp1_2.add(jb2);
		
		
		
		
		
		/**
		 * 家庭成员模块
		 */

		fp1=new JPanel();
		fp1.setOpaque(false);
		familyPanel=new JPanel();
		familyPanel.setOpaque(false);
		
		//fBox
		jb3=new JButton("修改成员",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		jb3.addActionListener((ActionListener) this);
		btnDecorate(jb3);
		jb4=new JButton("添加成员",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		jb4.addActionListener((ActionListener) this);
		btnDecorate(jb4);	
		
		Box fBox = Box.createVerticalBox();
		fBox.add(Box.createVerticalStrut(100));
		fBox.add(jb4);
		fBox.add(Box.createVerticalStrut(5));
		fBox.add(jb3);
		
		//fp1
		familyTableModel = new DefaultTableModel(null,new String[] { "姓名", "年龄","与学生关系","工作单位","职位","电话" });
		familyTable =new JTable(){//MVC设计模式，数据存在Model层
			public boolean isCellEditable(int row, int column)//让每一项都不可编辑
			{ 
				return false;
			};
		};
		familyTable.setOpaque(false);
		for(int i=0;i<famiMembers.size();i++){
			FamiMember famiMember=(FamiMember) famiMembers.get(i);
			familyTableModel.addRow(new String[]{famiMember.getfName(),String.valueOf(famiMember.getfAge()),famiMember.getfRelation(),famiMember.getfWorkPlace(),famiMember.getfJob(),famiMember.getfPhone()});
			}
		familyTable.setModel(familyTableModel);
		familyTable.setRowHeight(35);
		//设置列宽不可调整
        for(int c= 0;c<familyTable.getColumnCount();c++){
        	familyTable.getColumnModel().getColumn(c).setResizable(false);
        }
        //设置列不可移动调换顺序
        familyTable.getTableHeader().setReorderingAllowed(false); 
		
		JScrollPane jsp1 = new JScrollPane(familyTable);
		jsp1.setOpaque(false);
		jsp1.getViewport().setOpaque(false);
		jsp1.setPreferredSize(new Dimension(650,480));
		fp1.add(jsp1,BorderLayout.CENTER);
		
		familyPanel.setLayout(new BorderLayout());
		familyPanel.add(fBox,BorderLayout.WEST);
		familyPanel.add(fp1,BorderLayout.CENTER);
		//familyPanel.add(fp2,BorderLayout.WEST);
		
		
		/**
		 * 教育信息模块
		 */
		eduPanel=new JPanel();
		eduPanel.setOpaque(false);
		ep1=new JPanel();
		ep1.setOpaque(false);
		
		//ep1
		eduTableModel2 = new DefaultTableModel(null,new String[] { "开始日期","终止日期","学校","获得学历"});
		for(int i=0;i<eduExperiences.size();i++){
		EduExperience eduExperience=(EduExperience) eduExperiences.get(i);
		eduTableModel2.addRow(new String[]{eduExperience.getEduStartTime(),eduExperience.getEduEndTime(),eduExperience.getSchool(),eduExperience.getDegree()});
		}
		eduTable =new JTable(){//MVC设计模式，数据存在Model层
			public boolean isCellEditable(int row, int column)//让每一项都不可编辑
			{ 
				return false;
			};
		};
		eduTable.setModel(eduTableModel2);
		eduTable.setRowHeight(35);
		//设置列宽不可调整
        for(int c= 0;c<eduTable.getColumnCount();c++){
        	eduTable.getColumnModel().getColumn(c).setResizable(false);
        }
        //设置列不可移动调换顺序
        eduTable.getTableHeader().setReorderingAllowed(false); 
        
		JScrollPane jsp2 = new JScrollPane(eduTable);
		jsp2.setOpaque(false);
		jsp2.getViewport().setOpaque(false);
		jsp2.setPreferredSize(new Dimension(650,480));
		
		ep1.add(jsp2,BorderLayout.CENTER);
	
		//eBox
		jb6=new JButton("添加信息",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		jb6.addActionListener((ActionListener) this);
		btnDecorate(jb6);
		jb8=new JButton("删除信息",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		jb8.addActionListener((ActionListener) this);
		btnDecorate(jb8);
		
		Box eBox = Box.createVerticalBox();
		eBox.add(Box.createVerticalStrut(100));
		eBox.add(jb6);
		eBox.add(Box.createVerticalStrut(5));
		eBox.add(jb8);
		eBox.setBackground(Color.black);
		
		//eduPanel
		eduPanel.setLayout(new BorderLayout());
		eduPanel.add(eBox,BorderLayout.WEST);
		eduPanel.add(ep1,BorderLayout.CENTER);

		
		
		
		/**
		 * 奖惩模块
		 */
		jcPanel=new JPanel();	
		jcPanel.setOpaque(false);	
		jcp1=new JPanel();
		jcp1.setOpaque(false);
		
		//jcp1
		jcTableModel3 = new DefaultTableModel(null,new String[] { "奖励/处罚","名称","时间","原因","执行单位"});
		
		jcTable =new JTable(){
			public boolean isCellEditable(int row, int column)//让每一项都不可编辑
			{ 
				return false;
			};
		};
		for(int i=0;i<rewOrPuniInfos.size();i++){
			RewOrPuniInfo rewOrPuniInfo=(RewOrPuniInfo) rewOrPuniInfos.get(i);
			jcTableModel3.addRow(new String[]{String.valueOf(rewOrPuniInfo.getURewOrPuni()),rewOrPuniInfo.getUROrPName(),String.valueOf(rewOrPuniInfo.getUROrPTime()),rewOrPuniInfo.getUReason(),rewOrPuniInfo.getUPlace()});
		}
		jcTable.setModel(jcTableModel3);
		jcTable.setRowHeight(35);
		//设置列宽不可调整
        for(int c= 0;c<jcTable.getColumnCount();c++){
        	jcTable.getColumnModel().getColumn(c).setResizable(false);
        }
        //设置列不可移动调换顺序
        jcTable.getTableHeader().setReorderingAllowed(false); 
        
		JScrollPane jsp3 = new JScrollPane(jcTable);
		jcTable.setOpaque(false);
		jsp3.setOpaque(false);
		jsp3.getViewport().setOpaque(false);
		jsp3.setPreferredSize(new Dimension(650,480));
		jcp1.add(jsp3,BorderLayout.CENTER);

		jcPanel.add(jcp1,BorderLayout.CENTER);
		
		
		
		
		/**
		 * 基本信息
		 */	
		jlb3=new JLabel("姓名",JLabel.CENTER);
		jlb4=new JLabel("年龄",JLabel.CENTER);
		jlb5=new JLabel("籍贯",JLabel.CENTER);
		jlb6=new JLabel("一卡通号",JLabel.CENTER);
		jlb7=new JLabel("学号",JLabel.CENTER);
		jlb8=new JLabel("性别",JLabel.CENTER);
	
		jtf1=new JTextField(9);
		jtf2=new JTextField(9);
		jtf3=new JTextField(9);
		jtf4=new JTextField(9);
		jtf5=new JTextField(9);
		jtf6=new JTextField(9);
		if(basicInfo != null){
		jtf1.setText( basicInfo.getuName());
		jtf2.setText( String.valueOf((basicInfo.getuAge())));
		jtf3.setText( basicInfo.getuAddress());
		jtf4.setText( basicInfo.getuID());
		jtf5.setText( basicInfo.getuStdNumber());
		jtf6.setText( String.valueOf(basicInfo.getuSex()));
		}
		jtf1.setEditable(false);
		jtf2.setEditable(false);
		jtf3.setEditable(false);
		jtf4.setEditable(false);
		jtf5.setEditable(false);
		jtf6.setEditable(false);
		

		jp1_1.setLayout(new GridLayout(6,2));
		jp1_1.add(jlb3);
		jp1_1.add(jtf1);
		jp1_1.add(jlb4);
		jp1_1.add(jtf2);
		jp1_1.add(jlb5);
		jp1_1.add(jtf3);
		jp1_1.add(jlb6);
		jp1_1.add(jtf4);
		jp1_1.add(jlb7);
		jp1_1.add(jtf5);
		jp1_1.add(jlb8);
		jp1_1.add(jtf6);
		
		jp1.add(jp1_1,BorderLayout.CENTER);
		jp1.add(jp1_2,BorderLayout.SOUTH);
		

		
	


		//按钮
		JButton jb_jtcy = new JButton("家庭成员",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		btnDecorate(jb_jtcy);
		JButton jb_jyjl = new JButton("教育经历",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		btnDecorate(jb_jyjl);
		JButton jb_jcxx = new JButton("奖惩信息",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		btnDecorate(jb_jcxx);
		jb_jtcy.setPreferredSize(new Dimension(5,35));
		jb_jyjl.setPreferredSize(new Dimension(5,35));
		jb_jcxx.setPreferredSize(new Dimension(5,35));	
		//按钮监听	
		jb_jtcy.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				c.show(xjgl_content,"家庭成员");
				
			}
			
		});
		jb_jyjl.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				c.show(xjgl_content,"教育经历");
				
			}
			
		});
		jb_jcxx.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				c.show(xjgl_content,"奖惩信息");
				
			}
			
		});
		
		//菜单箱子
		Box horizontal = Box.createHorizontalBox();//****HorizontalBox
		horizontal.add(Box.createHorizontalStrut(5));
		horizontal.add(jb_jtcy);
		horizontal.add(Box.createHorizontalStrut(5));
		horizontal.add(jb_jyjl);
		horizontal.add(Box.createHorizontalStrut(5));
		horizontal.add(jb_jcxx);
			
		//内容主面板
		xjgl_content.setLayout(c);
		xjgl_content.setOpaque(false);
		xjgl_content.add("家庭成员",familyPanel);
		xjgl_content.add("教育经历",eduPanel);
		xjgl_content.add("奖惩信息",jcPanel);
		xjgl_content.setVisible(true);
		
		//主面板
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.add(horizontal,BorderLayout.NORTH);
		this.add(xjgl_content,BorderLayout.CENTER);
		this.setOpaque(false);
		this.setVisible(true);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comm = e.getActionCommand();
		if(comm.equals("添加信息")){
			try {
				
				new insertEduExperience().insert(eduTableModel2);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		
		    }
		else if(comm.equals("修改成员")){
			
			try {
				int rowNo=StuInfo.familyTable.getSelectedRow();
				if(rowNo==-1){
					JOptionPane.showMessageDialog(StuInfo.familyTable, "请选择一行");
					return;
				}
				String fName=(String)familyTableModel.getValueAt(rowNo,0);
				
				new changeFamiMem(fName,familyTableModel,rowNo);
				//new changeFamiMem(fName);
				//jTableModel1.
				//jTableModel1.fireTableRowsUpdated(rowNo,rowNo);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}	
		//else if(comm.equals("保存2")){
		//	new saveEduExperience();
			
		//}
		//else if(comm.equals("保存3")){
		//	new saveRewOrPuniInfo();
			
		//}	
		else if(comm.equals("添加成员")){

			try {
				new insertFamiMember().insertfm(familyTableModel);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
		}
	
		else if(comm.equals("修改1")){
			
		    new chanBasInfo(this);
			
		}
		else if(comm.equals("删除信息")){
			int rowNum=this.eduTable.getSelectedRow();
			if(rowNum==-1){
				JOptionPane.showMessageDialog(eduTable, "请选择一行");
				return;
			}
			String school=(String)eduTableModel2.getValueAt(rowNum, 2);
			
			System.out.println("school");
			 dbHelper.deleEducationInfo(school);
			 eduTableModel2.removeRow(rowNum);
			 JOptionPane.showMessageDialog(null, "删除成功！");
			 setVisible(true);
		}
		
	}
	
	public void btnDecorate(JButton btn){
		btn.setHorizontalTextPosition(0);
		btn.setBackground(new Color(0,0,0));
		btn.setOpaque(false);
		btn.setBorderPainted(false);//按钮不画边框
		btn.setFocusPainted(false);
		btn.addMouseListener(new myButtonListener(btn,"Image/button-normal.png","Image/button-click.png"));
		btn.setContentAreaFilled(false);
		btn.setPressedIcon(new ImageIcon(getClass().getResource("/Image/button-hold.png")));
	}

	class myButtonListener extends MouseAdapter{//继承MouseAdapter，重写部分方法
		JButton jb;
		String picture1;
		String picture2;
		public myButtonListener(JButton jb,String picture1,String picture2){
			this.jb=jb;
			this.picture1=picture1;
			this.picture2=picture2;
			
			
		}
		public void mouseEntered(MouseEvent arg0) {
			jb.setIcon(new ImageIcon(getClass().getResource("/"+picture2)));
			
			
		}

		public void mouseExited(MouseEvent arg0) {
			jb.setIcon(new ImageIcon(getClass().getResource("/"+picture1)));
			
	    }
	}
	
	public JTextField getJtf2() {
		return jtf2;
	}
	/**
	 * @param jtf2 The jtf2 to set.
	 */
	public void setJtf2(JTextField jtf2) {
		this.jtf2 = jtf2;
	}
	/**
	 * @return Returns the jtf3.
	 */
	public JTextField getJtf3() {
		return jtf3;
	}
	/**
	 * @param jtf3 The jtf3 to set.
	 */
	public void setJtf3(JTextField jtf3) {
		this.jtf3 = jtf3;
	}

}
