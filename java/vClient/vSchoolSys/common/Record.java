/*
 * Created on 2013-8-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vSchoolSys.common;

import java.io.Serializable;
import java.util.Calendar;
import java.text.SimpleDateFormat; 
import java.util.Date;



/**
 * @author C5137
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Record implements Serializable{
	private boolean bFlag;//是否被选中的标记，true表示选中
	private int blOrder;//序号
	private String uID;//借书人ID
	private String bID;//图书号
	private String bBookName;//图书名字
	private String Author;//作者名字
	private Date blBorDate;//借书日期
	private Date blRetDate;//还书日期
	private boolean bState;//true表示已还，false表示未还，且还书日期为空
	private int freq;//书借阅次数
	public Record(String uId,String bID,String bookName , Date blBorDate, Date blRetDate, int bState){
		this.uID = uId;
		this.bBookName = bookName;
		this.bID = bID;
		this.blBorDate = blBorDate;
		this.blRetDate = blRetDate;
		if(bState ==1)
			this.bState = true;
		else 
			this.bState = false;
	}
	
	public Record(boolean bflag,String uId,String bID,String bName,String bAuthor,Date borrowDate){
		this.bFlag = bflag;
		this.uID = uId;
	    this.Author = bAuthor;
		this.bID = bID;
		this.bBookName = bName;
		this.blBorDate = borrowDate;
		this.bState = false;
	}
	public Record(String bId,String bookName , String lAuthor, int lFreq){
		this.bID = bId;
		this.bBookName = bookName;
		this.Author = lAuthor;
		this.freq = lFreq;
	}
	public boolean getbState() {
		return bState;
	}

	public void setbState(boolean bState) {
		this.bState = bState;
	}

	public void setblOrder(int blOrder){
		this.blOrder = blOrder;
	}
	
	public String getuID(){
		return this.uID;
	}
	
	public String getbID(){
		return this.bID;
	}
	
	public Date getblBorDate(){
		return this.blBorDate;
	}
	
	public Date getblRetDate(){
		return this.blRetDate;
	}
	
	public String getbBookName() {
		
		return bBookName;
	}

	public void setbBookName(String bBookName) {
		this.bBookName = bBookName;
	}
	
	public String getAuthor() {
		return Author;
	}
	
	public void setAuthor(String author) {
		Author = author;
	}
	
	
	public boolean isBFlag() {
		return bFlag;
	}
	
	public void setBFlag(boolean flag) {
		bFlag = flag;
	}
	
	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
}
