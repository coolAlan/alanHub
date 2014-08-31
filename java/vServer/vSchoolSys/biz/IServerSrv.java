/*
 * Created on 2013-8-21
 *
 * 
 * 
 */
package vSchoolSys.biz;

/**
 * @author shipeng
 */

//。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
public interface IServerSrv extends Runnable{
	//�潦無墜�強
	void start();

	void close();

	boolean isClosed();
}
