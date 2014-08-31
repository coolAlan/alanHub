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
	//���ԣ�������ʾ��Ϣ
    private MyJTable xkTable;  
    private MyJTable kbTable;   
    private JTable cjTable;  

    //�����
    //public static  MyPanel mainPanel = new MyPanel("/image/center2.jpg");
   
    //�˵�����
    protected Box xkBox = Box.createHorizontalBox();
   
    //���������
    private JPanel xk_content =new JPanel();
    //����1��ѡ�����
    protected JPanel panel2 = new JPanel();
    //����2���α����
    protected JPanel panel3 = new JPanel();  
    //����3���α����
    protected JPanel gradePanel = new JPanel();  
    
    //����4����������
    protected JPanel roomPanel = new ClassroomPanel().getouterPanel();  
    
    
    
    
    //���������Ĳ���
    final CardLayout card = new CardLayout();
    
    //���ԣ��γ̷���������ṩ����
    private ICourseHelper courseHelper = new ICourseHelper();
  
    //���캯��
    public CourseView(String addr)  
    {  
    	super(addr);
        this.initialize();  
    }  
  
    //��ʼ��
    private void initialize()  
    {  
    	
    	//��ʼ�����(˳�򲻿ɻ�)
    	initialCjTable();
        initialKbTable();    
        initialXkTable();        
        //��ʼ�����
    	initialBox();
    	initialPanel2();
    	initialPanel3();
    	initialGradePanel();
    	intialXkContent();
    	initialMainPanel();

    }  
    

    //��ʼ���ɼ����
	private void initialCjTable() {
		// TODO Auto-generated method stub
		  

		 
        String[] cjColumnName = {"�γ̱��","�γ���","�γ̳ɼ�"};
          
        String[][] cjData = courseHelper.getGrade();
        
        this.cjTable = new MyJTable(cjData.length,cjColumnName.length){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		cjTable.setRowHeight(35);
		
        for(int i = 0;i<cjData.length;i++){
        	if( cjData[i][2].equals("0") ){
        		 cjData[i][2] = "δ��";
        	}
        }
   
        DefaultTableModel cjDefaultTable = new DefaultTableModel(cjData,cjColumnName); 
        this.cjTable.setModel(cjDefaultTable); 
        
        //�����п��ɵ���
        for(int c= 0;c<3;c++){
        	cjTable.getColumnModel().getColumn(c).setResizable(false);
        }
        //�����в����ƶ�����˳��
        cjTable.getTableHeader().setReorderingAllowed(false); 
        //�Զ�������
        TableRowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(cjTable.getModel());
        cjTable.setRowSorter(sorter2);
        sorter2.setRowFilter(RowFilter.regexFilter("0.*",0));
		
	}

	//�˵�����
    private void initialBox() {
      
    	JButton kbButton = new JButton("�α��ѯ",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
    	kbButton.setHorizontalTextPosition(0);
		btnDecorate(kbButton);
    	JButton xkButton = new JButton("�γ�ѡ��",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
    	xkButton.setHorizontalTextPosition(0);
		btnDecorate(xkButton);
		JButton cjButton = new JButton("�ɼ���ѯ",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
		cjButton.setHorizontalTextPosition(0);
		btnDecorate(cjButton);
		JButton jsButton = new JButton("���ҽ���",new ImageIcon(getClass().getResource("/Image/button-normal.png")));
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
    			card.show(xk_content,"ѡ��");
    		}
    	});
     	kbButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			card.show(xk_content,"�α�");
    		}
    	});
     	cjButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			card.show(xk_content,"�ɼ�");
    		}
    	});
     	jsButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			card.show(xk_content,"����");
    		}
    	});
	}
	
    //���������
    private void intialXkContent() {
    	xk_content.setOpaque(false);
    	xk_content.setLayout(card);		//���ÿ�Ƭ����
    	xk_content.add("ѡ��",panel2);	//ѡ�ο�Ƭ
    	xk_content.add("�α�",panel3);	//�α�Ƭ
    	xk_content.add("�ɼ�",gradePanel);	//�ɼ���Ƭ
    	xk_content.add("����",roomPanel);	//�����
	}
    //����1��ѡ�����  
	private void initialPanel2() {
        //panel2.setLayout(new BorderLayout());
		 panel2.setOpaque(false);
		
        JScrollPane js = new JScrollPane(xkTable);
        js.setPreferredSize(new Dimension(850, 480));
        //����js����͸��
        js.setOpaque(false);   
        js.getViewport().setOpaque(false); 
        //
        //panel2.add(js,BorderLayout.NORTH);
        //panel2.setLayout(new BorderLayout());
        panel2.setLayout(new FlowLayout());
        panel2.add(js);
	}
	//����2���α����
	private void initialPanel3() {
        //panel3.setLayout(new BorderLayout());   
        panel3.setOpaque(false);
        
        JScrollPane js = new JScrollPane(kbTable);
        js.setPreferredSize(new Dimension(850, 480));
        //����js����͸��
        js.setOpaque(false);   
        js.getViewport().setOpaque(false); 
        //
        //panel3.add(js,BorderLayout.CENTER);
        panel3.add(js);
        //�����Ȳ���ѡ���������ܣ����ڵ��kbButton�¼���
	}
  
    //����3:�ɼ����
	private void initialGradePanel() {
		// TODO Auto-generated method stub
		gradePanel.setOpaque(false);

	    JScrollPane js = new JScrollPane(cjTable);
	    js.setPreferredSize(new Dimension(850, 480));
	    //����js����͸��
	    js.setOpaque(false);   
	    js.getViewport().setOpaque(false); 
	    gradePanel.setLayout(new FlowLayout());
	    gradePanel.add(js);   
		
		
		
	}
	//ѡ�α�� 
    private void initialXkTable() {
   	

		  
        //xkTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //xkTable.setSelectionForeground(Color.GREEN);
        
        //��ͷ��
        String[] xkColumnName = {"�γ̱��","�γ���","�ο���ʦ","�Ͽ�ʱ��","ѧʱ","��ѡ����","��ѡ����","ѡ��","��ѡ","״̬"};
        
        //�õ����пγ���Ϣ   
        String[][] xkData = courseHelper.showAllCourse();
        
    	//���ñ������
        this.xkTable = new MyJTable(xkData.length,xkColumnName.length){
			public boolean isCellEditable(int row, int column) {
				if(column == 7 || column == 8)
					return true;
				else
					return false;
			}
		};
        
        //���ñ��ģ�Ͳ�д����Ϣ
        DefaultTableModel xkDefaultTable = new DefaultTableModel(xkData,xkColumnName); 
        
        //��ʼ���û�ѡ��״̬
        courseHelper.initialState(xkDefaultTable);

        this.xkTable.setModel(xkDefaultTable); 
        
        //���ñ��ʹ��8,9�е�Ԫ�����JButton
        this.xkTable.getColumnModel().getColumn(7).setCellRenderer(new MyButtonRender(true));  
        this.xkTable.getColumnModel().getColumn(7).setCellEditor(new MyButtonEditor(this.xkTable,this.kbTable,this.cjTable,true));  
        this.xkTable.getColumnModel().getColumn(8).setCellRenderer(new MyButtonRender(false));  
        this.xkTable.getColumnModel().getColumn(8).setCellEditor(new MyButtonEditor(this.xkTable,this.kbTable,this.cjTable,false)); 
		
        
        //�����п��ɵ���
        TableColumn column1 = this.xkTable.getColumnModel().getColumn(3);//�Ͽ�ʱ����
        column1.setPreferredWidth(100);//����������еĿ��
        TableColumn column2 = this.xkTable.getColumnModel().getColumn(1);//�Ͽ�ʱ����
        column2.setPreferredWidth(90);//����������еĿ�� 
        for(int c= 0;c<10;c++){
        	this.xkTable.getColumnModel().getColumn(c).setResizable(false);
        }
        //�����в����ƶ�����˳��
        this.xkTable.getTableHeader().setReorderingAllowed(false); 
        //������������
        this.xkTable.setGridColor(Color.RED);
        this.xkTable.setRowHeight(35);
        
        //�Զ�������
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(xkTable.getModel());
        xkTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("0.*"));
        
	}
    //�α���
  	private void initialKbTable() {

 

		 
        String[] kbColumnName = {"�γ̱��","�γ���","�ο���ʦ","�Ͽ�ʱ��","ѧʱ"};
          
        String[][] kbData = courseHelper.showUserCourse();
   
        this.kbTable = new MyJTable(kbData.length,kbColumnName.length){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		kbTable.setRowHeight(35);
		
        DefaultTableModel kbDefaultTable = new DefaultTableModel(kbData,kbColumnName); 
        this.kbTable.setModel(kbDefaultTable); 
        
        //�����п��ɵ���
        for(int c= 0;c<5;c++){
        	kbTable.getColumnModel().getColumn(c).setResizable(false);
        }
      //�����в����ƶ�����˳��
        kbTable.getTableHeader().setReorderingAllowed(false); 
        
        //�Զ�������
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(kbTable.getModel());
        kbTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("0.*",0));
  	}

	 //�����
	private void initialMainPanel() {  
		this.setLayout(new BorderLayout());
	   	this.add(xkBox,BorderLayout.NORTH);
	    this.add(xk_content,BorderLayout.CENTER);	
	}
	
	
	public void btnDecorate(JButton btn){
		btn.setBackground(new Color(0,0,0));
		btn.setOpaque(false);
		btn.setBorderPainted(false);//��ť�����߿�
		btn.setFocusPainted(false);
		btn.addMouseListener(new myButtonListener(btn,"Image/button-normal.png","Image/button-click.png"));
		btn.setContentAreaFilled(false);
		btn.setPressedIcon(new ImageIcon(getClass().getResource("/Image/button-hold.png")));
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