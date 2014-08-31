package vSchoolSys.common;

import java.io.Serializable;

//CourseRecord类：用来记录一条“选课记录”
public class CourseRecord implements Serializable{

	private String cID;//课程ID
	private String uID;//用户ID
	private String cName;//用户ID

	private int cGrade;//成绩	
	private String[] srtCouRecord = new String[3];;//String[]形式返回选课记录信息
	

	//构造函数
	public CourseRecord(String cID,String uID,int cGrade,String cName) {		
		this.cID=cID;
		this.uID=uID;
		this.cGrade=cGrade;	
		this.cName=cName;	
		initiSrtCouRecord();
	}
	
	//初始化srtCouRecord
	public void initiSrtCouRecord() {
		
		srtCouRecord[0]= this.cID;
		srtCouRecord[1]= this.cName;
		srtCouRecord[2]= ""+( this.cGrade );

	}
	
	//get/set方法
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
