package com.nt.Date_Time;

public class DateAndTime_Test {

	public static void main(String[] args) {
		
		java.util.Date udate = new java.util.Date();
		System.out.println("util date "+udate);
		long ms = udate.getTime();
		java.sql.Date sdate = new java.sql.Date(ms);
		System.out.println(sdate);
		
		
		
		
		
		

	}

}
