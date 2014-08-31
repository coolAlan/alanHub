/*
 * Created on 2013-9-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vLogin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import publicData.MyData;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;

/**
 * @author Alan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IRegister {

	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	private Socket socket;
	
	private Message m ;
	//用于用户注册等相关操作的消息发送
	public IRegister(String id){
		try {
			socket = new Socket(InetAddress.getLocalHost(),9876);
			System.out.println("register1");
			toServer = new ObjectOutputStream(socket.getOutputStream());
			fromServer = new ObjectInputStream(socket.getInputStream());
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<Object> list = new ArrayList<Object>();
		User user= new User(id,null);
		list.add(user);
		Message msg = new Message(1,1,"查询是否重复用户",list,null);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg to regester ");
			this.m = (Message)fromServer.readObject();
			
			User u = new User("","");
			Message mm = new Message(0,0,u);
			toServer.writeObject(mm);
			toServer.flush();
			
			socket.close();
			toServer.close();
			fromServer.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	public IRegister(String id,String pwd,String name,String stdNum,String phoneNum){
		try {
			socket = new Socket(InetAddress.getLocalHost(),9876);
			System.out.println("register2");
			toServer = new ObjectOutputStream(socket.getOutputStream());
			fromServer = new ObjectInputStream(socket.getInputStream());
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<Object> list = new ArrayList<Object>();
		User user= new User(id,pwd,name,stdNum,300,0,phoneNum,0);
		list.add(user);
		Message msg = new Message(1,2,"注册新用户",list,null);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg to register ");
			this.m = (Message)fromServer.readObject();
			
			User u = new User("","");
			Message mm = new Message(0,0,u);
			toServer.writeObject(mm);
			toServer.flush();
			
			socket.close();
			toServer.close();
			fromServer.close();
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
