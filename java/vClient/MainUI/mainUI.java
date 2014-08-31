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

    //�����ڶ����˵���壬����������ñ��������ڸ����������ģ�鰴ť
	private MyPanel topPanel = new MyPanel("/Image/ss.jpg");
	
	//�����˵���ť
	private JButton mainButton = new JButton(new ImageIcon(getClass().getResource("/Image/��ҳ1.png")));
	//private JButton mainButton = new JButton(new ImageIcon(getClass().getResource("/image/��ҳ1.png")));//��������������������ʾѧ��������Ϣ����ȡ����ͮ���֣����Լ���ӭ��Ϣ��������м����ٲ��Ľ���̬�����⣩
	private JButton xszxButton = new JButton(new ImageIcon(getClass().getResource("/Image/ѧ������1.png")));//�����ѡ�Ρ��α���+�ٲ��ɼ���ѯ��������ѯ��������ѯ�����ҽ��á�
	private JButton xjglButton = new JButton(new ImageIcon(getClass().getResource("/Image/ѧ������1.png")));//��ͮ�ģ���ͥ��Ա��Ϣ��ѧ��������
	private JButton tsgButton  = new JButton(new ImageIcon(getClass().getResource("/Image/ͼ���1.png")));//ʯ�B�ģ�ͼ���ѯ���ġ����顢�����¼��ѯ�����Ƽ�
	private JButton yktButton  = new JButton(new ImageIcon(getClass().getResource("/Image/һ��ͨ����1.png")));//ʱ���ģ��˵���ˮ��ѯ����ֵ�ɷѡ���ʧ
	private JButton sdButton   = new JButton(new ImageIcon(getClass().getResource("/Image/�̵�1.png")));//�������ģ���������
	
	private JButton close = new JButton(new ImageIcon(getClass().getResource("/Image/close1.png")));
	private JButton minBtn = new JButton(new ImageIcon(getClass().getResource("/Image/MinBtn1.png")));
	private JButton changeBtn = new JButton(new ImageIcon(getClass().getResource("/Image/cloth.png")));//��Ƥ��
	//������ģ���Ӧ��壨λ�����²�����ʾ����:    �ṩ���غ���屳��
	//�轫��ģ��Ҫ��ʾ�����ݷŵ������壬ͬʱ�ڶ����������ѡ��˵��İ�ť��ʵ�ָ�ģ��Ĺ����л���
	//**��ҿ��������룬ԭ�������������������һ��JFrame����ڷŵ������MyPanel����
	//**ԭ����ҵ���������Ĺ���ѡ��ť��������һ��ˮƽHorizontalBox�Ƕ����MyPanel���ϲ��ģ�һϸ���İ�ť
	//**����ͼ��ݵģ�ͼ���ѯ���ġ����顢�����¼��ѯ�����Ƽ��Ȱ�ť���Ƿ���ˮƽ�����˵�HorizontalBox���ڷ���MyPanel�ϲ�
	//**����ҵ���ʾ�����Ƿ���ˮƽ�����˵�HorizontalBox�²���
	private MyPanel mainPanel = new MyPanel("/Image/center2.jpg");//��ҳ
	private MyPanel xszxPanel = new CourseView("/Image/center2.jpg");//ѧ������
	private MyPanel xjglPanel = new MyPanel("/Image/center2.jpg");//ѧ������
	private MyPanel tsgPanel  = new MyPanel("/Image/center2.jpg");//ͼ���
	private MyPanel yktPanel  = new MyPanel("/Image/center2.jpg");//һ��ͨ����
	private MyPanel sdPanel   = new MyPanel("/Image/center2.jpg");//�̵�
	
	private MyPanel bottomPanel = new MyPanel("/Image/bottom.png");
	private JLabel date = new JLabel("".toString()+"   ");
	private Timer timer = new Timer(1000,new TimerListener());
	
	//������ģ���Ӧѡ����壺   ѡ����彫����ϸ��
	 
	JPanel ykt_content =new JPanel();
	
	private int xx, yy;//���ڽ��涨λ���϶�
	private boolean isDraging = false;
	private Box horizontal ;
	private CardLayout c;
	private JButton jb_jlcx;
	private JButton jb_czjf;
	private JButton jb_gs;
	public  mainUI() {
		timer.start();
		//��ʼ����ģ���ѡ�����
	
		
		MyPanel basicInfo = new MyPanel("/Image/bq.png");
		JLabel basic = new JLabel("������Ϣ",JLabel.CENTER);
		JLabel uid = new JLabel("һ��ͨ�ţ�"+MyData.user.getUId());
		JLabel name = new JLabel("������"+MyData.user.getUName());
		JLabel stuName = new JLabel("ѧ�ţ�"+MyData.user.getStdNumber());
		JLabel remain = new JLabel("��"+MyData.user.getRemain());
		int mark = MyData.user.getLost();
		//if(mark==1)
		//	JLabel lost = new JLabel("��ʧ");
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
		changeBtn.setBorderPainted(false);//��ť�����߿�
		changeBtn.setFocusPainted(false);
		changeBtn.setContentAreaFilled(false);
		
		
//***********************************************************************************//
		//�����ӣ�һ��ͨ����
		//��ʼ��һ��ͨ���
		yktPanel.setLayout(new BorderLayout());//һ��ͨ���ĵ�MyPanel�����ò���
		ykt_content.setOpaque(false);
		jb_jlcx = new JButton("��¼��ѯ",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		jb_jlcx.setHorizontalTextPosition(0);
		btnDecorate(jb_jlcx);
		jb_czjf = new JButton("��ֵ/�ɷ�",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		jb_czjf.setHorizontalTextPosition(0);
		btnDecorate(jb_czjf);
		jb_gs = new JButton("��ʧ",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		jb_gs.setHorizontalTextPosition(0);
		jb_gs.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				vLostReturn vLR = new vLostReturn();
				vLR.addMyListener(new MyListener(jb_jlcx,jb_czjf,sdButton));
				
			}
			
		});
		btnDecorate(jb_gs);
		if(MyData.user.getLost()==1){
			
			JOptionPane.showMessageDialog(null, "�˺��ѹ�ʧ�����ֹ����޷�ʹ�ã�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
			jb_jlcx.setEnabled(false);
			jb_czjf.setEnabled(false);
			sdButton.setEnabled(false);
		}
		jb_jlcx.setPreferredSize(new Dimension(5,29));
		jb_czjf.setPreferredSize(new Dimension(5,29));
		jb_gs.setPreferredSize(new Dimension(5,29));
		//�������ö����˵���ť��HorizontalBox
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
		
		ykt_content.add("��¼��ѯ",new vRecordShow());
		ykt_content.add("��ֵ/�ɷ�",new vIncreConsu());
		ykt_content.add("��ʧ�һ�",new vLostReturn());
		ykt_content.setVisible(true);
		
		jb_jlcx.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				ykt_content.setVisible(false);
				ykt_content.removeAll();
				ykt_content.add("��¼��ѯ",new vRecordShow());
				ykt_content.add("��ֵ/�ɷ�",new vIncreConsu());
				ykt_content.add("��ʧ�һ�",new vLostReturn());
				ykt_content.setVisible(true);
				c.show(ykt_content,"��¼��ѯ");
				//���ð�ť״̬
				
			}
			
		});
		jb_czjf.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				c.show(ykt_content,"��ֵ/�ɷ�");
