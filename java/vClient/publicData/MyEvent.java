/*
 * Created on 2013-8-27
 *
 */
package publicData;

import java.util.EventObject;

/**
 * @author shipeng
 * �Զ����¼�����һ��ͨ�Ĺ�ʧ״̬�ı�ʱ�������¼�
 */
public class MyEvent extends EventObject {

	
	public MyEvent(Object arg0) {
		super(arg0);
		System.out.println("��ʧ event !");
	}

}
