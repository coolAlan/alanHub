/*
 * Created on 2013-8-21
 *
 * 
 * 
 */
package vSchoolSys.biz;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import publicData.ClientIndividual;
import publicData.MyEvent;
import publicData.SrvData;

/**
 * @author 时鹏
 */

public class IServerSrvImpl implements IServerSrv {

	private ServerSocket serverSocket;
	private boolean closed;
	
	private JTable jtable;
	private JFrame frame;
	private DefaultTableModel model;
	private JLabel number ;
	
	//构造函数，开启端口
	public IServerSrvImpl(){
		
	}

	public void run() {
		try {
			serverSocket = new ServerSocket(9876);
			closed = false;
			System.out.print("serverSocket is built,the port is 9876\n");
			SrvData srvData = new SrvData();//初始化登录用户记录
			JLabel label = new JLabel(new ImageIcon(getClass().getResource("/images/srvFlag02.png")));
			jtable = new JTable();
			
			model =  new DefaultTableModel(
					null	
					,new String[]{"客户ID","ip地址","状态"});
			
			JLabel title = new JLabel("服务器，端口9876：在线用户............");
			number = new JLabel("在线人数： "+SrvData.clientNum);
			JPanel pane = new JPanel();
			pane.add(label);
			pane.add(title);
			pane.add(number);
			//pane.add(jtable);
			jtable.setModel(model);
			for(int i=0;i<SrvData.clients.size();i++){
				ClientIndividual individual = SrvData.clients.get(i);
				model.addRow(new String[]{individual.getClientName(),individual.getIp(),"在线"});
			}
			frame = new JFrame("服务器");
			srvData.addMyListener(new MyListener());
			frame.add(new JScrollPane(jtable),BorderLayout.CENTER);
			frame.add(pane,BorderLayout.NORTH);
			frame.pack();
			frame.setLocationRelativeTo(null); // 设置窗口居中
			frame.setVisible(true);
			frame.addWindowListener(new WindowAdapter()  {    //关闭窗口事件
				public void windowClosing( WindowEvent e )   
				{   
						closed = true;
						try {
							serverSocket.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.exit(0);  
						
					
				}     
			});
			
		} catch (IOException e) {

			e.printStackTrace();
		}	
		try{
			
			int clientNo = 0;
			while(!closed && !serverSocket.isClosed()){
				
				//监听是否有客户发送请求
				Socket s  = serverSocket.accept();
				
				InetAddress inetAddress = s.getInetAddress();
				System.out.println("host name:"+inetAddress.getHostName());
			 	System.out.println("ip name:"+inetAddress.getHostAddress());
				
			 	//创建一个新线程
				HandleAClient task = new HandleAClient(s);
				//运行该新线程
				new Thread(task).start();
				
				clientNo++;
			}
		}
		catch(IOException ex){
			ex.printStackTrace();
		} 
	}
	
	public void start() {
		new Thread(this).start();
	}

	public void close() {
	}

	public boolean isClosed() {

		return closed;
	}
	public class MyListener implements java.util.EventListener{
		 //这里是当事件发生后的响应过程
		    public void EventActivated(MyEvent me)
		    {
		    	System.out.println("响应！");
		    	//frame.setVisible(false);
		    	
		    	
		    	System.out.println(SrvData.clients.size());
		    	model.setNumRows(0);
		    	for(int i=0;i<SrvData.clients.size();i++){
		    		System.out.println("for loop");
					ClientIndividual individual = SrvData.clients.get(i);
					model.addRow(new String[]{individual.getClientName(),individual.getIp(),"在线"});
					
					model.fireTableDataChanged();
					
		    	
		    	}
		    	number.setText("在线人数： "+SrvData.clients.size());
				//number = new JLabel("在线人数： "+SrvData.clients.size());
				number.repaint();
		    	jtable.setModel(model);
		    	//frame.setVisible(true);
		        
		    }
		}
}
