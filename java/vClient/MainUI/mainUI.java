/*
 * Created on 2013-9-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package MainUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import publicData.MyData;
import publicData.MyListener;
import publicData.MyPanel;
import publicData.myButtonListener;
import vBasicInfo.view.StuInfo;
import vCardCenter.view.vIncreConsu;
import vCardCenter.view.vLostReturn;
import vCardCenter.view.vRecordShow;
import vEshop.view.MainClient;
import vLibrary.view.MainFrame;
import vSchoolSys.common.Message;
import vSelectCourse.view.CourseView;

/**
 * @author Alan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class mainUI extends JFrame {

    //主窗口顶部菜单面板，下面代码设置背景。需在该面板加入各大模块按钮
	private MyPanel topPanel = new MyPanel("/Image/ss.jpg");
	
	//声明菜单按钮
	private JButton mainButton = new JButton(new ImageIcon(getClass().getResource("/Image/主页1.png")));
	//private JButton mainButton = new JButton(new ImageIcon(getClass().getResource("/image/主页1.png")));//考虑让周滢滢来做，显示学生基本信息（提取自刘彤部分），以及欢迎信息，如果可行加入少博的教务动态（虚拟）
	private JButton xszxButton = new JButton(new ImageIcon(getClass().getResource("/Image/学生在线1.png")));//彭成伦选课、课表部分+少博成绩查询、考场查询、分数查询、教室借用。
	private JButton xjglButton = new JButton(new ImageIcon(getClass().getResource("/Image/学籍管理1.png")));//刘彤的，家庭成员信息、学籍、奖惩
	private JButton tsgButton  = new JButton(new ImageIcon(getClass().getResource("/Image/图书馆1.png")));//石珺的，图书查询借阅、还书、借书记录查询、榜单推荐
	private JButton yktButton  = new JButton(new ImageIcon(getClass().getResource("/Image/一卡通中心1.png")));//时鹏的，账单流水查询、充值缴费、挂失
	private JButton sdButton   = new JButton(new ImageIcon(getClass().getResource("/Image/商店1.png")));//赵子琦的，单独界面
	
	private JButton close = new JButton(new ImageIcon(getClass().getResource("/Image/close1.png")));
	private JButton minBtn = new JButton(new ImageIcon(getClass().getResource("/Image/MinBtn1.png")));
	private JButton changeBtn = new JButton(new ImageIcon(getClass().getResource("/Image/cloth.png")));//换皮肤
	//各功能模块对应面板（位于中下部的显示区域）:    提供承载和面板背景
	//需将各模块要显示的内容放到这个面板，同时在顶部加入二级选择菜单的按钮（实现各模块的功能切换）
	//**大家可以这样想，原来大家做的主界面做在一个JFrame里，现在放到的这个MyPanel里面
	//**原来大家的主界面里的功能选择按钮现在做在一个水平HorizontalBox里，嵌套在MyPanel的上部的，一细条的按钮
	//**比如图书馆的，图书查询借阅、还书、借书记录查询、榜单推荐等按钮都是放在水平二级菜单HorizontalBox，在放入MyPanel上部
	//**而大家的显示内容是放在水平二级菜单HorizontalBox下部的
	private MyPanel mainPanel = new MyPanel("/Image/center2.jpg");//主页
	private MyPanel xszxPanel = new CourseView("/Image/center2.jpg");//学生在线
	private MyPanel xjglPanel = new MyPanel("/Image/center2.jpg");//学籍管理
	private MyPanel tsgPanel  = new MyPanel("/Image/center2.jpg");//图书馆
	private MyPanel yktPanel  = new MyPanel("/Image/center2.jpg");//一卡通中心
	private MyPanel sdPanel   = new MyPanel("/Image/center2.jpg");//商店
	
	private MyPanel bottomPanel = new MyPanel("/Image/bottom.png");
	private JLabel date = new JLabel("".toString()+"   ");
	private Timer timer = new Timer(1000,new TimerListener());
	
	//各功能模块对应选项面板：   选项面板将功能细化
	 
	JPanel ykt_content =new JPanel();
	
	private int xx, yy;//用于界面定位和拖动
	private boolean isDraging = false;
	private Box horizontal ;
	private CardLayout c;
	private JButton jb_jlcx;
	private JButton jb_czjf;
	private JButton jb_gs;
	public  mainUI() {
		timer.start();
		//初始化各模块的选项面板
	
		
		MyPanel basicInfo = new MyPanel("/Image/bq.png");
		JLabel basic = new JLabel("基本信息",JLabel.CENTER);
		JLabel uid = new JLabel("一卡通号："+MyData.user.getUId());
		JLabel name = new JLabel("姓名："+MyData.user.getUName());
		JLabel stuName = new JLabel("学号："+MyData.user.getStdNumber());
		JLabel remain = new JLabel("余额："+MyData.user.getRemain());
		int mark = MyData.user.getLost();
		//if(mark==1)
		//	JLabel lost = new JLabel("挂失");
		Box vertical = Box.createVerticalBox();
		vertical.add(Box.createVerticalStrut(20));
		vertical.add(basic);
		vertical.add(Box.createVerticalStrut(25));
		vertical.add(uid);
		vertical.add(name);
		vertical.add(stuName);
		vertical.add(remain);
		//vertical.add(lost);
		basicInfo.add(vertical);
		mainPanel.setLayout(null);
		basicInfo.setBounds(650,100,200,200);
		//basicInfo.setOpaque(false);
		mainPanel.add(basicInfo);
		
		
		changeBtn.addMouseListener(new myButtonListener(changeBtn,"Image/cloth.png","Image/cloth_g.png"));
		changeBtn.setPressedIcon(new ImageIcon(getClass().getResource("/Image/cloth_b.png")));
		changeBtn.addActionListener(new MyListen());
		changeBtn.setBounds(0,0,10,10);
		changeBtn.setOpaque(false);
		changeBtn.setBorderPainted(false);//按钮不画边框
		changeBtn.setFocusPainted(false);
		changeBtn.setContentAreaFilled(false);
		
		
//***********************************************************************************//
		//举例子：一卡通中心
		//初始化一卡通面板
		yktPanel.setLayout(new BorderLayout());//一卡通中心的MyPanel，设置布局
		ykt_content.setOpaque(false);
		jb_jlcx = new JButton("记录查询",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		jb_jlcx.setHorizontalTextPosition(0);
		btnDecorate(jb_jlcx);
		jb_czjf = new JButton("充值/缴费",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		jb_czjf.setHorizontalTextPosition(0);
		btnDecorate(jb_czjf);
		jb_gs = new JButton("挂失",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		jb_gs.setHorizontalTextPosition(0);
		jb_gs.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				vLostReturn vLR = new vLostReturn();
				vLR.addMyListener(new MyListener(jb_jlcx,jb_czjf,sdButton));
				
			}
			
		});
		btnDecorate(jb_gs);
		if(MyData.user.getLost()==1){
			
			JOptionPane.showMessageDialog(null, "账号已挂失，部分功能无法使用！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			jb_jlcx.setEnabled(false);
			jb_czjf.setEnabled(false);
			sdButton.setEnabled(false);
		}
		jb_jlcx.setPreferredSize(new Dimension(5,29));
		jb_czjf.setPreferredSize(new Dimension(5,29));
		jb_gs.setPreferredSize(new Dimension(5,29));
		//用来放置二级菜单按钮的HorizontalBox
		horizontal = Box.createHorizontalBox();//****HorizontalBox
		horizontal.add(Box.createHorizontalStrut(5));
		horizontal.add(jb_jlcx);
		horizontal.add(Box.createHorizontalStrut(5));
		horizontal.add(jb_czjf);
		horizontal.add(Box.createHorizontalStrut(5));
		horizontal.add(jb_gs);
		
		yktPanel.add(horizontal,BorderLayout.NORTH);
		c = new CardLayout();
		
		ykt_content.setLayout(c);
		
		ykt_content.add("记录查询",new vRecordShow());
		ykt_content.add("充值/缴费",new vIncreConsu());
		ykt_content.add("挂失找回",new vLostReturn());
		ykt_content.setVisible(true);
		
		jb_jlcx.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				ykt_content.setVisible(false);
				ykt_content.removeAll();
				ykt_content.add("记录查询",new vRecordShow());
				ykt_content.add("充值/缴费",new vIncreConsu());
				ykt_content.add("挂失找回",new vLostReturn());
				ykt_content.setVisible(true);
				c.show(ykt_content,"记录查询");
				//设置按钮状态
				
			}
			
		});
		jb_czjf.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				c.show(ykt_content,"充值/缴费");
