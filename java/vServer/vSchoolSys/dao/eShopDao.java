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




//操作数据库tblItem的基础类
public class eShopDao {
	//储存7个分别用于储存不同类别的商品
	public ArrayList<Object> arrayList = new ArrayList<Object>();
	public  ArrayList<ArrayList<DataGoods>> array=new ArrayList<ArrayList<DataGoods>>();
	//定义对应数据库的基本的变量
	public int tbType;//商品类型
	public int tbNumber;//商品数量
	public String tbFind;//搜索商品的关键字
	public  int count=0;//计数变量
	public String tbName;//商品名称
	public double tbPrice;//商品单价
	public String tbImage;//商品图片地址
	public int tbAdd;//是否加入购物车判断
	//商品的数量操作和标记
	
	
	private User user;
	private Connection conn;
	private Statement stmt;
	
	
	private dataBaseHelper helper = new dataBaseHelper();
	
	
	public eShopDao(){
		
//	    // 创建执行SQL的环境，连接数据库
//		
		this.stmt = helper.getStmn();
		this.conn = helper.getCon();
	}

	
	
	
	//从数据库中向6个数组加入对应商品
	public synchronized void getAllGoods() {
		
	//根据商品类型iType从数据库选择对应商品
    //String sql_Select = "select * from tblItem where iType="+tbType+" and iNumber <> 0 and iAdd ="+0;
    String sql_Select = "select * from tblItem where iNumber <> 0 ";//获得所有数量不为零的商品
    ResultSet rs_Select;
	try {
		 rs_Select = stmt.executeQuery(sql_Select);
		 while(rs_Select.next()){
	    	//得到该类商品数量
	        count = count + 1;
	        //将该类商品放入ArrayList
	        tbName=rs_Select.getString(2);
	        tbPrice=rs_Select.getDouble(3);
	    	tbNumber=rs_Select.getInt(4);
	    	tbType = rs_Select.getInt(5);//获取该商品的类型，放到ArrayList里
	    	tbImage=rs_Select.getString(6);
	    	tbAdd = rs_Select.getInt(7);
//	    	将商品对象加入对应数组
	    	arrayList.add(new DataGoods(tbName,tbPrice,tbNumber,tbImage,tbType,tbAdd));
	    	
	        }
		} catch (SQLException e) {
		
		e.printStackTrace();
		}

    }
	
	
	
//	从数据库中向搜索商品数组加入对应商品
	public synchronized void SearchGood(String find)  {
	
//  根据搜索关键字tbFind从数据库选择对应商品
    String sql_Select = "select * from tblItem where iName like '%"+find+"%'and iNumber <> 0 ";
    ResultSet rs_Select;
	try {
		rs_Select = stmt.executeQuery(sql_Select);
		while(rs_Select.next()){
	    	//得到该类商品数量
	        count = count + 1;
	        //将该类商品放入ArrayList
	        tbName=rs_Select.getString(2);
	        tbPrice=rs_Select.getDouble(3);
	    	tbNumber=rs_Select.getInt(4);
	    	tbType = rs_Select.getInt(5);//获取该商品的类型，放到ArrayList里
	    	tbImage=rs_Select.getString(6);
	    	tbAdd = rs_Select.getInt(7);
            // 将商品对象加入对应数组
	    	arrayList.add(new DataGoods(tbName,tbPrice,tbNumber,tbImage,tbType,tbAdd));
	    }
	} catch (SQLException e) {
		
		e.printStackTrace();
	}

    
   
		
    }
	
	

//商品购买数量减少时修改数据库中的库存
	public synchronized void Updatedown(String updateName,int number){
		
    String sql_Select = "select * from tblItem where iName = '"+updateName+"'and iNumber <> 0";
    ResultSet rs_Select;
	try {
		rs_Select = stmt.executeQuery(sql_Select);
    //	  得到数量改变后的库存量
		int num=0;//数量改变后的库存量
	    while(rs_Select.next()){
	    	
	    	num=rs_Select.getInt(4);
	    }
	    
	    num = num-number;
	    if(num<0)
	    	num=0;
	    	
//	  将数量改变加入数据库库存
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
	
	
	
	
	
//	定义对应数据库的基本变量
	
	
//	向数据库tblItemList中写入一行新数据
	public synchronized void addItemList(ItemList itemList){
		String uId = itemList.getIuID();//一卡通号
		String iName = itemList.getIuName();//姓名
	    String iPhone = itemList.getIphone();//联系方式
		String iAddr = itemList.getIAddr();//收获地址
	    String iRemark = itemList.getIRemark();//留言
		double iSum = itemList.getIsum();//消费总金额
		//获取系统日期
	    java.sql.Date sd;
	    itemList.getIuID();
	    java.util.Date ud= new java.util.Date();
	    sd = new java.sql.Date(ud.getTime());//数据库类型的date
	    //获取本次写入信息的iOrder
	    String sql_Select = "select * from tblItemList ";
	    ResultSet rs_Select;
		try {
			rs_Select = stmt.executeQuery(sql_Select);
			int iOrder=0;
		    while(rs_Select.next()){
		    iOrder=rs_Select.getInt(1)+1;
		    
		    }
		    //将信息写入tblItemList
		    String sql_Insert = "insert into tblItemList (ilOrder,uID,ilName,ilPhone,ilAddr,ilRemark,ilSum,ilDate) values("+iOrder+",'"+uId+"','"+iName+"','"+iPhone+"','"+iAddr+"','"+iRemark+"',"+iSum+",'"+sd+"')";
		    stmt.executeUpdate(sql_Insert);
		    System.out.println("uID"+uId);
		    ResultSet rs = stmt.executeQuery("select * from tblUser where uID = '"+uId+"'");
		    double sum = 0;
		    while(rs.next()){
			    sum =rs.getDouble(5);
			    user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),sum,new Integer(rs.getInt(6)).intValue(),rs.getString(7),new Integer(rs.getInt(8)).intValue());
		    }
		    sum = sum + iSum; //isum是负值
		    user.setRemain(sum);
		    String str ="update tblUser set uBalance = "+ sum +" where uID = '"+uId+"'";
		    stmt.executeUpdate(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	////关闭数据库连接
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
		this.tbType = tbType;  //****此处需要被赋值，待初始化的商品类型
	}


	public String getTbFind() {
		return tbFind;
	}


	public void setTbFind(String tbFind) {
		this.tbFind = tbFind;//****此处需要被赋值，搜索时的关键字
	}
}


