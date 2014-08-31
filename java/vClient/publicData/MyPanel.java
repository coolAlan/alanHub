package publicData;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	private String address;
	public MyPanel(String addr){
		address = addr;
	}
	public void setBack(String str){
		address = str;
		repaint();
	}
	public void paintComponent(Graphics g) {
		int x = 0, y = 0;
		ImageIcon icon0 = new ImageIcon(getClass().getResource(address));
		g.drawImage(icon0.getImage(), x, y, getSize().width,getSize().height, this);// 图片会自动缩放
	}
}
