package com.wuwenqi.java.valueObject;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			IOrderManager userManager = (IOrderManager) Naming.lookup("OrderManager");

			long t1 = System.currentTimeMillis();
			for (int i = 0; i < 1000; i++) {
				userManager.getOrder(i);
			}

			long t2 = System.currentTimeMillis();
			System.out.println("getOrder spend " + (t2 - t1) + "ms");

			for (int i = 0; i < 1000; i++) {
				userManager.getClientName(i);
				userManager.getNumber(i);
				userManager.getProdName(i);
			}

			long t3 = System.currentTimeMillis();
			System.out.println("3 method call spend " + (t3 - t2) + "ms");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
