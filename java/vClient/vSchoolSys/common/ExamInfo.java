package vSchoolSys.common;
import java.awt.*;
import java.io.Serializable;
import java.util.*;

/**
 * 
 * @author liushaobo
 * ������Ϣ�࣬�洢���ƿ�����Ϣ
 *
 */

public class ExamInfo implements Serializable {
	private String eCourseID;
	private String eCourseName;
	private String eTime;
	private String eRoom;
	private int eDuration;	//����ʱ��

	public ExamInfo() {
		
	}
	
	public ExamInfo(String id, String name, String time, String room, int duration) {
		this.eCourseID = id;
		this.eCourseName = name;
		this.eTime = time;
		this.eRoom = room;
		this.eDuration = duration;
	}
	
	/**
	 * @param args
	 */
	
	//������set��get����
	public void seteCourseID(String id) {
		this.eCourseID = id;
	}
	public String geteCourseID() {
		return eCourseID;
	}
	
	public void seteCourseName(String name) {
		this.eCourseName = name;
	}
	public String geteCourseName() {
		return eCourseName;
	}
	
	public void seteTime(String time) {
		this.eTime = time;
	}
	public String geteTime() {
		return eTime;
	}
	
	public void seteRoom(String room) {
		this.eRoom = room;
	}
	public String geteRoom() {
		return eRoom;
	}
	
	public void seteDuration(int duration) {
		this.eDuration = duration;
	}
	public int geteDuration() {
		return eDuration;
	}
	
}