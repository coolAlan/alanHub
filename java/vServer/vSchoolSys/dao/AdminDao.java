/*
 * Created on 2013-9-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vSchoolSys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vSchoolSys.common.Book;
import vSchoolSys.common.Course;
import vSchoolSys.common.DataGoods;
import vSchoolSys.common.FamiMember;
import vSchoolSys.common.RewOrPuniInfo;
import vSchoolSys.common.User;

/**
 * @author Alan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminDao {

	private ResultSet rs;
	private Connection con;
	private Statement stmn;
	private dataBaseHelper dbHelper = new dataBaseHelper();
	
	private User user=null;
	
	private ArrayList<Object> allCourseList;//全部“课程信息”
	private ArrayList<Object> allRecordList;//全部“选课记录”
	private ArrayList<Object> userCourseList;//指定用户所选课的“课程信息”
	
	public  AdminDao() {
		this.stmn = dbHelper.getStmn();
		this.con = dbHelper.getCon();
	}
	//查询课程基本信息
//	查询：返回所有课程信息  ArrayList<Object>
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
	 public synchronized void deleCourse(String cId) {
		try {
				stmn.executeUpdate("DELETE FROM tblCourse where cID='"+cId+"'");
				stmn.executeUpdate("DELETE FROM tblCourseList where cID='"+cId+"'");
		} catch (SQLException e) {
			e.printStackTrace();	
		}
	}
	 public synchronized void addCourse(Course course) {
		boolean result =false ;//没有找到时返回false
 		try {
 			 PreparedStatement pstmt=con.prepareStatement("insert into tblCourse values(?,?,?,?,?,?,?)");//values(?,?,?)和要数据库的字段对应
		 	 pstmt.setString(1,course.getCouID());
		 	 pstmt.setString(2,course.getCouName());
			 pstmt.setString(3,course.getCouTeacher());
			 pstmt.setInt(4,course.getCouHour());
			 pstmt.setString(5,course.getCouTime());
			 pstmt.setInt(6,course.getCouCount());
			 pstmt.setInt(7,0);	
			 pstmt.executeUpdate();//执行插入;
			 result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
   public synchronized ArrayList<Object> getAllGoods() {
	 ArrayList<Object> arrayList = new ArrayList<Object>();
	 String tbID;//商品ID
	 int tbType;//商品类型
	 int tbNumber;//商品数量
	 String tbName;//商品名称
	 double tbPrice;//商品单价
	 String tbImage;//商品图片地址
	//根据商品类型iType从数据库选择对应商品
    
     String sql_Select = "select * from tblItem where iNumber <> 0 ";//获得所有数量不为零的商品
     ResultSet rs_Select;
	 try {
		 rs_Select = stmn.executeQuery(sql_Select);
		 while(rs_Select.next()){
	    	//得到该类商品数量	        
	        //将该类商品放入ArrayList
		 	tbID =rs_Select.getString(1);
	        tbName=rs_Select.getString(2);
	        tbPrice=rs_Select.getDouble(3);
	    	tbNumber=rs_Select.getInt(4);
	    	tbType = rs_Select.getInt(5);//获取该商品的类型，放到ArrayList里
	    	tbImage=rs_Select.getString(6);
	    	//tbAdd = rs_Select.getInt(7);
//	    	将商品对象加入对应数组
	    	arrayList.add(new DataGoods(tbID,tbName,tbPrice,tbNumber,tbImage,tbType));
	    	
	        }
		} catch (SQLException e) {
		
		e.printStackTrace();
		}
		return arrayList;
    }
   public synchronized void deleGood(String gId) {
	try {
			stmn.executeUpdate("DELETE FROM tblItem where iID='"+gId+"'");
			
	} catch (SQLException e) {
		e.printStackTrace();	
	}
   }
	public synchronized void addGood(DataGoods good ) {
		boolean result =false ;//没有找到时返回false
 		try {
 			 PreparedStatement pstmt=con.prepareStatement("insert into tblItem values(?,?,?,?,?,?,?)");//values(?,?,?)和要数据库的字段对应
		 	 pstmt.setString(1,good.getGID());
		 	 pstmt.setString(2,good.getgName());
			 pstmt.setDouble(3,good.getsPrice());
			 pstmt.setInt(4,good.getNumber());
			 pstmt.setInt(5,good.getType());
			 pstmt.setString(6,good.getImageAddr());
			 pstmt.setInt(7,0);	
			 pstmt.executeUpdate();//执行插入;
			 result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	修改：修改用户的 基本信息(Age，Address）
	public synchronized void changeGoodInfo(String iID,double price,int num){
		boolean result =false ;//没有找到时返回false
 		try {
 			stmn.executeUpdate("UPDATE tblItem SET iPrice = "+price+" WHERE iID = '"+iID+"'");
 			stmn.executeUpdate("UPDATE tblItem SET iNumber = '"+num+"' WHERE iID = '"+iID+"'");		
			result =true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	查询4：用户的所有奖惩信息，存进rewOrPuniInfos
	public synchronized ArrayList<Object> getRewOrPuniInfo(){
		System.out.println("cc");
		ArrayList<Object>rewOrPuniInfos = new ArrayList<Object>();
		boolean result =false ;//没有找到时返回false
 		try {
 			
 			rs = stmn.executeQuery("select * from tblROrPInfo ");
			while(rs.next()){
				
				rewOrPuniInfos.add(new RewOrPuniInfo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
				result =true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rewOrPuniInfos;
	}
	public synchronized void delReward(int i) {
		try {
			System.out.println(i);
			stmn.executeUpdate("DELETE FROM tblROrPInfo where r_order = "+i);
			
	} catch (SQLException e) {
		e.printStackTrace();	 
	}
		
	}
	public synchronized void addReward(RewOrPuniInfo info) {
		
		boolean result =false ;//没有找到时返回false
 		try {
 			 PreparedStatement pstmt=con.prepareStatement("insert into tblROrPInfo values(?,?,?,?,?,?,?)");//values(?,?,?)和要数据库的字段对应
		 	 pstmt.setInt(1,info.getOrder());
		 	 pstmt.setString(2,info.getUID());
			 pstmt.setString(3,info.getURewOrPuni());
			 pstmt.setString(4,info.getUROrPName());
			 pstmt.setString(5,info.getUROrPTime());
			 pstmt.setString(6,info.getUReason());
			 pstmt.setString(7,info.getUPlace());	
			 pstmt.executeUpdate();//执行插入;
			 result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	查询4：用户的所有图书信息，存进books
	public synchronized ArrayList<Object> getBooks(){
		
		ArrayList<Object> books = new ArrayList<Object>();
		boolean result =false ;//没有找到时返回false
 		try {
 			
 			rs = stmn.executeQuery("select * from tblBook ");
			while(rs.next()){
				
				books.add(new Book(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(5)));
				result =true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	public synchronized void delBook(String id) {
		try {
			
			stmn.executeUpdate("DELETE FROM tblBook where bID = '"+id+"'");
			stmn.executeUpdate("DELETE FROM tblBookList where bID = '"+id+"'");
			
	} catch (SQLException e) {
		e.printStackTrace();	 
	}
 }
	
	public synchronized void addBook(Book b) {
		
		boolean result =false ;//没有找到时返回false
 		try {
 			 PreparedStatement pstmt=con.prepareStatement("insert into tblBook values(?,?,?,?,?,?)");//values(?,?,?)和要数据库的字段对应
		 	 pstmt.setString(1,b.getbID());
		 	 pstmt.setString(2,b.getbName());
			 pstmt.setInt(3,b.getbSum());
			 pstmt.setInt(4,b.getbSum());
			 pstmt.setString(5,b.getbAuthor());
			 pstmt.setInt(6,0);
			
			 pstmt.executeUpdate();//执行插入;
			 result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 public void close(){
		try {
			
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
	
	
	
	

}