//				设置按钮状态
			}
			
		});
		jb_gs.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				c.show(ykt_content,"挂失找回");
				
			}
			
		});
		
		yktPanel.add(ykt_content,BorderLayout.CENTER);
		//ykt_content.add()
		//之后在写对这3个按钮的效应分别显示不同的图层，此处可以考虑CardLayout布局
//************************************************************************//
	
		
		
		
//************************************************************************//		
		/*
		 * 同上加载学籍管理
		 * */
		xjglPanel.setLayout(new BorderLayout());//一卡通中心的MyPanel，设置布局
		
		xjglPanel.add(new StuInfo() ,BorderLayout.CENTER);
		//ykt_content.add()
		
		
		try {
			sdPanel=new MainClient("/Image/center2.jpg");
//************************************************************************//		
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		/*
		 * 同上加载图书馆
		 * */
		tsgPanel.setLayout(new BorderLayout());
		tsgPanel.add(new MainFrame() ,BorderLayout.CENTER);
		
		
		
//************************************************************************//		

		
		this.setLayout(new BorderLayout());
		JLabel text = new JLabel("   学生虚拟校园系统v2.5");
		bottomPanel.setLayout(new BorderLayout());  
		bottomPanel.add(text,BorderLayout.WEST);
		date.setText(new Date().toString()+"   ");
		bottomPanel.add(date,BorderLayout.EAST);
		bottomPanel.setPreferredSize(new Dimension(950,32));
		//.setOpaque(false);
		this.add(bottomPanel,BorderLayout.SOUTH);
		JScrollPane c = new JScrollPane();
		
		bottomPanel.setVisible(true);
		//为顶部菜单添加按钮响应
		mainButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
		    xjglPanel.setVisible(false);
			yktPanel.setVisible(false);
			tsgPanel.setVisible(false);
			sdPanel.setVisible(false);
			xszxPanel.setVisible(false);
			mainUI.this.add(mainPanel,BorderLayout.CENTER);
			mainPanel.setVisible(true);
//			设置按钮状态
			 yktButton.addMouseListener(new myButtonListener(yktButton,"Image/一卡通中心1.png","Image/一卡通中心2.png"));
			   tsgButton.addMouseListener(new myButtonListener(tsgButton,"Image/图书馆1.png","Image/图书馆2.png"));
			   xjglButton.addMouseListener(new myButtonListener(xjglButton,"Image/学籍管理1.png","Image/学籍管理2.png"));
			   sdButton.addMouseListener(new myButtonListener(sdButton,"Image/商店1.png","Image/商城2.png"));
			   xszxButton.addMouseListener(new myButtonListener(xszxButton,"Image/学生在线1.png","Image/学生在线2.png"));

			 mainButton.setIcon(new ImageIcon(getClass().getResource("/Image/主页2.png")));
			    mainButton.updateUI();
			   xszxButton.setIcon(new ImageIcon(getClass().getResource("/Image/学生在线1.png")));
			   xszxButton.updateUI();
			   xjglButton.setIcon(new ImageIcon(getClass().getResource("/Image/学籍管理1.png")));
			   xjglButton.updateUI();
			   mainButton.addMouseListener(new myButtonListener(mainButton,"Image/主页2.png","Image/主页2.png"));// = new JButton(new ImageIcon());//刘彤的，家庭成员信息、学籍、奖惩
			   tsgButton.setIcon(new ImageIcon(getClass().getResource("/Image/图书馆1.png")));
			   tsgButton.updateUI();
			   yktButton.setIcon(new ImageIcon(getClass().getResource("/Image/一卡通中心1.png")));
			   yktButton.updateUI(); 
			   sdButton.setIcon(new ImageIcon(getClass().getResource("/Image/商店1.png")));
			   sdButton.updateUI();

			
			
			}});

		
				
				
		yktButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				xjglPanel.setVisible(false);
				tsgPanel.setVisible(false);
				sdPanel.setVisible(false);
				xszxPanel.setVisible(false);
				mainPanel.setVisible(false);
				mainUI.this.add(yktPanel,BorderLayout.CENTER);
				//yktPanel.add(bottom,BorderLayout.SOUTH);
				yktPanel.setVisible(true);
