package vSchoolSys.common;

import java.io.Serializable;
import java.util.Date;

public class EduExperience implements Serializable{
	private String uID;//һ��ͨ��
	private String eduStartTime;//������ʼʱ��
	private String eduEndTime;//��������ʱ��
	private String school;//ѧУ
	private String degree;//���ѧλ
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