//				���ð�ť״̬
			}
			
		});
		jb_gs.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				c.show(ykt_content,"��ʧ�һ�");
				
			}
			
		});
		
		yktPanel.add(ykt_content,BorderLayout.CENTER);
		//ykt_content.add()
		//֮����д����3����ť��ЧӦ�ֱ���ʾ��ͬ��ͼ�㣬�˴����Կ���CardLayout����
//************************************************************************//
	
		
		
		
//************************************************************************//		
		/*
		 * ͬ�ϼ���ѧ������
		 * */
		xjglPanel.setLayout(new BorderLayout());//һ��ͨ���ĵ�MyPanel�����ò���
		
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
		 * ͬ�ϼ���ͼ���
		 * */
		tsgPanel.setLayout(new BorderLayout());
		tsgPanel.add(new MainFrame() ,BorderLayout.CENTER);
		
		
		
//************************************************************************//		

		
		this.setLayout(new BorderLayout());
		JLabel text = new JLabel("   ѧ������У԰ϵͳv2.5");
		bottomPanel.setLayout(new BorderLayout());  
		bottomPanel.add(text,BorderLayout.WEST);
		date.setText(new Date().toString()+"   ");
		bottomPanel.add(date,BorderLayout.EAST);
		bottomPanel.setPreferredSize(new Dimension(950,32));
		//.setOpaque(false);
		this.add(bottomPanel,BorderLayout.SOUTH);
		JScrollPane c = new JScrollPane();
		
		bottomPanel.setVisible(true);
		//Ϊ�����˵���Ӱ�ť��Ӧ
		mainButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
		    xjglPanel.setVisible(false);
			yktPanel.setVisible(false);
			tsgPanel.setVisible(false);
			sdPanel.setVisible(false);
			xszxPanel.setVisible(false);
			mainUI.this.add(mainPanel,BorderLayout.CENTER);
			mainPanel.setVisible(true);
