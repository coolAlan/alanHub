package vSchoolSys.common;

import java.io.Serializable;
import java.util.Date;

public class EduExperience implements Serializable{
	private String uID;//一卡通号
	private String eduStartTime;//教育开始时间
	private String eduEndTime;//教育结束时间
	private String school;//学校
	private String degree;//获得学位
	public EduExperience(String uID,String eduStartTime,String eduEndTime,String school,String degree){
		this.uID=uID;
		this.eduStartTime=eduStartTime;
		this.eduEndTime=eduEndTime;
		this.school=school;
		this.degree=degree;
		
	}
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getEduStartTime() {
		return eduStartTime;
	}
	public void setEduStartTime(String eduStartTime) {
		this.eduStartTime = eduStartTime;
	}
	public String getEduEndTime() {
		return eduEndTime;
	}
	public void setEduEndTime(String eduEndTime) {
		this.eduEndTime = eduEndTime;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	

}
