package com.wuwenqi.java.observer;

/**
 * 观察者接口
 * 
 * @author wuwenqi
 * 
 */
public interface IObserver {
	/**
	 * 更新观察者
	 * 
	 * @param evt
	 */
	void update(Event evt);
}
