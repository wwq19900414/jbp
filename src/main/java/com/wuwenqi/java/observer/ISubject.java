package com.wuwenqi.java.observer;

/**
 * <pre>
 * 主题（被观察对象）接口，通常有如下三个典型的方法：
 * 1、增加观察者
 * 2、删除观察者
 * 3、状态发生变化时，通知观察者
 * </pre>
 * 
 * @author wuwenqi
 * 
 */
public interface ISubject {
	/**
	 * 添加观察者
	 * 
	 * @param observer
	 */
	void attach(IObserver observer);

	/**
	 * 删除观察者
	 * 
	 * @param observer
	 */
	void detach(IObserver observer);

	/**
	 * 通知所有的观察者
	 */
	void inform();
}
