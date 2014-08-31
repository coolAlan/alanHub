/*
 * Created on 2013-8-27
 *
 */
package vCardCenter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import publicData.MyData;
import publicData.MyEvent;
import publicData.MyListener;
import vCardCenter.biz.ILostReturn;
import vSchoolSys.common.User;
/**
 * @author shipeng
 *
 * ʵ�ֹ�ʧ���һصĽ���
 */
public class vLostReturn extends JPanel {

	private JRadioButton jrbLost, jrbReturn;
	private static Vector vectorListeners=new Vector();
	/**
	 * һ��ͨ���ĵĹ�ʧ���һ�
	 */
	public vLostReturn() {
		   //this.setTitle("��ʧ���һ�");
	       JPanel pane = new JPanel();
	      
	       pane.setLayout(new BorderLayout());
		   JPanel jpRadioButtons = new JPanel();
		   jpRadioButtons.setOpaque(false);
		   jpRadioButtons.setLayout(new GridLayout(2, 1));
		  // this.setForeground(new Color(253,255,199));
		   jpRadioButtons.add(jrbReturn = new JRadioButton("���һ�"));
		   jpRadioButtons.add(jrbLost = new JRadioButton("��ʧ"));
		   //this.getContentPane().add(jpRadioButtons, BorderLayout.CENTER);
		   this.add(jpRadioButtons, BorderLayout.CENTER);

		   ButtonGroup group = new ButtonGroup();
		   group.add(jrbReturn);
		   group.add(jrbLost);
		   User user = MyData.getUser();
		   if(user.getLost()==0)
		   jrbReturn.setSelected(true);
		   if(user.getLost()==1)
		   jrbLost.setSelected(true);
		   
		   jrbReturn.setMnemonic('R');
		   jrbLost.setMnemonic('L');
		    
		   JButton jbt_ok = new JButton("ȷ��");
		   pane.add(jpRadioButtons,BorderLayout.CENTER);
		   JPanel jp = new JPanel();
		   jp.setOpaque(false);
		   jp.add(jbt_ok);
		   pane.add(jp,BorderLayout.SOUTH);
		   pane.setOpaque(false);
		   jbt_ok.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				//new MyEvent(vLostReturn.class);
				if(jrbLost.isSelected()){
					User user = MyData.getUser();
					user.setLost(1);//1��ʾ��ʧ
					MyData myData = new MyData();
					myData.setUser(user);//�޸ı��ص�user���������
					ILostReturn lost= new ILostReturn();
					if(new Integer((Integer)lost.getMessage().getData().get(0)).intValue()==1){
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						}
					
				}
				else{
					User user = MyData.getUser();
					user.setLost(0);//0��ʾ�һ�
					MyData myData = new MyData();
					myData.setUser(user);//�޸ı��ص�user���������
					ILostReturn lost= new ILostReturn();
					if(new Integer((Integer)lost.getMessage().getData().get(0)).intValue()==1){
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						}
					
				}
				processEvent();
				
			}
		   	
		   });
		   this.add(pane,BorderLayout.NORTH);
		  // pane.setBackground(new Color(253,255,199));
		   setPreferredSize(new Dimension(200,189));
			//pack();
		   this.setOpaque(false);
		   setVisible(true);
		
	}
	    public synchronized void addMyListener(MyListener ml)
	    {
	        vectorListeners.addElement(ml);
	    }
	    
	    public synchronized void removeMyListener(MyListener ml)
	    {
	        vectorListeners.removeElement(ml);
	    }
	    
	    protected void activateMyEvent()
	    {
	        Vector tempVector=null;
	        
	        MyEvent e=new MyEvent(this);
	        
	        synchronized(this)
	        {
	            tempVector=(Vector)vectorListeners.clone();
	            
	            for(int i=0;i<tempVector.size();i++)
	            {
	                MyListener ml=(MyListener)tempVector.elementAt(i);
	                ml.EventActivated(e);
	            }
	        }
	        
	    }
//	  ����һ�����÷������ڴ����¼�
	    public void processEvent()
	    {
	    	System.out.println("�¼�����");
	        activateMyEvent();
	    }   
	
	

}
