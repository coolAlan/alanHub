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

//自定义一个往列里边添加按钮的单元格编辑器,继承DefaultCellEditor
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
    
    //课程服务类,提供服务
    private ICourseHelper courseHelper = new ICourseHelper();
    
    public MyButtonEditor( JTable xkTable,JTable kbTable,JTable cjTable,boolean sign)  
    {  
        // DefautlCellEditor的构造器   
        super(new JTextField());       
        // 设置点击几次激活编辑。   
        this.setClickCountToStart(1);  
  
        //初始化属性
        this.xkTable = xkTable;
        this.kbTable = kbTable;    
        this.cjTable = cjTable;
        this.sign=sign;
                   
        this.initButton();     
        // 添加按钮
        this.panel = new JPanel(); 
        panel.setOpaque(false);
        this.panel.add(this.button);  
    }  
  
    private void initButton()  
    {  
        this.button = new JButton(); 
        

        // 为按钮添加事件(ps:这里只能添加ActionListner事件，Mouse事件无效)   
        this.button.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent e)  
            {  
            	if(sign==true){
            		//选课按钮点击事件
            		xkButtonClick();
            	} 	
            	else{      
            		//退选按钮点击事件
            		txButtonClick();
            	}       	             
            }  
        });  
  
    }  
    
    //处理选课按钮点击事件
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
		
		if( sigal.equals("已选")){
			//button.setEnabled(false);
			JOptionPane.showMessageDialog( null ,"你已选择过该课程");   
		}  		
		else if( courseHelper.Selectedable(uID,cTime) != null ){
			JOptionPane.showMessageDialog( null ,"选课时间冲突："+courseHelper.Selectedable(uID,cTime) );   
		}
		else if( yxCount < kxCount ){ 	
		
			//button.setEnabled(true);
			xkTable.setValueAt("已选",row,9);
			xkTable.setValueAt(""+(yxCount+1),row,6);
			
			JOptionPane.showMessageDialog( null ,"选课成功");   
			
			/**
			 * 刷新数据库
			 */

			//添加：向"tblCourseList"添加选课记录，传入新的已选人数，选课记录couRecord
			CourseRecord couRecord = new CourseRecord(cID,uID,0,cName);	
			int c = yxCount+1;
			courseHelper.addCourse(couRecord,c);
			
			/**
			 * 刷新课表界面
			 */        			
			 kbTable.setRowSorter(null);
	        //查询：获得指定ID用户的选课记录		
			String[][] kbData = courseHelper.showUserCourse();	 
	        String[] kbColumnName = {"课程编号","课程名","任课老师","上课时间","学时"};
	        DefaultTableModel defaultTable = new DefaultTableModel(kbData,kbColumnName);
	        kbTable.setModel(defaultTable); 
	        //
	        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(kbTable.getModel());
	        kbTable.setRowSorter(sorter);
	        sorter.setRowFilter(RowFilter.regexFilter("0.*",0));
	        		
	        //成绩表格
	        cjTable.setRowSorter(null);
	        String[] cjColumnName = {"课程编号","课程名","课程成绩"};        
	        String[][] cjData = courseHelper.getGrade();
	        for(int i = 0;i<cjData.length;i++){
	        	if( cjData[i][2].equals("0") ){
	        		 cjData[i][2] = "未考";
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
			
			JOptionPane.showMessageDialog( null ,"，选课失败，该课程人数已满");    
		}
    }
  
    //处理退选按钮点击事件
    public void txButtonClick(){
    	
    	int row = xkTable.getSelectedRow();
    	int col = xkTable.getSelectedColumn();
    	int yxCount = Integer.parseInt( (String) (xkTable.getValueAt(row, 6)));
		int kxCount = Integer.parseInt( (String) (xkTable.getValueAt(row, 5)));
		
		String sigal = (String) (xkTable.getValueAt(row, 9));
		String cName = (String) (xkTable.getValueAt(row, 1));
    	
		if( sigal.equals("未选") ){
			JOptionPane.showMessageDialog( null ,"你未选过该课程");   
		}else{ 			          			        			

			xkTable.setValueAt("未选",row,9);
			xkTable.setValueAt(""+(yxCount-1),row,6);
			
			String cID = (String) (xkTable.getValueAt(row, 0));    
			String uID = (MyData.getUser()).getUId();
			CourseRecord couRecord = new CourseRecord(cID,uID,0,cName);
			
			JOptionPane.showMessageDialog( null ,"退选成功"); 
			
			
			//删除：从“tblCourseList”删除该选课记录
			int c = yxCount-1;
			courseHelper.subCourse(couRecord ,c);
      
			
			kbTable.setRowSorter(null);
	        //查询：重新获得该用户的选课记录
	        String[][] kbData = courseHelper.showUserCourse();
	        String[] kbColumnName = {"课程编号","课程名","任课老师","上课时间","学时"};
	        DefaultTableModel defaultTable = new DefaultTableModel(kbData,kbColumnName);
	        kbTable.setModel(defaultTable); 
	        
	        //
	        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(kbTable.getModel());
	        kbTable.setRowSorter(sorter);
	        sorter.setRowFilter(RowFilter.regexFilter("0.*",0));
	        
	        //刷新成绩表格
	        cjTable.setRowSorter(null);
	        
	        String[] cjColumnName = {"课程编号","课程名","课程成绩"};  
	        String[][] cjData = courseHelper.getGrade();
	        for(int i = 0;i<cjData.length;i++){
	        	if( cjData[i][2].equals("0") ){
	        		 cjData[i][2] = "未考";
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
    
    
    

    
    //这里重写父类的编辑方法，返回一个JPanel对象即可（也可以直接返回一个Button对象，但是那样会填充满整个单元格） 
      
    @Override  
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)  
    {  
        //使点击按钮时按钮内部Text不变
        this.button.setText(value == null ? "" : String.valueOf(value));    
        
        return this.panel;  
    }  
  
     
    // 重写编辑单元格时获取的值。如果不重写，这里可能会为按钮设置错误的值。 
    @Override  
    public Object getCellEditorValue()  
    {  
        return this.button.getText();  
    }
        
    };

