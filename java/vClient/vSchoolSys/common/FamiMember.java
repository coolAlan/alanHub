
package vSchoolSys.common;

import java.io.Serializable;


public class FamiMember implements Serializable{
private String uID;//һ��ͨ��
private String fName;//��ͥ��Ա����
private int fAge;//��ͥ��Ա����
private String fRelation;//��ѧ����ϵ
private String fWorkPlace;//������λ
private String fJob;//ְλ
private String fPhone;//�绰
  public FamiMember(String uID,String fName,int fAge,String fRelation,String fWorkPlace,String fJob,String fPhone){
	this.uID=uID;
	this.fName=fName;
	this.fAge=fAge;
	this.fRelation=fRelation;
	this.fWorkPlace=fWorkPlace;
	this.fJob=fJob;
	this.fPhone=fPhone;
}


public int getfAge() {
	return fAge;
}
/**
 * @param age The fAge to set.
 */
public void setfAge(int fAge) {
	this.fAge = fAge;
}
/**
 * @return Returns the fJob.
 */
public String getfJob() {
	return fJob;
}
/**
 * @param job The fJob to set.
 */
public void setfJob(String fJob) {
	this.fJob = fJob;
}
/**
 * @return Returns the fName.
 */
public String getfName() {
	return fName;
}
/**
 * @param name The fName to set.
 */
public void setfName(String fName) {
	this.fName = fName;
}
/**
 * @return Returns the fPhone.
 */
public String getfPhone() {
	return fPhone;
}
/**
 * @param phone The fPhone to set.
 */
public void setfPhone(String fPhone) {
	this.fPhone = fPhone;
}
/**
 * @return Returns the fRelation.
 */
public String getfRelation() {
	return fRelation;
}
/**
 * @param relation The fRelation to set.
 */
public void setfRelation(String fRelation) {
	this.fRelation = fRelation;
}
/**
 * @return Returns the fWorkPlace.
 */
public String getfWorkPlace() {
	return fWorkPlace;
}
/**
 * @param workPlace The fWorkPlace to set.
 */
public void setfWorkPlace(String fWorkPlace) {
	this.fWorkPlace = fWorkPlace;
}
/**
 * @return Returns the uID.
 */
public String getuID() {
	return uID;
}
/**
 * @param uid The uID to set.
 */
public void setuID(String uID) {
	this.uID = uID;
}
}
