package vSchoolSys.common;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
/**
 * 
 * @author liushaobo
 * 教室类，管理教室借用
 *
 */
public class Classroom {
	private String cSpareTime;	//教室有空时间
	private String cNameOfRoom;	//教室名
	private int cMaxCapacity;	//教室最大容量
//	private boolean cRent;		//教室是否被借
	
	public Classroom() {
		
	}
	public Classroom(String time, String name, int capacity) {
		this.cSpareTime = time;
		this.cNameOfRoom = name;
		this.cMaxCapacity = capacity;
//		this.cRent = rent;
	}
	
	//各变量set、get函数
	public void setcSpareTime(String time) {
		cSpareTime = time;
	}
	public String getcSpareTime() {
		return cSpareTime;
	}
	
	public void setcNameOfRoom(String name) {
		cNameOfRoom = name;
	}
	public String getcNameOfRoom() {
		return cNameOfRoom;
	}
	
	public void setcMaxCapacity(int capacity) {
		cMaxCapacity = capacity;
	}
	public int getcMaxCapacity() {
		return cMaxCapacity;
	}
	
/*	public void setcRent(boolean rent) {
		cRent = rent;
	}
	public boolean getcRent() {
		return cRent;
	}
*/	
}
