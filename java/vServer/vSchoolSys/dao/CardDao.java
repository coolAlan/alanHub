package vSchoolSys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import vSchoolSys.common.ItemList;
import vSchoolSys.common.User;

public class CardDao {
	
	private ResultSet rs;
	private ResultSet rs1;
	
	private Connection con;
	private Statement stmn;
	private dataBaseHelper helper = new dataBaseHelper();
	
	private User user=null;
	private ArrayList<Object> itemList = null;
		
	public CardDao() {
		this.stmn = helper.getStmn();
		this.con = helper.getCon();
	}
	
	//用于用户登录一卡通中心时，查询验证用户信息
	public synchronized boolean  searchUser(String uID ,String pwd){
		boolean result =false ;//没有找到时返回false
 		try {
			
 			rs = stmn.executeQuery("select * from tblUser where uID = '"+uID+"' AND uPwd = '"+pwd +"'");
 			
			while(rs.next()){
				
				user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),new Double(rs.getDouble(5)).doubleValue(),new Integer(rs.getInt(6)).intValue(),rs.getString(7),new Integer(rs.getInt(8)).intValue());
				result =true;
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
	}
	
	public synchronized boolean  searchRecord(String uID ){
		boolean result=false;//查询不到结果时返回false
		try {
			int i=1;
			rs = stmn.executeQuery("select uID,ilSum,ilDate from tblItemList where uID = '"+uID+"' order by ilDate desc");
			itemList = new ArrayList<Object>();
			while(rs.next()){
				
				double temp = rs.getDouble(2);
			
				if(temp>0)
				    itemList.add(new ItemList(i,1,temp,rs.getDate(3)));
				else
					itemList.add(new ItemList(i,0,temp,rs.getDate(3)));
				i++;
				result =true;
			}
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
		
	}
	//一卡通的充值，包括修改金额和增加记录
	public synchronized boolean increaseMoney(double money,String uId,Date date,double newRemain){
		boolean result=false;//插入失败时返回false
			 
		 try {
            //获取表的最大值
		 	stmn.execute("SELECT MAX(ilOrder) from tblItemList");
		 	rs = stmn.getResultSet();
		 	int max = 0;
		 	while(rs.next()){
		 		max = rs.getInt(1);
		 	}
		 	 PreparedStatement pstmt=con.prepareStatement("insert into tblItemList values(?,?,?,?,?,?,?,?)");//values(?,?,?)和要数据库的字段对应
		 	 pstmt.setInt(1,max+1);
		 	 pstmt.setString(2,uId);
			 pstmt.setString(3,"充值");
			 pstmt.setString(4,"");
			 pstmt.setString(5,"");
			 pstmt.setString(6,"");
			 pstmt.setDouble(7,money);
			 
			 pstmt.setDate(8,new java.sql.Date(date.getTime()));
			 pstmt.executeUpdate();//执行插入;
			
			 stmn.executeUpdate("UPDATE tblUser SET uBalance = "+newRemain+" WHERE uID = '"+uId+"'");
			 result =true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
			
		
		return result;
		
	}
	//用于一卡通缴费操作，包括修改金额和添加消费记录
	public synchronized boolean decreaseMoney(double money, String uId, Date date, double newRemain,String costName) {
		boolean result=false;//插入失败时返回false

		 try {
		 	//获取表的最大值
		 	stmn.execute("SELECT MAX(ilOrder) from tblItemList");
		 	rs = stmn.getResultSet();
		 	int max = 0;
		 	while(rs.next()){
		 		max = rs.getInt(1);
		 	}
		 	 PreparedStatement pstmt=con.prepareStatement("insert into tblItemList values(?,?,?,?,?,?,?,?)");//values(?,?,?)和要数据库的字段对应
		 	 pstmt.setInt(1,max+1);
		 	 pstmt.setString(2,uId);
			 pstmt.setString(3,costName);
			 pstmt.setString(4,"");
			 pstmt.setString(5,"");
			 pstmt.setString(6,"");
			 pstmt.setDouble(7,money);
			 
			 pstmt.setDate(8,new java.sql.Date(date.getTime()));
			 pstmt.executeUpdate();//执行插入;
			
			 stmn.executeUpdate("UPDATE tblUser SET uBalance = "+newRemain+" WHERE uID = '"+uId+"'");
			 result =true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
			
		
		return result;
	}
    //修改一卡通的挂失状态
	public synchronized boolean modifyLost(int lost,String uId){
		boolean result=false;//修改失败时返回false
		 try {
			stmn.executeUpdate("UPDATE tblUser SET uCardIsLost = "+lost+" WHERE uID = '"+uId+"'");
			result = true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * @return Returns the user.
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @return the itemList
	 */
	public ArrayList<Object> getItemList() {
		return itemList;
	}

	public void close(){
		try {
			if (rs1 != null) 
				 rs1.close();
			
			if(con != null)
				con.close();
			if(stmn != null)
				stmn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
