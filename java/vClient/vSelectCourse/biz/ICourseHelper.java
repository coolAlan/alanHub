package vSelectCourse.biz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import publicData.MyData;
import vSchoolSys.common.Course;
import vSchoolSys.common.CourseRecord;
import vSchoolSys.common.Message;
import vSchoolSys.common.RoomRecord;
import vSchoolSys.common.User;


public class ICourseHelper {

	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	//private Socket socket;
	
	private User user;
	private Message message ;
	
	private boolean role = false; 
	private String[][] strCourse;
	private String[][] strUserCourse;
	private String[][] strUserGrade;
	
	public ICourseHelper() {
		
		this.toServer= MyData.getToServer();
		this.fromServer=MyData.getFromServer();
		this.user = MyData.getUser();	
	}
	
	//用于向Server发送“查询所有课程”的消息Message，代号（3，1）
	public void getAllCourse() {
		Message msg = new Message(3,1,"查询所有课程",new ArrayList<Object>(),user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {		
			e.printStackTrace();
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		}
	}
	
	//用于向Server发送“查询用户所选课程信息”的消息Message，代号（3，2）
	public void getUserCourse(String uId) {

		ArrayList<Object> data = new ArrayList<Object>();
		data.add(user);// data[0]为用户	
		
		//将待查询人的user类传入data域
		Message msg = new Message(3,2,"查询该人已选课程",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			//接受server发回来的Message，用于课表Table的显示
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
	}
	
	//用于向Server发送“选课”的消息Message，代号（3，3）
	public void addCourse(CourseRecord couRecord,int yxcount) {
		
		//设置list，用于传给Message的data
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(couRecord);//data[0] 为课程记录
		data.add(new Integer(yxcount));// data[0]为刷新的已选课程数		
		//创建Message
		Message msg = new Message(3,3,"插入选课记录",data,user);	
		try {
 			//创建并向server发送Message
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			//接受server发回来的Message，仅仅是为了不让它留在输出内存，没啥用
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {	
			e.printStackTrace();
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		}
	}
	
	//用于向Server发送"退课"的消息Message，代号(3,4)
	public void subCourse(CourseRecord couRecord,int yxcount) {
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(couRecord);//data[0]为课程记录
		data.add(new Integer(yxcount));//data[1]刷新的已选课人数
		//将待查询人的user类传入data域
		Message msg = new Message(3,4,"删除选课记录",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {	
			e.printStackTrace();
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		}
	}
	
	//通过String[][]返回所有“课程”的表格形式记录
	public String[][] showAllCourse(){
			
		this.getAllCourse();
		ArrayList<Object> courseList = (this.message).getData();
		
		int row = courseList.size();	
		strCourse = new String[row][10];
		int i = 0;
		while(i < row){
			strCourse[i]=((Course)( courseList.get(i) )).CourseToString();	
			i++;
		}
		return strCourse;
	}
	
	//通过String[][]返回该用户所有“课表”的表格形式记录
	public String[][] showUserCourse()
	{	
		this.getUserCourse(user.getUId());
		ArrayList<Object> userCourse = (this.message).getData();
		
		int row = userCourse.size();
		strUserCourse = new String[row][5];
		int i = 0;
		while(i < row)
		{
			strUserCourse[i]=((Course)( userCourse.get(i))).couRecordToString();	
			i++;
		}
		return strUserCourse;
	}
	
	//初始化用户的选课状态
	public void initialState(DefaultTableModel kbDefaultModel) {
		
		this.getUserCourse(user.getUId());
		//得到Message的data(该ID号所选的课)
		ArrayList<Object> userCourse = (this.message).getData();
		
		int size = userCourse.size();
		int row = kbDefaultModel.getRowCount();
		int j = 0;
		for(int i = 0; i < size; i++){
			for(j=0;j<row;j++){
				if(  (kbDefaultModel.getValueAt(j, 0)).equals( ((Course)(userCourse.get(i))).getCouID() )  ){
					kbDefaultModel.setValueAt("已选", j, 9);
				}
			} 
		}
	}

	public Message getMessage() {
		return message;
	}

	//判断选课时间是否冲突
	public String Selectedable(String uID,String cTime) {

		this.getUserCourse(uID);		
		ArrayList<Object> userCourse = (this.message).getData();
		
		int row = userCourse.size();
		int i = 0;		
		while(i < row)
		{		
			if(cTime.regionMatches(0,((Course)userCourse.get(i)).getCouTime(),0,7)){
				System.out.println("冲突了，别以为我不知道");
				return ((Course)userCourse.get(i)).getCouName();
			}
			i++;		
		}
		return null;
	}

	//返回成绩数组
	public String[][] getGrade() {
		
		this.getUserGrade(user.getUId());
		ArrayList<Object> userGrade = (this.message).getData();
		
		int row = userGrade.size();
		strUserGrade = new String[row][3];
		int i = 0;
		while(i < row)
		{
			strUserGrade[i]=((CourseRecord)( userGrade.get(i))).getSrtCouRecord();	
			i++;
		}
		return strUserGrade;
	}

	private void getUserGrade(String uId) {
		// TODO Auto-generated method stub
		
		//将待查询人的user类传入data域
		Message msg = new Message(3,5,"查询该人已选课程成绩",new ArrayList<Object>(),user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			//接受server发回来的Message，用于成绩Table的显示
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		
	}

	//查询可借教室
	public ArrayList<Object> checkRoom(RoomRecord record) {
		// TODO Auto-generated method stub
		
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(record);
		Message msg = new Message(3,6,"查询可借教室",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			//接受server发回来的Message，用于成绩Table的显示
			this.message = (Message)fromServer.readObject();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		
		return this.message.getData();
	}

	//借用教室
	public boolean rentRoom(User user2, RoomRecord record) {
		
		boolean success = false;
		String s = "成功";
		System.out.println("sssssssss"+record.getcRoomNumber());
		ArrayList<Object> data = new ArrayList<Object>();
		
		System.out.println("sssssssss"+record.s);
		data.add(record);
		data.add(record.getcRoomNumber());
		Message msg = new Message(3,7,"借用教室",data,user);
		
		try {
			toServer.writeObject(msg);
			toServer.flush();
			System.out.println("msg from server ");
			//接受server发回来的Message，用于成绩Table的显示
			this.message = (Message)fromServer.readObject();
			if (  s.equals( (String)(this.message.getData().get(0))))  {
				success = true;
			}
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		
		return success;
	}
	
}