//				设置按钮状态
				 mainButton.addMouseListener(new myButtonListener(mainButton,"Image/主页1.png","Image/主页2.png"));
				   tsgButton.addMouseListener(new myButtonListener(tsgButton,"Image/图书馆1.png","Image/图书馆2.png"));
				   xjglButton.addMouseListener(new myButtonListener(xjglButton,"Image/学籍管理1.png","Image/学籍管理2.png"));
				   sdButton.addMouseListener(new myButtonListener(sdButton,"Image/商店1.png","Image/商城2.png"));
				   xszxButton.addMouseListener(new myButtonListener(xszxButton,"Image/学生在线1.png","Image/学生在线2.png"));

				
				 mainButton.setIcon(new ImageIcon(getClass().getResource("/Image/主页1.png")));
				    mainButton.updateUI();
				   xszxButton.setIcon(new ImageIcon(getClass().getResource("/Image/学生在线1.png")));
				   xszxButton.updateUI();
				   xjglButton.setIcon(new ImageIcon(getClass().getResource("/Image/学籍管理1.png")));
				   xjglButton.updateUI();
				   yktButton.addMouseListener(new myButtonListener(yktButton,"Image/一卡通中心2.png","Image/一卡通中心2.png"));// = new JButton(new ImageIcon());//刘彤的，家庭成员信息、学籍、奖惩
				   tsgButton.setIcon(new ImageIcon(getClass().getResource("/Image/图书馆1.png")));
				   tsgButton.updateUI();
				   yktButton.setIcon(new ImageIcon(getClass().getResource("/Image/一卡通中心2.png")));
				   yktButton.updateUI(); 
				   sdButton.setIcon(new ImageIcon(getClass().getResource("/Image/商店1.png")));
				   sdButton.updateUI();
			}});
		
		tsgButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				xjglPanel.setVisible(false);
				yktPanel.setVisible(false);
				sdPanel.setVisible(false);
				xszxPanel.setVisible(false);
				mainPanel.setVisible(false);
				mainUI.this.add(tsgPanel,BorderLayout.CENTER);
				tsgPanel.setVisible(true);
				
