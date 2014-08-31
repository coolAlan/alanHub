/*
 * Created on 2013-8-29
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vLogin;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vSchoolSys.common.Message;
/**
 * @author C3337
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class moreInf extends JFrame{
	JFrame jf;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
	private String id;
	private String pwd;
	private String name;
	private String stdNum;
	private String phoneNum;
	private Date birth;
	
	public moreInf(String ID,String PWD) {
		id=ID;
		pwd=PWD;
		System.out.println(id);
		System.out.println(pwd);
		jf = new JFrame("填写详细信息");
		jf.setUndecorated(true);
		jf.setLayout(new GridLayout(5, 4));
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
		jb6.setBounds(455,8,20,20);
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
		jb7.setBounds(435,8,20,20);
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
		jb8.setBounds(415,8,20,20);
		
		
		jf.add(jp0);
		
		
		JPanel jp1 = new JPanel();
		jf.add(jp1);
		//JLabel jl1 = new JLabel("姓      名  ：");
		ImageIcon bg2= new ImageIcon(getClass().getResource("/Image/姓名.png"));
		JLabel jl1 = new JLabel(bg2); 
		jtf1 = new JTextField(12);
		jp1.add(jl1);
		jp1.add(jtf1);
		
		
		
		
		JPanel jp4 = new JPanel();
		jf.add(jp4);
		//JLabel jl4 = new JLabel("学     号   ：");
		ImageIcon bg3= new ImageIcon(getClass().getResource("/Image/学号.png"));
		JLabel jl4 = new JLabel(bg3); 
		jp4.add(jl4);
		jtf4= new JTextField(12);
		jp4.add(jtf4);
		
		JPanel jp5 = new JPanel();
		jf.add(jp5);
		//JLabel jl5 = new JLabel("联系方式：");
		ImageIcon bg4= new ImageIcon(getClass().getResource("/Image/联系方式.png"));
		JLabel jl5 = new JLabel(bg4); 
		jp5.add(jl5);
		jtf5 = new JTextField(12);
		jp5.add(jtf5);
		
		
		JPanel jp6 = new JPanel();
		jf.add(jp6);
		 JButton jb1 =new JButton(new ImageIcon(getClass().getResource("/Image/完成注册.png")));
			jb1.setBackground(new Color(0,0,0));
			jb1.setOpaque(false);
			jb1.setBorderPainted(false);//按钮不画边框
			jb1.setFocusPainted(false);
			jb1.addMouseListener(new myButtonListener(jb1,"Image/完成注册.png","Image/完成注册2.png"));
			jb1.setContentAreaFilled(false);
		    jb1.setPressedIcon(new ImageIcon(getClass().getResource("/Image/完成注册3.png")));
		jb1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				if ("".equals(jtf1.getText())
	                    || "".equals(jtf4.getText())
						|| jtf5.getText()== null) {
					JOptionPane.showConfirmDialog(
							jf,
							"	输入不能为空！!\n请重新输入！", "错误",
							JOptionPane.DEFAULT_OPTION);
					jtf1.setText(null);
					jtf4.setText(null);
					jtf5.setText(null);
				} else {
					jf.dispose();
				
				name=jtf1.getText();
				
				stdNum=jtf4.getText();
				phoneNum=jtf5.getText();
				
				IRegister register = new IRegister(id,pwd,name,stdNum,phoneNum);
				Message msg = register.getMessage();
				msg.getData();
		       new GetLoginBox();
		      }}});
		
		        			
		jp6.add(jb1);
		
		ImageIcon bg = new ImageIcon(getClass().getResource("/Image/blue.png"));
		JLabel label = new JLabel(bg); 
		label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight()); 
		jf.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); 
		JPanel jp=(JPanel)jf.getContentPane(); 
		jp.setOpaque(false);
		jp0.setOpaque(false);
		jp1.setOpaque(false);
		//jp2.setOpaque(false);
		//jp3.setOpaque(false);
		jp4.setOpaque(false);
		jp5.setOpaque(false);
		jp6.setOpaque(false);
		
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setSize(500, 300);
		jf.setLocation(300, 200);
		jf.setLocationRelativeTo(null);
		//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
