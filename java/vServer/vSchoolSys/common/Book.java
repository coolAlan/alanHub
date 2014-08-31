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
//图书类
public class Book implements Serializable{
	private boolean bFlag;   //用于标记图书是否被选中
	private String bID;	    //图书ID
	private String bName;   //书名
	private int bSum;		    //图书总量
	private int bRemain;	    //剩余数量
	private String bAuthor;//图书作者
	private Book book;	    //图书对象
	private int freq;		    //总借阅次数
	private Date borrowDate;    //借书日期
	private Date returnDate;
	private int state;//标记该书是否被该用户借过没还，1表示已借未还，0表示未借或借过已还
	//构造函数
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
	//返回图书对象
	public Book getBook(){
		return this.book;
	}
	
	//返回图书ID
	public String getbID(){
		return this.bID;
	}
	
	//返回书名
	public String getbName(){
		return this.bName;
	}
	
	//返回图书总量
	public int getbSum(){
		return bSum;
	}
	
	//返回剩余总量
	public int getbRemain(){
		return this.bRemain;
	}
	
	//返回图书作者
	public String getbAuthor(){
		return this.bAuthor;
	}

	//返回标记
	public boolean getbFlag() {
		return bFlag;
	}

	//修改图书标记
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
