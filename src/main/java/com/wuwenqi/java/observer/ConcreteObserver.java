package com.wuwenqi.java.observer;

import java.util.Random;

/**
 * <pre>
 * 具体的观察者实现，当其监听的状态发生改变时，update方法就会被主题(被观察者)回调。 
 * 因而可以在update方法内进行业务逻辑的处理
 * </pre>
 * 
 * @author wuwenqi
 * 
 */
public class ConcreteObserver implements IObserver {

	@Override
	public void update(Event evt) {
		Random r = new Random();
		System.out.println("observer " + r.nextInt(1000) + " receives informations");
	}

}
