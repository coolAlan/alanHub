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
import vSchoolSys.common.ItemList;
import vSchoolSys.common.Message;
import vSchoolSys.common.ShopCartGoods;
import vSchoolSys.common.User;

/**
 * @author shipeng
 * 负责发送商店模块添加消费记录的消息
 * 
 */
public class IAddSaleRecord {

	 private ObjectOutputStream toServer;
	 	private ObjectInputStream fromServer;
		private Socket socket;
		private boolean role = false; 
		private Message m ;
		public IAddSaleRecord(ItemList itemlist,ArrayList<ShopCartGoods> cartlist) {
			toServer= MyData.getToServer();
			fromServer=MyData.getFromServer();
			User user = MyData.getUser();
			
			ArrayList<Object> list = new ArrayList<Object>();
			list.add(itemlist);//0号放订单信息
			for(int i=0;i<cartlist.size();i++)
				list.add(cartlist.get(i));
			Message msg = new Message(5,3,"商店提交订单信息",list,user);
			
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
