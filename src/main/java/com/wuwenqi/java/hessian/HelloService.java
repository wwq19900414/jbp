package com.wuwenqi.java.hessian;

public class HelloService implements ISayService {

	@Override
	public String say(String msg) {
		return "Hello ! " + msg;
	}

}
