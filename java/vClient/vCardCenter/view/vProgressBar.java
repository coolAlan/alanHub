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
	

//	 ������ش��ڴ�С   
    public static final int LOAD_WIDTH = 455;  
    public static final int LOAD_HEIGHT = 295;  
    // ��ȡ��Ļ���ڴ�С   
    public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;  
    public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;  
    // ������������   
    public JProgressBar progressbar;  
    // �����ǩ���   
    public JLabel label;  

	public vProgressBar() {
//		 ������ǩ,���ڱ�ǩ�Ϸ���һ��ͼƬ   
        label = new JLabel(new ImageIcon(getClass().getResource("/Image/8.gif")));
        
        label.setBounds(0, 0, LOAD_WIDTH, LOAD_HEIGHT - 15);  
        // ����������   
        progressbar = new JProgressBar();  
        // ��ʾ��ǰ����ֵ��Ϣ   
        progressbar.setStringPainted(true);  
        // ���ý������߿���ʾ   
        progressbar.setBorderPainted(false);  
        // ���ý�������ǰ��ɫ   
        progressbar.setForeground(new Color(0, 210, 40));  
        // ���ý������ı���ɫ   
        progressbar.setBackground(new Color(188, 190, 194));  
        progressbar.setBounds(0, LOAD_HEIGHT - 15, LOAD_WIDTH, 15);  
        // ������   
        this.add(label);  
        this.add(progressbar);  
        // ���ò���Ϊ��   
        this.setLayout(null);  
        // ���ô��ڳ�ʼλ��   
        this.setLocation((WIDTH - LOAD_WIDTH) / 2, (HEIGHT - LOAD_HEIGHT) / 2);  
        // ���ô��ڴ�С   
        this.setSize(LOAD_WIDTH, LOAD_HEIGHT);  
        // ���ô�����ʾ   
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
