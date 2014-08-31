package vSchoolSys.common;
import java.awt.*;
import java.io.Serializable;
import java.util.*;

/**
 * 
 * @author liushaobo
 * 成绩信息类，存储各成绩信息
 *
 */
public class GradeInfo {
	private String eCourseName;	//课程名称
	private int eGrade;	//课程成绩
	private double eGPA;	//课程绩点
	
	public GradeInfo() {
		
	}
	
	public GradeInfo(String name, int grade, double gpa) {
		this.eCourseName = name;
		this.eGrade = grade;
		this.eGPA = gpa;
	}
	
	//各变量set、get函数
	public void seteCourseName(String name) {
		eCourseName = name;
	}
	public String geteCourseName() {
		return eCourseName;
	}
	
	public void seteGrade(int grade) {
		eGrade = grade;
	}
	public int geteGrade() {
		return eGrade;
	}
	
	public void seteGPA(float gpa) {
		eGPA = gpa;
	}
	public double geteGPA() {
		return eGPA;
	}

	/**
	 * @param args
	 */
/**	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
*/
}
