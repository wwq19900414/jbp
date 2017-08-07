package com.wuwenqi.java.observer;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.wuwenqi.java.hessian.ISayService;

/**
 * <pre>
 * 一个简单的自定义观察者模式的实现，涉及到四个对象：
 * ISubject
 * ConcreteSubject
 * 
 * IObserver
 * ConcreteObserver
 * </pre>
 * 
 * @author wuwenqi
 * 
 */
public class Main {
	public static void main(String[] wwq) throws MalformedURLException {
		// ISubject subject = new ConcreteSubject();
		//
		// IObserver ob1 = new ConcreteObserver();
		// IObserver ob2 = new ConcreteObserver();
		// IObserver ob3 = new ConcreteObserver();
		//
		// subject.attach(ob1);
		// subject.attach(ob2);
		// subject.attach(ob3);
		//
		// subject.inform();

		String url = "http://localhost/system/hessian/helloService";
		System.out.println(url);
		HessianProxyFactory factory = new HessianProxyFactory();
		ISayService helloService = (ISayService) factory.create(ISayService.class, url);
		System.out.println(helloService.say("wuwenqi"));

		url = "http://localhost/system/hessian/loveService";
		System.out.println(url);
		ISayService loveService = (ISayService) factory.create(ISayService.class, url);
		System.out.println(loveService.say("wuwenqi"));
	}
}
