/*
 * Created on 2013-8-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vServerModelSrv.biz;

import java.util.ArrayList;

import vSchoolSys.common.CourseRecord;
import vSchoolSys.common.Message;
import vSchoolSys.common.RoomRecord;
import vSchoolSys.common.User;
import vSchoolSys.dao.CourseDao;

/**
 * 服务器端，用于操作有关课程模块的操作
 */
public class vCourseSrv {

	private Message msg;
	
	public vCourseSrv(int type,Message msg) {
		
		User user;
		String userID;
		
		//数据库操作
		//dataBaseHelper dbHelper =new dataBaseHelper();
		CourseDao courseDao =new CourseDao();
		ArrayList<Object> arrayList;
		ArrayList<Object> alist;	
		
		//"选课模块"
		CourseRecord courseRecord;		
		int yxcount;
		//
		RoomRecord roomRecord;
		
		boolean success;
		
		switch(type){
		case 1:
			user = msg.getSender();
			System.out.println("vCourseSrv 1");
			arrayList = courseDao.getAllCourse();
			this.msg =new Message(3,1,"选课模块查询全部",arrayList,user);//返回客户端的message
			break;
		case 2:
			user = msg.getSender();
			System.out.println("vCourseSrv 2");
			arrayList = courseDao.getUserCourse(user.getUId());
			this.msg =new Message(3,2,"选课模块查询已选",arrayList,user);//返回客户端的message
			break;
		case 3:
			user = msg.getSender();
			System.out.println("vCourseSrv 3");
			//
			arrayList = msg.getData();
			courseRecord= (CourseRecord)arrayList.get(0);
			yxcount = ((Integer)arrayList.get(1)).intValue();//刷新后的已选人数
			courseDao.insertCourseRecord(courseRecord);//增加选课记录
			courseDao.changeCourseCount(yxcount,courseRecord.getcID());//修改已选人数
			//
			this.msg =new Message(3,3,"选课模块已选课",new ArrayList<Object>(),user);//返回客户端的message
			break;
		case 4:
			user = msg.getSender();
			System.out.println("vCourseSrv 4");
			//
			arrayList = msg.getData();
			courseRecord = (CourseRecord)arrayList.get(0);
			yxcount = ((Integer)arrayList.get(1)).intValue();//刷新后的已选人数
			courseDao.deleteCourseRecord(courseRecord);//删除选课记录
			courseDao.changeCourseCount(yxcount,courseRecord.getcID());//修改已选人数
			//
			this.msg =new Message(3,4,"选课模块退选课", new ArrayList<Object>(),user);//返回客户端的message
			break;
		case 5:
			user = msg.getSender();
			System.out.println("vCourseSrv 5");
			arrayList = courseDao.getAllCourseRecord(user.getUId());
			this.msg =new Message(3,5,"查询该人已选课程成绩", arrayList,user);//返回客户端的message
			break;
		case 6:
			user = msg.getSender();
			System.out.println("vCourseSrv 6");
			roomRecord = (RoomRecord)msg.getData().get(0);
			arrayList = courseDao.getAllOkRoom(roomRecord);
			this.msg =new Message(3,6,"查询可借教室", arrayList,user);//返回客户端的message
			break;
		case 7:
			user = msg.getSender();
			System.out.println("vCourseSrv 7");
			roomRecord = (RoomRecord)(msg.getData().get(0));
			String str =(String)msg.getData().get(1);
			
			System.out.println("1212"+roomRecord.s);
			arrayList = courseDao.rentRoom(roomRecord,user,str);
			this.msg =new Message(3,7,"借用教室", arrayList,user);//返回客户端的message
			break;
		}
		courseDao.close();
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
