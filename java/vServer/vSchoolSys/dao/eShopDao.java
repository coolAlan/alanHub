package vSchoolSys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vSchoolSys.common.DataGoods;
import vSchoolSys.common.ItemList;
import vSchoolSys.common.User;




//�������ݿ�tblItem�Ļ�����
public class eShopDao {
	//����7���ֱ����ڴ��治ͬ������Ʒ
	public ArrayList<Object> arrayList = new ArrayList<Object>();
	public  ArrayList<ArrayList<DataGoods>> array=new ArrayList<ArrayList<DataGoods>>();
	//�����Ӧ���ݿ�Ļ����ı���
	public int tbType;//��Ʒ����
	public int tbNumber;//��Ʒ����
	public String tbFind;//������Ʒ�Ĺؼ���
	public  int count=0;//��������
	public String tbName;//��Ʒ����
	public double tbPrice;//��Ʒ����
	public String tbImage;//��ƷͼƬ��ַ
	public int tbAdd;//�Ƿ���빺�ﳵ�ж�
	//��Ʒ�����������ͱ��
	
	
	private User user;
	private Connection conn;
	private Statement stmt;
	
	
	private dataBaseHelper helper = new dataBaseHelper();
	
	
	public eShopDao(){
		
//	    // ����ִ��SQL�Ļ������������ݿ�
//		
		this.stmt = helper.getStmn();
		this.conn = helper.getCon();
	}

	
	
	
	//�����ݿ�����6����������Ӧ��Ʒ
	public synchronized void getAllGoods() {
		
	//������Ʒ����iType�����ݿ�ѡ���Ӧ��Ʒ
    //String sql_Select = "select * from tblItem where iType="+tbType+" and iNumber <> 0 and iAdd ="+0;
    String sql_Select = "select * from tblItem where iNumber <> 0 ";//�������������Ϊ�����Ʒ
    ResultSet rs_Select;
	try {
		 rs_Select = stmt.executeQuery(sql_Select);
		 while(rs_Select.next()){
	    	//�õ�������Ʒ����
	        count = count + 1;
	        //��������Ʒ����ArrayList
	        tbName=rs_Select.getString(2);
	        tbPrice=rs_Select.getDouble(3);
	    	tbNumber=rs_Select.getInt(4);
	    	tbType = rs_Select.getInt(5);//��ȡ����Ʒ�����ͣ��ŵ�ArrayList��
	    	tbImage=rs_Select.getString(6);
	    	tbAdd = rs_Select.getInt(7);
//	    	����Ʒ��������Ӧ����
	    	arrayList.add(new DataGoods(tbName,tbPrice,tbNumber,tbImage,tbType,tbAdd));
	    	
	        }
		} catch (SQLException e) {
		
		e.printStackTrace();
		}

    }
	
	
	
//	�����ݿ�����������Ʒ��������Ӧ��Ʒ
	public synchronized void SearchGood(String find)  {
	
//  ���������ؼ���tbFind�����ݿ�ѡ���Ӧ��Ʒ
    String sql_Select = "select * from tblItem where iName like '%"+find+"%'and iNumber <> 0 ";
    ResultSet rs_Select;
	try {
		rs_Select = stmt.executeQuery(sql_Select);
		while(rs_Select.next()){
	    	//�õ�������Ʒ����
	        count = count + 1;
	        //��������Ʒ����ArrayList
	        tbName=rs_Select.getString(2);
	        tbPrice=rs_Select.getDouble(3);
	    	tbNumber=rs_Select.getInt(4);
	    	tbType = rs_Select.getInt(5);//��ȡ����Ʒ�����ͣ��ŵ�ArrayList��
	    	tbImage=rs_Select.getString(6);
	    	tbAdd = rs_Select.getInt(7);
            // ����Ʒ��������Ӧ����
	    	arrayList.add(new DataGoods(tbName,tbPrice,tbNumber,tbImage,tbType,tbAdd));
	    }
	} catch (SQLException e) {
		
		e.printStackTrace();
	}

    
   
		
    }
	
	

//��Ʒ������������ʱ�޸����ݿ��еĿ��
	public synchronized void Updatedown(String updateName,int number){
		
    String sql_Select = "select * from tblItem where iName = '"+updateName+"'and iNumber <> 0";
    ResultSet rs_Select;
	try {
		rs_Select = stmt.executeQuery(sql_Select);
    //	  �õ������ı��Ŀ����
		int num=0;//�����ı��Ŀ����
	    while(rs_Select.next()){
	    	
	    	num=rs_Select.getInt(4);
	    }
	    
	    num = num-number;
	    if(num<0)
	    	num=0;
	    	
//	  �������ı�������ݿ���
	    String sql_Update =
	    	"update tblItem set iNumber = "+num+" where iName = '"+updateName+"'";
	    
	    stmt.executeUpdate(sql_Update);
	    
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
    


    
    }
	


	/**
	 * @return Returns the user.
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * @return Returns the arrayList.
	 */
	public ArrayList<Object> getArrayList() {
		return arrayList;
	}
	
	
	
	
	
//	�����Ӧ���ݿ�Ļ�������
	
	
//	�����ݿ�tblItemList��д��һ��������
	public synchronized void addItemList(ItemList itemList){
		String uId = itemList.getIuID();//һ��ͨ��
		String iName = itemList.getIuName();//����
	    String iPhone = itemList.getIphone();//��ϵ��ʽ
		String iAddr = itemList.getIAddr();//�ջ��ַ
	    String iRemark = itemList.getIRemark();//����
		double iSum = itemList.getIsum();//�����ܽ��
		//��ȡϵͳ����
	    java.sql.Date sd;
	    itemList.getIuID();
	    java.util.Date ud= new java.util.Date();
	    sd = new java.sql.Date(ud.getTime());//���ݿ����͵�date
	    //��ȡ����д����Ϣ��iOrder
	    String sql_Select = "select * from tblItemList ";
	    ResultSet rs_Select;
		try {
			rs_Select = stmt.executeQuery(sql_Select);
			int iOrder=0;
		    while(rs_Select.next()){
		    iOrder=rs_Select.getInt(1)+1;
		    
		    }
		    //����Ϣд��tblItemList
		    String sql_Insert = "insert into tblItemList (ilOrder,uID,ilName,ilPhone,ilAddr,ilRemark,ilSum,ilDate) values("+iOrder+",'"+uId+"','"+iName+"','"+iPhone+"','"+iAddr+"','"+iRemark+"',"+iSum+",'"+sd+"')";
		    stmt.executeUpdate(sql_Insert);
		    System.out.println("uID"+uId);
		    ResultSet rs = stmt.executeQuery("select * from tblUser where uID = '"+uId+"'");
		    double sum = 0;
		    while(rs.next()){
			    sum =rs.getDouble(5);
			    user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),sum,new Integer(rs.getInt(6)).intValue(),rs.getString(7),new Integer(rs.getInt(8)).intValue());
		    }
		    sum = sum + iSum; //isum�Ǹ�ֵ
		    user.setRemain(sum);
		    String str ="update tblUser set uBalance = "+ sum +" where uID = '"+uId+"'";
		    stmt.executeUpdate(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	////�ر����ݿ�����
	public void close(){
		
			try {
				if(stmt!=null)
				  stmt.close();
				if(conn!=null)
				  conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	
	
	
    //setter and getter
	public int getTbType() {
		return tbType;
	}


	public void setTbType(int tbType) {
		this.tbType = tbType;  //****�˴���Ҫ����ֵ������ʼ������Ʒ����
	}


	public String getTbFind() {
		return tbFind;
	}


	public void setTbFind(String tbFind) {
		this.tbFind = tbFind;//****�˴���Ҫ����ֵ������ʱ�Ĺؼ���
	}
}


