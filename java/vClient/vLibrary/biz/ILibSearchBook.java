/*
 * Created on 2013-9-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vLibrary.biz;

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
 * ��������ͼ�����Ϣ
 */
public class ILibSearchBook {

	private ObjectOutputStream toServer;
 	private ObjectInputStream fromServer;
	private Socket socket;
	private boolean role = false; 
	private Message m ;
	public  ILibSearchBook(String str,int Stype){
		    String keyword = str;//�����Ĺؼ���
		    int sType = Stype;//���������ͣ�1��������������
			
			toServer= MyData.getToServer();
			fromServer=MyData.getFromServer();
			User user = MyData.getUser();
			
			ArrayList<Object> list = new ArrayList<Object>();
			list.add(keyword);//0��λ�ô�Źؼ���
			list.add(sType);//1��λ�ô����������

			Message msg = new Message(4,1,"ͼ�������ͼ����Ϣ",list,user);
			
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
	 * @return Returns the fromServer.
	 */
	public ObjectInputStream getFromServer() {
		return fromServer;
	}
	/**
	 * @param fromServer The fromServer to set.
	 */
	public void setFromServer(ObjectInputStream fromServer) {
		this.fromServer = fromServer;
	}
	/**
	 * @return Returns the socket.
	 */
	public Socket getSocket() {
		return socket;
	}
	/**
	 * @param socket The socket to set.
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	/**
	 * @return Returns the toServer.
	 */
	public ObjectOutputStream getToServer() {
		return toServer;
	}
	/**
	 * @param toServer The toServer to set.
	 */
	public void setToServer(ObjectOutputStream toServer) {
		this.toServer = toServer;
	}
	
	/**
	 * @return Returns the role.
	 */
	public boolean isRole() {
		System.out.println("role is "+role);
		return role;
	}
	
	/**
	 * @return Returns the m.
	 */
	public Message getMessage() {
		return m;
	}
	

}
