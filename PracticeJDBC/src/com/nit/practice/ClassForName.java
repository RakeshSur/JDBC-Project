package com.nit.practice;

import java.sql.Connection;
import java.sql.DriverManager;

public class ClassForName {

	public static void main(String[] args) throws Exception {
		//load jdbc driver class
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection with oracle d/b
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
		if(con==null)
			System.out.println("Connection not established");
		else
			System.out.println("Connection is established");
	}

}
