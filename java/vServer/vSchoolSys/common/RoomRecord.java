package vSchoolSys.common;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;

public class RoomRecord implements Serializable{
	
	private String cRoomNumber;		//教室名称
	private String cDuration;		//借用时段
	private String cDate;			//借用日期
	private int cCapacity;			//借用教室容量
	
	public String s;
	public RoomRecord() {
		
	}
	public RoomRecord(int cap, String date, String duration) {
		this.cDate = date;
		this.cDuration = duration;
		this.cCapacity = cap;
	}
	public RoomRecord(String name, int cap, String date, String duration) {
		this.cDate = date;
		this.cDuration = duration;
		this.cCapacity = cap;
		this.cRoomNumber = name;
	}
	//各set、get函数
	public void setcDate(String date) {
		cDate = date;
	}
	public String getcDate() {
		return cDate;
	}
	
	public void setcDuration(String duration) {
		cDuration = duration;
	}
	public String getcDuration() {
		return cDuration;
	}
	
	public void setcCapacity(int cap) {
		cCapacity = cap;
	}
	public int getcCapacity() {
		return cCapacity;
	}
	
	public void setcRoomNumber(String num) {
		cRoomNumber = num;
	}
	public String getcRoomNumber() {
		return cRoomNumber;
	}
	/**
	 * @param args
	 */
	

}
