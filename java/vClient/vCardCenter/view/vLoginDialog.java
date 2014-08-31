/*
 * Created on 2013-8-23
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vCardCenter.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import publicData.MyData;
import vCardCenter.biz.IClientSrv;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;

/**
 * @author shipeng
 *
 * 显示登陆的用户界面
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class vLoginDialog extends JDialog {
	private String uID;
	private String pwd;
	private JTextField jtf_ID = new JTextField(9);
	private JPasswordField jtf_pwd = new JPasswordField(9);
    private boolean loginState = false;
    private User user;
	public vLoginDialog(){
	   // setTitle("为保护账号安全，请再次输入密码");
		this.setTitle("登录");
		JLabel label1 = new JLabel("一卡通号：");
		label1.setPreferredSize(new Dimension(70,20));
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label2 = new JLabel("密码：");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		label2.setPreferredSize(new Dimension(70,20));
		jtf_ID.setText("213110337");
		jtf_pwd.setText("200012");
		 
		JPanel jpl_1 = new JPanel();
		jpl_1.setLayout(new FlowLayout());
		jpl_1.add(label1);
		jpl_1.add(jtf_ID);
		
		
		JPanel jpl_2 = new JPanel();
		jpl_2.setLayout(new FlowLayout());
		jpl_2.add(label2);
		jpl_2.add(jtf_pwd);
		JPanel jpl_3 = new JPanel();
		JButton jbt_login= new JButton("登陆");
		JButton jbt_cancel= new JButton("取消");
		jpl_3.setLayout(new FlowLayout());
		jpl_3.add(jbt_login);
		jpl_3.add(jbt_cancel);
		Box horizontal = Box.createHorizontalBox();
		Box vertical = Box.createVerticalBox();
		
		vertical.add(jpl_1);
		vertical.add(jpl_2);
		vertical.add(jpl_3);


		add(vertical,BorderLayout.CENTER);
		setPreferredSize(new Dimension(300,250));
		pack();
		setModal(true);
		
	 	
	 	
		jbt_login.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
						uID = jtf_ID.getText();
						
						pwd = new String(jtf_pwd.getPassword());
						System.out.println("login:"+uID+"  "+pwd);
						
						IClientSrv IclientSrv = new IClientSrv(uID,pwd);
						
						//System.out.println("User:"+user.getStdNumber());
						if(IclientSrv.isRole()){
							user = (User)IclientSrv.getMessage().getData().get(0);//默认第一条信息是User
							MyData.user = user;
							JOptionPane.showMessageDialog(null, "登陆成功", "系统信息", JOptionPane.INFORMATION_MESSAGE);
							loginState = true;
							setVisible(false);
							
						}
						else{
							String str = (IclientSrv.getMessage()).getMName();
							if( str.equals("重复登录") )
									JOptionPane.showMessageDialog(null, "重复登录", "系统信息", JOptionPane.INFORMATION_MESSAGE);
							else
								JOptionPane.showMessageDialog(null, "登陆失败", "系统信息", JOptionPane.INFORMATION_MESSAGE);
									
							if(MyData.socket!=null){
								ObjectOutputStream toServer = MyData.toServer;
								ObjectInputStream fromServer = MyData.fromServer;
								Message msg = new Message(0,0,null);
								try {
									toServer.writeObject(msg);
									toServer.flush();
									
									MyData.socket.close();
									MyData.toServer.close();
									MyData.fromServer.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							
							jtf_ID.setText("");
							jtf_pwd.setText("");
							loginState = false;
						}
						
					}
					
				
				
			}	
		);
		jbt_cancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				
			}
			
		});
	}
	
	/**
	 * @return Returns the pwd.
	 */
	public String getPwd() {
		return pwd.toString();
	}
	/**
	 * @param pwd The pwd to set.
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * @return Returns the uID.
	 */
	public String getUID() {
		return uID.toString();
	}
	/**
	 * @param uid The uID to set.
	 */
	public void setUID(String uid) {
		uID = uid;
	}
	
	/**
	 * @return Returns the loginState.
	 */
	public boolean isLoginState() {
		return loginState;
	}
	
	/**
	 * @return Returns the user.
	 */
	public User getUser() {
		return user;
	}
	
}
