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
 * 用于存储登录用户信息的类
 */
public class ClientIndividual {

	private String ClientName;//用户一卡通号
	private String ip;//用户IP
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
