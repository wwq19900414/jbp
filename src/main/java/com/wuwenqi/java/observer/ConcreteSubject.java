package com.wuwenqi.java.observer;

import java.util.Vector;

public class ConcreteSubject implements ISubject {
	Vector<IObserver> observers = new Vector<IObserver>();

	@Override
	public void attach(IObserver observer) {
		observers.add(observer);

	}

	@Override
	public void detach(IObserver observer) {
		observers.removeElement(observer);

	}

	@Override
	public void inform() {
		Event event = new Event();
		for (IObserver observer : observers) {
			observer.update(event);
		}

	}

}
