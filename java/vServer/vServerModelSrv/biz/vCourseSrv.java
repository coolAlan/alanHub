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
 * �������ˣ����ڲ����йؿγ�ģ��Ĳ���
 */
public class vCourseSrv {

	private Message msg;
	
	public vCourseSrv(int type,Message msg) {
		
		User user;
		String userID;
		
		//���ݿ����
		//dataBaseHelper dbHelper =new dataBaseHelper();
		CourseDao courseDao =new CourseDao();
		ArrayList<Object> arrayList;
		ArrayList<Object> alist;	
		
		//"ѡ��ģ��"
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
			this.msg =new Message(3,1,"ѡ��ģ���ѯȫ��",arrayList,user);//���ؿͻ��˵�message
			break;
		case 2:
			user = msg.getSender();
			System.out.println("vCourseSrv 2");
			arrayList = courseDao.getUserCourse(user.getUId());
			this.msg =new Message(3,2,"ѡ��ģ���ѯ��ѡ",arrayList,user);//���ؿͻ��˵�message
			break;
		case 3:
			user = msg.getSender();
			System.out.println("vCourseSrv 3");
			//
			arrayList = msg.getData();
			courseRecord= (CourseRecord)arrayList.get(0);
			yxcount = ((Integer)arrayList.get(1)).intValue();//ˢ�º����ѡ����
			courseDao.insertCourseRecord(courseRecord);//����ѡ�μ�¼
			courseDao.changeCourseCount(yxcount,courseRecord.getcID());//�޸���ѡ����
			//
			this.msg =new Message(3,3,"ѡ��ģ����ѡ��",new ArrayList<Object>(),user);//���ؿͻ��˵�message
			break;
		case 4:
			user = msg.getSender();
			System.out.println("vCourseSrv 4");
			//
			arrayList = msg.getData();
			courseRecord = (CourseRecord)arrayList.get(0);
			yxcount = ((Integer)arrayList.get(1)).intValue();//ˢ�º����ѡ����
			courseDao.deleteCourseRecord(courseRecord);//ɾ��ѡ�μ�¼
			courseDao.changeCourseCount(yxcount,courseRecord.getcID());//�޸���ѡ����
			//
			this.msg =new Message(3,4,"ѡ��ģ����ѡ��", new ArrayList<Object>(),user);//���ؿͻ��˵�message
			break;
		case 5:
			user = msg.getSender();
			System.out.println("vCourseSrv 5");
			arrayList = courseDao.getAllCourseRecord(user.getUId());
			this.msg =new Message(3,5,"��ѯ������ѡ�γ̳ɼ�", arrayList,user);//���ؿͻ��˵�message
			break;
		case 6:
			user = msg.getSender();
			System.out.println("vCourseSrv 6");
			roomRecord = (RoomRecord)msg.getData().get(0);
			arrayList = courseDao.getAllOkRoom(roomRecord);
			this.msg =new Message(3,6,"��ѯ�ɽ����", arrayList,user);//���ؿͻ��˵�message
			break;
		case 7:
			user = msg.getSender();
			System.out.println("vCourseSrv 7");
			roomRecord = (RoomRecord)(msg.getData().get(0));
			String str =(String)msg.getData().get(1);
			
			System.out.println("1212"+roomRecord.s);
			arrayList = courseDao.rentRoom(roomRecord,user,str);
			this.msg =new Message(3,7,"���ý���", arrayList,user);//���ؿͻ��˵�message
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
