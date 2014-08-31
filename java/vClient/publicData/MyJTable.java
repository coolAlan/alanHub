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

	
	//��Ҫ�ı�������Ϊ״̬���
	static int current = -1;
	static int Selected = 0;
	
	public MyJTable(final int rowCount, int columnCount) {
		
		super(rowCount,columnCount);

		//�����Զ�������Ⱦ��RoutineColor
		for (int i = 1; i < columnCount; i++) {
			this.setDefaultRenderer(this.getColumnClass(i), new RoutineColor() );
		}
		
		
		
  		//������������ƶ��ļ���������ƶ�ˢ�±����ɫ
  		this.addMouseMotionListener(new MouseMotionAdapter() {
  			public void mouseMoved(MouseEvent e) {
  				Point p = e.getPoint();
  				//�ؼ�������current
  				current = rowAtPoint(p);
  			}
  		});
  		
  		
  
		//������������˳����ļ���������˳����ʹ����ɫ��ԭ
  		this.addMouseListener(new MouseAdapter() {
  		@Override
  		public void mouseExited(MouseEvent e) {
  			current = -1;
  			}
  	     });
  	     
 	}


	//������Ⱦ��
	class RoutineColor extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
		
			if (row % 2 == 0) {
				// ����ż���еı�����ɫ
				setBackground(new Color(236, 246, 248));
			} else {
				// �����еı�����ɫ
				setBackground(new Color(255, 255, 255));
			}
			if (row == MyJTable.current) {
				//��������е���ɫ
				setBackground(new Color(154,221,151));
			}
			
			//������Ҫ��Ϊ�����ѡ�α�������ѡ�γ������ñ���
			if(  ((String)table.getValueAt(row, table.getColumnCount()-1)).equals("��ѡ")     ){
				setBackground(new Color(145, 204, 243));
			}
		
			//��Ԫ�����ݾ��з��������̳�Label��ķ���, ����table�ĵ�Ԫ����뷽ʽ
			setHorizontalAlignment((int) Component.CENTER_ALIGNMENT); // ˮƽ����
			setHorizontalTextPosition((int) Component.CENTER_ALIGNMENT); // ��ֱ����
		
			table.getTableHeader().setBackground(Color.BLACK);//���ñ�ͷ������ɫ
			table.getTableHeader().setBackground(SystemColor.controlHighlight);//���ñ�ͷ������ɫ
		
			
			table.setSelectionBackground(new Color(154,221,151));//����ѡ���еı���ɫ
			table.setSelectionForeground(new Color(247, 81, 53));//����ѡ���е�ǰ��ɫ
		
			return super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
		}
 
		// ����и������ݵ�����С
		public void adjustTableColumnWidths(JTable table) {
			
			JTableHeader header = table.getTableHeader(); // ��ͷ
			int rowCount = table.getRowCount(); // ��������
			TableColumnModel cm = table.getColumnModel(); // ������ģ��
			for (int i = 0; i < cm.getColumnCount(); i++) { // ѭ������ÿһ��
				TableColumn column = cm.getColumn(i); // ��i���ж���
				int width = (int) header.getDefaultRenderer()
						.getTableCellRendererComponent(table,
								column.getIdentifier(), false, false, -1, i)
								.getPreferredSize().getWidth(); // �ñ�ͷ�Ļ����������i�б�ͷ�Ŀ��
				for (int row = 0; row < rowCount; row++) { // ѭ�������i�е�ÿһ�У��õ�Ԫ������������i�е�row�еĵ�Ԫ���
					int preferedWidth = (int) table.getCellRenderer(row, i)
							.getTableCellRendererComponent(table,
									table.getValueAt(row, i), false, false, row, i)
									.getPreferredSize().getWidth();
					width = Math.max(width, preferedWidth); // ȡ���Ŀ��
				}
				column.setPreferredWidth(width + table.getIntercellSpacing().width); // ���õ�i�е���ѡ���
			}
			table.doLayout(); // ���ող����õĿ�����²��ָ�����
		}
	}

}
