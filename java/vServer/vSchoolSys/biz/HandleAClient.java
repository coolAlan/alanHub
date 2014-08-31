/*
 * Created on 2013-8-21
 * ����ͻ�����ķ�����
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
			//whileѭ������������Ϣ
			while(true){
				Object obj = inputFromClient.readObject();
				if (obj != null) {
					Message msg = (Message) obj;

					int msgId = msg.getMId();

					//�յ�һ�����Ϊ0��Message�����ر�socket
					if (msgId == 0) {
						SrvData srvData= new SrvData(0);
						
						if(msg.getSender().getUId().equals(""))break;
						else{
						System.out.println("srv ����"+msg.getSender().getUId());
						srvData.exitClient(msg.getSender().getUId());
						socket.close();
						break;
						}
					}
					
					//System.out.println(msg.getSender().getUId());
					//����Message���ѡ���Ӧ�Ĳ���
					this.chooseModel(msgId, msg);
				} else {
                    //û�н��ܵ�message��socket�ر�
					socket.close();
					JOptionPane.showMessageDialog(null, "sokect�ѹر�");
					break;//ͬʱ����ѭ��
					
				}
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
		
	}
	
	//����Message������sendToClient()�����úõ�Message���͵��ͻ���Client
	public void chooseModel(int mId, Message msg){
		 
		int type = msg.getType(); 
		
		switch(mId){
		case 1:
			//1�������û�����ģ�飬������������
			vRegisterSrv registerSrv = new vRegisterSrv(type,msg);
			sendToClient(registerSrv .getMsg());
			System.out.println("message ok");
			break;
			
		case 2:
			//2������ѧ������ϵͳ����������ͮ
			
			//����ѧ������ģ��Ĳ�������Type ��  message����Ҫ�ṩuser��data��
			System.out.println("��ʼ��");
			System.out.println(msg.getSender().getUId());
			System.out.println("����");
			vBasicIfoSrv basicInfoSrv= new vBasicIfoSrv(type,msg);
			//��courseSrv�õ�������Ϣ��Message�������ݸ�Client
			this.sendToClient(basicInfoSrv.getMsg());
			System.out.println("message ok");
			break;
		case 3:
			//3������ѡ��ϵͳ�������������
			
			vCourseSrv courseSrv= new vCourseSrv(type,msg);
			//��courseSrv�õ�������Ϣ��Message
			this.sendToClient(courseSrv.getMsg());
			System.out.println("message ok");
			break;
		case 4:
			//4������ͼ�����ϵͳ��������ʯ�B
			vLibrarySrv lib = new vLibrarySrv(type,msg);
			sendToClient(lib.getMsg());
			System.out.println("message ok");
			break;
		case 5:
			// 5�������̵�ϵͳ��������������
			veShopSrv eshop = new veShopSrv(type,msg);
			sendToClient(eshop.getMsg());
			System.out.println("message ok");
			break;
		case 6:
			// 6������һ��ͨ���ģ�������ʱ��
			vCardSrv cardSrv = new vCardSrv(type,msg);
			sendToClient(cardSrv.getMsg());
			System.out.println("message ok");
			break;
		case 7:
			//7��ѧ������ϵͳ�����������ٲ�
			break;
		
		case 8:
			vAdminSrv admin = new vAdminSrv(type,msg);
			sendToClient(admin.getMsg());
			System.out.println("message ok");
		default:
			break;
		}
	}
	
	//Server������Ϣ���ͻ��� Client
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
