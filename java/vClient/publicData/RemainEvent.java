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
 * 当价格修改时触发该事件,用来及时刷新余额的显示
 */
public class RemainEvent extends EventObject{

	public RemainEvent(Object arg0) {
		super(arg0);
		System.out.println("余额 event !");
	}

}
