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
	
	//�����û���¼һ��ͨ����ʱ����ѯ��֤�û���Ϣ
	public synchronized boolean  searchUser(String uID ,String pwd){
		boolean result =false ;//û���ҵ�ʱ����false
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
		boolean result=false;//��ѯ�������ʱ����false
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
	//һ��ͨ�ĳ�ֵ�������޸Ľ������Ӽ�¼
	public synchronized boolean increaseMoney(double money,String uId,Date date,double newRemain){
		boolean result=false;//����ʧ��ʱ����false
			 
		 try {
            //��ȡ������ֵ
		 	stmn.execute("SELECT MAX(ilOrder) from tblItemList");
		 	rs = stmn.getResultSet();
		 	int max = 0;
		 	while(rs.next()){
		 		max = rs.getInt(1);
		 	}
		 	 PreparedStatement pstmt=con.prepareStatement("insert into tblItemList values(?,?,?,?,?,?,?,?)");//values(?,?,?)��Ҫ���ݿ���ֶζ�Ӧ
		 	 pstmt.setInt(1,max+1);
		 	 pstmt.setString(2,uId);
			 pstmt.setString(3,"��ֵ");
			 pstmt.setString(4,"");
			 pstmt.setString(5,"");
			 pstmt.setString(6,"");
			 pstmt.setDouble(7,money);
			 
			 pstmt.setDate(8,new java.sql.Date(date.getTime()));
			 pstmt.executeUpdate();//ִ�в���;
			
			 stmn.executeUpdate("UPDATE tblUser SET uBalance = "+newRemain+" WHERE uID = '"+uId+"'");
			 result =true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
			
		
		return result;
		
	}
	//����һ��ͨ�ɷѲ����������޸Ľ���������Ѽ�¼
	public synchronized boolean decreaseMoney(double money, String uId, Date date, double newRemain,String costName) {
		boolean result=false;//����ʧ��ʱ����false

		 try {
		 	//��ȡ������ֵ
		 	stmn.execute("SELECT MAX(ilOrder) from tblItemList");
		 	rs = stmn.getResultSet();
		 	int max = 0;
		 	while(rs.next()){
		 		max = rs.getInt(1);
		 	}
		 	 PreparedStatement pstmt=con.prepareStatement("insert into tblItemList values(?,?,?,?,?,?,?,?)");//values(?,?,?)��Ҫ���ݿ���ֶζ�Ӧ
		 	 pstmt.setInt(1,max+1);
		 	 pstmt.setString(2,uId);
			 pstmt.setString(3,costName);
			 pstmt.setString(4,"");
			 pstmt.setString(5,"");
			 pstmt.setString(6,"");
			 pstmt.setDouble(7,money);
			 
			 pstmt.setDate(8,new java.sql.Date(date.getTime()));
			 pstmt.executeUpdate();//ִ�в���;
			
			 stmn.executeUpdate("UPDATE tblUser SET uBalance = "+newRemain+" WHERE uID = '"+uId+"'");
			 result =true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
			
		
		return result;
	}
    //�޸�һ��ͨ�Ĺ�ʧ״̬
	public synchronized boolean modifyLost(int lost,String uId){
		boolean result=false;//�޸�ʧ��ʱ����false
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
