//SelectStudent.java
package com.nit.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectStudent {

	public static void main(String[] args) throws Exception {
		//active oracle thin s/w by registring jdbc driver
		//create obj with driverManager service
		//create jdbcDriver class obj
		oracle.jdbc.driver.OracleDriver driver= new oracle.jdbc.driver.OracleDriver();
		//register with driver Manager Services
		DriverManager.registerDriver(driver);
		//establish the connection with oracle d/b
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
		Statement st = con.createStatement();
		//send and execute sql queries 
		ResultSet rs = st.executeQuery("SELECT * FROM STUDENT");
		//process the result
		while(rs.next()!=false) {
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
		}
		//close jdbc obj
		rs.close();
		st.close();
		con.close(); 
	
		
		

	}

}
