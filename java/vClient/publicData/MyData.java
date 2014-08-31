package publicData;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import vEshop.view.MainClient.MyRemainListener;
import vSchoolSys.common.User;

/*
 * Created on 2013-8-26
 *
 */

/**
 * @author shipeng
 *
 * �洢������Ϣ
 * 
 */
public class  MyData {
	public static ObjectOutputStream toServer;
	public static ObjectInputStream fromServer;
	public static Socket socket;
	public static User user;
	
	private static Vector vectorListeners=new Vector();

	
	public MyData(){
		//���ڰ��¼�
	}
	/**
	 * @return Returns the fromServer.
	 */
	public static ObjectInputStream getFromServer() {
		return fromServer;
	}
	/**
	 * @param fromServer The fromServer to set.
	 */
	public static void setFromServer(ObjectInputStream fromServer) {
		MyData.fromServer = fromServer;
	}
	/**
	 * @return Returns the socket.
	 */
	public static Socket getSocket() {
		return socket;
	}
	/**
	 * @param socket The socket to set.
	 */
	public static void setSocket(Socket socket) {
		MyData.socket = socket;
	}
	/**
	 * @return Returns the toServer.
	 */
	public static ObjectOutputStream getToServer() {
		return toServer;
	}
	/**
	 * @param toServer The toServer to set.
	 */
	public static void setToServer(ObjectOutputStream toServer) {
		MyData.toServer = toServer;
	}
	/**
	 * @return Returns the user.
	 */
	public static User getUser() {
		return user;
	}
	/**
	 * @param user The user to set.
	 */
	public  void setUser(User user) {
		MyData.user = user;
		processEvent();//����user�ı���¼�
	}
	 public synchronized void addRemainListener(MyRemainListener ml)
	    {
	        vectorListeners.addElement(ml);
	    }
	    
	    public synchronized void removeRemainListener(MyRemainListener ml)
	    {
	        vectorListeners.removeElement(ml);
	    }
	    
	    protected  void activateRemainEvent()
	    {
	        Vector tempVector=null;
	        
	        RemainEvent e=new RemainEvent(this);
	        
	        synchronized(this)
	        {
	            tempVector=(Vector)vectorListeners.clone();
	            
	            for(int i=0;i<tempVector.size();i++)
	            {
	            	MyRemainListener ml=(MyRemainListener)tempVector.elementAt(i);
	                ml.EventActivated(e);
	            }
	        }
	        
	    }
//	  ����һ�����÷������ڴ����¼�
	    public  void processEvent()
	    {
	    	System.out.println("����¼�����");
	        activateRemainEvent();
	    }  
}
