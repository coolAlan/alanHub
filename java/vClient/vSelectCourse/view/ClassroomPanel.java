package vSelectCourse.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import publicData.MyData;
import vSchoolSys.common.RoomRecord;
import vSchoolSys.common.User;
import vSelectCourse.biz.ICourseHelper;

public class ClassroomPanel extends JPanel {
//	public static Classroom cRoom;
	private Object time;		//记录durationBox选中项信息的对象
	private String number;		//NumberBox选中项信息被转化为的String类对象
	private String cDuration = "8:00~9:30";
	private int cCapacity;		//教师容量
	private String cDate;		//借用日期
	private RoomRecord record;	
	private User user = null;
	
	private ICourseHelper courseHelper = new ICourseHelper();
//	private ArrayList<Object> ClassroomList;
	
	static JPanel outerPanel = new JPanel();	//借教室的大子容器
	static JPanel innerPanel = new JPanel();	//包含label、combobox、textfield和button的小容器
	static JLabel l1 = new JLabel("人数");
	static JLabel l2 = new JLabel("时间");
	static JLabel l3 = new JLabel("日期");
	static EmptyRoomTable roomTable;
	static boolean rent;	//记录借用是否成功
	
	static JComboBox DurationBox;
	static JComboBox NumberBox;
	
	static JTextField date = new JTextField(10);
//	static JTextField phoneNumber = new JTextField(11);
	
	static JButton showRoom = new JButton("显示可借");
	static JButton register = new JButton("借用登记");
/*	static JButton ok = new JButton("登记");
	static JButton cancel = new JButton("取消");
*/	
	public ClassroomPanel() {
//		ClassroomList = list;
		this.user = MyData.getUser();
		outerPanel.setOpaque(false);
		innerPanel.setOpaque(false);
//		this.setcDate(date.getText());
		
		//创建两个ComoboBox
		Vector<String> DurationList = new Vector<String>(); 
		DurationList.add("8:00~9:30");
		DurationList.add("9:40~12:00");
		DurationList.add("14:00~15:30");
		DurationList.add("15:40~18:00");
		DurationList.add("18:30~21:00");
		DurationList.add("21:00~22:00");
		DurationList.add("18:30~22:00");	
		DurationBox = new JComboBox(DurationList);//DurationBox构建成功
		DurationBox.setMaximumRowCount(4);
		DurationBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			//获取DurationBox选中项，结果保存在time中
			time = DurationBox.getSelectedItem();
			ClassroomPanel.this.setcDuration(time);
			}
		});
		
//		String duration = time.toString();
//		cDuration = time.toString();
		
		Vector<String> NumberList = new Vector<String>();
		NumberList.add("30人以下");
		NumberList.add("30~60人");
		NumberList.add("60人以上");	
		number = "30人以下";
		NumberBox = new JComboBox(NumberList);//NumberBox构建成功
		NumberBox.setMaximumRowCount(3);
		//为NumberBox设置添加事件监听器（用匿名内部类）
		NumberBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			//获取NumberBox选中项，结果保存在number中
				number = (String)NumberBox.getSelectedItem();
				System.out.println(number);
				//ClassroomPanel.this.setcCapacity(number);
			}			
		});
		
				
		//增加监听
		ButtonListener listener1 = new ButtonListener();
		showRoom.addActionListener(listener1);
		register.addActionListener(listener1);
		

		//向outerPanel里添加组件
		innerPanel.add(l1);
		innerPanel.add(NumberBox);
		innerPanel.add(l2);
		innerPanel.add(DurationBox);
		innerPanel.add(l3);
		innerPanel.add(date);
		innerPanel.add(showRoom);
		innerPanel.add(register);
		innerPanel.setPreferredSize(new Dimension(800,120));
		outerPanel.setLayout(new FlowLayout());
		outerPanel.add(innerPanel);
		outerPanel.setVisible(true);
		
		}

	//内部监听类
	private class ButtonListener implements ActionListener {
		/* 
		 * 利用该内部类来监听所有事件源产生的事件 
		 * 便于处理事件代码模块化 
		 */ 
//		EmptyRoomTable roomTable = null;
		public void actionPerformed(ActionEvent e) { 
			String buttonName = e.getActionCommand();
			if(buttonName.equals("显示可借")) {
				cDate = date.getText();
				record = new RoomRecord(setcCapacity(number), getcDate(),getcDuration());
				if(roomTable != null) {
					roomTable.setVisible(false);
				}
				
				//。。。。。。。。。。。。。。。。。。。。
				roomTable = new EmptyRoomTable ( courseHelper.checkRoom(record));
				
				outerPanel.add(roomTable);
				outerPanel.setVisible(false);
				outerPanel.setVisible(true);
			}
			else if(buttonName.equals("借用登记")) {
				int row=roomTable.getcTable().getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(roomTable.getcTable(), "请选择一行");
					return;
				}
				
				String cName = (String)roomTable.getModel().getValueAt(row,0);
				record = new RoomRecord(cName,setcCapacity(number), getcDate(),getcDuration());
				//System.out.println("到大是怎么回事呢，好纠结啊"+cName);
				record.setcRoomNumber(cName);
				record.s ="1111";
				System.out.println(record.getcRoomNumber());
				
				int res = JOptionPane.showConfirmDialog(null, 
						"确认要借用此教室吗？", "确认借用", JOptionPane.YES_NO_CANCEL_OPTION);
				if(res == JOptionPane.YES_OPTION) {
					
					//。。。。。。。。。。。。。。。。。。。。。。。。
					rent =  courseHelper.rentRoom(user, record);
					if(rent) {
						JOptionPane.showMessageDialog(outerPanel, 
								"借用教室成功"); 
						roomTable.getModel().removeRow(row);
						outerPanel.setVisible(false);
						outerPanel.setVisible(true);
					}
				}
				else if(res == JOptionPane.NO_OPTION || res == JOptionPane.CANCEL_OPTION) {
					return;
				}
				
			}	
		}
	}
	
	//set、get函数
	public void setcDuration(Object time) {
		cDuration = (String) time;
	}
	public String getcDuration() {
		return cDuration;
	}
	
	public int setcCapacity(String num) {
		String Num = num;
		if(Num.equals("30人以下"))
			cCapacity = 30;
		else if(Num.equals("30~60人"))
			cCapacity = 60;
		else if(Num.equals("60人以上"))
			cCapacity = 200;
		System.out.println(Num);
		return cCapacity;
	}
	public int getcCapacity() {
		return cCapacity;
	}
	
	public void setcDate(String date) {
		cDate = date;
	}
	public String getcDate() {
		return cDate;
	}
	
	public JPanel getouterPanel() {
		return outerPanel;
	}
	
	public RoomRecord getRecord() {
		return record;
	}
}