//			���ð�ť״̬
			 yktButton.addMouseListener(new myButtonListener(yktButton,"Image/һ��ͨ����1.png","Image/һ��ͨ����2.png"));
			   tsgButton.addMouseListener(new myButtonListener(tsgButton,"Image/ͼ���1.png","Image/ͼ���2.png"));
			   xjglButton.addMouseListener(new myButtonListener(xjglButton,"Image/ѧ������1.png","Image/ѧ������2.png"));
			   sdButton.addMouseListener(new myButtonListener(sdButton,"Image/�̵�1.png","Image/�̳�2.png"));
			   xszxButton.addMouseListener(new myButtonListener(xszxButton,"Image/ѧ������1.png","Image/ѧ������2.png"));

			 mainButton.setIcon(new ImageIcon(getClass().getResource("/Image/��ҳ2.png")));
			    mainButton.updateUI();
			   xszxButton.setIcon(new ImageIcon(getClass().getResource("/Image/ѧ������1.png")));
			   xszxButton.updateUI();
			   xjglButton.setIcon(new ImageIcon(getClass().getResource("/Image/ѧ������1.png")));
			   xjglButton.updateUI();
			   mainButton.addMouseListener(new myButtonListener(mainButton,"Image/��ҳ2.png","Image/��ҳ2.png"));// = new JButton(new ImageIcon());//��ͮ�ģ���ͥ��Ա��Ϣ��ѧ��������
			   tsgButton.setIcon(new ImageIcon(getClass().getResource("/Image/ͼ���1.png")));
			   tsgButton.updateUI();
			   yktButton.setIcon(new ImageIcon(getClass().getResource("/Image/һ��ͨ����1.png")));
			   yktButton.updateUI(); 
			   sdButton.setIcon(new ImageIcon(getClass().getResource("/Image/�̵�1.png")));
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
//				���ð�ť״̬
				 mainButton.addMouseListener(new myButtonListener(mainButton,"Image/��ҳ1.png","Image/��ҳ2.png"));
				   tsgButton.addMouseListener(new myButtonListener(tsgButton,"Image/ͼ���1.png","Image/ͼ���2.png"));
				   xjglButton.addMouseListener(new myButtonListener(xjglButton,"Image/ѧ������1.png","Image/ѧ������2.png"));
				   sdButton.addMouseListener(new myButtonListener(sdButton,"Image/�̵�1.png","Image/�̳�2.png"));
				   xszxButton.addMouseListener(new myButtonListener(xszxButton,"Image/ѧ������1.png","Image/ѧ������2.png"));

				
				 mainButton.setIcon(new ImageIcon(getClass().getResource("/Image/��ҳ1.png")));
				    mainButton.updateUI();
				   xszxButton.setIcon(new ImageIcon(getClass().getResource("/Image/ѧ������1.png")));
				   xszxButton.updateUI();
				   xjglButton.setIcon(new ImageIcon(getClass().getResource("/Image/ѧ������1.png")));
				   xjglButton.updateUI();
				   yktButton.addMouseListener(new myButtonListener(yktButton,"Image/һ��ͨ����2.png","Image/һ��ͨ����2.png"));// = new JButton(new ImageIcon());//��ͮ�ģ���ͥ��Ա��Ϣ��ѧ��������
				   tsgButton.setIcon(new ImageIcon(getClass().getResource("/Image/ͼ���1.png")));
				   tsgButton.updateUI();
				   yktButton.setIcon(new ImageIcon(getClass().getResource("/Image/һ��ͨ����2.png")));
				   yktButton.updateUI(); 
				   sdButton.setIcon(new ImageIcon(getClass().getResource("/Image/�̵�1.png")));
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
				
