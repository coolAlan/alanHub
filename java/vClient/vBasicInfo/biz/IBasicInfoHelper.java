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
	
	//用于向Server发送“查询用户基本信息”的消息Message，代号（2，1）
	public  BasicInfo getBasicInfo() {
				
		Message msg = new Message(2,1,"查询基本信息",new ArrayList<Object>(),user);
		
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

	
	//用于向Server发送“查询用户家庭成员”的消息Message，代号（2，2）
	public ArrayList<Object> getFamilyMember() {

		Message msg = new Message(2,2,"查询家庭成员",new ArrayList<Object>(),user);
		
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
	
	//用于向Server发送“查询教育信息”的消息Message，代号（2，3）
	public ArrayList<Object> getEducationInfo() {

		Message msg = new Message(2,3,"查询教育信息",new ArrayList<Object>(),user);
			
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
	
	//用于向Server发送“查询奖惩信息”的消息Message，代号（2，4）
	public ArrayList<Object> getRewOrPunInfo() {

		Message msg = new Message(2,4,"查询奖惩信息",new ArrayList<Object>(),user);
			
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

	//用于向Server发送“修改基本信息”的消息Message，代号（2，5）
	public void changeBasicInfo(int age, String address) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(age);
		data.add((Object)address);
		Message msg = new Message(2,5,"修改基本信息",data,user);
		
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
	
	//用于向Server发送“修改家庭成员信息”的消息Message，代号（2，6）
	public void changeFamilyInfo( FamiMember fMember ) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add((Object)fMember);
	
		Message msg = new Message(2,6,"修改家庭成员信息",data,user);
		
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

	//用于向Server发送“添加教育记录信息”的消息Message，代号（2，7）
	public void addEducationInfo(EduExperience eduExperience ) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(eduExperience); 
		
		Message msg = new Message(2,7,"添加教育信息",data,user);
		
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

	//用于向Server发送“添加家庭成员”的消息Message，代号（2，8）
	public void addFamilyInfo(FamiMember fMember) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(fMember); 
		
		Message msg = new Message(2,8,"添加家庭成员",data,user);
		
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

	//用于向Server发送“删除教育信息”的消息Message，代号（2，9）
	public void deleEducationInfo(String school) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(school); 
		Message msg = new Message(2,9,"删除教育信息",data,user);
		
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
