package vSchoolSys.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vSchoolSys.common.BasicInfo;
import vSchoolSys.common.EduExperience;
import vSchoolSys.common.FamiMember;
import vSchoolSys.common.RewOrPuniInfo;
import vSchoolSys.common.User;

public class BasicInfoDao {

	private ResultSet rs;
	private Connection con;
	private Statement stmn;
	private dataBaseHelper dbHelper = new dataBaseHelper();
	
	private User user=null;
	
	private ArrayList<Object> basicInfo = new ArrayList<Object>();
	private ArrayList<Object> famiMembers = new ArrayList<Object>();
	private ArrayList<Object> eduExperiences = new ArrayList<Object>();
	private ArrayList<Object> rewOrPuniInfos = new ArrayList<Object>();
	
	public BasicInfoDao() {
		this.stmn = dbHelper.getStmn();
		this.con = dbHelper.getCon();
	}
	
	//查询1：查找该ID用户的基本信息，存进basicInfo
	public  synchronized ArrayList<Object> getBasicInfo(String uID){
		boolean result =false ;//没有找到时返回false
	 	try {
			System.out.println("shipeng");
			//获取该ID用户的基本信息
	 		rs = stmn.executeQuery("select * from tblUserInfo where uID = '"+uID+"'");
	 			
			while(rs.next()){
				System.out.println(" 111");
				//存储该ID用户的基本信息
				basicInfo.add( new BasicInfo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5), rs.getInt(6),rs.getInt(7),rs.getDate(8),rs.getString(9)) );
				result =true;
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return basicInfo;		
	}
		
	//查询2：用户的所有家庭成员信息，存进famiMembers
	public synchronized ArrayList<Object> getFamiMember(String uID){
		
		System.out.println("cc");
		this.famiMembers = new ArrayList<Object>();
		boolean result =false ;//没有找到时返回false
 		try {
 			
 			rs = stmn.executeQuery("select * from tblFamMember where uID = '"+uID+"'");
 			
			while(rs.next()){
				System.out.println(" liutong");
				famiMembers.add(new FamiMember(rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6), rs.getString(7),rs.getString(8)));
				result =true;
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return famiMembers;
	}


	//查询3：用户的所有教育信息，存进eduExperiences
	public synchronized ArrayList<Object> getEduExperience(String uID){
		System.out.println("cc");
		this.eduExperiences = new ArrayList<Object>();
		boolean result =false ;//没有找到时返回false
 		try {
 			rs = stmn.executeQuery("select * from tblEduExp where uID = '"+uID+"'");	
			while(rs.next()){
				System.out.println(" liutong");
				eduExperiences.add(new EduExperience(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
				result =true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eduExperiences;
	}
		
	//查询4：用户的所有奖惩信息，存进rewOrPuniInfos
	public synchronized ArrayList<Object> getRewOrPuniInfo(String uID){
		System.out.println("cc");
		this.rewOrPuniInfos = new ArrayList<Object>();
		boolean result =false ;//没有找到时返回false
 		try {
 			
 			rs = stmn.executeQuery("select * from tblROrPInfo where uID = '"+uID+"'");
			while(rs.next()){
				
				rewOrPuniInfos.add(new RewOrPuniInfo(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
				result =true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rewOrPuniInfos;
	}

	//修改：修改用户的 基本信息(Age，Address）
	public synchronized void changeBasicInfo(int uAge,String uAddress,String uID){
		boolean result =false ;//没有找到时返回false
 		try {
 			stmn.executeUpdate("UPDATE tblUserInfo SET uAge = "+uAge+" WHERE uID = '"+uID+"'");
 			stmn.executeUpdate("UPDATE tblUserInfo SET uAddress = '"+uAddress+"' WHERE uID = '"+uID+"'");		
			result =true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//修改：修改家庭成员信息
	public synchronized boolean changeFamilyInfo(FamiMember famiMember) {
		
		boolean result =false ;//没有找到时返回false
 		try {
 			
 			stmn.executeUpdate("UPDATE tblFamMember SET fAge = "+famiMember.getfAge()+" WHERE fName= '"+famiMember.getfName()+"'");
 			stmn.executeUpdate("UPDATE tblFamMember SET fRelation = '"+famiMember.getfRelation() +"' WHERE fName = '"+famiMember.getfName()+"'");
 			stmn.executeUpdate("UPDATE tblFamMember SET fWorkPlace = '"+famiMember.getfWorkPlace()+"' WHERE fName = '"+famiMember.getfName()+"'");
 			stmn.executeUpdate("UPDATE tblFamMember SET fJob = '"+famiMember.getfJob()+"' WHERE fName = '"+famiMember.getfName()+"'");
 			stmn.executeUpdate("UPDATE tblFamMember SET fPhone = '"+famiMember.getfJob()+"' WHERE fName = '"+famiMember.getfName()+"'");
		
			result =true;			
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return result;
	}	
					
	//添加：增加一条教育记录
	public synchronized void addEducationInfo(EduExperience eduExperience) {
		boolean result =false ;//没有找到时返回false
 		try {
 			stmn.executeUpdate("INSERT INTO tblEduExp  (eduStartTime,eduEndTime,school,degree,uID) values ('"+eduExperience.getEduStartTime()+" ','"+eduExperience.getEduEndTime()+" ','"+eduExperience.getSchool()+"','"+eduExperience.getDegree()+"', '"+eduExperience.getuID()+"')");
				result =true;	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//添加：增加一条家庭成员记录
	public synchronized void addFamilyInfo(FamiMember famiMember) {
		boolean result =false ;//没有找到时返回false
 		try {
			stmn.executeUpdate("INSERT INTO tblFamMember  (uID,fName,fAge,fRelation,fWorkPlace,fJob,fPhone) values ('"+famiMember.getuID()+"','"+famiMember.getfName()+" ','"+famiMember.getfAge()+" ','"+famiMember.getfRelation()+"','"+famiMember.getfWorkPlace()+"', '"+famiMember.getfJob()+"','"+famiMember.getfPhone()+"')");
				result =true;		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//删除：删除一条教育记录
	public synchronized void deleEducationInfo(String school) {
		try {
				stmn.executeUpdate("DELETE FROM tblEduExp where school='"+school+"'");
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
