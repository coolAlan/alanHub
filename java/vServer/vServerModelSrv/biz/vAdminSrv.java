/*
 * Created on 2013-9-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vServerModelSrv.biz;

import java.util.ArrayList;

import vSchoolSys.common.Book;
import vSchoolSys.common.Course;
import vSchoolSys.common.DataGoods;
import vSchoolSys.common.Message;
import vSchoolSys.common.RewOrPuniInfo;
import vSchoolSys.common.User;
import vSchoolSys.dao.AdminDao;

/**
 * @author Alan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class vAdminSrv {

	private Message message;
	
	public vAdminSrv(int type,Message msg) {
	
		User user = msg.getSender();

		String uID=user.getUId();
		//System.out.println("wahahahaha"+user.getUId());
		
		
		//数据库操作
		AdminDao adminDao =new AdminDao();
		
		ArrayList<Object> arrayList;
		ArrayList<Object> aList;

		switch(type){
		case 1:
			System.out.println("vAdminSrv 1");
			//请求数据库操作并得到返回的ArrayList<Object>
			arrayList = adminDao.getAllCourse();
			//返回给客户端的Message
			this.message =new Message(8,1,"查询课程信息",arrayList,user);
			break;
		case 2:

			System.out.println("vAdminSrv 2");
			arrayList = msg.getData();
		    
			adminDao.addCourse((Course)arrayList.get(0));
			//返回给客户端的Message
			arrayList.clear();
			this.message =new Message(8,2,"增加课程",arrayList,user);
			break;
		case 3:
		
			System.out.println("vAdminSrv 3");
			
			arrayList = msg.getData();
			 adminDao.deleCourse((String)arrayList.get(0));
			//返回给客户端的Message
			this.message =new Message(8,3,"删除课程",new ArrayList<Object>(),user);
			break;
		case 4:
		
			System.out.println("vAdminSrv 4");
			//请求数据库操作并得到返回的ArrayList<Object>
			arrayList = adminDao.getAllGoods();
			//返回给客户端的Message
			this.message =new Message(8,4,"查询商品信息",arrayList,user);
			break;
		case 5:
			user = msg.getSender();
			System.out.println("vAdminSrv 5");
			
			aList = msg.getData();
			DataGoods good = (DataGoods)aList.get(0);
			adminDao.changeGoodInfo(good.getGID(),good.getsPrice(),good.getNumber());
			//返回给客户端的Message
			this.message =new Message(8,5,"修改商品信息", new ArrayList<Object>(),user);//返回客户端的message
			break;
		case 6:
			user = msg.getSender();
			System.out.println("vAdminSrv 6");
			aList = msg.getData();
			adminDao.addGood((DataGoods)aList.get(0));
			//返回给客户端的Message
			this.message =new Message(8,6,"增加商品信息", new ArrayList<Object>(),user);//返回客户端的message
			break;
		case 7:
			user = msg.getSender();
			System.out.println("vAdminSrv 7");
			
			aList = msg.getData();
			adminDao.deleGood((String)aList.get(0));
			this.message =new Message(8,7,"删除商品信息", new ArrayList<Object>(),user);//返回客户端的message
			break;
		case 8:
			user = msg.getSender();
			System.out.println("vAdminSrv 8");
			
			aList = adminDao.getRewOrPuniInfo();
			
		    this.message =new Message(8,8,"查询奖惩信息",aList ,user);//返回客户端的message
			break;
		case 9:
			user = msg.getSender();
			System.out.println("vAdminSrv 9");
			arrayList = msg.getData();
			adminDao.delReward(((Integer)arrayList.get(0)).intValue());
			this.message =new Message(8,9,"删除奖惩信息", new ArrayList<Object>(),user);//返回客户端的message
			break;
		case 10:
			user = msg.getSender();
			System.out.println("vAdminSrv 10");
			arrayList = msg.getData();
			adminDao.addReward((RewOrPuniInfo)arrayList.get(0));
			this.message =new Message(8,9,"添加奖惩信息", new ArrayList<Object>(),user);//返回客户端的message
			break;
		case 11:
			user = msg.getSender();
			System.out.println("vAdminSrv 11");
			aList = adminDao.getBooks();
		    this.message =new Message(8,11,"查询图书信息",aList ,user);//返回客户端的message
			break;
		case 12:
			user = msg.getSender();
			System.out.println("vAdminSrv 12");
			arrayList = msg.getData();
		    adminDao.delBook((String)arrayList.get(0));
		    arrayList.clear();
		    this.message =new Message(8,12,"删除图书信息",arrayList ,user);//返回客户端的message
			break;
		case 13:
			user = msg.getSender();
			System.out.println("vAdminSrv 13");
			arrayList = msg.getData();
			adminDao.addBook((Book)arrayList.get(0));
			this.message =new Message(8,13,"添加图书信息", new ArrayList<Object>(),user);//返回客户端的message
			break;
		}
		adminDao.close();
	}
	/**
	 * @return Returns the msg.
	 */
	public Message getMsg() {
		return message;
	}
	/**
	 * @param msg The msg to set.
	 */
	public void setMsg(Message msg) {
		this.message = msg;
	}
	

}
