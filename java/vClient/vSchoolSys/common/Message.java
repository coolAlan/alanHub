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
 * ���ڿͻ�����������˴�����Ϣ��ʵ����
 */
public class Message implements Serializable {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	//private static final long serialVersionUID = 8454558035178168414L;
	/*
	 * mId������ǲ�������ģ��
	 * ����
	 * 1�������û�����ģ�飬������������
	 * 2������ѧ������ϵͳ����������ͮ
	 * 3������ѡ��ϵͳ�������������
	 * 4������ͼ�����ϵͳ��������ʯ�B
	 * 5�������̵�ϵͳ��������������
	 * 6������һ��ͨ���ģ�������ʱ��
	 * 7��ѧ������ϵͳ�����������ٲ�
	 * */
	private int mId;// Ψһ��˵��Message������һģ��
	private String mName;//��������
	private int type;//ĳһģ���ڲ�������
	private ArrayList<Object> data;//��������
	private int refData;//���ݱ�ǣ�������data����1������Ϊ0  
	private User sender;//������client���û�
	private int refS ; //1����server��0����client
	
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
