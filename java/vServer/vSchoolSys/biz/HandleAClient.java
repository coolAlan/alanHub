/*
 * Created on 2013-8-21
 * 处理客户请求的服务类
 */
package vSchoolSys.biz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import publicData.SrvData;
import vSchoolSys.common.Message;
import vServerModelSrv.biz.vAdminSrv;
import vServerModelSrv.biz.vBasicIfoSrv;
import vServerModelSrv.biz.vCardSrv;
import vServerModelSrv.biz.vCourseSrv;
import vServerModelSrv.biz.vLibrarySrv;
import vServerModelSrv.biz.vRegisterSrv;
import vServerModelSrv.biz.veShopSrv;

public class HandleAClient implements Runnable{

	private Socket socket;
	private ObjectOutputStream outputToClient;
	private ObjectInputStream inputFromClient;

	public HandleAClient(Socket socket) {
		this.socket = socket;	
	}
	
	public void run() {
		try {
			inputFromClient= new ObjectInputStream(socket.getInputStream());
			outputToClient = new ObjectOutputStream(socket.getOutputStream());
			//while循环反复接受消息
			while(true){
				Object obj = inputFromClient.readObject();
				if (obj != null) {
					Message msg = (Message) obj;

					int msgId = msg.getMId();

					//收到一个编号为0的Message，则会关闭socket
					if (msgId == 0) {
						SrvData srvData= new SrvData(0);
						
						if(msg.getSender().getUId().equals(""))break;
						else{
						System.out.println("srv 下线"+msg.getSender().getUId());
						srvData.exitClient(msg.getSender().getUId());
						socket.close();
						break;
						}
					}
					
					//System.out.println(msg.getSender().getUId());
					//根据Message编号选择对应的操作
					this.chooseModel(msgId, msg);
				} else {
                    //没有接受到message则socket关闭
					socket.close();
					JOptionPane.showMessageDialog(null, "sokect已关闭");
					break;//同时跳出循环
					
				}
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
		
	}
	
	//设置Message并调用sendToClient()将设置好的Message发送到客户端Client
	public void chooseModel(int mId, Message msg){
		 
		int type = msg.getType(); 
		
		switch(mId){
		case 1:
			//1：代表用户管理模块，负责人周滢滢
			vRegisterSrv registerSrv = new vRegisterSrv(type,msg);
			sendToClient(registerSrv .getMsg());
			System.out.println("message ok");
			break;
			
		case 2:
			//2：代表学籍管理系统，负责人刘彤
			
			//传入学籍管理模块的操作类型Type 和  message（主要提供user和data）
			System.out.println("开始了");
			System.out.println(msg.getSender().getUId());
			System.out.println("有吗");
			vBasicIfoSrv basicInfoSrv= new vBasicIfoSrv(type,msg);
			//从courseSrv得到完整信息的Message，并传递给Client
			this.sendToClient(basicInfoSrv.getMsg());
			System.out.println("message ok");
			break;
		case 3:
			//3：代表选课系统，负责人彭成伦
			
			vCourseSrv courseSrv= new vCourseSrv(type,msg);
			//从courseSrv得到完整信息的Message
			this.sendToClient(courseSrv.getMsg());
			System.out.println("message ok");
			break;
		case 4:
			//4：代表图书管理系统，负责人石珺
			vLibrarySrv lib = new vLibrarySrv(type,msg);
			sendToClient(lib.getMsg());
			System.out.println("message ok");
			break;
		case 5:
			// 5：代表商店系统，负责人赵子琦
			veShopSrv eshop = new veShopSrv(type,msg);
			sendToClient(eshop.getMsg());
			System.out.println("message ok");
			break;
		case 6:
			// 6：代表一卡通中心，负责人时鹏
			vCardSrv cardSrv = new vCardSrv(type,msg);
			sendToClient(cardSrv.getMsg());
			System.out.println("message ok");
			break;
		case 7:
			//7：学生在线系统，负责人刘少博
			break;
		
		case 8:
			vAdminSrv admin = new vAdminSrv(type,msg);
			sendToClient(admin.getMsg());
			System.out.println("message ok");
		default:
			break;
		}
	}
	
	//Server发送信息到客户端 Client
	public void sendToClient(Message msg){
		try {
			System.out.println("send ok");
			outputToClient.writeObject(msg);
			System.out.println(msg.getMName());
			outputToClient.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @return Returns the inputFromClient.
	 */
	public ObjectInputStream getInputFromClient() {
		return inputFromClient;
	}
	/**
	 * @param inputFromClient The inputFromClient to set.
	 */
	public void setInputFromClient(ObjectInputStream inputFromClient) {
		this.inputFromClient = inputFromClient;
	}
	/**
	 * @return Returns the outputToClient.
	 */
	public ObjectOutputStream getOutputToClient() {
		return outputToClient;
	}
	/**
	 * @param outputToClient The outputToClient to set.
	 */
	public void setOutputToClient(ObjectOutputStream outputToClient) {
		this.outputToClient = outputToClient;
	}

	
}
