package vSchoolSys.common;

import java.io.Serializable;


//存储课程信息的实体类

public class Course implements Serializable{

	private String couID; 
	private String couName;
	private String couTeacher;
	private String couTime;
	private int couHour;
	private int couCount;
	private int couSelectedCount;
	
	private String[] strCourse = new String[10];;
	private String[] strCouRecord = new String[5];
	
	//默认构造函数
	public Course(){	
	}
	
	//构造函数
	public Course(String id,String name,String teacher,int hour,String time,int count,int selectedCount){	
		
		this.couID=id;
		this.couName=name;
		this.couTeacher=teacher;
		this.couHour=hour;
		this.couTime=time;
		this.couCount=count;
		this.couSelectedCount=selectedCount;
	}
		
	//以字符串数组String[]返回“课程表格xkTable”中的一条记录
	public  String[] CourseToString(){

		strCourse[0]=couID;
		strCourse[1]=couName;
		strCourse[2]=couTeacher;
		strCourse[3]=couTime;
		strCourse[4]=""+couHour;
		strCourse[5]=""+couCount;
		strCourse[6]=""+couSelectedCount;
		strCourse[7]="选课";
		strCourse[8]="退课";
		strCourse[9]="未选";
	
		return strCourse;
	}
	
	//以字符串数组String[]返回“课表表格kbTable”中的一条记录
	public String[] couRecordToString() {
		
		strCouRecord[0]=couID;
		strCouRecord[1]=couName;
		strCouRecord[2]=couTeacher;
		strCouRecord[3]=couTime;
		strCouRecord[4]=""+couHour;
		
		return strCouRecord;
	}
	
	//各字段的get/set方法
	public int getCouCount() {
		return couCount;
	}
	public void setCouCount(int couCount) {
		this.couCount = couCount;
	}
	public int getCouHour() {
		return couHour;
	}
	public void setCouHour(int couHour) {
		this.couHour = couHour;
	}
	public String getCouID() {
		return couID;
	}
	public void setCouID(String couID) {
		this.couID = couID;
	}
	public String getCouName() {
		return couName;
	}
	public void setCouName(String couName) {
		this.couName = couName;
	}
	public int getCouSelectedCount() {
		return couSelectedCount;
	}
	public void setCouSelectedCount(int couSelectedCount) {
		this.couSelectedCount = couSelectedCount;
	}
	public String getCouTeacher() {
		return couTeacher;
	}
	public void setCouTeacher(String couTeacher) {
		this.couTeacher = couTeacher;
	}
	public String getCouTime() {
		return couTime;
	}
	public void setCouTime(String couTime) {
		this.couTime = couTime;
	}
	
}
