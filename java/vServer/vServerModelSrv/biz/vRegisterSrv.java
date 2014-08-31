/*
 * Created on 2013-9-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vServerModelSrv.biz;

import java.util.ArrayList;

import vSchoolSys.common.Message;
import vSchoolSys.common.User;
import vSchoolSys.dao.RegisterDao;

/**
 * @author Alan
 *
 * 用于处理用户注册相关的事件
 */
public class vRegisterSrv {

	private Message msg;
	
	public vRegisterSrv(int type,Message msg){
		ArrayList<Object> arrayList;
		RegisterDao dao = new RegisterDao();
		switch(type){
		case 1:
			arrayList = msg.getData();
			String id = ((User)arrayList.get(0)).getUId();
			Boolean success =  dao.searchUser(id);
			arrayList.clear();
			arrayList.add(success);
			this.msg =new Message(1,1,"用户注册",arrayList,null);//返回客户端的message
			break;
		case 2:
			arrayList = msg.getData();
			User user = ((User)arrayList.get(0));
			Boolean s =  dao.insertUser(user);
			arrayList.clear();
			arrayList.add(s);
			this.msg =new Message(1,2,"用户注册",arrayList,user);//返回客户端的message
			break;	
		}
		dao.close();
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
