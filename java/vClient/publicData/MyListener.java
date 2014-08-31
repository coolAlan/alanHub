/*
 * Created on 2013-9-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package publicData;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * @author Alan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MyListener implements java.util.EventListener{


	private JButton jb_jlcx;
	private JButton jb_czjf;
	private JButton sdButton;
	public MyListener(JButton bt1, JButton bt2,JButton sdButton) {
		jb_jlcx = bt1;
		jb_czjf = bt2;
		this.sdButton= sdButton;
	}
	 public void EventActivated(MyEvent me)
	    {
	        System.out.println("事件已经被触发");
	        if(MyData.getUser()!=null){
	        	if(MyData.getUser().getLost()==1)
	        	{
	        		JOptionPane.showMessageDialog(null, "账号已挂失，部分功能无法使用！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
	        		jb_jlcx.setEnabled(false);
	        		jb_czjf.setEnabled(false);
	        		sdButton.setEnabled(false);
	        	
	        	}
	        	else{
	        		jb_jlcx.setEnabled(true);
	        		jb_czjf.setEnabled(true);
	        		sdButton.setEnabled(true);
	        	}
	        }
	    }

}