//				设置按钮状态		
				 mainButton.addMouseListener(new myButtonListener(mainButton,"Image/主页1.png","Image/主页2.png"));
				   yktButton.addMouseListener(new myButtonListener(yktButton,"Image/一卡通中心1.png","Image/一卡通中心2.png"));
				   xjglButton.addMouseListener(new myButtonListener(xjglButton,"Image/学籍管理1.png","Image/学籍管理2.png"));
				   sdButton.addMouseListener(new myButtonListener(sdButton,"Image/商店1.png","Image/商城2.png"));
				   xszxButton.addMouseListener(new myButtonListener(xszxButton,"Image/学生在线1.png","Image/学生在线2.png"));

				 mainButton.setIcon(new ImageIcon(getClass().getResource("/Image/主页1.png")));
				    mainButton.updateUI();
				   xszxButton.setIcon(new ImageIcon(getClass().getResource("/Image/学生在线1.png")));
				   xszxButton.updateUI();
				   xjglButton.setIcon(new ImageIcon(getClass().getResource("/Image/学籍管理1.png")));
				   xjglButton.updateUI();
				   tsgButton.addMouseListener(new myButtonListener(tsgButton,"Image/图书馆2.png","Image/图书馆2.png"));// = new JButton(new ImageIcon());//刘彤的，家庭成员信息、学籍、奖惩
				   tsgButton.setIcon(new ImageIcon(getClass().getResource("/Image/图书馆2.png")));
				   tsgButton.updateUI();
				   yktButton.setIcon(new ImageIcon(getClass().getResource("/Image/一卡通中心1.png")));
				   yktButton.updateUI(); 
				   sdButton.setIcon(new ImageIcon(getClass().getResource("/Image/商店1.png")));
				   sdButton.updateUI();
			}});
			
		
		
		xjglButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				tsgPanel.setVisible(false);
				yktPanel.setVisible(false);
				sdPanel.setVisible(false);
				xszxPanel.setVisible(false);
				mainPanel.setVisible(false);
				mainUI.this.add(xjglPanel,BorderLayout.CENTER);
				xjglPanel.setVisible(true);
