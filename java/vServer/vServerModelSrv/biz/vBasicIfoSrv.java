package vServerModelSrv.biz;

import java.util.ArrayList;

import publicData.SrvData;
import vSchoolSys.common.CourseRecord;
import vSchoolSys.common.EduExperience;
import vSchoolSys.common.FamiMember;
import vSchoolSys.common.ItemList;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;
import vSchoolSys.dao.BasicInfoDao;
import vSchoolSys.dao.CardDao;
import vSchoolSys.dao.CourseDao;

public class vBasicIfoSrv {

	private Message message;
	
	public vBasicIfoSrv(int type,Message msg) {
	
		User user = msg.getSender();

		String uID=user.getUId();
		System.out.println("wahahahaha"+user.getUId());
		
		
		//数据库操作
		BasicInfoDao basicInfoDao =new BasicInfoDao();
		
		ArrayList<Object> arrayList;
		ArrayList<Object> aList;

		switch(type){
		case 1:
			System.out.println("vBasicIfoSrv 1");
			//请求数据库操作并得到返回的ArrayList<Object>
			arrayList = basicInfoDao.getBasicInfo(uID);
			//返回给客户端的Message
			this.message =new Message(2,1,"查询基本信息",arrayList,user);
			break;
		case 2:

			System.out.println("vBasicIfoSrv 2");
			//请求数据库操作并得到返回的ArrayList<Object>
			arrayList = basicInfoDao.getFamiMember(uID);
			//返回给客户端的Message
			this.message =new Message(2,1,"查询家庭成员",arrayList,user);
			break;
		case 3:
		
			System.out.println("vBasicIfoSrv 3");
			//请求数据库操作并得到返回的ArrayList<Object>
			arrayList = basicInfoDao.getEduExperience(uID);
			//返回给客户端的Message
			this.message =new Message(2,1,"查询教育信息",arrayList,user);
			break;
		case 4:
		
			System.out.println("vBasicIfoSrv 4");
			//请求数据库操作并得到返回的ArrayList<Object>
			arrayList = basicInfoDao.getRewOrPuniInfo(uID);
			//返回给客户端的Message
			this.message =new Message(2,1,"查询奖惩信息",arrayList,user);
			break;
		case 5:
			user = msg.getSender();
			System.out.println("vBasicIfoSrv 5");
			
			aList = msg.getData();
			int age= Integer.parseInt( ( aList.get(0) ).toString() );//data[0]是修改的年龄
			String address = ( aList.get(1) ).toString();
			basicInfoDao.changeBasicInfo(age, address, uID);
			//返回给客户端的Message
			this.message =new Message(2,5,"修改基本信息", new ArrayList<Object>(),user);//返回客户端的message
			break;
		case 6:
			user = msg.getSender();
			System.out.println("vBasicIfoSrv 6");
			
			aList = msg.getData();
			FamiMember famiMember = (FamiMember)( aList.get(0) );//data[0]是家庭成员
			basicInfoDao.changeFamilyInfo(famiMember);
			//返回给客户端的Message
			this.message =new Message(2,6,"修改家庭成员信息", new ArrayList<Object>(),user);//返回客户端的message
			break;
		case 7:
			user = msg.getSender();
			System.out.println("vBasicIfoSrv 7");
			
			aList = msg.getData();
			EduExperience eduExperience = (EduExperience)( aList.get(0) );//data[0]是教育记录
			basicInfoDao.addEducationInfo(eduExperience);
			
			this.message =new Message(2,7,"添加教育信息", new ArrayList<Object>(),user);//返回客户端的message
			break;
		case 8:
			user = msg.getSender();
			System.out.println("vBasicIfoSrv 8");
			
			aList = msg.getData();
			FamiMember familyMember = (FamiMember)( aList.get(0) );//data[0]是家庭成员
			basicInfoDao.addFamilyInfo(familyMember);
			
			this.message =new Message(2,8,"添加家庭成员", new ArrayList<Object>(),user);//返回客户端的message
			break;
		case 9:
			user = msg.getSender();
			System.out.println("vBasicIfoSrv 9");
			
			arrayList = msg.getData();
			String school= (String)arrayList.get(0);//data[0]是选课记录
			basicInfoDao.deleEducationInfo(school);
			
			this.message =new Message(2,9,"删除教育信息", new ArrayList<Object>(),user);//返回客户端的message
			break;
		}
		basicInfoDao.close();
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
