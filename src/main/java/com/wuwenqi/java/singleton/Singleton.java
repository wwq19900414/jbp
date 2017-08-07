package com.wuwenqi.java.singleton;

/**
 * <pre>
 * 一、简单的单例实现:
 * 1、一个private访问级别的构造函数，只有这样，才能确保单例不会在系统的其他代码内被实例化，这点相当重要
 * 2、instance成员变量和getInstance()方法必须是static的
 * 
 * 二、优缺点：
 * 1、优点：实现简单，十分可靠。
 * 2、不足：无法对instance实例做延迟加载。
 *    假如单例是复杂的大对象，创建过程很慢。
 *    由于instance成员变量是static的，在JVM加载这个类时就会被初始化，而不管是否会被用到
 * </pre>
 * 
 * @author wuwenqi
 * 
 */
public class Singleton {
	private Singleton() {
		long now = System.currentTimeMillis();
		System.out.println("Singleton is create,instance " + now);
	}

	private static Singleton instance = new Singleton();

	public static Singleton getInstance() {
		return instance;
	}

	public static void main(String[] wwq) {
		// 模拟单例类扮演一个完全无用的角色，还是会被实例化
		System.out.println("我就是不用单例的对象干正经事，还是会实例化单例哦");
	}
}
