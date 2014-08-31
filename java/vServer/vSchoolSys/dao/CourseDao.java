/*
 * Created on 2013-9-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vSchoolSys.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vSchoolSys.common.Course;
import vSchoolSys.common.CourseRecord;
import vSchoolSys.common.RoomRecord;
import vSchoolSys.common.User;

/**
 * @author C5134
 */

/**
 * @ѡ��ģ��:���ݿ����
 */
public class CourseDao {
	
	private ResultSet rs;
	private ResultSet rs1;
	private ResultSet rs2;
	
	private Connection con;
	private Statement stmn;
	private Statement stmn2;
	private dataBaseHelper helper = new dataBaseHelper();
	
	private User user=null;
	
	private ArrayList<Object> allCourseList;//ȫ�����γ���Ϣ��
	private ArrayList<Object> allRecordList;//ȫ����ѡ�μ�¼��
	private ArrayList<Object> userCourseList;//ָ���û���ѡ�εġ��γ���Ϣ��
	
	private ArrayList<Object> array;
	
		
	public CourseDao(){
		this.stmn = helper.getStmn();
		this.stmn2 = helper.getStmn2();
		this.con = helper.getCon();
	}
	
	//��ѯ���������пγ���Ϣ  ArrayList<Object>
	 public  synchronized ArrayList<Object> getAllCourse(){
			
		 try{
			 //
			 String sql="Select * from tblCourse";
			 rs = stmn.executeQuery(sql);	
			 //����᲻�������ڴ�й©�������أ�    �����Ȳ�����
			 allCourseList = new ArrayList<Object>();
			 while( rs.next() ){
			
				String id = rs.getString("cID");
				String name = rs.getString("cCourseName");
				String teacher = rs.getString("cTeacher");
				int hour = rs.getInt("cHour");
				String time = rs.getString("cTime");
				int count = rs.getInt("cCount");
				int selectCounut= rs.getInt("cSelectedCount");
				//����һ�����γ���Ϣ��
				allCourseList.add(new Course(id,name,teacher,hour,time,count,selectCounut));			
			 }			
		 }catch(Exception e){
			 e.printStackTrace();
		 }	 
		 //���ؿγ���Ϣ
		 return allCourseList;
	 }
	 	
	//��ѯ��ͨ����tblCourseList���õ����û��α�Ŀγ̱�ţ����ӡ�TblCourse����ȡ�γ���Ϣ
	public synchronized ArrayList<Object> getUserCourse(String uid) {
			
		 try{
			 //
			 userCourseList = new ArrayList<Object>();
			 
			 String sql1="Select * from tblCourseList where uID='"+uid+"'";
			 rs1 = stmn.executeQuery(sql1);	
			 //
			 while( rs1.next()){
			
				 String cID = rs1.getString("cID");
				 
				 String sql2="Select * from tblCourse where cID='"+cID+"'";
				 //�����һ��statement
				 Statement st1 = con.createStatement();
				 rs2 = st1.executeQuery(sql2);	
				 
				 while( rs2.next() ){

					 String name = rs2.getString("cCourseName");
					 String teacher = rs2.getString("cTeacher");
					 int hour = rs2.getInt("cHour");
					 String time = rs2.getString("cTime");
					 int count = rs2.getInt("cCount");
					 int selectCounut= rs2.getInt("cSelectedCount");
					 
					 userCourseList.add(new Course(cID,name,teacher,hour,time,count,selectCounut));					
				 	}			 	
			 	}	
		 	}catch(Exception e){
			 e.printStackTrace();
		 }
		 return userCourseList;
	}
	
