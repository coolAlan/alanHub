/*
 * Created on 2013-8-27
 *
 */
package publicData;

import java.util.EventObject;

/**
 * @author shipeng
 * 自定义事件，当一卡通的挂失状态改变时触发该事件
 */
public class MyEvent extends EventObject {

	
	public MyEvent(Object arg0) {
		super(arg0);
		System.out.println("挂失 event !");
	}

}
