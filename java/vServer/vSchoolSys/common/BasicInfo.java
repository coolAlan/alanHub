package vSchoolSys.common;

import java.io.Serializable;
import java.util.Date;

public class BasicInfo implements Serializable{
private String uID;//一卡通
private String uName;//用户姓名
private String uStdNumber;//学号
private double uBalance;//余额
private String uPhone;//电话
private int uAge;//年龄
private int uSex;//性别0男1女
private Date uBirth;//出生日期
private String uAddress;//家庭住址

	public BasicInfo(String uID,String uName,String uStdNumber,double uBalance,String uPhone,int uAge,int uSex,Date uBirth,String uAddress) {
	
	 this.uID=uID;
	 this.uName=uName;
	 this.uStdNumber=uStdNumber;
	 this.uBalance=uBalance;
	 this.uPhone=uPhone;
	 this.uAge=uAge;
	 this. uSex= uSex;
	 this.uBirth=uBirth;
	 this.uAddress=uAddress;
	}

	public String getuID() {
		return uID;
	}

	public void setuID(String uID) {
		this.uID = uID;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuStdNumber() {
		return uStdNumber;
	}

	public void setuStdNumber(String uStdNumber) {
		this.uStdNumber = uStdNumber;
	}

	public double getuBalance() {
		return uBalance;
	}

	public void setuBalance(double uBalance) {
		this.uBalance = uBalance;
	}

	public String getuPhone() {
		return uPhone;
	}

	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}

	public int getuAge() {
		return uAge;
	}

	public void setuAge(int uAge) {
		this.uAge = uAge;
	}

	public int getuSex() {
		return uSex;
	}

	public void setuSex(int uSex) {
		this.uSex = uSex;
	}

	public Date getuBirth() {
		return uBirth;
	}

	public void setuBirth(Date uBirth) {
		this.uBirth = uBirth;
	}

	public String getuAddress() {
		return uAddress;
	}

	public void setuAddress(String uAddress) {
		this.uAddress = uAddress;
	}

}
