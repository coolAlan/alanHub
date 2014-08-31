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
	
	private ArrayList<Object> allCourseList;//ȫ�����γ���Ϣ��
	private ArrayList<Object> allRecordList;//ȫ����ѡ�μ�¼��
	private ArrayList<Object> userCourseList;//ָ���û���ѡ�εġ��γ���Ϣ��
	
	public  AdminDao() {
		this.stmn = dbHelper.getStmn();
		this.con = dbHelper.getCon();
	}
	//��ѯ�γ̻�����Ϣ
//	��ѯ���������пγ���Ϣ  ArrayList<Object>
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
	 public synchronized void deleCourse(String cId) {
		try {
				stmn.executeUpdate("DELETE FROM tblCourse where cID='"+cId+"'");
				stmn.executeUpdate("DELETE FROM tblCourseList where cID='"+cId+"'");
		} catch (SQLException e) {
			e.printStackTrace();	
		}
	}
	 public synchronized void addCourse(Course course) {
		boolean result =false ;//û���ҵ�ʱ����false
 		try {
 			 PreparedStatement pstmt=con.prepareStatement("insert into tblCourse values(?,?,?,?,?,?,?)");//values(?,?,?)��Ҫ���ݿ���ֶζ�Ӧ
		 	 pstmt.setString(1,course.getCouID());
		 	 pstmt.setString(2,course.getCouName());
			 pstmt.setString(3,course.getCouTeacher());
			 pstmt.setInt(4,course.getCouHour());
			 pstmt.setString(5,course.getCouTime());
			 pstmt.setInt(6,course.getCouCount());
			 pstmt.setInt(7,0);	
			 pstmt.executeUpdate();//ִ�в���;
			 result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
   public synchronized ArrayList<Object> getAllGoods() {
	 ArrayList<Object> arrayList = new ArrayList<Object>();
	 String tbID;//��ƷID
	 int tbType;//��Ʒ����
	 int tbNumber;//��Ʒ����
	 String tbName;//��Ʒ����
	 double tbPrice;//��Ʒ����
	 String tbImage;//��ƷͼƬ��ַ
	//������Ʒ����iType�����ݿ�ѡ���Ӧ��Ʒ
    
     String sql_Select = "select * from tblItem where iNumber <> 0 ";//�������������Ϊ�����Ʒ
     ResultSet rs_Select;
	 try {
		 rs_Select = stmn.executeQuery(sql_Select);
		 while(rs_Select.next()){
	    	//�õ�������Ʒ����	        
	        //��������Ʒ����ArrayList
		 	tbID =rs_Select.getString(1);
	        tbName=rs_Select.getString(2);
	        tbPrice=rs_Select.getDouble(3);
	    	tbNumber=rs_Select.getInt(4);
	    	tbType = rs_Select.getInt(5);//��ȡ����Ʒ�����ͣ��ŵ�ArrayList��
	    	tbImage=rs_Select.getString(6);
	    	//tbAdd = rs_Select.getInt(7);
//	    	����Ʒ��������Ӧ����
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
		boolean result =false ;//û���ҵ�ʱ����false
 		try {
 			 PreparedStatement pstmt=con.prepareStatement("insert into tblItem values(?,?,?,?,?,?,?)");//values(?,?,?)��Ҫ���ݿ���ֶζ�Ӧ
		 	 pstmt.setString(1,good.getGID());
		 	 pstmt.setString(2,good.getgName());
			 pstmt.setDouble(3,good.getsPrice());
			 pstmt.setInt(4,good.getNumber());
			 pstmt.setInt(5,good.getType());
			 pstmt.setString(6,good.getImageAddr());
			 pstmt.setInt(7,0);	
			 pstmt.executeUpdate();//ִ�в���;
			 result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	�޸ģ��޸��û��� ������Ϣ(Age��Address��
	public synchronized void changeGoodInfo(String iID,double price,int num){
		boolean result =false ;//û���ҵ�ʱ����false
 		try {
 			stmn.executeUpdate("UPDATE tblItem SET iPrice = "+price+" WHERE iID = '"+iID+"'");
 			stmn.executeUpdate("UPDATE tblItem SET iNumber = '"+num+"' WHERE iID = '"+iID+"'");		
			result =true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	��ѯ4���û������н�����Ϣ�����rewOrPuniInfos
	public synchronized ArrayList<Object> getRewOrPuniInfo(){
		System.out.println("cc");
		ArrayList<Object>rewOrPuniInfos = new ArrayList<Object>();
		boolean result =false ;//û���ҵ�ʱ����false
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
		
		boolean result =false ;//û���ҵ�ʱ����false
 		try {
 			 PreparedStatement pstmt=con.prepareStatement("insert into tblROrPInfo values(?,?,?,?,?,?,?)");//values(?,?,?)��Ҫ���ݿ���ֶζ�Ӧ
		 	 pstmt.setInt(1,info.getOrder());
		 	 pstmt.setString(2,info.getUID());
			 pstmt.setString(3,info.getURewOrPuni());
			 pstmt.setString(4,info.getUROrPName());
			 pstmt.setString(5,info.getUROrPTime());
			 pstmt.setString(6,info.getUReason());
			 pstmt.setString(7,info.getUPlace());	
			 pstmt.executeUpdate();//ִ�в���;
			 result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	��ѯ4���û�������ͼ����Ϣ�����books
	public synchronized ArrayList<Object> getBooks(){
		
		ArrayList<Object> books = new ArrayList<Object>();
		boolean result =false ;//û���ҵ�ʱ����false
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
		
		boolean result =false ;//û���ҵ�ʱ����false
 		try {
 			 PreparedStatement pstmt=con.prepareStatement("insert into tblBook values(?,?,?,?,?,?)");//values(?,?,?)��Ҫ���ݿ���ֶζ�Ӧ
		 	 pstmt.setString(1,b.getbID());
		 	 pstmt.setString(2,b.getbName());
			 pstmt.setInt(3,b.getbSum());
			 pstmt.setInt(4,b.getbSum());
			 pstmt.setString(5,b.getbAuthor());
			 pstmt.setInt(6,0);
			
			 pstmt.executeUpdate();//ִ�в���;
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
