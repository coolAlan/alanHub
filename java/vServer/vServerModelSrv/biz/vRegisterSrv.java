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
 * ���ڴ����û�ע����ص��¼�
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
			this.msg =new Message(1,1,"�û�ע��",arrayList,null);//���ؿͻ��˵�message
			break;
		case 2:
			arrayList = msg.getData();
			User user = ((User)arrayList.get(0));
			Boolean s =  dao.insertUser(user);
			arrayList.clear();
			arrayList.add(s);
			this.msg =new Message(1,2,"�û�ע��",arrayList,user);//���ؿͻ��˵�message
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