//				设置按钮状态		
				 mainButton.addMouseListener(new myButtonListener(mainButton,"Image/主页1.png","Image/主页2.png"));
				   yktButton.addMouseListener(new myButtonListener(yktButton,"Image/一卡通中心1.png","Image/一卡通中心2.png"));
				   tsgButton.addMouseListener(new myButtonListener(tsgButton,"Image/图书馆1.png","Image/图书馆2.png"));
				   
				   sdButton.addMouseListener(new myButtonListener(sdButton,"Image/商店1.png","Image/商城2.png"));
				   xszxButton.addMouseListener(new myButtonListener(xszxButton,"Image/学生在线1.png","Image/学生在线2.png"));

				
			    mainButton.setIcon(new ImageIcon(getClass().getResource("/Image/主页1.png")));
			    mainButton.updateUI();
			   xszxButton.setIcon(new ImageIcon(getClass().getResource("/Image/学生在线1.png")));
			   xszxButton.updateUI();
			   xjglButton.setIcon(new ImageIcon(getClass().getResource("/Image/学籍管理2.png")));
			   xjglButton.updateUI();
			   xjglButton.addMouseListener(new myButtonListener(xjglButton,"Image/学籍管理2.png","Image/学籍管理2.png"));// = new JButton(new ImageIcon());//刘彤的，家庭成员信息、学籍、奖惩
			   tsgButton.setIcon(new ImageIcon(getClass().getResource("/Image/图书馆1.png")));
			   tsgButton.updateUI();
			   yktButton.setIcon(new ImageIcon(getClass().getResource("/Image/一卡通中心1.png")));
			   yktButton.updateUI(); 
			   sdButton.setIcon(new ImageIcon(getClass().getResource("/Image/商店1.png")));
			   sdButton.updateUI();
			}});
		
		
		sdButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				xjglPanel.setVisible(false);
				yktPanel.setVisible(false);
				tsgPanel.setVisible(false);
				xszxPanel.setVisible(false);
				mainPanel.setVisible(false);
				mainUI.this.add(sdPanel,BorderLayout.CENTER);
				sdPanel.setVisible(true);
				
