/*
 * Created on 2013-8-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vSchoolSys.common;

import java.io.Serializable;
import java.util.Date;

/**
 * @author C5137
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//ͼ����
public class Book implements Serializable{
	private boolean bFlag;   //���ڱ��ͼ���Ƿ�ѡ��
	private String bID;	    //ͼ��ID
	private String bName;   //����
	private int bSum;		    //ͼ������
	private int bRemain;	    //ʣ������
	private String bAuthor;//ͼ������
	private Book book;	    //ͼ�����
	private int freq;		    //�ܽ��Ĵ���
	private Date borrowDate;    //��������
	private Date returnDate;
	private int state;//��Ǹ����Ƿ񱻸��û����û����1��ʾ�ѽ�δ����0��ʾδ������ѻ�
	//���캯��
	public Book(boolean bFlag, String bID, String bName, int bSum, int bRemain, String bAuthor,int st){
		this.bFlag = false;
		this.bID = bID;
		this.bName = bName;
		this.bSum = bSum;
		this.bRemain = bRemain;
		this.bAuthor = bAuthor;
		this.state = st;
	}
	public Book(boolean bFlag,String bID,String bName,String bAuthor,Date borrowDate){
		this.bFlag = false;
		this.bID = bID;
		this.bName = bName;
		
		this.bAuthor = bAuthor;
		this.borrowDate = borrowDate;
	}
	public Book(String bID,String bName,int sum,String bAuthor){
		
		this.bID = bID;
		this.bName = bName;
		this.bSum =sum;
		this.bRemain=sum;
		this.bAuthor = bAuthor;
		
	}
	//����ͼ�����
	public Book getBook(){
		return this.book;
	}
	
	//����ͼ��ID
	public String getbID(){
		return this.bID;
	}
	
	//��������
	public String getbName(){
		return this.bName;
	}
	
	//����ͼ������
	public int getbSum(){
		return bSum;
	}
	
	//����ʣ������
	public int getbRemain(){
		return this.bRemain;
	}
	
	//����ͼ������
	public String getbAuthor(){
		return this.bAuthor;
	}

	//���ر��
	public boolean getbFlag() {
		return bFlag;
	}

	//�޸�ͼ����
	public void setbFlag(boolean flag) {
		bFlag = flag;
	}
	
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}


	public Date getBorrowDate() {
		return borrowDate;
	}
	
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
