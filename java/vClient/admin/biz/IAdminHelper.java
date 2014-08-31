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
	
	//用于向Server发送“查询课程基本信息”的消息Message，代号（8，1）
	public  ArrayList<Object> getCourseInfo() {
				
		Message msg = new Message(8,1,"查询课程信息",new ArrayList<Object>(),user);
		
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

	
	//用于向Server发送"增加课程”的消息Message，代号（8，2）
	public ArrayList<Object> addCourse(Course course) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(course);
		Message msg = new Message(8,2,"增加课程",list,user);
		
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
	
	//用于向Server发送“删除课程信息”的消息Message，代号（8，3）
	public boolean delCourse(String cID) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(cID);
		Message msg = new Message(8,3,"删除课程信息",list,user);
			
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
	
	//用于向Server发送“查询商品信息”的消息Message，代号（8，4）
	public ArrayList<Object> getGoodsInfo() {

		Message msg = new Message(8,4,"查询商品信息",new ArrayList<Object>(),user);
			
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

	//用于向Server发送“修改商品信息”的消息Message，代号（8，5）
	public void changeGoodInfo(DataGoods good) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(good);
		
		Message msg = new Message(8,5,"修改商品信息",data,user);
		
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
	
	//用于向Server发送“增加商品信息”的消息Message，代号（8，6）
	public void addGood(DataGoods good) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(good);
		Message msg = new Message(8,6,"增加商品信息",data,user);
		
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

	//用于向Server发送“删除商品信息”的消息Message，代号（8，7）
	public void delGood(String gID) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(gID);
		
		Message msg = new Message(8,7,"删除商品信息",data,user);
		
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
	
//	用于向Server发送“查询奖惩信息”的消息Message，代号（8，8）
	public ArrayList<Object> getReward() {

		ArrayList<Object> data = new ArrayList<Object>();
		
		
		Message msg = new Message(8,8,"查询奖惩信息",data,user);
		
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
	
	
	

	//用于向Server发送“删除奖惩信息”的消息Message，代号（8，9）
	public void delReward(int order) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(order);
		Message msg = new Message(8,9,"删除奖惩信息",data,user);
		
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
//	用于向Server发送“添加奖惩信息”的消息Message，代号（8，10）
	public void addReward(RewOrPuniInfo info) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(info); 
		
		Message msg = new Message(8,10,"添加奖惩信息",data,user);
		
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
//	用于向Server发送“查询图书信息”的消息Message，代号（8，11）
	public ArrayList<Object> getBook() {

		ArrayList<Object> data = new ArrayList<Object>();
		
		
		Message msg = new Message(8,11,"查询图书信息",data,user);
		
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
//	用于向Server发送“删除图书信息”的消息Message，代号（8，12）
	public void delBook(String bid) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(bid);
		Message msg = new Message(8,12,"删除图书信息",data,user);
		
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
//	用于向Server发送“添加图书信息”的消息Message，代号（8，13）
	public void addBook(Book book) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(book); 
		
		Message msg = new Message(8,13,"添加图书信息",data,user);
		
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
