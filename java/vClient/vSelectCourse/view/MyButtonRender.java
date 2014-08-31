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

    //构造函数
    public MyButtonRender(boolean sign)
    {
    	this.sign=sign;
        this.initButton();
        this.initPanel();
        // 添加按钮
        this.panel.add(this.button);
    }

    //初始化this.button
    private void initButton()
    {
        this.button = new JButton();
    }

    //初始化this.panel
    private void initPanel()
    {
        this.panel = new JPanel();
        panel.setOpaque(false);
        // panel使用绝对定位，这样button就不会充满整个单元格。
        //this.panel.setLayout(null);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
            int column)
    {
        // 只为按钮赋值即可。也可以作其它操作，如绘背景等。
    	if(sign==true){
    		this.button.setText("选课");   
    	}
    	else{
    		this.button.setText("退选");
    	}
    	
        return this.panel;
    }

}















