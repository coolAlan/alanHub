/*
 * Created on 2013-8-31
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package publicData;

import java.util.ArrayList;
import java.util.Vector;

import vSchoolSys.biz.IServerSrvImpl.MyListener;



//import publicData.MyListener;

/**
 * @author shipeng
 *
 *  用于存储已登录用户的信息
 */
public class SrvData {

	public static int clientNum;//在线用户数目
	public static ArrayList<ClientIndividual> clients;
	private static Vector vectorListeners=new Vector();
	public SrvData() {
		clientNum=0;
		clients = new ArrayList<ClientIndividual>();
	}
	public SrvData(int i){
		
	}
	public   boolean addClient(String ClientID , String ip){
		clientNum++;
		clients.add(new ClientIndividual(ClientID,ip));
		processEvent();
		return true;
		
	}
	public  boolean exitClient(String ClientID){
		
		int i=0;
		for(;i<clients.size();i++){
			System.out.println("sssss "+((ClientIndividual)(clients.get(i))).getClientName());
			if(((ClientIndividual)(clients.get(i))).getClientName().equals(ClientID)){
			clientNum--;
			break;
			}
		}
		clients.remove(i);
		processEvent();
		return true;
	}
	public  boolean  findOnline(String ClientID){
		int i=0;
		
		String login = ClientID;
		for(;i<clients.size();i++){
			
			String str = (((ClientIndividual)(clients.get(i))).getClientName());
			if(login.equals(str))
			{
			  
			   return true;
			}
		}
		
		return false;
		
	}
	

	/**
	 * @return Returns the clientNum.
	 */
	public int getClientNum() {
		return clientNum;
	}
	/**
	 * @param clientNum The clientNum to set.
	 */
	public void setClientNum(int clientNum) {
		SrvData.clientNum = clientNum;
	}
	/**
	 * @return Returns the clients.
	 */
	public ArrayList<ClientIndividual> getClients() {
		return clients;
	}
	/**
	 * @param clients The clients to set.
	 */
	public void setClients(ArrayList<ClientIndividual> clients) {
		SrvData.clients = clients;
	}
	public synchronized void addMyListener(MyListener ml)
    {
        vectorListeners.addElement(ml);
    }
    
    public synchronized void removeMyListener(MyListener ml)
    {
        vectorListeners.removeElement(ml);
    }
    
    protected  void activateMyEvent()
    {
        Vector tempVector=null;
        
        MyEvent e=new MyEvent(this);
        
        synchronized(this)
        {
            tempVector=(Vector)vectorListeners.clone();
            
            for(int i=0;i<tempVector.size();i++)
            {
            	//System.out.println();
                MyListener ml=(MyListener)tempVector.elementAt(i);
                ml.EventActivated(e);
                
            }
        }
        
    }
//	  定义一个公用方法用于触发事件
	 public  void processEvent()
	 {
	    	System.out.println("事件产生");
	        activateMyEvent();
	 }   
	   
		
}
