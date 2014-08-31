package vSelectCourse.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.*;

import publicData.MyData;

import vSchoolSys.common.CourseRecord;
import vSchoolSys.common.Message;
import vSelectCourse.biz.ICourseHelper;

//�Զ���һ�����������Ӱ�ť�ĵ�Ԫ��༭��,�̳�DefaultCellEditor
public class MyButtonEditor extends DefaultCellEditor  
{  
  
    /** 
     * serialVersionUID 
     */  
    private static final long serialVersionUID = -6546334664166791132L;  
  
    private JPanel panel;  
    private JButton button;  
    private boolean sign;
    
    private static JTable xkTable;
    private JTable kbTable;
    private JTable cjTable;
    
    //�γ̷�����,�ṩ����
    private ICourseHelper courseHelper = new ICourseHelper();
    
    public MyButtonEditor( JTable xkTable,JTable kbTable,JTable cjTable,boolean sign)  
    {  
        // DefautlCellEditor�Ĺ�����   
        super(new JTextField());       
        // ���õ�����μ���༭��   
        this.setClickCountToStart(1);  
  
        //��ʼ������
        this.xkTable = xkTable;
        this.kbTable = kbTable;    
        this.cjTable = cjTable;
        this.sign=sign;
                   
        this.initButton();     
        // ��Ӱ�ť
        this.panel = new JPanel(); 
        panel.setOpaque(false);
        this.panel.add(this.button);  
    }  
  
