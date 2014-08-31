/*
 * Created on 2013-8-26
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vCardCenter.biz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import publicData.MyData;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;


/**
 * @author shipeng
 * 用于发送Message，并且获得用户消费Record信息。代号2
 */
public class IRecordSrv {
	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	private Message m;
	public IRecordSrv() {
		System.out.println("recordSrv");
		toServer= MyData.getToServer();
		fromServer=MyData.getFromServer();
		User user = MyData.getUser();
		Message msg = new Message(6,2,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			this.m = (Message)fromServer.readObject();
			System.out.println("msg out");
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
	/**
	 * @param m The m to set.
	 */
	public void setMessage(Message m) {
		this.m = m;
	}
}
