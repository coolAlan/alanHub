package vSelectCourse.biz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import publicData.MyData;
import vSchoolSys.common.Course;
import vSchoolSys.common.CourseRecord;
import vSchoolSys.common.Message;
import vSchoolSys.common.RoomRecord;
import vSchoolSys.common.User;


public class ICourseHelper {

	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	//private Socket socket;
	
	private User user;
	private Message message ;
	
	private boolean role = false; 
	private String[][] strCourse;
	private String[][] strUserCourse;
	private String[][] strUserGrade;
	
	public ICourseHelper() {
		
		this.toServer= MyData.getToServer();
		this.fromServer=MyData.getFromServer();
		this.user = MyData.getUser();	
	}
	
	//������Server���͡���ѯ���пγ̡�����ϢMessage�����ţ�3��1��
	public void getAllCourse() {
		Message msg = new Message(3,1,"��ѯ���пγ�",new ArrayList<Object>(),user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {		
			e.printStackTrace();
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		}
	}
	
	//������Server���͡���ѯ�û���ѡ�γ���Ϣ������ϢMessage�����ţ�3��2��
	public void getUserCourse(String uId) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(user);// data[0]Ϊ�û�	
		
		//������ѯ�˵�user�ഫ��data��
		Message msg = new Message(3,2,"��ѯ������ѡ�γ�",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			//����server��������Message�����ڿα�Table����ʾ
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
	}
	
	//������Server���͡�ѡ�Ρ�����ϢMessage�����ţ�3��3��
	public void addCourse(CourseRecord couRecord,int yxcount) {
		
		//����list�����ڴ���Message��data
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(couRecord);//data[0] Ϊ�γ̼�¼
		data.add(new Integer(yxcount));// data[0]Ϊˢ�µ���ѡ�γ���		
		//����Message
		Message msg = new Message(3,3,"����ѡ�μ�¼",data,user);	
		try {
 			//��������server����Message
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			//����server��������Message��������Ϊ�˲�������������ڴ棬ûɶ��
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {	
			e.printStackTrace();
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		}
	}
	
	//������Server����"�˿�"����ϢMessage������(3,4)
	public void subCourse(CourseRecord couRecord,int yxcount) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(couRecord);//data[0]Ϊ�γ̼�¼
		data.add(new Integer(yxcount));//data[1]ˢ�µ���ѡ������
		//������ѯ�˵�user�ഫ��data��
		Message msg = new Message(3,4,"ɾ��ѡ�μ�¼",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {	
			e.printStackTrace();
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		}
	}
	
	//ͨ��String[][]�������С��γ̡��ı����ʽ��¼
	public String[][] showAllCourse(){
			
		this.getAllCourse();
		ArrayList<Object> courseList = (this.message).getData();
		
		int row = courseList.size();	
		strCourse = new String[row][10];
		int i = 0;
		while(i < row){
			strCourse[i]=((Course)( courseList.get(i) )).CourseToString();	
			i++;
		}
		return strCourse;
	}
	
	//ͨ��String[][]���ظ��û����С��α��ı����ʽ��¼
	public String[][] showUserCourse()
	{	
		this.getUserCourse(user.getUId());
		ArrayList<Object> userCourse = (this.message).getData();
		
		int row = userCourse.size();
		strUserCourse = new String[row][5];
		int i = 0;
		while(i < row)
		{
			strUserCourse[i]=((Course)( userCourse.get(i))).couRecordToString();	
			i++;
		}
		return strUserCourse;
	}
	
	//��ʼ���û���ѡ��״̬
	public void initialState(DefaultTableModel kbDefaultModel) {
		
		this.getUserCourse(user.getUId());
		//�õ�Message��data(��ID����ѡ�Ŀ�)
		ArrayList<Object> userCourse = (this.message).getData();
		
		int size = userCourse.size();
		int row = kbDefaultModel.getRowCount();
		int j = 0;
		for(int i = 0; i < size; i++){
			for(j=0;j<row;j++){
				if(  (kbDefaultModel.getValueAt(j, 0)).equals( ((Course)(userCourse.get(i))).getCouID() )  ){
					kbDefaultModel.setValueAt("��ѡ", j, 9);
				}
			} 
		}
	}

	public Message getMessage() {
		return message;
	}

	//�ж�ѡ��ʱ���Ƿ��ͻ
	public String Selectedable(String uID,String cTime) {

		this.getUserCourse(uID);		
		ArrayList<Object> userCourse = (this.message).getData();
		
		int row = userCourse.size();
		int i = 0;		
		while(i < row)
		{		
			if(cTime.regionMatches(0,((Course)userCourse.get(i)).getCouTime(),0,7)){
				System.out.println("��ͻ�ˣ�����Ϊ�Ҳ�֪��");
				return ((Course)userCourse.get(i)).getCouName();
			}
			i++;		
		}
		return null;
	}

	//���سɼ�����
	public String[][] getGrade() {
		
		this.getUserGrade(user.getUId());
		ArrayList<Object> userGrade = (this.message).getData();
		
		int row = userGrade.size();
		strUserGrade = new String[row][3];
		int i = 0;
		while(i < row)
		{
			strUserGrade[i]=((CourseRecord)( userGrade.get(i))).getSrtCouRecord();	
			i++;
		}
		return strUserGrade;
	}

	private void getUserGrade(String uId) {
		// TODO Auto-generated method stub
		
		//������ѯ�˵�user�ഫ��data��
		Message msg = new Message(3,5,"��ѯ������ѡ�γ̳ɼ�",new ArrayList<Object>(),user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			//����server��������Message�����ڳɼ�Table����ʾ
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		
	}

	//��ѯ�ɽ����
	public ArrayList<Object> checkRoom(RoomRecord record) {
		// TODO Auto-generated method stub
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(record);
		Message msg = new Message(3,6,"��ѯ�ɽ����",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			//����server��������Message�����ڳɼ�Table����ʾ
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		
		return this.message.getData();
	}

	//���ý���
	public boolean rentRoom(User user2, RoomRecord record) {
		
		boolean success = false;
		String s = "�ɹ�";
		System.out.println("sssssssss"+record.getcRoomNumber());
		ArrayList<Object> data = new ArrayList<Object>();
		
		System.out.println("sssssssss"+record.s);
		data.add(record);
		data.add(record.getcRoomNumber());
		Message msg = new Message(3,7,"���ý���",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			//����server��������Message�����ڳɼ�Table����ʾ
			this.message = (Message)fromServer.readObject();
			if (  s.equals( (String)(this.message.getData().get(0))))  {
				success = true;
			}
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		
		return success;
	}
	
}
