/*
 * Created on 2013-8-27
 *
 */
package publicData;

import java.util.EventObject;

/**
 * @author shipeng
 * 自定义事件，用户有登录或者下线时触发该事件
 */
public class MyEvent extends EventObject {

	
	public MyEvent(Object arg0) {
		super(arg0);
		System.out.println("有用户登录或者下线");
	}

}
