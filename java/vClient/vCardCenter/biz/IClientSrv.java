/*
 * Created on 2013-8-21
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vCardCenter.biz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import publicData.MyData;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;

/**
 * @author shipeng
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IClientSrv extends JFrame{
	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	private Socket socket;
	private boolean role = false; 
	private Message m ;
	public IClientSrv(String id,String pwd){
		try{
			
			socket = new Socket(InetAddress.getLocalHost(),9876);
			
			
			System.out.println("login");
			toServer = new ObjectOutputStream(socket.getOutputStream());
			fromServer = new ObjectInputStream(socket.getInputStream());
	
			
			User user = new User(id,pwd);//生成发送者
			Message msg = new Message(6,1,user);
			toServer.writeObject(msg);
			toServer.flush();
			//fromServer = new ObjectInputStream(socket.getInputStream());
			System.out.println("read ok");
			m = (Message) fromServer.readObject();
			while(m==null){
				m = (Message) fromServer.readObject();
				//System.out.println("2111");
			}
			System.out.println(m.getMName());
			if(m.getData().size()!=0){
				role = true;
				MyData.socket = socket;
				MyData.toServer = toServer;
				MyData.fromServer = fromServer;
			}
			else{
				role = false;
				MyData.socket= null;
				MyData.toServer = null;
				MyData.fromServer = null;
			}
		}
		catch(IOException ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"网络连接错误","error",JOptionPane.ERROR);
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
