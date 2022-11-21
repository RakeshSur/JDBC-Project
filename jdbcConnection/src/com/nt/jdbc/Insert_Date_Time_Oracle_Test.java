package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Insert_Date_Time_Oracle_Test {
	private static final String INSERT_DATE_TIME_QUERY = "INSERT INTO CUSTOMER_INFO(CNAME,BILLAMT,DOB,TOP,ORDER_DATE_TIME) VALUES(?,?,?,?,?)";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj414db1", "root", "root");
				PreparedStatement ps = con.prepareStatement(INSERT_DATE_TIME_QUERY);

		) {
			String name = null;
			float billamt = 0.0f;
			String dob = null;
			String top = null;
			String order_date_time = null;
			if (sc != null) {
				System.out.println("Enter the detalis");
				System.out.println("Enter the name of the customer::  ");
				name = sc.next();
				System.out.println("Enter the billing ammount :: ");
				billamt = sc.nextFloat();
				System.out.println("Enter the date of birth (dd-MM-yyy):: ");
				dob = sc.next();
				System.out.println("Enter the time of purchaing (hh:mm:ss):: ");
				top = sc.next();
				System.out.println("Enter the order date ann time(dd-MM-YYYY hh:mm:ss) ");
				sc.nextLine();
				order_date_time = sc.nextLine();

				// convert time and date String into java.sql.time obj

				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date udate = sdf.parse(dob);
				java.sql.Date sdate = new java.sql.Date(udate.getTime());

				// time
				java.sql.Time stop = java.sql.Time.valueOf(top);

				// timestamp
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				java.util.Date udatetime = sdf2.parse(order_date_time);
				java.sql.Timestamp stimestamp = new java.sql.Timestamp(udatetime.getTime());

				if (ps != null) {
					ps.setString(1, name);
					ps.setFloat(2, billamt);
					ps.setDate(3, sdate);
					ps.setTime(4, stop);
					ps.setTimestamp(5, stimestamp);
					int result = ps.executeUpdate();
					if (result == 0) {
						System.out.println("Record not inserted:::");
					} else {
						System.out.println("Record inesrted::");
					}
				}

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
