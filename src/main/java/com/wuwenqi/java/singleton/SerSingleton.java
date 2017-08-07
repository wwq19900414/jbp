package com.wuwenqi.java.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * <pre>
 * 一、一个可以被串行化的单例
 * </pre>
 * 
 * @author wuwenqi
 * 
 */
public class SerSingleton implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2357766146180994465L;

	private SerSingleton() {
		long now = System.currentTimeMillis();
		System.out.println("SerSingleton is create,instance " + now);
	}

	private static SerSingleton instance = new SerSingleton();

	public static SerSingleton getInstance() {
		return instance;
	}

	public static void createString() {
		System.out.println("createString in SerSingleton");
	}

	/**
	 * <pre>
	 * 注意：
	 * 没有这个方法，则经过序列化和反序列化之后，不再是同一个实例。 
	 * 有了这个方法，则经过序列化和反序列化之后，还是同一个实例
	 * 
	 * 事实上，在可串行化的类中，实现了私有的readResolve()方法后，readObject()已经形同虚设
	 * 它直接使用readResolve()方法替换了原本的返回值，从而在形式上构成了单例
	 * </pre>
	 * 
	 * @return
	 */
	private Object readResolve() {
		return instance;
	}

	public static void main(String[] wwq) throws Exception {
		SerSingleton s1 = null;
		SerSingleton s = SerSingleton.getInstance();

		// 先将实例串行化到文件
		FileOutputStream fos = new FileOutputStream("D:/var/SerSingleton.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s);
		oos.flush();
		oos.close();

		// 从文件独处原有的单例类
		FileInputStream fis = new FileInputStream("D:/var/SerSingleton.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		s1 = (SerSingleton) ois.readObject();

		System.out.println(s.equals(s1));
	}
}
