/*
 * Created on 2013-8-27
 *
 */
package vCardCenter.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import publicData.MyData;
import vCardCenter.biz.IDecrease;
import vCardCenter.biz.IIncrease;
import vSchoolSys.common.User;

/**
 * @author C4534
 *ʵ�ֳ�ֵ��ɷѵĽ���
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class vIncreConsu extends JPanel {
	
	private JRadioButton jrbLost, jrbReturn;
	private Vector vectorListeners=new Vector();
	private JTextField jtf_money = new JTextField(9);
	private JPasswordField jtf_pwd = new JPasswordField(9);
	
	private JTextField jtf_cost = new JTextField(9);
	private JLabel label4 = new JLabel("��");
    private JComboBox combo = new JComboBox();
    private String [] text = {"ˮ��","���","������"};
    private double money;
    private String costName;
    private double [] cost ={(double)(int)(Math.random()*20000)/100,(double)(int)(Math.random()*25000)/100,(double)(int)(Math.random()*15000)/100+10};
	public vIncreConsu(int i){
		
	}
    public vIncreConsu(){
		//���������ˮ��Ѽ۸�
		//setTitle("��ֵ���ɷ�");
		//setModal(true);//ģ̬�Ի���
		//����tab
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.WRAP_TAB_LAYOUT);
		
		JPanel jpane1 = new JPanel(new BorderLayout()); 
		jpane1.setOpaque(false);
		//����tab1
		
		JLabel label1 = new JLabel("��ֵ��");
		label1.setPreferredSize(new Dimension(70,20));
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label2 = new JLabel("��ֵ���룺");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		label2.setPreferredSize(new Dimension(70,20));
		
		 
		JPanel jpl_1 = new JPanel();
		jpl_1.setOpaque(false);
		jpl_1.setLayout(new FlowLayout());
		jpl_1.add(label1);
		jpl_1.add(jtf_money);
		
		
		JPanel jpl_2 = new JPanel();
		jpl_2.setOpaque(false);
		jpl_2.setLayout(new FlowLayout());
		jpl_2.add(label2);
		jpl_2.add(jtf_pwd);
		
		
		JPanel jpl_3 = new JPanel();
		jpl_3.setOpaque(false);
		JButton jbt_ok= new JButton("ȷ��");
		JButton jbt_cancel= new JButton("ȡ��");
		jpl_3.setLayout(new FlowLayout());
		jpl_3.add(jbt_ok);
		jpl_3.add(jbt_cancel);
		
		
		Box vertical = Box.createVerticalBox();
		
		vertical.add(jpl_1);
		vertical.add(jpl_2);
		vertical.add(jpl_3);

		jpane1.add(vertical,BorderLayout.CENTER);
		tabbedPane.addTab("��ֵ",jpane1);
		
		JPanel jpane2 = new JPanel(new BorderLayout()); 
		jpane2.setOpaque(false);
		//����tab2
		JLabel label3 = new JLabel("�ɷ���Ŀ��");
		label1.setPreferredSize(new Dimension(70,20));
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		label4.setPreferredSize(new Dimension(70,20));
		label4.setHorizontalAlignment(SwingConstants.RIGHT);
		jtf_cost.setEditable(false);
		
		for(int i=0;i<3;i++)
		combo.addItem(text[i]);
		combo.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				money = cost[combo.getSelectedIndex()];
				System.out.println(money);
				jtf_cost.setText(new Double(money).toString());
				costName = (String)combo.getSelectedItem();
			}
			
		});
		
		JPanel jpl_4 = new JPanel();
		jpl_4.setOpaque(false);
		jpl_4.setLayout(new FlowLayout());
		jpl_4.add(label3);
		jpl_4.add(combo);
		
		JPanel jpl_5 = new JPanel();
		jpl_5.setOpaque(false);
		jpl_5.setLayout(new FlowLayout());
		jpl_5.add(label4);
		jpl_5.add(jtf_cost);
		
		JButton jbt_yes= new JButton("�ɷ�");
		
		Box vertical2 = Box.createVerticalBox();
		
		vertical2.add(jpl_4);
		vertical2.add(jpl_5);
		vertical2.add(jbt_yes);
		
		jpane2.add(vertical2,BorderLayout.CENTER);
		tabbedPane.addTab("�ɷ�",jpane2);
		
		this.add(tabbedPane,BorderLayout.CENTER);
		this.setOpaque(false);
		setPreferredSize(new Dimension(300,250));
		//pack();
		jbt_yes.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				IDecrease decrease = new IDecrease(money,costName);
				//������Ϣȷ���ɷѳɹ�
				if(new Integer((Integer)decrease.getMessage().getData().get(0)).intValue()==1){
					JOptionPane.showMessageDialog(null,"�ɷѳɹ�");
					User user = MyData.getUser();
					user.setRemain(user.getRemain()-money);
					new MyData().setUser(user);
					//setVisible(false);
					}
			}
			
		});
		jbt_ok.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				IIncrease increase = new IIncrease(new Double(jtf_money.getText()).doubleValue(),jtf_pwd.getText());
                //������Ϣȷ���ɷѳɹ�
				if(new Integer((Integer)increase.getMessage().getData().get(0)).intValue()==1){
				JOptionPane.showMessageDialog(null,"��ֵ�ɹ�");
				User user = MyData.getUser();
				user.setRemain(new Double(jtf_money.getText()).doubleValue()+user.getRemain());
				new MyData().setUser(user);
				System.out.println("new remain :"+MyData.getUser().getRemain());
				jtf_money.setText("");
				jtf_pwd.setText("");
				//setVisible(false);
				}
				else
				{
					jtf_money.setText("");
					jtf_pwd.setText("");
					JOptionPane.showMessageDialog(null,"��ֵʧ��");
				}
			}
			
		});
		jbt_cancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				jtf_money.setText("");
				jtf_pwd.setText("");
				//setVisible(false);
				
			}
			
		});
	}
	   

}
