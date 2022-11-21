package com.nt.practice;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InsertDateAndTime_MySql {
	private static final String INSERT_QUERY = "INSERT INTO CUSTOMER_INFO(CNAME,BILLAMT,DOB,TOP,ORDER_DATE_TIME)VALUES(?,?,?,?,?)";

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj414db1", "root", "root");
				PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
				
				){
			String name = null,dbirth = null,time = null,order_dateTime = null;
			float billamount = 0.0f;
			if(sc!=null) {
				System.out.println("Enter the name:::::");
				name = sc.next();
				System.out.println("Enter the billammount::::::");
				billamount = sc.nextFloat();
				System.out.println("Enter Customer date of birth(dd-MM-yyyy):::");
				dbirth = sc.next();
				System.out.println("Enter Customer time of purchage(hh:mm:ss)");
				time = sc.next();
				System.out.println("Enter Customer purchaging Order date and time(dd-MM-yyyy hh:mm:ss):::::::");
				sc.nextLine();
				order_dateTime = sc.nextLine();
			}
			//converting date and time String to java.sql.dateAndTime obj
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udate = sdf.parse(dbirth);
			java.sql.Date sdate = new java.sql.Date(udate.getTime());
			
			
			java.sql.Time stime = java.sql.Time.valueOf(time);
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
			java.util.Date udate1 = sdf1.parse(order_dateTime);
			java.sql.Timestamp stimeStamp = new java.sql.Timestamp(udate1.getTime());
			//set param
			if(ps!=null) {
				ps.setString(1, name);
			    ps.setFloat(2, billamount);
			    ps.setDate(3, sdate);
			    ps.setTime(4, stime);
			    ps.setTimestamp(5, stimeStamp);
			    int count = ps.executeUpdate();
			    if(count ==0)
			    	System.out.println("Record not inserted:::::::::::::::::::::::::");
			    else
			    	System.out.println("Record Inserted:::::::::::::::::::::::::::::");
			    }
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
