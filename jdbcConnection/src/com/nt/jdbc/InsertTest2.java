//InsertTest.javaUsingTWR.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest2 {
	/* Program for insert data into db table*/

	public static void main(String[] args) {
	try(Scanner sc = new Scanner(System.in);
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			Statement st = con.createStatement()
			){
		int sno = 0;
		String loc = null;
		int sal = 0;
		if(sc!=null) {
			System.out.println("Enter sno:: ");
			sno = sc.nextInt();
			System.out.println("Enter location:: ");
			loc = sc.next();
			System.out.println("Enter salary:: ");
			sal = sc.nextInt();
		}
		//convert input value as required
		loc = "'"+loc+"'";
		//prepare sql query
		//INSERT INTO TEST VALUES(10,'JAL',20000);
		String query = "INSERT INTO TEST VALUES("+sno+","+loc+","+sal+")";
		System.out.println(query);
		//send and execute query
		int count =0;
		if(st!=null)
			count = st.executeUpdate(query);
		//process the result
		if(count==0)
			System.out.println("Record not inserted");
		else
			System.out.println("Record inserted");
		
	}
	catch(SQLException se) {
		se.printStackTrace();
	}
	catch(Exception e) {
		e.printStackTrace();
	}

	}

}
