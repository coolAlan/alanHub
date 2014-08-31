/*
 * Created on 2013-8-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vLogin;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import publicData.MyData;
import publicData.Stream;
import vCardCenter.biz.IClientSrv;
import vCardCenter.view.vProgressBar;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;
import admin.view.adminUI;



/**
 * @author C4348
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetLoginBox extends JFrame{
	//JFrame jf;
	private JTextField jtf;
	private JPasswordField jpf;
	private boolean loginState = false;
	private User user;
	private int xx, yy;//���ڽ��涨λ���϶�
	private boolean isDraging = false;
	
	public GetLoginBox() {
		   

		this.setUndecorated(true);
		ImageIcon bg = new ImageIcon(getClass().getResource("/Image/blue.png"));
		ImageIcon bg2 = new ImageIcon(getClass().getResource("/Image/�û���.png"));
		JLabel jl1 = new JLabel(bg2); 
		ImageIcon bg3 = new ImageIcon(getClass().getResource("/Image/����.png"));
		JLabel jl2 = new JLabel(bg3); 
		JLabel label = new JLabel(bg); 
		ImageIcon bg4 = new ImageIcon(getClass().getResource("/Image/��ס����.png"));
		JLabel jl3 = new JLabel(bg4); 
		label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight()); 
		//jl1.setBounds(0,0,bg2.getIconWidth(),bg2.getIconHeight()); 
		this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); 
		JPanel jp=(JPanel)this.getContentPane(); 
		jp.setOpaque(false);
		
		this.setLayout(new GridLayout(4, 4));
		
		//JLabel jl1 = new JLabel("�û�����");
		//jl1.setForeground(Color.WHITE);
		jtf = new JTextField(12);
		JPanel jp0 = new JPanel();
		jp0.setLayout(null);
		JButton jb6 =new JButton(new ImageIcon(getClass().getResource("/Image/Lclose.png")));
		jb6.setBackground(new Color(0,0,0));
		jb6.setOpaque(false);
		jb6.setBorderPainted(false);//��ť�����߿�
		jb6.setFocusPainted(false);
		jb6.addMouseListener(new myButtonListener(jb6,"Image/Lclose.png","Image/Lclose2.png"));
		jb6.setContentAreaFilled(false);
	    jb6.setPressedIcon(new ImageIcon(getClass().getResource("/Image/Lclose3.png")));
		jp0.add(jb6);
		jb6.setBounds(370,8,20,20);
		jb6.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				GetLoginBox.this.setVisible(false);
				
			}
			
		});
		
		
		
		
		//��С����ť
		JButton jb8 =new JButton(new ImageIcon(getClass().getResource("/Image/��С.png")));
		jb8.setBackground(new Color(0,0,0));
		jb8.setOpaque(false);
		jb8.setBorderPainted(false);//��ť�����߿�
		jb8.setFocusPainted(false);
		jb8.addMouseListener(new myButtonListener(jb8,"Image/��С.png","Image/��С2.png"));
		jb8.setContentAreaFilled(false);
	    jb8.setPressedIcon(new ImageIcon(getClass().getResource("/Image/��С3.png")));
		jp0.add(jb8);
		jb8.setBounds(330,8,20,20);
		jb8.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				GetLoginBox.this.setExtendedState(ICONIFIED);
			}
			
		});
		this.add(jp0);
		
		
		jp0.setOpaque(false);
		JPanel jp1 = new JPanel();
		jp1.setLayout(null);
		jl1.setBounds(40, 15, 80, 20);
		jtf.setBounds(140, 15, 150, 20);
		jp1.add(jl1);
		jp1.add(jtf);
		this.add(jp1);
		jp1.setOpaque(false);
		jtf.setForeground(Color.BLUE);
		//JLabel jl2 = new JLabel("  ��  �룺 ");
		//jl2.setForeground(Color.WHITE);
		jpf = new JPasswordField(12);
		jpf.setForeground(Color.BLUE);
		JPanel jp2 = new JPanel();
		jp2.setLayout(null);
		jl2.setBounds(40, 20,80, 20);
		jpf.setBounds(140, 20, 150, 20);
		jl3.setBounds(320, 16, 70, 30);
		
		final JCheckBox  box=new JCheckBox();
		
		box.setBounds(298, 22, 20, 20);
		box.setOpaque(false);
		jp2.add(box);
		Stream stream = new Stream();
		String [] str = stream.StreamReader();
		if(str!=null){
			box.setSelected(true);
			jtf.setText(str[0]);
			jpf.setText(str[1]);
		}
		else
			box.setSelected(false);
		box.addItemListener(new ItemListener(){
			
		
			public void itemStateChanged(ItemEvent e) {
				Stream stream = new Stream();
				if(box.isSelected()){
					String id=jtf.getText();
					String pwd=jpf.getText();//�������
						
					stream.StreamWrite(id,pwd);
					
				}
				else{
					
				}
			}}
		);
		
		
		
		jp2.add(jl2);
		jp2.add(jpf);
		jp2.add(jl3);
		this.add(jp2);
		jp2.setOpaque(false);
 
		JPanel jp3 = new JPanel();
	    JButton jb1 =new JButton(new ImageIcon(getClass().getResource("/Image/ע��.png")));
		jb1.setBackground(new Color(0,0,0));
		jb1.setOpaque(false);
		jb1.setBorderPainted(false);//��ť�����߿�
		jb1.setFocusPainted(false);
		jb1.addMouseListener(new myButtonListener(jb1,"Image/ע��.png","Image/ע��2.png"));
		jb1.setContentAreaFilled(false);
	    jb1.setPressedIcon(new ImageIcon(getClass().getResource("/Image/ע��3.png")));
		
		
		jb1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					new register();
					GetLoginBox.this.dispose();
				}});
		
	    JButton jb2=new JButton(new ImageIcon(getClass().getResource("/Image/��¼4.png")));
		//��¼��ť
	    jb2.setBackground(new Color(0,0,0));
		jb2.setOpaque(false);
		jb2.setBorderPainted(false);//��ť�����߿�
		jb2.setFocusPainted(false);
		jb2.addMouseListener(new myButtonListener(jb2,"Image/��¼4.png","Image/��¼5.png"));
		jb2.setContentAreaFilled(false);
	    jb2.setPressedIcon(new ImageIcon(getClass().getResource("/Image/��¼6.png")));
	    
	    jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {		
				if ("".equals(jtf.getText())
                    || "".equals(new String(jpf.getPassword()))
					|| jpf.getPassword() == null) {
				JOptionPane.showConfirmDialog(
						null,
						"�û��� ���� ���� ����Ϊ�գ�!\n���������룡", "����",
						JOptionPane.DEFAULT_OPTION);
				jtf.setText(null);
				jpf.setText(null);
				jtf.requestFocus();
			} else {
				
				String id=jtf.getText();
				String pwd=jpf.getText();//�������
				IClientSrv IclientSrv = new IClientSrv(id,pwd); 
				if(IclientSrv.isRole()){
					user = (User)IclientSrv.getMessage().getData().get(0);//Ĭ�ϵ�һ����Ϣ��User
					MyData.user = user;
					
					loginState = true;
					SwingUtilities.invokeLater(new Runnable(){
						public void run(){
							System.out.println("main ");
							//new mainUI();
							if(user.getRole()==0){
						vProgressBar t = new vProgressBar();  
						 new Thread(t).start();  
						 }
							else
 								new adminUI();
						}
						
					});
					GetLoginBox.this.setVisible(false);
					
					
				}
				else{
					String str = (IclientSrv.getMessage()).getMName();
					if( str.equals("�ظ���¼") )
							JOptionPane.showMessageDialog(null, "�ظ���¼", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "��½ʧ��", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
							
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
						
							e.printStackTrace();
						}
						
					}
					
					jtf.setText("");
					jpf.setText("");
					loginState = false;
				}
				
				
			}
				
	}});
	    jb2.addKeyListener(new KeyAdapter(){
	    	 public void keyPressed(KeyEvent event){
	    	    if(event.getKeyText(event.getKeyCode()).compareToIgnoreCase("Enter")==0){
	    	    	login();
	    	    }
	    	 }
	    });
		
		
	    JButton jb3 =new JButton(new ImageIcon(getClass().getResource("/Image/�ر�.png")));
		jb3.setBackground(new Color(0,0,0));
		jb3.setOpaque(false);
		jb3.setBorderPainted(false);//��ť�����߿�
		jb3.setFocusPainted(false);
		jb3.addMouseListener(new myButtonListener(jb3,"Image/�ر�.png","Image/�ر�2.png"));
		jb3.setContentAreaFilled(false);
	    jb3.setPressedIcon(new ImageIcon(getClass().getResource("/Image/�ر�3.png")));
		
		jb3.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				GetLoginBox.this.setVisible(false);
				
			}
			
		});
		
		jp3.add(jb2);
		jp3.add(jb1);
		jp3.add(jb3);
		this.add(jp3);
		jp3.setOpaque(false);
		jb2.setFocusable(true);
		this.setResizable(false);
		this.setVisible(true);
		
		this.setSize(400, 300);
		this.setLocation(300, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jb2.requestFocusInWindow();
		
		
		GetLoginBox.this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				isDraging = true;xx = e.getX();
				yy = e.getY();
				}
			public void mouseReleased(MouseEvent e) 
			{isDraging = false;
			}
			});
		GetLoginBox.this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isDraging) {
					int left = GetLoginBox.this.getLocation().x;
					int top =GetLoginBox.this.getLocation().y;
					GetLoginBox.this.setLocation(left + e.getX() - xx, top + e.getY() - yy);
					} 
				}
			}); 
		GetLoginBox.this.setVisible(true);
	
	}
	//��¼�¼�
	public void login(){
		if ("".equals(jtf.getText())
                || "".equals(new String(jpf.getPassword()))
				|| jpf.getPassword() == null) {
			JOptionPane.showConfirmDialog(
					null,
					"�û��� ���� ���� ����Ϊ�գ�!\n���������룡", "����",
					JOptionPane.DEFAULT_OPTION);
			jtf.setText(null);
			jpf.setText(null);
			jtf.requestFocus();
		} else {
			
			String id=jtf.getText();
			String pwd=jpf.getText();//�������
			IClientSrv IclientSrv = new IClientSrv(id,pwd); 
			if(IclientSrv.isRole()){
				user = (User)IclientSrv.getMessage().getData().get(0);//Ĭ�ϵ�һ����Ϣ��User
				MyData.user = user;
				//JOptionPane.showMessageDialog(null, "��½�ɹ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				loginState = true;
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						System.out.println("main ");
						//new mainUI();
						if(user.getRole()==0){
					vProgressBar t = new vProgressBar();  
					 new Thread(t).start();  
					 }
						else
								new adminUI();
					}
					
				});
				GetLoginBox.this.setVisible(false);
				
				
			}
			else{
				String str = (IclientSrv.getMessage()).getMName();
				if( str.equals("�ظ���¼") )
						JOptionPane.showMessageDialog(null, "�ظ���¼", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "��½ʧ��", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
						
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
				
						e.printStackTrace();
					}
					
				}
				
				jtf.setText("");
				jpf.setText("");
				loginState = false;
			}
			
			
		}
	}
	//���̻س�������ť
	public static void enterPressesWhenFocused(JButton button) {
        button.registerKeyboardAction(button.getActionForKeyStroke(KeyStroke
                .getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);

        button.registerKeyboardAction(button.getActionForKeyStroke(KeyStroke
                .getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);
    }
 
	public static void main(String[] args) {
		new GetLoginBox();
	}
	

	class myButtonListener extends MouseAdapter{//�̳�MouseAdapter����д���ַ���
		JButton jb;
		String picture1;
		String picture2;
		public myButtonListener(JButton jb,String picture1,String picture2){
			this.jb=jb;
			this.picture1=picture1;
			this.picture2=picture2;
			
			
		}
		public void mouseEntered(MouseEvent arg0) {
			jb.setIcon(new ImageIcon(getClass().getResource("/"+picture2)));
			
			
		}

		public void mouseExited(MouseEvent arg0) {
			jb.setIcon(new ImageIcon(getClass().getResource("/"+picture1)));
			
	    }
	}
	
	

	
}
	