//				设置按钮状态		
				  mainButton.addMouseListener(new myButtonListener(mainButton,"Image/主页1.png","Image/主页2.png"));
				   yktButton.addMouseListener(new myButtonListener(yktButton,"Image/一卡通中心1.png","Image/一卡通中心2.png"));
				   tsgButton.addMouseListener(new myButtonListener(tsgButton,"Image/图书馆1.png","Image/图书馆2.png"));
				   xjglButton.addMouseListener(new myButtonListener(xjglButton,"Image/学籍管理1.png","Image/学籍管理2.png"));
				   
				   xszxButton.addMouseListener(new myButtonListener(xszxButton,"Image/学生在线1.png","Image/学生在线2.png"));

				
				 mainButton.setIcon(new ImageIcon(getClass().getResource("/Image/主页1.png")));
				    mainButton.updateUI();
				   xszxButton.setIcon(new ImageIcon(getClass().getResource("/Image/学生在线1.png")));
				   xszxButton.updateUI();
				   xjglButton.setIcon(new ImageIcon(getClass().getResource("/Image/学籍管理1.png")));
				   xjglButton.updateUI();
				   sdButton.addMouseListener(new myButtonListener(sdButton,"Image/商店2.png","Image/商店2.png"));// = new JButton(new ImageIcon());//刘彤的，家庭成员信息、学籍、奖惩
				   tsgButton.setIcon(new ImageIcon(getClass().getResource("/Image/图书馆1.png")));
				   tsgButton.updateUI();
				   yktButton.setIcon(new ImageIcon(getClass().getResource("/Image/一卡通中心1.png")));
				   yktButton.updateUI(); 
				   sdButton.setIcon(new ImageIcon(getClass().getResource("/Image/商店2.png")));
				   sdButton.updateUI();
			}});
		
		
		xszxButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				xjglPanel.setVisible(false);
				yktPanel.setVisible(false);
				sdPanel.setVisible(false);
				tsgPanel.setVisible(false);
				mainPanel.setVisible(false);
				mainUI.this.add(xszxPanel,BorderLayout.CENTER);
				xszxPanel.setVisible(true);
				
