package vSchoolSys.common;

import java.io.Serializable;

//从数据库中得到的商品类
public class DataGoods implements Serializable{
	private String gName;  //商品名
	private double sPrice;//商品单价
	private int number;//商品的库存量，表示可以购买的数量，--
	private String imageAddr;//图片的本地地址
	private int type;//商品类型
	private int ifAdd;
	private String gID;//商品ID
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
