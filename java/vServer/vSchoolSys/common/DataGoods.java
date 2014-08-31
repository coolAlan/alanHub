package vSchoolSys.common;

import java.io.Serializable;

//�����ݿ��еõ�����Ʒ��
public class DataGoods implements Serializable{
	private String gName;  //��Ʒ��
	private double sPrice;//��Ʒ����
	private int number;//��Ʒ�Ŀ��������ʾ���Թ����������--
	private String imageAddr;//ͼƬ�ı��ص�ַ
	private int type;//��Ʒ����
	private int ifAdd;
	private String gID;//��ƷID
	public DataGoods(String gName, double sPrice, int number, String imageAddr,int type,int ifAdd) {
		super();
		this.gName = gName;
		this.sPrice = sPrice;
		this.number = number;
		this.imageAddr = imageAddr;
		this.type= type;
		this.ifAdd = ifAdd;
	}
	public DataGoods(String gid ,String gName, double sPrice, int number, String imageAddr,int type) {
		super();
		this.gID = gid;
		this.gName = gName;
		this.sPrice = sPrice;
		this.number = number;
		this.imageAddr = imageAddr;
		this.type= type;
		this.ifAdd = ifAdd;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public double getsPrice() {
		return sPrice;
	}
	public void setsPrice(double sPrice) {
		this.sPrice = sPrice;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getImageAddr() {
		return imageAddr;
	}
	public void setImageAddr(String imageAddr) {
		this.imageAddr = imageAddr;
	}

	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getIfAdd() {
		return ifAdd;
	}
	
	public void setIfAdd(int ifAdd) {
		this.ifAdd = ifAdd;
	}
	
	public String getGID() {
		return gID;
	}

	public void setGID(String gid) {
		gID = gid;
	}
}
