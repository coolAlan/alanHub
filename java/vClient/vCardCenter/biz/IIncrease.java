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
	 * 一卡通的充值，发送Message并且刷新结果，通过输入密码实现 代号3
	 */
	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	private Message m;
	public IIncrease(double money,String pwd) {
		System.out.println("IncreaseSrv");
		toServer= MyData.getToServer();
		fromServer=MyData.getFromServer();
		User user = MyData.getUser();
//		此处暂时不修改user属性
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(new ItemList(1, money , new Date(), pwd));
		Message msg = new Message(6,3,"充值",list,user);
		
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