    private void initButton()  
    {  
        this.button = new JButton(); 
        

        // Ϊ��ť����¼�(ps:����ֻ�����ActionListner�¼���Mouse�¼���Ч)   
        this.button.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent e)  
            {  
            	if(sign==true){
            		//ѡ�ΰ�ť����¼�
            		xkButtonClick();
            	} 	
            	else{      
            		//��ѡ��ť����¼�
            		txButtonClick();
            	}       	             
            }  
        });  
  
    }  
    
    //����ѡ�ΰ�ť����¼�
    public void xkButtonClick(){

    	int row = xkTable.getSelectedRow();
    	int col = xkTable.getSelectedColumn();
    	
    	int yxCount = Integer.parseInt( (String) (xkTable.getValueAt(row, 6)));
		int kxCount = Integer.parseInt( (String) (xkTable.getValueAt(row, 5)));
		
		String sigal = (String) (xkTable.getValueAt(row, 9));
		
		String cID = (String) (xkTable.getValueAt(row, 0));   
		String cName = (String) (xkTable.getValueAt(row, 1)); 
		String uID = (MyData.getUser()).getUId();
		
    	String cTime = (String) (xkTable.getValueAt(row, 3));    
		
		if( sigal.equals("��ѡ")){
			//button.setEnabled(false);
			JOptionPane.showMessageDialog( null ,"����ѡ����ÿγ�");   
		}  		
		else if( courseHelper.Selectedable(uID,cTime) != null ){
			JOptionPane.showMessageDialog( null ,"ѡ��ʱ���ͻ��"+courseHelper.Selectedable(uID,cTime) );   
		}
		else if( yxCount < kxCount ){ 	
		
			//button.setEnabled(true);
			xkTable.setValueAt("��ѡ",row,9);
			xkTable.setValueAt(""+(yxCount+1),row,6);
			
			JOptionPane.showMessageDialog( null ,"ѡ�γɹ�");   
			
			/**
			 * ˢ�����ݿ�
			 */

			//��ӣ���"tblCourseList"���ѡ�μ�¼�������µ���ѡ������ѡ�μ�¼couRecord
			CourseRecord couRecord = new CourseRecord(cID,uID,0,cName);	
			int c = yxCount+1;
			courseHelper.addCourse(couRecord,c);
			
			/**
			 * ˢ�¿α����
			 */        			
			 kbTable.setRowSorter(null);
	        //��ѯ�����ָ��ID�û���ѡ�μ�¼		
			String[][] kbData = courseHelper.showUserCourse();	 
	        String[] kbColumnName = {"�γ̱��","�γ���","�ο���ʦ","�Ͽ�ʱ��","ѧʱ"};
	        DefaultTableModel defaultTable = new DefaultTableModel(kbData,kbColumnName);
	        kbTable.setModel(defaultTable); 
	        //
	        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(kbTable.getModel());
	        kbTable.setRowSorter(sorter);
	        sorter.setRowFilter(RowFilter.regexFilter("0.*",0));
	        		
	        //�ɼ����
	        cjTable.setRowSorter(null);
	        String[] cjColumnName = {"�γ̱��","�γ���","�γ̳ɼ�"};        
	        String[][] cjData = courseHelper.getGrade();
	        for(int i = 0;i<cjData.length;i++){
	        	if( cjData[i][2].equals("0") ){
	        		 cjData[i][2] = "δ��";
	        	}
	        }
	        DefaultTableModel cjDefaultTable = new DefaultTableModel(cjData,cjColumnName); 
	        System.out.println(cjData[1][1]);
	        cjTable.setModel(cjDefaultTable); 
	        
	        //
	        TableRowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(cjTable.getModel());
	        cjTable.setRowSorter(sorter2);
	        sorter2.setRowFilter(RowFilter.regexFilter("0.*",0));
	        
	        
    	       		
		}else{
			
			JOptionPane.showMessageDialog( null ,"��ѡ��ʧ�ܣ��ÿγ���������");    
		}
    }
  
    //������ѡ��ť����¼�
    public void txButtonClick(){
    	
    	int row = xkTable.getSelectedRow();
    	int col = xkTable.getSelectedColumn();
    	int yxCount = Integer.parseInt( (String) (xkTable.getValueAt(row, 6)));
		int kxCount = Integer.parseInt( (String) (xkTable.getValueAt(row, 5)));
		
		String sigal = (String) (xkTable.getValueAt(row, 9));
		String cName = (String) (xkTable.getValueAt(row, 1));
    	
		if( sigal.equals("δѡ") ){
			JOptionPane.showMessageDialog( null ,"��δѡ���ÿγ�");   
		}else{ 			          			        			

			xkTable.setValueAt("δѡ",row,9);
			xkTable.setValueAt(""+(yxCount-1),row,6);
			
			String cID = (String) (xkTable.getValueAt(row, 0));    
			String uID = (MyData.getUser()).getUId();
			CourseRecord couRecord = new CourseRecord(cID,uID,0,cName);
			
			JOptionPane.showMessageDialog( null ,"��ѡ�ɹ�"); 
			
			
			//ɾ�����ӡ�tblCourseList��ɾ����ѡ�μ�¼
			int c = yxCount-1;
			courseHelper.subCourse(couRecord ,c);
      
			
			kbTable.setRowSorter(null);
	        //��ѯ�����»�ø��û���ѡ�μ�¼
	        String[][] kbData = courseHelper.showUserCourse();
	        String[] kbColumnName = {"�γ̱��","�γ���","�ο���ʦ","�Ͽ�ʱ��","ѧʱ"};
	        DefaultTableModel defaultTable = new DefaultTableModel(kbData,kbColumnName);
	        kbTable.setModel(defaultTable); 
	        
	        //
	        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(kbTable.getModel());
	        kbTable.setRowSorter(sorter);
	        sorter.setRowFilter(RowFilter.regexFilter("0.*",0));
	        
	        //ˢ�³ɼ����
	        cjTable.setRowSorter(null);
	        
	        String[] cjColumnName = {"�γ̱��","�γ���","�γ̳ɼ�"};  
	        String[][] cjData = courseHelper.getGrade();
	        for(int i = 0;i<cjData.length;i++){
	        	if( cjData[i][2].equals("0") ){
	        		 cjData[i][2] = "δ��";
	        	}
	        }   
	        DefaultTableModel cjDefaultTable = new DefaultTableModel(cjData,cjColumnName); 
	        cjTable.setModel(cjDefaultTable); 
	        //
	        TableRowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(cjTable.getModel());
	        cjTable.setRowSorter(sorter2);
	        sorter2.setRowFilter(RowFilter.regexFilter("0.*",0));
    		         		
		}
    	
    }
    
    
    

    
    //������д����ı༭����������һ��JPanel���󼴿ɣ�Ҳ����ֱ�ӷ���һ��Button���󣬵��������������������Ԫ�� 
      
    @Override  
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)  
    {  
        //ʹ�����ťʱ��ť�ڲ�Text����
        this.button.setText(value == null ? "" : String.valueOf(value));    
        
        return this.panel;  
    }  
  
     
    // ��д�༭��Ԫ��ʱ��ȡ��ֵ���������д��������ܻ�Ϊ��ť���ô����ֵ�� 
    @Override  
    public Object getCellEditorValue()  
    {  
        return this.button.getText();  
    }
        
    };

