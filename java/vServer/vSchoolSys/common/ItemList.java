/*
 * Created on 2013-8-26
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vSchoolSys.common;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shipeng
 *
 *�洢���Ѽ�¼��ʵ���� 
 */
public class ItemList implements Serializable {
	private int order;//���
	private String iuID;//������ID
	private String iuName;//�ջ�������
	private String iphone;//�ջ����ֻ�
	private String iAddr;//�ջ��˵�ַ
	private String iRemark;//�ջ��˱�ע
	private double isum;//�����ܼƸ�����ʾ����������ʾ��ֵ
	private Date date;//��������
	private int consumeName;//�������ƣ�ֻ������1����ֵ 0��������
	private String ConsumePwd;//��������ֻ���ڳ�ֵ���߳�ֵ����������
	public ItemList(String iuID,String iuName,String iphone,String iAddr,String iRemark,double isum,Date date){
		this.iuID=iuID;//������ID
		this.iuName=iuName;//�ջ�������
		this.iphone=iphone;//�ջ����ֻ�
		this.iAddr=iAddr;//�ջ��˵�ַ
		this.iRemark=iRemark;//�ջ��˱�ע
		this.isum=isum;//�����ܼƸ�����ʾ����������ʾ��ֵ
		this.date=date;//��������
	}
	public ItemList(int order,int str ,double sum , Date date){
		this.order = order;
		this.consumeName = str;
		this.isum = sum;
		this.date = date;
	}
	public ItemList(int c ,double sum , Date date,String pwd){

		this.consumeName = c;
		this.isum = sum;
		this.date = date;
		this.ConsumePwd =pwd;
	}
	
	/**
	 * @return Returns the date.
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date The date to set.
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return Returns the iAddr.
	 */
	public String getIAddr() {
		return iAddr;
	}
	/**
	 * @param addr The iAddr to set.
	 */
	public void setIAddr(String addr) {
		iAddr = addr;
	}
	/**
	 * @return Returns the iphone.
	 */
	public String getIphone() {
		return iphone;
	}
	/**
	 * @param iphone The iphone to set.
	 */
	public void setIphone(String iphone) {
		this.iphone = iphone;
	}
	/**
	 * @return Returns the iRemark.
	 */
	public String getIRemark() {
		return iRemark;
	}
	/**
	 * @param remark The iRemark to set.
	 */
	public void setIRemark(String remark) {
		iRemark = remark;
	}
	/**
	 * @return Returns the isum.
	 */
	public double getIsum() {
		return isum;
	}
	/**
	 * @param isum The isum to set.
	 */
	public void setIsum(double isum) {
		this.isum = isum;
	}
	/**
	 * @return Returns the iuID.
	 */
	public String getIuID() {
		return iuID;
	}
	/**
	 * @param iuID The iuID to set.
	 */
	public void setIuID(String iuID) {
		this.iuID = iuID;
	}
	/**
	 * @return Returns the iuName.
	 */
	public String getIuName() {
		return iuName;
	}
	/**
	 * @param iuName The iuName to set.
	 */
	public void setIuName(String iuName) {
		this.iuName = iuName;
	}
	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}
	/**
	 * @return the consumeName
	 */
	public int getConsumeName() {
		return consumeName;
	}
	/**
	 * @param consumeName the consumeName to set
	 */
	public void setConsumeName(int consumeName) {
		this.consumeName = consumeName;
	}
	
	
	/**
	 * @return Returns the consumePwd.
	 */
	public String getConsumePwd() {
		return ConsumePwd;
	}
	/**
	 * @param consumePwd The consumePwd to set.
	 */
	public void setConsumePwd(String consumePwd) {
		ConsumePwd = consumePwd;
	}
}
