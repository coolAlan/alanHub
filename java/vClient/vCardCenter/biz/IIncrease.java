/*
 * Created on 2013-8-27
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vCardCenter.biz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import publicData.MyData;

import vSchoolSys.common.ItemList;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;

/**
 * @author shipeng
 *
 */
public class IIncrease {

	/**
	 * һ��ͨ�ĳ�ֵ������Message����ˢ�½����ͨ����������ʵ�� ����3
	 */
	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	private Message m;
	public IIncrease(double money,String pwd) {
		System.out.println("IncreaseSrv");
		toServer= MyData.getToServer();
		fromServer=MyData.getFromServer();
		User user = MyData.getUser();
//		�˴���ʱ���޸�user����
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(new ItemList(1, money , new Date(), pwd));
		Message msg = new Message(6,3,"��ֵ",list,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			//System.out.println("msg from server ");
			this.m = (Message)fromServer.readObject();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		 

	}

	/**
	 * @return Returns the message.
	 */
	public Message getMessage() {
		return m;
	}
	
}
