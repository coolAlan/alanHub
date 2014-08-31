package vBasicInfo.biz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import publicData.MyData;
import vSchoolSys.common.BasicInfo;
import vSchoolSys.common.Course;
import vSchoolSys.common.CourseRecord;
import vSchoolSys.common.EduExperience;
import vSchoolSys.common.FamiMember;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;

public class IBasicInfoHelper {

	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	
	private User user;
	private Message message ;
	
	private boolean role = false; 
	
	public IBasicInfoHelper() {
		
		this.toServer= MyData.getToServer();
		this.fromServer=MyData.getFromServer();
		this.user = MyData.getUser();
	}
	
	//������Server���͡���ѯ�û�������Ϣ������ϢMessage�����ţ�2��1��
	public  BasicInfo getBasicInfo() {
				
		Message msg = new Message(2,1,"��ѯ������Ϣ",new ArrayList<Object>(),user);
		
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
		ArrayList<Object> data = this.message.getData();
		if(data.size()==0)
			return null;
		else
		return (BasicInfo)(data.get(0));
	}

	
	//������Server���͡���ѯ�û���ͥ��Ա������ϢMessage�����ţ�2��2��
	public ArrayList<Object> getFamilyMember() {

		Message msg = new Message(2,2,"��ѯ��ͥ��Ա",new ArrayList<Object>(),user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		return this.message.getData();
	}
	
	//������Server���͡���ѯ������Ϣ������ϢMessage�����ţ�2��3��
	public ArrayList<Object> getEducationInfo() {

		Message msg = new Message(2,3,"��ѯ������Ϣ",new ArrayList<Object>(),user);
			
		try {
			toServer.writeObject(msg);
			toServer.flush();
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		return this.message.getData();
	}
	
	//������Server���͡���ѯ������Ϣ������ϢMessage�����ţ�2��4��
	public ArrayList<Object> getRewOrPunInfo() {

		Message msg = new Message(2,4,"��ѯ������Ϣ",new ArrayList<Object>(),user);
			
		try {
			toServer.writeObject(msg);
			toServer.flush();
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		return this.message.getData();
	}

	//������Server���͡��޸Ļ�����Ϣ������ϢMessage�����ţ�2��5��
	public void changeBasicInfo(int age, String address) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(age);
		data.add((Object)address);
		Message msg = new Message(2,5,"�޸Ļ�����Ϣ",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		
	}
	
	//������Server���͡��޸ļ�ͥ��Ա��Ϣ������ϢMessage�����ţ�2��6��
	public void changeFamilyInfo( FamiMember fMember ) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add((Object)fMember);
	
		Message msg = new Message(2,6,"�޸ļ�ͥ��Ա��Ϣ",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		
	}

	//������Server���͡���ӽ�����¼��Ϣ������ϢMessage�����ţ�2��7��
	public void addEducationInfo(EduExperience eduExperience ) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(eduExperience); 
		
		Message msg = new Message(2,7,"��ӽ�����Ϣ",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		
	}

	//������Server���͡���Ӽ�ͥ��Ա������ϢMessage�����ţ�2��8��
	public void addFamilyInfo(FamiMember fMember) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(fMember); 
		
		Message msg = new Message(2,8,"��Ӽ�ͥ��Ա",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		
	}

	//������Server���͡�ɾ��������Ϣ������ϢMessage�����ţ�2��9��
	public void deleEducationInfo(String school) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(school); 
		Message msg = new Message(2,9,"ɾ��������Ϣ",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
	}
	
	public Message getMessage() {
		return message;
	}

}
