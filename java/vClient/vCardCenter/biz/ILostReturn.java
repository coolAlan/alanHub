/*
 * Created on 2013-8-27
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vCardCenter.biz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import publicData.MyData;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;

/**
 * @author shipeng
 * һ��ͨ��ʧ��ͨ�ŷ��ͺͽ���Message
 */
public class ILostReturn {

	/**
	 * ����һ��ͨ�Ĺ�ʧ���һأ�����5
	 */
	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	private Message m;
	public ILostReturn() {
		toServer= MyData.getToServer();
		fromServer=MyData.getFromServer();
		User user = MyData.getUser();
		
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(user.getLost());
		Message msg = new Message(6,5,"��ʧ���һ�",list,user);
		
		try {
			
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
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
