/*
 * Created on 2013-8-31
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package publicData;

import java.util.AbstractList;

/**
 * @author C5535
 *
 * ���ڴ洢��¼�û���Ϣ����
 */
public class ClientIndividual {

	private String ClientName;//�û�һ��ͨ��
	private String ip;//�û�IP
	public ClientIndividual(String client,String ip) {
		//System.out.println(ClientName);
		this.ClientName = client;
		this.ip=ip;
	}
	

	/**
	 * @return Returns the clientName.
	 */
	public String getClientName() {
		return ClientName;
	}
	/**
	 * @param clientName The clientName to set.
	 */
	public void setClientName(String clientName) {
		ClientName = clientName;
	}
	/**
	 * @return Returns the ip.
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip The ip to set.
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}


	
}