//				���ð�ť״̬		
				 mainButton.addMouseListener(new myButtonListener(mainButton,"Image/��ҳ1.png","Image/��ҳ2.png"));
				   yktButton.addMouseListener(new myButtonListener(yktButton,"Image/һ��ͨ����1.png","Image/һ��ͨ����2.png"));
				   xjglButton.addMouseListener(new myButtonListener(xjglButton,"Image/ѧ������1.png","Image/ѧ������2.png"));
				   sdButton.addMouseListener(new myButtonListener(sdButton,"Image/�̵�1.png","Image/�̳�2.png"));
				   xszxButton.addMouseListener(new myButtonListener(xszxButton,"Image/ѧ������1.png","Image/ѧ������2.png"));

				 mainButton.setIcon(new ImageIcon(getClass().getResource("/Image/��ҳ1.png")));
				    mainButton.updateUI();
				   xszxButton.setIcon(new ImageIcon(getClass().getResource("/Image/ѧ������1.png")));
				   xszxButton.updateUI();
				   xjglButton.setIcon(new ImageIcon(getClass().getResource("/Image/ѧ������1.png")));
				   xjglButton.updateUI();
				   tsgButton.addMouseListener(new myButtonListener(tsgButton,"Image/ͼ���2.png","Image/ͼ���2.png"));// = new JButton(new ImageIcon());//��ͮ�ģ���ͥ��Ա��Ϣ��ѧ��������
				   tsgButton.setIcon(new ImageIcon(getClass().getResource("/Image/ͼ���2.png")));
				   tsgButton.updateUI();
				   yktButton.setIcon(new ImageIcon(getClass().getResource("/Image/һ��ͨ����1.png")));
				   yktButton.updateUI(); 
				   sdButton.setIcon(new ImageIcon(getClass().getResource("/Image/�̵�1.png")));
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
//				���ð�ť״̬		
				 mainButton.addMouseListener(new myButtonListener(mainButton,"Image/��ҳ1.png","Image/��ҳ2.png"));
				   yktButton.addMouseListener(new myButtonListener(yktButton,"Image/һ��ͨ����1.png","Image/һ��ͨ����2.png"));
				   tsgButton.addMouseListener(new myButtonListener(tsgButton,"Image/ͼ���1.png","Image/ͼ���2.png"));
				   
				   sdButton.addMouseListener(new myButtonListener(sdButton,"Image/�̵�1.png","Image/�̳�2.png"));
				   xszxButton.addMouseListener(new myButtonListener(xszxButton,"Image/ѧ������1.png","Image/ѧ������2.png"));

				
			    mainButton.setIcon(new ImageIcon(getClass().getResource("/Image/��ҳ1.png")));
			    mainButton.updateUI();
			   xszxButton.setIcon(new ImageIcon(getClass().getResource("/Image/ѧ������1.png")));
			   xszxButton.updateUI();
			   xjglButton.setIcon(new ImageIcon(getClass().getResource("/Image/ѧ������2.png")));
			   xjglButton.updateUI();
			   xjglButton.addMouseListener(new myButtonListener(xjglButton,"Image/ѧ������2.png","Image/ѧ������2.png"));// = new JButton(new ImageIcon());//��ͮ�ģ���ͥ��Ա��Ϣ��ѧ��������
			   tsgButton.setIcon(new ImageIcon(getClass().getResource("/Image/ͼ���1.png")));
			   tsgButton.updateUI();
			   yktButton.setIcon(new ImageIcon(getClass().getResource("/Image/һ��ͨ����1.png")));
			   yktButton.updateUI(); 
			   sdButton.setIcon(new ImageIcon(getClass().getResource("/Image/�̵�1.png")));
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
				
//				���ð�ť״̬		
				  mainButton.addMouseListener(new myButtonListener(mainButton,"Image/��ҳ1.png","Image/��ҳ2.png"));
				   yktButton.addMouseListener(new myButtonListener(yktButton,"Image/һ��ͨ����1.png","Image/һ��ͨ����2.png"));
				   tsgButton.addMouseListener(new myButtonListener(tsgButton,"Image/ͼ���1.png","Image/ͼ���2.png"));
				   xjglButton.addMouseListener(new myButtonListener(xjglButton,"Image/ѧ������1.png","Image/ѧ������2.png"));
				   
				   xszxButton.addMouseListener(new myButtonListener(xszxButton,"Image/ѧ������1.png","Image/ѧ������2.png"));

				
				 mainButton.setIcon(new ImageIcon(getClass().getResource("/Image/��ҳ1.png")));
				    mainButton.updateUI();
				   xszxButton.setIcon(new ImageIcon(getClass().getResource("/Image/ѧ������1.png")));
				   xszxButton.updateUI();
				   xjglButton.setIcon(new ImageIcon(getClass().getResource("/Image/ѧ������1.png")));
				   xjglButton.updateUI();
				   sdButton.addMouseListener(new myButtonListener(sdButton,"Image/�̵�2.png","Image/�̵�2.png"));// = new JButton(new ImageIcon());//��ͮ�ģ���ͥ��Ա��Ϣ��ѧ��������
				   tsgButton.setIcon(new ImageIcon(getClass().getResource("/Image/ͼ���1.png")));
				   tsgButton.updateUI();
				   yktButton.setIcon(new ImageIcon(getClass().getResource("/Image/һ��ͨ����1.png")));
				   yktButton.updateUI(); 
				   sdButton.setIcon(new ImageIcon(getClass().getResource("/Image/�̵�2.png")));
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
				
