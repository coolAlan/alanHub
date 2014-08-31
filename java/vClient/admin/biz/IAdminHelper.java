/*
 * Created on 2013-9-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package admin.biz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import publicData.MyData;
import vSchoolSys.common.Book;
import vSchoolSys.common.Course;
import vSchoolSys.common.DataGoods;
import vSchoolSys.common.Message;
import vSchoolSys.common.RewOrPuniInfo;
import vSchoolSys.common.User;

/**
 * @author Alan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IAdminHelper {
	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	
	private User user;
	private Message message ;
	
	private boolean role = false; 
	
	public IAdminHelper() {
		
		this.toServer= MyData.getToServer();
		this.fromServer=MyData.getFromServer();
		this.user = MyData.getUser();
	}
	
	//������Server���͡���ѯ�γ̻�����Ϣ������ϢMessage�����ţ�8��1��
	public  ArrayList<Object> getCourseInfo() {
				
		Message msg = new Message(8,1,"��ѯ�γ���Ϣ",new ArrayList<Object>(),user);
		
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
		return data;
	}

	
	//������Server����"���ӿγ̡�����ϢMessage�����ţ�8��2��
	public ArrayList<Object> addCourse(Course course) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(course);
		Message msg = new Message(8,2,"���ӿγ�",list,user);
		
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
	
	//������Server���͡�ɾ���γ���Ϣ������ϢMessage�����ţ�8��3��
	public boolean delCourse(String cID) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(cID);
		Message msg = new Message(8,3,"ɾ���γ���Ϣ",list,user);
			
		try {
			toServer.writeObject(msg);
			toServer.flush();
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		return true;
	}
	
	//������Server���͡���ѯ��Ʒ��Ϣ������ϢMessage�����ţ�8��4��
	public ArrayList<Object> getGoodsInfo() {

		Message msg = new Message(8,4,"��ѯ��Ʒ��Ϣ",new ArrayList<Object>(),user);
			
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

	//������Server���͡��޸���Ʒ��Ϣ������ϢMessage�����ţ�8��5��
	public void changeGoodInfo(DataGoods good) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(good);
		
		Message msg = new Message(8,5,"�޸���Ʒ��Ϣ",data,user);
		
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
	
	//������Server���͡�������Ʒ��Ϣ������ϢMessage�����ţ�8��6��
	public void addGood(DataGoods good) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(good);
		Message msg = new Message(8,6,"������Ʒ��Ϣ",data,user);
		
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

	//������Server���͡�ɾ����Ʒ��Ϣ������ϢMessage�����ţ�8��7��
	public void delGood(String gID) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(gID);
		
		Message msg = new Message(8,7,"ɾ����Ʒ��Ϣ",data,user);
		
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
	
//	������Server���͡���ѯ������Ϣ������ϢMessage�����ţ�8��8��
	public ArrayList<Object> getReward() {

		ArrayList<Object> data = new ArrayList<Object>();
		
		
		Message msg = new Message(8,8,"��ѯ������Ϣ",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		return message.getData();
		
	}
	
	
	

	//������Server���͡�ɾ��������Ϣ������ϢMessage�����ţ�8��9��
	public void delReward(int order) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(order);
		Message msg = new Message(8,9,"ɾ��������Ϣ",data,user);
		
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
//	������Server���͡���ӽ�����Ϣ������ϢMessage�����ţ�8��10��
	public void addReward(RewOrPuniInfo info) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(info); 
		
		Message msg = new Message(8,10,"��ӽ�����Ϣ",data,user);
		
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
//	������Server���͡���ѯͼ����Ϣ������ϢMessage�����ţ�8��11��
	public ArrayList<Object> getBook() {

		ArrayList<Object> data = new ArrayList<Object>();
		
		
		Message msg = new Message(8,11,"��ѯͼ����Ϣ",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		return message.getData();
		
	}
//	������Server���͡�ɾ��ͼ����Ϣ������ϢMessage�����ţ�8��12��
	public void delBook(String bid) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(bid);
		Message msg = new Message(8,12,"ɾ��ͼ����Ϣ",data,user);
		
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
//	������Server���͡����ͼ����Ϣ������ϢMessage�����ţ�8��13��
	public void addBook(Book book) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(book); 
		
		Message msg = new Message(8,13,"���ͼ����Ϣ",data,user);
		
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
