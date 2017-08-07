package com.wuwenqi.java.singleton;

public class Main implements Runnable {

	public static void main(String[] wwq) {
		for (int i = 0; i < 5; i++) {
			new Thread(new Main()).start();
		}
	}

	// 测试LazySingleton和Singleton的性能，这里模拟多线程环境
	@Override
	public void run() {
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			Singleton.getInstance();
		}
		long t2 = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			LazySingleton.getInstance();
		}
		long t3 = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			StaticSingleton.getInstance();
		}
		long t4 = System.currentTimeMillis();

		System.out.println(t2 - t1 + "ms Singleton");
		System.out.println(t3 - t2 + "ms LazySingleton");
		System.out.println(t4 - t3 + "ms StaticSingleton");

	}

}
