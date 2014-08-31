/*
 * Created on 2013-9-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package admin.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import publicData.MyData;
import vSchoolSys.common.Book;
import vSchoolSys.common.Course;
import vSchoolSys.common.DataGoods;
import vSchoolSys.common.Message;
import vSchoolSys.common.RewOrPuniInfo;
import admin.biz.IAdminHelper;



/**
 * @author Alan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class adminUI extends JFrame{
	private IAdminHelper adminHelper = new IAdminHelper();
	private int rowNum=0;//�γ���Ϣ������
	//private int rowGoods = 0;//��Ʒ��Ϣ������
	public adminUI() {
		JTabbedPane jtp=new JTabbedPane();
		this.add(jtp,BorderLayout.CENTER);
		//�γ�����\ɾ��
		
		JPanel course = new JPanel();
		course.setLayout(new BorderLayout());
		ArrayList<Object> courseArray =adminHelper.getCourseInfo();
		final JTable jtb_course = new JTable(){//MVC���ģʽ�����ݴ���Model��
			public boolean isCellEditable(int row, int column)//��ÿһ����ɱ༭
			{ 
				return false;
			};
		};
		final DefaultTableModel	jTableModel1 = new DefaultTableModel(null,new String[] { "�γ̱��", "�γ�����","�ον�ʦ","�Ͽ�ʱ��","��ʱ","����" });
		jtb_course.setModel(jTableModel1);
		//model�ĳ�ʼ��������ӵĿγ̵���ѡ����Ϊ��##
		
		for(int i=0;i<courseArray.size();i++){
			Course c = ((Course)courseArray.get(i));
			String couID = c.getCouID(); 
			String couName = c.getCouName();
			String couTeacher = c.getCouTeacher();
			String couTime = c.getCouTime();
			int couHour = c.getCouHour();
			int couCount = c.getCouCount();
			jTableModel1.addRow(new Object[]{couID,couName,couTeacher,couTime,couHour,couCount});
		}
		rowNum = courseArray.size();
		JButton bt_courseDel=new JButton("ɾ��");
		bt_courseDel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				int row=jtb_course.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(null, "��ѡ��һ��");
					return;
				}
				String cName=(String)jTableModel1.getValueAt(row, 1);
				int value=JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ�� "+cName+" ��","��ʾ",JOptionPane.OK_CANCEL_OPTION);
			    if (value==JOptionPane.OK_OPTION) {
				String cID=(String)jTableModel1.getValueAt(row, 0);
				System.out.println(cID);
				adminHelper.delCourse(cID);
				jTableModel1.removeRow(row);
				rowNum--;
			    }
				
			}
			
		});
		JButton bt_courseAdd=new JButton("����");
		bt_courseAdd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				//ִ�в����������������
				try {
					CourseInsert insert= new CourseInsert(jTableModel1,rowNum);
					if(insert.isSuccess()){
					   rowNum++;
					   
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		JPanel jp_btn = new JPanel();
		course.add(new JScrollPane(jtb_course),BorderLayout.NORTH);
		jp_btn.add(bt_courseAdd);
		jp_btn.add(bt_courseDel);
		course.add(jp_btn,BorderLayout.CENTER);
		jtp.add("�γ̹���",course);
		//2********************************************************************************
		//��Ʒ����ɾ�����޸�
		
		JPanel shop = new JPanel();
		shop.setLayout(new BorderLayout());
		final JTable jtb_shop = new JTable(){//MVC���ģʽ�����ݴ���Model��
			public boolean isCellEditable(int row, int column)//��ÿһ����ɱ༭
			{ 
				return false;
			};
		};
		ArrayList<Object> goodsArray =adminHelper.getGoodsInfo();
		final DefaultTableModel	jTableModel2 = new DefaultTableModel(null,new String[] { "��Ʒ���", "��Ʒ����","��Ʒ�۸�","����","����"});
		//model�ĳ�ʼ��������ӵ���Ʒ����������Ϊ��##Ĭ�ϼ۸�����
		//rowGoods = goodsArray.size();
		for(int i=0;i<goodsArray.size();i++){
			 DataGoods good =(DataGoods)goodsArray.get(i);
			 String tbID = good.getGID();//��ƷID
			 String tbName = good.getgName();//��Ʒ����
			 double tbPrice = good.getsPrice();//��Ʒ����
			 int tbNumber = good.getNumber();//��Ʒ����
			 int tbType = good.getType();//��Ʒ����
			 String type = null;
			 switch(tbType){
			 case 1:
			 	type = "ˮ��";
			 	break;
			 case 2:
			 	type = "ͼ������";
			 	break;
			 case 3:
			 	type = "���˻���";
			 	break;
			 case 4:
			 	type = "��Ʒ��Ʒ";
			 	break;
			 case 5:
			 	type ="����Ʒ";
			 	break;
			 case 6:
			 	type ="��ʳС��";
			 	break;
			 }
			 jTableModel2.addRow(new Object[]{tbID,tbName,tbPrice,tbNumber,type});
			
		}
		jtb_shop.setModel(jTableModel2);
		JButton bt_goodDel=new JButton("ɾ����Ʒ");
		bt_goodDel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				int row =jtb_shop.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(null, "��ѡ��һ��");
					return;
				}
				String gName=(String)jTableModel2.getValueAt(row, 1);
				int value=JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ�� "+gName+" ��","��ʾ",JOptionPane.OK_CANCEL_OPTION);
			    if (value==JOptionPane.OK_OPTION) {
//			    	ִ��ɾ������
				String gID=(String)jTableModel2.getValueAt(row, 0);
				adminHelper.delGood(gID);
				jTableModel2.removeRow(row);
				//rowGoods--;
			    }
				
				
			
				
			}
			
		});
		JButton bt_goodAdd=new JButton("������Ʒ");
		bt_goodAdd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				try {
					GoodInsert insert= new GoodInsert(jTableModel2);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		JButton bt_goodModify = new JButton("�޸���Ʒ��Ϣ");
		bt_goodModify.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				int rowNo=jtb_shop.getSelectedRow();
				if(rowNo==-1){
					JOptionPane.showMessageDialog(null, "��ѡ��һ��");
					return;
				}
				String gID=(String)jTableModel2.getValueAt(rowNo,0);
				
				try {
					//�������ڣ������޸���Ʒ�������ͼ۸�
					GoodModify modify = new GoodModify(jTableModel2,rowNo);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		shop.add(new JScrollPane(jtb_shop),BorderLayout.NORTH);
		JPanel jp_sp = new JPanel();
		jp_sp.add(bt_goodAdd);
		jp_sp.add(bt_goodDel);
		jp_sp.add(bt_goodModify);
		shop.add(jp_sp,BorderLayout.CENTER);
		jtp.add("��Ʒ����",shop);
		
		//��������\ɾ��
		
		JPanel reward = new JPanel();
		reward.setLayout(new BorderLayout());
		ArrayList<Object> rewardList = adminHelper.getReward();
		final JTable jtb_rewords = new JTable(){//MVC���ģʽ�����ݴ���Model��
			public boolean isCellEditable(int row, int column)//��ÿһ����ɱ༭
			{ 
				return false;
			};
		};
		final DefaultTableModel	jTableModel3 = new DefaultTableModel(null,new String[] {"���", "һ��ͨ��", "��Ŀ����","����","ʱ��","ԭ��","�ص�" });
		//model�ĳ�ʼ��������ӵĿγ̵���ѡ����Ϊ��##
		for(int i=0;i<rewardList.size();i++){
			RewOrPuniInfo info = (RewOrPuniInfo)rewardList.get(i);
			int order = info.getOrder();
			String id = info.getUID();
			String type = info.getURewOrPuni();
			String name = info.getUROrPName();
			String date = info.getUROrPTime();
			String reason = info.getUReason();
			String place = info.getUPlace();
			jTableModel3.addRow(new Object[]{order,id,type,name,date,reason,place});
		}
		jtb_rewords.setModel(jTableModel3);
		JButton bt_rewardDel=new JButton("ɾ��");
		bt_rewardDel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				int row=jtb_rewords.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(null, "��ѡ��һ��");
					return;
				}
				int od = ((Integer)jTableModel3.getValueAt(row, 0)).intValue();
				//ִ��ɾ������

				int value=JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ�� ���"+od+" ��","��ʾ",JOptionPane.OK_CANCEL_OPTION);
			    if (value==JOptionPane.OK_OPTION) {
//			    	ִ��ɾ������
				adminHelper.delReward(od);
				jTableModel3.removeRow(row);
				//rowGoods--;
			    }
			
				
			}
			
		});
		JButton bt_rewardAdd=new JButton("����");
		bt_rewardAdd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				try {
					//ִ�в����������������
					RewardInsert insert = new RewardInsert(jTableModel3);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		reward.add(new JScrollPane(jtb_rewords),BorderLayout.NORTH);
		JPanel jp_rew = new JPanel();
		jp_rew.add(bt_rewardAdd);
		jp_rew.add(bt_rewardDel);
		reward.add(jp_rew,BorderLayout.CENTER);
		jtp.add("���͹���",reward);
		//*********************************
		//ͼ�����
		
		JPanel book = new JPanel();
		book.setLayout(new BorderLayout());
		ArrayList<Object> bookList = adminHelper.getBook();
		final JTable jtb_books = new JTable(){//MVC���ģʽ�����ݴ���Model��
			public boolean isCellEditable(int row, int column)//��ÿһ����ɱ༭
			{ 
				return false;
			};
		};
		final DefaultTableModel	jTableModel4 = new DefaultTableModel(null,new String[] {"�����", "����", "����","����"});
		//model�ĳ�ʼ��������ӵĿγ̵���ѡ����Ϊ��##
		for(int i=0;i<bookList.size();i++){
			Book b = (Book)bookList.get(i);
			
			String id = b.getbID();
			String name =b.getbName();
			int num = b.getbSum();
			String author = b.getbAuthor();
			
			jTableModel4.addRow(new Object[]{id,name,num,author});
		}
		jtb_books.setModel(jTableModel4);
		JButton bt_BookDel=new JButton("ɾ��");
		bt_BookDel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				int row=jtb_books.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(null, "��ѡ��һ��");
					return;
				}
				String id = (String)jTableModel4.getValueAt(row, 0);
				//ִ��ɾ������
				String bName = (String)jTableModel4.getValueAt(row, 1);
				int value=JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ�� ͼ��"+bName+" ��","��ʾ",JOptionPane.OK_CANCEL_OPTION);
			    if (value==JOptionPane.OK_OPTION) {
//			    	ִ��ɾ������
				adminHelper.delBook(id);
				jTableModel4.removeRow(row);
				//rowGoods--;
			    }
			
				
			}
			
		});
		JButton bt_BookAdd=new JButton("����");
		bt_BookAdd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				try {
					//ִ�в����������������
					BookInsert insert = new BookInsert(jTableModel4);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		book.add(new JScrollPane(jtb_books),BorderLayout.NORTH);
		JPanel jp_book = new JPanel();
		jp_book.add(bt_BookAdd);
		jp_book.add(bt_BookDel);
		book.add(jp_book,BorderLayout.CENTER);
		jtp.add("ͼ�����",book);
		
		
		pack();
		this.addWindowListener(new WindowAdapter()  {    //�رմ����¼�
			public void windowClosing( WindowEvent e )   
			{   
				try {
					//ֻ�е�MyData.socket���ӳɹ�ʱ�����û���¼
					if(MyData.socket!=null){
					ObjectOutputStream toServer = MyData.toServer;
					ObjectInputStream fromServer = MyData.fromServer;
					Message msg = new Message(0,0,MyData.user);
					toServer.writeObject(msg);
					toServer.flush();
					
					MyData.socket.close();
					MyData.toServer.close();
					MyData.fromServer.close();
					}
				} catch (IOException e1) {  
					 
					e1.printStackTrace();    
				}        
				System.exit(0);  
				
			}     
		});
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public static void main(String args[]){
		new adminUI();
	}

}