//				���ð�ť״̬	
				mainButton.addMouseListener(new myButtonListener(mainButton,"Image/��ҳ1.png","Image/��ҳ2.png"));
				   yktButton.addMouseListener(new myButtonListener(yktButton,"Image/һ��ͨ����1.png","Image/һ��ͨ����2.png"));
				   tsgButton.addMouseListener(new myButtonListener(tsgButton,"Image/ͼ���1.png","Image/ͼ���2.png"));
				   xjglButton.addMouseListener(new myButtonListener(xjglButton,"Image/ѧ������1.png","Image/ѧ������2.png"));
				   sdButton.addMouseListener(new myButtonListener(sdButton,"Image/�̵�1.png","Image/�̳�2.png"));
				   

				
				 mainButton.setIcon(new ImageIcon(getClass().getResource("/Image/��ҳ1.png")));
				    mainButton.updateUI();
				   xszxButton.setIcon(new ImageIcon(getClass().getResource("/Image/ѧ������2.png")));
				   xszxButton.updateUI();
				   xjglButton.setIcon(new ImageIcon(getClass().getResource("/Image/ѧ������1.png")));
				   xjglButton.updateUI();
				   xszxButton.addMouseListener(new myButtonListener(xszxButton,"Image/ѧ������2.png","Image/ѧ������2.png"));// = new JButton(new ImageIcon());//��ͮ�ģ���ͥ��Ա��Ϣ��ѧ��������
				   tsgButton.setIcon(new ImageIcon(getClass().getResource("/Image/ͼ���1.png")));
				   tsgButton.updateUI();
				   yktButton.setIcon(new ImageIcon(getClass().getResource("/Image/һ��ͨ����1.png")));
				   yktButton.updateUI(); 
				   sdButton.setIcon(new ImageIcon(getClass().getResource("/Image/�̵�1.png")));
				   sdButton.updateUI();
				}});
		
		close.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				int value=JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�ر���","��ʾ",JOptionPane.OK_CANCEL_OPTION);
			    if (value==JOptionPane.OK_OPTION) {
			    	if(MyData.socket!=null){
						ObjectOutputStream toServer = MyData.toServer;
						ObjectInputStream fromServer = MyData.fromServer;
						if(MyData.user==null)
						System.out.println("user is null");
						Message msg = new Message(0,0,MyData.user);
						System.out.println(MyData.user.getUId()+"����");
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
		mainButton.setBorderPainted(false);//��ť�����߿�
		mainButton.setFocusPainted(false);
		mainButton.addMouseListener(new myButtonListener(mainButton,"Image/��ҳ1.png","Image/��ҳ2.png"));
		mainButton.setContentAreaFilled(false);
		mainButton.setPressedIcon(new ImageIcon(getClass().getResource("/Image/��ҳ3.png")));
		
		yktButton.setBackground(new Color(0,0,0));
		yktButton.setOpaque(false);
		yktButton.setBorderPainted(false);//��ť�����߿�
		yktButton.setFocusPainted(false);
		yktButton.addMouseListener(new myButtonListener(yktButton,"Image/һ��ͨ����1.png","Image/һ��ͨ����2.png"));
		yktButton.setContentAreaFilled(false);
		yktButton.setPressedIcon(new ImageIcon(getClass().getResource("/Image/һ��ͨ����3.png")));
		
		tsgButton.setBackground(new Color(0,0,0));
		tsgButton.setOpaque(false);
		tsgButton.setBorderPainted(false);//��ť�����߿�
		tsgButton.setFocusPainted(false);
		tsgButton.addMouseListener(new myButtonListener(tsgButton,"Image/ͼ���1.png","Image/ͼ���2.png"));
		tsgButton.setContentAreaFilled(false);
		tsgButton.setPressedIcon(new ImageIcon(getClass().getResource("/Image/ͼ���3.png")));
		
		xjglButton.setBackground(new Color(0,0,0));
		xjglButton.setOpaque(false);
		xjglButton.setBorderPainted(false);//��ť�����߿�
		xjglButton.setFocusPainted(false);
		xjglButton.addMouseListener(new myButtonListener(xjglButton,"Image/ѧ������1.png","Image/ѧ������2.png"));
		xjglButton.setContentAreaFilled(false);
		xjglButton.setPressedIcon(new ImageIcon(getClass().getResource("/Image/ѧ������3.png")));
		
		sdButton.setBackground(new Color(0,0,0));
		sdButton.setOpaque(false);
		sdButton.setBorderPainted(false);//��ť�����߿�
		sdButton.setFocusPainted(false);
		sdButton.addMouseListener(new myButtonListener(sdButton,"Image/�̵�1.png","Image/�̳�2.png"));
		sdButton.setContentAreaFilled(false);
		sdButton.setPressedIcon(new ImageIcon(getClass().getResource("/Image/�̳�3.png")));
		
		
		xszxButton.setBackground(new Color(0,0,0));
		xszxButton.setOpaque(false);
		xszxButton.setBorderPainted(false);//��ť�����߿�
		xszxButton.setFocusPainted(false);
		xszxButton.addMouseListener(new myButtonListener(xszxButton,"Image/ѧ������1.png","Image/ѧ������2.png"));
		xszxButton.setContentAreaFilled(false);
		xszxButton.setPressedIcon(new ImageIcon(getClass().getResource("/Image/ѧ������3.png")));
		
		close.setBackground(new Color(0,0,0));
		close.setOpaque(false);
		close.setBorderPainted(false);//��ť�����߿�
		close.setFocusPainted(false);
		close.addMouseListener(new myButtonListener(close,"Image/close1.png","Image/close2.png"));
		close.setContentAreaFilled(false);
		close.setPressedIcon(new ImageIcon(getClass().getResource("/Image/close3.png")));
		
		minBtn.setBackground(new Color(0,0,0));
		minBtn.setOpaque(false);
		minBtn.setBorderPainted(false);//��ť�����߿�
		minBtn.setFocusPainted(false);
		minBtn.addMouseListener(new myButtonListener(minBtn,"Image/MinBtn1.png","Image/MinBtn2.png"));
		minBtn.setContentAreaFilled(false);
		minBtn.setPressedIcon(new ImageIcon(getClass().getResource("/Image/MinBtn3.png")));
		
		this.add(topPanel,BorderLayout.NORTH);
		this.add(mainPanel,BorderLayout.CENTER);//��Ϊ��ҳ��Ĭ����ʾ�ģ�����Ҫ�Ȱ����ӽ���
		

		this.setSize(950, 670); // ��ʼ���ڵĴ�С
		this.setLocationRelativeTo(null); // ���ô��ھ���
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter()  {    //�رմ����¼�
			public void windowClosing( WindowEvent e )   
			{   
				try {
					//ֻ�е�MyData.socket���ӳɹ�ʱ�����û���¼
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
		btn.setBorderPainted(false);//��ť�����߿�
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
	
	

	//�԰���ÿ����Ĳ˵���ťע����������ֱ���Ӧ����ʾ�����
	class MyListen implements ActionListener {

		private int i =0;
		String [] top = new String[]{"/Image/ss.jpg","/Image/top2_y.jpg","/Image/sj.jpg","/Image/green.jpg"};
		String [] s = new String[]{"/Image/center2.jpg","/Image/skin2_y.jpg","/Image/aa.jpg","/Image/world64.jpg"};
		String [] bottom = new String[]{"/Image/bottom.png","/Image/bottom_g.png","/Image/bottom_r.png","/Image/bottom_p.png"};
		public void actionPerformed(ActionEvent arg0) {
			i++;
			i=i%4;
			mainPanel.setBack(s[i]);//��ҳ
			topPanel.setBack(top[i]);//��ͷ
			bottomPanel.setBack(bottom[i]);//�Ͷ�
			xszxPanel.setBack(s[i]);//ѧ������
			xjglPanel.setBack(s[i]) ;//ѧ������
			tsgPanel.setBack(s[i]);//ͼ���
			yktPanel.setBack(s[i]);//һ��ͨ����
			sdPanel.setBack(s[i]);//�̵�
		}
	
	}
}
