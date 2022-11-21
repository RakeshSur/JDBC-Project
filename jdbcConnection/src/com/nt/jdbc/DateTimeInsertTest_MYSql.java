//DateTimeInsertTest_MYSql.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateTimeInsertTest_MYSql {
	private static final String INSERT_CUSTOMER_QUERY = "INSERT INTO CUSTOMER_INFO (CNAME,BILLAMT,DOB,TOP,ORDER_DATE_TIME)VALUES(?,?,?,?,?)";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj414db1", "root", "root");
				PreparedStatement ps = con.prepareStatement(INSERT_CUSTOMER_QUERY);
				Scanner sc = new Scanner(System.in);
				){
			//read the input
			String name = null , dob = null, top = null,orderDate=null;
			float billamt = 0.0f;
			if(sc!=null && ps!=null) {
				System.out.println("Enter the coustmer name:: ");
				name = sc.next();
				System.out.println("Enter the billAmmount:: ");
				billamt = sc.nextFloat();
				System.out.println("Enter the DOB(dd-MM-yyyy):: ");
				dob = sc.next();
				System.out.println("Enter the TOP(hh:mm:ss) :: ");
				top = sc.next();
				System.out.println("Enter the orderDateTIme(dd-MM-YYYY hh:mm:ss) :: ");
				sc.nextLine();
				orderDate = sc.nextLine();
				//converting string DOB to java.sql.date class obj
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date udob = sdf.parse(dob);
				java.sql.Date sqdob = new java.sql.Date(udob.getTime());
				
				//converting String ( TOP ) to java.sql.time class obj
				java.sql.Time sqtop =  java.sql.Time.valueOf(top);
				
				//converting String orderdateTime into java.sql.TimeStamp classs obj
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				java.util.Date uorderdt = sdf1.parse(orderDate);
				java.sql.Timestamp sqorderdt = new java.sql.Timestamp(uorderdt.getTime());
				
				//set sql value to params
				
				ps.setString(1, name); ps.setFloat(2, billamt); ps.setDate(3, sqdob); ps.setTime(4, sqtop);  ps.setTimestamp(5, sqorderdt);
				//execute the sql query
				int count = ps.executeUpdate();
				if(count==0) {
					System.out.println("Record not inserted:: ");
				}
				else {
					System.out.println("Record inserted:: ");
				}
				
				
				
				
			}
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
