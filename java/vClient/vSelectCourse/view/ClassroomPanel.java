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
	private Object time;		//��¼durationBoxѡ������Ϣ�Ķ���
	private String number;		//NumberBoxѡ������Ϣ��ת��Ϊ��String�����
	private String cDuration = "8:00~9:30";
	private int cCapacity;		//��ʦ����
	private String cDate;		//��������
	private RoomRecord record;	
	private User user = null;
	
	private ICourseHelper courseHelper = new ICourseHelper();
//	private ArrayList<Object> ClassroomList;
	
	static JPanel outerPanel = new JPanel();	//����ҵĴ�������
	static JPanel innerPanel = new JPanel();	//����label��combobox��textfield��button��С����
	static JLabel l1 = new JLabel("����");
	static JLabel l2 = new JLabel("ʱ��");
	static JLabel l3 = new JLabel("����");
	static EmptyRoomTable roomTable;
	static boolean rent;	//��¼�����Ƿ�ɹ�
	
	static JComboBox DurationBox;
	static JComboBox NumberBox;
	
	static JTextField date = new JTextField(10);
//	static JTextField phoneNumber = new JTextField(11);
	
	static JButton showRoom = new JButton("��ʾ�ɽ�");
	static JButton register = new JButton("���õǼ�");
/*	static JButton ok = new JButton("�Ǽ�");
	static JButton cancel = new JButton("ȡ��");
*/	
	public ClassroomPanel() {
//		ClassroomList = list;
		this.user = MyData.getUser();
		outerPanel.setOpaque(false);
		innerPanel.setOpaque(false);
//		this.setcDate(date.getText());
		
		//��������ComoboBox
		Vector<String> DurationList = new Vector<String>(); 
		DurationList.add("8:00~9:30");
		DurationList.add("9:40~12:00");
		DurationList.add("14:00~15:30");
		DurationList.add("15:40~18:00");
		DurationList.add("18:30~21:00");
		DurationList.add("21:00~22:00");
		DurationList.add("18:30~22:00");	
		DurationBox = new JComboBox(DurationList);//DurationBox�����ɹ�
		DurationBox.setMaximumRowCount(4);
		DurationBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			//��ȡDurationBoxѡ������������time��
			time = DurationBox.getSelectedItem();
			ClassroomPanel.this.setcDuration(time);
			}
		});
		
//		String duration = time.toString();
//		cDuration = time.toString();
		
		Vector<String> NumberList = new Vector<String>();
		NumberList.add("30������");
		NumberList.add("30~60��");
		NumberList.add("60������");	
		number = "30������";
		NumberBox = new JComboBox(NumberList);//NumberBox�����ɹ�
		NumberBox.setMaximumRowCount(3);
		//ΪNumberBox��������¼����������������ڲ��ࣩ
		NumberBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			//��ȡNumberBoxѡ������������number��
				number = (String)NumberBox.getSelectedItem();
				System.out.println(number);
				//ClassroomPanel.this.setcCapacity(number);
			}			
		});
		
				
		//���Ӽ���
		ButtonListener listener1 = new ButtonListener();
		showRoom.addActionListener(listener1);
		register.addActionListener(listener1);
		

		//��outerPanel��������
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

	//�ڲ�������
	private class ButtonListener implements ActionListener {
		/* 
		 * ���ø��ڲ��������������¼�Դ�������¼� 
		 * ���ڴ����¼�����ģ�黯 
		 */ 
//		EmptyRoomTable roomTable = null;
		public void actionPerformed(ActionEvent e) { 
			String buttonName = e.getActionCommand();
			if(buttonName.equals("��ʾ�ɽ�")) {
				cDate = date.getText();
				record = new RoomRecord(setcCapacity(number), getcDate(),getcDuration());
				if(roomTable != null) {
					roomTable.setVisible(false);
				}
				
				//����������������������������������������
				roomTable = new EmptyRoomTable ( courseHelper.checkRoom(record));
				
				outerPanel.add(roomTable);
				outerPanel.setVisible(false);
				outerPanel.setVisible(true);
			}
			else if(buttonName.equals("���õǼ�")) {
				int row=roomTable.getcTable().getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(roomTable.getcTable(), "��ѡ��һ��");
					return;
				}
				
				String cName = (String)roomTable.getModel().getValueAt(row,0);
				record = new RoomRecord(cName,setcCapacity(number), getcDate(),getcDuration());
				//System.out.println("��������ô�����أ��þ��ᰡ"+cName);
				record.setcRoomNumber(cName);
				record.s ="1111";
				System.out.println(record.getcRoomNumber());
				
				int res = JOptionPane.showConfirmDialog(null, 
						"ȷ��Ҫ���ô˽�����", "ȷ�Ͻ���", JOptionPane.YES_NO_CANCEL_OPTION);
				if(res == JOptionPane.YES_OPTION) {
					
					//������������������������������������������������
					rent =  courseHelper.rentRoom(user, record);
					if(rent) {
						JOptionPane.showMessageDialog(outerPanel, 
								"���ý��ҳɹ�"); 
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
	
	//set��get����
	public void setcDuration(Object time) {
		cDuration = (String) time;
	}
	public String getcDuration() {
		return cDuration;
	}
	
	public int setcCapacity(String num) {
		String Num = num;
		if(Num.equals("30������"))
			cCapacity = 30;
		else if(Num.equals("30~60��"))
			cCapacity = 60;
		else if(Num.equals("60������"))
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
