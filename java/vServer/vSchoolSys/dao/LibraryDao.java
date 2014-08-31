/*
 * Created on 2013-9-5
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
import java.util.Date;

import vSchoolSys.common.Book;
import vSchoolSys.common.ListRec;
import vSchoolSys.common.Record;

import vSchoolSys.common.User;

/**
 * @author Alan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LibraryDao {
	private ResultSet rs;
	private ResultSet rs1;
	private ResultSet rs2;
	private Connection con;
	private Statement stmn;
	private dataBaseHelper helper = new dataBaseHelper();
	
	private User user=null;
	
	
	private ArrayList<Object> bookList = null;   //用于存放图书搜索结果的Book对象
	private ArrayList<Object> checkList = null;  //用于存放记录查看的Record对象
	private ArrayList<Object> returnList = null; //用于存放应还书籍的Record对象
	private ArrayList<Object> sortList = null;     //用于存放进行排序的ListRec对象
	private ArrayList<Object> itemList = null;
		
	public LibraryDao()  {
		this.stmn = helper.getStmn();
		this.con = helper.getCon();
	}
//	搜索图书，按所输入书名进行模糊搜索
	public synchronized boolean searchBook(String bookName,String uId){
		bookList  = new ArrayList<Object>();
		boolean result = false ;
 		try {	
 			rs = stmn.executeQuery("select * from tblBook where bName like '%"+ bookName +"%' ");
 			
			while (rs.next()) {
				String bookID = rs.getString(1);
				if (checkEverBorrow(uId,bookID))
				bookList.add(new Book(false, bookID, rs.getString(2),
						rs.getInt(3), rs.getInt(4), rs.getString(5),1));
				else
				bookList.add(new Book(false, bookID, rs.getString(2),
							rs.getInt(3), rs.getInt(4), rs.getString(5),0));	
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;	
	}
	
	//查看对应用户借书记录
	public synchronized boolean checkRec(User user){
		checkList = new ArrayList<Object>();
		boolean result = false ;
 		try {	
 			String bookName = null;
 			String author = null;
 			Statement st1 = con.createStatement();
 			ResultSet rs2;
 			rs = stmn.executeQuery("select * from tblBookList where uID = '"+ user.getUId() +"'");
			while (rs.next()) {
				String bID = rs.getString(3);
				rs2 = st1.executeQuery("select * from tblBook where bID = '"+ bID +"' ");
				while (rs2.next()) {
		 			bookName = rs2.getString(2);
		 			
				}
				
				checkList.add(new Record(user.getUId(),bID, bookName, rs.getDate(4), rs.getDate(5) , rs.getInt(6)));
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;	
	}
	
	
	
//	返回还书记录集合
	public ArrayList<Object> getReturnList() {
		return returnList;
	}
	
	
	//返回记录列表
	public ArrayList<Object> getCheckList() {
		return checkList;
	}
	
	//返回图书列表
	public ArrayList<Object> getBookList() {
		return bookList;
	}
	
	//书籍借出后对剩余数量进行减一，并增加该书借阅总次数
	public synchronized boolean decreaseBook(Book book){
		boolean result=false;
		 try {
		 	stmn.execute("SELECT * from tblBook where bID = '"+ book.getbID() +"' "	);
		 	rs = stmn.getResultSet();
		 	int newFreq = 0;
		 	
		 	int newRemain = book.getbRemain() - 1;
		 	while(rs.next()){
		 		newFreq = rs.getInt(6) + 1 ;
		 	}
			stmn.executeUpdate("UPDATE tblBook SET bRemain = '"+ newRemain +"' where bID = '"+ book.getbID()+ "'");
			stmn.executeUpdate("UPDATE tblBook SET bFreq = '"+ newFreq +"' where bID = '"+ book.getbID()+ "'");
			result =true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	//还书后增加剩余图书数量
	public synchronized boolean increaseBook(Record record) {
		boolean result=false;
		 try {
		 	stmn.execute("select * from tblBook where bID = '"+ record.getbID() +"'");
		 	rs = stmn.getResultSet();
		 	int newRemain = 0;
		 	while(rs.next()){
		 	 newRemain = rs.getInt(4) + 1;
		 	}
			stmn.executeUpdate("UPDATE tblBook SET bRemain = "+ newRemain +" where bID = '"+ record.getbID()+ "'");
			
			stmn.execute("select * from tblBookList where bID = '"+ record.getbID() +"' AND uID = '" + record.getuID()+"'");
		 	rs2 = stmn.getResultSet();
			Date date = new Date();
			stmn.executeUpdate("UPDATE tblBookList SET blRetDate = '"+ new java.sql.Date( date.getTime()) + "', bState="+ 1+ " where bID = '"+ record.getbID()+ "' AND uID = '" + record.getuID()+"'");
			
			result =true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}

	//在数据库中插入借书记录
	public synchronized boolean insertRec(String uId, String bId, Date bDate , int bState) {
		boolean result = false;// 插入失败时返回false
		try {
			stmn.execute("SELECT MAX(blOrder) from tblBookList");
			rs = stmn.getResultSet();
			int max = 0;
			while (rs.next()) {
				max = rs.getInt(1);
			}
			PreparedStatement pstmt = con
					.prepareStatement("insert into tblBookList values(?,?,?,?,?,?)");  // values(?,?,?)和要数据库的字段对应
			pstmt.setInt(1, max + 1);
			pstmt.setString(2, uId);
			pstmt.setString(3, bId);
			pstmt.setDate(4, new java.sql.Date(bDate.getTime()));
			pstmt.setDate(5, null);
			pstmt.setInt(6, bState) ;
			pstmt.executeUpdate();// 执行插入;
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	

	//搜索未归还书籍
	public synchronized boolean searchRec(User user) {
		returnList  = new ArrayList<Object>();
		boolean result = false ;
		String bookName = null;
		String author = null;
 		try {
 			Statement st1 = con.createStatement();
 			ResultSet rs2;
 			rs = stmn.executeQuery("select * from tblBookList where uID = '"+ user.getUId() +"'AND bState = " + false  +"");
			while (rs.next()) {
				String bID = rs.getString(3);
				rs2 = st1.executeQuery("select * from tblBook where bID = '"+ bID +"' ");
				while (rs2.next()) {
		 			bookName = rs2.getString(2);
		 			author = rs2.getString(5);
				}
				returnList.add(new Record(false,user.getUId(), bID,
						bookName, author, rs.getDate(4)));
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//返回排序后的ListRec对象集合
	public ArrayList<Object> getSortList() {
		return sortList;
	}

	//图书按借出次数进行排序，并将排序后的记录存放在sortList中
	public  synchronized boolean sortList(){
		sortList  = new ArrayList<Object>();
		boolean result = false ;
 		try {
 			rs = stmn.executeQuery("select * from tblBook ORDER BY bFreq DESC");
			while (rs.next()) {
				sortList.add(new Record(rs.getString(1),rs.getString(2), rs.getString(5), rs.getInt(6)));
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	

	//检查用户user是否已借阅过图书book并尚未归还
	public  synchronized boolean checkEverBorrow(String uId , String bookID){
		boolean result = false;
 		try {
 			Statement st1 = con.createStatement();
			ResultSet rt = st1.executeQuery("select * from tblBookList where bID = '"
					+ bookID + "'AND uID = '" + uId
					+ "'AND bState = "+ 0+"");
			while (rt.next()) {
				result = true ; //有未归还图书
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//
	public void close(){
		try {
			if(con!=null)
				con.close();
			if(stmn!=null)
				stmn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
     }
	

}
