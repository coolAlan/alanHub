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

//����������������������������������������������������������
public interface IServerSrv extends Runnable{
	//�̵߳�����
	void start();

	void close();

	boolean isClosed();
}
