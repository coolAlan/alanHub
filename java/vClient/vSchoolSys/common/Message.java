/*
 * Created on 2013-8-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vSchoolSys.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author shipeng
 *
 * 用于客户端与服务器端传递消息的实体类
 */
public class Message implements Serializable {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	//private static final long serialVersionUID = 8454558035178168414L;
	/*
	 * mId用来标记操作所属模块
	 * 其中
	 * 1：代表用户管理模块，负责人周滢滢
	 * 2：代表学籍管理系统，负责人刘彤
	 * 3：代表选课系统，负责人彭成伦
	 * 4：代表图书管理系统，负责人石珺
	 * 5：代表商店系统，负责人赵子琦
	 * 6：代表一卡通中心，负责人时鹏
	 * 7：学生在线系统，负责人刘少博
	 * */
	private int mId;// 唯一，说明Message来自那一模块
	private String mName;//操作名称
	private int type;//某一模块内操作类型
	private ArrayList<Object> data;//传送数据
	private int refData;//数据标记，有数据data，填1，否则为0  
	private User sender;//发送者client的用户
	private int refS ; //1代表server，0代表client
	
	public Message(int mId,int type,String mName,ArrayList<Object> data,User sender){
		this.mId= mId;
		this.type=type;
		this.mName= mName;
		if(data.size()!=0){
			refData=1;
			this.data=data;
		}else
			this.data=data;
		this.sender=sender;
		
	}
	public Message(int mId,int type,User sender){
		this.mId= mId;
		this.type=type;
		this.refData=0;
		this.data=null;
		this.sender=sender;
		
	}

	/**
	 * 
	 */
	public Message() {
		this.mId= 0;
		this.type=0;
		this.mName= null;
	    this.refData=0;
		this.data=null;
		this.sender=null;
		this.refS =0;
	}
	/**
	 * @return Returns the data.
	 */
	public ArrayList<Object> getData() {
		return data;
	}
	/**
	 * @param data The data to set.
	 */
	public void setData(ArrayList<Object> data) {
		this.data = data;
	}
	/**
	 * @return Returns the mId.
	 */
	public int getMId() {
		return mId;
	}
	/**
	 * @param id The mId to set.
	 */
	public void setMId(int id) {
		mId = id;
	}
	/**
	 * @return Returns the mName.
	 */
	public String getMName() {
		return mName;
	}
	/**
	 * @param name The mName to set.
	 */
	public void setMName(String name) {
		mName = name;
	}
	/**
	 * @return Returns the sender.
	 */
	public User getSender() {
		return sender;
	}
	/**
	 * @param sender The sender to set.
	 */
	public void setSender(User sender) {
		this.sender = sender;
	}
	/**
	 * @return Returns the type.
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(int type) {
		this.type = type;
	}
}
