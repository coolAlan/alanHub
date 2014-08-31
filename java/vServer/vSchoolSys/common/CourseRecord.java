package vSchoolSys.common;

import java.io.Serializable;

//CourseRecord�ࣺ������¼һ����ѡ�μ�¼��
public class CourseRecord implements Serializable{

	private String cID;//�γ�ID
	private String uID;//�û�ID
	private String cName;//�û�ID

	private int cGrade;//�ɼ�	
	private String[] srtCouRecord = new String[3];;//String[]��ʽ����ѡ�μ�¼��Ϣ
	

	//���캯��
	public CourseRecord(String cID,String uID,int cGrade,String cName) {		
		this.cID=cID;
		this.uID=uID;
		this.cGrade=cGrade;	
		this.cName=cName;	
		initiSrtCouRecord();
	}
	
	//��ʼ��srtCouRecord
	public void initiSrtCouRecord() {
		
		srtCouRecord[0]= this.cID;
		srtCouRecord[1]= this.cName;
		srtCouRecord[2]= ""+( this.cGrade );

	}
	
	//get/set����
	public String[] getSrtCouRecord() {
		return srtCouRecord;
	}

	public String getcID() {
		return cID;
	}
	public void setcID(String cID) {
		this.cID = cID;
	}

	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}

	public int getcGrade() {
		return cGrade;
	}
	public void setcGrade(int cGrade) {
		this.cGrade = cGrade;
	}
	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}


}
