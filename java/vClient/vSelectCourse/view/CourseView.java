package vSelectCourse.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.table.*;

import publicData.MyData;
import publicData.MyJTable;
import publicData.MyPanel;

import vCardCenter.view.vIncreConsu;
import vCardCenter.view.vRecordShow;
import vSchoolSys.common.Message;
import vSchoolSys.common.User;
import vSelectCourse.biz.ICourseHelper;



public class CourseView  extends MyPanel
{  
	//属性：用于显示信息
    private MyJTable xkTable;  
    private MyJTable kbTable;   
    private JTable cjTable;  

    //主面板
    //public static  MyPanel mainPanel = new MyPanel("/image/center2.jpg");
   
    //菜单箱子
    protected Box xkBox = Box.createHorizontalBox();
   
    //内容主面板
    private JPanel xk_content =new JPanel();
    //内容1：选课面板
    protected JPanel panel2 = new JPanel();
    //内容2：课表面板
    protected JPanel panel3 = new JPanel();  
    //内容3：课表面板
    protected JPanel gradePanel = new JPanel();  
    
    //内容4：借教室面板
    protected JPanel roomPanel = new ClassroomPanel().getouterPanel();  
    
    
    
    
    //内容主面板的布局
    final CardLayout card = new CardLayout();
    
    //属性：课程服务类对象，提供服务
    private ICourseHelper courseHelper = new ICourseHelper();
  
    //构造函数
    public CourseView(String addr)  
    {  
    	super(addr);
        this.initialize();  
    }  
  
    //初始化
    private void initialize()  
    {  
    	
    	//初始化表格(顺序不可换)
    	initialCjTable();
        initialKbTable();    
        initialXkTable();        
        //初始化面板
    	initialBox();
    	initialPanel2();
    	initialPanel3();
    	initialGradePanel();
    	intialXkContent();
    	initialMainPanel();

    }  
    

