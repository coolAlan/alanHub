/*
 * Created on 2013-8-28
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vCardCenter.view;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import MainUI.mainUI;


/**
 * @author C5140
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class vProgressBar extends JWindow implements Runnable {
	

//	 定义加载窗口大小   
    public static final int LOAD_WIDTH = 455;  
    public static final int LOAD_HEIGHT = 295;  
    // 获取屏幕窗口大小   
    public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;  
    public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;  
    // 定义进度条组件   
    public JProgressBar progressbar;  
    // 定义标签组件   
    public JLabel label;  

	public vProgressBar() {
//		 创建标签,并在标签上放置一张图片   
        label = new JLabel(new ImageIcon(getClass().getResource("/Image/8.gif")));
        
        label.setBounds(0, 0, LOAD_WIDTH, LOAD_HEIGHT - 15);  
        // 创建进度条   
        progressbar = new JProgressBar();  
        // 显示当前进度值信息   
        progressbar.setStringPainted(true);  
        // 设置进度条边框不显示   
        progressbar.setBorderPainted(false);  
        // 设置进度条的前景色   
        progressbar.setForeground(new Color(0, 210, 40));  
        // 设置进度条的背景色   
        progressbar.setBackground(new Color(188, 190, 194));  
        progressbar.setBounds(0, LOAD_HEIGHT - 15, LOAD_WIDTH, 15);  
        // 添加组件   
        this.add(label);  
        this.add(progressbar);  
        // 设置布局为空   
        this.setLayout(null);  
        // 设置窗口初始位置   
        this.setLocation((WIDTH - LOAD_WIDTH) / 2, (HEIGHT - LOAD_HEIGHT) / 2);  
        // 设置窗口大小   
        this.setSize(LOAD_WIDTH, LOAD_HEIGHT);  
        // 设置窗口显示   
        this.setVisible(true);  
  
	}
	
    public void run() {  
    	int i;
        for (i= 0; i < 19; i++) {  
            try {  
                Thread.sleep(85);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            progressbar.setValue(i);  
        } 
        for (i= 19; i <=100; i++) {  
            try {  
                Thread.sleep(15);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            progressbar.setValue(i);  
        } 
        SwingUtilities.invokeLater(new Runnable(){
			public void run(){
             new mainUI();
            }
        });
        this.dispose();  
  
    }  
	

}
