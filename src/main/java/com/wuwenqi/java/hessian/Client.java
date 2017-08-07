package com.wuwenqi.java.hessian;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;

public class Client {
	public static void main(String[] args) throws MalformedURLException {
		String url = "http://www.wuwenqi.com/hessian/loveService";

		HessianProxyFactory factory = new HessianProxyFactory();
		ISayService helloService = (ISayService) factory.create(ISayService.class, url);

		String result = helloService.say("wuwenqi");
		System.out.println(result);
	}
}
