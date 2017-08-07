package com.wuwenqi.java.flyweight;

public class Main {
	public static void main(String[] wwq) {
		ReportManagerFactory factory = new ReportManagerFactory();
		IReportManager rm = factory.getFinancialReportManager("A");
		System.out.println(rm.createReport());
	}
}
