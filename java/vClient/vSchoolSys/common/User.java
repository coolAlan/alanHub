
package vSchoolSys.common;

import java.io.Serializable;

/**
 * @author shipeng
 *
 * 存储用户信息的实体类
 */
public class User implements Serializable {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	//private static final long serialVersionUID = 3454558035178168414L;
	private String uId=null;//用户ID即一卡通号
	private String uName=null;//用户姓名
	private String pwd=null;//用户密码
	private  String stdNumber=null;//用户学号
	private double remain=0;//一卡通余额
	private  int role=0;//0是学生，1是管理员
	private String uPhone=null;//手机号码
	private int lost = 0; //1表示挂失
	//全职构造函数
	public User(String uId,String pwd,String uName,String stdNumber,double remain,int role,String uPhone,int lost){
		this.uId=uId;
		this.uName=uName;
		this.pwd=pwd;
		this.stdNumber=stdNumber;
		this.uPhone=uPhone;
		this.remain = remain;
		this.role =role;
		this.lost = lost;
	}
	//登陆构造函数，只需要输入ID和密码
	public User(String uId, String pwd){
		this.uId = uId;
		this.pwd = pwd;
	
	}
	

	/**
	 * @return Returns the pwd.
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd The pwd to set.
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * @return Returns the remain.
	 */
	public double getRemain() {
		return remain;
	}
	/**
	 * @param remain The remain to set.
	 */
	public void setRemain(double remain) {
		this.remain = remain;
	}
	/**
	 * @return Returns the role.
	 */
	public int getRole() {
		return role;
	}
	/**
	 * @param role The role to set.
	 */
	public void setRole(int role) {
		this.role = role;
	}
	/**
	 * @return Returns the stdNumber.
	 */
	public String getStdNumber() {
		return stdNumber;
	}
	/**
	 * @param stdNumber The stdNumber to set.
	 */
	public void setStdNumber(String stdNumber) {
		this.stdNumber = stdNumber;
	}
	/**
	 * @return Returns the uId.
	 */
	public String getUId() {
		return uId;
	}
	/**
	 * @param id The uId to set.
	 */
	public void setUId(String id) {
		uId = id;
	}
	/**
	 * @return Returns the uName.
	 */
	public String getUName() {
		return uName;
	}
	/**
	 * @param name The uName to set.
	 */
	public void setUName(String name) {
		uName = name;
	}
	/**
	 * @return Returns the uPhone.
	 */
	public String getUPhone() {
		return uPhone;
	}
	/**
	 * @param phone The uPhone to set.
	 */
	public void setUPhone(String phone) {
		uPhone = phone;
	}
	
	/**
	 * @return Returns the lost.
	 */
	public int getLost() {
		return lost;
	}
	/**
	 * @param lost The lost to set.
	 */
	public void setLost(int lost) {
		this.lost = lost;
	}
}
