/*
 * Created on 2013-9-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vEshop.biz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import publicData.MyData;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;

/**
 * @author shipeng
 *
 * 发送搜索商品的消息
 */
public class ISearchGood {

	private ObjectOutputStream toServer;
 	private ObjectInputStream fromServer;
	private Socket socket;
	private boolean role = false; 
	private Message m ;
	public ISearchGood(String find){
		toServer= MyData.getToServer();
		fromServer=MyData.getFromServer();
		User user = MyData.getUser();
		
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(new String(find));
		Message msg = new Message(5,2,"查询商品",list,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg to eShop ");
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
