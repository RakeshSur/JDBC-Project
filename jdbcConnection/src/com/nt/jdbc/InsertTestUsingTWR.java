//InsertTest.javaUsingTWR.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* Program for insert data into db table*/
public class InsertTestUsingTWR {

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
				Statement st = con.createStatement();
				){
			int empno = 0;
			String ename = null;
			int sal = 0;
			if(sc!=null) {
				System.out.println("Enter empno:: ");
				empno = sc.nextInt();
				System.out.println("Enter Ename:: ");
				ename = sc.next();
				System.out.println("Enter salary");
				sal = sc.nextInt();
			}
			//convert input value as required
		  ename = "'"+ename+"'";
		  //prepare sql query
		  //INSERT INTO TARGET VALUES(200,'RAKESH',40000)
		  String query = "INSERT INTO TARGET VALUES("+empno+","+ename+","+sal+")";
			System.out.println(query);
			//send and execute query
			int count = 0;
			if(st!=null)
				count = st.executeUpdate(query);
			//process the result
			if(count ==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted ");
				
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}//main

}//class
