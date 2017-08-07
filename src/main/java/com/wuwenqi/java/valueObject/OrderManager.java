package com.wuwenqi.java.valueObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OrderManager extends UnicastRemoteObject implements IOrderManager {

	private static final long serialVersionUID = 3811969352637679054L;

	protected OrderManager() throws RemoteException {
		super();
	}

	@Override
	public Order getOrder(int id) throws RemoteException {
		Order o = new Order();
		o.setClientName("wuwenqi");
		o.setNumber(25);
		o.setProductName("car");
		return o;
	}

	@Override
	public String getClientName(int id) throws RemoteException {
		return "wuwenqi";
	}

	@Override
	public String getProdName(int id) throws RemoteException {
		return "car";
	}

	@Override
	public int getNumber(int id) throws RemoteException {
		return 25;
	}

}