//				设置按钮状态	
				mainButton.addMouseListener(new myButtonListener(mainButton,"Image/主页1.png","Image/主页2.png"));
				   yktButton.addMouseListener(new myButtonListener(yktButton,"Image/一卡通中心1.png","Image/一卡通中心2.png"));
				   tsgButton.addMouseListener(new myButtonListener(tsgButton,"Image/图书馆1.png","Image/图书馆2.png"));
				   xjglButton.addMouseListener(new myButtonListener(xjglButton,"Image/学籍管理1.png","Image/学籍管理2.png"));
				   sdButton.addMouseListener(new myButtonListener(sdButton,"Image/商店1.png","Image/商城2.png"));
				   

				
				 mainButton.setIcon(new ImageIcon(getClass().getResource("/Image/主页1.png")));
				    mainButton.updateUI();
				   xszxButton.setIcon(new ImageIcon(getClass().getResource("/Image/学生在线2.png")));
				   xszxButton.updateUI();
				   xjglButton.setIcon(new ImageIcon(getClass().getResource("/Image/学籍管理1.png")));
				   xjglButton.updateUI();
				   xszxButton.addMouseListener(new myButtonListener(xszxButton,"Image/学生在线2.png","Image/学生在线2.png"));// = new JButton(new ImageIcon());//刘彤的，家庭成员信息、学籍、奖惩
				   tsgButton.setIcon(new ImageIcon(getClass().getResource("/Image/图书馆1.png")));
				   tsgButton.updateUI();
				   yktButton.setIcon(new ImageIcon(getClass().getResource("/Image/一卡通中心1.png")));
				   yktButton.updateUI(); 
				   sdButton.setIcon(new ImageIcon(getClass().getResource("/Image/商店1.png")));
				   sdButton.updateUI();
				}});
		
		close.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				int value=JOptionPane.showConfirmDialog(null, "确定要关闭吗？","提示",JOptionPane.OK_CANCEL_OPTION);
			    if (value==JOptionPane.OK_OPTION) {
			    	if(MyData.socket!=null){
						ObjectOutputStream toServer = MyData.toServer;
						ObjectInputStream fromServer = MyData.fromServer;
						if(MyData.user==null)
						System.out.println("user is null");
						Message msg = new Message(0,0,MyData.user);
						System.out.println(MyData.user.getUId()+"下线");
						try {
							toServer.writeObject(msg);
							toServer.flush();
							MyData.socket.close();
							MyData.toServer.close();
							MyData.fromServer.close();
							System.exit(0);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						
			    	}
			    }
				
			}
			
		});
		minBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				mainUI.this.setExtendedState(ICONIFIED);
				
			}
			
		});
		topPanel.setLayout(null);
		mainButton.setBounds(20,20,81,81);
		topPanel.add(mainButton);
		xszxButton.setBounds(120,20,81,81);
		topPanel.add(xszxButton);
		xjglButton.setBounds(220,20,81,81);
		topPanel.add(xjglButton);
		tsgButton.setBounds(320,20,81,81);
		topPanel.add(tsgButton);
		yktButton.setBounds(420,20,81,81);
		topPanel.add(yktButton);
		sdButton.setBounds(520,20,81,81);
		topPanel.add(sdButton);
		close.setBounds(911,0,39,18);	
		topPanel.add(close);
		minBtn.setBounds(876,0,39,18);	
		topPanel.add(minBtn);
		changeBtn.setBounds(847,0,39,18);
		topPanel.add(changeBtn);
		topPanel.setPreferredSize(new Dimension(950, 110));
		
		mainButton.setBackground(new Color(0,0,0));
		mainButton.setOpaque(false);
		mainButton.setBorderPainted(false);//按钮不画边框
		mainButton.setFocusPainted(false);
		mainButton.addMouseListener(new myButtonListener(mainButton,"Image/主页1.png","Image/主页2.png"));
		mainButton.setContentAreaFilled(false);
		mainButton.setPressedIcon(new ImageIcon(getClass().getResource("/Image/主页3.png")));
		
		yktButton.setBackground(new Color(0,0,0));
		yktButton.setOpaque(false);
		yktButton.setBorderPainted(false);//按钮不画边框
		yktButton.setFocusPainted(false);
		yktButton.addMouseListener(new myButtonListener(yktButton,"Image/一卡通中心1.png","Image/一卡通中心2.png"));
		yktButton.setContentAreaFilled(false);
		yktButton.setPressedIcon(new ImageIcon(getClass().getResource("/Image/一卡通中心3.png")));
		
		tsgButton.setBackground(new Color(0,0,0));
		tsgButton.setOpaque(false);
		tsgButton.setBorderPainted(false);//按钮不画边框
		tsgButton.setFocusPainted(false);
		tsgButton.addMouseListener(new myButtonListener(tsgButton,"Image/图书馆1.png","Image/图书馆2.png"));
		tsgButton.setContentAreaFilled(false);
		tsgButton.setPressedIcon(new ImageIcon(getClass().getResource("/Image/图书馆3.png")));
		
		xjglButton.setBackground(new Color(0,0,0));
		xjglButton.setOpaque(false);
		xjglButton.setBorderPainted(false);//按钮不画边框
		xjglButton.setFocusPainted(false);
		xjglButton.addMouseListener(new myButtonListener(xjglButton,"Image/学籍管理1.png","Image/学籍管理2.png"));
		xjglButton.setContentAreaFilled(false);
		xjglButton.setPressedIcon(new ImageIcon(getClass().getResource("/Image/学籍管理3.png")));
		
		sdButton.setBackground(new Color(0,0,0));
		sdButton.setOpaque(false);
		sdButton.setBorderPainted(false);//按钮不画边框
		sdButton.setFocusPainted(false);
		sdButton.addMouseListener(new myButtonListener(sdButton,"Image/商店1.png","Image/商城2.png"));
		sdButton.setContentAreaFilled(false);
		sdButton.setPressedIcon(new ImageIcon(getClass().getResource("/Image/商城3.png")));
		
		
		xszxButton.setBackground(new Color(0,0,0));
		xszxButton.setOpaque(false);
		xszxButton.setBorderPainted(false);//按钮不画边框
		xszxButton.setFocusPainted(false);
		xszxButton.addMouseListener(new myButtonListener(xszxButton,"Image/学生在线1.png","Image/学生在线2.png"));
		xszxButton.setContentAreaFilled(false);
		xszxButton.setPressedIcon(new ImageIcon(getClass().getResource("/Image/学生在线3.png")));
		
		close.setBackground(new Color(0,0,0));
		close.setOpaque(false);
		close.setBorderPainted(false);//按钮不画边框
		close.setFocusPainted(false);
		close.addMouseListener(new myButtonListener(close,"Image/close1.png","Image/close2.png"));
		close.setContentAreaFilled(false);
		close.setPressedIcon(new ImageIcon(getClass().getResource("/Image/close3.png")));
		
		minBtn.setBackground(new Color(0,0,0));
		minBtn.setOpaque(false);
		minBtn.setBorderPainted(false);//按钮不画边框
		minBtn.setFocusPainted(false);
		minBtn.addMouseListener(new myButtonListener(minBtn,"Image/MinBtn1.png","Image/MinBtn2.png"));
		minBtn.setContentAreaFilled(false);
		minBtn.setPressedIcon(new ImageIcon(getClass().getResource("/Image/MinBtn3.png")));
		
		this.add(topPanel,BorderLayout.NORTH);
		this.add(mainPanel,BorderLayout.CENTER);//因为主页是默认显示的，所以要先把它加进来
		

		this.setSize(950, 670); // 初始窗口的大小
		this.setLocationRelativeTo(null); // 设置窗口居中
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter()  {    //关闭窗口事件
			public void windowClosing( WindowEvent e )   
			{   
				try {
					//只有当MyData.socket连接成功时，即用户登录
					if(MyData.socket!=null){
					ObjectOutputStream toServer = MyData.toServer;
					ObjectInputStream fromServer = MyData.fromServer;
					Message msg = new Message(0,0,MyData.user);
					toServer.writeObject(msg);
					toServer.flush();
					
					MyData.socket.close();
					MyData.toServer.close();
					MyData.fromServer.close();
					}
				} catch (IOException e1) {  
					 
					e1.printStackTrace();    
				}        
				System.exit(0);  
				
			}     
		});
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				isDraging = true;xx = e.getX();
				yy = e.getY();
				}
			public void mouseReleased(MouseEvent e) 
			{isDraging = false;
			}
			});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isDraging) {
					int left = mainUI.this.getLocation().x;
					int top = mainUI.this.getLocation().y;
					mainUI.this.setLocation(left + e.getX() - xx, top + e.getY() - yy);
					} 
				}
			}); 
		this.setVisible(true);
	}
	public void btnDecorate(JButton btn){
		btn.setHorizontalTextPosition(0);
		btn.setBackground(new Color(0,0,0));
		btn.setOpaque(false);
		btn.setBorderPainted(false);//按钮不画边框
		btn.setFocusPainted(false);
		btn.addMouseListener(new myButtonListener(btn,"Image/button-normal.png","Image/button-click.png"));
		btn.setContentAreaFilled(false);
		btn.setPressedIcon(new ImageIcon(getClass().getResource("/Image/button-hold.png")));
	}


	
	private class TimerListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			DateFormat longDateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
			
			date.setText(longDateFormat.format(new Date())+"     ");
			date.repaint();
			bottomPanel.add(date,BorderLayout.EAST);
		}
		
	}
	
	

	//对按下每个大的菜单按钮注册监听器，分别响应，显示其界面
	class MyListen implements ActionListener {

		private int i =0;
		String [] top = new String[]{"/Image/ss.jpg","/Image/top2_y.jpg","/Image/sj.jpg","/Image/green.jpg"};
		String [] s = new String[]{"/Image/center2.jpg","/Image/skin2_y.jpg","/Image/aa.jpg","/Image/world64.jpg"};
		String [] bottom = new String[]{"/Image/bottom.png","/Image/bottom_g.png","/Image/bottom_r.png","/Image/bottom_p.png"};
		public void actionPerformed(ActionEvent arg0) {
			i++;
			i=i%4;
			mainPanel.setBack(s[i]);//主页
			topPanel.setBack(top[i]);//顶头
			bottomPanel.setBack(bottom[i]);//低端
			xszxPanel.setBack(s[i]);//学生在线
			xjglPanel.setBack(s[i]) ;//学籍管理
			tsgPanel.setBack(s[i]);//图书馆
			yktPanel.setBack(s[i]);//一卡通中心
			sdPanel.setBack(s[i]);//商店
		}
	
	}
}
