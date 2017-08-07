package com.wuwenqi.java.hessian;

public class LoveService implements ISayService {

	@Override
	public String say(String msg) {
		return "I love you ! " + msg;
	}

}