	//��ӣ���tblCourseList�����һ����¼
	public synchronized void insertCourseRecord(CourseRecord couRecord){
		
		try{		
			String sql = "Insert into tblCourseList(cID,uID,cGrade,cName) values('"+couRecord.getcID()+"','"+couRecord.getuID()+"','"+couRecord.getcGrade()+"','"+couRecord.getcName()+"')";
			stmn.executeUpdate(sql);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//ɾ������tblCourseList��ɾ��һ����¼
	public synchronized void deleteCourseRecord(CourseRecord couRecord) {
		try{
			String sql = "Delete from tblCourseList where cID='"+couRecord.getcID()+"' and uID='"+couRecord.getuID()+"'";
			stmn.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	//ˢ�£�����tblCourse������ѡ������yxCount����ˢ��
	public synchronized void changeCourseCount(int count, String cID) {
		try{
			String sql = "update tblCourse set cSelectedCount = '"+count+"' where cID = '"+cID+"'";   
			stmn.executeUpdate(sql);	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//��ѯ����������ѡ�μ�¼  ArrayList<Object>
		public synchronized ArrayList<Object> getAllCourseRecord(String uID) {

			 try{
				 //
				 String sql="Select * from tblCourseList where uID = '"+uID+"'";
				 rs = stmn.executeQuery(sql);
				//����᲻�������ڴ�й©�������أ�    �����Ȳ�����
				 allRecordList = new ArrayList<Object>();
				 while( rs.next() ){
				
					String cid = rs.getString("cID");
					String uid = rs.getString("uID");		
					int grade= rs.getInt("cGrade");
					String cName = rs.getString("cName");
					//System.out.println("hahhahha");
					
					allRecordList.add(new CourseRecord(cid,uid,grade,cName));			
					
				 }				 
			 }catch(Exception e){
				 e.printStackTrace();
			 }	 
			 return allRecordList;
		}

	
	
	public User getUser() {
		return user;
	}

	/**
	 * ��������
	 */
	private ArrayList<Object> rentedRooms = new ArrayList<Object>();	//�ѱ���Ľ���(����û�õ�)
	private ArrayList<Object> emptyRooms = new ArrayList<Object>();		//�ɽ����

	public ArrayList<Object> getAllOkRoom(RoomRecord roomRecord) {
		// TODO Auto-generated method stub
		boolean empty = true;
		try {
			rs = stmn.executeQuery("select * " + "from tblClassroomList where cDate = '" +
					roomRecord.getcDate() + "' and cDuration = '" +
					roomRecord.getcDuration() + "' and cMaxCapacity = " + roomRecord.getcCapacity() + "");

			rs1 = stmn2.executeQuery("select * " +
					"from tblClassroom where cMaxCapacity = " +
					roomRecord.getcCapacity() + "and cDuration = '" +
					roomRecord.getcDuration() + "'");
			//ѡ���ճ�����,emptyΪtrueʱ��ʾ�ý��ҿɽ�
			//boolean empty = true;
			while(rs1.next()) {
				while(rs.next()) {
					if(rs1.getString("cRoomNumber") == rs.getString("cRoomNumber")) {
						empty = false;
						break;
					}					
				}
				if(empty) {
					
					emptyRooms.add(rs1.getString("cRoomNumber"));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return emptyRooms;
	}

	
	public void close(){
		try {
			if (rs1 != null) 
				 rs1.close();
			if (rs2 != null) 
				 rs2.close();
			if (rs != null) 
				 rs.close();
			if(con != null)
				con.close();
			if(stmn != null)
				stmn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//��tblClassroomList����ӽ��ý��Ҽ�¼
	public ArrayList<Object> rentRoom(RoomRecord roomRecord, User user,String str) {
		
		try {
			String name = user.getUName();
			String phoneNum = user.getUPhone();
			//String nameOfRoom = str;
			String nameOfRoom = roomRecord.getcRoomNumber();
			System.out.println("����ѡ�����ٵ�˯"+nameOfRoom);
			
			int cap = roomRecord.getcCapacity();
			String date = roomRecord.getcDate();
			String duration = roomRecord.getcDuration();
			stmn.executeUpdate("insert into tblClassroomList " +
					"values('" + name + "','" + phoneNum + "','" +
					nameOfRoom + "'," + cap + ",'"  + date +
					"','" + duration + "')");
			array =  new ArrayList<Object>();
			array.add("�ɹ�");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return array;
	}

}
