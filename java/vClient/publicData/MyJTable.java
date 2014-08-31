package publicData;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class MyJTable extends JTable {

	
	//重要的变量，作为状态监测
	static int current = -1;
	static int Selected = 0;
	
	public MyJTable(final int rowCount, int columnCount) {
		
		super(rowCount,columnCount);

		//传入自定义表格渲染器RoutineColor
		for (int i = 1; i < columnCount; i++) {
			this.setDefaultRenderer(this.getColumnClass(i), new RoutineColor() );
		}
		
		
		
  		//给表格添加鼠标移动的监听，鼠标移动刷新表格颜色
  		this.addMouseMotionListener(new MouseMotionAdapter() {
  			public void mouseMoved(MouseEvent e) {
  				Point p = e.getPoint();
  				//关键：设置current
  				current = rowAtPoint(p);
  			}
  		});
  		
  		
  
		//给表格添加鼠标退出表格的监听，鼠标退出表格，使其颜色还原
  		this.addMouseListener(new MouseAdapter() {
  		@Override
  		public void mouseExited(MouseEvent e) {
  			current = -1;
  			}
  	     });
  	     
 	}


	//表格的渲染器
	class RoutineColor extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
		
			if (row % 2 == 0) {
				// 设置偶数行的背景颜色
				setBackground(new Color(236, 246, 248));
			} else {
				// 基数行的背景颜色
				setBackground(new Color(255, 255, 255));
			}
			if (row == MyJTable.current) {
				//鼠标悬浮行的颜色
				setBackground(new Color(154,221,151));
			}
			
			//这里主要是为了针对选课表格，针对已选课程行设置背景
			if(  ((String)table.getValueAt(row, table.getColumnCount()-1)).equals("已选")     ){
				setBackground(new Color(145, 204, 243));
			}
		
			//单元格内容居中方法——继承Label类的方法, 设置table的单元格对齐方式
			setHorizontalAlignment((int) Component.CENTER_ALIGNMENT); // 水平居中
			setHorizontalTextPosition((int) Component.CENTER_ALIGNMENT); // 垂直居中
		
			table.getTableHeader().setBackground(Color.BLACK);//设置表头的字体色
			table.getTableHeader().setBackground(SystemColor.controlHighlight);//设置表头的字体色
		
			
			table.setSelectionBackground(new Color(154,221,151));//设置选中行的背景色
			table.setSelectionForeground(new Color(247, 81, 53));//设置选中行的前景色
		
			return super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
		}
 
		// 表格列根据内容调整大小
		public void adjustTableColumnWidths(JTable table) {
			
			JTableHeader header = table.getTableHeader(); // 表头
			int rowCount = table.getRowCount(); // 表格的行数
			TableColumnModel cm = table.getColumnModel(); // 表格的列模型
			for (int i = 0; i < cm.getColumnCount(); i++) { // 循环处理每一列
				TableColumn column = cm.getColumn(i); // 第i个列对象
				int width = (int) header.getDefaultRenderer()
						.getTableCellRendererComponent(table,
								column.getIdentifier(), false, false, -1, i)
								.getPreferredSize().getWidth(); // 用表头的绘制器计算第i列表头的宽度
				for (int row = 0; row < rowCount; row++) { // 循环处理第i列的每一行，用单元格绘制器计算第i列第row行的单元格度
					int preferedWidth = (int) table.getCellRenderer(row, i)
							.getTableCellRendererComponent(table,
									table.getValueAt(row, i), false, false, row, i)
									.getPreferredSize().getWidth();
					width = Math.max(width, preferedWidth); // 取最大的宽度
				}
				column.setPreferredWidth(width + table.getIntercellSpacing().width); // 设置第i列的首选宽度
			}
			table.doLayout(); // 按照刚才设置的宽度重新布局各个列
		}
	}

}
