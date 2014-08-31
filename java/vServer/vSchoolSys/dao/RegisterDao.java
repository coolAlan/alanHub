/*
 * Created on 2013-9-8
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

import vSchoolSys.common.User;

/**
 * @author Alan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RegisterDao {

	private ResultSet rs;
	private ResultSet rs1;
	
	private Connection con;
	private Statement stmn;
	private dataBaseHelper helper = new dataBaseHelper();
	
	private User user=null;
	private ArrayList<Object> itemList = null;
		
	public RegisterDao() {
		this.stmn = helper.getStmn();
		this.con = helper.getCon();
	}
	public synchronized boolean  searchUser(String uID ){
		boolean result =false ;
 		try {
 			
 			rs = stmn.executeQuery("select * from tblUser where uID = '"+uID+"'");
 			
			while(rs.next()){

				result =true;
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
		
	}
	
	public synchronized boolean  insertUser(User user){
		boolean result =false ;
 		try {
 			String uID = user.getUId();
 			String pwd = user.getPwd();
 			String name = user.getUName();
 			String stuNum = user.getStdNumber();
 			double uBalance = user.getRemain();
 			int uRole = user.getRole();
 			String phoneNum = user.getUPhone();
 			int uCardState = user.getLost();
 			PreparedStatement preparedStatement=con.prepareStatement("insert into tblUser(uID,uPwd,uName,uStdNumber,uBalance,uRole,uPhone,uCardIsLost)"+"values(?,?,?,?,?,?,?,?)");
 			preparedStatement.setString(1,uID);
 			preparedStatement.setString(2,pwd);
 			preparedStatement.setString(3,name);
 			preparedStatement.setString(4,stuNum);
 			preparedStatement.setDouble(5,uBalance);
 			preparedStatement.setInt(6,uRole);
 			preparedStatement.setString(7,phoneNum);
 			preparedStatement.setInt(8,uCardState);
 			preparedStatement.execute();
 			
 		
 			result = true;
 			
			}
		 catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
		
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
