package com.wuwenqi.java.singleton;

/**
 * <pre>
 * 一、有延迟加载的单例实现:
 * 1、一个private访问级别的构造函数，只有这样，才能确保单例不会在系统的其他代码内被实例化，这点相当重要
 * 2、静态成员变量 instance初始值为null，确保系统启动时不会有额外的负载。
 * 3、getInstance()方法必须是static的，而且是同步的synchronized，方法内，判断单例是否存在，存在则返回，不存在则建立单例
 * 
 * 二、优缺点：
 * 1、优点：有延迟加载的单例实现。
 * 2、不足：引入了同步关键词synchronized，在多线程环境下，有线程同步消耗，耗时大大增加，得不偿失。
 * </pre>
 * 
 * @author wuwenqi
 * 
 */
public class LazySingleton {
	private LazySingleton() {
		long now = System.currentTimeMillis();
		System.out.println("LazySingleton is create,instance " + now);
	}

	private static LazySingleton instance = null;

	public static synchronized LazySingleton getInstance() {
		if (instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}

	public static void main(String[] wwq) {
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			Singleton.getInstance();
		}
		long t2 = System.currentTimeMillis();

		for (int i = 0; i < 10000; i++) {
			getInstance();
		}
		long t3 = System.currentTimeMillis();
		System.out.println(t2 - t1 + "ms");
		System.out.println(t3 - t2 + "ms");
	}
}
