/*
 * Created on 2013-8-27
 *
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
 * 
 */
public class IDecrease {

	/**
	 * 一卡通的缴费，发送Message并且刷新结果 代号4
	 */
	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	private Message m;
	public IDecrease(double cost,String md) {
		toServer= MyData.getToServer();
		fromServer=MyData.getFromServer();
		User user = MyData.getUser();
		//此处暂时不修改user属性
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(new ItemList(1, cost*-1 , new Date(),md));
		Message msg = new Message(6,4,"缴费",list,user);
		
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
	 * @return Returns the m.
	 */
	public Message getMessage() {
		return m;
	}
	
}
