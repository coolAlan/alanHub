package vCardCenter.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.List;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;


import publicData.MyData;

import vCardCenter.biz.IRecordSrv;
import vSchoolSys.common.ItemList;
import vSchoolSys.common.Message;

/**
* ������ʾ���Ѽ�¼��� 
*/
public class vRecordShow extends javax.swing.JPanel {
	private JPanel jpl;
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private JLabel jlbl_title;
	private JPanel jpl3;
	private JPanel jpl2;
	private JLabel jpl_remain = new JLabel();
	
	private String uID=MyData.user.getUId();
	private String uName=MyData.user.getUName();
	
	private DefaultTableModel jTableModel;
	private Message msg;

	/**
	* Auto-generated main method to display this JFrame
	*/
		
	public vRecordShow() {
		super();
		//this.setTitle("�˵���¼");
		IRecordSrv IRS = new IRecordSrv();
		msg = IRS.getMessage();
		if(msg==null)
			System.out.println("record message is null");
		initGUI();
	}
	
	private void initGUI() {
		try {
			
			//setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			//getContentPane().setLayout( new BorderLayout());
			jpl = new JPanel();
			
			jpl.setLayout(new BorderLayout());
			//getContentPane().add(jpl, BorderLayout.SOUTH);
			this.setPreferredSize(new Dimension(900,460));
			this.add(jpl, BorderLayout.SOUTH);
			//new String[][] {{"1","����","100.00","2013-8-8"},{"2","����","100.00","2013-8-8"}},
			List itemList = msg.getData();		
			jTableModel = 
				new DefaultTableModel(
							null,
							new String[] { "���", "����","���","ʱ��" });
			
			jTable1 = new JTable(){//MVC���ģʽ�����ݴ���Model��
				public boolean isCellEditable(int row, int column)//��ÿһ����ɱ༭
				{ 
					return false;
				};
			};
			
			///����ItemList��ArrayList
			jTable1.setModel(jTableModel);
			ItemList item;
			System.out.println("size"+itemList.size());
			for(int i=0;i<itemList.size();i++){
	        	Object obj = 	itemList.get(i);
				item = (ItemList)obj;
			if(item.getConsumeName()==0)
			jTableModel.addRow(new String[]{new Integer(item.getOrder()).toString(),"����",
									new Double(item.getIsum()).toString(),
									item.getDate().toString()});
			if(item.getConsumeName()==1)
				jTableModel.addRow(new String[]{new Integer(item.getOrder()).toString(),"��ֵ",
										new Double(item.getIsum()).toString(),
										item.getDate().toString()});
			}
			jTable1.setGridColor(Color.BLUE);
			jTable1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			//jTable1.set;
			JScrollPane jsp = new JScrollPane(jTable1);
			jsp.setOpaque(false);
			jpl.add(jsp, BorderLayout.SOUTH);
			//jTable1.setLayout(null);
			
		    this.add(jpl, BorderLayout.CENTER);	
			jpl3 = new JPanel();
			//jpl3.setPreferredSize(new java.awt.Dimension(392, 28));
					
			jlbl_title = new JLabel();
			jlbl_title.setHorizontalAlignment(SwingConstants.CENTER);
			jlbl_title.setText("ѧ�����Ѽ�¼");
			jpl3.setLayout(new BorderLayout());
			
			jpl3.add(jlbl_title,BorderLayout.CENTER);
			JLabel jpl_ID = new JLabel();
			JLabel jpl_Name = new JLabel();
			
			
			jpl_ID.setText(uID);
			jpl_ID.setHorizontalAlignment(SwingConstants.LEFT);
			
			jpl_Name.setText(uName);
			jpl_Name.setHorizontalAlignment(SwingConstants.RIGHT);
			
			jpl_remain.setText("��"+MyData.getUser().getRemain());
			
			JPanel jpl4 = new JPanel();
			jpl4.setLayout(new FlowLayout());
			jpl4.setOpaque(false);
			jpl4.add(jpl_ID);
			jpl4.add(jpl_Name);
			jpl4.add(jpl_remain);
			jpl3.add(jpl4,BorderLayout.SOUTH);
			jpl3.add(jlbl_title,BorderLayout.CENTER);
			jpl3.setOpaque(false);
					
			this.add(jpl3, BorderLayout.NORTH);		
			this.setOpaque(false);
		//	pack();
			
			
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	/**
	 * @return Returns the jTable1Model.
	 */
	public DefaultTableModel getJTable1Model() {
		return jTableModel;
	}
	/**
	 * @param table1Model The jTable1Model to set.
	 */
	public void setJTable1Model(DefaultTableModel table1Model) {
		jTableModel = table1Model;
	}
	/**
	 * @return Returns the uID.
	 */
	public String getUID() {
		return uID;
	}
	/**
	 * @param uid The uID to set.
	 */
	public void setUID(String uid) {
		this.uID = uid;
	}
	/**
	 * @return Returns the uName.
	 */
	public String getUName() {
		return uName;
	}
	/**
	 * @param name The uName to set.
	 */
	public void setUName(String name) {
		this.uName = name;
	}
	
}
