package com.nit.practice;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCDriverConnection {
	
	public static void main(String[] args) throws Exception {
		//activate oracle thin software by registering jdbc driver
		//class object with driverManager service
		//create jdbc driver class object
		oracle.jdbc.driver.OracleDriver driver = new oracle.jdbc.driver.OracleDriver();
		//register with driver Manager Services
		DriverManager.registerDriver(driver);
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
		if(con==null) 
			System.out.println("Connection not establish");
		else
			System.out.println("Connection Establish");
			
	
		
		
	}

}
