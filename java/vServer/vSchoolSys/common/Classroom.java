package vSchoolSys.common;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
/**
 * 
 * @author liushaobo
 * �����࣬������ҽ���
 *
 */
public class Classroom {
	private String cSpareTime;	//�����п�ʱ��
	private String cNameOfRoom;	//������
	private int cMaxCapacity;	//�����������
//	private boolean cRent;		//�����Ƿ񱻽�
	
	public Classroom() {
		
	}
	public Classroom(String time, String name, int capacity) {
		this.cSpareTime = time;
		this.cNameOfRoom = name;
		this.cMaxCapacity = capacity;
//		this.cRent = rent;
	}
	
	//������set��get����
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
