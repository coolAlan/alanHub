package vSelectCourse.view;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyButtonRender implements TableCellRenderer
{
    private JPanel panel;
    private JButton button;
    private boolean sign;

    //���캯��
    public MyButtonRender(boolean sign)
    {
    	this.sign=sign;
        this.initButton();
        this.initPanel();
        // ��Ӱ�ť
        this.panel.add(this.button);
    }

    //��ʼ��this.button
    private void initButton()
    {
        this.button = new JButton();
    }

    //��ʼ��this.panel
    private void initPanel()
    {
        this.panel = new JPanel();
        panel.setOpaque(false);
        // panelʹ�þ��Զ�λ������button�Ͳ������������Ԫ��
        //this.panel.setLayout(null);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
            int column)
    {
        // ֻΪ��ť��ֵ���ɡ�Ҳ������������������汳���ȡ�
    	if(sign==true){
    		this.button.setText("ѡ��");   
    	}
    	else{
    		this.button.setText("��ѡ");
    	}
    	
        return this.panel;
    }

}















