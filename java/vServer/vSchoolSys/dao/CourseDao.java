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
 * @选课模块:数据库操作
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
	
	private ArrayList<Object> allCourseList;//全部“课程信息”
	private ArrayList<Object> allRecordList;//全部“选课记录”
	private ArrayList<Object> userCourseList;//指定用户所选课的“课程信息”
	
	private ArrayList<Object> array;
	
		
	public CourseDao(){
		this.stmn = helper.getStmn();
		this.stmn2 = helper.getStmn2();
		this.con = helper.getCon();
	}
	
	//查询：返回所有课程信息  ArrayList<Object>
	 public  synchronized ArrayList<Object> getAllCourse(){
			
		 try{
			 //
			 String sql="Select * from tblCourse";
			 rs = stmn.executeQuery(sql);	
			 //这里会不会引发内存泄漏的问题呢？    暂且先不考虑
			 allCourseList = new ArrayList<Object>();
			 while( rs.next() ){
			
				String id = rs.getString("cID");
				String name = rs.getString("cCourseName");
				String teacher = rs.getString("cTeacher");
				int hour = rs.getInt("cHour");
				String time = rs.getString("cTime");
				int count = rs.getInt("cCount");
				int selectCounut= rs.getInt("cSelectedCount");
				//加入一条“课程信息”
				allCourseList.add(new Course(id,name,teacher,hour,time,count,selectCounut));			
			 }			
		 }catch(Exception e){
			 e.printStackTrace();
		 }	 
		 //返回课程信息
		 return allCourseList;
	 }
	 	
	//查询：通过“tblCourseList”得到该用户课表的课程编号，并从“TblCourse”获取课程信息
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
				 //这里加一个statement
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
	
	//添加：向“tblCourseList”添加一条记录
	public synchronized void insertCourseRecord(CourseRecord couRecord){
		
		try{		
			String sql = "Insert into tblCourseList(cID,uID,cGrade,cName) values('"+couRecord.getcID()+"','"+couRecord.getuID()+"','"+couRecord.getcGrade()+"','"+couRecord.getcName()+"')";
			stmn.executeUpdate(sql);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//删除：向“tblCourseList”删除一条记录
	public synchronized void deleteCourseRecord(CourseRecord couRecord) {
		try{
			String sql = "Delete from tblCourseList where cID='"+couRecord.getcID()+"' and uID='"+couRecord.getuID()+"'";
			stmn.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	//刷新：负责“tblCourse表”中已选人数“yxCount”的刷新
	public synchronized void changeCourseCount(int count, String cID) {
		try{
			String sql = "update tblCourse set cSelectedCount = '"+count+"' where cID = '"+cID+"'";   
			stmn.executeUpdate(sql);	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//查询：返回所有选课记录  ArrayList<Object>
		public synchronized ArrayList<Object> getAllCourseRecord(String uID) {

			 try{
				 //
				 String sql="Select * from tblCourseList where uID = '"+uID+"'";
				 rs = stmn.executeQuery(sql);
				//这里会不会引发内存泄漏的问题呢？    暂且先不考虑
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
	 * 借教室相关
	 */
	private ArrayList<Object> rentedRooms = new ArrayList<Object>();	//已被借的教室(好像没用到)
	private ArrayList<Object> emptyRooms = new ArrayList<Object>();		//可借教室

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
			//选出空出教室,empty为true时表示该教室可借
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

	//向tblClassroomList内添加借用教室记录
	public ArrayList<Object> rentRoom(RoomRecord roomRecord, User user,String str) {
		
		try {
			String name = user.getUName();
			String phoneNum = user.getUPhone();
			//String nameOfRoom = str;
			String nameOfRoom = roomRecord.getcRoomNumber();
			System.out.println("名称选调生迟点睡"+nameOfRoom);
			
			int cap = roomRecord.getcCapacity();
			String date = roomRecord.getcDate();
			String duration = roomRecord.getcDuration();
			stmn.executeUpdate("insert into tblClassroomList " +
					"values('" + name + "','" + phoneNum + "','" +
					nameOfRoom + "'," + cap + ",'"  + date +
					"','" + duration + "')");
			array =  new ArrayList<Object>();
			array.add("成功");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return array;
	}

}
