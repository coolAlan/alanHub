package vSchoolSys.common;

import java.io.Serializable;

//���빺�ﳵ�е���Ʒ��
public class ShopCartGoods implements Serializable{
  
	public String gName;//��Ʒ������
	public double sPrice;//����
	public int number;//���������
	public int aNumber;//���
	

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
