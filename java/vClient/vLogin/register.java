package vLogin;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;

import vSchoolSys.common.Message;

public class register extends JFrame {
	
	JFrame jf;
	JTextField jtf;
	JPasswordField jpf, jpf2;
	private String id;
	private String str;
 
	public register() {
		jf = new JFrame("注册");
		jf.setUndecorated(true);
		jf.setLayout(new GridLayout(6, 1));
		ImageIcon bg = new ImageIcon(getClass().getResource("/Image/blue.png"));
		JLabel label = new JLabel(bg); 
		label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight()); 
		jf.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); 
		JPanel jp=(JPanel)jf.getContentPane(); 
		jp.setOpaque(false);
		JPanel jp0 = new JPanel();
		
		jp0.setLayout(null);
		JButton jb6 =new JButton(new ImageIcon(getClass().getResource("/Image/Lclose.png")));
		jb6.setBackground(new Color(0,0,0));
		jb6.setOpaque(false);
		jb6.setBorderPainted(false);//按钮不画边框
		jb6.setFocusPainted(false);
		jb6.addMouseListener(new myButtonListener(jb6,"Image/Lclose.png","Image/Lclose2.png"));
		jb6.setContentAreaFilled(false);
	    jb6.setPressedIcon(new ImageIcon(getClass().getResource("/Image/Lclose3.png")));
		jp0.add(jb6);
		jb6.setBounds(360,8,20,20);
		jb6.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		JButton jb7 =new JButton(new ImageIcon(getClass().getResource("/Image/放大.png")));
		jb7.setBackground(new Color(0,0,0));
		jb7.setOpaque(false);
		jb7.setBorderPainted(false);//按钮不画边框
		jb7.setFocusPainted(false);
		jb7.addMouseListener(new myButtonListener(jb7,"Image/放大.png","Image/放大2.png"));
		jb7.setContentAreaFilled(false);
	    jb7.setPressedIcon(new ImageIcon(getClass().getResource("/Image/放大3.png")));
		jp0.add(jb7);
		jb7.setBounds(340,8,20,20);
		jb7.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
		
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		JButton jb8 =new JButton(new ImageIcon(getClass().getResource("/Image/缩小.png")));
		jb8.setBackground(new Color(0,0,0));
		jb8.setOpaque(false);
		jb8.setBorderPainted(false);//按钮不画边框
		jb8.setFocusPainted(false);
		jb8.addMouseListener(new myButtonListener(jb8,"Image/缩小.png","Image/缩小2.png"));
		jb8.setContentAreaFilled(false);
	    jb8.setPressedIcon(new ImageIcon(getClass().getResource("/Image/缩小3.png")));
		jp0.add(jb8);
		jb8.setBounds(320,8,20,20);
		
		jf.add(jp0);
		jp0.setOpaque(false);
		
		ImageIcon bg2 = new ImageIcon(getClass().getResource("/Image/用户名2.png"));
		JLabel jl1 = new JLabel(bg2); 
		jtf = new JTextField(12);
		JPanel jp1 = new JPanel();
		jp1.add(jl1);
		jp1.add(jtf);
		jf.add(jp1);
		jp1.setOpaque(false);
 
		ImageIcon bg3 = new ImageIcon(getClass().getResource("/Image/密码2.png"));
		JLabel jl2 = new JLabel(bg3); 
		jpf = new JPasswordField(12);
		JPanel jp2 = new JPanel();
		jp2.add(jl2);
		jp2.add(jpf);
		jf.add(jp2);
		jp2.setOpaque(false);
 
		ImageIcon bg4 = new ImageIcon(getClass().getResource("/Image/确认密码.png"));
		JLabel jl3 = new JLabel(bg4); 
		jpf2 = new JPasswordField(12);
		JPanel jp3 = new JPanel();
		jp3.add(jl3);
		jp3.add(jpf2);
		jf.add(jp3);
		jp3.setOpaque(false);
 
		JPanel jp4 = new JPanel();
	    JButton jb1 =new JButton(new ImageIcon(getClass().getResource("/Image/确认注册.png")));
		jb1.setBackground(new Color(0,0,0));
		jb1.setOpaque(false);
		jb1.setBorderPainted(false);//按钮不画边框
		jb1.setFocusPainted(false);
		jb1.addMouseListener(new myButtonListener(jb1,"Image/确认注册.png","Image/确认注册2.png"));
		jb1.setContentAreaFilled(false);
	    jb1.setPressedIcon(new ImageIcon(getClass().getResource("/Image/确认注册3.png")));
	    jb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if ("".equals(jtf.getText())
			
					|| "".equals(new String(jpf.getPassword()))
					|| jpf.getPassword() == null
					|| "".equals(new String(jpf2.getPassword()))
					|| jpf2.getPassword() == null) {
				final JFrame jf = new JFrame("错误");
				JLabel jl = new JLabel("用户名 或者 密码 不能为空！");
				JPanel jp1 = new JPanel();
				JPanel jp2 = new JPanel();
				jp1.add(jl);
				jf.add(jp1);
				JButton jb = new JButton("确定");
				jb.addActionListener(new ActionListener() {
 
					
					public void actionPerformed(ActionEvent e) {
						
						jf.dispose();
					}
 
				});
				jp2.add(jb);
				jf.add(jp2);
				jf.setLayout(new GridLayout(2, 1));
				jf.setLocationRelativeTo(null);
				jf.setSize(200,140);
				//jf.pack();
				jf.setResizable(false);
				jf.setVisible(true);
				
				//jf.setLocation(400, 300);
			} else {
				id=jtf.getText();
				str = new String(jpf.getPassword());
				String str2 = new String(jpf2.getPassword());
				System.out.println(str);
				System.out.println(str2);
				if (!str.equals(str2))
					JOptionPane.showMessageDialog(null,"两次输入密码不一致");
				else{
					IRegister register = new IRegister(id);
					Message msg = register.getMessage();
					ArrayList<Object> list = msg.getData();
					boolean login = (Boolean)list.get(0);
					
					if(login)
					{JOptionPane.showMessageDialog(null,"用户名已存在");}
					else
					{
						id=jtf.getText();
					    jf.dispose();
					    new moreInf(id,str);
					}}}}}
					
				);
					
				
			
	    
	  
	    JButton jb2 =new JButton(new ImageIcon(getClass().getResource("/Image/取消.png")));
		jb2.setBackground(new Color(0,0,0));
		jb2.setOpaque(false);
		jb2.setBorderPainted(false);//按钮不画边框
		jb2.setFocusPainted(false);
		jb2.addMouseListener(new myButtonListener(jb2,"Image/取消.png","Image/取消2.png"));
		jb2.setContentAreaFilled(false);
	    jb2.setPressedIcon(new ImageIcon(getClass().getResource("/Image/取消3.png")));
		jb2.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
			 });
		jp4.add(jb1);
		jp4.add(jb2);
		jf.add(jp4);
		jp4.setOpaque(false);
 
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setSize(400, 300);
		jf.setLocation(300, 200);
		jf.setLocationRelativeTo(null);
		//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
 
	//public static void main(String[] args) {
		//new register();
	//}
 


	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	class myButtonListener extends MouseAdapter{//继承MouseAdapter，重写部分方法
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

