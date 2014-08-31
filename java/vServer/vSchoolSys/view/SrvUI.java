/*
 * Created on 2013-8-31
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package vSchoolSys.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import publicData.ClientIndividual;
import publicData.MyEvent;
import publicData.SrvData;
import vSchoolSys.biz.IServerSrvImpl;

/**
 * @author Alan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SrvUI {
	
	//public static void main(String args[]){
	//	new SrvUI();
	//}
	public SrvUI(){
		new IServerSrvImpl().start();
		
	}
	 

}
