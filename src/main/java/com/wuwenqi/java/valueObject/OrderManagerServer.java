package com.wuwenqi.java.valueObject;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class OrderManagerServer {
	public static void main(String[] wwq) {
		try {
			LocateRegistry.createRegistry(1099);
			IOrderManager userManager = new OrderManager();
			Naming.rebind("OrderManager", userManager);
			System.out.println("OrderManager is ready");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
