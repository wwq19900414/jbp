package com.wuwenqi.java.singleton;

/**
 * <pre>
 * 一、有延迟加载,且没有同步线程消耗的单例实现:
 * 1、一个private访问级别的构造函数，只有这样，才能确保单例不会在系统的其他代码内被实例化，这点相当重要
 * 2、使用一个static内部类，内部类持有单例的实例。单例类被加载时，内部内并不会被初始化。
 * 3、getInstance()方法必须是static的，方法内，返回内部类持有的单例
 * 
 * 二、优缺点：
 * 1、优点：既有延迟加载，也不必使用同步关键词，是一种比较完善的实现。
 * 2、极少情况下的例外：通过反射机制强行调用私有的构造方法来生成多个实例。序列化和反序列化时。
 * </pre>
 * 
 * @author wuwenqi
 * 
 */
public class StaticSingleton {
	private StaticSingleton() {
		long now = System.currentTimeMillis();
		System.out.println("StaticSingleton is create,instance " + now);
	}

	private static class SingletonHolder {
		private static StaticSingleton instance = new StaticSingleton();
	}

	public static StaticSingleton getInstance() {
		return SingletonHolder.instance;
	}

	public static void main(String[] wwq) {
		System.out.println("不用单例，这种实现会不会创建单例呢？不会");
		getInstance();
	}
}