    //初始化成绩表格
	private void initialCjTable() {
		// TODO Auto-generated method stub
		  

		 
        String[] cjColumnName = {"课程编号","课程名","课程成绩"};
          
        String[][] cjData = courseHelper.getGrade();
        
        this.cjTable = new MyJTable(cjData.length,cjColumnName.length){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		cjTable.setRowHeight(35);
		
        for(int i = 0;i<cjData.length;i++){
        	if( cjData[i][2].equals("0") ){
        		 cjData[i][2] = "未考";
        	}
        }
   
        DefaultTableModel cjDefaultTable = new DefaultTableModel(cjData,cjColumnName); 
        this.cjTable.setModel(cjDefaultTable); 
        
        //设置列宽不可调整
        for(int c= 0;c<3;c++){
        	cjTable.getColumnModel().getColumn(c).setResizable(false);
        }
        //设置列不可移动调换顺序
        cjTable.getTableHeader().setReorderingAllowed(false); 
        //自动排序功能
        TableRowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(cjTable.getModel());
        cjTable.setRowSorter(sorter2);
        sorter2.setRowFilter(RowFilter.regexFilter("0.*",0));
		
	}

	//菜单箱子
    private void initialBox() {
      
    	JButton kbButton = new JButton("课表查询",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
    	kbButton.setHorizontalTextPosition(0);
		btnDecorate(kbButton);
    	JButton xkButton = new JButton("课程选择",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
    	xkButton.setHorizontalTextPosition(0);
		btnDecorate(xkButton);
		JButton cjButton = new JButton("成绩查询",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		cjButton.setHorizontalTextPosition(0);
		btnDecorate(cjButton);
		JButton jsButton = new JButton("教室借用",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		jsButton.setHorizontalTextPosition(0);
		btnDecorate(jsButton);
		
		kbButton.setPreferredSize(new Dimension(5,35));
		xkButton.setPreferredSize(new Dimension(5,35));
		cjButton.setPreferredSize(new Dimension(5,35));
		jsButton.setPreferredSize(new Dimension(5,35));
		
		
    	xkBox.add(Box.createHorizontalStrut(5));
    	xkBox.add(xkButton);
    	xkBox.add(Box.createHorizontalStrut(5));
    	xkBox.add(kbButton);
    	xkBox.add(Box.createHorizontalStrut(5));
    	xkBox.add(cjButton);
    	xkBox.add(Box.createHorizontalStrut(5));
    	xkBox.add(jsButton);

    	xkButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			card.show(xk_content,"选课");
    		}
    	});
     	kbButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			card.show(xk_content,"课表");
    		}
    	});
     	cjButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			card.show(xk_content,"成绩");
    		}
    	});
     	jsButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			card.show(xk_content,"教室");
    		}
    	});
	}
	
    //内容主面板
    private void intialXkContent() {
    	xk_content.setOpaque(false);
    	xk_content.setLayout(card);		//设置卡片布局
    	xk_content.add("选课",panel2);	//选课卡片
    	xk_content.add("课表",panel3);	//课表卡片
    	xk_content.add("成绩",gradePanel);	//成绩卡片
    	xk_content.add("教室",roomPanel);	//借教室
	}
    //内容1：选课面板  
	private void initialPanel2() {
        //panel2.setLayout(new BorderLayout());
		 panel2.setOpaque(false);
		
        JScrollPane js = new JScrollPane(xkTable);
        js.setPreferredSize(new Dimension(850, 480));
        //设置js背景透明
        js.setOpaque(false);   
        js.getViewport().setOpaque(false); 
        //
        //panel2.add(js,BorderLayout.NORTH);
        //panel2.setLayout(new BorderLayout());
        panel2.setLayout(new FlowLayout());
        panel2.add(js);
	}
	//内容2：课表面板
	private void initialPanel3() {
        //panel3.setLayout(new BorderLayout());   
        panel3.setOpaque(false);
        
        JScrollPane js = new JScrollPane(kbTable);
        js.setPreferredSize(new Dimension(850, 480));
        //设置js背景透明
        js.setOpaque(false);   
        js.getViewport().setOpaque(false); 
        //
        //panel3.add(js,BorderLayout.CENTER);
        panel3.add(js);
        //这里先不将选课面板放入框架，放在点击kbButton事件中
	}
  
    //内容3:成绩面板
	private void initialGradePanel() {
		// TODO Auto-generated method stub
		gradePanel.setOpaque(false);

	    JScrollPane js = new JScrollPane(cjTable);
	    js.setPreferredSize(new Dimension(850, 480));
	    //设置js背景透明
	    js.setOpaque(false);   
	    js.getViewport().setOpaque(false); 
	    gradePanel.setLayout(new FlowLayout());
	    gradePanel.add(js);   
		
		
		
	}
	//选课表格 
    private void initialXkTable() {
   	

		  
        //xkTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //xkTable.setSelectionForeground(Color.GREEN);
        
        //表头名
        String[] xkColumnName = {"课程编号","课程名","任课老师","上课时间","学时","可选人数","已选人数","选课","退选","状态"};
        
        //得到所有课程信息   
        String[][] xkData = courseHelper.showAllCourse();
        
    	//设置表格属性
        this.xkTable = new MyJTable(xkData.length,xkColumnName.length){
			public boolean isCellEditable(int row, int column) {
				if(column == 7 || column == 8)
					return true;
				else
					return false;
			}
		};
        
        //设置表格模型并写入信息
        DefaultTableModel xkDefaultTable = new DefaultTableModel(xkData,xkColumnName); 
        
        //初始化用户选课状态
        courseHelper.initialState(xkDefaultTable);

        this.xkTable.setModel(xkDefaultTable); 
        
        //设置表格使第8,9列单元格放置JButton
        this.xkTable.getColumnModel().getColumn(7).setCellRenderer(new MyButtonRender(true));  
        this.xkTable.getColumnModel().getColumn(7).setCellEditor(new MyButtonEditor(this.xkTable,this.kbTable,this.cjTable,true));  
        this.xkTable.getColumnModel().getColumn(8).setCellRenderer(new MyButtonRender(false));  
        this.xkTable.getColumnModel().getColumn(8).setCellEditor(new MyButtonEditor(this.xkTable,this.kbTable,this.cjTable,false)); 
		
        
        //设置列宽不可调整
        TableColumn column1 = this.xkTable.getColumnModel().getColumn(3);//上课时间列
        column1.setPreferredWidth(100);//这个是设置列的宽度
        TableColumn column2 = this.xkTable.getColumnModel().getColumn(1);//上课时间列
        column2.setPreferredWidth(90);//这个是设置列的宽度 
        for(int c= 0;c<10;c++){
        	this.xkTable.getColumnModel().getColumn(c).setResizable(false);
        }
        //设置列不可移动调换顺序
        this.xkTable.getTableHeader().setReorderingAllowed(false); 
        //设置其他属性
        this.xkTable.setGridColor(Color.RED);
        this.xkTable.setRowHeight(35);
        
        //自动排序功能
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(xkTable.getModel());
        xkTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("0.*"));
        
	}
    //课表表格
  	private void initialKbTable() {

 

		 
        String[] kbColumnName = {"课程编号","课程名","任课老师","上课时间","学时"};
          
        String[][] kbData = courseHelper.showUserCourse();
   
        this.kbTable = new MyJTable(kbData.length,kbColumnName.length){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		kbTable.setRowHeight(35);
		
        DefaultTableModel kbDefaultTable = new DefaultTableModel(kbData,kbColumnName); 
        this.kbTable.setModel(kbDefaultTable); 
        
        //设置列宽不可调整
        for(int c= 0;c<5;c++){
        	kbTable.getColumnModel().getColumn(c).setResizable(false);
        }
      //设置列不可移动调换顺序
        kbTable.getTableHeader().setReorderingAllowed(false); 
        
        //自动排序功能
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(kbTable.getModel());
        kbTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("0.*",0));
  	}

	 //主面板
	private void initialMainPanel() {  
		this.setLayout(new BorderLayout());
	   	this.add(xkBox,BorderLayout.NORTH);
	    this.add(xk_content,BorderLayout.CENTER);	
	}
	
	
	public void btnDecorate(JButton btn){
		btn.setBackground(new Color(0,0,0));
		btn.setOpaque(false);
		btn.setBorderPainted(false);//按钮不画边框
		btn.setFocusPainted(false);
		btn.addMouseListener(new myButtonListener(btn,"Image/button-normal.png","Image/button-click.png"));
		btn.setContentAreaFilled(false);
		btn.setPressedIcon(new ImageIcon(getClass().getResource("/Image/button-hold.png")));
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