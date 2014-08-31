package vSchoolSys.common;

import java.io.Serializable;

//加入购物车中的商品类
public class ShopCartGoods implements Serializable{
  
	public String gName;//商品的名字
	public double sPrice;//单价
	public int number;//购买的数量
	public int aNumber;//库存
	

	  public ShopCartGoods(String gName, double sPrice, int number, int aNumber) {
			super();
			this.gName = gName;
			this.sPrice = sPrice;
			this.number = number;
			this.aNumber = aNumber;
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



	public int getaNumber() {
		return aNumber;
	}



	public void setaNumber(int aNumber) {
		this.aNumber = aNumber;
	}
	
	
}
