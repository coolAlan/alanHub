
package vSchoolSys.common;

import java.io.Serializable;

/**
 * @author shipeng
 *
 * �洢�û���Ϣ��ʵ����
 */
public class User implements Serializable {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	//private static final long serialVersionUID = 3454558035178168414L;
	private String uId=null;//�û�ID��һ��ͨ��
	private String uName=null;//�û�����
	private String pwd=null;//�û�����
	private  String stdNumber=null;//�û�ѧ��
	private double remain=0;//һ��ͨ���
	private  int role=0;//0��ѧ����1�ǹ���Ա
	private String uPhone=null;//�ֻ�����
	private int lost = 0; //1��ʾ��ʧ
	//ȫְ���캯��
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
	//��½���캯����ֻ��Ҫ����ID������
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
