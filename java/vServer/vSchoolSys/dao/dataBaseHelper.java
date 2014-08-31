/*
 * Created on 2013-8-23
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vSchoolSys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import vSchoolSys.common.Course;
import vSchoolSys.common.CourseRecord;
import vSchoolSys.common.ItemList;
import vSchoolSys.common.User;

/**
 * @author shhipeng
 * 处理数据库的帮助类
 */
public class dataBaseHelper {

	private ResultSet rs;
	private ResultSet rs1;
	private ResultSet rs2;
	
	private Connection con;
	private Statement stmn;
	private Statement stmn2;
	
	
	//连接数据库
	public dataBaseHelper()  {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:SchoolDB","","");
			//String path = System.getProperty("user.dir");
          //  String DBConnstr = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="+path+"\\vCampus.mdb";
           // con = DriverManager.getConnection(DBConnstr);
            System.out.println("连接数据成功");
			
			stmn =con.createStatement();	
			stmn2 =con.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void close(){
		
			try {
				if (rs1 != null) {
					 rs1.close();
				    }
				if (rs2 != null) {
					 rs2.close();
				 }
				if (rs != null) {
				 rs.close();
			     }
				if(con!=null)
					con.close();
				if(stmn!=null)
					stmn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		
	}
	
	public Connection getCon() {
		return con;
	}

	public Statement getStmn() {
		return stmn;
	}

	public Statement getStmn2() {
		
		return stmn2;
	}

	
	
	

}
