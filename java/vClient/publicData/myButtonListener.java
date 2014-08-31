/*
 * Created on 2013-9-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package publicData;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Alan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class myButtonListener extends MouseAdapter{

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
