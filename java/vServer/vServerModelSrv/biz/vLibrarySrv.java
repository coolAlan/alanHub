/*
 * Created on 2013-9-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vServerModelSrv.biz;

import java.util.ArrayList;
import java.util.Date;

import publicData.SrvData;
import vSchoolSys.common.Book;
import vSchoolSys.common.ItemList;
import vSchoolSys.common.Message;
import vSchoolSys.common.Record;
import vSchoolSys.common.User;
import vSchoolSys.dao.CardDao;
import vSchoolSys.dao.LibraryDao;

/**
 * @author Alan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class vLibrarySrv {

	private Message msg;
	//用于处理一卡通的消息，type是操作编号
	public vLibrarySrv(int type,Message msg) {
		User user;
		String userID;
		//dataBaseHelper dbh =new  dataBaseHelper();
		LibraryDao libDao =new LibraryDao();
		ArrayList<Object> arrayList;
		ArrayList<Object> alist;
		ItemList itemlist;
		boolean success;
		switch(type){
		case 1:
			
			user = msg.getSender();
			System.out.println("vLibrarySrv 1");
			int sType = Integer.valueOf((Integer)(msg.getData().get(1)));
			if(sType==1){
			libDao.searchBook((String)(msg.getData().get(0)),user.getUId());
			arrayList = libDao.getBookList();//没有搜索到时返回空的ArrayList
			this.msg =new Message(4,1,"图书搜索结果",arrayList,user);//返回客户端的message
			}
			break;
		case 2:
			user = msg.getSender();
			System.out.println("vLibrarySrv 2");
			arrayList = msg.getData();
			for(int i = 0;i<arrayList.size();i++){
			libDao.decreaseBook((Book)arrayList.get(i));
			libDao.insertRec(user.getUId(), ((Book)arrayList.get(i)).getbID(), new Date(), 0);  //插入借书记录
			}
			arrayList.clear();
			this.msg =new Message(4,2,"图书借阅结果",arrayList,user);//返回客户端的message
			
			break;
		case 3:
			user = msg.getSender();
			System.out.println("vLibrarySrv 3");
			 //查询借书记录
			libDao.checkRec(user);
			arrayList = libDao.getCheckList();
			this.msg =new Message(4,3,"图书借阅记录",arrayList,user);//返回客户端的message
			break;
		case 4:
			user = msg.getSender();
			System.out.println("vLibrarySrv 4");
			 //查询图书还书列表
			libDao.searchRec(user);
			arrayList = libDao.getReturnList();
			this.msg =new Message(4,4,"图书还书列表",arrayList,user);//返回客户端的message
			break;
		case 5:
			user = msg.getSender();
			System.out.println("vLibrarySrv 5");
			 //还书操作
			alist = msg.getData();
			for(int i=0;i<alist.size();i++){
			libDao.increaseBook((Record)alist.get(i));
			}
			arrayList = new ArrayList<Object>();
			this.msg =new Message(4,5,"图书馆还书操作",arrayList,user);//返回客户端的message
			break;
		case 6:
			user = msg.getSender();
			System.out.println("vLibrarySrv 6");
			 //查询借书排行榜
			libDao.sortList();
			arrayList = libDao.getSortList();
			this.msg =new Message(4,6,"图书馆借书排行榜",arrayList,user);//返回客户端的message
			break;
		}
		libDao.close();
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
