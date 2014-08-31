/*
 * Created on 2013-8-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vServerModelSrv.biz;

import java.util.ArrayList;

import publicData.SrvData;
import vSchoolSys.common.ItemList;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;
import vSchoolSys.dao.CardDao;

/**
 * @author shipeng
 * 用来处理有关一卡通中心的业务逻辑
 */
public class vCardSrv {

	private Message msg;
	//用于处理一卡通的消息，type是操作编号
	public vCardSrv(int type,Message msg) {
		User user;
		String userID;
		//dataBaseHelper dbh =new  dataBaseHelper();
		CardDao cardDao =new CardDao();
		ArrayList<Object> arrayList;
		ArrayList<Object> alist;
		ItemList itemlist;
		boolean success;
		switch(type){
		case 1:
			//一卡通中心的登录操作
			user = msg.getSender();
			userID = user.getUId();//获取一卡通号
			String password = user.getPwd();//获取密码
			
			//if(!dbh.searchUser(userID,password))
			//	JOptionPane.showMessageDialog(null,"用户名和密码错误！","sorry",JOptionPane.ERROR);
			System.out.println(userID+"  "+password);
			success = cardDao.searchUser(userID,password);
			System.out.println(success);
			arrayList = new ArrayList<Object>();
			SrvData srvData= new SrvData(0);
			if(success){
				System.out.println("账号"+userID);
				
				if(!srvData.findOnline(userID)){//若登录账号未登录，则会登录成功
					arrayList.add(cardDao.getUser());
					this.msg =new Message(6,1,"一卡通中心登录",arrayList,user);//返回客户端的message
					srvData.addClient(userID,"127.0.0.1");
					
				}
				else{
					arrayList.clear();
					this.msg =new Message(6,1,"重复登录",arrayList,user);//返回客户端的message
				}
			}
			else{
				arrayList.clear();
				this.msg =new Message(6,1,"一卡通中心登录",arrayList,user);//返回客户端的message
			}
			break;
		case 2:
			//一卡通的查询消费记录操作
			user = msg.getSender();
			userID = user.getUId();//获取一卡通号
			cardDao.searchRecord(userID);
			
			arrayList =cardDao.getItemList();
			this.msg =new Message(6,2,"一卡通中心消费记录",arrayList,user);//返回客户端的message
			
			break;
		case 3:
			//一卡通中心的充值操作
			user = msg.getSender();
			userID = user.getUId();//获取一卡通号
			double newRemain = user.getRemain();
			
			arrayList = msg.getData();
			itemlist =(ItemList)arrayList.get(0);
			user.setRemain(itemlist.getIsum()+newRemain);//修改发送者属性
			success = cardDao.increaseMoney(itemlist.getIsum(),userID,itemlist.getDate(),itemlist.getIsum()+newRemain);
			alist = new ArrayList<Object>();
			if(success)
			    alist.add(new Integer(1));//充值成功
			else 
				alist.add(new Integer(0));//充值失败
			this.msg =new Message(6,3,"一卡通中心充值",alist,user);//返回客户端的message
			break;
		case 4:
			//一卡通中心的缴费操作
			user = msg.getSender();
			userID = user.getUId();//获取一卡通号
			double remain = user.getRemain();
			arrayList = msg.getData();
			itemlist =(ItemList)arrayList.get(0);
			user.setRemain(itemlist.getIsum()+remain);//修改发送者属性
			success =cardDao.decreaseMoney(itemlist.getIsum(),userID,itemlist.getDate(),itemlist.getIsum()+remain,itemlist.getConsumePwd());
			alist = new ArrayList<Object>();
			if(success)
			    alist.add(new Integer(1));//缴费成功
			else 
				alist.add(new Integer(0));//缴费失败
			this.msg =new Message(6,4,"一卡通中心缴费",alist,user);//返回客户端的message
			break;
		case 5:
			//一卡通中心的挂失操作
			user = msg.getSender();
			userID = user.getUId();//获取一卡通号
			
			int u = (Integer)msg.getData().get(0);
			
			success = cardDao.modifyLost(u,userID);
            //修改发送者属性，此处不修改的原因是在客户层已修改
			user.setLost(u);
			alist = new ArrayList<Object>();
			if(success)
			    alist.add(new Integer(1));//修改成功
			else 
				alist.add(new Integer(0));//修改失败
			this.msg =new Message(6,5,"一卡通中心挂失",alist,user);//返回客户端的message
			break;
		}
		cardDao.close();
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
