/*
 * Created on 2013-9-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package publicData;

import java.util.EventObject;

/**
 * @author shipeng
 *
 * ���۸��޸�ʱ�������¼�,������ʱˢ��������ʾ
 */
public class RemainEvent extends EventObject{

	public RemainEvent(Object arg0) {
		super(arg0);
		System.out.println("��� event !");
	}

}
