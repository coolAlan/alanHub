/*
 * Created on 2013-9-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vServerModelSrv.biz;

import java.util.ArrayList;

import vSchoolSys.common.CourseRecord;
import vSchoolSys.common.ItemList;
import vSchoolSys.common.Message;
import vSchoolSys.common.ShopCartGoods;
import vSchoolSys.common.User;
import vSchoolSys.dao.dataBaseHelper;
import vSchoolSys.dao.eShopDao;

/**
 * @author Alan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class veShopSrv {

	private Message msg;
	
	public veShopSrv(int type,Message msg) {
		
		User user;
		String userID;
		
		//���ݿ����
		
		eShopDao dataBase =  new eShopDao();
		
		ArrayList<Object> arrayList;
		ArrayList<Object> alist;	
		
		//"ѡ��ģ��"
		CourseRecord courseRecord;//		
		int yxcount;
		
		boolean success;
		
		switch(type){
		case 1://���������Ʒ��Ϣ
			user = msg.getSender();
			dataBase.getAllGoods();
			System.out.println("veShop 1");
			arrayList = dataBase.getArrayList();
			this.msg =new Message(5,1,"�̵�ģ��ȫ����Ʒ",arrayList,user);//���ؿͻ��˵�message
			break;
		case 2:
			user = msg.getSender();
			arrayList = msg.getData();
			String find = (String) arrayList.get(0);
			dataBase.SearchGood(find);
			System.out.println("veShop 2");
			arrayList = dataBase.getArrayList();
			this.msg =new Message(5,2,"�̵�ģ���ѯ",arrayList,user);//���ؿͻ��˵�message
			break;
		case 3:
			
			arrayList = msg.getData();
			ItemList itemList =(ItemList) arrayList.get(0);
			for(int i=1;i<arrayList.size();i++){
			    dataBase.Updatedown(((ShopCartGoods)arrayList.get(i)).getgName(),((ShopCartGoods)arrayList.get(i)).getNumber());
			    ((ShopCartGoods)arrayList.get(i)).getgName();
			}
			dataBase.addItemList(itemList);
			System.out.println("veShop 2");
			arrayList = new ArrayList<Object>();
			arrayList.add(new Integer(1));
			user = dataBase.getUser();
			this.msg =new Message(5,3,"�̵깺��",arrayList,user);//���ؿͻ��˵�message
			break;
		case 4:
			
			break;
		}
		dataBase.close();
	
	}
	/**
	 * @return Returns the msg.
	 */
	public Message getMsg() {
		return msg;
	}
	/**
	 * @param msg The msg to set.
	 */
	public void setMsg(Message msg) {
		this.msg = msg;
	}
	

}
