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
		
		
		//���ݿ����
		BasicInfoDao basicInfoDao =new BasicInfoDao();
		
		ArrayList<Object> arrayList;
		ArrayList<Object> aList;

		switch(type){
		case 1:
			System.out.println("vBasicIfoSrv 1");
			//�������ݿ�������õ����ص�ArrayList<Object>
			arrayList = basicInfoDao.getBasicInfo(uID);
			//���ظ��ͻ��˵�Message
			this.message =new Message(2,1,"��ѯ������Ϣ",arrayList,user);
			break;
		case 2:

			System.out.println("vBasicIfoSrv 2");
			//�������ݿ�������õ����ص�ArrayList<Object>
			arrayList = basicInfoDao.getFamiMember(uID);
			//���ظ��ͻ��˵�Message
			this.message =new Message(2,1,"��ѯ��ͥ��Ա",arrayList,user);
			break;
		case 3:
		
			System.out.println("vBasicIfoSrv 3");
			//�������ݿ�������õ����ص�ArrayList<Object>
			arrayList = basicInfoDao.getEduExperience(uID);
			//���ظ��ͻ��˵�Message
			this.message =new Message(2,1,"��ѯ������Ϣ",arrayList,user);
			break;
		case 4:
		
			System.out.println("vBasicIfoSrv 4");
			//�������ݿ�������õ����ص�ArrayList<Object>
			arrayList = basicInfoDao.getRewOrPuniInfo(uID);
			//���ظ��ͻ��˵�Message
			this.message =new Message(2,1,"��ѯ������Ϣ",arrayList,user);
			break;
		case 5:
			user = msg.getSender();
			System.out.println("vBasicIfoSrv 5");
			
			aList = msg.getData();
			int age= Integer.parseInt( ( aList.get(0) ).toString() );//data[0]���޸ĵ�����
			String address = ( aList.get(1) ).toString();
			basicInfoDao.changeBasicInfo(age, address, uID);
			//���ظ��ͻ��˵�Message
			this.message =new Message(2,5,"�޸Ļ�����Ϣ", new ArrayList<Object>(),user);//���ؿͻ��˵�message
			break;
		case 6:
			user = msg.getSender();
			System.out.println("vBasicIfoSrv 6");
			
			aList = msg.getData();
			FamiMember famiMember = (FamiMember)( aList.get(0) );//data[0]�Ǽ�ͥ��Ա
			basicInfoDao.changeFamilyInfo(famiMember);
			//���ظ��ͻ��˵�Message
			this.message =new Message(2,6,"�޸ļ�ͥ��Ա��Ϣ", new ArrayList<Object>(),user);//���ؿͻ��˵�message
			break;
		case 7:
			user = msg.getSender();
			System.out.println("vBasicIfoSrv 7");
			
			aList = msg.getData();
			EduExperience eduExperience = (EduExperience)( aList.get(0) );//data[0]�ǽ�����¼
			basicInfoDao.addEducationInfo(eduExperience);
			
			this.message =new Message(2,7,"��ӽ�����Ϣ", new ArrayList<Object>(),user);//���ؿͻ��˵�message
			break;
		case 8:
			user = msg.getSender();
			System.out.println("vBasicIfoSrv 8");
			
			aList = msg.getData();
			FamiMember familyMember = (FamiMember)( aList.get(0) );//data[0]�Ǽ�ͥ��Ա
			basicInfoDao.addFamilyInfo(familyMember);
			
			this.message =new Message(2,8,"��Ӽ�ͥ��Ա", new ArrayList<Object>(),user);//���ؿͻ��˵�message
			break;
		case 9:
			user = msg.getSender();
			System.out.println("vBasicIfoSrv 9");
			
			arrayList = msg.getData();
			String school= (String)arrayList.get(0);//data[0]��ѡ�μ�¼
			basicInfoDao.deleEducationInfo(school);
			
			this.message =new Message(2,9,"ɾ��������Ϣ", new ArrayList<Object>(),user);//���ؿͻ��˵�message
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
