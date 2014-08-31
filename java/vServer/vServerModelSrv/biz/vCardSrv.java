/*
 * Created on 2013-8-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vServerModelSrv.biz;

import java.util.ArrayList;

import publicData.SrvData;
import vSchoolSys.common.ItemList;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;
import vSchoolSys.dao.CardDao;

/**
 * @author shipeng
 * ���������й�һ��ͨ���ĵ�ҵ���߼�
 */
public class vCardSrv {

	private Message msg;
	//���ڴ���һ��ͨ����Ϣ��type�ǲ������
	public vCardSrv(int type,Message msg) {
		User user;
		String userID;
		//dataBaseHelper dbh =new  dataBaseHelper();
		CardDao cardDao =new CardDao();
		ArrayList<Object> arrayList;
		ArrayList<Object> alist;
		ItemList itemlist;
		boolean success;
		switch(type){
		case 1:
			//һ��ͨ���ĵĵ�¼����
			user = msg.getSender();
			userID = user.getUId();//��ȡһ��ͨ��
			String password = user.getPwd();//��ȡ����
			
			//if(!dbh.searchUser(userID,password))
			//	JOptionPane.showMessageDialog(null,"�û������������","sorry",JOptionPane.ERROR);
			System.out.println(userID+"  "+password);
			success = cardDao.searchUser(userID,password);
			System.out.println(success);
			arrayList = new ArrayList<Object>();
			SrvData srvData= new SrvData(0);
			if(success){
				System.out.println("�˺�"+userID);
				
				if(!srvData.findOnline(userID)){//����¼�˺�δ��¼������¼�ɹ�
					arrayList.add(cardDao.getUser());
					this.msg =new Message(6,1,"һ��ͨ���ĵ�¼",arrayList,user);//���ؿͻ��˵�message
					srvData.addClient(userID,"127.0.0.1");
					
				}
				else{
					arrayList.clear();
					this.msg =new Message(6,1,"�ظ���¼",arrayList,user);//���ؿͻ��˵�message
				}
			}
			else{
				arrayList.clear();
				this.msg =new Message(6,1,"һ��ͨ���ĵ�¼",arrayList,user);//���ؿͻ��˵�message
			}
			break;
		case 2:
			//һ��ͨ�Ĳ�ѯ���Ѽ�¼����
			user = msg.getSender();
			userID = user.getUId();//��ȡһ��ͨ��
			cardDao.searchRecord(userID);
			
			arrayList =cardDao.getItemList();
			this.msg =new Message(6,2,"һ��ͨ�������Ѽ�¼",arrayList,user);//���ؿͻ��˵�message
			
			break;
		case 3:
			//һ��ͨ���ĵĳ�ֵ����
			user = msg.getSender();
			userID = user.getUId();//��ȡһ��ͨ��
			double newRemain = user.getRemain();
			
			arrayList = msg.getData();
			itemlist =(ItemList)arrayList.get(0);
			user.setRemain(itemlist.getIsum()+newRemain);//�޸ķ���������
			success = cardDao.increaseMoney(itemlist.getIsum(),userID,itemlist.getDate(),itemlist.getIsum()+newRemain);
			alist = new ArrayList<Object>();
			if(success)
			    alist.add(new Integer(1));//��ֵ�ɹ�
			else 
				alist.add(new Integer(0));//��ֵʧ��
			this.msg =new Message(6,3,"һ��ͨ���ĳ�ֵ",alist,user);//���ؿͻ��˵�message
			break;
		case 4:
			//һ��ͨ���ĵĽɷѲ���
			user = msg.getSender();
			userID = user.getUId();//��ȡһ��ͨ��
			double remain = user.getRemain();
			arrayList = msg.getData();
			itemlist =(ItemList)arrayList.get(0);
			user.setRemain(itemlist.getIsum()+remain);//�޸ķ���������
			success =cardDao.decreaseMoney(itemlist.getIsum(),userID,itemlist.getDate(),itemlist.getIsum()+remain,itemlist.getConsumePwd());
			alist = new ArrayList<Object>();
			if(success)
			    alist.add(new Integer(1));//�ɷѳɹ�
			else 
				alist.add(new Integer(0));//�ɷ�ʧ��
			this.msg =new Message(6,4,"һ��ͨ���Ľɷ�",alist,user);//���ؿͻ��˵�message
			break;
		case 5:
			//һ��ͨ���ĵĹ�ʧ����
			user = msg.getSender();
			userID = user.getUId();//��ȡһ��ͨ��
			
			int u = (Integer)msg.getData().get(0);
			
			success = cardDao.modifyLost(u,userID);
            //�޸ķ��������ԣ��˴����޸ĵ�ԭ�����ڿͻ������޸�
			user.setLost(u);
			alist = new ArrayList<Object>();
			if(success)
			    alist.add(new Integer(1));//�޸ĳɹ�
			else 
				alist.add(new Integer(0));//�޸�ʧ��
			this.msg =new Message(6,5,"һ��ͨ���Ĺ�ʧ",alist,user);//���ؿͻ��˵�message
			break;
		}
		cardDao.close();
	}

	/**
	 * @return Returns the msg.
	 */
	public Message getMsg() {
		return msg;
	}
	/**
	 * @param msg The msg to set.
	 */
	public void setMsg(Message msg) {
		this.msg = msg;
	}
}
