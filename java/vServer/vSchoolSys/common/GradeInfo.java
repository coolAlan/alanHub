package vSchoolSys.common;
import java.awt.*;
import java.io.Serializable;
import java.util.*;

/**
 * 
 * @author liushaobo
 * �ɼ���Ϣ�࣬�洢���ɼ���Ϣ
 *
 */
public class GradeInfo {
	private String eCourseName;	//�γ�����
	private int eGrade;	//�γ̳ɼ�
	private double eGPA;	//�γ̼���
	
	public GradeInfo() {
		
	}
	
	public GradeInfo(String name, int grade, double gpa) {
		this.eCourseName = name;
		this.eGrade = grade;
		this.eGPA = gpa;
	}
	
	//������set��get����
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
