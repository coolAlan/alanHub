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
 * @author ʱ��
 */

public class IServerSrvImpl implements IServerSrv {

	private ServerSocket serverSocket;
	private boolean closed;
	
	private JTable jtable;
	private JFrame frame;
	private DefaultTableModel model;
	private JLabel number ;
	
	//���캯���������˿�
	public IServerSrvImpl(){
		
	}

	public void run() {
		try {
			serverSocket = new ServerSocket(9876);
			closed = false;
			System.out.print("serverSocket is built,the port is 9876\n");
			SrvData srvData = new SrvData();//��ʼ����¼�û���¼
			JLabel label = new JLabel(new ImageIcon(getClass().getResource("/images/srvFlag02.png")));
			jtable = new JTable();
			
			model =  new DefaultTableModel(
					null	
					,new String[]{"�ͻ�ID","ip��ַ","״̬"});
			
			JLabel title = new JLabel("���������˿�9876�������û�............");
			number = new JLabel("���������� "+SrvData.clientNum);
			JPanel pane = new JPanel();
			pane.add(label);
			pane.add(title);
			pane.add(number);
			//pane.add(jtable);
			jtable.setModel(model);
			for(int i=0;i<SrvData.clients.size();i++){
				ClientIndividual individual = SrvData.clients.get(i);
				model.addRow(new String[]{individual.getClientName(),individual.getIp(),"����"});
			}
			frame = new JFrame("������");
			srvData.addMyListener(new MyListener());
			frame.add(new JScrollPane(jtable),BorderLayout.CENTER);
			frame.add(pane,BorderLayout.NORTH);
			frame.pack();
			frame.setLocationRelativeTo(null); // ���ô��ھ���
			frame.setVisible(true);
			frame.addWindowListener(new WindowAdapter()  {    //�رմ����¼�
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
				
				//�����Ƿ��пͻ���������
				Socket s  = serverSocket.accept();
				
				InetAddress inetAddress = s.getInetAddress();
				System.out.println("host name:"+inetAddress.getHostName());
			 	System.out.println("ip name:"+inetAddress.getHostAddress());
				
			 	//����һ�����߳�
				HandleAClient task = new HandleAClient(s);
				//���и����߳�
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
		 //�����ǵ��¼����������Ӧ����
		    public void EventActivated(MyEvent me)
		    {
		    	System.out.println("��Ӧ��");
		    	//frame.setVisible(false);
		    	
		    	
		    	System.out.println(SrvData.clients.size());
		    	model.setNumRows(0);
		    	for(int i=0;i<SrvData.clients.size();i++){
		    		System.out.println("for loop");
					ClientIndividual individual = SrvData.clients.get(i);
					model.addRow(new String[]{individual.getClientName(),individual.getIp(),"����"});
					
					model.fireTableDataChanged();
					
		    	
		    	}
		    	number.setText("���������� "+SrvData.clients.size());
				//number = new JLabel("���������� "+SrvData.clients.size());
				number.repaint();
		    	jtable.setModel(model);
		    	//frame.setVisible(true);
		        
		    }
		}
}
